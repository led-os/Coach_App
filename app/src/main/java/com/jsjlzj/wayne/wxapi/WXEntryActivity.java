package com.jsjlzj.wayne.wxapi;

import android.text.TextUtils;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.ui.MainActivity;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.basis.LoginByPhoneActivity;
import com.jsjlzj.wayne.ui.basis.LoginRoleSelectActivity;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseNoLoginActivity;
import com.jsjlzj.wayne.ui.mvp.relizelogin.LoginActivityPresenter;
import com.jsjlzj.wayne.ui.mvp.relizelogin.LoginActivityView;
import com.jsjlzj.wayne.ui.store.attestation.AttestationActivity;
import com.jsjlzj.wayne.ui.store.attestation.AttestationInfoActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SPUtil;
import com.jsjlzj.wayne.utils.eventbus.EnumEventBus;
import com.jsjlzj.wayne.utils.eventbus.MdlEventBus;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.jsjlzj.wayne.constant.HttpConstant.WXAPPID;
import static com.jsjlzj.wayne.constant.HttpConstant.WXAPPSECET;

public class WXEntryActivity extends MVPBaseNoLoginActivity<LoginActivityView, LoginActivityPresenter> implements LoginActivityView, IWXAPIEventHandler {
    private IWXAPI iwxapi;
    private String access = "";
    private String openId = "";
    private String headImg = "";
    private String wxName = "";
    private String gender = "";
    private Call call;
    private Map<Object, Object> map;

    @Override
    protected int getLayoutResId() {
        return R.layout.main_layout;
    }

    @Override
    protected void initViewAndControl() {
        iwxapi = WXAPIFactory.createWXAPI(this, WXAPPID, false);
        iwxapi.handleIntent(getIntent(), this);
        iwxapi.registerApp(WXAPPID);
    }

    @Override
    protected LoginActivityPresenter createPresenter() {
        return new LoginActivityPresenter(this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                if(baseResp.getType()==1){
                    String code = ((SendAuth.Resp) baseResp).code;
                    getAccessToken(code);
                }else {
                    LogAndToastUtil.toast("分享成功");
                    finish();
                }
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                if(baseResp.getType()==1) {
                    LogAndToastUtil.toast("您已拒绝授权");
                }else{
                    LogAndToastUtil.toast("分享被拒绝");
                }
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                if(baseResp.getType()==1) {
                    LogAndToastUtil.toast("您已取消授权");
                }else{
                    LogAndToastUtil.toast("取消分享");
                }
                finish();
                break;
            default:
                finish();
                break;
        }

    }

    public void initHttp(String url) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        call = okHttpClient.newCall(request);
    }

    public void getAccessToken(String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WXAPPID + "&secret=" + WXAPPSECET + "&code=" + code + "&grant_type=authorization_code";
        initHttp(url);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {}

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseInfo = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(responseInfo);
                    access = jsonObject.getString("access_token");
                    openId = jsonObject.getString("openid");
                    getUserInfo();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void getUserInfo() {
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access + "&openid=" + openId + "&lang=zh_CN";
        initHttp(url);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (!TextUtils.isDigitsOnly(openId)) {
                    presenter.getByOpenid(openId);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseInfo = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(responseInfo);
                    headImg = jsonObject.getString("headimgurl");
                    wxName = jsonObject.getString("nickname");
                    gender = jsonObject.getString("sex");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (!TextUtils.isDigitsOnly(openId)) {
                    if (MyApp.idBindWeChat == 1) {
                        if (null == map) map = new HashMap<>();
                        map.clear();
                        map.put("openid", openId);
                        map.put("wxName", wxName);
                        presenter.bindWeChat(map);
                    } else {
                        presenter.getByOpenid(openId);
                    }
                }
            }
        });
    }

    @Override
    public void showResultGetByOpenid(MdlBaseHttpResp<MdlUser> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            MdlUser.MdlUserBean bean = resp.getData().getData();
            MyApp.ME.user = bean;
            SPUtil.saveToken2SP(bean.getToken());
            SPUtil.saveUser2SP(MyApp.user);
            if (TextUtils.isEmpty(bean.getAccountType())) {
                LoginByPhoneActivity.go2This(this, "", openId, headImg, gender, wxName);
            } else {
                switch (bean.getAccountType()) {
                    case "NONE":
                        LoginRoleSelectActivity.go2This(this, true);
                        break;
                    case "STORE":
                        switch (bean.getStoreStatus()) {
                            case 1:
                                AttestationInfoActivity.go2this(WXEntryActivity.this, 1);
                                break;
                            case 3:
                                MainActivity.go2this(this, true);
                                break;
                            default:
                                AttestationActivity.go2this(WXEntryActivity.this);
                                break;
                        }
                        break;
                    case "TRAINER":
                        MainActivity.go2this(this, false);
                        break;
                    default:
                }
            }
        } else {
            LoginByPhoneActivity.go2This(this, "", openId, headImg, gender, wxName);
        }
    }

    @Override
    public void bindWeChat(MdlBaseHttpResp<MdlUser> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            LogAndToastUtil.toast("绑定成功");
            MyApp.user = resp.getData().getData();
            SPUtil.saveUser2SP(MyApp.user);
            SPUtil.saveToken2SP(MyApp.user.getToken());
            EventBus.getDefault().post(new MdlEventBus(EnumEventBus.MESSAGE_BIND_WX,resp.getData().getData().getWxName()));
            finish();
        } else {
            LogAndToastUtil.toast(resp.getMsg());
        }
    }
}

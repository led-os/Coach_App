package com.jsjlzj.wayne.ui.basis;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.constant.MyPermissionConstant;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.ui.MainActivity;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseNoLoginActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizelogin.LoginActivityPresenter;
import com.jsjlzj.wayne.ui.mvp.relizelogin.LoginActivityView;
import com.jsjlzj.wayne.ui.store.attestation.AttestationActivity;
import com.jsjlzj.wayne.ui.store.attestation.AttestationInfoActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.RegexUtil;
import com.jsjlzj.wayne.utils.SPUtil;
import com.jsjlzj.wayne.utils.permission.PermissionUtil;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import static com.jsjlzj.wayne.constant.HttpConstant.WXAPPID;

/**
 * @description 手机号登陆
 * @date: 2019/12/23
 * @author: 曾海强
 */
public class LoginByPhoneActivity extends MVPBaseNoLoginActivity<LoginActivityView, LoginActivityPresenter> implements LoginActivityView {
    public static final String LOGIN_OPENID = "openid";
    public static final String LOGIN_HEADIMG = "headImg";
    public static final String LOGIN_GENDER = "gender";
    public static final String LOGIN_WXNAME = "wxName";
    public static final String LOGIN_PGHONE = "phone";
    private String openid;
    private String headImg;
    private String gender;
    private String wxName;
    private ImageView checkBox;
    private boolean isSelect = false;

    public static void go2This(Activity context, String phone, String openid, String headImg, String gender, String wxName) {
        Intent intent = new Intent(context, LoginByPhoneActivity.class);
        intent.putExtra(LOGIN_OPENID, openid);
        intent.putExtra(LOGIN_HEADIMG, headImg);
        intent.putExtra(LOGIN_GENDER, gender);
        intent.putExtra(LOGIN_WXNAME, wxName);
        intent.putExtra(LOGIN_PGHONE, phone);
        context.startActivity(intent);
        context.finish();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.actvity_login_phone;
    }

    private EditText edPhone;
    private TextView btnNext, btnPwdLogin, btnNode, bindPhone;
    private TextView btnWechat;
    private IWXAPI api;

    @Override
    protected void initViewAndControl() {
        //注意reqCode后面+PIC；是为了区分这个请求是团队图片发出的，还是团队头像发出的
        PermissionUtil.checkPermission(this,
                MyPermissionConstant.READ_EXTERNAL_STORAGE,
                Manifest.permission.SYSTEM_ALERT_WINDOW,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_CHECKIN_PROPERTIES,
                Manifest.permission.CAMERA,
                Manifest.permission.CONTROL_LOCATION_UPDATES,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CALL_PHONE
        );
    }

    private void init() {
        api = WXAPIFactory.createWXAPI(this, WXAPPID, false);
        openid = getIntent().getStringExtra(LOGIN_OPENID);
        headImg = getIntent().getStringExtra(LOGIN_HEADIMG);
        gender = getIntent().getStringExtra(LOGIN_GENDER);
        wxName = getIntent().getStringExtra(LOGIN_WXNAME);
        checkBox = findView(R.id.box2);
        if (!SPUtil.getCheckbox()) {
            isSelect = false;
            checkBox.setImageDrawable(ContextCompat.getDrawable(LoginByPhoneActivity.this, R.drawable.all_noselectm));
        }else {
            isSelect = true;
            checkBox.setImageDrawable(ContextCompat.getDrawable(LoginByPhoneActivity.this,R.drawable.all_selectm));
        }
        initView();
    }

    @Override
    public void permissionSuccess(int permissionReqCode) {
        super.permissionSuccess(permissionReqCode);
        switch (permissionReqCode) {
            case MyPermissionConstant.READ_EXTERNAL_STORAGE:
                init();
                break;
        }

    }

    @Override
    public void permissionFail(int permissionReqCode) {
        super.permissionFail(permissionReqCode);
        switch (permissionReqCode) {
            case MyPermissionConstant.READ_EXTERNAL_STORAGE:
                init();
                break;
        }
    }

    @Override
    protected LoginActivityPresenter createPresenter() {
        return new LoginActivityPresenter(this);
    }

    /**
     *
     */
    private void initView() {
        edPhone = findView(R.id.edPhone);
        btnNext = findView(R.id.btnNext);
        btnPwdLogin = findView(R.id.btnPwdLogin);
        btnWechat = findView(R.id.btnWechat);
        btnNode = findView(R.id.btnNode);
        bindPhone = findView(R.id.bindPhone);

        btnNext.setOnClickListener(onClickListner);
        btnWechat.setOnClickListener(onClickListner);
        btnNode.setOnClickListener(onClickListner);
        checkBox.setOnClickListener(onClickListner);

        btnPwdLogin.setOnClickListener(onClickListner);
        edPhone.addTextChangedListener(watcherEdPhone);
        if (!TextUtils.isEmpty(openid)) {
            btnPwdLogin.setVisibility(View.GONE);
            btnWechat.setVisibility(View.GONE);
            btnNode.setVisibility(View.GONE);
            bindPhone.setText("绑定手机号");
        } else {
            btnPwdLogin.setVisibility(View.VISIBLE);
            btnWechat.setVisibility(View.VISIBLE);
            bindPhone.setText("请输入手机号码");
            btnNode.setVisibility(View.VISIBLE);
        }
        String node = "勾选即代表同意<font color=\'#5E9DEF\'>" + "《用户隐私声明》" + "</font>";
        btnNode.setText(Html.fromHtml(node));
    }

    private MyOnClickListner onClickListner = new MyOnClickListner();

    private class MyOnClickListner extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnWechat:
                    checkInstalledWX();
                    break;
                case R.id.btnNext://下一步
                    if (!isSelect) {
                        LogAndToastUtil.toast(view.getContext(), "请先阅读下方《用户隐私声明》");
                        return;
                    }
                    SPUtil.saveCheckbox();
                    done(edPhone.getText().toString());
                    break;
                case R.id.btnPwdLogin://转到 账号密码登录
                    LoginByPwdActivity.go2This(LoginByPhoneActivity.this, edPhone.getText() + "");
                    break;
                case R.id.box2:
                    if (isSelect) {
                        isSelect = false;
                        checkBox.setImageDrawable(ContextCompat.getDrawable(LoginByPhoneActivity.this, R.drawable.all_noselectm));
                    } else {
                        isSelect = true;
                        checkBox.setImageDrawable(ContextCompat.getDrawable(LoginByPhoneActivity.this, R.drawable.all_selectm));
                    }
                    break;
                case R.id.btnNode:
                    JsjlAgreementActivity.go2this2(LoginByPhoneActivity.this);
//                    WebViewActivity.go2this(LoginByPhoneActivity.this);
                    break;
                default:
                    break;
            }
        }
    }

    public void checkInstalledWX() {
        if (!api.isWXAppInstalled()) {
            LogAndToastUtil.toast("您的设备未安装微信客户端");
        } else {
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk";
            api.sendReq(req);
            finish();
        }
    }

    private TextWatcher watcherEdPhone = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (null != s && s.length() == 11) {
                btnNext.setSelected(RegexUtil.isMobile(s.toString()));
            } else btnNext.setSelected(false);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    private void done(String account) {
        if (!RegexUtil.isMobile(account)) {
            LogAndToastUtil.toast(this, "请输入正确的账号");
            return;
        }
        LoginByPhoneSmesActivity.go2This(this, account, openid, headImg, gender, wxName);
        finish();
    }

    public void showSelectStoreUserInfo(MdlBaseHttpResp<MdlUser> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            MyApp.user = resp.getData().getData();
            SPUtil.saveToken2SP(MyApp.user.getToken());
            SPUtil.saveUser2SP(MyApp.user);
            if (!TextUtils.isEmpty(MyApp.user.getAccountType())) {
                switch (MyApp.user.getAccountType()) {
                    case "NONE":
                        LoginRoleSelectActivity.go2This(this, true);
                        break;
                    case "STORE":
                        switch (MyApp.user.getStoreStatus()) {
                            case 1:
                                AttestationInfoActivity.go2this(LoginByPhoneActivity.this, 1);
                                break;
                            case 3:
                                MainActivity.go2this(this, true);
                                break;
                            default:
                                AttestationActivity.go2this(LoginByPhoneActivity.this);
                                break;
                        }
                        break;
                    case "TRAINER":
                        MainActivity.go2this(this, false);
                        break;
                }
            }
        }
    }
}

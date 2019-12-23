package com.jsjlzj.wayne.ui.basis;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.ui.MainActivity;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseNoLoginActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizelogin.LoginActivityPresenter;
import com.jsjlzj.wayne.ui.mvp.relizelogin.LoginActivityView;
import com.jsjlzj.wayne.ui.store.attestation.AttestationActivity;
import com.jsjlzj.wayne.ui.store.attestation.AttestationInfoActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SPUtil;
import com.jsjlzj.wayne.widgets.text.CountDownTextView;
import com.jsjlzj.wayne.widgets.text.VerifyCodeView;

import java.util.HashMap;
import java.util.Map;

import static com.jsjlzj.wayne.ui.basis.LoginByPhoneActivity.LOGIN_GENDER;
import static com.jsjlzj.wayne.ui.basis.LoginByPhoneActivity.LOGIN_HEADIMG;
import static com.jsjlzj.wayne.ui.basis.LoginByPhoneActivity.LOGIN_OPENID;
import static com.jsjlzj.wayne.ui.basis.LoginByPhoneActivity.LOGIN_WXNAME;

public class LoginByPhoneSmesActivity extends MVPBaseNoLoginActivity<LoginActivityView, LoginActivityPresenter> implements LoginActivityView {

    public static void go2This(Activity context, String phone, String openid,String headImg,String gender,String wxName) {
        Intent intent = new Intent(context, LoginByPhoneSmesActivity.class);
        intent.putExtra(LoginByPhoneActivity.LOGIN_PGHONE, phone);
        intent.putExtra(LOGIN_OPENID, openid);
        intent.putExtra(LOGIN_HEADIMG, headImg);
        intent.putExtra(LOGIN_GENDER, gender);
        intent.putExtra(LOGIN_WXNAME, wxName);
        context.startActivity(intent);
    }
    public static void go2ThisUnBind(Activity context,String phone,int isBind) {
        Intent intent = new Intent(context, LoginByPhoneSmesActivity.class);
        intent.putExtra(LoginByPhoneActivity.LOGIN_PGHONE, phone);
        intent.putExtra("isBind", isBind);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.actvity_login_phone_smes_find;
    }

    private TextView tvContext, btnLogin;
    private ImageView btnBack;
    private VerifyCodeView edPwd;
    private CountDownTextView btnGetSmes;
    private String strPhone;
    private boolean isFrist = true;
    private Map<Object, Object> mapGetSmes, mapLoginSmes;
    private String openid;
    private String headImg;
    private String gender;
    private String wxName;
    private int isBind;
    @Override
    protected void initViewAndControl() {
        initView();
    }

    @Override
    protected LoginActivityPresenter createPresenter() {
        return new LoginActivityPresenter(this);
    }

    private void initView() {
        mapGetSmes = new HashMap<>();
        strPhone = getIntent().getStringExtra(LoginByPhoneActivity.LOGIN_PGHONE);
        isBind=getIntent().getIntExtra("isBind",0);
        openid=getIntent().getStringExtra(LOGIN_OPENID);
        headImg=getIntent().getStringExtra(LOGIN_HEADIMG);
        gender=getIntent().getStringExtra(LOGIN_GENDER);
        wxName=getIntent().getStringExtra(LOGIN_WXNAME);
        mapGetSmes.put("mobile", strPhone);
        tvContext = findView(R.id.tvContext);
        btnLogin = findView(R.id.btnLogin);
        btnBack = findView(R.id.btnBack);
        edPwd = findView(R.id.edPwd);
        btnGetSmes = findView(R.id.btnGetSmes);


        btnBack.setOnClickListener(onClickListner);
        btnLogin.setOnClickListener(onClickListner);

        edPwd.setInputCompleteListener(new VerifyCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                btnLogin.setSelected(true);
            }

            @Override
            public void invalidContent() {
                btnLogin.setSelected(false);
            }
        });
        btnGetSmes.setNormalText("获取验证码")
                .setCountDownText("重新获取(", ")")
                .setCloseKeepCountDown(true)//关闭页面保持倒计时开关
                .setCountDownClickable(true)//倒计时期间点击事件是否生效开关
                .setShowFormatTime(true)//是否格式化时间
                .setOnCountDownFinishListener(new CountDownTextView.OnCountDownFinishListener() {
                    @Override
                    public void onFinish() {
                        btnGetSmes.setTextColor(getResources().getColor(R.color.login_btn_smes));
                    }
                })
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isFrist) {
                            isFrist = false;
                            btnGetSmes.startCountDown(59);
                            btnGetSmes.setTextColor(getResources().getColor(R.color.gray));
                        } else {
                            LogAndToastUtil.toast("短信已发送", Toast.LENGTH_SHORT);
                            btnGetSmes.startCountDown(59);
                            btnGetSmes.setTextColor(getResources().getColor(R.color.gray));
                        }
                        if(isBind==1){
                            mapGetSmes.put("type", "UNTYING_WECHAT_CODE");
                        }else {
                            if (!TextUtils.isEmpty(openid)) {
                                mapGetSmes.put("type", "WECHAT_LOGIN_CODE");
                            } else {
                                mapGetSmes.put("type", "MOBILE_LOGIN_CODE");
                            }
                        }
                        presenter.getSmes(mapGetSmes);
                    }
                });
        btnGetSmes.performClick();
        tvContext.setText("已向您的手机 " + strPhone + " 发送验证码");
    }

    private MyOnClickListner onClickListner = new MyOnClickListner();

    private class MyOnClickListner extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnLogin://下一步
                    done(edPwd.getEditContent());
                    break;
                case R.id.btnBack://转到 账号密码登录
                    LoginByPhoneActivity.go2This(LoginByPhoneSmesActivity.this,strPhone,openid,headImg,gender,wxName);
                    finish();
                    break;
            }
        }
    }

    private void done(String smes) {
        if (TextUtils.isEmpty(smes)) {
            LogAndToastUtil.toast(this, "请输入正确的账号");
            return;
        }
        if (null == mapLoginSmes) mapLoginSmes = new HashMap<>();
        mapLoginSmes.clear();
        if(isBind==1){
            mapLoginSmes.put("code", smes);
            presenter.unBindWeChat(mapLoginSmes);
        }else {
            if (!TextUtils.isEmpty(openid)) {
                mapLoginSmes.put("code", smes);
                mapLoginSmes.put("mobile", strPhone);
                mapLoginSmes.put("gender", gender);
                mapLoginSmes.put("headImg", headImg);
                mapLoginSmes.put("openid", openid);
                mapLoginSmes.put("wxName", wxName);
                presenter.loginByWX(mapLoginSmes);
            } else {
                mapLoginSmes.put("code", smes);
                mapLoginSmes.put("mobile", strPhone);

                presenter.loginBySmes(mapLoginSmes);
            }
        }
    }

    @Override
    public void showResultLoginBySmsCode(MdlBaseHttpResp<MdlUser> resp) {
        result(resp);
    }

    @Override
    public void showResultLoginByWeChat(MdlBaseHttpResp<MdlUser> resp){
        result(resp);
    }

    @Override
    public void showResultUntyingWeChat(MdlBaseHttpResp<MdlUser> resp) {
        if(resp.getStatus()== HttpConstant.R_HTTP_OK&&null!=resp.getData()&&null!=resp.getData().getData()){
            LogAndToastUtil.toast("解绑成功");
            MyApp.user=resp.getData().getData();
            SPUtil.saveToken2SP( MyApp.user.getToken());
            SPUtil.saveUser2SP(MyApp.user);
            presenter.selectStoreUserInfo(null);
            finish();
        }else{
            LogAndToastUtil.toast(resp.getMsg());
        }
    }

    @Override
    public void showSelectStoreUserInfo(MdlBaseHttpResp<MdlUser> resp) {
        if(resp.getStatus()==HttpConstant.R_HTTP_OK&&null!=resp.getData()&&null!=resp.getData().getData()){
            MyApp.user=resp.getData().getData();
        }

    }

    public void result(MdlBaseHttpResp<MdlUser> resp){
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && resp.getData().getData() != null) {
            MdlUser data = resp.getData();
            MyApp.ME.user = data.getData();
            SPUtil.saveToken2SP(data.getData().getToken());
            SPUtil.saveUser2SP(data.getData());
            switch (data.getData().getAccountType()) {
                case "NONE":
                    LoginRoleSelectActivity.go2This(this, true);
                    break;
                case "STORE":
                    switch (data.getData().getStoreStatus()) {
                        case 1:
                            AttestationInfoActivity.go2this(LoginByPhoneSmesActivity.this,1);
                            break;
                        case 3:
                            MainActivity.go2this(this, true);
                            break;
                        default:
                            AttestationActivity.go2this(LoginByPhoneSmesActivity.this);
                            break;
                    }
                    break;
                case "TRAINER":
                    MainActivity.go2this(this, false);
                    break;
            }

            finish();
        } else {
            LogAndToastUtil.toast(this, resp.getMsg());
        }
    }
}

package com.jsjlzj.wayne.ui.basis;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
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
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;

import static com.jsjlzj.wayne.constant.HttpConstant.WXAPPID;

public class LoginByPwdActivity extends MVPBaseNoLoginActivity<LoginActivityView, LoginActivityPresenter> implements LoginActivityView {
    public static void go2This(Activity context, String phone) {
        Intent intent = new Intent(context, LoginByPwdActivity.class);
        intent.putExtra(LoginByPhoneActivity.LOGIN_PGHONE, phone);
        context.startActivity(intent);
        context.finish();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.actvity_login_pwd;
    }

    private EditText edPhone, edPwd;
    private TextView btnLogin, btnFastLogin, btnForgetPwd;
    private ImageView btnEys, btnWechat;
    private IWXAPI api;


    @Override
    protected void initViewAndControl() {
        api = WXAPIFactory.createWXAPI(this, WXAPPID, false);
        initView();
    }

    @Override
    protected LoginActivityPresenter createPresenter() {
        return new LoginActivityPresenter(this);
    }

    private void initView() {
        edPhone = findView(R.id.edPhone);
        edPwd = findView(R.id.edPwd);
        btnEys = findView(R.id.btnEys);
        btnLogin = findView(R.id.btnLogin);
        btnFastLogin = findView(R.id.btnFastLogin);
        btnForgetPwd = findView(R.id.btnForgetPwd);
        btnWechat = findView(R.id.btnWechat);

        btnEys.setOnClickListener(onClickListner);
        btnLogin.setOnClickListener(onClickListner);
        btnFastLogin.setOnClickListener(onClickListner);
        btnForgetPwd.setOnClickListener(onClickListner);
        btnWechat.setOnClickListener(onClickListner);


        edPhone.addTextChangedListener(watcherEdPhone);
        edPwd.addTextChangedListener(watcherEdPwd);
        String stringExtra = getIntent().getStringExtra(LoginByPhoneActivity.LOGIN_PGHONE);
        if (null != stringExtra) {
            edPhone.setText(stringExtra);
            flagPhone = stringExtra.length() == 11;
        }
    }

    private MyOnClickListner onClickListner = new MyOnClickListner();

    private class MyOnClickListner extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnWechat:
                    checkInstalledWX();
                    break;
                case R.id.btnEys:
                    view.setSelected(!view.isSelected());
                    if (view.isSelected()) {//如果选中，显示密码
                        edPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    } else {//否则隐藏密码
                        edPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                    break;
                case R.id.btnLogin:
                    done(edPhone.getText().toString(), edPwd.getText().toString());
                    break;
                case R.id.btnFastLogin://快捷登录  回到验证码登录
                    LoginByPhoneActivity.go2This(LoginByPwdActivity.this, edPhone.getText() + "","","","","");
                    break;
                case R.id.btnForgetPwd:
                    LoginFindPwdActivity.go2This(LoginByPwdActivity.this, edPhone.getText() + "");
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
                flagPhone = RegexUtil.isMobile(s.toString());
            } else flagPhone = false;
            parseBtnLogin();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private TextWatcher watcherEdPwd = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (null != s) {
                flagPwd = RegexUtil.isPwd(s.toString());
            } else flagPwd = false;
            parseBtnLogin();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private Map<Object, Object> mapLoginPwd;

    private void login(String account, String password) {
        if (null == mapLoginPwd) mapLoginPwd = new HashMap<>();
        mapLoginPwd.put("mobile", account);
        mapLoginPwd.put("password", password);
        presenter.loginByPwd(mapLoginPwd);
    }

    private boolean flagPhone, flagPwd;

    private void parseBtnLogin() {
        if (flagPhone && flagPwd) {
            btnLogin.setSelected(true);
        } else
            btnLogin.setSelected(false);
    }

    private void done(String account, String pwd) {
        if (!RegexUtil.isMobile(account)) {
            LogAndToastUtil.toast(this, "请输入正确的账号");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            LogAndToastUtil.toast(this, "请输入密码");
            return;
        } else if (pwd.length() < 6 || pwd.length() > 10) {
            LogAndToastUtil.toast(this, "请输入6-10位密码");
            return;
        }
        login(account, pwd);
    }

    @Override
    public void showResultLogin(MdlBaseHttpResp<MdlUser> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && resp.getData().getData() != null) {
            MdlUser data = resp.getData();
            MyApp.ME.user = data.getData();
            SPUtil.saveToken2SP(data.getData().getToken());
            SPUtil.saveUser2SP(MyApp.user);
            switch (data.getData().getAccountType()) {
                case "NONE":
                    LoginRoleSelectActivity.go2This(this, true);
                    break;
                case "STORE":
                    switch (data.getData().getStoreStatus()) {
                        case 1:
                            AttestationInfoActivity.go2this(LoginByPwdActivity.this,1);
                            break;
                        case 3:
                            MainActivity.go2this(this, true);
                            break;
                        default:
                            LoginRoleSelectActivity.go2This(LoginByPwdActivity.this,true);
                            break;
                    }
                    break;
                case "TRAINER":
                    MainActivity.go2this(this, false);
                    break;
                default:
            }

            finish();
        } else {
            LogAndToastUtil.toast(this, resp.getMsg());
        }
    }
}

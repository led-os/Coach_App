package com.jsjlzj.wayne.ui.basis;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseNoLoginActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizelogin.LoginActivityPresenter;
import com.jsjlzj.wayne.ui.mvp.relizelogin.LoginActivityView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.RegexUtil;
import com.jsjlzj.wayne.widgets.text.CountDownTextView;

import java.util.HashMap;
import java.util.Map;

public class LoginFindPwdActivity extends MVPBaseNoLoginActivity<LoginActivityView, LoginActivityPresenter> implements LoginActivityView {

    public static void go2This(Activity context, String phone) {
        Intent intent = new Intent(context, LoginFindPwdActivity.class);
        intent.putExtra(LoginByPhoneActivity.LOGIN_PGHONE, phone);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.actvity_login_pwd_find;
    }

    private EditText edPhone, edSmes, edPwd;
    private TextView btnLogin;
    private ImageView btnBack;
    private CountDownTextView btnGetSmes;

    private String strPhone;

    @Override
    protected void initViewAndControl() {
        initView();
    }

    @Override
    protected LoginActivityPresenter createPresenter() {
        return new LoginActivityPresenter(this);
    }

    private void initView() {
        edPhone = findView(R.id.edPhone);
        edSmes = findView(R.id.edSmes);
        edPwd = findView(R.id.edPwd);
        btnBack = findView(R.id.btnBack);
        btnLogin = findView(R.id.btnLogin);
        btnGetSmes = findView(R.id.btnGetSmes);

        edPhone.addTextChangedListener(watcherEdPhone);
        edSmes.addTextChangedListener(watcherEdSmes);
        edPwd.addTextChangedListener(watcherEdPwd);
        btnBack.setOnClickListener(onClickListner);
        btnLogin.setOnClickListener(onClickListner);

        edPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        strPhone = getIntent().getStringExtra(LoginByPhoneActivity.LOGIN_PGHONE);
        if (null != strPhone) {
            edPhone.setText(strPhone);
            flagPhone = strPhone.length() == 1;
        }

        btnGetSmes.setNormalText("获取验证码")
                .setCountDownText("重新获取", "")
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
                        if (!RegexUtil.isMobile(edPhone.getText().toString())) {
                            LogAndToastUtil.toast("请输入正确的号码");
                            return;
                        }
                        LogAndToastUtil.toast("短信已发送", Toast.LENGTH_SHORT);
                        btnGetSmes.startCountDown(59);
                        btnGetSmes.setTextColor(getResources().getColor(R.color.gray));
                        if (mapGetSmes == null) mapGetSmes = new HashMap<>();
                        mapGetSmes.put("mobile", edPhone.getText().toString());
                        mapGetSmes.put("type", "UPDATE_PASSWORD_CODE");
                        presenter.getSmes(mapGetSmes);
                    }
                });
    }

    private MyOnClickListner onClickListner = new MyOnClickListner();

    private class MyOnClickListner extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnBack:
                    finish();
                    break;
                case R.id.btnLogin:
                    done(edPhone.getText().toString(), edSmes.getText().toString(), edPwd.getText().toString());
                    break;
            }
        }
    }

    private TextWatcher watcherEdPhone = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            flagPhone = RegexUtil.isMobile(s.toString());
            parseBtnLogin();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private TextWatcher watcherEdSmes = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            flagSmes = null != s;
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

    private boolean flagPhone, flagSmes, flagPwd;

    private void parseBtnLogin() {
        if (flagPhone && flagSmes && flagPwd) {
            btnLogin.setSelected(true);
        } else
            btnLogin.setSelected(false);
    }

    private Map<Object, Object> mapGetSmes, mapFindPwd;

    private void done(String account, String smes, String pwd) {
        if (!RegexUtil.isMobile(account)) {
            LogAndToastUtil.toast(this, "请输入正确的账号");
            return;
        }
        if (TextUtils.isEmpty(smes)) {
            LogAndToastUtil.toast(this, "请输入验证码");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            LogAndToastUtil.toast(this, "请输入密码");
            return;
        } else if (pwd.length() < 6 || pwd.length() > 20) {
            LogAndToastUtil.toast(this, "请输入6-20位密码");
            return;
        }

        if (null == mapFindPwd) mapFindPwd = new HashMap<>();
        mapFindPwd.put("mobile", account);
        mapFindPwd.put("code", smes);
        mapFindPwd.put("password", pwd);
        presenter.resetPwd(mapFindPwd);
    }

    @Override
    public void showResultResetPwd(MdlBaseHttpResp<MdlUser> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK) {
            LogAndToastUtil.toast(this, "修改成功");
            finish();
        }
    }
}

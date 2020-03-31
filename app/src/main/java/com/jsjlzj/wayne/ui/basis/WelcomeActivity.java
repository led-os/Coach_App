package com.jsjlzj.wayne.ui.basis;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.ui.MainActivity;
import com.jsjlzj.wayne.ui.publicac.mine.PrivateAgreementFragment;
import com.jsjlzj.wayne.utils.SPUtil;

/**
 * @description 欢迎页面
 * @date: modify 2019/12/23
 * @author: 曾海强
 */
public class WelcomeActivity extends AppCompatActivity implements PrivateAgreementFragment.OnPrivateListener {

    private PrivateAgreementFragment fragment;
    private int type;

    public static void go2this(Context context, String... urls) {
        Intent intent = new Intent(context, WelcomeActivity.class);
        intent.putExtra("urls", urls);
        context.startActivity(intent);
    }

    public static void go2this(Context context, int sort, String... urls) {
        Intent intent = new Intent(context, WelcomeActivity.class);
        intent.putExtra("urls", urls);
        intent.putExtra("sort", sort);
        context.startActivity(intent);
    }


    private ImageView welcomeImageView;

    protected Handler mainHandler = new Handler();


    @Override
    protected void onResume() {
        super.onResume();
        boolean isAgree = SPUtil.getAgree();
        if (!isAgree) {
            fragment = PrivateAgreementFragment.showDialog(getSupportFragmentManager(), getResources().getString(R.string.user_argument), "不同意", "同意", 0, this);
        } else {
            SPUtil.saveAgree();
            doTab();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome_main);
        // 避免第一次安装后每次都打开启动页
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
        welcomeImageView = findViewById(R.id.welcomeImageView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainHandler.removeCallbacksAndMessages(null);
    }


    @Override
    public void onCancleClick() {
        if (type == 0) {
            type = 1;
            fragment.setData(getResources().getString(R.string.hint), "离开", "同意条款", 1);
        } else {
            finish();
        }
    }

    @Override
    public void onConfirmClick() {
        PrivateAgreementFragment.hideDialog(getSupportFragmentManager());
        doTab();
    }

    private void doTab() {
        SPUtil.saveAgree();
        mainHandler.postDelayed(() -> {
            if (!TextUtils.isEmpty(SPUtil.getTokenFromSP()) && null != SPUtil.getUserFromSP() && !TextUtils.isEmpty(SPUtil.getYXAccountSP())
                    && !TextUtils.isEmpty(SPUtil.getYXTokenSP())) {
//            presenter.selectStoreUserInfo(null);
                MdlUser.MdlUserBean bean = SPUtil.getUserFromSP();
                if (!TextUtils.isEmpty(bean.getAccountType())) {
                    switch (bean.getAccountType()) {
                        case "NONE":
                            LoginRoleSelectActivity.go2This(WelcomeActivity.this, true);
                            finish();
                            break;
                        case "STORE":
                            switch (bean.getStoreStatus()) {
                                case 3:
                                    MainActivity.go2this(WelcomeActivity.this, true);
                                    finish();
                                    break;
                                default:
                                    LoginByPhoneActivity.go2This(WelcomeActivity.this, "", "", "", "", "");
                                    finish();
                                    break;
                            }
                            break;
                        case "TRAINER":
                            MainActivity.go2this(WelcomeActivity.this, false);
                            finish();
                            break;
                        default:
                            LoginByPhoneActivity.go2This(WelcomeActivity.this, "", "", "", "", "");
                            finish();
                            break;
                    }
                } else {
                    LoginByPhoneActivity.go2This(WelcomeActivity.this, "", "", "", "", "");
                    finish();
                }
            } else {
                LoginByPhoneActivity.go2This(WelcomeActivity.this, "", "", "", "", "");
                finish();
            }
        }, 2000);
    }

    @Override
    public void onPrivateClick() {
        WebViewContainerActivity.go2this(this, getString(R.string.user_argument),
                HttpConstant.WEB_URL_PRIVATE_POLICY, WebViewContainerFragment.TYPE_PRIVATE_POLICY);
    }
}


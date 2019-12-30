package com.jsjlzj.wayne.ui.basis;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.ui.MainActivity;
import com.jsjlzj.wayne.utils.SPUtil;

/**
* @description 欢迎页面
* @date:   modify 2019/12/23
* @author: 曾海强
*/
public class WelcomeActivity extends AppCompatActivity {

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome_main);
        welcomeImageView = findViewById(R.id.welcomeImageView);
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
                                LoginByPhoneActivity.go2This(WelcomeActivity.this,"","","","","");
                                finish();
                                break;
                    }
                }else{
                    LoginByPhoneActivity.go2This(WelcomeActivity.this,"","","","","");
                    finish();
                }
            }else{
                LoginByPhoneActivity.go2This(WelcomeActivity.this,"","","","","");
                finish();
            }
        }, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainHandler.removeCallbacksAndMessages(null);
    }
}


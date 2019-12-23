package com.jsjlzj.wayne.ui.mvp.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jsjlzj.wayne.ui.AppManager;
import com.jsjlzj.wayne.ui.basis.LoginByPhoneActivity;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BasePresenter;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BaseView;
import com.jsjlzj.wayne.utils.SPUtil;
import com.jsjlzj.wayne.utils.StatusBarCompatUtil;
import com.jsjlzj.wayne.utils.permission.MyPermissionResultListener;
import com.jsjlzj.wayne.utils.permission.PermissionUtil;
import com.jsjlzj.wayne.widgets.dialog.OnLineDialog;
import com.netease.nim.uikit.R;
import com.netease.nim.uikit.api.model.session.SessionCustomization;
import com.netease.nim.uikit.business.preference.UserPreferences;
import com.netease.nim.uikit.business.session.audio.MessageAudioControl;
import com.netease.nim.uikit.business.session.constant.Extras;
import com.netease.nim.uikit.business.session.fragment.MessageFragment;
import com.netease.nim.uikit.common.CommonUtil;
import com.netease.nim.uikit.common.activity.UI;
import com.netease.nim.uikit.common.util.sys.ScreenUtil;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.AuthServiceObserver;
import com.netease.nimlib.sdk.auth.OnlineClient;

import java.util.List;

/**
 * Created by zhoujianghua on 2015/9/10.
 */
public abstract class MyBaseMessageActivity<MVP_V extends BaseView, MVP_P extends BasePresenter<MVP_V>> extends UI implements MyPermissionResultListener {

    protected MVP_P presenter;

    protected String sessionId;

    private SessionCustomization customization;

    protected MessageFragment messageFragment;

    private SensorManager sensorManager;

    private Sensor proximitySensor;

    protected abstract MessageFragment fragment();

    protected abstract int getContentViewId();

    protected abstract void initToolBar();
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //下面的调用父类的语句不能省略，否则，以这个Activity做为容器的fragment请求权限时，回调会有问题
        //问题描述：http://blog.csdn.net/liup1211/article/details/76507896
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        Permissions4M.onRequestPermissionsResult(this, requestCode, grantResults);
        PermissionUtil.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
    /**
     * 是否开启距离传感器
     */
    protected abstract boolean enableSensor();

    protected abstract MVP_P createPresenter();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        presenter.attachView((MVP_V) this);

        setContentView(getContentViewId());
        StatusBarCompatUtil.compat(this, Color.parseColor("#25252A"));
        initToolBar();
        parseIntent();
        AppManager.getAppManager().addActivity(this);


        if (enableSensor()) {
            initSensor();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(messageFragment==null)messageFragment = (MessageFragment) switchContent(fragment());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
        NIMClient.getService(AuthServiceObserver.class).observeOtherClients(clientsObserver, false);
        NIMClient.getService(AuthServiceObserver.class).observeOnlineStatus(statusCodeObserver, false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensorManager != null && proximitySensor != null) {
            sensorManager.registerListener(sensorEventListener, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        NIMClient.getService(AuthServiceObserver.class).observeOtherClients(clientsObserver, true);
        NIMClient.getService(AuthServiceObserver.class).observeOnlineStatus(statusCodeObserver, true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager != null && proximitySensor != null) {
            sensorManager.unregisterListener(sensorEventListener);
        }
    }

    @Override
    public void onBackPressed() {
        if (messageFragment != null && messageFragment.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (messageFragment != null) {
            messageFragment.onActivityResult(requestCode, resultCode, data);
        }

        if (customization != null) {
            customization.onActivityResult(this, requestCode, resultCode, data);
        }
    }

    private void parseIntent() {
        Intent intent = getIntent();
        sessionId = intent.getStringExtra(Extras.EXTRA_ACCOUNT);
        customization = (SessionCustomization) intent.getSerializableExtra(Extras.EXTRA_CUSTOMIZATION);

        if (customization != null) {
            addRightCustomViewOnActionBar(this, customization.buttons);
        }
    }

    // 添加action bar的右侧按钮及响应事件
    private void addRightCustomViewOnActionBar(UI activity, List<SessionCustomization.OptionsButton> buttons) {
        if (CommonUtil.isEmpty(buttons)) {
            return;
        }

        Toolbar toolbar = getToolBar();
        if (toolbar == null) {
            return;
        }

        LinearLayout buttonContainer = (LinearLayout) LayoutInflater.from(activity).inflate(R.layout.nim_action_bar_custom_view, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        for (final SessionCustomization.OptionsButton button : buttons) {
            ImageView imageView = new ImageView(activity);
            imageView.setImageResource(button.iconId);
            imageView.setBackgroundResource(R.drawable.nim_nim_action_bar_button_selector);
            imageView.setPadding(ScreenUtil.dip2px(10), 0, ScreenUtil.dip2px(10), 0);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    button.onClick(MyBaseMessageActivity.this, v, sessionId);
                }
            });
            buttonContainer.addView(imageView, params);
        }

        toolbar.addView(buttonContainer, new Toolbar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.RIGHT | Gravity.CENTER));
    }


    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] dis = event.values;
            if (0.0f == dis[0]) {
                //靠近，设置为听筒模式
                MessageAudioControl.getInstance(MyBaseMessageActivity.this).setEarPhoneModeEnable(true);
            } else {
                //离开，复原
                MessageAudioControl.getInstance(MyBaseMessageActivity.this).setEarPhoneModeEnable(UserPreferences.isEarPhoneModeEnable());
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private void initSensor() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        }
    }
    private OnLineDialog onLineDialog;
    Observer<List<OnlineClient>> clientsObserver = new Observer<List<OnlineClient>>() {
        @Override
        public void onEvent(List<OnlineClient> onlineClients) {
            if (onlineClients == null || onlineClients.size() == 0) {
                return;
            }

            onLineDialog=new OnLineDialog(AppManager.getAppManager().currentActivity(), "登录失败", "请您重新登录", new OnLineDialog.ClickListener() {
                @Override
                public void clickConfirm() {
                    onLineDialog.dismiss();

                    SPUtil.saveUser2SP(null);
                    SPUtil.saveToken2SP("");
                    SPUtil.saveYXAccountSP("");
                    SPUtil.saveYXTokenSP("");
//                    NIMClient.getService(AuthServiceObserver.class).observeOtherClients(clientsObserver, false);
                    LoginByPhoneActivity.go2This(AppManager.getAppManager().currentActivity(),"","","","","");
                }
            });
            onLineDialog.setCancelable(false);
            onLineDialog.setCanceledOnTouchOutside(false);
            onLineDialog.show();
        }
    };

    Observer<StatusCode> statusCodeObserver=  new Observer<StatusCode> () {
        public void onEvent(StatusCode status) {
            if (status.wontAutoLogin()) {
                // 被踢出、账号被禁用、密码错误等情况，自动登录失败，需要返回到登录界面进行重新登录操作
                onLineDialog=new OnLineDialog(AppManager.getAppManager().currentActivity(), "您被下线", "您的账号被踢出下线，请注意账号信息安全", new OnLineDialog.ClickListener() {
                    @Override
                    public void clickConfirm() {
                        onLineDialog.dismiss();

                        SPUtil.saveUser2SP(null);
                        SPUtil.saveToken2SP("");
                        SPUtil.saveYXAccountSP("");
                        SPUtil.saveYXTokenSP("");
//                        NIMClient.getService(AuthServiceObserver.class).observeOnlineStatus(statusCodeObserver, false);
                        LoginByPhoneActivity.go2This(AppManager.getAppManager().currentActivity(),"","","","","");
                    }
                });
                onLineDialog.setCancelable(false);
                onLineDialog.setCanceledOnTouchOutside(false);
                onLineDialog.show();
            }
        }
    };
}

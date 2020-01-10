package com.jsjlzj.wayne.ui.mvp.base;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.ColorRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.AppManager;
import com.jsjlzj.wayne.ui.basis.LoginByPhoneActivity;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BasePresenter;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BaseView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SPUtil;
import com.jsjlzj.wayne.utils.StatusBarCompatUtil;
import com.jsjlzj.wayne.utils.Utility;
import com.jsjlzj.wayne.utils.eventbus.EnumEventBus;
import com.jsjlzj.wayne.utils.eventbus.EventBusManager;
import com.jsjlzj.wayne.utils.eventbus.MdlEventBus;
import com.jsjlzj.wayne.utils.permission.MyPermissionResultListener;
import com.jsjlzj.wayne.utils.permission.PermissionUtil;
import com.jsjlzj.wayne.widgets.dialog.OnLineDialog;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.AuthServiceObserver;
import com.netease.nimlib.sdk.auth.OnlineClient;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;


public abstract class MVPBaseActivity<MVP_V extends BaseView, MVP_P extends BasePresenter<MVP_V>> extends AppCompatActivity implements MyPermissionResultListener {
    //组件状态
    public static final int STATE_ACTIVE = 1;//活跃
    public static final int STATE_ONPAUSE = 2;//暂停
    public static final int STATE_DESTORY = 3;//销毁
    public static final int STATE_INIT = 0;//初始
    private int state = STATE_INIT;

    protected MVP_P presenter;
    protected String TAG = getClass().getSimpleName() + "";
    protected SharedPreferences sp;
    protected int currentPage = 1;
    protected int totalPage;
    protected boolean isResumed;


    @LayoutRes
    protected abstract int getLayoutResId();

    protected abstract void initViewAndControl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        state = STATE_ACTIVE;
        setContentView(getLayoutResId());
        StatusBarCompatUtil.compat(this, Color.parseColor("#25252A"));

//        StatusBarCompatUtil.compat(this, ResourceUtil.getColor(getStatusColor()));
        presenter = createPresenter();
        presenter.attachView((MVP_V) this);
        initViewAndControl();

        EventBusManager.register(this);
        AppManager.getAppManager().addActivity(this);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        state = STATE_ACTIVE;

    }
    private OnLineDialog onLineDialog;

    @Override
    protected void onResume() {
        super.onResume();
        isResumed = true;
        NIMClient.getService(AuthServiceObserver.class).observeOtherClients(clientsObserver, true);
        NIMClient.getService(AuthServiceObserver.class).observeOnlineStatus(statusCodeObserver, true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        state = STATE_ONPAUSE;
        isResumed = false;
    }
//    public String  getTopActivity(Context context){
//        ActivityManager am = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
//        String activityName = am.getRunningTasks(1).get(0).topActivity.getClassName();
//        try {
//            Class class =  Class.forName(activityName);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//
//    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T findView(int viewId) {
        return (T) findViewById(viewId);
    }

    protected <T extends View> T findLayout(int viewId) {
        return (T) LayoutInflater.from(this).inflate(viewId, null);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusMessage(MdlEventBus event) {
        switch (event.eventType) {
            case EnumEventBus.LOGOUT_OK:
                finish();
                break;
        }
    }

    @ColorRes
    protected int getStatusColor() {
        return R.color.colorPrimary;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //下面的调用父类的语句不能省略，否则，以这个Activity做为容器的fragment请求权限时，回调会有问题
        //问题描述：http://blog.csdn.net/liup1211/article/details/76507896
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        Permissions4M.onRequestPermissionsResult(this, requestCode, grantResults);
        PermissionUtil.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        state = STATE_DESTORY;
        LogAndToastUtil.clearToast();
        LogAndToastUtil.cancelWait(this);
        presenter.detachView();
        EventBusManager.unregister(this);
        AppManager.getAppManager().finishActivity(this);
        NIMClient.getService(AuthServiceObserver.class).observeOtherClients(clientsObserver, false);
        NIMClient.getService(AuthServiceObserver.class).observeOnlineStatus(statusCodeObserver, false);
    }

    protected void handleBackKey() {
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            handleBackKey();
            return true;
        }
        return false;
    }

    public void clickLeft(View v) {
        handleBackKey();
    }

    protected abstract MVP_P createPresenter();


    @Override
    public void permissionSuccess(int permissionReqCode) {

    }

    @Override
    public void permissionFail(int permissionReqCode) {

    }

    protected void setImg(String path, ImageView imgView) {
        if (TextUtils.isEmpty(path)) {
            Glide.with(this)
                    .load(R.mipmap.ic_launcher)
//                    .placeholder(ShotUtil.getDefaultDrawable(this))
//                    .error(ShotUtil.getDefaultDrawable(this))
                    .into(imgView);
//            imgView.setImageDrawable(ShotUtil.getDefaultDrawable(this));
            return;
        }
        if (this != null)
            Glide.with(this)
                    .load(path)
//                    .placeholder(ShotUtil.getDefaultDrawable(this))
//                    .error(ShotUtil.getDefaultDrawable(this))
                    .into(imgView);
    }

    protected void getPicBitmap(String path, SimpleTarget<Bitmap> simpleTarget) {
        if (TextUtils.isEmpty(path)) return;
        Glide.with(this).asBitmap().load(Utility.getRemotePicUrlFromServer(path)).into(simpleTarget);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

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

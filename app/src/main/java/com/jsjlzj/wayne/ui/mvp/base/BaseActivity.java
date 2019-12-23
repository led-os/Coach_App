package com.jsjlzj.wayne.ui.mvp.base;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.PublicConstant;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BasePresenter;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BaseView;
import com.jsjlzj.wayne.ui.mvp.relizelogin.LoginActivityView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.ResourceUtil;
import com.jsjlzj.wayne.utils.StatusBarCompatUtil;
import com.jsjlzj.wayne.utils.permission.MyPermissionResultListener;
import com.jsjlzj.wayne.utils.permission.PermissionUtil;
import com.jsjlzj.wayne.utils.shot.ShotUtil;
import com.jsjlzj.wayne.utils.eventbus.EnumEventBus;
import com.jsjlzj.wayne.utils.eventbus.EventBusManager;
import com.jsjlzj.wayne.utils.eventbus.MdlEventBus;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public abstract class BaseActivity<L extends BaseView, L1 extends BasePresenter<LoginActivityView>> extends AppCompatActivity implements MyPermissionResultListener {
    //组件状态
    public static final int STATE_ACTIVE=1;//活跃
    public static final int STATE_ONPAUSE=2;//暂停
    public static final int STATE_DESTORY=3;//销毁
    public static final int STATE_INIT=0;//初始
    private int state =STATE_INIT;

    protected  String TAG=""+getClass().getSimpleName();
    protected TextView tvTitle;

    protected SharedPreferences sp;
    protected int currentPage = 1;
    protected int totalPage;
    protected boolean isResumed;

    @Override
    protected void onResume() {
        super.onResume();
        isResumed = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        state =STATE_ONPAUSE;
        isResumed = false;
    }


    protected void setTitleResId(@StringRes int resId){
        if(tvTitle != null) {
            tvTitle.setText(resId);
        }
    }
    protected void setTitleStr(String title){
        if(tvTitle != null) {
            tvTitle.setText(title);
        }
    }

    protected int getTitleResId(){
        return 0;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusMessage(MdlEventBus event){
        switch (event.eventType){
            case EnumEventBus.LOGOUT_OK:
                finish();
                break;
        }
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        state =STATE_ACTIVE;
        sp = getSharedPreferences(PublicConstant.APP_NAME, MODE_PRIVATE);

        StatusBarCompatUtil.compat(this, ResourceUtil.getColor(getStatusColor()));

        initToolbar();
        initViewAndControl();

        EventBusManager.register(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        state =STATE_ACTIVE;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @ColorRes
    protected int getStatusColor(){
        return R.color.colorPrimary;
    }


    protected void initToolbar(){
        tvTitle = findView(R.id.toolbarTitle);
        if (tvTitle != null && getTitleResId() != 0){
            tvTitle.setText(getTitleResId());
        }
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T findView(int viewId){

        return (T) findViewById(viewId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        state =STATE_DESTORY;
        LogAndToastUtil.clearToast();
        LogAndToastUtil.cancelWait(this);
        EventBusManager.unregister(this);
    }

    protected abstract int getLayoutResId();
    protected abstract void initViewAndControl();


    protected void handleBackKey(){
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

    public void clickLeft(View v){
        handleBackKey();
    }

    @Override
    public void permissionSuccess(int permissionReqCode) {

    }

    @Override
    public void permissionFail(int permissionReqCode) {

    }


    protected void setImg(String path, ImageView imgView) {
        if (TextUtils.isEmpty(path)){
            imgView.setImageDrawable(ShotUtil.getDefaultDrawable(this));
            return;
        }
        if (this != null)
            Glide.with(this)
                    .load(path)
//                    .placeholder(ShotUtil.getDefaultDrawable(this))
//                    .error(ShotUtil.getDefaultDrawable(this))
                    .into(imgView);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}

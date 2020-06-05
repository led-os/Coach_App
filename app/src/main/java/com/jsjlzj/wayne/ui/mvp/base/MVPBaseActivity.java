package com.jsjlzj.wayne.ui.mvp.base;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.AppManager;
import com.jsjlzj.wayne.ui.basis.LoginByPhoneActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BasePresenter;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BaseView;
import com.jsjlzj.wayne.ui.publicac.dialog.EmptyFragment;
import com.jsjlzj.wayne.ui.publicac.dialog.LoadingFragment;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SPUtil;
import com.jsjlzj.wayne.utils.StatusBarCompatUtil;
import com.jsjlzj.wayne.utils.Utility;
import com.jsjlzj.wayne.utils.eventbus.EnumEventBus;
import com.jsjlzj.wayne.utils.eventbus.EventBusManager;
import com.jsjlzj.wayne.utils.eventbus.MdlEventBus;
import com.jsjlzj.wayne.utils.permission.MyPermissionResultListener;
import com.jsjlzj.wayne.utils.permission.PermissionUtil;
import com.jsjlzj.wayne.widgets.SearchBarView;
import com.jsjlzj.wayne.widgets.dialog.OnLineDialog;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.AuthServiceObserver;
import com.netease.nimlib.sdk.auth.OnlineClient;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.ButterKnife;


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

    protected ImageView mBackBtn, mRightBtn;
    protected TextView mTitleTv, mRightTv;
    protected SearchBarView mSearchBar;
    protected RelativeLayout relTitleBar;

    @LayoutRes
    protected abstract int getLayoutResId();

    protected abstract MVP_P createPresenter();

    protected abstract void initViewAndControl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        state = STATE_ACTIVE;
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        StatusBarCompatUtil.compat(this, Color.parseColor("#f1404b"));
//        StatusBarCompatUtil.compat(this, ResourceUtil.getColor(getStatusColor()));
        presenter = createPresenter();
        if(presenter != null){
            presenter.attachView((MVP_V) this);
        }
        initViewAndControl();

        EventBusManager.register(this);

        AppManager.getAppManager().addActivity(this);
    }

    public MyViewClickListener clickListener = new MyViewClickListener();


    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            onMultiClick(view);
        }
    }

    protected void onMultiClick(View view){

    }



    /**
     * 初始化普通标题
     */
    protected void initTitle(String title) {
        initTitle(true, title);
    }

    /**
     * 初始化普通标题 是否显示左侧标题
     */
    protected void initTitle(boolean isShowLeft, String title) {
        initView();
        if (mSearchBar != null) {
            mSearchBar.setVisibility(View.GONE);
            if (isShowLeft) {
                mBackBtn.setVisibility(View.VISIBLE);
            } else {
                mBackBtn.setVisibility(View.GONE);
            }
        }
        mTitleTv.setText(title);
    }

    protected void initSearchTitle() {
        initView();
        mSearchBar.setVisibility(View.VISIBLE);
        mRightTv.setVisibility(View.VISIBLE);
        mBackBtn.setVisibility(View.GONE);
        mRightBtn.setVisibility(View.GONE);
        mTitleTv.setVisibility(View.GONE);
    }

    protected void initRightTitle(String title, String rightContent) {
        initView();
        if (mSearchBar != null) {
            mSearchBar.setVisibility(View.GONE);
        }
        mTitleTv.setText(title);
        mRightBtn.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(rightContent)) {
            mRightTv.setText(rightContent);
            mRightTv.setVisibility(View.VISIBLE);
        } else {
            mRightTv.setVisibility(View.GONE);
        }
    }

    protected void initRightTitle(String title, int rightContent) {
        initView();
        if (mSearchBar != null) {
            mSearchBar.setVisibility(View.GONE);
        }
        mTitleTv.setText(title);
        mTitleTv.setVisibility(View.VISIBLE);
        mRightBtn.setVisibility(View.VISIBLE);
        if (rightContent > 0) {
            mRightBtn.setImageDrawable(ContextCompat.getDrawable(this,rightContent));
            mRightBtn.setVisibility(View.VISIBLE);
        } else {
            mRightBtn.setVisibility(View.GONE);
        }
    }


    private void initView() {
        mBackBtn = findViewById(R.id.btn_back);
        mTitleTv = findViewById(R.id.tv_title);
        mRightBtn = findViewById(R.id.btn_title_right);
        mRightTv = findViewById(R.id.tv_right_btn);
        mSearchBar = findViewById(R.id.search_bar_title);
        relTitleBar = findViewById(R.id.rel_title_bar);
        if (mBackBtn != null) {
            mBackBtn.setOnClickListener(v -> onBackPressed());
        }
    }

    private EmptyFragment mEmptyFragment;
    private LoadingFragment loadingFragment;

    /**
     * 显示 loading
     */
    protected void showLoadingFragment() {
        if (loadingFragment != null) {
            loadingFragment.dismissAllowingStateLoss();
            loadingFragment = null;
        }
        loadingFragment = new LoadingFragment();
        getSupportFragmentManager().beginTransaction().add(loadingFragment, "loading_fragment").commitAllowingStateLoss();
    }

    /**
     * 隐藏loading
     */
    protected void cancelLoadingFragment() {
        if (loadingFragment != null && loadingFragment.isAdded()) {
            loadingFragment.dismissAllowingStateLoss();
            loadingFragment = null;
        }
    }

    /**
     * 显示空页面
     *
     * @param contentId 要依附在哪个的id上
     */
    protected void showEmpty(int contentId, int type, View.OnClickListener listener) {
        if (mEmptyFragment == null) {
            mEmptyFragment = new EmptyFragment();
            if (type == 0) {
                mEmptyFragment.showEmpty();
            } else {
                mEmptyFragment.showEmpty(type, listener);
            }
            getSupportFragmentManager().beginTransaction().add(contentId, mEmptyFragment).commitAllowingStateLoss();
        } else {
            getSupportFragmentManager().beginTransaction().show(mEmptyFragment).commitAllowingStateLoss();
        }
    }

    /**
     * 关闭空页面
     */
    protected void hideEmpty() {
        if (mEmptyFragment != null && mEmptyFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().hide(mEmptyFragment).commitAllowingStateLoss();
        }
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
        if(presenter != null){
            presenter.detachView();
        }
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

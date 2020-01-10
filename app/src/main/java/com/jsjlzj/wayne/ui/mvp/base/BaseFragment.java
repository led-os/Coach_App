package com.jsjlzj.wayne.ui.mvp.base;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.permission.MyPermissionResultListener;
import com.jsjlzj.wayne.utils.permission.PermissionUtil;
import com.jsjlzj.wayne.utils.shot.ShotUtil;
import com.jsjlzj.wayne.utils.eventbus.EventBusManager;
import com.jsjlzj.wayne.utils.eventbus.MdlEventBus;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by haozo on 2017/9/26.
 */

public abstract class BaseFragment extends Fragment implements MyPermissionResultListener {
    //组件状态
    public static final int STATE_ACTIVE=1;//活跃
    public static final int STATE_ONPAUSE=2;//暂停
    public static final int STATE_DESTORY=3;//销毁
    public static final int STATE_INIT=0;//初始
    private int state =STATE_INIT;

    protected  String TAG=""+getClass().getSimpleName();
    protected boolean isInit = false;
    protected View view = null;
    private LayoutInflater layoutInflater;

    protected int currentPage = 1;
    protected int totalPage;

    protected abstract int getLayoutId();

    protected abstract void initView(View view);

    protected abstract void fragment2Front();

    private TextView tvTitle;

    protected void setTitleResId(@StringRes int resId){
        if(tvTitle != null) {
            tvTitle.setText(resId);
        }
    }

    protected int getTitleResId(){
        return 0;
    }

    protected void initToolbar(){
        tvTitle = findView(R.id.toolbarTitle);
        if (tvTitle != null && getTitleResId() != 0){
            tvTitle.setText(getTitleResId());
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        state =STATE_ACTIVE;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //LogAndToastUtil.log("------%s-----onCreateView-----------",this.getClass().getSimpleName());
        this.layoutInflater = inflater;
        view = inflater.inflate(getLayoutId(), container, false);

        isInit = true;
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        state =STATE_ONPAUSE;
    }

    @Override
    public void onDestroyView() {
        //LogAndToastUtil.log("------%s-----onDestroyView-----------",this.getClass().getSimpleName());
        super.onDestroyView();
        isInit = false;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        state =STATE_DESTORY;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        //LogAndToastUtil.log("------%s-----onActivityCreated-----------",this.getClass().getSimpleName());
        super.onActivityCreated(savedInstanceState);

        initToolbar();
        initView(view);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        LogAndToastUtil.log("------%s-----setUserVisibleHint-----isVisibleToUser:%s------",this.getClass().getSimpleName(),isVisibleToUser);
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isInit) {
            fragment2Front();
        }
    }

    protected <T extends View> T findView(int viewId) {
        return (T) view.findViewById(viewId);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusMessage(MdlEventBus event) {

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //LogAndToastUtil.log("------%s-----onCreate-----------",this.getClass().getSimpleName());
        super.onCreate(savedInstanceState);


        EventBusManager.register(this);
    }

    @Override
    public void onDestroy() {
//        //LogAndToastUtil.log("------%s-----onDestroy-----------",this.getClass().getSimpleName());
        super.onDestroy();
        //LogAndToastUtil.clearToast();
        EventBusManager.unregister(this);
    }

    @Override
    public void permissionSuccess(int permissionReqCode) {

    }

    @Override
    public void permissionFail(int permissionReqCode) {

    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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
}

package com.jsjlzj.wayne.ui.mvp.base;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BasePresenter;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BaseView;
import com.jsjlzj.wayne.utils.permission.MyPermissionResultListener;
import com.jsjlzj.wayne.utils.permission.PermissionUtil;
import com.jsjlzj.wayne.utils.eventbus.EventBusManager;
import com.jsjlzj.wayne.utils.eventbus.MdlEventBus;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by Administrator on 2018/4/18.
 */

public abstract class MVPBaseFragment<MVP_V extends BaseView, MVP_P extends BasePresenter<MVP_V>> extends Fragment implements MyPermissionResultListener {
    //组件状态
    public static final int STATE_ACTIVE = 1;//活跃
    public static final int STATE_ONPAUSE = 2;//暂停
    public static final int STATE_DESTORY = 3;//销毁
    public static final int STATE_INIT = 0;//初始
    private int state = STATE_INIT;

    protected MVP_P presenter;
    protected String TAG = getClass().getSimpleName() + "";
    protected boolean isInit = false;
    protected boolean isFirstLoad = false;
    protected View view = null;
    protected LayoutInflater layoutInflater;

    protected int currentPage = 1;
    protected int totalPage;

    @LayoutRes
    protected abstract int getLayoutResId();

    protected abstract void initViewAndControl(View view);

    protected abstract void fragment2Front();

    protected abstract MVP_P createPresenter();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        state = STATE_ACTIVE;
    }

    @Override
    public void onStart() {
        super.onStart();
        state = STATE_ACTIVE;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.layoutInflater = inflater;
        view = inflater.inflate(getLayoutResId(), container, false);

        isInit = true;
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        state = STATE_ONPAUSE;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isInit = false;
        isFirstLoad = false;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        state = STATE_DESTORY;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.bind(this,view);
        initViewAndControl(view);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed()) {
            fragment2Front();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            fragment2Front();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusMessage(MdlEventBus event) {

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

    protected <T extends View> T findView(int viewId) {
        return (T) view.findViewById(viewId);
    }

    protected <T extends View> T findLayout(int viewId) {
        return (T) LayoutInflater.from(getContext()).inflate(viewId, null);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        presenter.attachView((MVP_V) this);

        EventBusManager.register(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtil.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBusManager.unregister(this);
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
                    .error(R.mipmap.ic_launcher)
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


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}

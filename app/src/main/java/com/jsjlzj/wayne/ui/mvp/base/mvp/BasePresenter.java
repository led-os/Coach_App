package com.jsjlzj.wayne.ui.mvp.base.mvp;


import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnLoadHttpDataListener;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.Utility;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<MVP_V extends BaseView> implements OnLoadHttpDataListener<MdlBaseHttpResp>{
    protected boolean havaShowLoading = true;

    protected WeakReference<MVP_V> mViewRef;

    protected abstract BaseModel getMode();

    protected BaseModel baseModel;

    protected MVP_V view;

    protected MVP_V getView() {
        return mViewRef.get();
    }

    public void attachView(MVP_V view) {
        mViewRef = new WeakReference<>(view);
    }

    protected abstract void responseSuccess(int code, MdlBaseHttpResp resp);

    public void setHavaShowLoading(boolean havaShowLoading) {
        this.havaShowLoading = havaShowLoading;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }

        if (baseModel == null) baseModel = getMode();
        if (baseModel != null) {
            baseModel.detachModel();
        }
    }

    @Override
    public void onSuccess(int code, MdlBaseHttpResp resp) {
        if (view instanceof MVPBaseFragment) {
            if(((MVPBaseFragment)view).getState()!=MVPBaseFragment.STATE_ACTIVE)return;
        }
        if (view instanceof MVPBaseActivity) {
            if(((MVPBaseActivity)view).getState()!=MVPBaseActivity.STATE_ACTIVE)return;
        }

        if (view != null) {
            view.hideLoading();
            if (havaShowLoading) view.hideLoading();
            if (resp.getStatus() == HttpConstant.R_HTTP_OK) {//成功
                responseSuccess(code, resp);
                LogAndToastUtil.log(view.getClass().getSimpleName() + "httpposts", resp + "");
            } else if (resp.getStatus() == HttpConstant.R_TOKEN_EXPIRE) {//token过期,需要重新登录
                if (view instanceof Fragment) {
                    Utility.needLogin(((Fragment) view).getActivity());
                } else if (view instanceof Activity) {
                    Utility.needLogin((Activity) view);
                }
                LogAndToastUtil.log(view.getClass().getSimpleName() + "httppostt", resp + "");
            } else {
                if (view instanceof Fragment) {
                    LogAndToastUtil.toast(((Fragment)view).getContext(),resp.getMsg());
                } else if (view instanceof Activity) {
                    LogAndToastUtil.toast((Activity)view,resp.getMsg());
                }
//                if(resp.getStatus() == HttpConstant.R_HTTP_ERROR_MSG){
//                    ToastHelper.showToast();
//                }
                responseSuccess(code, resp);
                LogAndToastUtil.log(view.getClass().getSimpleName() + "httppostf", resp + "");
            }
        }
    }

    @Override
    public void onFailure(int code, Throwable e) {
        if (view != null) {
            view.hideLoading();
            if (havaShowLoading) view.hideLoading();
            LogAndToastUtil.log(view.getClass().getSimpleName() + "httppostf", e.getMessage() + "");
        } else {
            LogAndToastUtil.log("httppostf", e + "");
        }
        try {
            throw new Exception(e);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}

package com.jsjlzj.wayne.ui.mvp.base.mvp;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.utils.DelayHandler;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.ResourceUtil;


public interface BaseView {
    default void showLoading() {
        if (this instanceof Fragment) {
            DelayHandler.getInstance().post(new Runnable() {
                @Override
                public void run() {
                    LogAndToastUtil.showWait(((Fragment) BaseView.this).getContext(), ResourceUtil.getString(R.string.loading));
                }
            });
        } else if (this instanceof Activity) {
            DelayHandler.getInstance().post(new Runnable() {
                @Override
                public void run() {
                    LogAndToastUtil.showWait((Activity) BaseView.this, ResourceUtil.getString(R.string.loading));
                }
            });
        }
    }


    default void hideLoading() {
        if (this instanceof Fragment) {
            DelayHandler.getInstance().post(new Runnable() {
                @Override
                public void run() {
                    LogAndToastUtil.cancelWait(((Fragment) BaseView.this).getContext());
                }
            });
        } else if (this instanceof Activity) {
            DelayHandler.getInstance().post(new Runnable() {
                @Override
                public void run() {
                    LogAndToastUtil.cancelWait((Activity) BaseView.this);
                }
            });
        }
    }
}

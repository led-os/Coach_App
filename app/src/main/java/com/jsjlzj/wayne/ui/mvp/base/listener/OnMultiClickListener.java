package com.jsjlzj.wayne.ui.mvp.base.listener;

import android.view.View;

import com.jsjlzj.wayne.utils.LogAndToastUtil;


/**
 * Created by Administrator on 2018/6/7.
 */

public abstract class OnMultiClickListener implements View.OnClickListener {
    private static final int CLICK_DELAY_TIME = 250;
    private static long lastClickTime = 0L;

    public abstract void OnMultiClick(View view);


    @Override
    public void onClick(View view) {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - lastClickTime) >= CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            OnMultiClick(view);
        } else {
            LogAndToastUtil.toast(view.getContext(), "点击过快，请稍后");
        }


    }


}

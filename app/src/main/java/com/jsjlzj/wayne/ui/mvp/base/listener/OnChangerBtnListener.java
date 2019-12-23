package com.jsjlzj.wayne.ui.mvp.base.listener;

import android.view.View;

import com.jsjlzj.wayne.utils.LogAndToastUtil;


/**
 * Created by Administrator on 2018/6/7.
 */

public abstract class OnChangerBtnListener implements View.OnClickListener {
    private static final int CLICK_DELAY_TIME = 100;
    private static long lastClickTime = 0L;
    private boolean isGreen, isStart;
    private int position;
    public OnChangerBtnListener(boolean isGreen, boolean isStart, int position) {
        this.isGreen = isGreen;
        this.isStart = isStart;
        this.position = position;
    }

    public OnChangerBtnListener(boolean isStart, int position) {
        this.isStart = isStart;
        this.position = position;
    }

    public abstract void OnMultiClick(View view, boolean isGreen, boolean isStart, int position);

    @Override
    public void onClick(View view) {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - lastClickTime) >= CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            OnMultiClick(view,isGreen,isStart,position);
        } else {
            LogAndToastUtil.toast(view.getContext(), "点击过快，请稍后");
        }
    }


}

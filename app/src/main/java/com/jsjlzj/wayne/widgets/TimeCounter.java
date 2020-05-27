package com.jsjlzj.wayne.widgets;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.jsjlzj.wayne.utils.DateUtil;

/**
 * Created by yangjingsong on 16/4/21.
 */
public class TimeCounter extends CountDownTimer {

    /**
     * 0 ： 模拟考试倒计时3s    1 ：考试时间倒计时02:00:00  2 : 支付订单倒计时  3.订单详情支付倒计时
     */
    private int showType;
    private TextView button;
    private int mStringId;
    private CountTimeListener listener;
    private long joinTime;

    public TimeCounter(long millisInFuture, long countDownInterval, TextView button, int strId,int showType,CountTimeListener listener) {
        super(millisInFuture, countDownInterval);
        this.button = button;
        this.button.setEnabled(false);
        mStringId = strId;
        joinTime = System.currentTimeMillis();
        this.showType = showType;
        this.listener = listener;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if(showType == 0){
            button.setText(millisUntilFinished / 1000 +"");
        }else if(showType == 1){
            button.setText(""+ DateUtil.getDownTimer(millisUntilFinished));
        }else if(showType == 2){
            button.setText("付款 "+DateUtil.getPayDownTimer(millisUntilFinished));
        }else {
            button.setText("等待买家付款 剩"+DateUtil.getPayDownTimerFromDetail(millisUntilFinished)+"自动关闭订单");
        }
    }
    public void setJoinTime(long joinTime,int type) {
        this.joinTime = joinTime;
        this.showType = type;
    }

    @Override
    public void onFinish() {

        if(showType == 0 || showType == 1){
            listener.onCountTimeFinish();
            button.setText(mStringId);
            button.setEnabled(true);
        }else {
            if(listener != null){
                listener.onCountTimeFinish();
            }
        }


    }


    public interface CountTimeListener {
        void onCountTimeFinish();
    }
}

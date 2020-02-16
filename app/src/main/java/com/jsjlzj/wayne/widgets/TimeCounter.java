package com.jsjlzj.wayne.widgets;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.jsjlzj.wayne.utils.DateUtil;

/**
 * Created by yangjingsong on 16/4/21.
 */
public class TimeCounter extends CountDownTimer {

    /**
     * 0 ： 模拟考试倒计时3s    1 ：考试时间倒计时02:00:00
     */
    private final int showType;
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
        }else {
            button.setText(""+ DateUtil.getDownTimer(millisUntilFinished));
        }
    }

    @Override
    public void onFinish() {
        listener.onCountTimeFinish();
        button.setText(mStringId);
        button.setEnabled(true);
    }


    public interface CountTimeListener {
        void onCountTimeFinish();
    }
}

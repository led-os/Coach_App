package com.jsjlzj.wayne.widgets.dialog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.widgets.wheel.ArrayWheelAdapter;
import com.jsjlzj.wayne.widgets.wheel.WheelView;
import com.jsjlzj.wayne.widgets.wheel.listens.OnWheelChangedListener;
import com.jsjlzj.wayne.widgets.wheel.listens.OnWheelScrollListener;


public class WorkTimeDialog extends AlertDialog implements View.OnClickListener {
    private Context context;
    private ClickListener listener;
    private WheelView wheelView1, wheelView2;
    private ArrayWheelAdapter<String> adapter1, adapter2;
    private String[] datas=new String[]{
            "00:00","00:30","01:00","01:30","02:00","02:30","03:00","03:30",
            "04:00","04:30","05:00","05:30","06:00","06:30","07:00","07:30",
            "08:00","08:30","09:00","09:30","10:00","10:30","11:00","11:30",
            "12:00","12:30","13:00","13:30","14:00","14:30","15:00","15:30",
            "16:00","16:30","17:00","17:30","18:00","18:30","19:00","19:30",
            "20:00","20:30","21:00","21:30","22:00","22:30","23:00","23:30"
    };

    public WorkTimeDialog(@NonNull Activity context, ClickListener listener) {
        super(context, R.style.dialog);
        this.context = context;
        this.listener = listener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        setContentView(R.layout.dialog_salary);
        initViewAndControl();
    }


    private void initViewAndControl() {
        findViewById(R.id.tvComplete).setOnClickListener(this);
        findViewById(R.id.tvCancel).setOnClickListener(this);

        wheelView1 = findViewById(R.id.wheelView1);
        wheelView2 = findViewById(R.id.wheelView2);
        LinearLayout linearLayout =findViewById(R.id.wheelViewLayout);
        DisplayMetrics dm = MyApp.ME.dm;

        if (dm != null) {
            ViewGroup.LayoutParams layoutParams =linearLayout.getLayoutParams();
            layoutParams.height=dm.heightPixels / 3 +10;
            linearLayout.setLayoutParams(layoutParams);

            wheelView1.setHeight(dm.heightPixels / 3);
            wheelView2.setHeight(dm.heightPixels / 3);
        }
        initWheel();
    }


    private void initWheel() {
        adapter1 = new ArrayWheelAdapter<>(context, datas);
        adapter1.setItemResource(R.layout.wheel_text_item);//设置圈里面View的视图
        wheelView1.setViewAdapter(adapter1);
        wheelView1.addChangingListener(changedListener);
        wheelView1.addScrollingListener(scrollListener);


        adapter2 = new ArrayWheelAdapter<>(context, datas);
        adapter2.setItemResource(R.layout.wheel_text_item);//设置圈里面View的视图
        wheelView2.setViewAdapter(adapter2);
        wheelView2.addChangingListener(changedListener);
        wheelView2.addScrollingListener(scrollListener);

        wheelView1.setCyclic(false);
        wheelView1.setCurrentItem(4);

        wheelView2.setCyclic(false);
        wheelView2.setCurrentItem(14);
    }

    private OnWheelScrollListener scrollListener = new OnWheelScrollListener() {
        @Override
        public void onScrollingStarted(WheelView wheel) {

        }

        @Override
        public void onScrollingFinished(WheelView wheel) {
            switch (wheel.getId()) {
                case R.id.wheelViewEducation:
                    break;
            }
        }
    };

    private OnWheelChangedListener changedListener = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            wheel.invalidateWheel(true);
        }
    };

    @Override
    public void onClick(View v) {
        if (listener == null) {
            return;
        }
        switch (v.getId()) {
            case R.id.tvCancel:
                dismiss();
                break;
            case R.id.tvComplete:
                listener.clickConfirm(datas[wheelView1.getCurrentItem()],datas[wheelView2.getCurrentItem()]);
                dismiss();
                break;
        }
    }

    public interface ClickListener {
        void clickConfirm(String startTime,String endTime);
    }
}

package com.jsjlzj.wayne.widgets.dialog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.widgets.wheel.ArrayWheelAdapter;
import com.jsjlzj.wayne.widgets.wheel.WheelView;
import com.jsjlzj.wayne.widgets.wheel.listens.OnWheelChangedListener;
import com.jsjlzj.wayne.widgets.wheel.listens.OnWheelScrollListener;


public class ExperienceDialog extends AlertDialog implements View.OnClickListener {
    private Context context;
    private ClickListener listener;
    private WheelView wheelView;
    private ArrayWheelAdapter<String> adapter;
    private String[] datas;

    public ExperienceDialog(@NonNull Activity context, ClickListener listener,String[] datas) {
        super(context, R.style.dialog);
        this.context = context;
        this.datas=datas;
        this.listener = listener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);


        setContentView(R.layout.dialog_education);

        initViewAndControl();
    }


    private void initViewAndControl() {
        findViewById(R.id.tvComplete).setOnClickListener(this);
        findViewById(R.id.tvCancel).setOnClickListener(this);

        wheelView = findViewById(R.id.wheelViewEducation);

        DisplayMetrics dm = MyApp.ME.dm;
        if (dm != null) {
            wheelView.setHeight(dm.heightPixels / 3);
        }
        initWheel();
    }


    private void initWheel() {

        adapter = new ArrayWheelAdapter<>(context, datas);
        adapter.setItemResource(R.layout.wheel_text_item);//设置圈里面View的视图
        wheelView.setViewAdapter(adapter);
        wheelView.addChangingListener(changedListener);
        wheelView.addScrollingListener(scrollListener);
        wheelView.setCyclic(false);
        wheelView.setCurrentItem(0);
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
                listener.clickConfirm(datas[wheelView.getCurrentItem()],wheelView.getCurrentItem());
                dismiss();
                break;
        }
    }

    public interface ClickListener {
        void clickConfirm(String data,int position);
    }
}

package com.jsjlzj.wayne.widgets.dialog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
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

import java.util.Calendar;


public class DateYMDialog extends AlertDialog implements View.OnClickListener {
    private Context context;
    private ClickListener listener;
    private WheelView viewYear;
    private WheelView viewMonth;
    private boolean haveTody;

    public DateYMDialog(@NonNull Activity context, boolean haveTody, ClickListener listener) {
        super(context, R.style.dialog);
        this.context = context;
        this.listener = listener;
        this.haveTody = haveTody;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        window.getDecorView().setPadding(20, 0, 20, 0);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        setContentView(R.layout.dialog_date_ym);
        initViewAndControl();
    }


    private void initViewAndControl() {
        findViewById(R.id.tvCancel).setOnClickListener(this);
        findViewById(R.id.tvComplete).setOnClickListener(this);

        viewYear = findViewById(R.id.wheelViewYear);
        viewMonth = findViewById(R.id.wheelViewMonth);

        DisplayMetrics dm = MyApp.ME.dm;
        if (dm != null) {
            viewYear.setHeight(dm.heightPixels / 3);
            viewMonth.setHeight(dm.heightPixels / 3);
        }


        initWheel();
    }

    String[] yearData;

    private void initWheel() {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR)+1;
        int len = currentYear - 1950;
        yearData = new String[len];

        for (int i = 0; i < len; i++) {
//            if (i == len - 1) yearData[i] = "至今";
//            else
                yearData[i] = String.valueOf(i + 1950);
        }

        ArrayWheelAdapter<String> yearAdapter = new ArrayWheelAdapter<>(context, yearData);
        yearAdapter.setItemResource(R.layout.wheel_text_item);//设置圈里面View的视图
        viewYear.setViewAdapter(yearAdapter);
        viewYear.setCyclic(false);
        viewYear.setCurrentItem(yearData.length-1);
        viewYear.addChangingListener(changedListener);


        len = 12;
        final String[] monthData = new String[len];
        for (int i = 0; i < len; i++) {
            monthData[i] = String.valueOf(i + 1);
        }


        ArrayWheelAdapter<String> monthAdapter = new ArrayWheelAdapter<>(context, monthData);
        monthAdapter.setItemResource(R.layout.wheel_text_item);//设置圈里面View的视图
        viewMonth.setViewAdapter(monthAdapter);

//        len = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//        final String[] dayData = new String[len];
//        for (int i = 0; i < len; i++) {
//            dayData[i] = String.valueOf(i + 1);
//        }


        viewMonth.addChangingListener(changedListener);



        viewMonth.setCyclic(false);
        viewMonth.setCurrentItem(calendar.get(Calendar.MONTH)+1);

    }


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
            case R.id.tvComplete:
                int currentItem = viewYear.getCurrentItem();
                if(!haveTody){
                    listener.clickConfirm((currentItem + 1950)+"", (viewMonth.getCurrentItem() + 1) + "");
                }else {
                    listener.clickConfirm(yearData[currentItem], currentItem >= yearData.length ? "" : (viewMonth.getCurrentItem() + 1) + "");
                }
                break;
        }
        dismiss();
    }

    public interface ClickListener {
        void clickConfirm(String year, String month);
    }
}

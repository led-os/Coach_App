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
import com.jsjlzj.wayne.widgets.dialog.dataItem.AddressItem;
import com.jsjlzj.wayne.widgets.wheel.ArrayWheelAdapter;
import com.jsjlzj.wayne.widgets.wheel.WheelView;
import com.jsjlzj.wayne.widgets.wheel.listens.OnWheelChangedListener;
import com.jsjlzj.wayne.widgets.wheel.listens.OnWheelScrollListener;

import java.util.List;


public class AddressDialog extends AlertDialog implements View.OnClickListener {
    private Context context;
    private ClickListener listener;
    private WheelView wheelView1;
    private WheelView wheelView2;
    private List<AddressItem> addressItems;
    private ArrayWheelAdapter<String> dayAdapter;

    public AddressDialog(@NonNull Activity context, List<AddressItem> addressItems,ClickListener listener) {
        super(context, R.style.dialog);
        this.context = context;
        this.listener = listener;
        this.addressItems=addressItems;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        window.getDecorView().setPadding(20, 0, 20, 0);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        setContentView(R.layout.dialog_address);
        initViewAndControl();
    }


    private void initViewAndControl() {
        findViewById(R.id.tvComplete).setOnClickListener(this);

        wheelView1 = findViewById(R.id.wheelView1);
        wheelView2 = findViewById(R.id.wheelView2);

        DisplayMetrics dm = MyApp.ME.dm;
        if(dm!=null){
            wheelView1.setHeight(dm.heightPixels / 5);
            wheelView2.setHeight(dm.heightPixels / 5);
        }
        initWheel();
    }


    private void initWheel() {
        if(addressItems==null ||addressItems.size()==0)return;
        ArrayWheelAdapter<AddressItem> yearAdapter = new ArrayWheelAdapter<>(context, (AddressItem[]) addressItems.toArray());
        yearAdapter.setItemResource(R.layout.wheel_text_item);//设置圈里面View的视图
        wheelView1.setViewAdapter(yearAdapter);


        dayAdapter = new ArrayWheelAdapter<String>( context,(String[]) addressItems.get(0).getCity().toArray());
        dayAdapter.setItemResource(R.layout.wheel_text_item);//设置圈里面View的视图
        wheelView2.setViewAdapter(dayAdapter);


        wheelView1.addChangingListener(changedListener);
        wheelView2.addChangingListener(changedListener);

        wheelView1.addScrollingListener(scrollListener);
        wheelView2.addScrollingListener(scrollListener);

        wheelView1.setCyclic(false);
        wheelView1.setCurrentItem(0);


        wheelView2.setCyclic(false);
        wheelView2.setCurrentItem(0);
    }

    private OnWheelScrollListener scrollListener = new OnWheelScrollListener() {
        @Override
        public void onScrollingStarted(WheelView wheel) {

        }

        @Override
        public void onScrollingFinished(WheelView wheel) {
            switch (wheel.getId()) {
                case R.id.wheelView1:
                    resetDay();
                    break;
            }
        }
    };

    private void resetDay() {
        int yearPosition = wheelView1.getCurrentItem();
        dayAdapter = new ArrayWheelAdapter<>(context, (String[]) addressItems.get(yearPosition).getCity().toArray());
        dayAdapter.setItemResource(R.layout.wheel_text_item);//设置圈里面View的视图
        wheelView2.setViewAdapter(dayAdapter);
        wheelView2.setCyclic(false);
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
                if(addressItems!=null)
                listener.clickConfirm(addressItems.get(wheelView1.getCurrentItem()).getProvince(),addressItems.get(wheelView1.getCurrentItem()).getCity().get(wheelView2.getCurrentItem()) );
                break;
        }
        dismiss();
    }

    public interface ClickListener {
        void clickConfirm(String province,String city);
    }
}

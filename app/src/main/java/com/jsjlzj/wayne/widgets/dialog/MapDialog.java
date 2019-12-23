package com.jsjlzj.wayne.widgets.dialog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.jsjlzj.wayne.R;


public class MapDialog extends AlertDialog implements View.OnClickListener {
    private Context context;
    private ClickListener listener;
    private TextView btn1,btn2;
    public MapDialog(@NonNull Activity context, ClickListener listener) {
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
        setContentView(R.layout.dialog_sex);
        initViewAndControl();
    }


    private void initViewAndControl() {
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        findViewById(R.id.btnCancel).setOnClickListener(this);
        btn1.setText("百度地图");
        btn2.setText("高德地图");
    }


    public void setVisible(){
        if(btn1!=null) {
            btn1.setVisibility(View.GONE);
        }
    }

    public void setVisibleBtn2(){
        if(btn2!=null) {
            btn2.setVisibility(View.GONE);
        }
    }


    @Override
    public void onClick(View v) {
        if (listener == null) {
            return;
        }
        switch (v.getId()) {
            case R.id.btn1:
                listener.clickConfirm(1);//百度
                dismiss();
                break;
            case R.id.btn2:
                listener.clickConfirm(2);//高德
                dismiss();
                break;
            case R.id.btnCancel:
                dismiss();
                break;
        }
    }

    public interface ClickListener {
        void clickConfirm(int isMan);
    }
}

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


public class PositinStateDialog extends AlertDialog implements View.OnClickListener {
    private Context context;
    private ClickListener listener;
    private TextView btn0,btn1,btn2,btn3,title;

    public PositinStateDialog(@NonNull Activity context, ClickListener listener) {
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


        setContentView(R.layout.dialog_position_state);

        initViewAndControl();
    }


    private void initViewAndControl() {
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        title = findViewById(R.id.title);
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        findViewById(R.id.btnCancel).setOnClickListener(this);
    }

    public void setTitle(String msg){
        if(title!=null) {
            title.setText(msg);
        }
    }

    @Override
    public void onClick(View v) {
        if (listener == null) {
            return;
        }
        switch (v.getId()) {
            case R.id.btnCancel:
                dismiss();
                break;
            case R.id.btn0:
                listener.clickConfirm(btn0.getText().toString(),"1");
                dismiss();
                break;
            case R.id.btn1:
                listener.clickConfirm(btn1.getText().toString(),"2");
                dismiss();
                break;
            case R.id.btn2:
                listener.clickConfirm(btn2.getText().toString(),"3");
                dismiss();
                break;
            case R.id.btn3:
                listener.clickConfirm(btn3.getText().toString(),"0");
                dismiss();
                break;
        }
    }

    public interface ClickListener {
        void clickConfirm(String data,String code);
    }
}

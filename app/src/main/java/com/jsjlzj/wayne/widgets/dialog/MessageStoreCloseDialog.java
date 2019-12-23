package com.jsjlzj.wayne.widgets.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.jsjlzj.wayne.R;

import java.util.HashMap;
import java.util.Map;

public class MessageStoreCloseDialog extends AlertDialog implements View.OnClickListener {
    private ClickListener listener;
    Map<Integer,Integer> indexs;

    public MessageStoreCloseDialog(@NonNull Context context, ClickListener listener) {
        super(context, R.style.dialog);
        this.listener = listener;
        this.indexs=new HashMap<>();
    }

    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_message_close_store);

        initViewAndControl();
    }

    private void initViewAndControl() {
        findViewById(R.id.btnCancel).setOnClickListener(this);
        findViewById(R.id.btnConfirm).setOnClickListener(this);
        findViewById(R.id.btn0).setOnClickListener(this);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);
        findViewById(R.id.btn10).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        v.setSelected(!v.isSelected());
        switch (v.getId()) {
            case R.id.btnConfirm:
                listener.clickConfirm(0);
                dismiss();
                return;
            case R.id.btnCancel:
                dismiss();
                return;
            case R.id.btn0:
                if(v.isSelected())indexs.put(0,0);
                else indexs.remove(0);
                break;
            case R.id.btn1:
                if(v.isSelected())indexs.put(1,1);
                else indexs.remove(1);
                break;
            case R.id.btn2:
                if(v.isSelected())indexs.put(2,2);
                else indexs.remove(2);
                break;
            case R.id.btn3:
                if(v.isSelected())indexs.put(3,3);
                else indexs.remove(3);
                break;
            case R.id.btn4:
                if(v.isSelected())indexs.put(4,4);
                else indexs.remove(4);
                break;
            case R.id.btn5:
                if(v.isSelected())indexs.put(5,5);
                else indexs.remove(5);
                break;
            case R.id.btn6:
                if(v.isSelected())indexs.put(6,6);
                else indexs.remove(6);
                break;
            case R.id.btn7:
                if(v.isSelected())indexs.put(7,7);
                else indexs.remove(7);
                break;
            case R.id.btn8:
                if(v.isSelected())indexs.put(8,8);
                else indexs.remove(8);
                break;
            case R.id.btn9:
                if(v.isSelected())indexs.put(9,9);
                else indexs.remove(9);
                break;
            case R.id.btn10:
                if(v.isSelected())indexs.put(10,10);
                else indexs.remove(10);
                break;
        }
    }

    public interface ClickListener {
        void clickConfirm(int index);
    }
}
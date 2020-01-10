package com.jsjlzj.wayne.widgets.dialog;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jsjlzj.wayne.R;

public class CommonDialog extends AlertDialog implements View.OnClickListener {
    private ClickListener listener;
    private String msg;

    public CommonDialog(@NonNull Context context, String msg, ClickListener listener) {
        super(context, R.style.dialog);
        this.msg = msg;
        this.listener = listener;
    }
    private TextView tv_cancel,tv_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_common);

        initViewAndControl();
    }

    private void initViewAndControl() {

        TextView tvMsg = findViewById(R.id.tvMsg);
        if (TextUtils.isEmpty(msg)) {

            tvMsg.setVisibility(View.GONE);
        } else {
            tvMsg.setVisibility(View.VISIBLE);
            tvMsg.setText(msg);
        }
        tv_cancel=findViewById(R.id.tv_cancel);
        tv_confirm=findViewById(R.id.tv_confirm);
        tv_cancel.setOnClickListener(this);
        tv_confirm.setOnClickListener(this);
    }

    public void setCancel(String msg){
        if(tv_cancel!=null){
            tv_cancel.setText(msg);
        }
    }
    public void setConfirm(String msg){
        if(tv_confirm!=null){
            tv_confirm.setText(msg);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                listener.clickCancel();
                break;
            case R.id.tv_confirm:
                listener.clickConfirm();
                break;
        }
        dismiss();
    }

    public interface ClickListener {
        void clickConfirm();

        void clickCancel();
    }
}
package com.jsjlzj.wayne.widgets.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jsjlzj.wayne.R;

public class OnLineDialog extends AlertDialog implements View.OnClickListener {
    private ClickListener listener;
    private String msg;
    private String title;

    public OnLineDialog(@NonNull Context context,String title, String msg, ClickListener listener) {
        super(context, R.style.dialog);
        this.msg = msg;
        this.title=title;
        this.listener = listener;
    }
    private TextView tvTitle,confirm,tvMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.online_dialog);

        initViewAndControl();
    }

    private void initViewAndControl() {

        tvTitle=findViewById(R.id.tvTitle);
        confirm=findViewById(R.id.confirm);
        tvMsg=findViewById(R.id.tvMsg);
        confirm.setOnClickListener(this);
        tvTitle.setText(title);
        tvMsg.setText(msg);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm:
                listener.clickConfirm();
                break;
        }
        dismiss();
    }

    public interface ClickListener {
        void clickConfirm();

    }
}
package com.jsjlzj.wayne.widgets.dialog;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.jsjlzj.wayne.R;

public class MessageCloseDialog extends AlertDialog implements View.OnClickListener {
    private ClickListener listener;
    private String str;

    public MessageCloseDialog(@NonNull Context context, String str, ClickListener listener) {
        super(context, R.style.dialog);
        this.listener = listener;
        this.str = str;
    }

    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_message_close);

        initViewAndControl();
    }

    private void initViewAndControl() {
        tvContent = findViewById(R.id.tvContent);
        tvContent.setText(str);
        findViewById(R.id.tv_cancel).setOnClickListener(this);
        findViewById(R.id.tv_confirm).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                break;
            case R.id.tv_confirm:
                listener.clickConfirm();
                break;
        }
        dismiss();
    }

    public interface ClickListener {
        void clickConfirm();
    }
}
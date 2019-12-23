package com.jsjlzj.wayne.widgets.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.jsjlzj.wayne.R;

public class CommonPhoneDialog extends AlertDialog implements View.OnClickListener {
    private ClickListener listener;

    public CommonPhoneDialog(@NonNull Context context, ClickListener listener) {
        super(context, R.style.dialog);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_common_phone);
        initViewAndControl();
    }

    private void initViewAndControl() {
        findViewById(R.id.tv_cancel).setOnClickListener(this);
        findViewById(R.id.tv_confirm).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                break;
            case R.id.tv_confirm:
                if(listener!=null)listener.clickConfirm();
                break;
        }
        dismiss();
    }

    public interface ClickListener {
        void clickConfirm();
    }
}
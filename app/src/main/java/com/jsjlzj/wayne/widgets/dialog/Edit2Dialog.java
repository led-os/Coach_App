package com.jsjlzj.wayne.widgets.dialog;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.utils.Utility;

public class Edit2Dialog extends AlertDialog implements View.OnClickListener {
    private ClickListener listener;
    private EditText editText;

    public Edit2Dialog(@NonNull Context context, ClickListener listener) {
        super(context, R.style.dialog);
        this.listener = listener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_edit2);
        initViewAndControl();
    }

    private void initViewAndControl() {
        findViewById(R.id.tv_cancel).setOnClickListener(this);
        findViewById(R.id.tv_confirm).setOnClickListener(this);
        editText = findViewById(R.id.tvMsg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                listener.clickCancel();
                break;
            case R.id.tv_confirm:
                listener.clickConfirm(Utility.getEditTextStr(editText));
                break;
        }
        dismiss();
    }

    public interface ClickListener {
        void clickConfirm(String s);

        void clickCancel();
    }
}
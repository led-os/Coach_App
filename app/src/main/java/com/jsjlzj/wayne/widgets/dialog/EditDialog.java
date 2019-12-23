package com.jsjlzj.wayne.widgets.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.utils.Utility;

public class EditDialog extends AlertDialog implements View.OnClickListener {
    private ClickListener listener;
    private String msg;
    private EditText editText;

    public EditDialog(@NonNull Context context, String msg, ClickListener listener) {
        super(context, R.style.dialog);
        this.msg = msg;
        this.listener = listener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_edit);
        initViewAndControl();
    }

    private void initViewAndControl() {

        findViewById(R.id.tv_cancel).setOnClickListener(this);
        findViewById(R.id.tv_confirm).setOnClickListener(this);
        editText = findViewById(R.id.tvMsg);
        if (!TextUtils.isEmpty(msg)) {
            editText.setHint(msg);
        } else {
            editText.setHint("输入内容");
        }
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        //弹出对话框后直接弹出键盘
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
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
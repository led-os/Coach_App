package com.jsjlzj.wayne.widgets.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jsjlzj.wayne.R;

public class CommonWechatDialog extends AlertDialog implements View.OnClickListener {
    private ClickListener listener;

    public CommonWechatDialog(@NonNull Context context,ClickListener listener) {
        super(context, R.style.dialog);
        this.listener = listener;
    }
    private TextView tv_confirm;
    private EditText editDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_common_wechat);

        initViewAndControl();
    }

    private void initViewAndControl() {
        editDialog=findViewById(R.id.editDialog);
        tv_confirm=findViewById(R.id.tv_confirm);
        tv_confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm:
                String editTextStr = editDialog.getText().toString();
                if(!TextUtils.isEmpty(editTextStr))listener.clickConfirm(editTextStr);
                break;
        }
        dismiss();
    }

    public interface ClickListener {
        void clickConfirm(String wechat);
    }
}
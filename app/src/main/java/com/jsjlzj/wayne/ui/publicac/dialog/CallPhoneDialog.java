package com.jsjlzj.wayne.ui.publicac.dialog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.widgets.dialog.SexDialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

/**
 * @ClassName: CallPhoneDialog
 * @Description: 拨打电话dialog
 * @Author: 曾海强
 * @CreateDate: 2020/6/30 9:43
 */
public class CallPhoneDialog extends AlertDialog implements View.OnClickListener {
    private Context context;
    private ClickListener listener;
    private String str1 = "0571 8601 1889";


    public CallPhoneDialog(@NonNull Activity context, ClickListener listener) {
        super(context, R.style.dialog);
        this.context = context;
        this.listener = listener;
    }

    public CallPhoneDialog(@NonNull Activity context,String tv1, ClickListener listener) {
        super(context, R.style.dialog);
        this.context = context;
        this.str1 = tv1;
        this.listener = listener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        setContentView(R.layout.layout_call_phone);
        initViewAndControl();
    }


    private void initViewAndControl() {
        ((TextView)findViewById(R.id.btn1)).setText(str1);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btnCancel).setOnClickListener(this);
    }





    @Override
    public void onClick(View v) {
        if (listener == null) {
            return;
        }
        switch (v.getId()) {
            case R.id.btn1:
                listener.clickConfirm(1);
                dismiss();
                break;
            case R.id.btnCancel:
                dismiss();
                break;
            default:break;
        }
    }

    public interface ClickListener {
        void clickConfirm(int isMan);
    }
}

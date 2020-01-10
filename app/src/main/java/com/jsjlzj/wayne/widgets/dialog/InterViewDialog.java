package com.jsjlzj.wayne.widgets.dialog;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.jsjlzj.wayne.R;

public class InterViewDialog extends AlertDialog implements View.OnClickListener {
    private ClickListener listener;

    public InterViewDialog(@NonNull Context context,ClickListener listener) {
        super(context, R.style.dialog);
        this.listener = listener;
    }
    private TextView cancel,confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interview_dialog);

        initViewAndControl();
    }

    private void initViewAndControl() {

        cancel=findViewById(R.id.cancel);
        confirm=findViewById(R.id.confirm);
        confirm.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm:
                listener.clickConfirm();
                break;
            case R.id.cancel:
                dismiss();
                break;
        }
        dismiss();
    }

    public interface ClickListener {
        void clickConfirm();

    }
}
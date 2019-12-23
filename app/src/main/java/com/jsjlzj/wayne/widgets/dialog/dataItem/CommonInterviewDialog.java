package com.jsjlzj.wayne.widgets.dialog.dataItem;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jsjlzj.wayne.R;

public class CommonInterviewDialog extends AlertDialog implements View.OnClickListener {
    private ClickListener listener;
    private ImageView image1,image2;
    private TextView tvContent;
    private String title,url1,url2;

    public CommonInterviewDialog(@NonNull Context context,String url1,String url2,String title, ClickListener listener) {
        super(context, R.style.dialog);
        this.listener = listener;
        this.url1=url1;
        this.url2=url2;
        this.title=title;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_common_interview);
        initViewAndControl();
    }

    private void initViewAndControl() {
        image1=findViewById(R.id.image1);
        image2=findViewById(R.id.image2);
        tvContent=findViewById(R.id.tvContent);
        tvContent.setText(title+"俱乐部\n向你发出面试邀请");
        Glide.with(getContext()).load(url1).into(image1);
        Glide.with(getContext()).load(url2).into(image2);
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
package com.jsjlzj.wayne.widgets.dialog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.BaseAdapterHelper;
import com.jsjlzj.wayne.adapter.recycler.MyRecyclerAdapter;
import com.jsjlzj.wayne.entity.store.ItemsBean;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.widgets.MyListView;
import com.jsjlzj.wayne.widgets.MyRecyclerView;

import java.util.List;


public class ReportDialog extends AlertDialog implements View.OnClickListener {
    private Context context;
    private ClickListener listener;
    private List<ItemsBean> list;

    public ReportDialog(@NonNull Activity context, ClickListener listener,List<ItemsBean> list) {
        super(context, R.style.dialog);
        this.context = context;
        this.listener = listener;
        this.list=list;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        setContentView(R.layout.dialog_reportsex);
        initViewAndControl();
    }

    private MyRecyclerView lv;
    private MyRecyclerAdapter<ItemsBean> adapter;
    private void initViewAndControl() {
        lv=findViewById(R.id.report_lv);
        findViewById(R.id.btnCancel).setOnClickListener(this);
        lv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));

        adapter=new MyRecyclerAdapter<ItemsBean>(context,R.layout.item_address) {
            @Override
            public int getItemCount() {
                return list==null?0:list.size();
            }

            @Override
            public void onUpdate(BaseAdapterHelper helper, ItemsBean item, int position) {
                helper.setText(R.id.tvPositionName,item.getName());
                helper.setOnClickListener(new OnMultiClickListener() {
                    @Override
                    public void OnMultiClick(View view) {
                        listener.clickConfirm(item.getCode());
                        dismiss();
                    }
                });
            }
        };
        adapter.setData(list);
        lv.setAdapter(adapter);
    }





    @Override
    public void onClick(View v) {
        if (listener == null) {
            return;
        }
        switch (v.getId()) {
            case R.id.btnCancel:
                dismiss();
                break;
        }
    }

    public interface ClickListener {
        void clickConfirm(String code);
    }
}

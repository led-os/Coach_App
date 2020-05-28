package com.jsjlzj.wayne.ui.store.home.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.mine.SettledInAdapter;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: FactorySettledInActivity
 * @Description: 厂家入驻
 * @Author: 曾海强
 * @CreateDate:
 */
public class FactorySettledInActivity extends MVPBaseActivity {

    @BindView(R.id.tv_to_settled)
    TextView tvToSettled;
    @BindView(R.id.rv_type)
    RecyclerView rvType;


    public static void go2this(Activity activity){
        activity.startActivity(new Intent(activity,FactorySettledInActivity.class));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_factory_settled_in;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViewAndControl() {
        initTitle("厂家入驻");
        rvType.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        rvType.setAdapter(new SettledInAdapter(this));
        tvToSettled.setOnClickListener(v -> {
            ApplySettledInActivity.go2this(this);
        });

    }



}

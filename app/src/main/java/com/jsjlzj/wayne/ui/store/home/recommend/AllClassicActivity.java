package com.jsjlzj.wayne.ui.store.home.recommend;


import android.app.Activity;
import android.content.Intent;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.home.HomeVideoAdapter;
import com.jsjlzj.wayne.entity.store.home.CategoryBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @ClassName: AllClassicActivity
 * @Description: 全部分类
 * @Author: 曾海强
 * @CreateDate:
 */
public class AllClassicActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView, HomeVideoAdapter.OnClassicItemListener {

    @BindView(R.id.rv_all_classic)
    RecyclerView rvAllClassic;

    private HomeVideoAdapter adapter;

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, AllClassicActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_all_classic;
    }


    @Override
    protected void initViewAndControl() {
        initTitle("全部分类");
       adapter = new HomeVideoAdapter(this,new ArrayList<>(),2);
       adapter.setListener(this);
       rvAllClassic.setLayoutManager(new GridLayoutManager(this,2));
       rvAllClassic.setAdapter(adapter);
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }


    @Override
    public void onItemClick(CategoryBean data) {
        ClassicDetailActivity.go2this(this,data.getName());
    }


}
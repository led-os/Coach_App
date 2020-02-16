package com.jsjlzj.wayne.ui.store.home.amoy;

import android.app.Activity;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.home.HotSchoolAdapter;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.ui.store.home.recommend.ClassicDetailActivity;

import java.util.ArrayList;

import butterknife.BindView;

public class HotSchoolActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView,HotSchoolAdapter.OnItemClickListener {

    @BindView(R.id.rv_hot_school)
    RecyclerView rvHotSchool;

    private HotSchoolAdapter adapter;

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, HotSchoolActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_hot_school;
    }


    @Override
    protected void initViewAndControl() {
        initTitle("热门学校");
        adapter = new HotSchoolAdapter(this,new ArrayList<>());
        adapter.setListener(this);
        rvHotSchool.setLayoutManager(new LinearLayoutManager(this));
        rvHotSchool.setAdapter(adapter);
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }


    @Override
    public void onItemClick(String string) {
        ClassicDetailActivity.go2this(this,string);
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
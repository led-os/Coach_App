package com.jsjlzj.wayne.ui.store.home.mine;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.mine.MinePrizeAdapter;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;

import java.util.ArrayList;

import butterknife.BindView;

/**
  *
  * @ClassName:      MinePrizeActivity
  * @Description:    我的奖品界面
  * @Author:         曾海强
  * @CreateDate:
  */
public class MinePrizeActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {


    @BindView(R.id.rv_data)
    RecyclerView rvData;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_mine_prize;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initTitle("积分明细");
        rvData.setLayoutManager(new LinearLayoutManager(this));
        MinePrizeAdapter adapter = new MinePrizeAdapter(this,new ArrayList<>());
        rvData.setAdapter(adapter);
    }


}

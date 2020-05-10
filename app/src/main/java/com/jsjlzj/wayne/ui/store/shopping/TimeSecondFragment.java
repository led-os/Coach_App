package com.jsjlzj.wayne.ui.store.shopping;


import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.shopping.TimeSecondAdapter;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.widgets.CustomXRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @ClassName: TimeSecondFragment
 * @Description: 显示抢购列表
 * @Author: 曾海强
 * @CreateDate:
 */
public class TimeSecondFragment extends MVPBaseFragment<HomeView, HomePresenter> implements HomeView {


    @BindView(R.id.rv_time_second)
    CustomXRecyclerView rvTimeSecond;
    /**
     * 0 ： 限时抢购   1 ：即将开始
     */
    private int type;

    public TimeSecondFragment() {}

    public static TimeSecondFragment getInstance(int type) {
        TimeSecondFragment fragment = new TimeSecondFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_time_second;
    }

    @Override
    protected void initViewAndControl(View view) {
        type = getArguments().getInt("type", 0);
        rvTimeSecond.setLayoutManager(new LinearLayoutManager(getActivity()));
        TimeSecondAdapter adapter = new TimeSecondAdapter(getActivity(),type,new ArrayList<>());
        rvTimeSecond.setAdapter(adapter);
        loadData(true);
    }

    @Override
    protected void fragment2Front() {}

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }


    public void loadData(boolean isRefresh) {

    }

}

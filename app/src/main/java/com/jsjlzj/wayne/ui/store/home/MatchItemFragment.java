package com.jsjlzj.wayne.ui.store.home;


import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.home.MatchAdapter;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;

import java.util.ArrayList;

import butterknife.BindView;

 /**
  *  
  * @ClassName:      赛事列表
  * @Description:    java类作用描述
  * @Author:         曾海强
  * @CreateDate:      
  */
public class MatchItemFragment extends MVPBaseFragment<HomeView, HomePresenter> implements HomeView {


    @BindView(R.id.rv_authentication)
    RecyclerView rvAuthentication;

    private MatchAdapter matchAdapter;
    /**
     * 0 : 全体赛事   1： 进行中    2 ：已结束
     */
    private int type;


    public MatchItemFragment() {
    }

    public MatchItemFragment(int type) {
        this();
        this.type = type;
    }


    public static MatchItemFragment getInstance(int type) {
        MatchItemFragment fragment = new MatchItemFragment(type);
        return fragment;
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_authentication;
    }

    @Override
    protected void initViewAndControl(View view) {
        rvAuthentication.setHasFixedSize(true);
        rvAuthentication.setNestedScrollingEnabled(false);
        matchAdapter = new MatchAdapter(getActivity(),new ArrayList<>());
        rvAuthentication.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAuthentication.setAdapter(matchAdapter);
    }

    @Override
    protected void fragment2Front() {

    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }


}

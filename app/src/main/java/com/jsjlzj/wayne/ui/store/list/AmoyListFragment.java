package com.jsjlzj.wayne.ui.store.list;


import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.home.AuthenticationAdapter;
import com.jsjlzj.wayne.adapter.recycler.home.HomeLikeAdapter;
import com.jsjlzj.wayne.adapter.recycler.home.MatchAdapter;
import com.jsjlzj.wayne.adapter.recycler.home.ProductAdapter;
import com.jsjlzj.wayne.adapter.recycler.search.SearchAdapter;
import com.jsjlzj.wayne.adapter.recycler.search.SearchUserAdapter;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName: AmoyListFragment
 * @Description: 列表
 * @Author: 曾海强
 * @CreateDate:
 */
public class AmoyListFragment extends MVPBaseFragment<HomeView, HomePresenter> implements HomeView {


    @BindView(R.id.rv_amoy_list)
    RecyclerView rvAmoyList;
    @BindView(R.id.tv_hot)
    TextView tvHot;
    @BindView(R.id.tv_news)
    TextView tvNews;
    @BindView(R.id.ll_video)
    LinearLayout llVideo;

    private RecyclerView.Adapter adapter;

    /**
     * 0 全部   1 淘学  2 ： 赛事  3 ：视频   4 ：文章   5 ：产品  6 ：用户  7 ：动态
     */
    private int type;


    public AmoyListFragment() {
    }

    public AmoyListFragment(int type) {
        this.type = type;
    }

    public static AmoyListFragment getInstance(int type) {
        AmoyListFragment fragment = new AmoyListFragment(type);
        return fragment;
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_amoy_list;
    }

    @Override
    protected void initViewAndControl(View view) {
        switch (type) {
            case 0:
            case 1:
                llVideo.setVisibility(View.GONE);
                adapter = new AuthenticationAdapter(getActivity(), new ArrayList<>());
                rvAmoyList.setLayoutManager(new LinearLayoutManager(getActivity()));
                rvAmoyList.setAdapter(adapter);
                break;
            case 2:
                llVideo.setVisibility(View.GONE);
                adapter = new MatchAdapter(getActivity(), new ArrayList<>());
                rvAmoyList.setLayoutManager(new LinearLayoutManager(getActivity()));
                rvAmoyList.setAdapter(adapter);
                break;
            case 3:
                llVideo.setVisibility(View.VISIBLE);
                adapter = new HomeLikeAdapter(getActivity(), new ArrayList<>());
                rvAmoyList.setLayoutManager(new LinearLayoutManager(getActivity()));
                rvAmoyList.setAdapter(adapter);
                break;
            case 4:
                llVideo.setVisibility(View.VISIBLE);
                adapter = new HomeLikeAdapter(getActivity(), new ArrayList<>());
                ((HomeLikeAdapter) adapter).setShowTime(false);
                rvAmoyList.setLayoutManager(new LinearLayoutManager(getActivity()));
                rvAmoyList.setAdapter(adapter);
                break;
            case 5:
                llVideo.setVisibility(View.GONE);
                adapter = new ProductAdapter(getActivity(), new ArrayList<>());
                rvAmoyList.setLayoutManager(new LinearLayoutManager(getActivity()));
                rvAmoyList.setAdapter(adapter);
                break;
            case 6:
                llVideo.setVisibility(View.GONE);
                adapter = new SearchUserAdapter(getActivity(), new ArrayList<>());
                rvAmoyList.setLayoutManager(new LinearLayoutManager(getActivity()));
                rvAmoyList.setAdapter(adapter);
                break;
            case 7:
                llVideo.setVisibility(View.GONE);
                adapter = new SearchAdapter(getActivity(), new ArrayList<>());
                rvAmoyList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                rvAmoyList.setAdapter(adapter);
                break;
            default:
                break;
        }

    }

    @Override
    protected void fragment2Front() {

    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }


    @OnClick({R.id.tv_hot, R.id.tv_news})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_hot:
                tvHot.setTextColor(ContextCompat.getColor(getActivity(),R.color.color_4F9BFA));
                tvNews.setTextColor(ContextCompat.getColor(getActivity(),R.color.color_333333));
                break;
            case R.id.tv_news:
                tvHot.setTextColor(ContextCompat.getColor(getActivity(),R.color.color_333333));
                tvNews.setTextColor(ContextCompat.getColor(getActivity(),R.color.color_4F9BFA));
                break;
        }
    }
}

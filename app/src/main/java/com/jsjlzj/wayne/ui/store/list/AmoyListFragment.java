package com.jsjlzj.wayne.ui.store.list;


import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alibaba.fastjson.JSONArray;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.home.HomeLikeAdapter;
import com.jsjlzj.wayne.adapter.recycler.home.MatchAdapter;
import com.jsjlzj.wayne.adapter.recycler.home.ProductAdapter;
import com.jsjlzj.wayne.adapter.recycler.search.SeaerchTaoLearnAdapter;
import com.jsjlzj.wayne.adapter.recycler.search.SearchAdapter;
import com.jsjlzj.wayne.adapter.recycler.search.SearchUserAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.store.home.CategoryBean;
import com.jsjlzj.wayne.entity.store.home.VideoBean;
import com.jsjlzj.wayne.entity.store.search.ChannelListBean;
import com.jsjlzj.wayne.entity.store.search.TaoLearnListBean;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.ui.store.home.amoy.HotSchoolActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName: AmoyListFragment
 * @Description: 列表
 * @Author: 曾海强
 * @CreateDate:
 */
public class AmoyListFragment extends MVPBaseFragment<HomeView, HomePresenter> implements HomeView, SearchUserAdapter.OnSearchUserClickListener, ProductAdapter.OnItemClickListener, SeaerchTaoLearnAdapter.OnItemClickListener {


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
    private JSONArray array;


    public AmoyListFragment() {
    }

    public AmoyListFragment(int type, JSONArray array) {
        this.type = type;
        this.array = array;
    }

    public static AmoyListFragment getInstance(int type, JSONArray array) {
        AmoyListFragment fragment = new AmoyListFragment(type, array);
        return fragment;
    }


    public void setArray(JSONArray array) {
        this.array = array;
        if(array == null ){
            showEmpty(R.id.rel_empty,0,null);
            return;
        }
        hideEmpty();
        switch (type) {
            case 0:
//                List<TaoLearnListBean> taoLearnList = array.toJavaList(TaoLearnListBean.class);
//                ((SeaerchTaoLearnAdapter)adapter).setData(taoLearnList);
                break;
            case 1:
                List<TaoLearnListBean> taoLearnList = array.toJavaList(TaoLearnListBean.class);
                ((SeaerchTaoLearnAdapter) adapter).setData(taoLearnList);
                ((SeaerchTaoLearnAdapter) adapter).setListener(this);
                if(taoLearnList == null || taoLearnList.size() <= 0){
                    showEmpty(R.id.rel_empty,0,null);
                }
                break;
            case 2:
                List<CategoryBean> matchList = array.toJavaList(CategoryBean.class);
                ((MatchAdapter)adapter).setData(matchList);
                if(matchList == null || matchList.size() <= 0){
                    showEmpty(R.id.rel_empty,0,null);
                }
                break;
            case 4:
            case 3:
                List<VideoBean> videoList = array.toJavaList(VideoBean.class);
                ((HomeLikeAdapter)adapter).setData(videoList);
                if(videoList == null || videoList.size() <= 0){
                    showEmpty(R.id.rel_empty,0,null);
                }
                break;
            case 5:
                List<CategoryBean> productList = array.toJavaList(CategoryBean.class);
                ((ProductAdapter)adapter).setData(productList);
                if(productList == null || productList.size() <= 0){
                    showEmpty(R.id.rel_empty,0,null);
                }
                break;
            case 6:
                List<ChannelListBean> userList = array.toJavaList(ChannelListBean.class);
                ((SearchUserAdapter)adapter).setData(userList);
                if(userList == null || userList.size() <= 0){
                    showEmpty(R.id.rel_empty,0,null);
                }
                break;
            default:
                break;
        }
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
                adapter = new SeaerchTaoLearnAdapter(getActivity(),  new ArrayList<>());
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
                adapter = new HomeLikeAdapter(getActivity(), new ArrayList<>(),1);
                rvAmoyList.setLayoutManager(new LinearLayoutManager(getActivity()));
                rvAmoyList.setAdapter(adapter);
                break;
            case 4:
                llVideo.setVisibility(View.VISIBLE);
                adapter = new HomeLikeAdapter(getActivity(), new ArrayList<>(),2);
                ((HomeLikeAdapter) adapter).setShowTime(false);
                rvAmoyList.setLayoutManager(new LinearLayoutManager(getActivity()));
                rvAmoyList.setAdapter(adapter);
                break;
            case 5:
                llVideo.setVisibility(View.GONE);
                adapter = new ProductAdapter(getActivity(), new ArrayList<>());
                ((ProductAdapter)adapter).setListener(this);
                rvAmoyList.setLayoutManager(new LinearLayoutManager(getActivity()));
                rvAmoyList.setAdapter(adapter);
                break;
            case 6:
                llVideo.setVisibility(View.GONE);
                adapter = new SearchUserAdapter(getActivity(), new ArrayList<>());
                ((SearchUserAdapter)adapter).setListener(this);
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
        setArray(array);
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
                tvHot.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_4F9BFA));
                tvNews.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_333333));
                break;
            case R.id.tv_news:
                tvHot.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_333333));
                tvNews.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_4F9BFA));
                break;
        }
    }

    @Override
    public void onItemClick(ChannelListBean bean) {
        WebViewContainerActivity.go2this(getActivity(),bean.getName(),HttpConstant.WEB_URL_USER_INFO+bean.getId(),
                WebViewContainerFragment.TYPE_USER_INFO);
    }

    @Override
    public void onFavoriteClick(ChannelListBean bean) {
//        currBean = bean;
        Map<Object, Object> map = new HashMap<>();
        map.put("id", bean.getId());
        if (!bean.isFollower()) {
            presenter.cancelFollow(map);
        } else {
            presenter.clickFollow(map);
        }
    }

    @Override
    public void onItemClick(CategoryBean bean) {
        WebViewContainerActivity.go2this(getActivity(),bean.getName(), HttpConstant.WEB_URL_PRODUCT_DETAIL+bean.getId(),
                WebViewContainerFragment.TYPE_PRODUCT_DETAIL);
    }

    @Override
    public void onItemClick(TaoLearnListBean bean) {
        HotSchoolActivity.go2this(getActivity(),bean.getId());
    }
}

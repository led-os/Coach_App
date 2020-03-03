package com.jsjlzj.wayne.ui.publicac;


import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.home.HomeLikeAdapter;
import com.jsjlzj.wayne.adapter.recycler.home.MatchAdapter;
import com.jsjlzj.wayne.adapter.recycler.home.ProductAdapter;
import com.jsjlzj.wayne.adapter.recycler.search.SeaerchTaoLearnAdapter;
import com.jsjlzj.wayne.adapter.recycler.search.SearchUserAdapter;
import com.jsjlzj.wayne.entity.store.search.ChannelListBean;
import com.jsjlzj.wayne.entity.store.search.SearchBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * @ClassName: SearchAllFragment
 * @Description: 搜索所有的相关数据
 * @Author: 曾海强
 * @CreateDate:
 */
public class SearchAllFragment extends MVPBaseFragment<HomeView, HomePresenter> implements HomeView, SearchUserAdapter.OnSearchUserClickListener {


    @BindView(R.id.rv_tao_learn)
    RecyclerView rvTaoLearn;
    @BindView(R.id.rel_tao_lean)
    RelativeLayout relTaoLean;
    @BindView(R.id.rv_match)
    RecyclerView rvMatch;
    @BindView(R.id.rel_match)
    RelativeLayout relMatch;
    @BindView(R.id.rv_video)
    RecyclerView rvVideo;
    @BindView(R.id.rel_video)
    RelativeLayout relVideo;
    @BindView(R.id.rv_information)
    RecyclerView rvInformation;
    @BindView(R.id.rel_information)
    RelativeLayout relInformation;
    @BindView(R.id.rv_product)
    RecyclerView rvProduct;
    @BindView(R.id.rel_product)
    RelativeLayout relProduct;
    @BindView(R.id.rv_user)
    RecyclerView rvUser;
    @BindView(R.id.rel_user)
    RelativeLayout relUser;

    private SeaerchTaoLearnAdapter seaerchTaoLearnAdapter;
    private MatchAdapter matchAdapter;
    private HomeLikeAdapter videoAdapter;
    private HomeLikeAdapter informationAdapter;
    private ProductAdapter productAdapter;
    private SearchUserAdapter searchUserAdapter;

    public SearchAllFragment() {}


    public static SearchAllFragment getInstance() {
        SearchAllFragment fragment = new SearchAllFragment();
        return fragment;
    }

    private SearchBean searchBean;

    public void setSearchBean(SearchBean searchBean) {
        this.searchBean = searchBean;
        if(searchBean.getData() != null){
            hideEmpty();
            if(searchBean.getData().getTaoLearnList() != null && searchBean.getData().getTaoLearnList().size() > 0){
                relTaoLean.setVisibility(View.VISIBLE);
                seaerchTaoLearnAdapter.setData(searchBean.getData().getTaoLearnList());
            }else {
                relTaoLean.setVisibility(View.GONE);
            }

            if(searchBean.getData().getSportEventList() != null && searchBean.getData().getSportEventList().size() > 0){
                relMatch.setVisibility(View.VISIBLE);
                matchAdapter.setData(searchBean.getData().getSportEventList());
            }else {
                relMatch.setVisibility(View.GONE);
            }

            if(searchBean.getData().getVideoList() != null && searchBean.getData().getVideoList().size() > 0){
                relVideo.setVisibility(View.VISIBLE);
                videoAdapter.setData(searchBean.getData().getVideoList());
            }else {
                relVideo.setVisibility(View.GONE);
            }

            if(searchBean.getData().getInformationList() != null && searchBean.getData().getInformationList().size() > 0){
                relInformation.setVisibility(View.VISIBLE);
                informationAdapter.setData(searchBean.getData().getInformationList());
            }else {
                relInformation.setVisibility(View.GONE);
            }

            if(searchBean.getData().getProductList() != null && searchBean.getData().getProductList().size() > 0){
                relProduct.setVisibility(View.VISIBLE);
                productAdapter.setData(searchBean.getData().getProductList());
            }else {
                relProduct.setVisibility(View.GONE);
            }

            if(searchBean.getData().getChannelList() != null && searchBean.getData().getChannelList().size() > 0){
                relUser.setVisibility(View.VISIBLE);
                searchUserAdapter.setData(searchBean.getData().getChannelList());
            }else {
                relUser.setVisibility(View.GONE);
            }
        }else {
            showEmpty(R.id.rel_empty,0,null);
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_search_all;
    }

    @Override
    protected void initViewAndControl(View view) {
        initRecyclerView();
    }

    private void initRecyclerView() {
        rvTaoLearn.setHasFixedSize(true);
        rvTaoLearn.setNestedScrollingEnabled(false);
        seaerchTaoLearnAdapter = new SeaerchTaoLearnAdapter(getActivity(),  new ArrayList<>());
        rvTaoLearn.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvTaoLearn.setAdapter(seaerchTaoLearnAdapter);

        rvMatch.setHasFixedSize(true);
        rvMatch.setNestedScrollingEnabled(false);
        matchAdapter = new MatchAdapter(getActivity(), new ArrayList<>());
        rvMatch.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMatch.setAdapter(matchAdapter);

        rvVideo.setHasFixedSize(true);
        rvVideo.setNestedScrollingEnabled(false);
        videoAdapter = new HomeLikeAdapter(getActivity(), new ArrayList<>());
        rvVideo.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvVideo.setAdapter(videoAdapter);

        rvInformation.setHasFixedSize(true);
        rvInformation.setNestedScrollingEnabled(false);
        informationAdapter = new HomeLikeAdapter(getActivity(), new ArrayList<>());
        informationAdapter.setShowTime(false);
        rvInformation.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvInformation.setAdapter(informationAdapter);

        rvProduct.setHasFixedSize(true);
        rvProduct.setNestedScrollingEnabled(false);
        productAdapter = new ProductAdapter(getActivity(), new ArrayList<>());
        rvProduct.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvProduct.setAdapter(productAdapter);

        rvUser.setHasFixedSize(true);
        rvUser.setNestedScrollingEnabled(false);
        searchUserAdapter = new SearchUserAdapter(getActivity(), new ArrayList<>());
        searchUserAdapter.setListener(this);
        rvUser.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvUser.setAdapter(searchUserAdapter);

        showEmpty(R.id.rel_empty,0,null);
    }

    @Override
    protected void fragment2Front() {

    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public void onItemClick(ChannelListBean string) {

    }

    @Override
    public void onFavoriteClick(ChannelListBean bean) {
        Map<Object, Object> map = new HashMap<>();
        map.put("id", bean.getId());
        if (!bean.isFollower()) {
            presenter.cancelFollow(map);
        } else {
            presenter.clickFollow(map);
        }
    }
}

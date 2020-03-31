package com.jsjlzj.wayne.ui.store.list;


import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.home.HomeLikeAdapter;
import com.jsjlzj.wayne.adapter.recycler.home.InformationAdapter;
import com.jsjlzj.wayne.adapter.recycler.search.SearchAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.home.VideoBean;
import com.jsjlzj.wayne.entity.store.home.VideoPageBean;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.CustomXRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @ClassName: MoreDataFragment
 * @Description: 收藏子界面
 * @Author: 曾海强
 * @CreateDate:
 */
public class MoreDataFragment extends MVPBaseFragment<HomeView, HomePresenter> implements HomeView, XRecyclerView.LoadingListener, InformationAdapter.OnItemClickListener, SearchAdapter.OnSearchItemClickListener {

    @BindView(R.id.rv_more_data)
    CustomXRecyclerView rvMoreData;
    /**
     * 0:动态  1 :视频  2 ：文章
     */
    private int type;
    private int pageNo = 1;
    private int pageCount;
    private boolean isRefresh;
    private Map<Object, Object> map = new HashMap<>();
    private RecyclerView.Adapter adapter;
    private List<VideoBean> videoList = new ArrayList<>();


    public MoreDataFragment() {
    }

    public MoreDataFragment(int type) {
        this.type = type;
    }

    public static MoreDataFragment getInstance(int type) {
        MoreDataFragment fragment = new MoreDataFragment(type);
        return fragment;
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_more_data;
    }

    @Override
    protected void initViewAndControl(View view) {
        initView();
        loadData(true);
    }

    @Override
    protected void fragment2Front() {
    }


    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }


    private void initView() {
        rvMoreData.setPullRefreshEnabled(true);
        rvMoreData.setLoadingMoreEnabled(true);
        rvMoreData.setLoadingListener(this);
        switch (type) {
            case 0:
                adapter = new SearchAdapter(getActivity(),videoList);
                ((SearchAdapter) adapter).setListener(this);
                rvMoreData.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                rvMoreData.setAdapter(adapter);
                break;
            case 1:
                adapter = new HomeLikeAdapter(getActivity(), videoList,1);
                ((HomeLikeAdapter) adapter).setAllTwo(true);
                ((HomeLikeAdapter) adapter).setShowTime(true);
                rvMoreData.setLayoutManager(new LinearLayoutManager(getActivity()));
                rvMoreData.setAdapter(adapter);
                break;
            case 2:
                adapter = new InformationAdapter(getActivity(), videoList);
                ((InformationAdapter) adapter).setListener(bean -> WebViewContainerActivity.go2this(getActivity(),bean.getName(), HttpConstant.WEB_URL_AETICLE_DETAIL+bean.getId(),
                        WebViewContainerFragment.TYPE_ARTICLE_DETAIL));
                rvMoreData.setLayoutManager(new LinearLayoutManager(getActivity()));
                rvMoreData.setAdapter(adapter);
                break;
            default:
                break;
        }
    }

    private void loadData(boolean isRefresh) {
        this.isRefresh = isRefresh;
        if (isRefresh) {
            pageNo = 1;
        }
        map.clear();
        map.put(HttpConstant.PAGE_NO, pageNo);
        map.put(HttpConstant.PAGE_SIZE, HttpConstant.PAGE_SIZE_NUMBER);
        switch (type) {
            case 0:
                presenter.getCollectDynamicList(map);
                break;
            case 1:
                presenter.getCollectVideoList(map);
                break;
            case 2:
                presenter.getCollectInformationList(map);
                break;
            default:
                break;
        }

    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public void onLoadMore() {
        if (pageNo < pageCount ) {
            pageNo++;
            loadData(false);
        } else {
            LogAndToastUtil.toast(getActivity(), getString(R.string.has_no_more_data));
        }
    }

    @Override
    public void onItemClick(VideoBean bean) {
        WebViewContainerActivity.go2this(getActivity(),bean.getName(),HttpConstant.WEB_URL_DYNAMIC_DETAIL+bean.getId(),
                WebViewContainerFragment.TYPE_DYNAMIC_DETAIL);
    }

    @Override
    public void onHearClick(VideoBean bean) {
        WebViewContainerActivity.go2this(getActivity(),bean.getChannelName(),HttpConstant.WEB_URL_USER_INFO+bean.getChannelId(),
                WebViewContainerFragment.TYPE_USER_INFO);
    }

    @Override
    public void onFavoriteClick(VideoBean bean) {
        Map<Object, Object> map = new HashMap<>();
        map.put("id", bean.getId());
        map.put("module", "COMMUNITY");
        if (!bean.isCollect()) {
            presenter.cancelCollect(map);
        } else {
            presenter.clickCollect(map);
        }
    }

    @Override
    public void onDeleteClick(VideoBean bean) {

    }


    @Override
    public void getVideoListSuccess(MdlBaseHttpResp<VideoPageBean> resp) {
        rvMoreData.refreshComplete();
        rvMoreData.loadMoreComplete();
        if (resp.getStatus() == HttpConstant.R_HTTP_OK) {
            pageNo = resp.getData().getData().getPageNo();
            int totalCount = resp.getData().getData().getTotalCount();
            int a = totalCount % HttpConstant.PAGE_SIZE_NUMBER;
            if (a == 0) {
                pageCount = totalCount / HttpConstant.PAGE_SIZE_NUMBER;
            } else {
                pageCount = (totalCount / HttpConstant.PAGE_SIZE_NUMBER) + 1;
            }
            List<VideoBean> list = resp.getData().getData().getResult();
            if (list != null && list.size() > 0) {
                if (isRefresh) {
                    this.videoList.clear();
                }
                this.videoList.addAll(list);
                setData();
                hideEmpty();
            } else if (isRefresh) {
                // 无数据
                showEmpty(R.id.rel_empty, 0, null);
            }
        }
    }


    private void setData() {
        switch (type) {
            case 0:
                ((SearchAdapter) adapter).setData(this.videoList);
                break;
            case 1:
                ((HomeLikeAdapter) adapter).setData(this.videoList);
                break;
            case 2:
                ((InformationAdapter) adapter).setData(this.videoList);
                break;
        }
    }
}

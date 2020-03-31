package com.jsjlzj.wayne.ui.store.home.community;


import android.view.View;

import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.search.SearchAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.DataBean;
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
import com.jsjlzj.wayne.widgets.dialog.CommonDialog;
import com.netease.nim.uikit.common.ToastHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @ClassName: CommunityItemFragment
 * @Description: 单个评论列表
 * @Author: 曾海强
 * @CreateDate:
 */
public class CommunityItemFragment extends MVPBaseFragment<HomeView, HomePresenter> implements HomeView, SearchAdapter.OnSearchItemClickListener, XRecyclerView.LoadingListener {

    @BindView(R.id.rv_dynamic)
    CustomXRecyclerView rvDynamic;

    private Map<Object,Object> map = new HashMap<>();
    private SearchAdapter adapter;
    private List<VideoBean> videoList = new ArrayList<>();
    /**
     * 0:热门  1：关注  2：同城  3 ： 全部  4 :我的动态
     */
    private int type;
    private int pageNo = 1,pageCount;
    private String cityName;
    private boolean isHot;
    private boolean isFollower;
    private boolean isRefresh;
    private VideoBean currBean;


    public CommunityItemFragment() {
    }


    public CommunityItemFragment(int type,String cityName) {
        this();
        this.type = type;
        this.cityName = cityName;
    }


    public void setCityName(String cityName) {
        this.cityName = cityName;
        loadData(true);
    }

    public static CommunityItemFragment getInstance(int type,String cityName) {
        CommunityItemFragment fragment = new CommunityItemFragment(type,cityName);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_community_item;
    }

    @Override
    protected void initViewAndControl(View view) {
        adapter = new SearchAdapter(getActivity(), new ArrayList<>());
        adapter.setListener(this);
        if(type == 4){
            adapter.setShowDelete(true);
        }
        rvDynamic.setPullRefreshEnabled(true);
        rvDynamic.setLoadingMoreEnabled(true);
        rvDynamic.setLoadingListener(this);
        rvDynamic.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvDynamic.setAdapter(adapter);
        loadData(true);
    }

    @Override
    protected void fragment2Front() {
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }



    public void loadData(boolean isRefresh){
        this.isRefresh = isRefresh;
        if (isRefresh) {
            pageNo = 1;
        }
        map.clear();
        isHot = type == 0;
        isFollower = type == 1;

        if(type == 0){
            map.put("isHot",true);
        }else if(type == 1){
            map.put("isFollower",true);
        }else if(type == 2){
            map.put("city",cityName);
        }
        map.put(HttpConstant.PAGE_NO, pageNo);
        map.put(HttpConstant.PAGE_SIZE, HttpConstant.PAGE_SIZE_NUMBER);
        map.put("type",0);
        if(type == 4){
            presenter.getMineDynamicList(map);
        }else {
            presenter.getDynamicList(map);
        }
    }


    @Override
    public void getVideoListSuccess(MdlBaseHttpResp<VideoPageBean> resp) {
        rvDynamic.refreshComplete();
        rvDynamic.loadMoreComplete();
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
                    videoList.clear();
                }
                videoList.addAll(list);
                adapter.setData(videoList);
                hideEmpty();
            } else if (isRefresh) {
                // 无数据
                showEmpty(R.id.rel_empty, 0, null);
            }
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
        currBean = bean;
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
        currBean = bean;
        if(type == 4){
            CommonDialog dialog = new CommonDialog(getActivity(), "确定删除该动态吗？", new CommonDialog.ClickListener() {
                @Override
                public void clickConfirm() {
                    map.clear();
                    map.put("id",bean.getId());
                    presenter.deleteDynamic(map);
                }

                @Override
                public void clickCancel() {}
            });
            dialog.show();
        }
    }

    @Override
    public void deleteDynamicSuccess(MdlBaseHttpResp<DataBean> resp) {
        LogAndToastUtil.toast("删除成功");
        if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            videoList.remove(currBean);
            adapter.notifyDataSetChanged();
            if(videoList == null || videoList.size() ==0){
                showEmpty(R.id.rel_empty,0,null);
            }
        }
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public void onLoadMore() {
        if (pageNo < pageCount) {
            pageNo++;
            loadData(false);
        } else {
            ToastHelper.showToast(getActivity(), getString(R.string.has_no_more_data));
        }
    }
}

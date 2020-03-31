package com.jsjlzj.wayne.ui.store.list;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.home.HomeLikeAdapter;
import com.jsjlzj.wayne.adapter.recycler.home.InformationAdapter;
import com.jsjlzj.wayne.adapter.recycler.home.ProductAdapter;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.home.CategoryBean;
import com.jsjlzj.wayne.entity.store.home.CategoryPageBean;
import com.jsjlzj.wayne.entity.store.home.VideoBean;
import com.jsjlzj.wayne.entity.store.home.VideoPageBean;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.widgets.CustomXRecyclerView;
import com.netease.nim.uikit.common.ToastHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @ClassName: MoreDataActivity
 * @Description: 更多数据界面
 * @Author: 曾海强
 * @CreateDate: 2020/3/17 10:55
 */
public class MoreDataActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView, XRecyclerView.LoadingListener, InformationAdapter.OnItemClickListener {

    @BindView(R.id.rv_more_data)
    CustomXRecyclerView rvMoreData;
    private String title;
    /**
     * 0:热门资讯  1 :干货  2 ：资讯   3 ：产品
     */
    private int type;
    private int pageNo=1;
    private int pageCount;
    private boolean isRefresh;
    private Map<Object, Object> map = new HashMap<>();
    private RecyclerView.Adapter adapter;
    private List<VideoBean> videoList = new ArrayList<>();
    private List<CategoryBean> categoryList = new ArrayList<>();
    private int categoryId;

    public static void go2this(Context context, String title, int type) {
        go2this(context,title,type,0);
    }

    public static void go2this(Context context, String title, int type,int categoryId) {
        Intent intent = new Intent(context, MoreDataActivity.class);
        intent.putExtra(ExtraConstant.EXTRA_TITLE, title);
        intent.putExtra(ExtraConstant.EXTRA_SHOW_TYPE, type);
        intent.putExtra(ExtraConstant.EXTRA_CATEGORY_ID,categoryId);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_more_data;
    }

    @Override
    protected void initViewAndControl() {
        title = getIntent().getStringExtra(ExtraConstant.EXTRA_TITLE);
        type = getIntent().getIntExtra(ExtraConstant.EXTRA_SHOW_TYPE, 0);
        categoryId = getIntent().getIntExtra(ExtraConstant.EXTRA_CATEGORY_ID,0);
        initTitle(title);
        initView();
        loadData(true);
    }


    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }


    private void initView(){
        rvMoreData.setPullRefreshEnabled(true);
        rvMoreData.setLoadingMoreEnabled(true);
        rvMoreData.setLoadingListener(this);
        switch (type) {
            case 0:
                adapter = new InformationAdapter(this, videoList);
                ((InformationAdapter)adapter).setListener(this);
                rvMoreData.setLayoutManager(new LinearLayoutManager(this));
                rvMoreData.setAdapter(adapter);
                break;
            case 1:
            case 2:
                adapter = new HomeLikeAdapter(this, videoList,type);
                ((HomeLikeAdapter)adapter).setAllOne(true);
                rvMoreData.setLayoutManager(new LinearLayoutManager(this));
                rvMoreData.setAdapter(adapter);
                break;
            case 3:
                adapter = new ProductAdapter(this, categoryList);
                rvMoreData.setLayoutManager(new LinearLayoutManager(this));
                rvMoreData.setAdapter(adapter);
                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
            case 7:

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
        switch (type) {
            case 0:
                map.clear();
                map.put(HttpConstant.PAGE_NO, pageNo);
                map.put(HttpConstant.PAGE_SIZE, HttpConstant.PAGE_SIZE_NUMBER);
                map.put(HttpConstant.STATUS, 2);
                presenter.getInformationList(map);
                break;
            case 1:
                map.clear();
                map.put(HttpConstant.PAGE_NO, pageNo);
                map.put(HttpConstant.PAGE_SIZE, HttpConstant.PAGE_SIZE_NUMBER);
                map.put(HttpConstant.STATUS, 0);
                map.put(HttpConstant.CATEGORY_ID,categoryId);
                presenter.getDriedFoodList(map);
                break;
            case 2:
                map.clear();
                map.put(HttpConstant.PAGE_NO, pageNo);
                map.put(HttpConstant.PAGE_SIZE, HttpConstant.PAGE_SIZE_NUMBER);
                map.put(HttpConstant.STATUS, 0);
                map.put(HttpConstant.CATEGORY_ID,categoryId);
                presenter.getInformationList(map);
                break;
            case 3:
                map.clear();
                map.put(HttpConstant.PAGE_NO, pageNo);
                map.put(HttpConstant.PAGE_SIZE, HttpConstant.PAGE_SIZE_NUMBER);
                map.put(HttpConstant.STATUS, 0);
                map.put(HttpConstant.CATEGORY_ID,categoryId);
                presenter.getProductList(map);
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
            ToastHelper.showToast(this, getString(R.string.has_no_more_data));
        }
    }

    @Override
    public void onItemClick(VideoBean bean) {
        if(type == 0){
            WebViewContainerActivity.go2this(this,bean.getName(), HttpConstant.WEB_URL_AETICLE_DETAIL+bean.getId(),
                    WebViewContainerFragment.TYPE_ARTICLE_DETAIL);
        }else if(type == 1){
            WebViewContainerActivity.go2this(this,bean.getName(),HttpConstant.WEB_URL_DYNAMIC_DETAIL+bean.getId(),
                    WebViewContainerFragment.TYPE_DYNAMIC_DETAIL);
        }else if(type == 2) {
            WebViewContainerActivity.go2this(this,bean.getName(),HttpConstant.WEB_URL_AETICLE_DETAIL+bean.getId(),
                    WebViewContainerFragment.TYPE_ARTICLE_DETAIL);
        }
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


    @Override
    public void getCategoryListSuccess(MdlBaseHttpResp<CategoryPageBean> resp) {
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
            List<CategoryBean> list = resp.getData().getData().getResult();
            if (list != null && list.size() > 0) {
                if (isRefresh) {
                    this.categoryList.clear();
                }
                this.categoryList.addAll(list);
                setData();
                hideEmpty();
            } else if (isRefresh) {
                // 无数据
                showEmpty(R.id.rel_empty, 0, null);
            }
        }
    }

    private void setData() {
        switch (type){
            case 0:
                ((InformationAdapter)adapter).setData(this.videoList);
                break;
            case 1:
            case 2:
                ((HomeLikeAdapter)adapter).setData(this.videoList);
                break;
            case 3:
                ((ProductAdapter)adapter).setData(this.categoryList);
                break;
        }
    }
}

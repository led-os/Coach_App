package com.jsjlzj.wayne.ui.store.home;


import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.home.DriedTypeAdapter;
import com.jsjlzj.wayne.adapter.recycler.home.ProductAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.home.AmoySchoolBean;
import com.jsjlzj.wayne.entity.store.home.BannerBean;
import com.jsjlzj.wayne.entity.store.home.CategoryBean;
import com.jsjlzj.wayne.entity.store.home.CategoryPageBean;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.widgets.CustomXRecyclerView;
import com.jsjlzj.wayne.widgets.LocalImageHolderView;
import com.netease.nim.uikit.common.ToastHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 *
 * @ClassName:      产品列表
 * @Description:    java类作用描述
 * @Author:         曾海强
 * @CreateDate:
 */
public class ProductFragment extends MVPBaseFragment<HomeView, HomePresenter> implements HomeView, ProductAdapter.OnItemClickListener, XRecyclerView.LoadingListener {

    @BindView(R.id.scroll_banner)
    ConvenientBanner scrollBanner;
    @BindView(R.id.rv_state)
    RecyclerView rvState;
    @BindView(R.id.rv_like)
    CustomXRecyclerView rvLike;

    private DriedTypeAdapter driedTypeAdapter;
    private ProductAdapter productAdapter;
    private List<BannerBean> images = new ArrayList<>();
    private List<CategoryBean> categoryList = new ArrayList<>();
    private List<CategoryBean> productList = new ArrayList<>();
    private Map<Object,Object> map = new HashMap<>();
    private int typeId;
    private int pageNo=1;
    private int pageCount;
    private boolean isRefresh;
    public ProductFragment() {
    }

    public static ProductFragment getInstance() {
        ProductFragment fragment = new ProductFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_product;
    }

    @Override
    protected void initViewAndControl(View view) {
        initRecycler();
        presenter.getProductData();
    }

    @Override
    protected void fragment2Front() {
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    private void initRecycler() {
        rvState.setHasFixedSize(true);
        rvState.setNestedScrollingEnabled(false);
        driedTypeAdapter = new DriedTypeAdapter(getActivity(),categoryList,3);
        rvState.setLayoutManager(new GridLayoutManager(getActivity(),4));
        rvState.setAdapter(driedTypeAdapter);


        rvLike.setLoadingListener(this);
        productAdapter = new ProductAdapter(getActivity(),productList);
        productAdapter.setListener(this);
        rvLike.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvLike.setAdapter(productAdapter);
    }

    private void initBanner() {
        scrollBanner.setPages(
                new CBViewHolderCreator() {
                    @Override
                    public LocalImageHolderView createHolder(View itemView) {
                        return new LocalImageHolderView(itemView);
                    }

                    @Override
                    public int getLayoutId() {
                        return R.layout.item_localimage;
                    }
                }, images)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.bg_circle_ccfffff_6, R.drawable.bg_circle_4f9bfa_6})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(position -> {
//                    BannerBean bean = images.get(position);
//                    WebViewContainerActivity.go2this(getActivity(),bean.getTitle(),bean.getLink(), WebViewContainerFragment.TYPE_BANNER_LINK_URL);
                })
                .setCanLoop(true);
    }

    @Override
    public void getDriedFoodSuccess(MdlBaseHttpResp<AmoySchoolBean> resp) {
        AmoySchoolBean.DataBean bean = resp.getData().getData();
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != bean) {
            if (null != bean.getBanner() && bean.getBanner().size() > 0) {
                images = bean.getBanner();
                if (images != null && images.size() > 0) {
                    initBanner();
                }
            }

            if (null != bean.getCategory() && bean.getCategory().size() > 0) {
                categoryList = bean.getCategory();
                if (categoryList != null && categoryList.size() > 0) {
                    driedTypeAdapter.setData(categoryList);
                    typeId = categoryList.get(0).getId();
                    loadData(true);
                }
            }
        }
    }


    @Override
    public void getCategoryListSuccess(MdlBaseHttpResp<CategoryPageBean> resp) {
        rvLike.refreshComplete();
        rvLike.loadMoreComplete();
        if (resp.getStatus() == HttpConstant.R_HTTP_OK){
            pageNo = resp.getData().getData().getPageNo();
            int totalCount = resp.getData().getData().getTotalCount();
            int a = totalCount % HttpConstant.PAGE_SIZE_NUMBER;
            if (a == 0) {
                pageCount = totalCount / HttpConstant.PAGE_SIZE_NUMBER;
            } else {
                pageCount =( totalCount / HttpConstant.PAGE_SIZE_NUMBER)+1;
            }
            List<CategoryBean> list = resp.getData().getData().getResult();
            if (list != null && list.size() > 0) {
                if (isRefresh) {
                    productList.clear();
                }
                productList.addAll(list);
                productAdapter.setData(productList);
                hideEmpty();
            } else if (isRefresh) {
                // 无数据
                showEmpty(R.id.rel_empty,0,null);
            }
        }

    }

    private void loadData(boolean isRefresh) {
        this.isRefresh = isRefresh;
        if(isRefresh) {
            pageNo = 1;
        }
        map.clear();
        map.put(HttpConstant.PAGE_NO, pageNo);
        map.put(HttpConstant.PAGE_SIZE, HttpConstant.PAGE_SIZE_NUMBER);
        map.put(HttpConstant.CATEGORY_ID, typeId);
        presenter.getProductList(map);
    }

    @Override
    public void onResume() {
        super.onResume();
        scrollBanner.startTurning();
    }

    @Override
    public void onPause() {
        super.onPause();
        scrollBanner.stopTurning();
    }

    @Override
    public void onItemClick(CategoryBean bean) {
        WebViewContainerActivity.go2this(getActivity(),bean.getName(),HttpConstant.WEB_URL_PRODUCT_DETAIL,
                WebViewContainerFragment.TYPE_PRODUCT_DETAIL);
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public void onLoadMore() {
        if (pageNo < pageCount -1) {
            pageNo++;
            loadData(false);
        } else {
            ToastHelper.showToast(getContext(), getString(R.string.has_no_more_data));
        }
    }
}

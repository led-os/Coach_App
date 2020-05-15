package com.jsjlzj.wayne.ui.store.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.shopping.ProductAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.shopping.ShoppingBean;
import com.jsjlzj.wayne.entity.shopping.ShoppingPageBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.keyboard.KeyboardUtil;
import com.jsjlzj.wayne.widgets.CustomXRecyclerView;
import com.jsjlzj.wayne.widgets.SearchBarView;
import com.netease.nim.uikit.common.ToastHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: SearchShopActivity
 * @Description: 商城搜索界面
 * @Author: 曾海强
 * @CreateDate:
 */
public class SearchShopActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView, XRecyclerView.LoadingListener {

    @BindView(R.id.tv_comprehensive)
    TextView tvComprehensive;
    @BindView(R.id.tv_volume)
    TextView tvVolume;
    @BindView(R.id.tv_new)
    TextView tvNew;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.img_time_top)
    ImageView imgTimeTop;
    @BindView(R.id.img_time_bottom)
    ImageView imgTimeBottom;
    @BindView(R.id.rel_price)
    RelativeLayout relPrice;
    @BindView(R.id.rv_search_shop)
    CustomXRecyclerView rvSearchShop;
    @BindView(R.id.img_volume_top)
    ImageView imgVolumeTop;
    @BindView(R.id.img_volume_bottom)
    ImageView imgVolumeBottom;
    @BindView(R.id.rel_volume)
    RelativeLayout relVolume;

    /**
     * 0,高销量；1,上新；2,价格-高到低;3价格-低到高;4,低销量；5，不是新品
     */
    private int searchType = -1;

    private ProductAdapter productAdapter;
    private Map<Object, Object> map = new HashMap<>();
    private int pageNo = 1, pageCount;
    private boolean isRefresh;
    private List<ShoppingBean> shoppingList = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_search_shop;
    }

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, SearchShopActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initSearchTitle();
        initSearchBar();
        initRecycler();
        tvComprehensive.setOnClickListener(clickListener);
        relVolume.setOnClickListener(clickListener);
        tvNew.setOnClickListener(clickListener);
        relPrice.setOnClickListener(clickListener);
        mRightTv.setOnClickListener(clickListener);

        loadData(true);
    }

    private void loadData(boolean isRefresh) {
        map.clear();
        if (isRefresh) {
            this.isRefresh = isRefresh;
        }
        if (searchType != -1) {
            map.put("type", searchType);
        }
        map.put("name", mSearchBar.getInputText());
        map.put(HttpConstant.PAGE_NO, pageNo);
        map.put(HttpConstant.PAGE_SIZE, HttpConstant.PAGE_SIZE_NUMBER);
        presenter.getSearchProductList(map);
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.tv_right_btn:
                finish();
                break;
            case R.id.tv_comprehensive:
                tvComprehensive.setTextColor(ContextCompat.getColor(this, R.color.color_f1404b));
                tvVolume.setTextColor(ContextCompat.getColor(this, R.color.color_666666));
                tvNew.setTextColor(ContextCompat.getColor(this, R.color.color_666666));
                tvPrice.setTextColor(ContextCompat.getColor(this, R.color.color_666666));
                imgTimeTop.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.triangle_up_normal));
                imgTimeBottom.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.triangle_down_pressed));
                imgVolumeTop.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.triangle_up_normal));
                imgVolumeBottom.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.triangle_down_pressed));
                searchType = -1;
                loadData(true);
                break;
            case R.id.rel_volume:
                tvVolume.setTextColor(ContextCompat.getColor(this, R.color.color_f1404b));
                tvComprehensive.setTextColor(ContextCompat.getColor(this, R.color.color_666666));
                tvNew.setTextColor(ContextCompat.getColor(this, R.color.color_666666));
                tvPrice.setTextColor(ContextCompat.getColor(this, R.color.color_666666));
                imgTimeTop.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.triangle_up_normal));
                imgTimeBottom.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.triangle_down_pressed));
                if(searchType != 0){
                    searchType = 0;
                    imgVolumeTop.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.triangle_up_normal));
                    imgVolumeBottom.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.triangle_down_pressed));
                }else {
                    searchType = 4;
                    imgVolumeTop.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.triangle_up_pressed));
                    imgVolumeBottom.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.triangle_down_normal));
                }
                loadData(true);
                break;
            case R.id.tv_new:
                searchType = 1;
                tvNew.setTextColor(ContextCompat.getColor(this, R.color.color_f1404b));
                tvVolume.setTextColor(ContextCompat.getColor(this, R.color.color_666666));
                tvComprehensive.setTextColor(ContextCompat.getColor(this, R.color.color_666666));
                tvPrice.setTextColor(ContextCompat.getColor(this, R.color.color_666666));
                imgTimeTop.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.triangle_up_normal));
                imgTimeBottom.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.triangle_down_pressed));
                imgVolumeTop.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.triangle_up_normal));
                imgVolumeBottom.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.triangle_down_pressed));
                loadData(true);
                break;
            case R.id.rel_price:
                tvPrice.setTextColor(ContextCompat.getColor(this, R.color.color_f1404b));
                tvVolume.setTextColor(ContextCompat.getColor(this, R.color.color_666666));
                tvComprehensive.setTextColor(ContextCompat.getColor(this, R.color.color_666666));
                tvNew.setTextColor(ContextCompat.getColor(this, R.color.color_666666));
                imgVolumeTop.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.triangle_up_normal));
                imgVolumeBottom.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.triangle_down_pressed));
                if (searchType != 2) {
                    searchType = 2;
                    imgTimeTop.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.triangle_up_normal));
                    imgTimeBottom.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.triangle_down_pressed));
                } else {
                    searchType = 3;
                    imgTimeTop.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.triangle_up_pressed));
                    imgTimeBottom.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.triangle_down_normal));
                }
                loadData(true);
                break;
            default:
                break;
        }
    }

    private void initRecycler() {
        productAdapter = new ProductAdapter(this, shoppingList);
        rvSearchShop.setLoadingListener(this);
        rvSearchShop.setLayoutManager(new GridLayoutManager(this, 2));
        rvSearchShop.setAdapter(productAdapter);
    }

    private void initSearchBar() {
        mSearchBar.setOnEditTextChangeListener(new SearchBarView.EditTextCallback() {
            @Override
            public void onEditTextChange(String str) {
            }

            @Override
            public void onEditFinish(String str) {
            }

            @Override
            public void onClickSoftWare(String str) {
                mSearchBar.getSearchEditText().setText(str);
                mSearchBar.getSearchEditText().setSelection(str.length());
                loadData(true);
                KeyboardUtil.closeKeyboard(tvPrice, SearchShopActivity.this);
            }
        });
    }


    @Override
    public void getShoppingListSuccess(MdlBaseHttpResp<ShoppingPageBean> resp) {
        rvSearchShop.refreshComplete();
        rvSearchShop.loadMoreComplete();
        if (resp.getStatus() == HttpConstant.R_HTTP_OK) {
            pageNo = resp.getData().getData().getPageNo();
            int totalCount = resp.getData().getData().getTotalCount();
            int a = totalCount % HttpConstant.PAGE_SIZE_NUMBER;
            if (a == 0) {
                pageCount = totalCount / HttpConstant.PAGE_SIZE_NUMBER;
            } else {
                pageCount = (totalCount / HttpConstant.PAGE_SIZE_NUMBER) + 1;
            }
            List<ShoppingBean> list = resp.getData().getData().getResult();
            if (list != null && list.size() > 0) {
                if (isRefresh) {
                    shoppingList.clear();
                }
                shoppingList.addAll(list);
                productAdapter.setData(shoppingList);
                hideEmpty();
            } else if (isRefresh) {
                // 无数据
                showEmpty(R.id.rel_empty, 0, null);
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
            ToastHelper.showToast(this, getString(R.string.has_no_more_data));
        }
    }

}

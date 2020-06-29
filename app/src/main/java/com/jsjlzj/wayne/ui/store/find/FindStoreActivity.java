package com.jsjlzj.wayne.ui.store.find;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.find.BusinessDistrictAdapter;
import com.jsjlzj.wayne.adapter.recycler.find.FindStoreAdapter;
import com.jsjlzj.wayne.adapter.recycler.find.SelectConditionAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.find.BusinessDistrictBean;
import com.jsjlzj.wayne.entity.find.FindStoreBannerBean;
import com.jsjlzj.wayne.entity.find.FindStoreBean;
import com.jsjlzj.wayne.entity.find.FindStoreConditionBean;
import com.jsjlzj.wayne.entity.find.FindStorePageBean;
import com.jsjlzj.wayne.entity.store.home.BannerBean;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
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
 * @ClassName: FindStoreActivity
 * @Description: 俱乐部界面
 * @Author: 曾海强
 * @CreateDate: 2020-06-20
 */
public class FindStoreActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView, XRecyclerView.LoadingListener {

    @BindView(R.id.tv_business)
    TextView tvBusiness;
    @BindView(R.id.ll_business)
    LinearLayout llBusiness;
    @BindView(R.id.tv_sort)
    TextView tvSort;
    @BindView(R.id.img_business)
    ImageView imgBusiness;
    @BindView(R.id.ll_sort)
    LinearLayout llSort;
    @BindView(R.id.tv_select)
    TextView tvSelect;
    @BindView(R.id.ll_select)
    LinearLayout llSelect;
    @BindView(R.id.img_sort)
    ImageView imgSort;
    @BindView(R.id.img_select)
    ImageView imgSelect;
    @BindView(R.id.rv_store)
    CustomXRecyclerView rvStore;
    @BindView(R.id.scroll_banner)
    ConvenientBanner scrollBanner;
    @BindView(R.id.include_condition)
    View includeCondition;
    @BindView(R.id.rv_business)
    RecyclerView rvBusiness;
    @BindView(R.id.rv_nearby)
    RecyclerView rvNearBy;
    @BindView(R.id.rel_business)
    RelativeLayout relBusiness;
    @BindView(R.id.rv_sort)
    RecyclerView rvSort;
    @BindView(R.id.rv_select)
    RecyclerView rvSelect;
    @BindView(R.id.view_other)
    View viewOther;
    @BindView(R.id.rel_select)
    RelativeLayout relSelect;
    @BindView(R.id.tv_new_store)
    TextView tvNewStore;
    @BindView(R.id.tv_business_ing)
    TextView tvBusinessIng;
    @BindView(R.id.tv_stop)
    TextView tvStop;
    @BindView(R.id.tv_house)
    TextView tvHouse;
    @BindView(R.id.tv_reset)
    TextView tvReset;
    @BindView(R.id.tv_finish)
    TextView tvFinish;


    private FindStoreAdapter findStoreAdapter;
    private int pageNo = 1;
    private int pageCount;
    private boolean isRefresh;
    private int orderTypeCode = 1;//1:智能排序;2:离我最近;3:好评优先;4:最新发布;5:人气优先；
    private Map<String, Object> map = new HashMap<>();
    private List<BannerBean> images = new ArrayList<>();
    private List<FindStoreBean> storeList = new ArrayList<>();
    private List<BusinessDistrictBean.DataBean> businessTypeList = new ArrayList<>();
    private BusinessDistrictAdapter businessDistrictAdapter;
    private List<BusinessDistrictBean.DataBean> nearbyList = new ArrayList<>();
    private BusinessDistrictAdapter nearByAdapter;
    private List<BusinessDistrictBean.DataBean> sortList = new ArrayList<>();
    private BusinessDistrictAdapter sortAdapter;
    private SelectConditionAdapter selectConditionAdapter;

    public static void go2this(Context context) {
        context.startActivity(new Intent(context, FindStoreActivity.class));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_find_store;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initRightTitle(getResources().getString(R.string.store), R.drawable.ic_search);
        llBusiness.setOnClickListener(clickListener);
        llSelect.setOnClickListener(clickListener);
        llSort.setOnClickListener(clickListener);
        viewOther.setOnClickListener(clickListener);
        mRightBtn.setOnClickListener(clickListener);
        tvBusinessIng.setOnClickListener(clickListener);
        tvNewStore.setOnClickListener(clickListener);
        tvStop.setOnClickListener(clickListener);
        tvHouse.setOnClickListener(clickListener);
        tvReset.setOnClickListener(clickListener);
        tvFinish.setOnClickListener(clickListener);
        tvBusinessIng.setTag(false);
        tvNewStore.setTag(false);
        tvStop.setTag(false);
        tvHouse.setTag(false);
        rvStore.setPullRefreshEnabled(false);
        rvStore.setLoadingMoreEnabled(true);
        rvStore.setLoadingListener(this);
        findStoreAdapter = new FindStoreAdapter(this, storeList);
        rvStore.setLayoutManager(new LinearLayoutManager(this));
        rvStore.setAdapter(findStoreAdapter);

        initCondition();
        presenter.getStoreBanner();
        presenter.getFindStoreCondition();
        loadData(true);
        presenter.getBusinessDistrict(MyApp.getApp().getProvince(), MyApp.getApp().getCurrentProvince(), MyApp.getApp().getAreaName());
    }

    private void initCondition() {

        rvSort.setLayoutManager(new LinearLayoutManager(this));
        String[] sortArray = getResources().getStringArray(R.array.find_store_sort_list);
        for (int i = 0; i < sortArray.length; i++) {
            BusinessDistrictBean.DataBean bean = new BusinessDistrictBean.DataBean(i + 1, sortArray[i]);
            sortList.add(bean);
        }
        sortAdapter = new BusinessDistrictAdapter(this, sortList,0);
        sortAdapter.setListener(((bean, pos) -> {
            orderTypeCode = bean.getCode();
            tvSort.setText(bean.getShortName());
            loadData(true);
            includeCondition.setVisibility(View.GONE);
        }));
        rvSort.setAdapter(sortAdapter);

        rvNearBy.setLayoutManager(new LinearLayoutManager(this));
        String[] nearArray = getResources().getStringArray(R.array.find_store_nearby_list);
        for (int i = 0; i < nearArray.length; i++) {
            BusinessDistrictBean.DataBean bean = new BusinessDistrictBean.DataBean();
            bean.setShortName(nearArray[i]);
            if (i == 1) {
                bean.setCode(500);
            } else if (i == 2) {
                bean.setCode(1000);
            } else if (i == 3) {
                bean.setCode(2000);
            } else if (i == 4) {
                bean.setCode(5000);
            } else if (i == 5) {
                bean.setCode(10000);
            } else {
                bean.setCode(0);
            }
            nearbyList.add(bean);
        }
        nearByAdapter = new BusinessDistrictAdapter(this, nearbyList);
        nearByAdapter.setListener(((bean, pos) -> {
            loadData(true);
            includeCondition.setVisibility(View.GONE);
        }));
        rvNearBy.setAdapter(nearByAdapter);
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
                    BannerBean bean = images.get(position);
                    if(!TextUtils.isEmpty(bean.getLink())){
                        WebViewContainerActivity.go2this(this, bean.getTitle(), bean.getLink(), WebViewContainerFragment.TYPE_BANNER_LINK_URL);
                    }
                })
                .setCanLoop(true);
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
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.btn_title_right:
                SearchFindStoreActivity.go2this(this);
                break;
            case R.id.ll_business:
                if(includeCondition.getVisibility() == View.VISIBLE){
                    includeCondition.setVisibility(View.GONE);
                }else {
                    tvBusiness.setTextColor(ContextCompat.getColor(this, R.color.color_f1404b));
                    imgBusiness.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_store_top_red));
                    tvSelect.setTextColor(ContextCompat.getColor(this, R.color.color_666666));
                    imgSelect.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_store_bottom));
                    tvSort.setTextColor(ContextCompat.getColor(this, R.color.color_666666));
                    imgSort.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_store_bottom));
                    includeCondition.setVisibility(View.VISIBLE);
                    relBusiness.setVisibility(View.VISIBLE);
                    rvSort.setVisibility(View.GONE);
                    relSelect.setVisibility(View.GONE);
                }
                break;
            case R.id.ll_select:
                if(includeCondition.getVisibility() == View.VISIBLE){
                    includeCondition.setVisibility(View.GONE);
                }else {
                    tvBusiness.setTextColor(ContextCompat.getColor(this, R.color.color_666666));
                    imgBusiness.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_store_bottom));
                    tvSelect.setTextColor(ContextCompat.getColor(this, R.color.color_f1404b));
                    imgSelect.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_store_top_red));
                    tvSort.setTextColor(ContextCompat.getColor(this, R.color.color_666666));
                    imgSort.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_store_bottom));
                    includeCondition.setVisibility(View.VISIBLE);
                    relBusiness.setVisibility(View.GONE);
                    rvSort.setVisibility(View.GONE);
                    relSelect.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.ll_sort:
                if(includeCondition.getVisibility() == View.VISIBLE){
                    includeCondition.setVisibility(View.GONE);
                }else {
                    tvBusiness.setTextColor(ContextCompat.getColor(this, R.color.color_666666));
                    imgBusiness.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_store_bottom));
                    tvSelect.setTextColor(ContextCompat.getColor(this, R.color.color_666666));
                    imgSelect.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_store_bottom));
                    tvSort.setTextColor(ContextCompat.getColor(this, R.color.color_f1404b));
                    imgSort.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_store_top_red));
                    includeCondition.setVisibility(View.VISIBLE);
                    relBusiness.setVisibility(View.GONE);
                    rvSort.setVisibility(View.VISIBLE);
                    relSelect.setVisibility(View.GONE);
                }
                break;
            case R.id.view_other:
                includeCondition.setVisibility(View.GONE);
                break;
            case R.id.tv_new_store:
                if (!(Boolean) tvNewStore.getTag()) {
                    tvNewStore.setTag(true);
                    tvNewStore.setTextColor(ContextCompat.getColor(this, R.color.color_F1404B));
                    tvNewStore.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_stroke_f1404b_solid_fff8f8_14));
                } else {
                    tvNewStore.setTag(false);
                    tvNewStore.setTextColor(ContextCompat.getColor(this, R.color.color_222222));
                    tvNewStore.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_solid_f9f9f9_12));
                }
                break;
            case R.id.tv_business_ing:
                if (!(Boolean) tvBusinessIng.getTag()) {
                    tvBusinessIng.setTag(true);
                    tvBusinessIng.setTextColor(ContextCompat.getColor(this, R.color.color_F1404B));
                    tvBusinessIng.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_stroke_f1404b_solid_fff8f8_14));
                } else {
                    tvBusinessIng.setTag(false);
                    tvBusinessIng.setTextColor(ContextCompat.getColor(this, R.color.color_222222));
                    tvBusinessIng.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_solid_f9f9f9_12));
                }
                break;
            case R.id.tv_stop:
                if (!(Boolean) tvStop.getTag()) {
                    tvStop.setTag(true);
                    tvStop.setTextColor(ContextCompat.getColor(this, R.color.color_F1404B));
                    tvStop.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_stroke_f1404b_solid_fff8f8_14));
                } else {
                    tvStop.setTag(false);
                    tvStop.setTextColor(ContextCompat.getColor(this, R.color.color_222222));
                    tvStop.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_solid_f9f9f9_12));
                }
                break;
            case R.id.tv_house:
                if (!(Boolean) tvHouse.getTag()) {
                    tvHouse.setTag(true);
                    tvHouse.setTextColor(ContextCompat.getColor(this, R.color.color_F1404B));
                    tvHouse.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_stroke_f1404b_solid_fff8f8_14));
                } else {
                    tvHouse.setTag(false);
                    tvHouse.setTextColor(ContextCompat.getColor(this, R.color.color_222222));
                    tvHouse.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_solid_f9f9f9_12));
                }
                break;
            case R.id.tv_reset:
                tvNewStore.setTag(false);
                tvBusinessIng.setTag(false);
                tvStop.setTag(false);
                tvHouse.setTag(false);
                tvNewStore.setTextColor(ContextCompat.getColor(this, R.color.color_222222));
                tvNewStore.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_solid_f9f9f9_12));
                tvBusinessIng.setTextColor(ContextCompat.getColor(this, R.color.color_222222));
                tvBusinessIng.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_solid_f9f9f9_12));
                tvHouse.setTextColor(ContextCompat.getColor(this, R.color.color_222222));
                tvHouse.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_solid_f9f9f9_12));
                tvStop.setTextColor(ContextCompat.getColor(this, R.color.color_222222));
                tvStop.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_solid_f9f9f9_12));
                selectConditionAdapter.initSelectCondition();
                break;
            case R.id.tv_finish:
                loadData(true);
                includeCondition.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }


    private String getFilterKeyCodes() {
        StringBuilder stringBuilder = new StringBuilder();
        if ((Boolean) tvNewStore.getTag()) {
            stringBuilder.append("1,");
        }
        if ((Boolean) tvBusinessIng.getTag()) {
            stringBuilder.append("2,");
        }
        if ((Boolean) tvStop.getTag()) {
            stringBuilder.append("3,");
        }
        if ((Boolean) tvHouse.getTag()) {
            stringBuilder.append("4,");
        }
        if (stringBuilder.length() > 1) {
            return stringBuilder.substring(0, stringBuilder.length() - 1);
        }
        return "";
    }

    private void loadData(boolean isRefresh) {
        this.isRefresh = isRefresh;
        if (isRefresh) {
            pageNo = 1;
        }
        map.clear();
        map.put(HttpConstant.PAGE_NO, pageNo);
        map.put(HttpConstant.PAGE_SIZE, HttpConstant.PAGE_SIZE_NUMBER);
        map.put(HttpConstant.LATITUDE, MyApp.getApp().getLatitude());
        map.put(HttpConstant.LONGITUDE, MyApp.getApp().getLongitude());
        map.put(HttpConstant.ORDER_TYPE_CODE, orderTypeCode);
        if (businessDistrictAdapter != null && businessTypeList.size() > 1) {
            map.put(HttpConstant.BUSINESS_DISTRICTID, businessTypeList.get(businessDistrictAdapter.getSelectPos()).getCode());
        }
        if (nearByAdapter != null && nearbyList.size() > 0) {
            map.put(HttpConstant.DISTANCE, nearbyList.get(nearByAdapter.getSelectPos()).getCode());
        }
        if (!TextUtils.isEmpty(getFilterKeyCodes())) {
            map.put(HttpConstant.FILTER_KEY_CODES, getFilterKeyCodes());
        }
        if (selectConditionAdapter != null) {
            map.put(HttpConstant.FILTER_LABELS, selectConditionAdapter.getSelectCondition());
        }
        presenter.getFindStoreListCondition(map);
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


    @Override
    public void getFindStoreBannerSuccess(MdlBaseHttpResp<FindStoreBannerBean> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && resp.getData().getData() != null) {
            images = resp.getData().getData();
            initBanner();
        }
    }


    @Override
    public void getFindStoreConditionSuccess(MdlBaseHttpResp<FindStoreConditionBean> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && resp.getData().getData() != null) {
            rvSelect.setLayoutManager(new LinearLayoutManager(this));
            selectConditionAdapter = new SelectConditionAdapter(this, resp.getData().getData());
            rvSelect.setAdapter(selectConditionAdapter);
        }
    }


    @Override
    public void getFindStoreListConditionSuccess(MdlBaseHttpResp<FindStorePageBean> resp) {
        rvStore.refreshComplete();
        rvStore.loadMoreComplete();
        if (resp.getStatus() == HttpConstant.R_HTTP_OK) {
            pageNo = resp.getData().getData().getPageNo();
            int totalCount = resp.getData().getData().getTotalCount();
            int a = totalCount % HttpConstant.PAGE_SIZE_NUMBER;
            if (a == 0) {
                pageCount = totalCount / HttpConstant.PAGE_SIZE_NUMBER;
            } else {
                pageCount = (totalCount / HttpConstant.PAGE_SIZE_NUMBER) + 1;
            }
            List<FindStoreBean> list = resp.getData().getData().getResult();
            if (list != null && list.size() > 0) {
                if (isRefresh) {
                    storeList.clear();
                }
                storeList.addAll(list);
                findStoreAdapter.setData(storeList);
                hideEmpty();
            } else if (isRefresh) {
                // 无数据
                showEmpty(R.id.rel_empty, 0, null);
            }
        }
    }


    @Override
    public void getFindStoreBusinessSuccess(MdlBaseHttpResp<BusinessDistrictBean> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && resp.getData().getData() != null) {
            businessTypeList = resp.getData().getData();
            businessTypeList.add(0, new BusinessDistrictBean.DataBean(0, "推荐商圈"));
            rvBusiness.setLayoutManager(new LinearLayoutManager(this));
            businessDistrictAdapter = new BusinessDistrictAdapter(this, businessTypeList);
            businessDistrictAdapter.setListener((bean, pos) -> {
                tvBusiness.setText(bean.getShortName());
//                loadData(true);
            });
            rvBusiness.setAdapter(businessDistrictAdapter);
        }
    }
}

package com.jsjlzj.wayne.ui.store.find;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.find.FindStoreAdapter;
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
    @BindView(R.id.view_other)
    View viewOther;

    private FindStoreAdapter findStoreAdapter;
    private int pageNo=1;
    private int pageCount;
    private boolean isRefresh;
    private Map<String,String> map = new HashMap<>();
    private List<String> images = new ArrayList<>();

    public static void go2this(Context context){
        context.startActivity(new Intent(context,FindStoreActivity.class));
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
        initRightTitle(getResources().getString(R.string.store),R.drawable.ic_search);
        llBusiness.setOnClickListener(clickListener);
        llSelect.setOnClickListener(clickListener);
        llSort.setOnClickListener(clickListener);
        viewOther.setOnClickListener(clickListener);
        rvStore.setPullRefreshEnabled(false);
        rvStore.setLoadingMoreEnabled(true);
        rvStore.setLoadingListener(this);
        findStoreAdapter = new FindStoreAdapter(this, new ArrayList<>());
        rvStore.setLayoutManager(new LinearLayoutManager(this));
        rvStore.setAdapter(findStoreAdapter);
        initBanner();
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
        switch (view.getId()){
            case R.id.ll_business:
                tvBusiness.setTextColor(ContextCompat.getColor(this,R.color.color_f1404b));
                imgBusiness.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_find_store_top_red));
                tvSelect.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                imgSelect.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_find_store_bottom));
                tvSort.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                imgSort.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_find_store_bottom));
                includeCondition.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_select:
                tvBusiness.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                imgBusiness.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_find_store_bottom));
                tvSelect.setTextColor(ContextCompat.getColor(this,R.color.color_f1404b));
                imgSelect.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_find_store_top_red));
                tvSort.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                imgSort.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_find_store_bottom));
                includeCondition.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_sort:
                tvBusiness.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                imgBusiness.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_find_store_bottom));
                tvSelect.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                imgSelect.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_find_store_bottom));
                tvSort.setTextColor(ContextCompat.getColor(this,R.color.color_f1404b));
                imgSort.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_find_store_top_red));
                includeCondition.setVisibility(View.VISIBLE);
                break;
            case R.id.view_other:
                includeCondition.setVisibility(View.GONE);
                break;
            default:break;
        }
    }

    private void loadData(boolean isRefresh) {
        this.isRefresh = isRefresh;
        if (isRefresh) {
            pageNo = 1;
        }
//        map.clear();
//        map.put(HttpConstant.PAGE_NO, pageNo);
//        map.put(HttpConstant.PAGE_SIZE, HttpConstant.PAGE_SIZE_NUMBER);
//        map.put(HttpConstant.CATEGORY_ID, typeId);
//        presenter.getInformationList(map);
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
}

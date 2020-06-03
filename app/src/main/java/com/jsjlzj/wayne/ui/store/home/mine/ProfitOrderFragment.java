package com.jsjlzj.wayne.ui.store.home.mine;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.mine.ProfitOrderAdapter;
import com.jsjlzj.wayne.adapter.recycler.shopping.MineOrderAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.DataBean;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.shopping.MineOrderPageBean;
import com.jsjlzj.wayne.entity.shopping.ProfitOrderPageBean;
import com.jsjlzj.wayne.entity.shopping.ShoppingCarBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.ui.store.shopping.ConfirmOrderActivity;
import com.jsjlzj.wayne.ui.store.shopping.LogisticsActivity;
import com.jsjlzj.wayne.ui.store.shopping.OrderDetailActivity;
import com.jsjlzj.wayne.ui.store.shopping.PaymentActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.CustomXRecyclerView;
import com.netease.nim.uikit.common.ToastHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
  *  
  * @ClassName:      收益订单
  * @Description:    java类作用描述
  * @Author:         曾海强
  * @CreateDate:      
  */
public class ProfitOrderFragment extends MVPBaseFragment<HomeView, HomePresenter> implements HomeView, XRecyclerView.LoadingListener {

    @BindView(R.id.rv_data)
    CustomXRecyclerView rvData;
    /**
     * 1:课程收益明细 2:商城收益明细
     */
    private int type = 2;
    private Map<Object,Object> map = new HashMap<>();
    private int pageNo = 1;
    private int pageCount;
    private boolean isRefresh;
    private List<ProfitOrderPageBean.DataBean.ResultBean> orderList = new ArrayList<>();
    private ProfitOrderAdapter adapter;

    public static ProfitOrderFragment getInstance(int type){
        ProfitOrderFragment fragment = new ProfitOrderFragment(type);
        return fragment;
    }

    public ProfitOrderFragment(int type) {
        this.type = type;
    }

    public ProfitOrderFragment() {}


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_profit_order;
    }

    @Override
    protected void initViewAndControl(View view) {
        rvData.setLoadingListener(this);
        rvData.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ProfitOrderAdapter(getActivity(),new ArrayList<>(),type);
        rvData.setAdapter(adapter);
        loadData(true);
    }



    @Override
    protected void fragment2Front() {
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }


    public void loadData(boolean isRefresh) {
        this.isRefresh = isRefresh;
        if (isRefresh) {
            pageNo = 1;
        }
        map.clear();
        map.put(HttpConstant.PAGE_NO, pageNo);
        map.put(HttpConstant.PAGE_SIZE, HttpConstant.PAGE_SIZE_NUMBER);
        map.put("type", type);
        presenter.getProfitOrderList(map);
    }


    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public void onLoadMore() {
        if (pageNo < pageCount - 1) {
            pageNo++;
            loadData(false);
        } else {
            ToastHelper.showToast(getContext(), getString(R.string.has_no_more_data));
        }
    }

    @Override
    public void getProfitOrderListSuccess(MdlBaseHttpResp<ProfitOrderPageBean> resp) {
        rvData.refreshComplete();
        rvData.loadMoreComplete();
        if (resp.getStatus() == HttpConstant.R_HTTP_OK) {
            pageNo = resp.getData().getData().getPageNo();
            int totalCount = resp.getData().getData().getTotalCount();
            int a = totalCount % HttpConstant.PAGE_SIZE_NUMBER;
            if (a == 0) {
                pageCount = totalCount / HttpConstant.PAGE_SIZE_NUMBER;
            } else {
                pageCount = (totalCount / HttpConstant.PAGE_SIZE_NUMBER) + 1;
            }
            List<ProfitOrderPageBean.DataBean.ResultBean> list = resp.getData().getData().getResult();
            if (list != null && list.size() > 0) {
                if (isRefresh) {
                    orderList.clear();
                }
                orderList.addAll(list);
                adapter.setData(orderList);
                hideEmpty();
            } else if (isRefresh) {
                // 无数据
                showEmpty(R.id.rel_empty, 0, null);
            }
        }
    }

}

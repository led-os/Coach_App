package com.jsjlzj.wayne.ui.store.home.mine;


import android.app.Activity;
import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.shopping.MineOrderAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.DataBean;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.shopping.MineOrderPageBean;
import com.jsjlzj.wayne.entity.shopping.ShoppingCarBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.ui.store.shopping.ConfirmOrderActivity;
import com.jsjlzj.wayne.ui.store.shopping.OrderDetailActivity;
import com.jsjlzj.wayne.ui.store.shopping.PaymentActivity;
import com.jsjlzj.wayne.ui.store.shopping.ScanLogisticsActivity;
import com.jsjlzj.wayne.ui.store.shopping.ShoppingEvaluateActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.CustomXRecyclerView;
import com.jsjlzj.wayne.widgets.TimeCounter;
import com.netease.nim.uikit.common.ToastHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @ClassName: MineOrderFragment
 * @Description: 我的订单fragmetn
 * @Author: 曾海强
 * @CreateDate:
 */
public class MineOrderFragment extends MVPBaseFragment<HomeView, HomePresenter> implements HomeView, XRecyclerView.LoadingListener, MineOrderAdapter.OnItemClickListener {

    @BindView(R.id.rv_data)
    CustomXRecyclerView rvData;
    /**
     * -1 :全部  0,待支付；1,待发货；2，待收货；3，待评价；4，售后
     */
    private int type;
    private Map<Object,Object> map = new HashMap<>();
    private int pageNo = 1;
    private int pageCount;
    private boolean isRefresh;
    private List<MineOrderPageBean.DataBean.ResultBean> orderList = new ArrayList<>();
    private MineOrderAdapter adapter;

    public static MineOrderFragment getInstance(int type){
        MineOrderFragment fragment = new MineOrderFragment(type);
        return fragment;
    }

    public MineOrderFragment(int type) {
        this.type = type;
    }

    public MineOrderFragment() {}


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_mine_order;
    }

    @Override
    protected void initViewAndControl(View view) {
        rvData.setLoadingListener(this);
        rvData.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MineOrderAdapter(getActivity(),new ArrayList<>(),type);
        adapter.setListener(this);
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
        if(type != -1){
            map.put("type", type);
        }
        presenter.getOrderList(map);
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
    public void getOrderListSuccess(MdlBaseHttpResp<MineOrderPageBean> resp) {
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
            List<MineOrderPageBean.DataBean.ResultBean> list = resp.getData().getData().getResult();
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


    @Override
    public void onLeftClick(MineOrderPageBean.DataBean.ResultBean bean) {
        switch (bean.getShowStatus()){
            case 3://查看物流
                ScanLogisticsActivity.go2this(getActivity(),bean.getOrderCode());
                break;
            default:break;

        }
    }

    @Override
    public void onRightClick(MineOrderPageBean.DataBean.ResultBean bean) {
        switch (bean.getShowStatus()){
            case 0://支付
                PaymentActivity.go2this(getActivity(),bean.getOrderCode(),String.valueOf(bean.getPayAmount()));
                break;
            case 5://已完成
            case 6://交易关闭
            case 1://已取消  再次购买
                ConfirmOrderActivity.go2this(getActivity(),transShoppingCarList(bean.getList()),null);
//                ShoppingEvaluateActivity.go2this(getActivity(),bean.getList().get(0));
                break;
            case 2://待发货  提醒发货
                LogAndToastUtil.toast("提醒成功,请耐心等待");
                break;
            case 3://确认收货
                presenter.confirmOrder(bean.getOrderCode());
                break;
            case 4://待评价
                break;
            default:break;

        }
    }

    @Override
    public void onItemClick(MineOrderPageBean.DataBean.ResultBean bean) {
        OrderDetailActivity.go2this(getActivity(),bean.getOrderCode(),0,OrderDetailActivity.REQUEST_CODE);
    }

    @Override
    public void getMessageSuccess(MdlBaseHttpResp<DataBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            LogAndToastUtil.toast("收货成功");
            loadData(true);
        }
    }

    private List<ShoppingCarBean.DataBean.ListResultsBean> transShoppingCarList(List<MineOrderPageBean.DataBean.ResultBean.ListBean> list) {
        List<ShoppingCarBean.DataBean.ListResultsBean> shoppingCarList = new ArrayList<>();
        for (int i = 0 ;i < list.size() ; i++){
            ShoppingCarBean.DataBean.ListResultsBean bean = new ShoppingCarBean.DataBean.ListResultsBean();
            MineOrderPageBean.DataBean.ResultBean.ListBean orderBean = list.get(i);
            bean.setBuyNum(orderBean.getProductCount());
            bean.setProductId(orderBean.getOrderProductId());
            bean.setPrice(orderBean.getProductPrice());
            bean.setProductName(orderBean.getName());
            bean.setSpData(orderBean.getProductSpec());
            bean.setProductUrl(orderBean.getProductPic());
            shoppingCarList.add(bean);
        }
        return shoppingCarList;
    }



}

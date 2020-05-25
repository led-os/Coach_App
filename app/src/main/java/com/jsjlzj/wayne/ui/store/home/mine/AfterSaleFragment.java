package com.jsjlzj.wayne.ui.store.home.mine;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.View;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.mine.AfterSaleAdapter;
import com.jsjlzj.wayne.adapter.recycler.shopping.MineOrderAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.DataBean;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.shopping.AfterSalePageBean;
import com.jsjlzj.wayne.entity.shopping.MineOrderPageBean;
import com.jsjlzj.wayne.entity.shopping.ShoppingCarBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.ui.store.shopping.ConfirmOrderActivity;
import com.jsjlzj.wayne.ui.store.shopping.OrderDetailActivity;
import com.jsjlzj.wayne.ui.store.shopping.PaymentActivity;
import com.jsjlzj.wayne.ui.store.shopping.ScanLogisticsActivity;
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
  * @ClassName:      退货或售后
  * @Description:    java类作用描述
  * @Author:         曾海强
  * @CreateDate:
  */
public class AfterSaleFragment extends MVPBaseFragment<HomeView, HomePresenter> implements HomeView, XRecyclerView.LoadingListener, AfterSaleAdapter.OnItemClickListener {

    @BindView(R.id.rv_data)
    CustomXRecyclerView rvData;

    private Map<Object,Object> map = new HashMap<>();
    private int pageNo = 1;
    private int pageCount;
    private boolean isRefresh;
    private List<AfterSalePageBean.DataBean.ResultBean> orderList = new ArrayList<>();
    private AfterSaleAdapter adapter;

    public static AfterSaleFragment getInstance(){
        AfterSaleFragment fragment = new AfterSaleFragment();
        return fragment;
    }


    public AfterSaleFragment() {}


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_mine_order;
    }

    @Override
    protected void initViewAndControl(View view) {
        rvData.setLoadingListener(this);
        rvData.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new AfterSaleAdapter(getActivity(),new ArrayList<>());
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


    private void loadData(boolean isRefresh) {
        this.isRefresh = isRefresh;
        if (isRefresh) {
            pageNo = 1;
        }
        map.clear();
        map.put(HttpConstant.PAGE_NO, pageNo);
        map.put(HttpConstant.PAGE_SIZE, HttpConstant.PAGE_SIZE_NUMBER);
        presenter.getAfterOrderList(map);
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
    public void getAfterOrderListSuccess(MdlBaseHttpResp<AfterSalePageBean> resp) {
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
            List<AfterSalePageBean.DataBean.ResultBean> list = resp.getData().getData().getResult();
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
    public void onLeftClick(AfterSalePageBean.DataBean.ResultBean bean) {
        if(bean.getStatus() == 9){//审核失败--联系客服
            
        }else {//撤销申请
            map.clear();
            map.put("id",bean.getGetMyAftersaleOrderResponseVo().getId());
            presenter.getAfterOrderCancel(map);
        }
    }

    @Override
    public void onRightClick(AfterSalePageBean.DataBean.ResultBean bean) {
        OrderDetailActivity.go2this(getActivity(),bean.getOrderCode());
    }

     @Override
     public void onItemCLick(AfterSalePageBean.DataBean.ResultBean bean) {
         OrderDetailActivity.go2this(getActivity(),bean.getOrderCode());
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

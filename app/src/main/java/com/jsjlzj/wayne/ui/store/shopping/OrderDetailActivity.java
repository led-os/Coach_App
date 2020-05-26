package com.jsjlzj.wayne.ui.store.shopping;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.shopping.OrderDetailAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.shopping.OrderDetailBean;
import com.jsjlzj.wayne.entity.shopping.ShoppingCarBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.DateUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: 订单详情
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate:
 */
public class OrderDetailActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {


    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.btn_title_right)
    ImageView btnTitleRight;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.rel_location)
    RelativeLayout relLocation;
    @BindView(R.id.rv_order)
    RecyclerView rvOrder;
    @BindView(R.id.tv_all_money)
    TextView tvAllMoney;
    @BindView(R.id.tv_coupon)
    TextView tvCoupon;
    @BindView(R.id.ll_coupon)
    LinearLayout llCoupon;
    @BindView(R.id.tv_freight)
    TextView tvFreight;
    @BindView(R.id.ll_freight)
    LinearLayout llFreight;
    @BindView(R.id.tv_actual_money)
    TextView tvActualMoney;
    @BindView(R.id.tv_order_code)
    TextView tvOrderCode;
    @BindView(R.id.tv_water)
    TextView tvWater;
    @BindView(R.id.tv_order_time)
    TextView tvOrderTime;
    @BindView(R.id.tv_pay_time)
    TextView tvPayTime;
    @BindView(R.id.tv_right_click)
    TextView tvRightClick;
    @BindView(R.id.tv_left_click)
    TextView tvLeftClick;
    private String orderCode;

    public static void go2this(Activity activity, String orderCode) {
        activity.startActivity(new Intent(activity, OrderDetailActivity.class).putExtra("orderCode", orderCode));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        btnBack.setOnClickListener(clickListener);
        btnTitleRight.setOnClickListener(clickListener);
        tvRightClick.setOnClickListener(clickListener);
        tvLeftClick.setOnClickListener(clickListener);
        orderCode = getIntent().getStringExtra("orderCode");
        Map<Object,Object> map = new HashMap<>();
        map.put("orderCode",orderCode);
        presenter.getOrderDetail(map);
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_title_right:
                break;
            case R.id.tv_right_click:
                break;
            case R.id.tv_left_click:
                break;
        }
    }


    @Override
    public void getOrderDetailSuccess(MdlBaseHttpResp<OrderDetailBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            if(resp.getData().getData() != null && resp.getData().getData().getOrderList() != null){
                initShowView(resp.getData().getData());
                rvOrder.setLayoutManager(new LinearLayoutManager(this));
                OrderDetailAdapter shoppingCarAdapter = new OrderDetailAdapter(this,resp.getData().getData().getOrderList());
                rvOrder.setAdapter(shoppingCarAdapter);
            }
        }
    }

    private void initShowView(OrderDetailBean.DataBean data) {
        tvName.setText(data.getReceiverName());
        tvPhone.setText(data.getReceiverPhone());
        tvLocation.setText(data.getReceiverAddress());
        tvOrderCode.setText("订单号："+orderCode);
        tvWater.setText("交易流水："+data.getPayCode());
        tvOrderTime.setText("下单时间："+data.getCreateTime());
        tvPayTime.setText("支付时间："+data.getPayTime());
        tvCoupon.setText("-"+getResources().getString(R.string.chinese_money)+data.getTotalDiscountAmount());
    }


//    private void calculateMoney(List<ShoppingCarBean.DataBean.ListResultsBean> selectList) {
//        float totalMontey = 0;
//        if(carAdapter.getSelectList().size() == resultList.size()){
//            isAllSelect = true;
//            imgAllSelect.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cbx_select));
//            tvAllSelect.setText("取消全选");
//        }else {
//            isAllSelect = false;
//            imgAllSelect.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cbx_unselect));
//            tvAllSelect.setText("全选");
//        }
//        //不显示优惠明细了
//        if(selectList != null && selectList.size() > 0 && curConponBean != null){
//            tvCoupon.setVisibility(View.GONE);
//            imgOpen.setVisibility(View.GONE);
//            tvDiscountDetail.setVisibility(View.GONE);
//        }else {
//            tvCoupon.setVisibility(View.GONE);
//            imgOpen.setVisibility(View.GONE);
//            tvDiscountDetail.setVisibility(View.GONE);
//        }
//        for (int i = 0; i < selectList.size(); i++) {
//            ShoppingCarBean.DataBean.ListResultsBean bean = selectList.get(i);
//            if (bean.getBuyNum() > 0) {
//                totalMontey += Float.valueOf(bean.getPrice()) * bean.getBuyNum();
//            }
//        }
//        if(curConponBean != null && tvCoupon.getVisibility() == View.VISIBLE){
//            totalMontey = totalMontey - curConponBean.getAmount();
//        }
//        tvMoney.setText(getResources().getString(R.string.chinese_money) + DateUtil.getTwoDotByFloat(totalMontey));
//    }

}

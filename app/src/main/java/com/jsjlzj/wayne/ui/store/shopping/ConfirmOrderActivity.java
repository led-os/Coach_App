package com.jsjlzj.wayne.ui.store.shopping;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.shopping.ShoppingCarAdapter;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @ClassName: ConfirmOrderActivity
 * @Description: 确认订单
 * @Author: 曾海强
 * @CreateDate: 2020/04/28
 */
public class ConfirmOrderActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    private static final int REQUEST_CODE_SELECT_LOCATION = 10101;

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_location_detail)
    TextView tvLocationDetail;
    @BindView(R.id.rel_location)
    RelativeLayout relLocation;
    @BindView(R.id.rel_location_select)
    RelativeLayout relLocationSelect;
    @BindView(R.id.rv_order)
    RecyclerView rvOrder;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_select_discount)
    TextView tvSelectDiscount;
    @BindView(R.id.tv_freight)
    TextView tvFreight;
    @BindView(R.id.tv_freight_des)
    TextView tvFreightDes;
    @BindView(R.id.tv_all_money)
    TextView tvAllMoney;
    @BindView(R.id.tv_discounted)
    TextView tvDiscounted;
    @BindView(R.id.tv_commit_order)
    TextView tvCommitOrder;


    public static void go2this(Activity activity) {
        activity.startActivity(new Intent(activity, ConfirmOrderActivity.class));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_confirm_order;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initTitle("确定订单");
        relLocationSelect.setVisibility(View.VISIBLE);
        relLocation.setVisibility(View.GONE);
        rvOrder.setLayoutManager(new LinearLayoutManager(this));
        ShoppingCarAdapter adapter = new ShoppingCarAdapter(this,new ArrayList<>());
        rvOrder.setAdapter(adapter);
        rvOrder.setNestedScrollingEnabled(false);
        rvOrder.setHasFixedSize(true);
        relLocationSelect.setOnClickListener(clickListener);
        relLocation.setOnClickListener(clickListener);
        tvCommitOrder.setOnClickListener(clickListener);
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.rel_location:
            case R.id.rel_location_select:
                LocationManagerActivity.go2this(this,REQUEST_CODE_SELECT_LOCATION,1);

                break;
            case R.id.ll_select_discount:
                break;
            case R.id.tv_commit_order:
                PaymentActivity.go2this(this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_SELECT_LOCATION && resultCode == RESULT_OK){
            relLocationSelect.setVisibility(View.GONE);
            relLocation.setVisibility(View.VISIBLE);
        }
    }
}

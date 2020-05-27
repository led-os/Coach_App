package com.jsjlzj.wayne.ui.store.home.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.shopping.MineOrderPageBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.ui.store.shopping.ShoppingEvaluateActivity;

/**
* @description 申请售后
* @date:   2020/05/27
* @author: 曾海强
*/
public class AfterSaleApplyActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    private MineOrderPageBean.DataBean.ResultBean.ListBean bean;

    public static void go2this(Context activity, MineOrderPageBean.DataBean.ResultBean.ListBean productId) {
        activity.startActivity(new Intent(activity, AfterSaleApplyActivity.class).putExtra("product_data", productId));
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_after_sale_apply;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initTitle("申请售后");
        bean = (MineOrderPageBean.DataBean.ResultBean.ListBean) getIntent().getSerializableExtra("product_data");
    }

}

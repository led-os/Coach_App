package com.jsjlzj.wayne.ui.store.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;

public class OrderDetailActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {



    public static void go2this(Activity activity,String orderCode){
        activity.startActivity(new Intent(activity,OrderDetailActivity.class).putExtra("orderCode",orderCode));
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

    }

}

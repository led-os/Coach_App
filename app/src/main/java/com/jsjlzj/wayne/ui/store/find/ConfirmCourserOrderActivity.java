package com.jsjlzj.wayne.ui.store.find;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.shopping.ShoppingCarAdapter;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;

import java.util.ArrayList;

import butterknife.BindView;

public class ConfirmCourserOrderActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    @BindView(R.id.rv_order)
    RecyclerView rvOrder;
    @BindView(R.id.tv_currency)
    TextView tvCurrency;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_all_money)
    TextView tvAllMoney;
    @BindView(R.id.tv_commit_order)
    TextView tvCommitOrder;

    public static void go2this(Activity activity){
        activity.startActivity(new Intent(activity,ConfirmCourserOrderActivity.class));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_confirm_courser_order;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initTitle("确认订单");
        rvOrder.setLayoutManager(new LinearLayoutManager(this));
        ShoppingCarAdapter adapter = new ShoppingCarAdapter(this,new ArrayList<>());
        rvOrder.setAdapter(adapter);
        rvOrder.setNestedScrollingEnabled(false);
        rvOrder.setHasFixedSize(true);
        tvCommitOrder.setOnClickListener(clickListener);
    }


    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.tv_commit_order:
                PayPasswordFragment.showFragment(getSupportFragmentManager(), 5.00f, 1, null);
                break;
            default:
                break;
        }
    }
}

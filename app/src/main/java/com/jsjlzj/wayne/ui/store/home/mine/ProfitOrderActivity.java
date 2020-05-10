package com.jsjlzj.wayne.ui.store.home.mine;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.widgets.MyViewPager;

import butterknife.BindView;

/**
 * @ClassName: ProfitOrderActivity
 * @Description: 收益订单
 * @Author: 曾海强
 * @CreateDate: 2020/05/07
 */
public class ProfitOrderActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.btn_title_right)
    ImageView btnTitleRight;
    @BindView(R.id.img_info)
    ImageView imgInfo;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.my_view_pager)
    MyViewPager myViewPager;

    public static void go2this(Activity activity) {
        activity.startActivity(new Intent(activity, ProfitOrderActivity.class));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_profit_order;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {

        btnBack.setOnClickListener(clickListener);
        imgInfo.setOnClickListener(clickListener);
        btnTitleRight.setOnClickListener(clickListener);
    }


    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.img_info:
                break;
            case R.id.btn_title_right:
                break;
        }
    }
}

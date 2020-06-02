package com.jsjlzj.wayne.ui.store.home.mine;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;

import butterknife.BindView;

/**
  *
  * @ClassName:      LeaderDesActivity
  * @Description:    申请成为团长界面  介绍页
  * @Author:         曾海强
  * @CreateDate:
  */
public class LeaderDesActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    @BindView(R.id.tv_commit)
    TextView tvCommit;

    public static void go2this(Activity activity){
        activity.startActivity(new Intent(activity,LeaderDesActivity.class));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_leader_des;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initRightTitle("申请成为团长",R.drawable.ic_profit_order_info);
        mRightBtn.setOnClickListener(view ->
                WebViewContainerActivity.go2this(LeaderDesActivity.this,"收益说明", HttpConstant.WEB_URL_BENEFIT_INFO, WebViewContainerFragment.TYPE_BANNER_LINK_URL
                ));
        tvCommit.setOnClickListener(view -> {
            ApplyLeaderActivity.go2this(this);
        });
    }


}

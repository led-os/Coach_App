package com.jsjlzj.wayne.ui.store.home.mine;

import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
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
        initTitle("申请成为团长");
        tvCommit.setOnClickListener(view -> {
            ApplyLeaderActivity.go2this(this);
        });
    }


}

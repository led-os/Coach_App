package com.jsjlzj.wayne.ui.store.shopping;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;

import butterknife.BindView;

/**
 * @ClassName: TimeSecondActivity
 * @Description: 显示秒杀界面
 * @Author: 曾海强
 * @CreateDate: 2020/04/25
 */
public class TimeSecondActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    @BindView(R.id.tv_robing)
    TextView tvRobing;
    @BindView(R.id.view_robing)
    View viewRobing;
    @BindView(R.id.tv_will_start)
    TextView tvWillStart;
    @BindView(R.id.view_will_start)
    View viewWillStart;


    private TimeSecondFragment timeSecondFragment1;//限时秒杀
    private TimeSecondFragment timeSecondFragment2;//即将开始

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, TimeSecondActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_time_second;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initTitle("限时秒杀");
        tvRobing.setOnClickListener(clickListener);
        tvWillStart.setOnClickListener(clickListener);
        showRobingFragment();
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()){
            case R.id.tv_robing:
                tvRobing.setTextColor(ContextCompat.getColor(this,R.color.color_f1404b));
                viewRobing.setVisibility(View.VISIBLE);
                tvWillStart.setTextColor(ContextCompat.getColor(this,R.color.color_777777));
                viewWillStart.setVisibility(View.GONE);
                showRobingFragment();
                break;
            case R.id.tv_will_start:
                tvWillStart.setTextColor(ContextCompat.getColor(this,R.color.color_f1404b));
                viewWillStart.setVisibility(View.VISIBLE);
                tvRobing.setTextColor(ContextCompat.getColor(this,R.color.color_777777));
                viewRobing.setVisibility(View.GONE);
                showWillStartFragment();
                break;
            default :
                break;
        }
    }

    public void showRobingFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(timeSecondFragment2 != null){
            transaction.hide(timeSecondFragment2);
        }
        if(timeSecondFragment1 == null){
            timeSecondFragment1 = TimeSecondFragment.getInstance(0);
            transaction.add(R.id.frame_layout,timeSecondFragment1);
        }else {
            transaction.show(timeSecondFragment1);
        }
        transaction.commitAllowingStateLoss();
    }

    public void showWillStartFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(timeSecondFragment1 != null){
            transaction.hide(timeSecondFragment1);
        }
        if(timeSecondFragment2 == null){
            timeSecondFragment2 = TimeSecondFragment.getInstance(1);
            transaction.add(R.id.frame_layout,timeSecondFragment2);
        }else {
            transaction.show(timeSecondFragment2);
        }
        transaction.commitAllowingStateLoss();
    }
}

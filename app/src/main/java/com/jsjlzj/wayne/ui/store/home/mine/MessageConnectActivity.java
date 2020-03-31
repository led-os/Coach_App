package com.jsjlzj.wayne.ui.store.home.mine;

import android.app.Activity;
import android.content.Intent;

import androidx.fragment.app.FragmentTransaction;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.ui.yunxin.fragment.MySessionListFragment;

public class MessageConnectActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {


    public static void go2this(Activity activity){
        Intent intent = new Intent(activity,MessageConnectActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_message_connect;
    }

    @Override
    protected void initViewAndControl() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        MySessionListFragment fragment = (MySessionListFragment) MySessionListFragment.getInstance(true);
        fragmentTransaction.add(R.id.frame_layout_message, fragment);
        fragmentTransaction.commit();

    }


    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }
}

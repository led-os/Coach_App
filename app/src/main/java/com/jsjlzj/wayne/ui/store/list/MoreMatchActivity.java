package com.jsjlzj.wayne.ui.store.list;

import android.app.Activity;
import android.content.Intent;

import androidx.fragment.app.FragmentTransaction;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.ui.store.home.MatchItemFragment;

 /**
  *
  * @ClassName:      MoreMatchActivity
  * @Description:    全部赛事
  * @Author:         曾海强
  * @CreateDate:
  */
public class MoreMatchActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    public static void go2this(Activity activity){
        Intent intent = new Intent(activity,MoreMatchActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_more_match;
    }

    @Override
    protected void initViewAndControl() {
        initTitle("全部赛事");
        showMatchFragment();
    }


    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    private void showMatchFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        MatchItemFragment matchItemFragment1 = MatchItemFragment.getInstance(0);
        fragmentTransaction.add(R.id.fragment, matchItemFragment1);
        fragmentTransaction.commit();
    }
}

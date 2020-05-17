package com.jsjlzj.wayne.ui.store.home.mine;

import android.app.Activity;
import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.TabLayoutUtils;
import com.jsjlzj.wayne.widgets.MyViewPager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
  *
  * @ClassName:      MineOrderActivity
  * @Description:    我的订单界面
  * @Author:         曾海强
  * @CreateDate:     2020/05/17
  */
public class MineOrderActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

     @BindView(R.id.tab_layout)
     TabLayout tabLayout;
     @BindView(R.id.my_view_pager)
     MyViewPager myViewPager;
     private String[] mTitles = new String[3];
     private List<MVPBaseFragment> fragments = new ArrayList<>();
     private int showPosition;

     public static void go2this(Activity activity,int showPosition){
         activity.startActivity(new Intent(activity,MineOrderActivity.class)
                .putExtra("showPosition",showPosition));
     }


     @Override
    protected int getLayoutResId() {
        return R.layout.activity_mine_order;
    }

    @Override
    protected HomePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViewAndControl() {
        initTitle("我的订单");
        showPosition = getIntent().getIntExtra("showPosition",0);
        initViewPager();
    }


    private void initViewPager() {
        mTitles = getResources().getStringArray(R.array.order_title_list);
        for (int i = 0; i < mTitles.length; i++) {
            MineOrderFragment mineOrderFragment = MineOrderFragment.getInstance(i-1);
            fragments.add(mineOrderFragment);
        }
        myViewPager.setSlide(true);
        myViewPager.setOffscreenPageLimit(mTitles.length - 1);
        myViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(), 0) {

            @Override
            public int getCount() {
                return fragments.size();
            }

            @NotNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

        });
        myViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                TabLayoutUtils.setTabStyle2(tabLayout, mTitles, position, R.layout.item_tablayout_2, MineOrderActivity.this);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        tabLayout.setupWithViewPager(myViewPager);
        TabLayoutUtils.setTabStyle2(tabLayout, mTitles, showPosition, R.layout.item_tablayout_2, MineOrderActivity.this);
        myViewPager.setCurrentItem(showPosition);
    }


}

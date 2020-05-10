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
 * @ClassName: CouponActivity
 * @Description: 优惠券界面
 * @Author: 曾海强
 * @CreateDate: 2020/05/08
 */
public class CouponActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {


    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.my_view_pager)
    MyViewPager myViewPager;
    private String[] mTitles = new String[3];
    private List<MVPBaseFragment> fragments = new ArrayList<>();

    public static void go2this(Activity activity){
        activity.startActivity(new Intent(activity,CouponActivity.class));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_coupon;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initTitle("优惠券");
        initViewPager();
    }


    private void initViewPager() {
        mTitles = getResources().getStringArray(R.array.coupon_title_list);
        for (int i = 0; i < mTitles.length; i++) {
            CouponFragment courserListFragment = CouponFragment.getInstance(i);
            fragments.add(courserListFragment);
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
                TabLayoutUtils.setTabStyle2(tabLayout, mTitles, position, R.layout.item_tablayout_2, CouponActivity.this);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        tabLayout.setupWithViewPager(myViewPager);
        TabLayoutUtils.setTabStyle2(tabLayout, mTitles, tabLayout.getSelectedTabPosition(), R.layout.item_tablayout_2, CouponActivity.this);
    }

}

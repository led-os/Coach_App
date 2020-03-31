package com.jsjlzj.wayne.ui.publicac.mine;

import android.app.Activity;
import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.ui.store.list.MoreDataFragment;
import com.jsjlzj.wayne.utils.TabLayoutUtils;
import com.jsjlzj.wayne.widgets.MyViewPager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @ClassName: MineFavoriteActivity
 * @Description: 我的收藏
 * @Author: 曾海强
 * @CreateDate:
 */
public class MineFavoriteActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {


    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    MyViewPager myViewPager;
    private MoreDataFragment dynamicFragment;
    private MoreDataFragment videoFragment;
    private MoreDataFragment contentFragment;
//    private MoreDataFragment productorFragment;
    private String[] mTitles = new String[3];
    private List<MVPBaseFragment> fragments = new ArrayList<>();


    public static void go2this(Activity context) {
        Intent intent = new Intent(context, MineFavoriteActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_mine_favorite;
    }

    @Override
    protected void initViewAndControl() {
        initTitle("我的收藏");
        initViewPager();
    }

    private void initViewPager() {
        mTitles = getResources().getStringArray(R.array.favorite_title_list);
        dynamicFragment = MoreDataFragment.getInstance(0);
        videoFragment = MoreDataFragment.getInstance(1);
        contentFragment = MoreDataFragment.getInstance(2);
//        productorFragment = AmoyListFragment.getInstance(5,null);
        fragments.add(dynamicFragment);
        fragments.add(videoFragment);
        fragments.add(contentFragment);
//        fragments.add(productorFragment);

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
                TabLayoutUtils.setSearchTabStyle(tabLayout, mTitles, position, R.layout.item_tablayout, MineFavoriteActivity.this);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        tabLayout.setupWithViewPager(myViewPager);
        TabLayoutUtils.setSearchTabStyle(tabLayout, mTitles, tabLayout.getSelectedTabPosition(), R.layout.item_tablayout, MineFavoriteActivity.this);
    }


    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

}

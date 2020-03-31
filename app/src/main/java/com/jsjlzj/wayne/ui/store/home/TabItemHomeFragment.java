package com.jsjlzj.wayne.ui.store.home;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.ui.publicac.SearchActivity;
import com.jsjlzj.wayne.utils.TabLayoutUtils;
import com.jsjlzj.wayne.widgets.MyViewPager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @description 首页
 * @date: 2019/12/30
 * @author: 曾海强
 */
public class TabItemHomeFragment extends MVPBaseFragment<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView {

    @BindView(R.id.img_search)
    ImageView imgSearch;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.my_view_pager)
    MyViewPager myViewPager;


    private String[] mTitles = new String[6];
    private List<MVPBaseFragment> fragments = new ArrayList<>();

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, TabItemHomeFragment.class);
        context.startActivity(intent);
    }

    public static Fragment getInstance() {
        TabItemHomeFragment fragment = new TabItemHomeFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_tab_item_home;
    }


    @Override
    protected void initViewAndControl(View view) {
        initViewPager();
        imgSearch.setOnClickListener(clickListener);
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.img_search:
                SearchActivity.go2this(getActivity());
                break;
            default:
                break;
        }
    }

    private void initViewPager() {
        mTitles = getResources().getStringArray(R.array.home_title_list);
        for (int i = 0; i < mTitles.length; i++) {
            switch (i) {
                case 1:
                    AmoySchoolFragment amoySchoolFragment = AmoySchoolFragment.getInstance();
                    fragments.add(amoySchoolFragment);
                    break;
                case 2:
                    MatchFragment matchFragment = MatchFragment.getInstance();
                    fragments.add(matchFragment);
                    break;
                case 3:
                    DriedFoodFragment driedFoodFragment = DriedFoodFragment.getInstance();
                    fragments.add(driedFoodFragment);
                    break;
                case 4:
                    InformationFragment informationFragment = InformationFragment.getInstance();
                    fragments.add(informationFragment);
                    break;
//                case 5:
//                    ProductFragment productFragment = ProductFragment.getInstance();
//                    fragments.add(productFragment);
//                    break;
                default:
                    RecommendFragment informationListFragment1 = RecommendFragment.getInstance(myViewPager);
                    fragments.add(informationListFragment1);
                    break;
            }
        }
        myViewPager.setSlide(true);
        myViewPager.setOffscreenPageLimit(mTitles.length - 1);
        myViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager(), 0) {

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
                TabLayoutUtils.setTabStyle(tabLayout, mTitles, position, R.layout.item_tablayout, getActivity());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        tabLayout.setupWithViewPager(myViewPager);
        TabLayoutUtils.setTabStyle(tabLayout, mTitles, tabLayout.getSelectedTabPosition(), R.layout.item_tablayout, getActivity());
    }


    @Override
    protected void fragment2Front() {
    }


    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
    }

}

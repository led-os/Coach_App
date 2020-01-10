package com.jsjlzj.wayne.ui.store.home;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.View;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;

/**
* @description 首页
* @date: 2019/12/30
* @author: 曾海强
*/
public class TabItemHomeFragment extends MVPBaseFragment<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView {

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
    }

    private void initViewPager() {
//        mTitles = getResources().getStringArray(R.array.negative_title);
//        categerys = getResources().getStringArray(R.array.negative_categery);
//        for (int i = 0; i < mTitles.length; i++) {
//            InformationListFragment informationListFragment = InformationListFragment.getInstance("3", categerys[i],100+i);
//            fragments.add(informationListFragment);
//        }
//        viewPager.setNoScroll(false);
//        viewPager.setOffscreenPageLimit(mTitles.length - 1);
//        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager(), 0) {
//
//            @Override
//            public int getCount() {
//                return fragments.size();
//            }
//
//            @NotNull
//            @Override
//            public Fragment getItem(int position) {
//                return fragments.get(position);
//            }
//
//            @Nullable
//            @Override
//            public CharSequence getPageTitle(int position) {
//                return mTitles[position];
//            }
//        });
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                tabTitle.setupWithViewPager(viewPager);
//                setTabStyle(position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//            }
//        });
//        tabTitle.setupWithViewPager(viewPager);
//        setTabStyle(tabTitle.getSelectedTabPosition());
    }


    @Override
    protected void fragment2Front() {
    }


    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
    }

}

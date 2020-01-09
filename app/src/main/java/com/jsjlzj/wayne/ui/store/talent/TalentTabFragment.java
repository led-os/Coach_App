package com.jsjlzj.wayne.ui.store.talent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.MdlPositionType;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.ui.store.talent.menu.TabItemTrainerFragment;
import com.jsjlzj.wayne.ui.store.talent.position.PositionSelectActivity;
import com.jsjlzj.wayne.ui.store.talent.utilac.ScreenActivity;
import com.jsjlzj.wayne.widgets.MyViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 达人 ---首页
 */
public class TalentTabFragment extends MVPBaseFragment<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView {

    public static Fragment getInstance() {
        TalentTabFragment fragment = new TalentTabFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_talent_tab;
    }


    private MyViewPager viewPager;
    private TabLayout tabLayout;
    private FragmentStatePagerAdapter mAdapter;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> strList = new ArrayList<>();



    @Override
    protected void fragment2Front() {

    }

    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
    }

    @Override
    protected void initViewAndControl(View view) {

        findView(R.id.btnAdd).setOnClickListener(clickListener);
        findView(R.id.btnSearch).setOnClickListener(clickListener);
        presenter.getPublishPositionTypeList(null);
        initViewPager();
    }

    private void initViewPager() {
        viewPager = findView(R.id.viewpager);
        tabLayout = findView(R.id.tabLayout);

    }

    private void resetItemTitle() {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(getTabView(i));
            }
        }
    }

    private View getTabView(int position) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.talent_tab_item, null);
        TextView tv = v.findViewById(R.id.tv_tab_item);
        tv.setText(strList.get(position));
        v.setId(position);
        return v;
    }

    private void selectTab(View view) {
        if (view == null) return;
        TextView tv = view.findViewById(R.id.tv_tab_item);
        TextPaint paint = tv.getPaint();
        paint.setFakeBoldText(true);
    }

    private void unselectTab(View view) {
        if (view == null) return;
        TextView tv = view.findViewById(R.id.tv_tab_item);
        TextPaint paint = tv.getPaint();
        paint.setFakeBoldText(false);
    }

    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnAdd:
                    PositionSelectActivity.go2this(getActivity());
                    break;
                case R.id.btnSearch:
                    ScreenActivity.go2this(getActivity(), 1,true);
                    break;
            }
        }
    }

    @Override
    public void showPositionTypePublished(MdlBaseHttpResp<MdlPositionType> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            strList.add("全部");
            fragmentList.add(TabItemTrainerFragment.getInstance());
            for (int i = 0; i < resp.getData().getData().size(); i++) {
                fragmentList.add(TabItemTrainerFragment.getInstance());
                strList.add(resp.getData().getData().get(i).getName());
            }
//            fragmentList.add(TabItemTrainerFragment.getInstance());
//            fragmentList.add(TabItemStoreInfoFragment.getInstance());
            mAdapter = new FragmentStatePagerAdapter(getChildFragmentManager()) {

                @Override
                public int getItemPosition(@NonNull Object object) {
                    return POSITION_NONE;
                }

                @Override
                public int getCount() {
                    return fragmentList.size();
                }


                @Override
                public Fragment getItem(int position) {
                    return fragmentList.get(position);
                }
            };
            viewPager.setAdapter(mAdapter);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            tabLayout.setupWithViewPager(viewPager);
            resetItemTitle();
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    View view = tab.getCustomView();
                    selectTab(view);
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                    View view = tab.getCustomView();
                    unselectTab(view);
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });
            viewPager.setCurrentItem(0);
            viewPager.setSlide(true);


        }
    }
}
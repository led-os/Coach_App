package com.jsjlzj.wayne.ui.store.home;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.ui.publicac.SearchActivity;
import com.jsjlzj.wayne.ui.publicac.ShopPoiActivity;
import com.jsjlzj.wayne.ui.publicac.mine.PublicActivity;
import com.jsjlzj.wayne.ui.store.home.community.CommunityItemFragment;
import com.jsjlzj.wayne.utils.TabLayoutUtils;
import com.jsjlzj.wayne.widgets.MyViewPager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @ClassName: TabItemCommunityFragment
 * @Description: 社区
 * @Author: 曾海强
 * @CreateDate:
 */
public class TabItemCommunityFragment extends MVPBaseFragment<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView {



    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.my_view_pager)
    MyViewPager myViewPager;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.lin_search_bar)
    LinearLayout linSearchBar;
    @BindView(R.id.img_send)
    ImageView imgSend;

    private CommunityItemFragment hotCommunityItemFragment;
    private CommunityItemFragment followCommunityItemFragment;
    private CommunityItemFragment cityCommunityItemFragment;
    private CommunityItemFragment allCommunityItemFragment;


    private String[] mTitles = new String[3];
    private List<MVPBaseFragment> fragments = new ArrayList<>();

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, TabItemCommunityFragment.class);
        context.startActivity(intent);
    }

    public static TabItemCommunityFragment getInstance() {
        TabItemCommunityFragment fragment = new TabItemCommunityFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_tab_item_community;
    }


    @Override
    protected void initViewAndControl(View view) {
        initViewPager();
        if(!TextUtils.isEmpty(MyApp.getApp().getCurrentProvince())){
            tvLocation.setText(MyApp.getApp().getCurrentProvince().replace("市",""));
        }
        tvLocation.setOnClickListener(clickListener);
        linSearchBar.setOnClickListener(clickListener);
        imgSend.setOnClickListener(clickListener);
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.tv_location:
                ShopPoiActivity.go2this(getActivity(),tvLocation.getText().toString(),ShopPoiActivity.REQUEST_CODE);
                break;
            case R.id.lin_search_bar:
                SearchActivity.go2this(getActivity());
                break;
            case R.id.img_send:
                PublicActivity.go2this(getActivity());
                break;
            default:
                break;
        }
    }

    private void initViewPager() {
        mTitles = getResources().getStringArray(R.array.community_title_list);
        for (int i = 0; i < mTitles.length; i++) {
            if(i == 0){
                hotCommunityItemFragment = CommunityItemFragment.getInstance(i,tvLocation.getText().toString());
                fragments.add(hotCommunityItemFragment);
            }else if(i == 1){
                followCommunityItemFragment = CommunityItemFragment.getInstance(i,tvLocation.getText().toString());
                fragments.add(followCommunityItemFragment);
            }else if(i == 2){
//                cityCommunityItemFragment = CommunityItemFragment.getInstance(i,tvLocation.getText().toString());
//                fragments.add(cityCommunityItemFragment);
                allCommunityItemFragment = CommunityItemFragment.getInstance(i,tvLocation.getText().toString());
                fragments.add(allCommunityItemFragment);
            }else if(i == 3){

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
                TabLayoutUtils.setCommunityTabStyle(tabLayout, mTitles, position, R.layout.item_tablayout_community, getActivity());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        tabLayout.setupWithViewPager(myViewPager);
        TabLayoutUtils.setCommunityTabStyle(tabLayout, mTitles, tabLayout.getSelectedTabPosition(), R.layout.item_tablayout_community, getActivity());
    }


    @Override
    protected void fragment2Front() {
    }


    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ShopPoiActivity.REQUEST_CODE && resultCode == Activity.RESULT_OK){
            String cityName = data.getStringExtra(ExtraConstant.EXTRA_NAME);
            tvLocation.setText(cityName);
            hotCommunityItemFragment.setCityName(cityName);
            followCommunityItemFragment.setCityName(cityName);
            allCommunityItemFragment.setCityName(cityName);
        }
    }
}

package com.jsjlzj.wayne.ui.store.home;


import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.find.FindCategoryBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.ui.publicac.SearchActivity;
import com.jsjlzj.wayne.ui.store.find.CourserListFragment;
import com.jsjlzj.wayne.ui.store.find.OptimizationFragment;
import com.jsjlzj.wayne.ui.store.find.PlayHistoryActivity;
import com.jsjlzj.wayne.ui.store.home.mine.MessageConnectActivity;
import com.jsjlzj.wayne.utils.TabLayoutUtils;
import com.jsjlzj.wayne.widgets.MyViewPager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @description 发现
 * @date: 2020/04/15
 * @author: 曾海强
 */
public class TabItemFindFragment extends MVPBaseFragment<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView {


    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.img_history)
    ImageView imgHistory;
    @BindView(R.id.img_info)
    ImageView imgInfo;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.my_view_pager)
    MyViewPager myViewPager;

    public TabItemFindFragment() {
    }

    private String[] mTitles ;
    private List<MVPBaseFragment> fragments = new ArrayList<>();

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, TabItemFindFragment.class);
        context.startActivity(intent);
    }

    public static Fragment getInstance() {
        TabItemFindFragment fragment = new TabItemFindFragment();
        return fragment;
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_tab_item_find;
    }

    @Override
    protected void initViewAndControl(View view) {
        presenter.getCategoryList();
        llSearch.setOnClickListener(clickListener);
        imgHistory.setOnClickListener(clickListener);
        imgInfo.setOnClickListener(clickListener);
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.ll_search://搜索
                SearchActivity.go2this(getActivity());
                break;
            case R.id.img_history://历史记录
                PlayHistoryActivity.go2this(getActivity());
                break;
            case R.id.img_info://我的消息
                MessageConnectActivity.go2this(getActivity());
                break;
            default:
                break;
        }
    }

    @Override
    protected void fragment2Front() {

    }

    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
    }

    private void initViewPager(List<FindCategoryBean.DataBean> categoryList) {
        mTitles = new String[categoryList.size()];
        for (int i = 0; i < categoryList.size(); i++){
            FindCategoryBean.DataBean bean = categoryList.get(i);
            mTitles[i] = bean.getName();
        }
        for (int i = 0; i < mTitles.length; i++) {
            if(i == 0){
                OptimizationFragment optimizationFragment = OptimizationFragment.getInstance(myViewPager);
                fragments.add(optimizationFragment);
            } else {
                CourserListFragment courserListFragment = CourserListFragment.getInstance(categoryList.get(i).getCategoryId());
                fragments.add(courserListFragment);
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
    public void getCategoryListSuccess(MdlBaseHttpResp<FindCategoryBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            if(resp.getData().getData() != null ){
                List<FindCategoryBean.DataBean> categoryList = resp.getData().getData();
                initViewPager(categoryList);
            }
        }
    }
}

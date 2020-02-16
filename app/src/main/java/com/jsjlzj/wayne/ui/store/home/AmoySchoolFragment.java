package com.jsjlzj.wayne.ui.store.home;



import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.google.android.material.tabs.TabLayout;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.ui.store.home.amoy.SignUpActivity;
import com.jsjlzj.wayne.utils.TabLayoutUtils;
import com.jsjlzj.wayne.widgets.LocalImageHolderView;
import com.jsjlzj.wayne.widgets.MyViewPager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
 /**
  *  
  * @ClassName:      淘学
  * @Description:    java类作用描述
  * @Author:         曾海强
  * @CreateDate:      
  */
public class AmoySchoolFragment extends MVPBaseFragment<HomeView, HomePresenter> implements HomeView {


    @BindView(R.id.scroll_banner)
    ConvenientBanner scrollBanner;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.amoy_view_pager)
    MyViewPager amoyViewPager;

    private String[] mTitles = new String[3];
    private List<MVPBaseFragment> fragments = new ArrayList<>();


    public AmoySchoolFragment() {
    }

    public static AmoySchoolFragment getInstance() {
        AmoySchoolFragment fragment = new AmoySchoolFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_amoy_school;
    }

    @Override
    protected void initViewAndControl(View view) {
        initBanner();
        initViewPager();
    }

    @Override
    protected void fragment2Front() {
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }


    private void initViewPager() {
        mTitles = getResources().getStringArray(R.array.amoy_title_list);
        for (int i = 0; i < mTitles.length; i++) {
            AuthenticationFragment authenticationFragment = AuthenticationFragment.getInstance(i);
            fragments.add(authenticationFragment);
        }
        amoyViewPager.setSlide(false);
        amoyViewPager.setOffscreenPageLimit(mTitles.length - 1);
        amoyViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager(), 0) {

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
        amoyViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                TabLayoutUtils.setTabStyle(tabLayout,mTitles,position,R.layout.item_tablayout,getActivity());
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
        tabLayout.setupWithViewPager(amoyViewPager);
        TabLayoutUtils.setTabStyle(tabLayout,mTitles,tabLayout.getSelectedTabPosition(),R.layout.item_tablayout,getActivity());
    }

    private void initBanner() {
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.ic_avatars);
        images.add(R.drawable.ic_avatars);
        images.add(R.drawable.ic_avatars);
        scrollBanner.setPages(
                new CBViewHolderCreator() {
                    @Override
                    public LocalImageHolderView createHolder(View itemView) {
                        return new LocalImageHolderView(itemView);
                    }

                    @Override
                    public int getLayoutId() {
                        return R.layout.item_localimage;
                    }
                }, images)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.bg_circle_98a1ac_4, R.drawable.bg_circle_ffffff_4})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(position -> {
                    SignUpActivity.go2this(getActivity());
                })
                .setCanLoop(true);
    }


    @Override
    public void onResume() {
        super.onResume();
        scrollBanner.startTurning();
    }

    @Override
    public void onPause() {
        super.onPause();
        scrollBanner.stopTurning();
    }

}

package com.jsjlzj.wayne.ui.store.home;


import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.google.android.material.tabs.TabLayout;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.home.AmoySchoolBean;
import com.jsjlzj.wayne.entity.store.home.BannerBean;
import com.jsjlzj.wayne.entity.store.home.CategoryBean;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.TabLayoutUtils;
import com.jsjlzj.wayne.widgets.LocalImageHolderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
  *
  * @ClassName:      赛事
  * @Description:    java类作用描述
  * @Author:         曾海强
  * @CreateDate:
  */
public class MatchFragment extends MVPBaseFragment<HomeView, HomePresenter> implements HomeView {


    @BindView(R.id.scroll_banner)
    ConvenientBanner scrollBanner;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.fragment)
    FrameLayout frameLayout;

    private String[] mTitles = new String[3];
    private List<BannerBean> images = new ArrayList<>();

    private List<CategoryBean> categoryBeans = new ArrayList<>();
    private FragmentTransaction fragmentTransaction;
    private MatchItemFragment matchItemFragment1;
    private MatchItemFragment matchItemFragment2;
    private MatchItemFragment matchItemFragment3;
    private int size;

    public MatchFragment() {
    }

    public static MatchFragment getInstance() {
        MatchFragment fragment = new MatchFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_match;
    }

    @Override
    protected void initViewAndControl(View view) {
        presenter.getMatchData();
    }

    @Override
    protected void fragment2Front() {
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }


    private void hideFragment() {
        if (matchItemFragment1 != null) {
            fragmentTransaction.hide(matchItemFragment1);
        }
        if (matchItemFragment2 != null) {
            fragmentTransaction.hide(matchItemFragment2);
        }
        if (matchItemFragment3 != null) {
            fragmentTransaction.hide(matchItemFragment3);
        }
    }


    private void showMatchFragment1() {
        fragmentTransaction = getChildFragmentManager().beginTransaction();
        hideFragment();
        if (matchItemFragment1 == null) {
            if(categoryBeans != null && categoryBeans.size() >=1 ){
                matchItemFragment1 = MatchItemFragment.getInstance(categoryBeans.get(0).getId());
            }
            fragmentTransaction.add(R.id.fragment, matchItemFragment1);
        } else {
            fragmentTransaction.show(matchItemFragment1);
        }
        fragmentTransaction.commit();
    }

    private void showMatchFragment2() {
        fragmentTransaction = getChildFragmentManager().beginTransaction();
        hideFragment();
        if (matchItemFragment2 == null) {
            if(categoryBeans != null && categoryBeans.size() >=2 ){
                matchItemFragment2 =  MatchItemFragment.getInstance(categoryBeans.get(0).getId());
            }
            fragmentTransaction.add(R.id.fragment, matchItemFragment2);
        } else {
            fragmentTransaction.show(matchItemFragment2);
        }
        fragmentTransaction.commit();
    }

    private void showMatchFragment3() {
        fragmentTransaction = getChildFragmentManager().beginTransaction();
        hideFragment();
        if (matchItemFragment3 == null) {
            if(categoryBeans != null && categoryBeans.size() >=3 ){
                matchItemFragment3 = MatchItemFragment.getInstance(categoryBeans.get(2).getId());
                fragmentTransaction.add(R.id.fragment, matchItemFragment3);
            }
        } else {
            fragmentTransaction.show(matchItemFragment3);
        }
        fragmentTransaction.commit();
    }

    private void initBanner() {
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
                .setPageIndicator(new int[]{R.drawable.bg_circle_ccfffff_6, R.drawable.bg_circle_4f9bfa_6})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(position -> {
                    BannerBean bean = images.get(position);
                    WebViewContainerActivity.go2this(getActivity(),bean.getTitle(),bean.getLink(), WebViewContainerFragment.TYPE_BANNER_LINK_URL,"");
                })
                .setCanLoop(true);
    }


    private void initFragment() {
        size = categoryBeans.size();
        mTitles = new String[size];
        fragmentTransaction = getChildFragmentManager().beginTransaction();
        for (int i = 0; i < size; i++) {
            CategoryBean bean = categoryBeans.get(i);
            mTitles[i] = bean.getName();
            tabLayout.addTab(tabLayout.newTab().setText(bean.getName()));
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                View view = tab.getCustomView();
                String text = tab.getText().toString();
                if(view == null){
                    view = LayoutInflater.from(getActivity()).inflate(R.layout.item_tablayout, null);
                }
                TextView tvTitle = view.findViewById(R.id.tv_title);
                tvTitle.setTextSize(17);
                tvTitle.setTextColor(ContextCompat.getColor(getActivity(),R.color.color_4F9BFA));
                tvTitle.setText(text);
                tab.setCustomView(view);
                switch (pos) {
                    case 0:
                        showMatchFragment1();
                        break;
                    case 1:
                        showMatchFragment2();
                        break;
                    case 2:
                        showMatchFragment3();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                String text = tab.getText().toString();
                if(view == null){
                    view = LayoutInflater.from(getActivity()).inflate(R.layout.item_tablayout, null);
                }
                TextView tvTitle = view.findViewById(R.id.tv_title);
                tvTitle.setText(text);
                tvTitle.setTextSize(15);
                tvTitle.setTextColor(ContextCompat.getColor(getActivity(),R.color.color_999999));
                tab.setCustomView(view);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
        showMatchFragment1();
        TabLayoutUtils.setTabStyle(tabLayout,mTitles,0,R.layout.item_tablayout,getActivity());
    }

    @Override
    public void getMatchSuccess(MdlBaseHttpResp<AmoySchoolBean> resp) {
        AmoySchoolBean.DataBean bean = resp.getData().getData();
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != bean) {
            if (null != bean.getBanner() && bean.getBanner().size() > 0) {
                images = bean.getBanner();
                if (images != null && images.size() > 0) {
                    initBanner();
                }
            }

            if (null != bean.getCategory() && bean.getCategory().size() > 0) {
                categoryBeans = bean.getCategory();
                if (categoryBeans != null && categoryBeans.size() > 0) {
                    initFragment();
                }
            }
        }
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

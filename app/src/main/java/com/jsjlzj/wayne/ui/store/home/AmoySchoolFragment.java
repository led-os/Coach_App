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
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.TabLayoutUtils;
import com.jsjlzj.wayne.widgets.LocalImageHolderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @ClassName: 淘学
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate:
 */
public class AmoySchoolFragment extends MVPBaseFragment<HomeView, HomePresenter> implements HomeView {


    @BindView(R.id.scroll_banner)
    ConvenientBanner scrollBanner;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.fragment)
    FrameLayout frameLayout;

    private String[] mTitles ;


    private List<BannerBean> images = new ArrayList<>();
    private List<CategoryBean> categoryBeans = new ArrayList<>();
    private FragmentTransaction fragmentTransaction;
    private AuthenticationFragment authenticationFragment1;
    private AuthenticationFragment authenticationFragment2;
    private AuthenticationFragment authenticationFragment3;
    private int size;

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
        presenter.getAmoySchoolData();
    }

    @Override
    protected void fragment2Front() {
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }


    private void hideFragment() {
        if (authenticationFragment1 != null) {
            fragmentTransaction.hide(authenticationFragment1);
        }
        if (authenticationFragment2 != null) {
            fragmentTransaction.hide(authenticationFragment2);
        }
        if (authenticationFragment3 != null) {
            fragmentTransaction.hide(authenticationFragment3);
        }
    }


    private void showAuthenFragment1() {
        fragmentTransaction = getChildFragmentManager().beginTransaction();
        hideFragment();
        if (authenticationFragment1 == null) {
            if(categoryBeans != null && categoryBeans.size() >=1 ){
                authenticationFragment1 = new AuthenticationFragment(categoryBeans.get(0).getId());
            }
            fragmentTransaction.add(R.id.fragment, authenticationFragment1);
        } else {
            fragmentTransaction.show(authenticationFragment1);
        }
        fragmentTransaction.commit();
    }

    private void showAuthenFragment2() {
        fragmentTransaction = getChildFragmentManager().beginTransaction();
        hideFragment();
        if (authenticationFragment2 == null) {
            if(categoryBeans != null && categoryBeans.size() >=2 ){
                authenticationFragment2 = new AuthenticationFragment(categoryBeans.get(1).getId());
            }
            fragmentTransaction.add(R.id.fragment, authenticationFragment2);
        } else {
            fragmentTransaction.show(authenticationFragment2);
        }
        fragmentTransaction.commit();
    }

    private void showAuthenFragment3() {
        fragmentTransaction = getChildFragmentManager().beginTransaction();
        hideFragment();
        if (authenticationFragment3 == null) {
            if(categoryBeans != null && categoryBeans.size() >=3 ){
                authenticationFragment3 = new AuthenticationFragment(categoryBeans.get(2).getId());
                fragmentTransaction.add(R.id.fragment, authenticationFragment3);
            }
        } else {
            fragmentTransaction.show(authenticationFragment3);
        }
        fragmentTransaction.commit();
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
                        showAuthenFragment1();
                        break;
                    case 1:
                        showAuthenFragment2();
                        break;
                    case 2:
                        showAuthenFragment3();
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
        showAuthenFragment1();
        TabLayoutUtils.setTabStyle(tabLayout,mTitles,0,R.layout.item_tablayout,getActivity());
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
//                    BannerBean bean = images.get(position);
//                    WebViewContainerActivity.go2this(getActivity(),bean.getTitle(),bean.getLink(), WebViewContainerFragment.TYPE_BANNER_LINK_URL);
                })
                .setCanLoop(true);
    }


    @Override
    public void getAmoySchoolSuccess(MdlBaseHttpResp<AmoySchoolBean> resp) {
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

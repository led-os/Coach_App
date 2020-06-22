package com.jsjlzj.wayne.ui.store.home.mine;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
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
 * @ClassName: ProfitOrderActivity
 * @Description: 收益订单
 * @Author: 曾海强
 * @CreateDate: 2020/05/07
 */
public class ProfitOrderActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.btn_title_right)
    ImageView btnTitleRight;
    @BindView(R.id.img_info)
    ImageView imgInfo;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.my_view_pager)
    MyViewPager myViewPager;
    private String[] mTitles = new String[3];
    private List<MVPBaseFragment> fragments = new ArrayList<>();
    private ProfitOrderFragment shopFragment ;
    private ProfitOrderFragment courseFragment;
    public static void go2this(Activity activity) {
        activity.startActivity(new Intent(activity, ProfitOrderActivity.class));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_profit_order;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        btnBack.setOnClickListener(clickListener);
        imgInfo.setOnClickListener(clickListener);
        btnTitleRight.setOnClickListener(clickListener);
        initViewPager();
    }


    private void initViewPager() {
        mTitles = getResources().getStringArray(R.array.profit_title_list);
        for (int i = 0; i < mTitles.length; i++) {
            if(i == 0){
                shopFragment = ProfitOrderFragment.getInstance(2);
                fragments.add(shopFragment);
            }else {
                courseFragment = ProfitOrderFragment.getInstance(1);
                fragments.add(courseFragment);
            }
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
                TabLayoutUtils.setTabStyle2(tabLayout, mTitles, position, R.layout.item_tablayout_2, ProfitOrderActivity.this);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        tabLayout.setupWithViewPager(myViewPager);
        TabLayoutUtils.setTabStyle2(tabLayout, mTitles, tabLayout.getSelectedTabPosition(), R.layout.item_tablayout_2, ProfitOrderActivity.this);
    }


    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.img_info:
                WebViewContainerActivity.go2this(this,"收益说明", HttpConstant.WEB_URL_BENEFIT_INFO, WebViewContainerFragment.TYPE_PROFIT);
                break;
            case R.id.btn_title_right:
                break;
        }
    }
}

package com.jsjlzj.wayne.ui.store.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.search.SearchAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.find.FindCategoryBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.ui.publicac.SearchActivity;
import com.jsjlzj.wayne.ui.publicac.SearchAllFragment;
import com.jsjlzj.wayne.ui.store.find.CourserListFragment;
import com.jsjlzj.wayne.ui.store.find.OptimizationFragment;
import com.jsjlzj.wayne.ui.store.list.AmoyListFragment;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.TabLayoutUtils;
import com.jsjlzj.wayne.utils.keyboard.KeyboardUtil;
import com.jsjlzj.wayne.widgets.MyViewPager;
import com.jsjlzj.wayne.widgets.SearchBarView;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
  *  
  * @ClassName:      搜索新的课程界面
  * @Description:    java类作用描述
  * @Author:         曾海强
  * @CreateDate:      
  */
public class SearchNewCourserActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

     @BindView(R.id.tab_layout)
     TabLayout tabLayout;
     @BindView(R.id.view_pager)
     MyViewPager myViewPager;

    private String[] mTitles ;
    private List<MVPBaseFragment> fragments = new ArrayList<>();
    private SearchAdapter searchAdapter;
    private Map<Object,Object> map = new HashMap<>();

    private CourserListFragment allCourserListFragment;
    private CourserListFragment zhengjiFragment;
    private CourserListFragment jianzhiFragment;
    private CourserListFragment yunCanFragment;
    private CourserListFragment pulitiFragment;
    private CourserListFragment kangfuFragment;
    private int showPosition;

     public static void go2this(Activity activity){
         activity.startActivity(new Intent(activity,SearchNewCourserActivity.class));
     }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_search_new_couser;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initSearchTitle();
        initSearchBar();
        presenter.getCategoryList();
        mRightTv.setOnClickListener(clickListener);
    }


    private void initSearchBar() {
        mSearchBar.setOnEditTextChangeListener(new SearchBarView.EditTextCallback() {
            @Override
            public void onEditTextChange(String str) {}

            @Override
            public void onEditFinish(String str) {}

            @Override
            public void onClickSoftWare(String str) {
                if(!TextUtils.isEmpty(str)){
                    mSearchBar.getSearchEditText().setText(str);
                    mSearchBar.getSearchEditText().setSelection(str.length());
                    myViewPager.setVisibility(View.VISIBLE);
                    KeyboardUtil.closeKeyboard(tabLayout, SearchNewCourserActivity.this);
                    loadData(str);
                }else {
                    LogAndToastUtil.toast("请输入关键词");
                }

            }
        });
    }

    public void loadData(String searchKey){
        switch (showPosition){
            case 0:
                allCourserListFragment.loadData(true,searchKey);
                break;
            case 1:
                zhengjiFragment.loadData(true,searchKey);
                break;
            case 2:
                jianzhiFragment.loadData(true,searchKey);
                break;
            case 3:
                yunCanFragment.loadData(true,searchKey);
                break;
            case 4:
                pulitiFragment.loadData(true,searchKey);
                break;
            case 5:
                kangfuFragment.loadData(true,searchKey);
                break;
            default :break;

        }

    }
    private void initViewPager(List<FindCategoryBean.DataBean>  categoryList) {
         int size = Math.min(categoryList.size(),6);
        mTitles = new String[size];
        for (int i = 0; i < size; i++){
            FindCategoryBean.DataBean bean = categoryList.get(i);
            if(i == 0){
                mTitles[0] = "全部";
            }else {
                mTitles[i] = bean.getName();
            }
        }
        for (int i = 0; i < mTitles.length; i++) {
            if(i == 0){
                allCourserListFragment = CourserListFragment.getInstance(0);
                fragments.add(allCourserListFragment);
            } else if(i == 1){
                zhengjiFragment = CourserListFragment.getInstance(categoryList.get(i).getCategoryId());
                fragments.add(zhengjiFragment);
            } else if(i == 2){
                jianzhiFragment = CourserListFragment.getInstance(categoryList.get(i).getCategoryId());
                fragments.add(jianzhiFragment);
            } else if(i == 3){
                yunCanFragment = CourserListFragment.getInstance(categoryList.get(i).getCategoryId());
                fragments.add(yunCanFragment);
            }else if(i == 4){
                pulitiFragment = CourserListFragment.getInstance(categoryList.get(i).getCategoryId());
                fragments.add(pulitiFragment);
            }else if(i == 5){
                kangfuFragment = CourserListFragment.getInstance(categoryList.get(i).getCategoryId());
                fragments.add(kangfuFragment);
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
                showPosition = position;
                LogAndToastUtil.log("============="+position);
                TabLayoutUtils.setSearchTabStyle(tabLayout, mTitles, position, R.layout.item_tablayout, SearchNewCourserActivity.this);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        tabLayout.setupWithViewPager(myViewPager);
        TabLayoutUtils.setSearchTabStyle(tabLayout, mTitles, tabLayout.getSelectedTabPosition(), R.layout.item_tablayout, SearchNewCourserActivity.this);
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()){
            case R.id.tv_right_btn:
                finish();
                break;
        }
    }


    @Override
    public void getFindCategoryListSuccess(MdlBaseHttpResp<FindCategoryBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            if(resp.getData().getData() != null ){
                List<FindCategoryBean.DataBean> categoryList = resp.getData().getData();
                initViewPager(categoryList);
            }
        }
    }
}

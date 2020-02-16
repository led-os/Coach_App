package com.jsjlzj.wayne.ui.publicac;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.search.SearchAdapter;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.address.MalAddressProvince;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.relizelogin.LoginActivityPresenter;
import com.jsjlzj.wayne.ui.mvp.relizelogin.LoginActivityView;

import com.jsjlzj.wayne.ui.store.list.AmoyListFragment;
import com.jsjlzj.wayne.utils.TabLayoutUtils;
import com.jsjlzj.wayne.widgets.MyViewPager;
import com.jsjlzj.wayne.widgets.SearchBarView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * @ClassName: SearchActivity
 * @Description: 搜索界面
 * @Author: 曾海强
 * @CreateDate:
 */
public class SearchActivity extends MVPBaseActivity<LoginActivityView, LoginActivityPresenter> implements LoginActivityView, SearchAdapter.OnSearchItemClickListener {


    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.tv_history)
    TextView tvHistory;
    @BindView(R.id.img_delete)
    ImageView imgDelete;
    @BindView(R.id.tfl_hot)
    TagFlowLayout tflHot;
    @BindView(R.id.tfl_history)
    TagFlowLayout tflHistory;
    @BindView(R.id.rel_history)
    RelativeLayout relHistory;
    @BindView(R.id.rv_hot_dynamic)
    RecyclerView rvHotDynamic;
    @BindView(R.id.view_pager)
    MyViewPager myViewPager;

    private String[] mTitles = new String[7];
    private List<MVPBaseFragment> fragments = new ArrayList<>();
    private SearchAdapter searchAdapter;
    private boolean isShowResult = false;

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra("isResult", "");
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_search;
    }


    @Override
    protected void initViewAndControl() {
        initSearchTitle();
        initSearchBar();
        initFagFlowLayout();
        initRecycler();
        initViewPager();
        mRightTv.setOnClickListener(clickListener);
    }

    private void initSearchBar() {
        mSearchBar.setOnEditTextChangeListener(new SearchBarView.EditTextCallback() {
            @Override
            public void onEditTextChange(String str) {

            }

            @Override
            public void onEditFinish(String str) {

            }

            @Override
            public void onClickSoftWare(String str) {
                isShowResult = true;
                mSearchBar.getSearchEditText().setText(str);
                mSearchBar.getSearchEditText().setSelection(str.length());
                relHistory.setVisibility(View.GONE);
                myViewPager.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initRecycler() {
        searchAdapter = new SearchAdapter(this,new ArrayList<>());
        searchAdapter.setListener(this);
        rvHotDynamic.setLayoutManager(new GridLayoutManager(this,2));
        rvHotDynamic.setAdapter(searchAdapter);
    }

    private void initViewPager() {
        mTitles = getResources().getStringArray(R.array.search_title_list);
        for (int i = 0; i < mTitles.length; i++) {
            AmoyListFragment amoySchoolFragment = AmoyListFragment.getInstance(i);
            fragments.add(amoySchoolFragment);
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
                TabLayoutUtils.setSearchTabStyle(tabLayout, mTitles, position, R.layout.item_tablayout, SearchActivity.this);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        tabLayout.setupWithViewPager(myViewPager);
        TabLayoutUtils.setSearchTabStyle(tabLayout, mTitles, tabLayout.getSelectedTabPosition(), R.layout.item_tablayout, SearchActivity.this);
    }


    private void initFagFlowLayout() {
        List<String> historyList = new ArrayList<>();
        historyList.add("蛋白粉");
        historyList.add("瑜伽教程");
        tflHistory.setAdapter(new TagAdapter<String>(historyList) {
            @Override
            public View getView(FlowLayout parent, int position, String bean) {
                RelativeLayout view = (RelativeLayout) getLayoutInflater().inflate(R.layout.item_search_hot, parent, false);
                TextView tvHot = view.findViewById(R.id.tv_hot);
                ImageView imgClose = view.findViewById(R.id.img_close);
                tvHot.setText(bean);
//                tvHot.setOnLongClickListener(v -> {
//                    imgClose.setVisibility(View.VISIBLE);
//                    return true;
//                });
                imgClose.setOnClickListener(v -> {
//                    removeHistoryList(bean);
//                    initHistory();
                });
                tvHot.setOnClickListener(v -> {
                    mSearchBar.getSearchEditText().setText(bean);
                    mSearchBar.getSearchEditText().setSelection(bean.length());
                    relHistory.setVisibility(View.GONE);
                    myViewPager.setVisibility(View.VISIBLE);
                });
                return view;
            }
        });
        List<String> hotList = new ArrayList<>();
        hotList.add("背部");
        hotList.add("瑜伽教程");
        hotList.add("增肌");
        hotList.add("胸");
        hotList.add("深蹲");
        hotList.add("资格培训");
        tflHot.setAdapter(new TagAdapter<String>(hotList) {
            @Override
            public View getView(FlowLayout parent, int position, String bean) {
                RelativeLayout view = (RelativeLayout) getLayoutInflater().inflate(R.layout.item_search_hot, parent, false);
                TextView tvHot = view.findViewById(R.id.tv_hot);
                ImageView imgClose = view.findViewById(R.id.img_close);
                tvHot.setText(bean);
//                tvHot.setOnLongClickListener(v -> {
//                    imgClose.setVisibility(View.VISIBLE);
//                    return true;
//                });
                imgClose.setOnClickListener(v -> {
//                    removeHistoryList(bean);
//                    initHistory();
                });
                tvHot.setOnClickListener(v -> {
                    mSearchBar.getSearchEditText().setText(bean);
                    mSearchBar.getSearchEditText().setSelection(bean.length());
                    relHistory.setVisibility(View.GONE);
                    myViewPager.setVisibility(View.VISIBLE);
                });
                return view;
            }
        });
    }

    @Override
    protected LoginActivityPresenter createPresenter() {
        return new LoginActivityPresenter(this);
    }


    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()){
            case R.id.tv_right_btn:
                if(isShowResult){
                    isShowResult = false;
                    mSearchBar.getSearchEditText().setText("");
                    relHistory.setVisibility(View.VISIBLE);
                    myViewPager.setVisibility(View.GONE);
                }else {
                    finish();
                }
                break;
        }
    }

    @Override
    public void showResultAllArea(MdlBaseHttpResp<MalAddressProvince> resp) {
    }

    @Override
    public void onItemClick(String string) {

    }

    @Override
    public void onHearClick(String string) {

    }

    @Override
    public void onFavoriteClick(String string) {

    }
}
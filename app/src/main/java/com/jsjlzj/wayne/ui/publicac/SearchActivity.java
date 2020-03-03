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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.android.material.tabs.TabLayout;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.search.SearchAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.home.VideoBean;
import com.jsjlzj.wayne.entity.store.search.SearchBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.ui.store.list.AmoyListFragment;
import com.jsjlzj.wayne.utils.TabLayoutUtils;
import com.jsjlzj.wayne.utils.keyboard.KeyboardUtil;
import com.jsjlzj.wayne.widgets.MyViewPager;
import com.jsjlzj.wayne.widgets.SearchBarView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @ClassName: SearchActivity
 * @Description: 搜索界面
 * @Author: 曾海强
 * @CreateDate:
 */
public class SearchActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView, SearchAdapter.OnSearchItemClickListener {

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
    private Map<Object,Object> map = new HashMap<>();
    private boolean isShowResult = false;


    private SearchAllFragment allTypeFragment;
    private AmoyListFragment taolearnFragment;
    private AmoyListFragment matchFragment;
    private AmoyListFragment videoFragment;
    private AmoyListFragment informationFragment;
    private AmoyListFragment productFragment;
    private AmoyListFragment userFragment;
    /**
     * 搜索关键字
     */
    private String searchKey;

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, SearchActivity.class);
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
//        initFagFlowLayout();
//        initRecycler();
        initViewPager();
        initResult();
        mRightTv.setOnClickListener(clickListener);
    }

    private void initResult() {
        isShowResult = true;
        relHistory.setVisibility(View.GONE);
        myViewPager.setVisibility(View.VISIBLE);
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
                KeyboardUtil.closeKeyboard(tvHistory,SearchActivity.this);
                loadData(str);
            }
        });
    }

    public void loadData(String searchKey){
        map.clear();
        map.put("name",searchKey);
        presenter.getSearchData(map);
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
            switch (i){
                case 0:
                    allTypeFragment = SearchAllFragment.getInstance();
                    fragments.add(allTypeFragment);
                    break;
                case 1:
                    taolearnFragment = AmoyListFragment.getInstance(i,null);
                    fragments.add(taolearnFragment);
                    break;
                case 2:
                    matchFragment = AmoyListFragment.getInstance(i,null);
                    fragments.add(matchFragment);
                    break;
                case 3:
                    videoFragment = AmoyListFragment.getInstance(i,null);
                    fragments.add(videoFragment);
                    break;
                case 4:
                    informationFragment = AmoyListFragment.getInstance(i,null);
                    fragments.add(informationFragment);
                    break;
                case 5:
                    productFragment = AmoyListFragment.getInstance(i,null);
                    fragments.add(productFragment);
                    break;
                case 6:
                    userFragment = AmoyListFragment.getInstance(i,null);
                    fragments.add(userFragment);
                    break;
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
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }


    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()){
            case R.id.tv_right_btn:
//                if(isShowResult){
//                    isShowResult = false;
//                    mSearchBar.getSearchEditText().setText("");
//                    relHistory.setVisibility(View.VISIBLE);
//                    myViewPager.setVisibility(View.GONE);
//                }else {
                    finish();
//                }
                break;
        }
    }


    @Override
    public void onItemClick(VideoBean bean) {

    }

    @Override
    public void onHearClick(VideoBean bean) {

    }

    @Override
    public void onFavoriteClick(VideoBean bean) {

    }


    @Override
    public void getSearchDataSuccess(MdlBaseHttpResp<SearchBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK && resp.getData() != null){
            SearchBean.DataBean data = resp.getData().getData();
            if(data != null){
                allTypeFragment.setSearchBean(resp.getData());

                JSONArray taoLearnArray = JSONArray.parseArray(JSON.toJSONString(data.getTaoLearnList()));
                taolearnFragment.setArray(taoLearnArray);

                JSONArray matchArray = JSONArray.parseArray(JSON.toJSONString(data.getSportEventList()));
                matchFragment.setArray(matchArray);

                JSONArray videoArray = JSONArray.parseArray(JSON.toJSONString(data.getVideoList()));
                videoFragment.setArray(videoArray);

                JSONArray informationArray = JSONArray.parseArray(JSON.toJSONString(data.getInformationList()));
                informationFragment.setArray(informationArray);

                JSONArray productArray = JSONArray.parseArray(JSON.toJSONString(data.getProductList()));
                productFragment.setArray(productArray);

                JSONArray userArray = JSONArray.parseArray(JSON.toJSONString(data.getChannelList()));
                userFragment.setArray(userArray);
            }
        }
    }
}
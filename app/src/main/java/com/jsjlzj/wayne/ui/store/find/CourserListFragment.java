package com.jsjlzj.wayne.ui.store.find;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.find.CourserNewAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.find.FindLessonBean;
import com.jsjlzj.wayne.entity.find.FindLessonPageBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.widgets.CustomXRecyclerView;
import com.netease.nim.uikit.common.ToastHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @ClassName: CourserListFragment
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/04/19
 */
public class CourserListFragment extends MVPBaseFragment<HomeView, HomePresenter> implements HomeView, XRecyclerView.LoadingListener {


    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.img_time_top)
    ImageView imgTimeTop;
    @BindView(R.id.img_time_bottom)
    ImageView imgTimeBottom;
    @BindView(R.id.tv_hot)
    TextView tvHot;
    @BindView(R.id.img_hot_top)
    ImageView imgHotTop;
    @BindView(R.id.img_hot_bottom)
    ImageView imgHotBottom;
    @BindView(R.id.rv_list)
    CustomXRecyclerView rvList;

    /**
     * categoryId 为0 时搜索全部课程
     */
    private int categoryId;

    private int pageNo = 1;
    private int pageCount;
    /**
     * 1:按时间 2:按热度",
     */
    private int sortLabel = 1;
    /**
     * 1:降序 2:升序",
     */
    private int sortType = 1;
    private boolean isRefresh;
    private Map<Object, Object> map = new HashMap<>();
    private CourserNewAdapter courserNewAdapter;
    private List<FindLessonBean> list = new ArrayList<>();

    public CourserListFragment() {
    }


    public static CourserListFragment getInstance(int categoryId) {
        CourserListFragment fragment = new CourserListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("categoryId", categoryId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_courser_list;
    }

    @Override
    protected void fragment2Front() {
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl(View view) {
        imgHotTop.setVisibility(View.INVISIBLE);
        imgHotBottom.setVisibility(View.INVISIBLE);
        categoryId = getArguments().getInt("categoryId", 0);
        loadData(true);
        rvList.setLoadingListener(this);
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        courserNewAdapter = new CourserNewAdapter(getActivity(), list);
        rvList.setAdapter(courserNewAdapter);


        tvTime.setOnClickListener(clickListener);
        imgTimeBottom.setOnClickListener(clickListener);
        imgTimeTop.setOnClickListener(clickListener);
        tvHot.setOnClickListener(clickListener);
        imgHotBottom.setOnClickListener(clickListener);
        imgHotTop.setOnClickListener(clickListener);

    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.tv_time:
            case R.id.img_time_bottom:
            case R.id.img_time_top:
                sortLabel = 1;
                if (sortType == 1) {
                    sortType = 2;
                    imgTimeTop.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.triangle_up_pressed));
                    imgTimeBottom.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.triangle_down_normal));
                    imgHotTop.setVisibility(View.INVISIBLE);
                    imgHotBottom.setVisibility(View.INVISIBLE);
                    imgTimeTop.setVisibility(View.VISIBLE);
                    imgTimeBottom.setVisibility(View.VISIBLE);
                } else {
                    sortType = 1;
                    imgTimeTop.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.triangle_up_normal));
                    imgTimeBottom.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.triangle_down_pressed));
                    imgHotTop.setVisibility(View.INVISIBLE);
                    imgHotBottom.setVisibility(View.INVISIBLE);
                    imgTimeTop.setVisibility(View.VISIBLE);
                    imgTimeBottom.setVisibility(View.VISIBLE);
                }
                loadData(true);
                break;
            case R.id.tv_hot:
            case R.id.img_hot_bottom:
            case R.id.img_hot_top:
                sortLabel = 2;
                if (sortType == 1) {
                    sortType = 2;
                    imgHotTop.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.triangle_up_pressed));
                    imgHotBottom.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.triangle_down_normal));
                    imgTimeBottom.setVisibility(View.INVISIBLE);
                    imgTimeTop.setVisibility(View.INVISIBLE);
                    imgHotTop.setVisibility(View.VISIBLE);
                    imgHotBottom.setVisibility(View.VISIBLE);
                } else {
                    sortType = 1;
                    imgHotTop.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.triangle_up_normal));
                    imgHotBottom.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.triangle_down_pressed));
                    imgTimeBottom.setVisibility(View.INVISIBLE);
                    imgTimeTop.setVisibility(View.INVISIBLE);
                    imgHotTop.setVisibility(View.VISIBLE);
                    imgHotBottom.setVisibility(View.VISIBLE);
                }
                loadData(true);
                break;
        }
    }

    private void loadData(boolean isRefresh) {
        loadData(isRefresh,"");
    }


    public void loadData(boolean isRefresh,String searchKey){
        this.isRefresh = isRefresh;
        if (isRefresh) {
            pageNo = 1;
        }
        map.clear();
        if(!TextUtils.isEmpty(searchKey)){
            map.put(HttpConstant.TITLE,searchKey);
        }
        if(categoryId != 0){
            map.put(HttpConstant.CATEGORY_ID, categoryId);
        }
        map.put(HttpConstant.PAGE_NO, pageNo);
        map.put(HttpConstant.PAGE_SIZE, HttpConstant.PAGE_SIZE_NUMBER);
        map.put(HttpConstant.SORT_LABEL,sortLabel);
        map.put(HttpConstant.SORT_TYPE,sortType);
        presenter.getSearchCategoryList(map);
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public void onLoadMore() {
        if (pageNo < pageCount - 1) {
            pageNo++;
            loadData(false);
        } else {
            ToastHelper.showToast(getContext(), getString(R.string.has_no_more_data));
        }
    }

    @Override
    public void getRecommendCategoryListSuccess(MdlBaseHttpResp<FindLessonPageBean> resp) {

        rvList.refreshComplete();
        rvList.loadMoreComplete();
        if (resp.getStatus() == HttpConstant.R_HTTP_OK) {
            pageNo = resp.getData().getData().getPageNo();
            int totalCount = resp.getData().getData().getTotalCount();
            int a = totalCount % HttpConstant.PAGE_SIZE_NUMBER;
            if (a == 0) {
                pageCount = totalCount / HttpConstant.PAGE_SIZE_NUMBER;
            } else {
                pageCount = (totalCount / HttpConstant.PAGE_SIZE_NUMBER) + 1;
            }
            List<FindLessonBean> list = resp.getData().getData().getResult();
            if (list != null && list.size() > 0) {
                if (isRefresh) {
                    this.list.clear();
                }
                this.list.addAll(list);
                courserNewAdapter.setData(this.list);
                hideEmpty();
            } else if (isRefresh) {
                // 无数据
                showEmpty(R.id.rel_empty, 0, null);
            }
        }
    }
}

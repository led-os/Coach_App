package com.jsjlzj.wayne.ui.store.find;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.find.CourserNewAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.find.FindLessonBean;
import com.jsjlzj.wayne.entity.find.FindLessonPageBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
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
 * @ClassName: MoreLessonActivity
 * @Description: 更多课程列表
 * @Author: 曾海强
 * @CreateDate: 2020/05/08
 */
public class MoreLessonActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView, XRecyclerView.LoadingListener {

    @BindView(R.id.rv_data)
    CustomXRecyclerView rvData;

    private int categoryId;

    private int pageNo = 1;
    private int pageCount;
    /**
     * 0 : 免费体验   1 ： 每日一学热门课程  2 ：热门听课   3 ：减脂  4 ： 更多运动   5 ： 4们课程   6 ： 分类推荐列表
     */
    private int type;

    private boolean isRefresh;
    private Map<Object, Object> map = new HashMap<>();
    private CourserNewAdapter courserNewAdapter;
    private List<FindLessonBean> list = new ArrayList<>();


    public static void go2this(Context activity, String title, int type, int categoryId) {
        Intent intent = new Intent(activity, MoreLessonActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("type", type);
        intent.putExtra("categoryId", categoryId);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_more_leasson;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        String title = getIntent().getStringExtra("title");
        initTitle(title);
        type = getIntent().getIntExtra("type", 0);
        categoryId = getIntent().getIntExtra("categoryId", 0);
        loadData(true);
        rvData.setLoadingListener(this);
        rvData.setLayoutManager(new LinearLayoutManager(this));
        courserNewAdapter = new CourserNewAdapter(this, list);
        rvData.setAdapter(courserNewAdapter);
    }


    private void loadData(boolean isRefresh) {
        this.isRefresh = isRefresh;
        if (isRefresh) {
            pageNo = 1;
        }
        map.clear();
        map.put(HttpConstant.PAGE_NO, pageNo);
        map.put(HttpConstant.PAGE_SIZE, HttpConstant.PAGE_SIZE_NUMBER);
        switch (type) {
            case 0:
                presenter.getFreeExperCategoryList(map);
                break;
            case 1:
                presenter.getHotCategoryList(map);
                break;
            case 2:
                presenter.getHotListeningCategoryList(map);
                break;
            case 3:
                presenter.getJianzhiCategoryList(map);
                break;
            case 4:
                presenter.getMotionCategoryList(map);
                break;
            case 5:
                presenter.getFourLessonCategoryList(map);
                break;
            case 6:
                map.put(HttpConstant.CATEGORY_ID, categoryId);
                presenter.getRecommendCategoryList(map);
                break;
            default:
                break;


        }

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
            ToastHelper.showToast(this, getString(R.string.has_no_more_data));
        }
    }

    @Override
    public void getRecommendCategoryListSuccess(MdlBaseHttpResp<FindLessonPageBean> resp) {

        rvData.refreshComplete();
        rvData.loadMoreComplete();
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

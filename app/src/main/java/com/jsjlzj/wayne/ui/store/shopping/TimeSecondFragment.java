package com.jsjlzj.wayne.ui.store.shopping;


import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.shopping.TimeSecondAdapter;
import com.jsjlzj.wayne.adapter.recycler.shopping.TimeSecondItemAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.DataBean;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.shopping.ShoppingBean;
import com.jsjlzj.wayne.entity.shopping.ShoppingPageBean;
import com.jsjlzj.wayne.entity.store.home.CategoryBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.CustomXRecyclerView;
import com.netease.nim.uikit.common.ToastHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @ClassName: TimeSecondFragment
 * @Description: 显示抢购列表
 * @Author: 曾海强
 * @CreateDate:
 */
public class TimeSecondFragment extends MVPBaseFragment<HomeView, HomePresenter> implements HomeView, XRecyclerView.LoadingListener, TimeSecondItemAdapter.OnItemClickListener {


    @BindView(R.id.rv_time_second)
    CustomXRecyclerView rvTimeSecond;
    /**
     * 0 ： 限时抢购   1 ：即将开始
     */
    private int type;
    private int pageNo = 1, pageCount;
    private Map<Object, Object> map = new HashMap<>();
    private boolean isRefresh;
    private TimeSecondItemAdapter timeSecondAdapter;
    private List<ShoppingBean> shoppingList = new ArrayList<>();

    public TimeSecondFragment() {
    }

    public static TimeSecondFragment getInstance(int type) {
        TimeSecondFragment fragment = new TimeSecondFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_time_second;
    }

    @Override
    protected void initViewAndControl(View view) {
        type = getArguments().getInt("type", 0);
        rvTimeSecond.setLoadingListener(this);
        rvTimeSecond.setLayoutManager(new LinearLayoutManager(getActivity()));
        timeSecondAdapter = new TimeSecondItemAdapter(getActivity(), type, new ArrayList<>());
        timeSecondAdapter.setListener(this);
        rvTimeSecond.setAdapter(timeSecondAdapter);
        loadData(true);
    }

    @Override
    protected void fragment2Front() {
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }


    public void loadData(boolean isRefresh) {
        this.isRefresh = isRefresh;
        if (isRefresh) {
            pageNo = 1;
        }
        map.put(HttpConstant.PAGE_NO, pageNo);
        map.put(HttpConstant.PAGE_SIZE, HttpConstant.PAGE_SIZE_NUMBER);
        map.put("type", type);
        presenter.getTimeSkillProductList(map);
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
    public void getShoppingListSuccess(MdlBaseHttpResp<ShoppingPageBean> resp) {
        rvTimeSecond.refreshComplete();
        rvTimeSecond.loadMoreComplete();
        if (resp.getStatus() == HttpConstant.R_HTTP_OK) {
            pageNo = resp.getData().getData().getPageNo();
            int totalCount = resp.getData().getData().getTotalCount();
            int a = totalCount % HttpConstant.PAGE_SIZE_NUMBER;
            if (a == 0) {
                pageCount = totalCount / HttpConstant.PAGE_SIZE_NUMBER;
            } else {
                pageCount = (totalCount / HttpConstant.PAGE_SIZE_NUMBER) + 1;
            }
            List<ShoppingBean> list = resp.getData().getData().getResult();
            if (list != null && list.size() > 0) {
                if (isRefresh) {
                    shoppingList.clear();
                }
                shoppingList.addAll(list);
                timeSecondAdapter.setData(shoppingList);
                hideEmpty();
            } else if (isRefresh) {
                // 无数据
                showEmpty(R.id.rel_empty, 0, null);
            }
        }
    }

    @Override
    public void getMessageSuccess(MdlBaseHttpResp<DataBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            LogAndToastUtil.toast("已开启提醒");
        }
    }

    @Override
    public void onItemClick(ShoppingBean bean) {

    }

    @Override
    public void onRobClick(int type, ShoppingBean bean) {
        if (type == 1) {
            map.clear();
            map.put("activityId",bean.getActivityId());
            map.put("id",bean.getId());
            presenter.getTimeSkillHint(map);
        }
    }
}

package com.jsjlzj.wayne.ui.store.home.mine;

import android.app.Activity;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.mine.CurrencyDetailAdapter;
import com.jsjlzj.wayne.adapter.recycler.mine.IntegralAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.find.CurrencyDetailPageBean;
import com.jsjlzj.wayne.entity.find.JiFenPageBean;
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
 * @ClassName: IntegralDetailActivity
 * @Description: 积分明细Activity
 * @Author: 曾海强
 * @CreateDate: 2020/05/08
 */
public class IntegralDetailActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView, XRecyclerView.LoadingListener {

    @BindView(R.id.rv_data)
    CustomXRecyclerView rvData;
    /**
     * 0 :积分明细  1 ：蜂隐币明细
     */
    private int type;
    private int pageNo = 1;
    private int pageCount;

    private boolean isRefresh;
    private Map<Object, Object> map = new HashMap<>();
    private List<JiFenPageBean.DataBean.ResultBean> jiFenList = new ArrayList<>();
    private List<CurrencyDetailPageBean.DataBean.ResultBean> currencyList = new ArrayList<>();
    private IntegralAdapter integralAdapter;
    private CurrencyDetailAdapter currencyDetailAdapter;

    public static void go2this(Activity activity,int type){
        activity.startActivity(new Intent(activity,IntegralDetailActivity.class).putExtra("type",type));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_integral_deetail;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        type = getIntent().getIntExtra("type",0);
        rvData.setLoadingListener(this);
        rvData.setLayoutManager(new LinearLayoutManager(this));
        if(type == 0){
            initTitle("积分明细");
            integralAdapter = new IntegralAdapter(this,jiFenList);
            rvData.setAdapter(integralAdapter);
        }else {
            initTitle("余额明细");
            currencyDetailAdapter = new CurrencyDetailAdapter(this,currencyList);
            rvData.setAdapter(currencyDetailAdapter);
        }
        loadData(true);

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
                presenter.getJifenList(map);
                break;
            case 1:
                presenter.getCurrencyDetailList(map);
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
    public void getJifenListSuccess(MdlBaseHttpResp<JiFenPageBean> resp) {

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
            List<JiFenPageBean.DataBean.ResultBean> list = resp.getData().getData().getResult();
            if (list != null && list.size() > 0) {
                if (isRefresh) {
                    jiFenList.clear();
                }
                jiFenList.addAll(list);
                integralAdapter.setData(jiFenList);
                hideEmpty();
            } else if (isRefresh) {
                // 无数据
                showEmpty(R.id.rel_empty, 0, null);
            }
        }
    }


    @Override
    public void getCurrencyDetailListSuccess(MdlBaseHttpResp<CurrencyDetailPageBean> resp) {

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
            List<CurrencyDetailPageBean.DataBean.ResultBean> list = resp.getData().getData().getResult();
            if (list != null && list.size() > 0) {
                if (isRefresh) {
                    currencyList.clear();
                }
                currencyList.addAll(list);
                currencyDetailAdapter.setData(currencyList);
                hideEmpty();
            } else if (isRefresh) {
                // 无数据
                showEmpty(R.id.rel_empty, 0, null);
            }
        }
    }
}

package com.jsjlzj.wayne.ui.store.home.mine;

import android.app.Activity;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.mine.CashOutAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.find.CashOutPageBean;
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
 * @ClassName: CashOutRecordActivity
 * @Description: 提现记录
 * @Author: 曾海强
 * @CreateDate: 2020/05/06
 */
public class CashOutRecordActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView, XRecyclerView.LoadingListener {

    @BindView(R.id.rv_cash_out)
    CustomXRecyclerView rvCashOut;
    private int pageNo=1;
    private int pageCount;
    private boolean isRefresh;
    private Map<Object,Object> map = new HashMap<>();
    private List<CashOutPageBean.DataBean.ResultBean> cashOutList = new ArrayList<>();
    private CashOutAdapter adapter;

    public static void go2this(Activity activity){
        activity.startActivity(new Intent(activity,CashOutRecordActivity.class));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_cash_out_record;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initTitle("提现记录");
        rvCashOut.setLoadingMoreEnabled(true);
        rvCashOut.setPullRefreshEnabled(true);
        rvCashOut.setLoadingListener(this);
        rvCashOut.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CashOutAdapter(this,cashOutList);
        rvCashOut.setAdapter(adapter);
        loadData(true);
    }

    private void loadData(boolean isRefresh) {
        this.isRefresh = isRefresh;
        if(isRefresh) {
            pageNo = 1;
        }
        map.clear();
        map.put(HttpConstant.PAGE_NO, pageNo);
        map.put(HttpConstant.PAGE_SIZE, HttpConstant.PAGE_SIZE_NUMBER);
        presenter.getCashOutList(map);
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public void onLoadMore() {
        if (pageNo < pageCount) {
            pageNo++;
            loadData(false);
        } else {
            ToastHelper.showToast(this, getString(R.string.has_no_more_data));
        }
    }

    @Override
    public void getCashOutListSuccess(MdlBaseHttpResp<CashOutPageBean> resp) {
        rvCashOut.refreshComplete();
        rvCashOut.loadMoreComplete();
        if (resp.getStatus() == HttpConstant.R_HTTP_OK){
            pageNo = resp.getData().getData().getPageNo();
            int totalCount = resp.getData().getData().getTotalCount();
            int a = totalCount % HttpConstant.PAGE_SIZE_NUMBER;
            if (a == 0) {
                pageCount = totalCount / HttpConstant.PAGE_SIZE_NUMBER;
            } else {
                pageCount =( totalCount / HttpConstant.PAGE_SIZE_NUMBER)+1;
            }
            List<CashOutPageBean.DataBean.ResultBean> list = resp.getData().getData().getResult();
            if (list != null && list.size() > 0) {
                if (isRefresh) {
                    cashOutList.clear();
                }
                cashOutList.addAll(list);
                adapter.setData(cashOutList);
                hideEmpty();
            } else if (isRefresh) {
                // 无数据
                showEmpty(R.id.rel_empty,0,null);
            }
        }
    }
}

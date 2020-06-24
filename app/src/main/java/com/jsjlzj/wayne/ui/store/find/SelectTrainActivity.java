package com.jsjlzj.wayne.ui.store.find;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.search.SearchUserAdapter;
import com.jsjlzj.wayne.entity.store.search.ChannelListBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.widgets.CustomXRecyclerView;
import com.netease.nim.uikit.common.ToastHelper;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;

/**
 * @description 选择教练
 * @date: 2020/06/24
 * @author: 曾海强
 */
public class SelectTrainActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView, XRecyclerView.LoadingListener, SearchUserAdapter.OnSearchUserClickListener {

    public static final int REQUEST_CODE = 10000;
    @BindView(R.id.rv_trainer)
    CustomXRecyclerView rvTrainer;
    private SearchUserAdapter adapter;
    private int pageNo = 1, pageCount;

    public static void go2this(Activity activity,int requestCode){
        activity.startActivityForResult(new Intent(activity,SelectTrainActivity.class),requestCode);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_select_train;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        rvTrainer.setLoadingMoreEnabled(true);
        rvTrainer.setPullRefreshEnabled(false);
        rvTrainer.setLoadingListener(this);
        rvTrainer.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SearchUserAdapter(this,new ArrayList<>());
        adapter.setListener(this);
        rvTrainer.setAdapter(adapter);
    }


    @Override
    public void onRefresh() {}

    @Override
    public void onLoadMore() {
        if (pageNo < pageCount) {
            pageNo++;
//            loadData(false);
        } else {
            ToastHelper.showToast(this, getString(R.string.has_no_more_data));
        }
    }

    @Override
    public void onItemClick(ChannelListBean string) {

    }

    @Override
    public void onFavoriteClick(ChannelListBean string) {

    }
}

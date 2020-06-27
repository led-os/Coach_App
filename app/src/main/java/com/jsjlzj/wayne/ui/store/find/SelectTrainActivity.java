package com.jsjlzj.wayne.ui.store.find;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.find.FindTrainAdapter;
import com.jsjlzj.wayne.adapter.recycler.search.SearchUserAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.find.FindTrainerBean;
import com.jsjlzj.wayne.entity.store.search.ChannelListBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.widgets.CustomXRecyclerView;
import com.netease.nim.uikit.common.ToastHelper;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;

/**
 * @description 选择教练
 * @date: 2020/06/24
 * @author: 曾海强
 */
public class SelectTrainActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView, FindTrainAdapter.OnSearchUserClickListener {

    public static final int REQUEST_CODE = 10000;
    @BindView(R.id.rv_trainer)
    RecyclerView rvTrainer;
    private FindTrainAdapter adapter;
    private int storeId;
    private int selectTrainId;

    public static void go2this(Activity activity,int storeId,int selectTrainId,int requestCode){
        activity.startActivityForResult(new Intent(activity,SelectTrainActivity.class)
                .putExtra("storeId",storeId)
                .putExtra("selectTrainId",selectTrainId),requestCode);
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
        initTitle("选择教练");
        selectTrainId = getIntent().getIntExtra("selectTrainId",0);
        storeId = getIntent().getIntExtra("storeId",1);
        rvTrainer.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FindTrainAdapter(this,new ArrayList<>(),selectTrainId);
        adapter.setListener(this);
        rvTrainer.setAdapter(adapter);
        presenter.getFindTrainerList(storeId);
    }


    @Override
    public void onItemClick(FindTrainerBean.DataBean bean) {

    }

    @Override
    public void onFavoriteClick(FindTrainerBean.DataBean bean) {
        Intent intent = new Intent();
        intent.putExtra("selectTrainId",bean.getId());
        setResult(RESULT_OK,intent);
        finish();
    }


    @Override
    public void getFindStoreTrainerListSuccess(MdlBaseHttpResp<FindTrainerBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            adapter.setData(resp.getData().getData());
        }
    }

}

package com.jsjlzj.wayne.ui.store.find;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.find.FindStoreAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.find.FindStoreBean;
import com.jsjlzj.wayne.entity.find.FindStoreRecommendBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: 评价成功
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate:
 */
public class StoreEvaluateSuccessActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {


    @BindView(R.id.tv_scan_detail)
    TextView tvScanDetail;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.rv_recommend)
    RecyclerView rvRecommend;
    private int evaluateId;
    private FindStoreAdapter findStoreAdapter;
    private List<FindStoreBean> list = new ArrayList<>();

    public static void go2this(Activity activity, int evaluateId) {
        activity.startActivity(new Intent(activity, StoreEvaluateSuccessActivity.class).putExtra("evaluateId", evaluateId));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_store_evaluste_success;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initTitle("评价结果");
        evaluateId = getIntent().getIntExtra("evaluateId",0);
        tvScanDetail.setOnClickListener(clickListener);
        tvScanDetail.setOnClickListener(clickListener);
        findStoreAdapter = new FindStoreAdapter(this, list);
        findStoreAdapter.setType(1);
        rvRecommend.setLayoutManager(new LinearLayoutManager(this));
        rvRecommend.setAdapter(findStoreAdapter);
        presenter.getFindStoreRecommendList(evaluateId);
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()){
            case R.id.tv_scan_detail:
                FindMyEvaluateActivity.go2this(this,evaluateId);
                break;
            case R.id.tv_share:
                break;
        }
    }

    @Override
    public void getFindStoreRecommendListSuccess(MdlBaseHttpResp<FindStoreRecommendBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            findStoreAdapter.setData(resp.getData().getData());
        }
    }
}

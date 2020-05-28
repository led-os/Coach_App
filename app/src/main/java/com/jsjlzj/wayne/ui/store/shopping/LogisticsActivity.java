package com.jsjlzj.wayne.ui.store.shopping;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.shopping.LogisticsAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.shopping.LogisticsBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;

import java.util.HashMap;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @description 物流信息
 * @date: 2020/05/28
 * @author: 曾海强
 */
public class LogisticsActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_yun_code)
    TextView tvYunCode;
    @BindView(R.id.rv_logistics)
    RecyclerView rvLogistics;

    private String orderCode;

    public static void go2this(Activity activity,String orderCode){
        activity.startActivity(new Intent(activity,LogisticsActivity.class)
                .putExtra("orderCode",orderCode));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_logistics;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initTitle("物流信息");
        orderCode = getIntent().getStringExtra("orderCode");
        Map<Object,Object> map = new HashMap<>();
        map.put("orderCode",orderCode);
        presenter.getLogisticsInfo(map);
    }

    @Override
    public void getLogisticsListSuccess(MdlBaseHttpResp<LogisticsBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            LogisticsBean.DataBean bean = resp.getData().getData();
            tvName.setText(bean.getReceiverName());
            tvPhone.setText(bean.getReceiverPhone());
            tvLocation.setText(bean.getReceiverAddress());
            if(bean.getList() != null && bean.getList().size() > 0){
                tvYunCode.setText("运单号: "+bean.getList().get(0).getLogisticCode());
                rvLogistics.setLayoutManager(new LinearLayoutManager(this));
                LogisticsAdapter adapter = new LogisticsAdapter(this,bean.getList());
                rvLogistics.setAdapter(adapter);
            }

        }
    }
}

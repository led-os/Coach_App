package com.jsjlzj.wayne.ui.store.home.mine;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.mine.CurrencyAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.find.CurrencyBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @ClassName: CurrencyActivity
 * @Description: 蜂隐币
 * @Author: 曾海强
 * @CreateDate: 2020/05/08
 */
public class CurrencyActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.rv_currency)
    RecyclerView rvCurrency;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.tv_recharge)
    TextView tvRecharge;

    private CurrencyAdapter currencyAdapter;

    public static void go2this(Activity activity){
        activity.startActivity(new Intent(activity,CurrencyActivity.class));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_currency;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        imgBack.setOnClickListener(clickListener);
        tvDetail.setOnClickListener(clickListener);
        tvRecharge.setOnClickListener(clickListener);
        rvCurrency.setLayoutManager(new GridLayoutManager(this,3));
        currencyAdapter = new CurrencyAdapter(this,new ArrayList<>());
        rvCurrency.setAdapter(currencyAdapter);
        presenter.getCurrencyList();
    }


    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_detail://明细
                IntegralDetailActivity.go2this(this,1);
                break;
            case R.id.tv_recharge://去充值
                CurrencyBean.DataBean.ProductListBean bean = currencyAdapter.getSelectMoney();
                String price = bean.getPrice();
                LogAndToastUtil.toast(price);
                break;
        }
    }


    @Override
    public void getCurrencyListSuccess(MdlBaseHttpResp<CurrencyBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            if(resp.getData().getData() != null && resp.getData().getData().getProductList() != null
            && resp.getData().getData().getProductList().size() > 0){
                tvMoney.setText(resp.getData().getData().getAmount()+"币");
                currencyAdapter.setData(resp.getData().getData().getProductList());
            }

        }
    }
}

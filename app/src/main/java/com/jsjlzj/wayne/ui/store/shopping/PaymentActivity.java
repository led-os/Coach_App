package com.jsjlzj.wayne.ui.store.shopping;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;

import butterknife.BindView;
import butterknife.OnClick;

public class PaymentActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.img_zfb)
    ImageView imgZfb;
    @BindView(R.id.img_wx)
    ImageView imgWx;
    @BindView(R.id.tv_to_pay)
    TextView tvToPay;

    public static void go2this(Activity activity){
        activity.startActivity(new Intent(activity,PaymentActivity.class));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_payment;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initTitle("确认支付");
        imgZfb.setSelected(true);
        imgWx.setSelected(false);


    }


    @OnClick({R.id.ll_zfb, R.id.ll_wx, R.id.tv_to_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_zfb:
                imgZfb.setSelected(true);
                imgWx.setSelected(false);
                break;
            case R.id.ll_wx:
                imgZfb.setSelected(false);
                imgWx.setSelected(true);
                break;
            case R.id.tv_to_pay:
                PayResultActivity.go2this(this,0);
                break;
        }
    }
}

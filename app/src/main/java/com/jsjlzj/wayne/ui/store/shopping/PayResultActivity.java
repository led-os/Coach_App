package com.jsjlzj.wayne.ui.store.shopping;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;

import butterknife.BindView;

/**
 * @ClassName: PayResultActivity
 * @Description: 支付结果
 * @Author: 曾海强
 * @CreateDate:
 */
public class PayResultActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    /**
     * 0 ： 支付成功    1 ： 提现成功
     */
    int type;
    @BindView(R.id.tv_success_hint)
    TextView tvSuccessHint;
    @BindView(R.id.tv_success_des)
    TextView tvSuccessDes;
    @BindView(R.id.tv_detail)
    TextView tvDetail;

    public static void go2this(Activity activity, int type) {
        activity.startActivity(new Intent(activity, PayResultActivity.class).putExtra(ExtraConstant.EXTRA_SHOW_TYPE, type));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_pay_result;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        type = getIntent().getIntExtra(ExtraConstant.EXTRA_SHOW_TYPE, 0);
        if (type == 0) {
            initTitle("支付结果");
            tvSuccessHint.setText("支付成功");
            tvSuccessDes.setText("订单编号：e283109872");
            tvDetail.setText("查看详情");

        } else {
            initTitle("提现");
            tvSuccessHint.setText("提现成功");
            tvSuccessDes.setText("您的提现申请提交成功，预计将在2个工作日内到账，感谢您的支持，请耐心等待。");
            tvDetail.setText("返回");
        }
        tvDetail.setOnClickListener(clickListener);
    }


    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        if(view.getId() == R.id.tv_detail){
            if(type == 0){

            }else {
                finish();
            }
        }
    }
}

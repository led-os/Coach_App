package com.jsjlzj.wayne.ui.store.home.mine;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.find.MineProfitBean;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;

import butterknife.BindView;

/**
 * @ClassName: MineProfitActivity
 * @Description: 我的收益
 * @Author: 曾海强
 * @CreateDate: 2020/05/06
 */
public class MineProfitActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_all_money)
    TextView tvAllMoney;
    @BindView(R.id.tv_commit_money)
    TextView tvCommitMoney;
    @BindView(R.id.tv_profit_last_month)
    TextView tvProfitLastMonth;
    @BindView(R.id.tv_profit_month)
    TextView tvProfitMonth;
    @BindView(R.id.tv_predict_last_month)
    TextView tvPredictLastMonth;
    @BindView(R.id.tv_order_num)
    TextView tvOrderNum;
    @BindView(R.id.tv_profit_money)
    TextView tvProfitMoney;
    @BindView(R.id.tv_fail_profit)
    TextView tvFailProfit;
    @BindView(R.id.tv_order_num_last)
    TextView tvOrderNumLast;
    @BindView(R.id.tv_profit_money_last)
    TextView tvProfitMoneyLast;
    @BindView(R.id.tv_fail_profit_last)
    TextView tvFailProfitLast;
    @BindView(R.id.ll_profit_detail)
    LinearLayout llProfitDetail;
    @BindView(R.id.ll_record)
    LinearLayout llRecord;   
    @BindView(R.id.img_profit_des)
    ImageView imgProfitDes;

    private String money ;

    public static void go2this(Activity activity){
        activity.startActivity(new Intent(activity,MineProfitActivity.class));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_mine_profit;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initTitle("我的收益");
        tvCommitMoney.setOnClickListener(clickListener);
        llProfitDetail.setOnClickListener(clickListener);
        llRecord.setOnClickListener(clickListener);
        imgProfitDes.setOnClickListener(clickListener);
        presenter.getMineProfit();
    }


    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()){
            case R.id.img_profit_des:
                WebViewContainerActivity.go2this(this,"收益说明", HttpConstant.WEB_URL_BENEFIT_INFO, WebViewContainerFragment.TYPE_PROFIT);
                break;
            case R.id.tv_commit_money:
                if(Float.valueOf(money) <= 0){
                    LogAndToastUtil.toast("暂无提现额度");
                }else {
                    CashOutActivity.go2this(this,tvMoney.getText().toString());
                }
                break;
            case R.id.ll_profit_detail:
                ProfitOrderActivity.go2this(this);
                break;
            case R.id.ll_record:
                CashOutRecordActivity.go2this(this);
                break;
            default:break;
        }
    }


    @Override
    public void getMineProfitSuccess(MdlBaseHttpResp<MineProfitBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            if(resp.getData().getData() != null){
                money = resp.getData().getData().getWithdrawableAmount();
                tvMoney.setText(getResources().getString(R.string.chinese_money) + money);
                tvAllMoney.setText(getResources().getString(R.string.chinese_money) + resp.getData().getData().getTotalAmount());
                tvProfitLastMonth.setText(getResources().getString(R.string.chinese_money) + resp.getData().getData().getLastMonthSettlementIncome());
                tvProfitMonth.setText(getResources().getString(R.string.chinese_money)+resp.getData().getData().getCurrentMonthEstimateIncome());
                tvPredictLastMonth.setText(getResources().getString(R.string.chinese_money)+resp.getData().getData().getCurrentMonthSettlementIncome());
            }
        }
    }
}

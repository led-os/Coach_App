package com.jsjlzj.wayne.ui.store.home.mine;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.DataBean;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.ui.store.shopping.PayResultActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * @ClassName: CashOutActivity
 * @Description: 提现界面
 * @Author: 曾海强
 * @CreateDate: 2020/05/06
 */
public class CashOutActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    public static final int REQUEST_CODE_ADD_BANK = 1000;

    @BindView(R.id.tv_cash_out_num)
    TextView tvCashOutNum;
    @BindView(R.id.tv_money)
    EditText tvMoney;
    @BindView(R.id.tv_all_cash_out)
    TextView tvAllCashOut;
    @BindView(R.id.tv_band_card)
    TextView tvBandCard;
    @BindView(R.id.tv_add_modify)
    TextView tvAddModify;
    @BindView(R.id.tv_one_cash_out)
    TextView tvOneCashOut;
    private Map<Object,Object> map = new HashMap<>();
    private int bankCardId;

    public static void go2this(Activity activity){
        activity.startActivity(new Intent(activity,CashOutActivity.class));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_cash_out;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initTitle("提现");
        tvAllCashOut.setOnClickListener(clickListener);
        tvAddModify.setOnClickListener(clickListener);
        tvOneCashOut.setOnClickListener(clickListener);
    }

    private void commitCashout() {
        if(TextUtils.isEmpty(tvMoney.getText().toString())){
            LogAndToastUtil.toast("请输入要申请的提现额度");
            return;
        }
        if(bankCardId == 0){
            LogAndToastUtil.toast("请选择收款银行卡");
            return;
        }
        map.clear();
        map.put("amount",tvMoney.getText().toString());
        map.put("bankCardId",tvMoney.getText().toString());
        presenter.applyLeader(map);
    }


    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()){
            case R.id.tv_all_cash_out://全部提现
                tvMoney.setText("4000.00");
                break;
            case R.id.tv_add_modify://添加或更换银行卡
                if(tvAddModify.getText().toString().equals("添加")){
                    AddBandCardActivity.go2this(this,0,REQUEST_CODE_ADD_BANK);
                }else {
                    AddBandCardActivity.go2this(this,1,REQUEST_CODE_ADD_BANK);
                }
                break;
            case R.id.tv_one_cash_out://一键提现
                if(!TextUtils.isEmpty(tvBandCard.getText().toString()) && tvBandCard.getVisibility() == View.VISIBLE){
                    commitCashout();
                }else {
                    LogAndToastUtil.toast("请选择收款银行卡");
                }
                break;
            default:break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_ADD_BANK && resultCode == Activity.RESULT_OK){
            tvBandCard.setVisibility(View.VISIBLE);
            String openName = data.getStringExtra("open_name");
            String bankCard = data.getStringExtra("bank_card");
            bankCardId = data.getIntExtra("bankCardId",0);
            tvBandCard.setText(openName+" ("+bankCard.substring(bankCard.length()-4)+")");
            tvAddModify.setText("更换");
        }
    }

    @Override
    public void getMessageSuccess(MdlBaseHttpResp<DataBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            LogAndToastUtil.toast("提现成功");
            PayResultActivity.go2this(this,1);
            finish();
        }
    }
}

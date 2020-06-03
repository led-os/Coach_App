package com.jsjlzj.wayne.ui.store.find;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.DataBean;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.find.CurrencyBean;
import com.jsjlzj.wayne.entity.find.FindLessonBean;
import com.jsjlzj.wayne.entity.find.FindLessonDetailBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.ui.store.home.mine.CurrencyActivity;
import com.jsjlzj.wayne.utils.DateUtil;
import com.jsjlzj.wayne.utils.GlidUtils;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SPUtil;
import com.jsjlzj.wayne.widgets.PayPsdInputView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: ConfirmCourserOrderActivity
 * @Description: 确认订单界面
 * @Author: 曾海强
 * @CreateDate:
 */
public class ConfirmCourserOrderActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    public static final int REQUEST_CODE_BUY_COURSE = 1234;
    @BindView(R.id.rv_order)
    RecyclerView rvOrder;
    @BindView(R.id.tv_currency)
    TextView tvCurrency;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_all_money)
    TextView tvAllMoney;
    @BindView(R.id.tv_commit_order)
    TextView tvCommitOrder;
    @BindView(R.id.img_pic)
    ImageView imgPic;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.tv_money_courser)
    TextView tvMoneyCourser;
    @BindView(R.id.tv_buy_currency)
    TextView tvBuyCurrency;

    private int courserId;
    private Map<Object, Object> map = new HashMap<>();
    private float currency;
    private float totalMoney;


    public static void go2this(Activity activity, int id,int requestCode) {
        activity.startActivityForResult(new Intent(activity, ConfirmCourserOrderActivity.class).putExtra("courserId", id),requestCode);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_confirm_courser_order;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initTitle("确认订单");
        courserId = getIntent().getIntExtra("courserId",0);
        map.put("id", courserId);
        presenter.getCourserDetail(map);
        presenter.getCurrencyList();
        SPUtil.getUserFromSP();
        tvCommitOrder.setOnClickListener(clickListener);
        tvBuyCurrency.setOnClickListener(clickListener);
    }


    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.tv_commit_order:
                if(currency > totalMoney && currency > 0){
                    PayPasswordFragment.showFragment(getSupportFragmentManager(), totalMoney, 1, new PayPsdInputView.onPasswordListener(){

                        @Override
                        public void onDifference(String oldPsd, String newPsd) { }

                        @Override
                        public void onEqual(String psd) { }

                        @Override
                        public void inputFinished(String inputPsd) {
                            map.clear();
                            map.put("lessonId",courserId);
                            map.put("payPassword",inputPsd);
                            presenter.buyCourserByCurrency(map);
                        }
                    });
                }else {
                    LogAndToastUtil.toast("余额不足，请充值");
                }

                break;
            case R.id.tv_buy_currency:
                CurrencyActivity.go2this(this);
                break;
            default:
                break;
        }
    }


    @Override
    public void getCourserDetailSuccess(MdlBaseHttpResp<FindLessonDetailBean> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK) {
            FindLessonBean bean = resp.getData().getData();
            if (bean != null) {
                tvName.setText(bean.getTitle());
                GlidUtils.setGrid(this, bean.getCoverImg(), imgPic);
                tvDes.setText(bean.getLessonDesc());
                tvMoneyCourser.setText("蜂隐币" + DateUtil.getTwoDotByFloatFY(bean.getPrice()));
                tvAllMoney.setText("蜂隐币 " + DateUtil.getTwoDotByFloatFY(bean.getPrice()));
                totalMoney = bean.getPrice();
            }
        }
    }


    @Override
    public void getCurrencyListSuccess(MdlBaseHttpResp<CurrencyBean> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK) {
            if (resp.getData().getData() != null && resp.getData().getData().getProductList() != null
                    && resp.getData().getData().getProductList().size() > 0) {
                currency = resp.getData().getData().getAmount();
                tvMoney.setText("余额：" + DateUtil.getTwoDotByFloatFY(currency) + "蜂隐币");
            }

        }
    }


    @Override
    public void getMessageSuccess(MdlBaseHttpResp<DataBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            LogAndToastUtil.toast("购买成功");
            setResult(Activity.RESULT_OK);
            finish();
        }
    }
}

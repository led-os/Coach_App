package com.jsjlzj.wayne.ui.store.shopping;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alipay.sdk.app.PayTask;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.shopping.CommitOrderBean;
import com.jsjlzj.wayne.entity.shopping.CommitOrderBody;
import com.jsjlzj.wayne.entity.shopping.PayResult;
import com.jsjlzj.wayne.entity.shopping.PayResultBean;
import com.jsjlzj.wayne.entity.shopping.VipDataBean;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.DateUtil;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SPUtil;
import com.jsjlzj.wayne.wxapi.RenewObserver;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.OnClick;

/**
  *
  * @ClassName:      PaymentActivity
  * @Description:    java类作用描述
  * @Author:         曾海强
  * @CreateDate:
  */
public class PaymentActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView,RenewObserver.OnWeiXinListener {

     private static final int SDK_PAY_FLAG = 1;
    public static final int REQUEST_CODE_BUY_VIP = 2345;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.img_zfb)
    ImageView imgZfb;
    @BindView(R.id.img_wx)
    ImageView imgWx;
    @BindView(R.id.tv_to_pay)
    TextView tvToPay;
    /**
     * 0 :微信  1 ：支付宝
     */
    private int payType = 1;
     /**
      * "0,商城; 1,蜂币充值; 2,VIP充值",
      */
    private int type;
    private String orderCode ,amount;
    private int productId;
    private boolean isPay;
    private Map<Object,Object> map = new HashMap<>();


    public static void go2this(Activity activity,String orderCode,String amount){
        activity.startActivity(new Intent(activity,PaymentActivity.class)
                .putExtra("orderCode",orderCode)
                .putExtra("amount",amount));
    }

     /**
      * 购买蜂隐币
      */
     public static void go2this(Activity activity,int type,int productId,String amount){
        activity.startActivity(new Intent(activity,PaymentActivity.class)
                .putExtra("type",type)
                .putExtra("productId",productId)
                .putExtra("amount",amount));
    }
     /**
      * vip购买
      */
     public static void go2this(Activity activity,int type,int productId,String amount,int requestCode){
        activity.startActivityForResult(new Intent(activity,PaymentActivity.class)
                .putExtra("type",type)
                .putExtra("productId",productId)
                .putExtra("amount",amount),requestCode);
    }

    @Override
    protected int getLayoutResId() {
//       EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        return R.layout.activity_payment;
    }


    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    JSONObject jsonObject = JSONObject.parseObject(resultInfo);
                    // 判断resultStatus 为9000则代表支付成功

                    if (TextUtils.equals(resultStatus, "9000")) {
                        JSONObject bean = JSONObject.parseObject(jsonObject.getString("alipay_trade_app_pay_response"));
                        String tradeNo = bean.getString("trade_no");
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        map.clear();
                        map.put("orderCode",orderCode);
                        map.put("payType",payType);
                        map.put("tradeNo",tradeNo);
                        presenter.searchPayResult(map);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        LogAndToastUtil.toast("支付失败");
                        finish();
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    @Override
    protected void initViewAndControl() {
        initTitle("确认支付");
        type = getIntent().getIntExtra("type",0);
        productId = getIntent().getIntExtra("productId",0);
        amount = getIntent().getStringExtra("amount");
        if(type == 1 || type == 2){
            tvPrice.setText(DateUtil.getTwoDotByFloatFY(Float.valueOf(amount)));
        }else {
            orderCode = getIntent().getStringExtra("orderCode");
            tvPrice.setText(DateUtil.getTwoDotByFloat(Float.valueOf(amount)));
        }
        imgZfb.setSelected(true);
        imgWx.setSelected(false);
        RenewObserver.getInstance().registerWeiXinListener(this);

    }


    @OnClick({R.id.ll_zfb, R.id.ll_wx, R.id.tv_to_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_zfb:
                payType = 1;
                imgZfb.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.cbx_select));
                imgWx.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.cbx_unselect));
                break;
            case R.id.ll_wx:
                payType = 0;
                imgZfb.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.cbx_unselect));
                imgWx.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.cbx_select));
                break;
            case R.id.tv_to_pay:
                if(type == 0){
                    toPay();
                }else {
                    commitVipAndCurrency();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 提交vip购买和蜂隐币订单
     */
    private void commitVipAndCurrency(){
        map.clear();
        isPay = false;
        List<CommitOrderBody> list = new ArrayList<>();
        CommitOrderBody commitOrderBody = new CommitOrderBody();
        commitOrderBody.setProductId(productId);list.add(commitOrderBody);
        map.put("products", list);
        map.put("type",type);
        map.put("payType",payType);
        presenter.commitOrder2(map);
    }

    private void toPay() {
        isPay = true;
        map.clear();
        map.put("orderCode",orderCode);
        map.put("payType",payType);
        presenter.toPayOrder(map);
    }

    @Override
    public void commitOrder2Success(MdlBaseHttpResp<CommitOrderBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK && resp.getData().getData() != null){
            if(isPay){
                if (payType == 0) {
                    CommitOrderBean.DataBean.WxPayParamBean payParamBean = resp.getData().getData().getWxPayParam();
                    if (payParamBean == null) {
                        return;
                    }
                    Runnable payRunnable = () -> {
                        PayReq request = new PayReq();
                        request.appId = payParamBean.getAppid();
                        request.partnerId = payParamBean.getPartnerid();
                        request.prepayId = payParamBean.getPrepayid();
                        request.packageValue = "Sign=WXPay";
                        request.nonceStr = payParamBean.getNoncestr();
                        request.timeStamp = payParamBean.getTimestamp();
                        request.sign = payParamBean.getSign();
                        MyApp.getApp().getIwxapi().sendReq(request);
                    };
                    // 必须异步调用
                    Thread payThread = new Thread(payRunnable);
                    payThread.start();
                } else if (payType == 1) {
                    // 订单信息
                    final String outTradeNo = resp.getData().getData().getUrl();
                    Runnable payRunnable = () -> {
                        PayTask alipay = new PayTask(PaymentActivity.this);
                        Map<String, String> result = alipay.payV2(outTradeNo, true);

                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    };
                    // 必须异步调用
                    Thread payThread = new Thread(payRunnable);
                    payThread.start();
                }
            }else {
                orderCode = resp.getData().getData().getOrderCode();
                toPay();
            }

        }
    }


     @Override
     public void searchPayResultSuccess(MdlBaseHttpResp<PayResultBean> resp) {
         if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            if(!TextUtils.isEmpty(resp.getData().getData().getOrderCode())){
                LogAndToastUtil.toast("支付成功");
                if(type == 0){
                    PayResultActivity.go2this(this,0,resp.getData().getData().getOrderCode());
                }
                SPUtil.saveWebRefresh(true);
                finish();
            }
         }
     }


     @Override
     public void commitVipOrderSuccess(MdlBaseHttpResp<VipDataBean> resp) {
        LogAndToastUtil.log("成功");
         if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            orderCode = resp.getData().getData();
         }
     }

    @Override
    public void onPay(int errCode) {
        if (errCode == BaseResp.ErrCode.ERR_OK) {
            map.clear();
            map.put("orderCode",orderCode);
            map.put("payType",payType);
            presenter.searchPayResult(map);
        } else if (errCode == BaseResp.ErrCode.ERR_USER_CANCEL) {
                LogAndToastUtil.toast("取消支付");
        }
    }

    @Override
    protected void onDestroy() {
        RenewObserver.getInstance().unRegisterWeiXinListener(this);
        super.onDestroy();
    }
}
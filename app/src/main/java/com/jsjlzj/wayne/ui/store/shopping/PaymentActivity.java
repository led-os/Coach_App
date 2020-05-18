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
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.shopping.CommitOrderBean;
import com.jsjlzj.wayne.entity.shopping.PayResult;
import com.jsjlzj.wayne.entity.shopping.PayResultBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

 /**
  *
  * @ClassName:      PaymentActivity
  * @Description:    java类作用描述
  * @Author:         曾海强
  * @CreateDate:
  */
public class PaymentActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

     private static final int SDK_PAY_FLAG = 1;

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

    private String orderCode ,amount;


     public static void go2this(Activity activity,String orderCode,String amount){
        activity.startActivity(new Intent(activity,PaymentActivity.class)
                .putExtra("orderCode",orderCode)
                .putExtra("amount",amount));
    }

    @Override
    protected int getLayoutResId() {
       EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
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
                    JSONObject bean = JSONObject.parseObject(jsonObject.getString("alipay_trade_app_pay_response"));
                    String tradeNo = bean.getString("trade_no");
                    System.out.println(resultStatus+"resultInfo====="+tradeNo);
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Map<Object,Object> map = new HashMap<>();
                        map.put("orderCode",orderCode);
                        map.put("payType",payType);
                        map.put("tradeNo",tradeNo);
                        presenter.searchPayResult(map);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        LogAndToastUtil.toast("支付失败");
                    }
                    break;
                }
//                case SDK_AUTH_FLAG: {
//                    @SuppressWarnings("unchecked")
//                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
//                    String resultStatus = authResult.getResultStatus();
//
//                    // 判断resultStatus 为“9000”且result_code
//                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
//                    if (TextUtils.equals(resultStatus, "9000")) {
//                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
//                        // 传入，则支付账户为该授权账户
//                        mPresenter.modifyOrderStatus(orderId);
//                    } else {
//                        // 其他状态值则为授权失败
//                        toastMessage(R.string.pay_fail);
//                    }
//                    break;
//                }
                default:
                    break;
            }
        }
    };

    @Override
    protected void initViewAndControl() {
        initTitle("确认支付");
        amount = getIntent().getStringExtra("amount");
        orderCode = getIntent().getStringExtra("orderCode");
        tvPrice.setText(amount);
        imgZfb.setSelected(true);
        imgWx.setSelected(false);
    }


    @OnClick({R.id.ll_zfb, R.id.ll_wx, R.id.tv_to_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_zfb:
                payType = 1;
                imgZfb.setSelected(true);
                imgWx.setSelected(false);
                break;
            case R.id.ll_wx:
                payType = 0;
                imgZfb.setSelected(false);
                imgWx.setSelected(true);
                break;
            case R.id.tv_to_pay:
                toPay();
                break;
        }
    }

    private void toPay() {
        Map map = new HashMap();
        map.put("orderCode",orderCode);
        map.put("payType",payType);
        presenter.toPayOrder(map);
    }

    @Override
    public void commitOrder2Success(MdlBaseHttpResp<CommitOrderBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK && resp.getData().getData() != null){
            if (payType == 0) {
//                PayResultBean.PayResponseBean orderBean = order.getPayResponse();
//                if (orderBean == null) {
//                    return;
//                }
//                Runnable payRunnable = new Runnable() {
//                    @Override
//                    public void run() {
//                        PayReq request = new PayReq();
//                        request.appId = orderBean.getAppId();
//                        request.partnerId = orderBean.getPartnerid();
//                        request.prepayId = orderBean.getPrepayid();
//                        request.packageValue = "Sign=WXPay";
//                        request.nonceStr = orderBean.getNonceStr();
//                        request.timeStamp = orderBean.getTimeStamp();
//                        request.sign = orderBean.getPaySign();
//                        App.getWeiXinAPI().sendReq(request);
//                    }
//                };
//                // 必须异步调用
//                Thread payThread = new Thread(payRunnable);
//                payThread.start();
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
        }
    }


     @Override
     public void searchPayResultSuccess(MdlBaseHttpResp<PayResultBean> resp) {
         if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            if(!TextUtils.isEmpty(resp.getData().getData().getOrderCode())){
                LogAndToastUtil.toast("支付成功");
                PayResultActivity.go2this(this,0,resp.getData().getData().getOrderCode());
            }
         }
     }
 }
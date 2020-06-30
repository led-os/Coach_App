package com.jsjlzj.wayne.ui.store.shopping;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.shopping.OrderDetailAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.constant.MyPermissionConstant;
import com.jsjlzj.wayne.entity.DataBean;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.shopping.AfterSaleDetailBean;
import com.jsjlzj.wayne.entity.shopping.MineOrderPageBean;
import com.jsjlzj.wayne.entity.shopping.OrderDetailBean;
import com.jsjlzj.wayne.entity.shopping.ShoppingCarBean;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.ui.publicac.dialog.CallPhoneDialog;
import com.jsjlzj.wayne.ui.store.home.mine.AfterSaleApplyActivity;
import com.jsjlzj.wayne.utils.DateUtil;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.permission.PermissionUtil;
import com.jsjlzj.wayne.widgets.TimeCounter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: 订单详情
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate:
 */
public class OrderDetailActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {


    public static final int REQUEST_CODE = 2222;
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.btn_title_right)
    ImageView btnTitleRight;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.rel_location)
    RelativeLayout relLocation;
    @BindView(R.id.rv_order)
    RecyclerView rvOrder;
    @BindView(R.id.tv_all_money)
    TextView tvAllMoney;
    @BindView(R.id.tv_coupon)
    TextView tvCoupon;
    @BindView(R.id.ll_coupon)
    LinearLayout llCoupon;
    @BindView(R.id.tv_freight)
    TextView tvFreight;
    @BindView(R.id.ll_freight)
    LinearLayout llFreight;
    @BindView(R.id.tv_actual_money)
    TextView tvActualMoney;
    @BindView(R.id.tv_order_code)
    TextView tvOrderCode;
    @BindView(R.id.tv_water)
    TextView tvWater;
    @BindView(R.id.tv_order_time)
    TextView tvOrderTime;
    @BindView(R.id.tv_pay_time)
    TextView tvPayTime;
    @BindView(R.id.tv_right_click)
    TextView tvRightClick;
    @BindView(R.id.tv_left_click)
    TextView tvLeftClick;
    @BindView(R.id.tv_shopping)
    TextView tvShopping;
    @BindView(R.id.tv_after_state)
    TextView tvAfterState;
    @BindView(R.id.tv_after_state_des)
    TextView tvAfterStateDes;
    @BindView(R.id.ll_after_state)
    LinearLayout llAfterState;
    @BindView(R.id.ll_money_info)
    LinearLayout llMoneyInfo;
    @BindView(R.id.tv_copy)
    TextView tvCopy;
    private String orderCode;
    private TimeCounter mTimeCounter;
    /**
     * "订单状态 0,待支付|WAITPAY;1,已取消|CANCLEED;2,待发货|WAITSEND;3,待收货|WAITRECEIVED;5,已完成（待收货）；6,交易关闭|CLOSE",
     */
    private int showStatus;
    private OrderDetailBean.DataBean orderDetailBean;
    private AfterSaleDetailBean.DataBean afterDetailBean;
    private MineOrderPageBean.DataBean.ResultBean.ListBean orderBean;
    private Map<Object, Object> map = new HashMap<>();
    private float totalMontey;
    /**
     * 0 其他  1 ：退货售后详情
     */
    private int type;
    private String phone;


    public static void go2this(Activity activity, String orderCode, int type, int requestCode) {
        activity.startActivityForResult(new Intent(activity, OrderDetailActivity.class)
                .putExtra("orderCode", orderCode)
                .putExtra("type", type), requestCode);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        btnBack.setOnClickListener(clickListener);
        btnTitleRight.setOnClickListener(clickListener);
        tvRightClick.setOnClickListener(clickListener);
        tvLeftClick.setOnClickListener(clickListener);
        tvCopy.setOnClickListener(clickListener);
        orderCode = getIntent().getStringExtra("orderCode");
        type = getIntent().getIntExtra("type", 0);
        phone = getResources().getString(R.string.link_phone);
        if (type == 1) {
            map.put("id", orderCode);
            presenter.getOrderAfterDetail(map);
        } else {
            map.put("orderCode", orderCode);
            presenter.getOrderDetail(map);

        }
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_title_right:
                clickCallPhone();
                break;
            case R.id.tv_right_click:
                clickStatus(true);
                break;
            case R.id.tv_left_click:
                clickStatus(false);
                break;
            case R.id.tv_copy:
                copyInfo(tvOrderCode.getText().toString());
                break;
            default:
                break;
        }
    }


    private void copyInfo(String copyStr) {
        //获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", copyStr);
        // 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);
        LogAndToastUtil.toast("复制成功");
    }

    /**
     * 点击状态
     *
     * @param b 是否是右边点击
     */
    private void clickStatus(boolean b) {
        if (b) {
            if (type == 1) {
                switch (afterDetailBean.getProductStatus()) {
                    case 7:
                    case 10:
                    case 13://联系客服
                        clickCallPhone();
                        break;
                    case 9:
                    case 1://重新申请
                        AfterSaleApplyActivity.go2this(this,orderBean);
                        break;
                    default:
                        break;
                }
            } else {
                switch (showStatus) {
                    case 0://付款
                        PaymentActivity.go2this(this, orderCode, String.valueOf(totalMontey));
                        break;
                    case 2://提醒发货
                        LogAndToastUtil.toast("提醒成功,请耐心等待");
                        break;
                    case 3://确认收货
                        presenter.confirmOrder(orderCode);
                        break;
                    case 5://查看物流
                        LogisticsActivity.go2this(this,orderCode);
                        break;
                    case 6://再次购买
                        ConfirmOrderActivity.go2this(this, transShoppingCarList(orderDetailBean.getOrderList()), null);
                        break;
                    default:
                        break;
                }
            }

        } else {
            if (type == 1) {
                switch (afterDetailBean.getProductStatus()) {
                    case 7:
                    case 10:
                    case 13://撤销申请
                        map.clear();
                        map.put("id", orderCode);
                        presenter.getCancelAfterSale(map);
                        break;
                    case 9:
                        clickCallPhone();
                        break;
                    case 1://重新申请
                        AfterSaleApplyActivity.go2this(this,orderBean);
                        break;
                    default:break;
                }
            } else {
                switch (showStatus) {
                    case 5:
                    case 0:
                        //再次购买
                        if (getResources().getString(R.string.again_buy).equals(tvRightClick.getText().toString())) {
                            ConfirmOrderActivity.go2this(this, transShoppingCarList(orderDetailBean.getOrderList()), null);
                        } else {//取消订单
                            presenter.getOrderCancel(orderCode);
                        }
                        break;
                    case 3://查看物流
                        LogisticsActivity.go2this(this,orderCode);
                        break;
                    default:
                        break;
                }
            }

        }
    }

    private List<ShoppingCarBean.DataBean.ListResultsBean> transShoppingCarList(List<MineOrderPageBean.DataBean.ResultBean.ListBean> list) {
        List<ShoppingCarBean.DataBean.ListResultsBean> shoppingCarList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ShoppingCarBean.DataBean.ListResultsBean bean = new ShoppingCarBean.DataBean.ListResultsBean();
            MineOrderPageBean.DataBean.ResultBean.ListBean shoppingBean = list.get(i);
            bean.setBuyNum(shoppingBean.getProductCount());
            bean.setProductId(shoppingBean.getOrderProductId());
            bean.setPrice(shoppingBean.getProductPrice());
            bean.setProductName(shoppingBean.getName());
            bean.setSpData(shoppingBean.getProductSpec());
            bean.setProductUrl(shoppingBean.getProductPic());
            shoppingCarList.add(bean);
        }
        return shoppingCarList;
    }

    @Override
    public void getOrderDetailSuccess(MdlBaseHttpResp<OrderDetailBean> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK) {
            if (resp.getData().getData() != null && resp.getData().getData().getOrderList() != null) {
                initShoppingView(resp.getData().getData());
                rvOrder.setLayoutManager(new LinearLayoutManager(this));
                OrderDetailAdapter shoppingCarAdapter = new OrderDetailAdapter(this, resp.getData().getData().getOrderList());
                rvOrder.setAdapter(shoppingCarAdapter);
            }
        }
    }

    @Override
    public void getOrderAfterDetailSuccess(MdlBaseHttpResp<AfterSaleDetailBean> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK) {
            if (resp.getData().getData() != null) {
                afterDetailBean = resp.getData().getData();
                initAfterSaleView();
                List<MineOrderPageBean.DataBean.ResultBean.ListBean> list = new ArrayList<>();
                orderBean = new MineOrderPageBean.DataBean.ResultBean.ListBean();
                orderBean.setName(afterDetailBean.getName());
                orderBean.setProductPrice(afterDetailBean.getProductPrice());
                orderBean.setProductPic(afterDetailBean.getProductPic());
                orderBean.setProductSpec(afterDetailBean.getProductSpec());
                orderBean.setProductCount(afterDetailBean.getProductCount());
                orderBean.setOrderCode(afterDetailBean.getOrderCode());
                list.add(orderBean);
                rvOrder.setLayoutManager(new LinearLayoutManager(this));
                OrderDetailAdapter shoppingCarAdapter = new OrderDetailAdapter(this, list);
                rvOrder.setAdapter(shoppingCarAdapter);
            }
        }
    }

    private void initAfterSaleView() {
        tvShopping.setText("退货信息");
        relLocation.setVisibility(View.GONE);
        llAfterState.setVisibility(View.VISIBLE);
        llMoneyInfo.setVisibility(View.GONE);

        if(TextUtils.isEmpty(afterDetailBean.getAftersaleReason())){
            tvOrderCode.setText("退款原因：");
        }else {
            tvOrderCode.setText("退款原因：" + afterDetailBean.getAftersaleReason());
        }
        tvOrderTime.setText("退款金额：" + DateUtil.getTwoDotByFloat(afterDetailBean.getRefundAmount()));
        tvWater.setText("申请时间：" + afterDetailBean.getCancleTime());
        tvPayTime.setText("退款编号：" + afterDetailBean.getOrderCode());

        //1,已取消|已取消页面和撤销申请页面;7,申请退款|等待卖家处理页面;8,审核通过|商家同意页面;9,审核不通过|审核不通过页面;10,退款中|等待卖家处理页面;12,退款成功|商家同意页面;13,等待退款|等待卖家处理页面
        switch (afterDetailBean.getProductStatus()) {
            case 1://已取消 已取消页面和撤销申请页面
                tvState.setText("退款取消 " + afterDetailBean.getCancleTime());
                tvAfterState.setText("您已撤销了本次退款申请");
                tvAfterStateDes.setText("·如有问题仍需解决，售后保障期内，您可以重新发起申请");
                tvLeftClick.setText("重新申请");
                tvRightClick.setVisibility(View.GONE);
                tvLeftClick.setVisibility(View.VISIBLE);
                break;
            case 12://,退款成功|商家同意页面
            case 8://审核通过|商家同意页面
                tvState.setText("退款成功 "+afterDetailBean.getRefundSuccessTime());
                tvAfterState.setText("退款成功");
                tvAfterState.setText("·退款将会在24小时内退回您的账户，如果没到账请联系客服");
                tvLeftClick.setVisibility(View.GONE);
                tvRightClick.setVisibility(View.GONE);
                break;
            case 9://审核不通过|审核不通过页面
                tvState.setText("退款失败 "+afterDetailBean.getNotpassTime());
                tvAfterState.setText("退款失败");
                tvAfterState.setText("·如有问题仍需解决，售后保障期内，您可以重新发起申请或者直接联系客服");
                tvLeftClick.setVisibility(View.VISIBLE);
                tvLeftClick.setText("重新申请");
                tvRightClick.setText("联系客服");
                break;
            case 7://申请退款|等待卖家处理页面
            case 10://退款中|等待卖家处理页面
            case 13://等待退款|等待卖家处理页面
                tvState.setText("请等待商家处理");
                tvAfterState.setText("退款申请中，请耐心等待商家处理");
                tvAfterState.setText("·商家同意或超时未处理，系统将退钱给您。\\n·如果商家拒绝您的申请，您可以修改退款申请再次发起，商家会重新处理。");
                tvLeftClick.setVisibility(View.VISIBLE);
                tvLeftClick.setText("撤销申请");
                tvRightClick.setText("联系客服");
                break;
            default:
                break;
        }

    }

    private void initShoppingView(OrderDetailBean.DataBean data) {
        showStatus = data.getShowStatus();
        orderDetailBean = data;
        calculateMoney(data.getOrderList());
        tvShopping.setText("商品");
        switch (showStatus) {
            case 0://待支付
                tvRightClick.setText("付款");
                tvLeftClick.setVisibility(View.VISIBLE);
                tvLeftClick.setText("取消订单");
                long payOverTime = DateUtil.getLongTimeByStyle(data.getEndPayTime(), "yyyy-MM-dd HH:mm:ss");
                if (payOverTime > System.currentTimeMillis()) {
                    mTimeCounter = new TimeCounter(payOverTime - System.currentTimeMillis(), 1000, tvState, R.string.trans_close, 3, () -> {
                        tvRightClick.setText(getResources().getString(R.string.again_buy));
                        tvState.setText("交易关闭");
                        tvLeftClick.setVisibility(View.GONE);
                    });
                    mTimeCounter.start();
                } else {
                    tvRightClick.setText(getResources().getString(R.string.again_buy));
                    tvState.setText("交易关闭");
                    tvLeftClick.setVisibility(View.GONE);
                }
                break;
            case 2:
                tvState.setText("买家已付款");
                tvLeftClick.setVisibility(View.GONE);
                tvRightClick.setText("提醒发货");
                break;
            case 3:
                tvState.setText("订单已经发货 请耐心等待");
                tvLeftClick.setVisibility(View.VISIBLE);
                tvLeftClick.setText("查看物流");
                tvRightClick.setText("确认收货");
                break;
            case 5://已完成
                tvState.setText("交易完成");
                tvLeftClick.setVisibility(View.VISIBLE);
                tvLeftClick.setText("查看物流");
                tvRightClick.setText("再次购买");
                break;
            case 6:
            default:
                tvRightClick.setText(getResources().getString(R.string.again_buy));
                tvState.setText("交易关闭");
                tvLeftClick.setVisibility(View.GONE);
                break;
        }
        tvName.setText(data.getReceiverName());
        tvPhone.setText(data.getReceiverPhone());
        tvLocation.setText(data.getReceiverAddress());
        tvOrderCode.setText("订单号：" + orderCode);
        tvOrderTime.setText("下单时间：" + data.getCreateTime());
        if (TextUtils.isEmpty(data.getPayCode())) {
            tvWater.setVisibility(View.GONE);
        } else {
            tvWater.setVisibility(View.VISIBLE);
            tvWater.setText("交易流水：" + data.getPayCode());
        }
        if (TextUtils.isEmpty(data.getPayTime())) {
            tvPayTime.setVisibility(View.GONE);
        } else {
            tvPayTime.setVisibility(View.VISIBLE);
            tvPayTime.setText("支付时间：" + data.getPayTime());
        }
        tvCoupon.setText("-" + getResources().getString(R.string.chinese_money) + data.getTotalDiscountAmount());
    }


    private void calculateMoney(List<MineOrderPageBean.DataBean.ResultBean.ListBean> selectList) {
        totalMontey = 0;
        for (int i = 0; i < selectList.size(); i++) {
            MineOrderPageBean.DataBean.ResultBean.ListBean bean = selectList.get(i);
            if (bean.getProductCount() > 0) {
                totalMontey += Float.valueOf(bean.getProductCount()) * bean.getProductPrice();
            }
        }
        tvAllMoney.setText(getResources().getString(R.string.chinese_money) + DateUtil.getTwoDotByFloat(totalMontey));
        if (orderDetailBean != null && orderDetailBean.getTotalDiscountAmount() != 0) {
            totalMontey = totalMontey - orderDetailBean.getTotalDiscountAmount();
        }
        tvActualMoney.setText(getResources().getString(R.string.chinese_money) + DateUtil.getTwoDotByFloat(totalMontey));
    }


    @Override
    public void getMessageSuccess(MdlBaseHttpResp<DataBean> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK) {
            LogAndToastUtil.toast("操作成功");
            setResult(Activity.RESULT_OK);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        if (mTimeCounter != null) {
            mTimeCounter.onFinish();
            mTimeCounter.cancel();
        }
        super.onDestroy();
    }


    private void clickCallPhone() {
        if (TextUtils.isEmpty(phone)) {
            return;
        }
        PermissionUtil.checkPermission(
                this,
                MyPermissionConstant.CALL_PHONE,
                Manifest.permission.CALL_PHONE);
    }

    @Override
    public void permissionSuccess(int permissionReqCode) {
        super.permissionSuccess(permissionReqCode);
        switch (permissionReqCode) {
            case MyPermissionConstant.CALL_PHONE:
                new CallPhoneDialog(OrderDetailActivity.this,phone,new CallPhoneDialog.ClickListener(){
                    @Override
                    public void clickConfirm(int isMan) {
                        Intent intent = new Intent();
                        //设置拨打电话的动作
                        intent.setAction(Intent.ACTION_DIAL);
                        //设置拨打电话的号码
                        intent.setData(Uri.parse("tel:" + phone));
                        //开启打电话的意图
                        startActivity(intent);
                    }
                }).show();
                break;
            default:break;
        }
    }
}

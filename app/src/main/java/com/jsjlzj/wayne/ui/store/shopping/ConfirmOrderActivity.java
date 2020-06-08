package com.jsjlzj.wayne.ui.store.shopping;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.shopping.ShoppingCarAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.shopping.CommitOrderBean;
import com.jsjlzj.wayne.entity.shopping.CommitOrderBody;
import com.jsjlzj.wayne.entity.shopping.LocationListBean;
import com.jsjlzj.wayne.entity.shopping.MineCouponBean;
import com.jsjlzj.wayne.entity.shopping.ShoppingCarBean;
import com.jsjlzj.wayne.entity.shopping.ShoppingDetailBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.DateUtil;
import com.jsjlzj.wayne.utils.LogAndToastUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @ClassName: ConfirmOrderActivity
 * @Description: 确认订单
 * @Author: 曾海强
 * @CreateDate: 2020/04/28
 */
public class ConfirmOrderActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    private static final int REQUEST_CODE_SELECT_LOCATION = 10101;

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_location_detail)
    TextView tvLocationDetail;
    @BindView(R.id.rel_location)
    RelativeLayout relLocation;
    @BindView(R.id.rel_location_select)
    RelativeLayout relLocationSelect;
    @BindView(R.id.rv_order)
    RecyclerView rvOrder;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_select_discount)
    TextView tvSelectDiscount;
    @BindView(R.id.tv_freight)
    TextView tvFreight;
    @BindView(R.id.tv_freight_des)
    TextView tvFreightDes;
    @BindView(R.id.tv_all_money)
    TextView tvAllMoney;
    @BindView(R.id.tv_discounted)
    TextView tvDiscounted;
    @BindView(R.id.tv_commit_order)
    TextView tvCommitOrder;
    @BindView(R.id.ll_select_discount)
    LinearLayout llSelectDiscount;

    private List<ShoppingCarBean.DataBean.ListResultsBean> selectList;
    private MineCouponBean.DataBean couponBean;
    private LocationListBean.DataBean locationBean;
    private float totalMontey;
    private List<MineCouponBean.DataBean> conponList = new ArrayList<>();
    private Map<Object,Object> map = new HashMap<>();

    private String productJson;
    private int type;
    private int buyNum;

    public static void go2this(Activity activity, List<ShoppingCarBean.DataBean.ListResultsBean> selectList, MineCouponBean.DataBean bean) {
        activity.startActivity(new Intent(activity, ConfirmOrderActivity.class).putExtra("selectList", (Serializable) selectList)
                .putExtra("couponBean",bean));
    }

    //直接购买
    public static void go2this(Activity activity, int type, int buyNum, String productJson) {
        activity.startActivity(new Intent(activity, ConfirmOrderActivity.class).putExtra("type",type)
                .putExtra("buyNum",buyNum)
                .putExtra("productJson",productJson));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_confirm_order;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initTitle("确定订单");

        selectList = (List<ShoppingCarBean.DataBean.ListResultsBean>) getIntent().getSerializableExtra("selectList");
        if(selectList == null || selectList.size() <= 0){
            type = getIntent().getIntExtra("type",0);
            buyNum = getIntent().getIntExtra("buyNum",0);
            productJson = getIntent().getStringExtra("productJson");
            ShoppingDetailBean.DataBean shopping = JSONObject.parseObject(productJson, ShoppingDetailBean.DataBean.class);
            ShoppingCarBean.DataBean.ListResultsBean bean = new ShoppingCarBean.DataBean.ListResultsBean();
            bean.setBuyNum(buyNum);
            float price = 0;
            if(shopping.getSkuPromotionPrice() > 0 ){
                price = shopping.getSkuPromotionPrice();
            }else {
                price = shopping.getSkuPrice();
            }
            bean.setPrice(price*100);
            bean.setSkuId(shopping.getSkuId());
            bean.setProductId(shopping.getSkuId());
            bean.setProductName(shopping.getName());
            bean.setSpData(shopping.getSpData());
            bean.setProductUrl(shopping.getPic());
            selectList = new ArrayList<>();
            selectList.add(bean);
        }
        initCoupon();
//        couponBean = (EnableCouponBean.DataBean) getIntent().getSerializableExtra("couponBean");
        relLocationSelect.setOnClickListener(clickListener);
        relLocation.setOnClickListener(clickListener);
        llSelectDiscount.setOnClickListener(clickListener);
        tvCommitOrder.setOnClickListener(clickListener);
        presenter.getLocationList();
    }

    private void initCoupon(){
        initShowView();
        map.clear();
        for (int i = 0; i < selectList.size(); i++) {
            ShoppingCarBean.DataBean.ListResultsBean bean = selectList.get(i);
            bean.setSkuId(bean.getId());
        }
        map.put("products",selectList);
        presenter.getEnableCoupon(map);

    }
    private void initShowView() {
        totalMontey = 0;
        for (int i = 0; i < selectList.size(); i++) {
            ShoppingCarBean.DataBean.ListResultsBean bean = selectList.get(i);
            if (bean.getBuyNum() > 0) {
                totalMontey += bean.getPrice() * bean.getBuyNum();
            }
        }
        tvPrice.setText(getResources().getString(R.string.chinese_money) + DateUtil.getTwoDotByFloat(totalMontey));
        tvAllMoney.setText(getResources().getString(R.string.chinese_money) + DateUtil.getTwoDotByFloat(totalMontey));
        relLocationSelect.setVisibility(View.VISIBLE);
        relLocation.setVisibility(View.GONE);
        rvOrder.setLayoutManager(new LinearLayoutManager(this));
        ShoppingCarAdapter adapter = new ShoppingCarAdapter(this, selectList, 1);
        rvOrder.setAdapter(adapter);
        rvOrder.setNestedScrollingEnabled(false);
        rvOrder.setHasFixedSize(true);
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.rel_location:
            case R.id.rel_location_select:
                LocationManagerActivity.go2this(this, REQUEST_CODE_SELECT_LOCATION, 1);
                break;
            case R.id.ll_select_discount:
                LogAndToastUtil.log("logand日志"+conponList.size());
                if(conponList.size() > 0){
                    CouponListFragment.showDialog(getSupportFragmentManager(),conponList,bean -> {
                        if(totalMontey > bean.getMinPoint()){
                            couponBean = bean;
                            tvSelectDiscount.setText(couponBean.getName());
                            tvSelectDiscount.setTextColor(ContextCompat.getColor(this,R.color.color_f1404b));
                            tvDiscounted.setVisibility(View.VISIBLE);
                            tvDiscounted.setText("已优惠¥" + DateUtil.getTwoDotByFloat(couponBean.getAmount()));
                            tvAllMoney.setText(getResources().getString(R.string.chinese_money) + DateUtil.getTwoDotByFloat(totalMontey - couponBean.getAmount()));
                        }else {
                            LogAndToastUtil.toast("不符合使用条件，请重新选择");
                        }

                    });
                }
                break;
            case R.id.tv_commit_order:
                commitOrder();
                break;
            default:
                break;
        }
    }

    /**
     * 提交订单
     */
    private void commitOrder() {
        Map<Object,Object> map = new HashMap<>();
        List<CommitOrderBody> list = new ArrayList<>();
        for (int i = 0; i< selectList.size(); i++){
            ShoppingCarBean.DataBean.ListResultsBean bean = selectList.get(i);
            CommitOrderBody commitOrderBody = new CommitOrderBody();
            commitOrderBody.setBuyCount(bean.getBuyNum());
            commitOrderBody.setShoppingCarId(bean.getId());
            commitOrderBody.setProductId(bean.getProductId());
            list.add(commitOrderBody);
        }
        map.put("products", list);
        if(couponBean != null){
            map.put("couponReceiveId",couponBean.getId());
        }
        if(locationBean != null){
            map.put("userAddressId",locationBean.getId());
        }
        map.put("type",0);
        presenter.commitOrder2(map);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_LOCATION && resultCode == RESULT_OK) {
            locationBean = (LocationListBean.DataBean) data.getSerializableExtra("location");
            if(locationBean != null){
                relLocationSelect.setVisibility(View.GONE);
                relLocation.setVisibility(View.VISIBLE);
                tvName.setText(locationBean.getUserName());
                tvPhone.setText(locationBean.getPhone());
                tvLocation.setText(locationBean.getProvince()+" "+locationBean.getCity()+" "+locationBean.getDistrict());
                tvLocationDetail.setText(locationBean.getDetail());
            }
        }
    }


    @Override
    public void getLocationListSuccess(MdlBaseHttpResp<LocationListBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            if(resp.getData().getData() != null && resp.getData().getData().size() > 0){
                for (LocationListBean.DataBean location : resp.getData().getData()) {
                    if(location.getIsDefault() == 1){
                        locationBean = location;
                        relLocationSelect.setVisibility(View.GONE);
                        relLocation.setVisibility(View.VISIBLE);
                        tvName.setText(locationBean.getUserName());
                        tvPhone.setText(locationBean.getPhone());
                        tvLocation.setText(locationBean.getProvince()+" "+locationBean.getCity()+" "+locationBean.getDistrict());
                        tvLocationDetail.setText(locationBean.getDetail());
                        break;
                    }
                }
            }
        }
    }


    @Override
    public void commitOrder2Success(MdlBaseHttpResp<CommitOrderBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK && resp.getData().getData() != null){
            PaymentActivity.go2this(this,resp.getData().getData().getOrderCode(),
                    resp.getData().getData().getPayAmount());
            finish();
        }

    }

    @Override
    public void getEnableCouponListSuccess(MdlBaseHttpResp<MineCouponBean> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && resp.getData().getData() != null) {
            conponList = resp.getData().getData();
            LogAndToastUtil.log("logand日志"+conponList.size());
            if(conponList != null && conponList.size() > 0){
                llSelectDiscount.setVisibility(View.VISIBLE);
                tvDiscounted.setVisibility(View.VISIBLE);
            }else {
                llSelectDiscount.setVisibility(View.GONE);
                tvDiscounted.setVisibility(View.GONE);
            }

        }
    }

}

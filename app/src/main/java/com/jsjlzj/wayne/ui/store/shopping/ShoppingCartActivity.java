package com.jsjlzj.wayne.ui.store.shopping;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.shopping.ProductAdapter;
import com.jsjlzj.wayne.adapter.recycler.shopping.ShoppingCarAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.shopping.EnableCouponBean;
import com.jsjlzj.wayne.entity.shopping.ShoppingCarBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.CustomXRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName: ShoppingCartActivity
 * @Description: 购物车界面
 * @Author: 曾海强
 * @CreateDate: 2020/05/12
 */
public class ShoppingCartActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView, ShoppingCarAdapter.OnItemClickListener {

    @BindView(R.id.img_empty)
    ImageView imgEmpty;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    @BindView(R.id.img_all_select)
    ImageView imgAllSelect;
    @BindView(R.id.tv_all_select)
    TextView tvAllSelect;
    @BindView(R.id.tv_recommend)
    TextView tvRecommend;
    @BindView(R.id.rv_empty)
    RecyclerView rvEmpty;
    @BindView(R.id.rel_empty)
    RelativeLayout relEmpty;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_coupon)
    TextView tvCoupon;
    @BindView(R.id.img_open)
    ImageView imgOpen;
    @BindView(R.id.tv_discount_detail)
    TextView tvDiscountDetail;
    @BindView(R.id.rv_cart)
    CustomXRecyclerView rvCart;

    private ProductAdapter emptyAdapter;
    private ShoppingCarAdapter carAdapter;
    private Map<Object, Object> map = new HashMap();
    private boolean isAllSelect = false;
    private EnableCouponBean.DataBean curConponBean;
    private List<EnableCouponBean.DataBean> conponList = new ArrayList<>();
    private int couponId;


    public static void go2this(Activity activity) {
        activity.startActivity(new Intent(activity, ShoppingCartActivity.class));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_shopping_cart;
    }


    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }


    @Override
    protected void initViewAndControl() {
        initRightTitle("购物车", "管理");
        mRightTv.setVisibility(View.GONE);
//        emptyAdapter = new ProductAdapter(this, new ArrayList<>());
//        rvEmpty.setLayoutManager(new GridLayoutManager(this, 2));
//        rvEmpty.setAdapter(emptyAdapter);
        rvCart.setPullRefreshEnabled(false);
        rvCart.setLoadingMoreEnabled(false);
        carAdapter = new ShoppingCarAdapter(ShoppingCartActivity.this, new ArrayList<>(), 0);
        rvCart.setLayoutManager(new LinearLayoutManager(this));
        rvCart.setAdapter(carAdapter);
        carAdapter.setListener(this);
        presenter.getShoppingCarList();
    }


    @OnClick({R.id.tv_right_btn, R.id.img_all_select, R.id.tv_all_select, R.id.tv_buy, R.id.tv_discount_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_right_btn:
                break;
            case R.id.img_all_select:
            case R.id.tv_all_select:
                if (isAllSelect) {
                    isAllSelect = false;
                    carAdapter.setSelectData(false);
                    imgAllSelect.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cbx_unselect));
                    tvAllSelect.setText("全选");
                } else {
                    isAllSelect = true;
                    carAdapter.setSelectData(true);
                    imgAllSelect.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cbx_select));
                    tvAllSelect.setText("取消全选");
                }
                calculateMoney(carAdapter.getSelectList());
                break;
            case R.id.tv_buy:
                List<ShoppingCarBean.DataBean.ListResultsBean> selectList = carAdapter.getSelectList();
                if(selectList == null || selectList.size() <= 0){
                    LogAndToastUtil.toast("请选择想要购买的商品");
                    return;
                }
                ConfirmOrderActivity.go2this(this, selectList);
                break;
            case R.id.tv_discount_detail:
                DiscountDetailFragment.showDialog(getSupportFragmentManager(), conponList);
                break;
            default:
                break;
        }
    }

    @Override
    public void getShoppingCarListSuccess(MdlBaseHttpResp<ShoppingCarBean> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK) {
            if (resp.getData().getData() != null && resp.getData().getData().getListResults() != null) {
                tvMoney.setText(resp.getData().getData().getPrice());
                carAdapter.setData(resp.getData().getData().getListResults());
                calculateMoney(carAdapter.getSelectList());
            } else {
                showEmpty(R.id.rel_empty, 0, null);
            }
            presenter.getEnableCouponList();
        }
    }

    @Override
    public void onAddClick(ShoppingCarBean.DataBean.ListResultsBean bean) {
        map.clear();
        map.put("buyNum", bean.getBuyNum());
        map.put("id", bean.getId());
        map.put("productId", bean.getProductId());
        presenter.addShoppingCar(map);
    }

    @Override
    public void onDeleteClick(ShoppingCarBean.DataBean.ListResultsBean bean) {
        map.clear();
        if(bean.getBuyNum() == 0){
            map.put("id", bean.getProductId());
            presenter.deleteCar(map);
        }else {
            map.put("id", bean.getId());
            map.put("buyNum", bean.getBuyNum());
            map.put("productId", bean.getProductId());
            presenter.updateShoppingBynum(map);
        }

    }

    private void calculateMoney(List<ShoppingCarBean.DataBean.ListResultsBean> selectList) {
        float totalMontey = 0;
        for (int i = 0; i < selectList.size(); i++) {
            ShoppingCarBean.DataBean.ListResultsBean bean = selectList.get(i);
            if (bean.getBuyNum() > 0) {
                totalMontey += Float.valueOf(bean.getPrice()) * bean.getBuyNum();
            }
        }
        tvMoney.setText(getResources().getString(R.string.chinese_money) + totalMontey);
    }

    @Override
    public void onItemClick(ShoppingCarBean bean) {
    }

    @Override
    public void onTypeClick(ShoppingCarBean.DataBean.ListResultsBean bean) {

    }

    @Override
    public void onSelectClick() {
        calculateMoney(carAdapter.getSelectList());
    }

    @Override
    public void onDeleteItem(ShoppingCarBean.DataBean.ListResultsBean bean, int pos) {

    }


    @Override
    public void getEnableCouponListSuccess(MdlBaseHttpResp<EnableCouponBean> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && resp.getData().getData() != null) {
            conponList = resp.getData().getData();
            for (int i = 0; i < resp.getData().getData().size(); i++) {
                EnableCouponBean.DataBean bean = resp.getData().getData().get(i);
                if (bean.getId() == couponId) {
                    curConponBean = bean;
                    tvCoupon.setText("已优惠 ¥ " + bean.getAmount());
                    tvCoupon.setVisibility(View.VISIBLE);
                    imgOpen.setVisibility(View.VISIBLE);
                    tvDiscountDetail.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}

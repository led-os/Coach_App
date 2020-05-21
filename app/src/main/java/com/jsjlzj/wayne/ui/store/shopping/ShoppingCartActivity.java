package com.jsjlzj.wayne.ui.store.shopping;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.shopping.ProductAdapter;
import com.jsjlzj.wayne.adapter.recycler.shopping.ShoppingCarAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.DataBean;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.shopping.MineCouponBean;
import com.jsjlzj.wayne.entity.shopping.ShoppingCarBean;
import com.jsjlzj.wayne.entity.shopping.ShoppingPageBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.DateUtil;
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
public class ShoppingCartActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView, ShoppingCarAdapter.OnItemClickListener, DiscountDetailFragment.OnClickDialogListener {

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
    @BindView(R.id.rel_empty_1)
    RelativeLayout relEmpty;
    @BindView(R.id.rel_shopping_cart)
    RelativeLayout relShoppingCart;
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
    private MineCouponBean.DataBean curConponBean;
    private List<MineCouponBean.DataBean> conponList = new ArrayList<>();
    private List<ShoppingCarBean.DataBean.ListResultsBean> resultList = new ArrayList<>();
    private int couponId;
    private boolean isUpdate = false;


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
        emptyAdapter = new ProductAdapter(this, new ArrayList<>());
        rvEmpty.setLayoutManager(new GridLayoutManager(this, 2));
        rvEmpty.setAdapter(emptyAdapter);
        rvCart.setPullRefreshEnabled(false);
        rvCart.setLoadingMoreEnabled(false);
        carAdapter = new ShoppingCarAdapter(ShoppingCartActivity.this, resultList, 0);
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
                selectAllClick();
                break;
            case R.id.tv_buy:
                toBuyClick();
                break;
            case R.id.tv_discount_detail:
                DiscountDetailFragment.showDialog(getSupportFragmentManager(), curConponBean,carAdapter.getSelectList(),isAllSelect,this);
                break;
            default:
                break;
        }
    }

    private void toBuyClick() {
        List<ShoppingCarBean.DataBean.ListResultsBean> selectList = carAdapter.getSelectList();
        if(selectList == null || selectList.size() <= 0){
            LogAndToastUtil.toast("请选择想要购买的商品");
            return;
        }
        ConfirmOrderActivity.go2this(this, selectList,curConponBean);
    }

    private void selectAllClick() {
        if(!isAllSelect){
            isAllSelect = true;
            carAdapter.setSelectData(true);
            imgAllSelect.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cbx_select));
            tvAllSelect.setText("取消全选");
        }else {
            isAllSelect = false;
            carAdapter.setSelectData(false);
            imgAllSelect.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cbx_unselect));
            tvAllSelect.setText("全选");
        }
        calculateMoney(carAdapter.getSelectList());
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
            map.put("id", bean.getId());
            presenter.deleteCar(map);
        }else {
            map.put("id", bean.getId());
            map.put("buyNum", bean.getBuyNum());
            map.put("productId", bean.getProductId());
            isUpdate = true;
            calculateMoney(carAdapter.getSelectList());
            presenter.updateShoppingBynum(map);
        }

    }

    private void calculateMoney(List<ShoppingCarBean.DataBean.ListResultsBean> selectList) {
        float totalMontey = 0;
        if(carAdapter.getSelectList().size() == resultList.size()){
            isAllSelect = true;
            imgAllSelect.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cbx_select));
            tvAllSelect.setText("取消全选");
        }else {
            isAllSelect = false;
            imgAllSelect.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cbx_unselect));
            tvAllSelect.setText("全选");
        }
        //不显示优惠明细了
        if(selectList != null && selectList.size() > 0 && curConponBean != null){
            tvCoupon.setVisibility(View.GONE);
            imgOpen.setVisibility(View.GONE);
            tvDiscountDetail.setVisibility(View.GONE);
        }else {
            tvCoupon.setVisibility(View.GONE);
            imgOpen.setVisibility(View.GONE);
            tvDiscountDetail.setVisibility(View.GONE);
        }
        for (int i = 0; i < selectList.size(); i++) {
            ShoppingCarBean.DataBean.ListResultsBean bean = selectList.get(i);
            if (bean.getBuyNum() > 0) {
                totalMontey += Float.valueOf(bean.getPrice()) * bean.getBuyNum();
            }
        }
        if(curConponBean != null && tvCoupon.getVisibility() == View.VISIBLE){
            totalMontey = totalMontey - curConponBean.getAmount();
        }
        tvMoney.setText(getResources().getString(R.string.chinese_money) + DateUtil.getTwoDotByFloat(totalMontey));
    }

    @Override
    public void onItemClick(ShoppingCarBean bean) {}

    @Override
    public void onTypeClick(ShoppingCarBean.DataBean.ListResultsBean bean) {

    }

    @Override
    public void onSelectClick() {
        calculateMoney(carAdapter.getSelectList());
    }

    @Override
    public void onSelectClickDialog() {
        selectAllClick();
    }

    @Override
    public void onToBuyClick() {
        toBuyClick();
    }

    @Override
    public void onDeleteItem(ShoppingCarBean.DataBean.ListResultsBean bean, int pos) {}

    @Override
    public void getShoppingCarListSuccess(MdlBaseHttpResp<ShoppingCarBean> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && !isUpdate) {
            if (resp.getData().getData() != null && resp.getData().getData().getListResults() != null
            && resp.getData().getData().getListResults().size() > 0) {
                relEmpty.setVisibility(View.GONE);
                relShoppingCart.setVisibility(View.VISIBLE);
                couponId = resp.getData().getData().getCouponId();
                tvMoney.setText(resp.getData().getData().getPrice());
                resultList.clear();
                resultList.addAll(resp.getData().getData().getListResults());
                carAdapter.setData(resultList);
            } else {
                relEmpty.setVisibility(View.VISIBLE);
                relShoppingCart.setVisibility(View.GONE);
                map.clear();
                map.put(HttpConstant.PAGE_NO, 1);
                map.put(HttpConstant.PAGE_SIZE, HttpConstant.PAGE_SIZE_NUMBER);
                map.put("isRecommandStatus",0);
                presenter.getSearchProductList(map);
            }
        }
    }



    @Override
    public void getShoppingListSuccess(MdlBaseHttpResp<ShoppingPageBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            if(resp.getData().getData() != null && resp.getData().getData().getResult() != null){
                emptyAdapter.setData(resp.getData().getData().getResult());
            }
        }
    }

    @Override
    public void getMessageSuccess(MdlBaseHttpResp<DataBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            calculateMoney(carAdapter.getSelectList());
        }
    }
}

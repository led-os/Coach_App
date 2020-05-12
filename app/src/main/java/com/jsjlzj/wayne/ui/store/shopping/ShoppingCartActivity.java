package com.jsjlzj.wayne.ui.store.shopping;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.shopping.ProductAdapter;
import com.jsjlzj.wayne.adapter.recycler.shopping.ShoppingCarAdapter;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.shopping.ShoppingCarBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.widgets.CustomXRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

 /**
  *
  * @ClassName:      ShoppingCartActivity
  * @Description:    购物车界面
  * @Author:         曾海强
  * @CreateDate:     2020/05/12
  */
public class ShoppingCartActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView, ShoppingCarAdapter.OnItemClickListener {

    @BindView(R.id.img_empty)
    ImageView imgEmpty;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    @BindView(R.id.tv_recommend)
    TextView tvRecommend;
    @BindView(R.id.rv_empty)
    RecyclerView rvEmpty;
    @BindView(R.id.rel_empty)
    RelativeLayout relEmpty;
    @BindView(R.id.rv_cart)
    CustomXRecyclerView rvCart;

    private ProductAdapter emptyAdapter;
    private ShoppingCarAdapter carAdapter;
    private Map<Object,Object> map =new HashMap();


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

         carAdapter = new ShoppingCarAdapter(this, new ArrayList<>());
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
                break;
            case R.id.tv_all_select:
                break;
            case R.id.tv_buy:
                ConfirmOrderActivity.go2this(this);
                break;
            case R.id.tv_discount_detail:
                DiscountDetailFragment.showDialog(getSupportFragmentManager(),"");
                break;
            default:
                break;
        }
    }

     @Override
     public void getShoppingCarListSuccess(MdlBaseHttpResp<ShoppingCarBean> resp) {

     }

     @Override
     public void onAddClick(ShoppingCarBean.DataBean.ListResultsBean bean) {
         map.clear();
         map.put("buyNum",1);
         map.put("id",1000);
         map.put("productId",123);
         map.put("userId",456);
         presenter.addShoppingCar(map);
     }

     @Override
     public void onDeleteClick(ShoppingCarBean.DataBean.ListResultsBean bean) {
         map.clear();
         map.put("buyNum",1);
         map.put("id",1000);
         map.put("productId",123);
         map.put("userId",456);
         presenter.updateShoppingBynum(map);
     }

     @Override
     public void onItemClick(ShoppingCarBean bean) {

     }

     @Override
     public void onTypeClick(ShoppingCarBean.DataBean.ListResultsBean bean) {

     }

     @Override
     public void onSelectClick() {

     }

     @Override
     public void onDeleteItem(ShoppingCarBean.DataBean.ListResultsBean bean, int pos) {

     }
 }

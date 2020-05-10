package com.jsjlzj.wayne.ui.store.home;


import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.shopping.GroupProductAdapter;
import com.jsjlzj.wayne.adapter.recycler.shopping.NewHotProductAdapter;
import com.jsjlzj.wayne.adapter.recycler.shopping.ProductAdapter;
import com.jsjlzj.wayne.adapter.recycler.shopping.SecondSkillAdapter;
import com.jsjlzj.wayne.adapter.recycler.shopping.ShopClassAdapter;
import com.jsjlzj.wayne.adapter.recycler.shopping.ShoppTypeAdapter;
import com.jsjlzj.wayne.entity.store.home.BannerBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.ui.store.search.SearchShopActivity;
import com.jsjlzj.wayne.ui.store.shopping.ShoppingCartActivity;
import com.jsjlzj.wayne.ui.store.shopping.TimeSecondActivity;
import com.jsjlzj.wayne.widgets.LocalImageHolderView;
import com.jsjlzj.wayne.widgets.NestedRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @ClassName: TabItemShoppingFragment
 * @Description: 商城
 * @Author: 曾海强
 * @CreateDate: 2020/04/20
 */
public class TabItemShoppingFragment extends MVPBaseFragment<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView {


    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.img_info)
    ImageView imgInfo;
    @BindView(R.id.scroll_banner)
    ConvenientBanner scrollBanner;
    @BindView(R.id.rv_shop_type)
    NestedRecyclerView rvShopType;
    @BindView(R.id.tv_s_skill)
    TextView tvSSkill;
    @BindView(R.id.tv_h)
    TextView tvH;
    @BindView(R.id.tv_h_mao)
    TextView tvHMao;
    @BindView(R.id.tv_m)
    TextView tvM;
    @BindView(R.id.tv_m_mao)
    TextView tvMMao;
    @BindView(R.id.tv_s)
    TextView tvS;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.rv_s_shop)
    NestedRecyclerView rvSShop;
    @BindView(R.id.tv_new_product)
    TextView tvNewProduct;
    @BindView(R.id.tv_new_product_des)
    TextView tvNewProductDes;
    @BindView(R.id.rv_new_product)
    RecyclerView rvNewProduct;
    @BindView(R.id.tv_hot_product)
    TextView tvHotProduct;
    @BindView(R.id.tv_hot_product_des)
    TextView tvHotProductDes;
    @BindView(R.id.rv_hot_product)
    RecyclerView rvHotProduct;
    @BindView(R.id.tv_compose)
    TextView tvCompose;
    @BindView(R.id.tv_compose_more)
    TextView tvComposeMore;
    @BindView(R.id.rv_compose)
    NestedRecyclerView rvCompose;
    @BindView(R.id.rv_shop_class)
    RecyclerView rvShopClass;
    @BindView(R.id.rv_shop)
    RecyclerView rvShop;
    @BindView(R.id.rel_shopping_cart)
    RelativeLayout relShoppingCart;
    @BindView(R.id.tv_number)
    TextView tvNumber;


    private List<BannerBean> images = new ArrayList<>();

    public TabItemShoppingFragment() {
    }


    public static Fragment getInstance() {
        TabItemShoppingFragment fragment = new TabItemShoppingFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_tab_item_shopping;
    }

    @Override
    protected void initViewAndControl(View view) {
        llSearch.setOnClickListener(clickListener);
        imgInfo.setOnClickListener(clickListener);
        tvComposeMore.setOnClickListener(clickListener);
        tvSSkill.setOnClickListener(clickListener);
        relShoppingCart.setOnClickListener(clickListener);
        initRecycler();
        presenter.getHomeShoppingData();
    }

    private void initRecycler() {
        ShoppTypeAdapter  shoppTypeAdapter = new ShoppTypeAdapter(getActivity(),new ArrayList<>());
        rvShopType.setLayoutManager(new GridLayoutManager(getActivity(),5));
        rvShopType.setAdapter(shoppTypeAdapter);

        SecondSkillAdapter secondSkillAdapter = new SecondSkillAdapter(getActivity(),new ArrayList<>());
        rvSShop.setLayoutManager(new GridLayoutManager(getActivity(),4));
        rvSShop.setAdapter(secondSkillAdapter);

        NewHotProductAdapter newProductAdapter = new NewHotProductAdapter(getActivity(),new ArrayList<>());
        rvNewProduct.setLayoutManager(new GridLayoutManager(getActivity(),2));
        rvNewProduct.setAdapter(newProductAdapter);

        ShopClassAdapter shopClassAdapter = new ShopClassAdapter(getActivity(),new ArrayList<>());
        rvShopClass.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        rvShopClass.setAdapter(shopClassAdapter);

        NewHotProductAdapter hotProductAdapter = new NewHotProductAdapter(getActivity(),new ArrayList<>());
        rvHotProduct.setLayoutManager(new GridLayoutManager(getActivity(),2));
        rvHotProduct.setAdapter(hotProductAdapter);

        GroupProductAdapter groupProductAdapter = new GroupProductAdapter(getActivity(),new ArrayList<>());
        rvCompose.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        rvCompose.setAdapter(groupProductAdapter);

        rvShop.setHasFixedSize(true);
        rvShop.setNestedScrollingEnabled(false);
        ProductAdapter productAdapter = new ProductAdapter(getActivity(),new ArrayList<>());
        rvShop.setLayoutManager(new GridLayoutManager(getActivity(),2));
        rvShop.setAdapter(productAdapter);
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.ll_search:
                SearchShopActivity.go2this(getActivity());
                break;
            case R.id.img_info:
                break;
            case R.id.tv_compose_more:
                break;
            case R.id.tv_s_skill:
                TimeSecondActivity.go2this(getActivity());
                break;
            case R.id.rel_shopping_cart:
                ShoppingCartActivity.go2this(getActivity());
                break;
            default:
                break;
        }
    }

    @Override
    protected void fragment2Front() {

    }

    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
    }


    private void initBanner() {
        scrollBanner.setPages(
                new CBViewHolderCreator() {
                    @Override
                    public LocalImageHolderView createHolder(View itemView) {
                        return new LocalImageHolderView(itemView);
                    }

                    @Override
                    public int getLayoutId() {
                        return R.layout.item_localimage;
                    }

                }, images)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.bg_circle_ccfffff_6, R.drawable.bg_circle_4f9bfa_6})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(position -> {
//                    BannerBean bean = images.get(position);
//                    WebViewContainerActivity.go2this(getActivity(),bean.getTitle(),bean.getLink(), WebViewContainerFragment.TYPE_BANNER_LINK_URL);
                })
                .setCanLoop(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        scrollBanner.startTurning();
    }

    @Override
    public void onPause() {
        super.onPause();
        scrollBanner.stopTurning();
    }

}

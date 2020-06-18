package com.jsjlzj.wayne.ui.store.home;


import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.shopping.GroupProductAdapter;
import com.jsjlzj.wayne.adapter.recycler.shopping.NewHotProductAdapter;
import com.jsjlzj.wayne.adapter.recycler.shopping.ProductAdapter;
import com.jsjlzj.wayne.adapter.recycler.shopping.SecondSkillAdapter;
import com.jsjlzj.wayne.adapter.recycler.shopping.ShopClassAdapter;
import com.jsjlzj.wayne.adapter.recycler.shopping.ShoppTypeAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.shopping.HomeShoppingDataBean;
import com.jsjlzj.wayne.entity.shopping.ShoppingBean;
import com.jsjlzj.wayne.entity.shopping.ShoppingNumBean;
import com.jsjlzj.wayne.entity.shopping.ShoppingPageBean;
import com.jsjlzj.wayne.entity.store.home.BannerBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.ui.store.find.MoreLessonActivity;
import com.jsjlzj.wayne.ui.store.home.mine.MessageConnectActivity;
import com.jsjlzj.wayne.ui.store.search.SearchShopActivity;
import com.jsjlzj.wayne.ui.store.shopping.ShoppingCartActivity;
import com.jsjlzj.wayne.ui.store.shopping.TimeSecondActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.ChatView;
import com.jsjlzj.wayne.widgets.CustomXRecyclerView;
import com.jsjlzj.wayne.widgets.MoveLayout;
import com.jsjlzj.wayne.widgets.LocalImageHolderView;
import com.jsjlzj.wayne.widgets.MyDragView;
import com.jsjlzj.wayne.widgets.NestedRecyclerView;
import com.netease.nim.uikit.common.ToastHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @ClassName: TabItemShoppingFragment
 * @Description: 商城
 * @Author: 曾海强
 * @CreateDate: 2020/04/20
 */
public class TabItemShoppingFragment extends MVPBaseFragment<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView, ShopClassAdapter.OnItemClickListener, View.OnTouchListener, OnLoadMoreListener, OnRefreshListener {


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
    @BindView(R.id.rel_new)
    RelativeLayout relNew;
    @BindView(R.id.rel_hot)
    RelativeLayout relHot;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;


    private List<BannerBean> images = new ArrayList<>();
    private ShoppTypeAdapter shoppTypeAdapter;//商品类型适配器
    private SecondSkillAdapter secondSkillAdapter;//限时秒杀适配器
    private NewHotProductAdapter newProductAdapter;//最新产品适配器
    private NewHotProductAdapter hotProductAdapter;//热门产品适配器
    private GroupProductAdapter groupProductAdapter;//组合优惠适配器
    private ShopClassAdapter shopClassAdapter;//底部分类适配器
    private ProductAdapter productAdapter;//底部商品列表

    private int pageNo = 1;
    private int pageCount;
    private List<ShoppingBean> productList = new ArrayList<>();
    private List<HomeShoppingDataBean.DataBean.CategoryListBean> shopClassList = new ArrayList<>();
    private boolean isRefresh = true;
    private ChatView chatView;

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
//        relShoppingCart.setOnTouchListener(this);
        relNew.setOnClickListener(clickListener);
        relHot.setOnClickListener(clickListener);
        initRecycler();
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);
        initData();
//        chatView = new ChatView(this);
//        chatView.show();
//        chatView.setOnClickListener(view1 -> ShoppingCartActivity.go2this(getActivity()));
        relShoppingCart.setOnTouchListener(this);
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        //屏宽
        screenWidth = wm.getDefaultDisplay().getWidth();
        //屏高
        screenHeight = wm.getDefaultDisplay().getHeight();
    }


    private void initData() {
        presenter.getHomeShoppingData();
        pageNo = 1;
        Map<Object, Object> map = new HashMap<>();
        map.put("keywords", "首页全部好货");
        map.put(HttpConstant.PAGE_NO, pageNo);
        map.put(HttpConstant.PAGE_SIZE, HttpConstant.PAGE_SIZE_NUMBER);
        presenter.getSearchProductList(map);
    }

    private void initRecycler() {
        shoppTypeAdapter = new ShoppTypeAdapter(getActivity(), new ArrayList<>());
        rvShopType.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        shoppTypeAdapter.setListener(bean -> {
            MoreLessonActivity.go2this(getActivity(), bean.getName(), 8, bean.getCategoryId());
        });
        rvShopType.setAdapter(shoppTypeAdapter);

        secondSkillAdapter = new SecondSkillAdapter(getActivity(), new ArrayList<>());
        rvSShop.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        rvSShop.setAdapter(secondSkillAdapter);

        newProductAdapter = new NewHotProductAdapter(getActivity(), new ArrayList<>(), 1);
        rvNewProduct.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        rvNewProduct.setAdapter(newProductAdapter);

        shopClassAdapter = new ShopClassAdapter(getActivity(), shopClassList);
        rvShopClass.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        shopClassAdapter.setListener(this);
        rvShopClass.setAdapter(shopClassAdapter);

        hotProductAdapter = new NewHotProductAdapter(getActivity(), new ArrayList<>(), 2);
        rvHotProduct.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        rvHotProduct.setAdapter(hotProductAdapter);

        groupProductAdapter = new GroupProductAdapter(getActivity(), new ArrayList<>());
        rvCompose.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        rvCompose.setAdapter(groupProductAdapter);

        rvShop.setHasFixedSize(true);
        rvShop.setNestedScrollingEnabled(true);
        productAdapter = new ProductAdapter(getActivity(), new ArrayList<>());
        rvShop.setLayoutManager(new GridLayoutManager(getActivity(), 2));
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
                MessageConnectActivity.go2this(getActivity());
                break;
            case R.id.tv_compose_more:
                MoreLessonActivity.go2this(getActivity(), "组合优惠", 7, 0);
                break;
            case R.id.tv_s_skill:
                TimeSecondActivity.go2this(getActivity());
                break;
            case R.id.rel_shopping_cart:
                ShoppingCartActivity.go2this(getActivity());
                break;
            case R.id.rel_new:
                MoreLessonActivity.go2this(getActivity(), "最新产品", 9, 0);
                break;
            case R.id.rel_hot:
                MoreLessonActivity.go2this(getActivity(), "热卖产品", 10, 0);
                break;
            default:
                break;
        }
    }

    @Override
    protected void fragment2Front() {
        presenter.getShoppingNum();
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
    public void getHomeShoppingDataSuccess(MdlBaseHttpResp<HomeShoppingDataBean> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && resp.getData().getData() != null) {
            if (resp.getData().getData().getBannerList() != null) {
                images = resp.getData().getData().getBannerList();
                initBanner();
            }
            if (resp.getData().getData().getCategoryList() != null) {
                shoppTypeAdapter.setData(resp.getData().getData().getCategoryList());
                shopClassList.clear();
                shopClassList.add(new HomeShoppingDataBean.DataBean.CategoryListBean(0, "全部", "优选好货"));
                shopClassList.addAll(resp.getData().getData().getCategoryList());
                shopClassAdapter.setData(shopClassList);
            }
            if (resp.getData().getData().getActivityList() != null) {
                secondSkillAdapter.setData(resp.getData().getData().getActivityList());
            }
            if (resp.getData().getData().getNewProductList() != null) {
                newProductAdapter.setData(resp.getData().getData().getNewProductList());
            }
            if (resp.getData().getData().getHotProductList() != null) {
                hotProductAdapter.setData(resp.getData().getData().getHotProductList());
            }
            if (resp.getData().getData().getDiscountsProductList() != null) {
                groupProductAdapter.setData(resp.getData().getData().getDiscountsProductList());
            }
        }
    }


    @Override
    public void getCategoryTypeListSuccess(MdlBaseHttpResp<ShoppingPageBean> resp) {
        refreshLayout.finishLoadMore();
        refreshLayout.finishRefresh();
        if (resp.getStatus() == HttpConstant.R_HTTP_OK) {
            pageNo = resp.getData().getData().getPageNo();
            int totalCount = resp.getData().getData().getTotalCount();
            int a = totalCount % HttpConstant.PAGE_SIZE_NUMBER;
            if (a == 0) {
                pageCount = totalCount / HttpConstant.PAGE_SIZE_NUMBER;
            } else {
                pageCount = (totalCount / HttpConstant.PAGE_SIZE_NUMBER) + 1;
            }
            List<ShoppingBean> list = resp.getData().getData().getResult();
            if (isRefresh) {
                productList.clear();
            }
            if (list != null && list.size() > 0) {
                productList.addAll(list);
                productAdapter.setData(productList);
            }
        }
    }

    @Override
    public void getShoppingNumSuccess(MdlBaseHttpResp<ShoppingNumBean> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && resp.getData() != null) {
            tvNumber.setText(resp.getData().getData() + "");
//            chatView.setNum(resp.getData().getData() + "");
        }
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

    @Override
    public void onItemClick(HomeShoppingDataBean.DataBean.CategoryListBean bean) {
        Map<Object, Object> map = new HashMap<>();
        if (bean.getCategoryId() == 0) {
            map.put("keywords", "首页全部好货");
        } else {
            map.put("productCategoryId", bean.getCategoryId());
        }
        isRefresh = true;
        map.put(HttpConstant.PAGE_NO, 1);
        map.put(HttpConstant.PAGE_SIZE, HttpConstant.PAGE_SIZE_NUMBER);
        presenter.getSearchProductList(map);
    }


    private float downX;
    private float downY;
    private int screenWidth;
    private int screenHeight;
    private int[] temp = new int[2];

    //是否拖动
    private boolean isDrag = false;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int X = (int) event.getRawX();
        int Y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isDrag = false;
                downX = X;
                downY = Y;
                temp[0] = (int) event.getRawX();
                temp[1] = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                if(Math.abs(X - downX) > 10 || Math.abs(Y - downY) > 10){
                    isDrag = true;
                }else {
                    break;
                }
                if(Y > 72 && Y < screenHeight - 200){
                    int dx = X - temp[0];
                    int dy = Y - temp[1];

                    int left = v.getLeft() + dx;
                    int top = v.getTop() + dy;
                    int right = v.getRight() + dx;
                    int bottom = v.getBottom() + dy;
                    if (left < 0) {
                        left = 0;
                        right = left + v.getWidth();
                    }
                    if (right > screenWidth) {
                        right = screenWidth;
                        left = right - v.getWidth();
                    }
                    if (top < 72) {
                        top = 72;
                        bottom = top + v.getHeight();
                    }
                    if(top > screenHeight - 200){
                        top = screenHeight - 200;
                        bottom = top + v.getHeight();
                    }
                    v.layout(left, top, right, bottom);
                }
                temp[0] = (int) event.getRawX();
                temp[1] = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                if (Math.abs(event.getRawX() - downX) > 10 || Math.abs(event.getRawY() - downY) > 10 && Y > 72 && Y < screenHeight - 200) {
                    RelativeLayout.LayoutParams lpFeedback = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    int left = v.getLeft() ;
                    int top = v.getTop() ;
                    lpFeedback.setMargins(left, top, 0, 0);
                    v.setLayoutParams(lpFeedback);
                }
                break;
            default:
                break;
        }
        return isDrag;
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if (pageNo < pageCount) {
            isRefresh = false;
            pageNo++;
            Map<Object, Object> map = new HashMap<>();
            map.put("keywords", "首页全部好货");
            map.put(HttpConstant.PAGE_NO, pageNo);
            map.put(HttpConstant.PAGE_SIZE, HttpConstant.PAGE_SIZE_NUMBER);
            presenter.getSearchProductList(map);
        } else {
            refreshLayout.finishLoadMore();
            ToastHelper.showToast(getActivity(), getString(R.string.has_no_more_data));
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        isRefresh = true;
        initData();
    }
}

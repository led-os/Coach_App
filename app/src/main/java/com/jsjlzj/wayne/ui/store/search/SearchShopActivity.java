package com.jsjlzj.wayne.ui.store.search;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.shopping.ProductAdapter;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.keyboard.KeyboardUtil;
import com.jsjlzj.wayne.widgets.CustomXRecyclerView;
import com.jsjlzj.wayne.widgets.SearchBarView;

import java.util.ArrayList;

import butterknife.BindView;

public class SearchShopActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    @BindView(R.id.tv_comprehensive)
    TextView tvComprehensive;
    @BindView(R.id.tv_volume)
    TextView tvVolume;
    @BindView(R.id.tv_new)
    TextView tvNew;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.img_time_top)
    ImageView imgTimeTop;
    @BindView(R.id.img_time_bottom)
    ImageView imgTimeBottom;
    @BindView(R.id.rel_price)
    RelativeLayout relPrice;
    @BindView(R.id.rv_search_shop)
    CustomXRecyclerView rvSearchShop;


    private int searchType;

    private ProductAdapter productAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_search_shop;
    }
    public static void go2this(Activity context) {
        Intent intent = new Intent(context, SearchShopActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initSearchTitle();
        initSearchBar();
        initRecycler();
        tvComprehensive.setOnClickListener(clickListener);
        tvVolume.setOnClickListener(clickListener);
        tvNew.setOnClickListener(clickListener);
        relPrice.setOnClickListener(clickListener);
        mRightTv.setOnClickListener(clickListener);
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()){
            case R.id.tv_right_btn:
                finish();
                break;
            case R.id.tv_comprehensive:
                tvComprehensive.setTextColor(ContextCompat.getColor(this,R.color.color_f1404b));
                tvVolume.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                tvNew.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                tvPrice.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                imgTimeTop.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.triangle_up_normal));
                imgTimeBottom.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.triangle_down_pressed));
                break;
            case R.id.tv_volume:
                tvVolume.setTextColor(ContextCompat.getColor(this,R.color.color_f1404b));
                tvComprehensive.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                tvNew.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                tvPrice.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                imgTimeTop.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.triangle_up_normal));
                imgTimeBottom.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.triangle_down_pressed));
                break;
            case R.id.tv_new:
                tvNew.setTextColor(ContextCompat.getColor(this,R.color.color_f1404b));
                tvVolume.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                tvComprehensive.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                tvPrice.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                imgTimeTop.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.triangle_up_normal));
                imgTimeBottom.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.triangle_down_pressed));
                break;
            case R.id.rel_price:
                tvPrice.setTextColor(ContextCompat.getColor(this,R.color.color_f1404b));
                tvVolume.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                tvComprehensive.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                tvNew.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                if(searchType == 4){

                    imgTimeTop.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.triangle_up_normal));
                    imgTimeBottom.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.triangle_down_pressed));
                }else {
                    imgTimeBottom.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.triangle_up_normal));
                    imgTimeTop.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.triangle_down_pressed));
                }
                break;
        }
    }

    private void initRecycler() {
        productAdapter = new ProductAdapter(this,new ArrayList<>());
        rvSearchShop.setLayoutManager(new GridLayoutManager(this,2));
        rvSearchShop.setAdapter(productAdapter);
    }

    private void initSearchBar() {
        mSearchBar.setOnEditTextChangeListener(new SearchBarView.EditTextCallback() {
            @Override
            public void onEditTextChange(String str) {}

            @Override
            public void onEditFinish(String str) {}

            @Override
            public void onClickSoftWare(String str) {
                mSearchBar.getSearchEditText().setText(str);
                mSearchBar.getSearchEditText().setSelection(str.length());
                KeyboardUtil.closeKeyboard(tvPrice, SearchShopActivity.this);
            }
        });
    }
}

package com.jsjlzj.wayne.ui.store.shopping;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.shopping.EnableCouponBean;
import com.jsjlzj.wayne.entity.shopping.ShoppingCarBean;
import com.jsjlzj.wayne.utils.DateUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName: DiscountDetailFragment
 * @Description: 优惠明细
 * @Author: 曾海强
 * @CreateDate: 2020/04/28
 */
public class DiscountDetailFragment extends DialogFragment {

    private static final String TAG = "discountDetail";
    private static final String COUPON_DATA = "coupon_data";
    private static final String SHOPPING_LIST = "shopping_list";
    private static final String ALL_SELECT = "all_select";
    @BindView(R.id.img_close)
    ImageView imgClose;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_discount)
    TextView tvDiscount;
    @BindView(R.id.tv_discount_total)
    TextView tvDiscountTotal;
    @BindView(R.id.tv_freight)
    TextView tvFreight;
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    @BindView(R.id.ll_coupon)
    LinearLayout llCoupon;
    @BindView(R.id.ll_discount)
    LinearLayout llDiscount;
    @BindView(R.id.img_all_select)
    ImageView imgAllSelect;
    @BindView(R.id.tv_all_select)
    TextView tvAllSelect;
    @BindView(R.id.tv_coupon)
    TextView tvCoupon;
    @BindView(R.id.img_open)
    ImageView imgOpen;
    @BindView(R.id.tv_discount_detail)
    TextView tvDiscountDetail;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    private EnableCouponBean.DataBean couponBean;
    private List<ShoppingCarBean.DataBean.ListResultsBean> resultList;
    private boolean isAllSelect;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = new Dialog(Objects.requireNonNull(getContext()));
        @SuppressLint("InflateParams")
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_discount_detail, null);
        dialog.setContentView(rootView);
        ButterKnife.bind(this, rootView);
        couponBean = (EnableCouponBean.DataBean) getArguments().getSerializable(COUPON_DATA);
        resultList = (List<ShoppingCarBean.DataBean.ListResultsBean>) getArguments().getSerializable(SHOPPING_LIST);
        isAllSelect = getArguments().getBoolean(ALL_SELECT);
        initView();
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.getAttributes().windowAnimations = R.style.DialogAnimation;
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.gravity = Gravity.BOTTOM;
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(lp);
        }
        return dialog;
    }

    private void initView() {
        float totalMoney = 0;
        if (resultList.size() > 0) {
            for (ShoppingCarBean.DataBean.ListResultsBean bean : resultList) {
                if (bean.getBuyNum() > 0) {
                    totalMoney += bean.getBuyNum() * bean.getPrice();
                }
            }
            tvPrice.setText(getActivity().getResources().getString(R.string.chinese_money) + DateUtil.getTwoDotByFloat(totalMoney));
        }
        if (couponBean != null) {
            llCoupon.setVisibility(View.VISIBLE);
            llDiscount.setVisibility(View.VISIBLE);
            tvDiscount.setText(couponBean.getName());
            imgOpen.setVisibility(View.VISIBLE);
            tvDiscountDetail.setVisibility(View.VISIBLE);
            tvCoupon.setVisibility(View.VISIBLE);
            tvCoupon.setText("已优惠 ¥ " + DateUtil.getTwoDotByFloat(couponBean.getAmount()));
            tvDiscountTotal.setText("- ¥" + DateUtil.getTwoDotByFloat(couponBean.getAmount()));
            totalMoney = totalMoney - couponBean.getAmount();
        } else {
            imgOpen.setVisibility(View.GONE);
            tvDiscountDetail.setVisibility(View.GONE);
            tvCoupon.setVisibility(View.GONE);
            llCoupon.setVisibility(View.GONE);
            llDiscount.setVisibility(View.GONE);
        }
        tvTotalMoney.setText(getActivity().getResources().getString(R.string.chinese_money) + DateUtil.getTwoDotByFloat(totalMoney));
        tvMoney.setText(tvTotalMoney.getText());
        if(isAllSelect){
            imgAllSelect.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.cbx_select));
            tvAllSelect.setText("取消全选");
        }else {
            imgAllSelect.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.cbx_unselect));
            tvAllSelect.setText("全选");
        }
    }


    public static void showDialog(FragmentManager fm, EnableCouponBean.DataBean couponList,
                                  List<ShoppingCarBean.DataBean.ListResultsBean> resultList,boolean isAllSelect, OnClickDialogListener listener) {
        DiscountDetailFragment fragment = ((DiscountDetailFragment) fm.findFragmentByTag(TAG));
        if (fragment == null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(COUPON_DATA, couponList);
            bundle.putSerializable(SHOPPING_LIST, (Serializable) resultList);
            bundle.putBoolean(ALL_SELECT,isAllSelect);
            fragment = new DiscountDetailFragment();
            fragment.setListener(listener);
            fragment.setArguments(bundle);
        }
        if (!fragment.isAdded()) {
            fragment.show(fm, TAG);
        }
    }

    public static void hideDialog(FragmentManager fm) {
        DiscountDetailFragment fragment = ((DiscountDetailFragment) fm.findFragmentByTag(TAG));
        if (fragment != null && fragment.isAdded()) {
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.remove(fragment);
            transaction.commitAllowingStateLoss();
        }
    }


    @OnClick({R.id.img_close, R.id.img_all_select, R.id.tv_all_select, R.id.tv_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_close:
                dismissAllowingStateLoss();
                break;
            case R.id.img_all_select:
            case R.id.tv_all_select:
                if (!isAllSelect) {
                    isAllSelect = true;
                    imgAllSelect.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.cbx_select));
                    tvAllSelect.setText("取消全选");
                    tvDiscount.setText(couponBean.getName());
                    float totalMoney = 0;
                    if (resultList.size() > 0) {
                        for (ShoppingCarBean.DataBean.ListResultsBean bean : resultList) {
                            if (bean.getBuyNum() > 0) {
                                totalMoney += bean.getBuyNum() * bean.getPrice();
                            }
                        }
                    }
                    tvDiscountTotal.setText("- ¥" + DateUtil.getTwoDotByFloat(couponBean.getAmount()));
                    tvTotalMoney.setText(getActivity().getResources().getString(R.string.chinese_money) + DateUtil.getTwoDotByFloat(totalMoney - couponBean.getAmount()));
                    tvMoney.setText(tvTotalMoney.getText().toString());
                } else {
                    isAllSelect = false;
                    imgAllSelect.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.cbx_unselect));
                    tvAllSelect.setText("全选");
                    tvDiscount.setText(couponBean.getName());
                    tvDiscountTotal.setText("- ¥0.00");
                    tvTotalMoney.setText(getActivity().getResources().getString(R.string.chinese_money) + "0.00");
                    tvMoney.setText(tvTotalMoney.getText().toString());
                }
                if (listener != null) {
                    listener.onSelectClickDialog();
                }
                break;
            case R.id.tv_buy:
                if (listener != null) {
                    listener.onToBuyClick();
                }
                break;
            default:
                break;
        }

    }

    private OnClickDialogListener listener;

    public void setListener(OnClickDialogListener listener) {
        this.listener = listener;
    }

    public interface OnClickDialogListener {

        void onSelectClickDialog();

        void onToBuyClick();
    }
}

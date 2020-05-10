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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jsjlzj.wayne.R;

import java.util.Objects;

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
    private static final String DATA = "data";
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
    private String bean;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = new Dialog(Objects.requireNonNull(getContext()));
        @SuppressLint("InflateParams")
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_discount_detail, null);
        ButterKnife.bind(getContext(), rootView);
        dialog.setContentView(rootView);
        bean = getArguments().getString(DATA);
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


    public static void showDialog(FragmentManager fm, String bean) {
        DiscountDetailFragment fragment = ((DiscountDetailFragment) fm.findFragmentByTag(TAG));
        if (fragment == null) {
            Bundle bundle = new Bundle();
            bundle.putString(DATA, bean);
            fragment = new DiscountDetailFragment();
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


    @OnClick(R.id.img_close)
    public void onViewClicked() {
        dismissAllowingStateLoss();
    }
}

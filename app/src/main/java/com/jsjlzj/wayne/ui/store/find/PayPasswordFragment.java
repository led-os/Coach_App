package com.jsjlzj.wayne.ui.store.find;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.utils.keyboard.KeyboardUtil;
import com.jsjlzj.wayne.widgets.PayPsdInputView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author zenghaiqiang
 * @date 2019/11/3
 * 订单列表fragment
 */
public class PayPasswordFragment extends DialogFragment {

    public static final String TAG = PayPasswordFragment.class.getSimpleName();
    PayPsdInputView.onPasswordListener clickListener;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.img_right)
    ImageView imgRight;
    @BindView(R.id.img_type)
    ImageView imgType;
    @BindView(R.id.tv_welfare)
    TextView tvWelfare;
    @BindView(R.id.pay_psd)
    public PayPsdInputView payPsd;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        Dialog dialog = new Dialog(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_pay_password, null, false);
        ButterKnife.bind(this, view);
        int type = getArguments().getInt(ExtraConstant.EXTRA_SHOW_TYPE);
        tvPrice.setText("" + getArguments().getFloat(ExtraConstant.EXTRA_PRICE, 0f));
        if(type == 4){
//            imgType.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.icon_jidian));
//            tvWelfare.setText(getResources().getString(R.string.jidian));
        }else {
//            imgType.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.icon_welfare_pay));
//            tvWelfare.setText(getResources().getString(R.string.welfare_pay));
        }
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = window.getAttributes();
        window.setAttributes(lp);
        payPsd.setComparePassword(clickListener);
        payPsd.setFocusable(true);
        payPsd.setFocusableInTouchMode(true);
        payPsd.setInputType(InputType.TYPE_CLASS_NUMBER);
        payPsd.requestFocus();
        KeyboardUtil.openKeyboard(payPsd, getActivity());
        return dialog;
    }

    public void setListener(PayPsdInputView.onPasswordListener listener) {
        clickListener = listener;
    }


    @OnClick(R.id.img_close)
    public void onViewClicked() {
        payPsd.setFocusable(false);
        payPsd.setFocusableInTouchMode(false);
        KeyboardUtil.closeKeyboard(payPsd, getActivity());
        dismissAllowingStateLoss();
    }

    @Override
    public void onStop() {
        payPsd.setFocusable(false);
        payPsd.setFocusableInTouchMode(false);
        KeyboardUtil.closeKeyboard(payPsd, getActivity());
        super.onStop();
    }

    public static PayPasswordFragment showFragment(FragmentManager manager, float totalPrice, int type, PayPsdInputView.onPasswordListener typeChangeListener) {
//        PayPasswordFragment fragment = ((PayPasswordFragment) manager.findFragmentByTag(TAG));
//        if(fragment == null){
        PayPasswordFragment fragment = new PayPasswordFragment();
        Bundle bundle = new Bundle();
        bundle.putFloat(ExtraConstant.EXTRA_PRICE, totalPrice);
        bundle.putInt(ExtraConstant.EXTRA_SHOW_TYPE,type);
        fragment.setArguments(bundle);
        fragment.setListener(typeChangeListener);
        fragment.show(manager, TAG);
//        }
//        if(!fragment.isAdded()){
//            fragment.setListener(typeChangeListener);
//            fragment.show(manager, TAG);
//        }
        return fragment;
    }

    public static void hideDialog(FragmentManager fm) {
        PayPasswordFragment fragment = ((PayPasswordFragment) fm.findFragmentByTag(TAG));
        if (fragment != null && fragment.isAdded()) {
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.remove(fragment);
            transaction.commitAllowingStateLoss();
        }
    }
}

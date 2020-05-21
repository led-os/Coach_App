package com.jsjlzj.wayne.ui.store.shopping;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.mine.CouponAdapter;
import com.jsjlzj.wayne.entity.shopping.MineCouponBean;
import com.jsjlzj.wayne.entity.shopping.ShoppingCarBean;
import com.jsjlzj.wayne.entity.trainer.InvitationBean;
import com.jsjlzj.wayne.utils.DateUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

 /**
  *  
  * @ClassName:      确认订单优惠券列表
  * @Description:    java类作用描述
  * @Author:         曾海强
  * @CreateDate:      
  */
public class CouponListFragment extends DialogFragment {

    private static final String TAG = "discountDetail";
    private static final String COUPON_DATA = "coupon_data";
    private static final String SHOPPING_LIST = "shopping_list";
    private static final String ALL_SELECT = "all_select";
    @BindView(R.id.img_close)
    ImageView imgClose;
    @BindView(R.id.rv_coupon)
    RecyclerView rvCoupon;

    private List<MineCouponBean.DataBean> couponList ;
    private boolean isAllSelect;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = new Dialog(Objects.requireNonNull(getContext()));
        @SuppressLint("InflateParams")
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_coupon_list, null);
        dialog.setContentView(rootView);
        ButterKnife.bind(this, rootView);
        couponList = (List<MineCouponBean.DataBean>) getArguments().getSerializable(COUPON_DATA);
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
        rvCoupon.setLayoutManager(new LinearLayoutManager(getActivity()));
        CouponAdapter adapter = new CouponAdapter(getActivity(),couponList);
        adapter.setListener(bean -> {
            if(listener != null){
                listener.onSelectClickDialog(bean);
                dismissAllowingStateLoss();
            }
        });
        rvCoupon.setAdapter(adapter);
    }


    public static void showDialog(FragmentManager fm,
                                  List<MineCouponBean.DataBean> resultList, OnClickDialogListener listener) {
        CouponListFragment fragment = ((CouponListFragment) fm.findFragmentByTag(TAG));
        if (fragment == null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(COUPON_DATA, (Serializable) resultList);
            fragment = new CouponListFragment();
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


    @OnClick({R.id.img_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_close:
                dismissAllowingStateLoss();
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

        void onSelectClickDialog(MineCouponBean.DataBean bean);

    }
}

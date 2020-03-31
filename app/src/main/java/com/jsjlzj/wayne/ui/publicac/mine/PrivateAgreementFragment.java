package com.jsjlzj.wayne.ui.publicac.mine;


import android.app.Dialog;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.ui.MyApp;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author zenghaiqiang
 * @date 2019/12/20
 * 描述：隐私政策弹框
 */
public class PrivateAgreementFragment extends DialogFragment {

    public static final String TITLE = "title";
    public static final String TYPE = "type";
    public static final String TAG = "private_agreement_fragment";
    private OnPrivateListener clickListener;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_commit)
    TextView tvCommit;


    @Override
    @NotNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        Dialog dialog = new Dialog(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_private_agreement, null, false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.color.transparent));
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        String title = bundle.getString(TITLE);
        int type = bundle.getInt(TYPE);
        String leftStr = bundle.getString(ExtraConstant.DIALOG_LEFT);
        String rightStr = bundle.getString(ExtraConstant.DIALOG_RIGHT);
        setData(title,leftStr,rightStr,type);
        dialog.setOnKeyListener((dialogInterface, keyCode, keyEvent) -> keyCode == KeyEvent.KEYCODE_BACK);
        return dialog;
    }



    public void setData(String title,String left,String right,int type){
        tvContent.setText(title);
        tvContent.getPaint().setFakeBoldText(true);
        int titleColor = ContextCompat.getColor(MyApp.getApp().getApplicationContext(),R.color.color_131720);
        int redColor = ContextCompat.getColor(MyApp.getApp().getApplicationContext(),R.color.color_1890FF);
//        int redColor = ThemeAttrsUtils.getColorTextSearch(getActivity());
        String[] strings = new String[3];
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if(type == 0){
            strings[0] = getResources().getString(R.string.private_user_read);
            strings[1] = getResources().getString(R.string.user_private);
            strings[2] = getResources().getString(R.string.detail_info_agree);
        }else {
            strings[0] = getResources().getString(R.string.hint_about_user_private);
            strings[1] = getResources().getString(R.string.user_private);
            strings[2] = getResources().getString(R.string.no_use_app);
        }
        for (int i = 0; i < strings.length; i++) {
            String text = strings[i];
            SpannableStringBuilder spannableStringBuilder1 = new SpannableStringBuilder(text);
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    if(clickListener != null){
                        clickListener.onPrivateClick();
                    }
                }
            };
            if (i % 2 == 0) {
                spannableStringBuilder1.setSpan(new ForegroundColorSpan(titleColor), 0, text.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else {
                spannableStringBuilder1.setSpan(clickableSpan, 0, text.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableStringBuilder1.setSpan(new ForegroundColorSpan(redColor), 0, text.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            spannableStringBuilder.append(spannableStringBuilder1);
        }
        tvDesc.setMovementMethod(LinkMovementMethod.getInstance());
        tvDesc.setText(spannableStringBuilder);
        tvCancel.setText(left);
        tvCommit.setText(right);
    }

    public void setListener(OnPrivateListener listener) {
        clickListener = listener;
    }

    @OnClick({R.id.tv_cancel, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                if(clickListener != null){
                    clickListener.onCancleClick();
                }
                break;
            case R.id.tv_commit:
                if (clickListener != null) {
                    clickListener.onConfirmClick();
                }
                break;
            default:
                break;
        }
    }

    public interface OnPrivateListener {

        void onCancleClick();

        void onConfirmClick();

        void onPrivateClick();
    }



    /**
     * 显示选择条件的fragment
     * @param fm
     * @param title
     * @param type
     * @param listener
     */
    public static PrivateAgreementFragment showDialog(FragmentManager fm, String title, String left, String right, int type, OnPrivateListener listener) {
        PrivateAgreementFragment fragment = ((PrivateAgreementFragment) fm.findFragmentByTag(TAG));
        if (fragment == null) {
            Bundle bundle = new Bundle();
            bundle.putInt(TYPE, type);
            bundle.putString(ExtraConstant.DIALOG_LEFT,left);
            bundle.putString(ExtraConstant.DIALOG_RIGHT,right);
            bundle.putString(TITLE,title);
            fragment = new PrivateAgreementFragment();
            fragment.setArguments(bundle);
        }
        if(!fragment.isAdded()){
            fragment.setListener(listener);
            fragment.show(fm, TAG);
        }
        return fragment;
    }

    public static void hideDialog(FragmentManager fm) {
        PrivateAgreementFragment fragment = ((PrivateAgreementFragment) fm.findFragmentByTag(TAG));
        if (fragment != null && fragment.isAdded()) {
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.remove(fragment);
            transaction.commitAllowingStateLoss();
        }
    }
}

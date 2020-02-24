package com.jsjlzj.wayne.ui.publicac.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.ExtraConstant;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author zenghaiqiang
 * @date 2020/2/19
 * 描述：提示弹框
 */
public class ZanFragment extends DialogFragment {

    public static final String TAG = ZanFragment.class.getSimpleName();
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.img_zan)
    ImageView imgZan;
    @BindView(R.id.tv_zan)
    TextView tvZan;


    @Override
    @NotNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        Dialog dialog = new Dialog(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_popup_dialog, null, false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.color.transparent));
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        tvZan.setText(bundle.getString(ExtraConstant.EXTRA_ZAN_NUMBER));
        tvContent.getPaint().setFakeBoldText(true);
        tvContent.setText(bundle.getString(ExtraConstant.EXTRA_NAME));
        dialog.setOnKeyListener((dialogInterface, keyCode, keyEvent) -> keyCode == KeyEvent.KEYCODE_BACK);
        return dialog;
    }


    @OnClick({R.id.tv_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_close:
                dismissAllowingStateLoss();
                break;
            default:
                break;
        }
    }

    public static void showDialog(FragmentManager fm, String name, String zanNumber) {
        ZanFragment fragment = ((ZanFragment) fm.findFragmentByTag(TAG));
        if (fragment == null) {
            Bundle bundle = new Bundle();
            bundle.putString(ExtraConstant.EXTRA_ZAN_NUMBER, zanNumber);
            bundle.putString(ExtraConstant.EXTRA_NAME,name);
            fragment = new ZanFragment();
            fragment.setArguments(bundle);
        }
        if(!fragment.isAdded()){
            fragment.show(fm, TAG);
        }
    }
}

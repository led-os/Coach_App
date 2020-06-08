package com.jsjlzj.wayne.ui.trainer.position;

import android.app.Dialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.publicac.dialog.ZanFragment;
import com.jsjlzj.wayne.ui.store.attestation.AttestationInfoActivity;
import com.jsjlzj.wayne.ui.trainer.personal.MasterCardActivity;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName: GotoFinishInfoFragment
 * @Description: 完善资料
 * @Author: 曾海强
 * @CreateDate:
 */
public class GotoFinishInfoFragment extends DialogFragment {

    public static final String TAG = ZanFragment.class.getSimpleName();
    @BindView(R.id.tv_go_finish)
    TextView tvGoFInish;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_hint)
    TextView tvHint;
    @BindView(R.id.tv_hint_1)
    TextView tvHint1;
    /**
     * 0 ：教练端完善信息   1 ：门店端完善信息
     */
    private int type;

    @Override
    @NotNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        Dialog dialog = new Dialog(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_goto_finish_info, null, false);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.color.transparent));
        ButterKnife.bind(this, view);
        type = getArguments().getInt("type", 0);
        if(type == 0){
            tvTitle.setText("您还未完善个人信息");
            tvHint.setText("完善个人信息");
            tvHint1.setText("可以发现更多的职位");
        }else {
            tvTitle.setText("您还未完善俱乐部信息");
            tvHint.setText("完善俱乐部信息");
            tvHint1.setText("可以发现更多的达人");
        }
        dialog.setOnKeyListener((dialogInterface, keyCode, keyEvent) -> keyCode == KeyEvent.KEYCODE_BACK);
        return dialog;
    }


    @OnClick({R.id.tv_go_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_go_finish:
                if(type == 0){
                    MasterCardActivity.go2this(getActivity());
                }else {
                    AttestationInfoActivity.go2this(getActivity(),2);
                }
                dismissAllowingStateLoss();
                break;
            default:
                break;
        }
    }

    public static void showDialog(FragmentManager fm, int type) {
        GotoFinishInfoFragment fragment = ((GotoFinishInfoFragment) fm.findFragmentByTag(TAG));
        if (fragment == null) {
            Bundle bundle = new Bundle();
            bundle.putInt("type", type);
            fragment = new GotoFinishInfoFragment();
            fragment.setArguments(bundle);
        }
        if (!fragment.isAdded()) {
            fragment.show(fm, TAG);
        }
    }
}

package com.jsjlzj.wayne.ui.trainer.position;

import android.app.Dialog;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.publicac.dialog.ZanFragment;
import com.jsjlzj.wayne.ui.trainer.personal.MasterCardActivity;

import org.jetbrains.annotations.NotNull;
import java.util.Objects;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

 /**
  *
  * @ClassName:      GotoFinishInfoFragment
  * @Description:    完善资料
  * @Author:         曾海强
  * @CreateDate:
  */
public class GotoFinishInfoFragment extends DialogFragment {

    public static final String TAG = ZanFragment.class.getSimpleName();
    @BindView(R.id.tv_go_finish)
    TextView tvGoFInish;


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
        dialog.setOnKeyListener((dialogInterface, keyCode, keyEvent) -> keyCode == KeyEvent.KEYCODE_BACK);
        return dialog;
    }


    @OnClick({R.id.tv_go_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_go_finish:
                MasterCardActivity.go2this(getActivity());
                dismissAllowingStateLoss();
                break;
            default:
                break;
        }
    }

    public static void showDialog(FragmentManager fm) {
        GotoFinishInfoFragment fragment = ((GotoFinishInfoFragment) fm.findFragmentByTag(TAG));
        if (fragment == null) {
            fragment = new GotoFinishInfoFragment();
        }
        if(!fragment.isAdded()){
            fragment.show(fm, TAG);
        }
    }
}

package com.jsjlzj.wayne.ui.trainer.personal;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.MdlInfo;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.ui.publicac.about.AboutUsActivity;
import com.jsjlzj.wayne.ui.publicac.help.HelpActivity;
import com.jsjlzj.wayne.ui.store.personal.set.SetingActivity;
import com.jsjlzj.wayne.ui.trainer.personal.set.CollectStoresActivity;
import com.jsjlzj.wayne.ui.trainer.personal.set.ConnectPositionListActivity;
import com.jsjlzj.wayne.ui.trainer.personal.set.InterviewPositionListActivity;
import com.jsjlzj.wayne.ui.trainer.personal.set.PersonalInfoSetTrainerActivity;
import com.jsjlzj.wayne.ui.trainer.publicac.JobIntentionActivity;
import com.jsjlzj.wayne.utils.Utility;
import com.jsjlzj.wayne.widgets.dialog.CommonDialog;

/**
 * 教练端 我的
 */
public class TabItemTrainerInfoFragment extends MVPBaseFragment<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {

    public static Fragment getInstance() {
        TabItemTrainerInfoFragment fragment = new TabItemTrainerInfoFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    private TextView tvName, tvNun1, tvNun2, tvNun3, tvNun4,
            btnGeren, btnMendian, btnSet, btnAbout, btnHelp;
    private ImageView image;


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_talent_tab_trainer_info;
    }



    @Override
    protected void initViewAndControl(View view) {
        tvName = findView(R.id.tvName);
        tvNun1 = findView(R.id.tvNun1);
        tvNun2 = findView(R.id.tvNun2);
        tvNun3 = findView(R.id.tvNun3);
        tvNun4 = findView(R.id.tvNun4);
        btnGeren = findView(R.id.btnGeren);
        btnMendian = findView(R.id.btnMendian);
        btnSet = findView(R.id.btnSet);
        btnAbout = findView(R.id.btnAbout);
        btnHelp = findView(R.id.btnHelp);
        image = findView(R.id.image);


        findView(R.id.btnLogout).setOnClickListener(clickListener);
        findView(R.id.btnCard).setOnClickListener(clickListener);
        findView(R.id.L1Nun1).setOnClickListener(clickListener);
        findView(R.id.L1Nun2).setOnClickListener(clickListener);
        findView(R.id.L1Nun3).setOnClickListener(clickListener);
        findView(R.id.L1Nun4).setOnClickListener(clickListener);
        tvName.setOnClickListener(clickListener);
        btnGeren.setOnClickListener(clickListener);
        btnMendian.setOnClickListener(clickListener);
        btnSet.setOnClickListener(clickListener);
        btnAbout.setOnClickListener(clickListener);
        btnHelp.setOnClickListener(clickListener);
        image.setOnClickListener(clickListener);
    }

    @Override
    protected void fragment2Front() {

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.myselfT(null);

    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }


    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.tvName:
                case R.id.image:
                case R.id.btnGeren://个人信息
                    PersonalInfoSetTrainerActivity.go2this(getActivity());//ok
                    break;
                case R.id.L1Nun1://待沟通  (1沟通过的职位)
                    ConnectPositionListActivity.go2this(getActivity());
                    break;
                case R.id.L1Nun2://待面试
                    InterviewPositionListActivity.go2this(getActivity());
                    break;
                case R.id.L1Nun3://收藏 (2感兴趣的职位)
                    ConnectPositionListActivity.go2this2(getActivity());
                    break;
                case R.id.L1Nun4://职位管理
                    JobIntentionActivity.go2this(getActivity());
                    break;
                case R.id.btnCard://达人卡---------------------
                    TrainerInfoSetActivity.go2this(getActivity());
                    break;
                case R.id.btnMendian://关注的俱乐部
                    CollectStoresActivity.go2this(getActivity());
                    break;
                case R.id.btnSet://设置
                    SetingActivity.go2this(getActivity());
                    break;
                case R.id.btnAbout://关于我们
                    AboutUsActivity.go2this(getActivity());
                    break;
                case R.id.btnHelp://帮助
                    HelpActivity.go2this(getActivity());
                    break;
                case R.id.btnLogout://退出登录
                    clickLogout();
                    break;
            }
        }
    }

    public void clickLogout() {
        CommonDialog dialog = new CommonDialog(getActivity(), "确定退出账号吗？", new CommonDialog.ClickListener() {
            @Override
            public void clickConfirm() {
                Utility.needLogin(getActivity());
            }

            @Override
            public void clickCancel() {

            }
        });
        dialog.show();

    }

    @Override
    public void myselfT(MdlBaseHttpResp<MdlInfo> resp) {
        if(resp.getStatus()== HttpConstant.R_HTTP_OK&&null!=resp.getData()&&null!=resp.getData().getData()){
            MdlInfo.DataBean bean=resp.getData().getData();
            tvNun1.setText(bean.getCommunicatedCount()+"");
            tvNun2.setText(bean.getInterviewedCount()+"");
            tvNun3.setText(bean.getLikeCount()+"");
            tvNun4.setText(bean.getWorkHopeCount()+"");
            tvName.setText(bean.getName());
            setImg(bean.getHeadImg(), image);
        }
    }
}

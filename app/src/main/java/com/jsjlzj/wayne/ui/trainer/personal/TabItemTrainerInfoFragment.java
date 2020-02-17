package com.jsjlzj.wayne.ui.trainer.personal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.MdlInfo;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.ui.publicac.about.AboutUsActivity;
import com.jsjlzj.wayne.ui.publicac.help.HelpActivity;
import com.jsjlzj.wayne.ui.publicac.mine.InvitationActivity;
import com.jsjlzj.wayne.ui.store.personal.set.SetingActivity;
import com.jsjlzj.wayne.ui.trainer.personal.set.CollectStoresActivity;
import com.jsjlzj.wayne.ui.trainer.personal.set.ConnectPositionListActivity;
import com.jsjlzj.wayne.ui.trainer.personal.set.InterviewPositionListActivity;
import com.jsjlzj.wayne.ui.trainer.personal.set.PersonalInfoSetTrainerActivity;
import com.jsjlzj.wayne.utils.Utility;
import com.jsjlzj.wayne.widgets.dialog.CommonDialog;
import com.jsjlzj.wayne.widgets.img.CimageView;

import butterknife.BindView;

/**
 * 教练端 我的
 */
public class TabItemTrainerInfoFragment extends MVPBaseFragment<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {

    @BindView(R.id.img_message)
    ImageView imgMessage;
    @BindView(R.id.img_user_right)
    ImageView imgUserRight;
    @BindView(R.id.image_head)
    CimageView imageHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sign)
    TextView tvSign;
    @BindView(R.id.tv_dynamic)
    TextView tvDynamic;
    @BindView(R.id.ll_dynamic)
    LinearLayout llDynamic;
    @BindView(R.id.tv_fen)
    TextView tvFen;
    @BindView(R.id.ll_fen)
    LinearLayout llFen;
    @BindView(R.id.tv_follow)
    TextView tvFollow;
    @BindView(R.id.ll_follow)
    LinearLayout llFollow;
    @BindView(R.id.tv_zan)
    TextView tvZan;
    @BindView(R.id.ll_zan)
    LinearLayout llZan;
    @BindView(R.id.tv_favorite)
    TextView tvFavorite;
    @BindView(R.id.ll_favorite)
    LinearLayout llFavorite;
    @BindView(R.id.img_recommend)
    ImageView imgRecommend;
    @BindView(R.id.ll_communicate)
    LinearLayout llCommunicate;
    @BindView(R.id.ll_interview)
    LinearLayout llInterview;
    @BindView(R.id.img_card)
    ImageView imgCard;
    @BindView(R.id.ll_card)
    LinearLayout llCard;
    @BindView(R.id.ll_qzgj)
    LinearLayout llQzgj;
    @BindView(R.id.tv_zwsc)
    ImageView tvZwsc;
    @BindView(R.id.ll_zwsc)
    LinearLayout llZwsc;
    @BindView(R.id.tv_favorite_store)
    TextView tvFavoriteStore;
    @BindView(R.id.ll_follow_store)
    LinearLayout llFollowStore;
    @BindView(R.id.ll_study)
    LinearLayout llStudy;
    @BindView(R.id.ll_sign_up)
    LinearLayout llSignUp;
    @BindView(R.id.ll_lljl)
    LinearLayout llLljl;
    @BindView(R.id.ll_set)
    LinearLayout llSet;
    @BindView(R.id.ll_yqhy)
    LinearLayout llYqhy;
    @BindView(R.id.ll_gywm)
    LinearLayout llGywm;
    @BindView(R.id.ll_bzyfk)
    LinearLayout llBzyfk;
    @BindView(R.id.btnLogout)
    Button btnLogout;

    public static Fragment getInstance() {
        TabItemTrainerInfoFragment fragment = new TabItemTrainerInfoFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }




    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_talent_tab_trainer_info;
    }


    @Override
    protected void initViewAndControl(View view) {
        imgMessage.setOnClickListener(clickListener);
        imageHead.setOnClickListener(clickListener);
        tvName.setOnClickListener(clickListener);
        tvSign.setOnClickListener(clickListener);
        imgUserRight.setOnClickListener(clickListener);
        llDynamic.setOnClickListener(clickListener);
        llFen.setOnClickListener(clickListener);
        llFollow.setOnClickListener(clickListener);
        llZan.setOnClickListener(clickListener);
        llFavorite.setOnClickListener(clickListener);
        llCommunicate.setOnClickListener(clickListener);
        llInterview.setOnClickListener(clickListener);
        llCard.setOnClickListener(clickListener);
        llQzgj.setOnClickListener(clickListener);
        llZwsc.setOnClickListener(clickListener);

        llFollowStore.setOnClickListener(clickListener);
        llStudy.setOnClickListener(clickListener);
        llSignUp.setOnClickListener(clickListener);
        llLljl.setOnClickListener(clickListener);
        llSet.setOnClickListener(clickListener);
        llYqhy.setOnClickListener(clickListener);
        llGywm.setOnClickListener(clickListener);
        llBzyfk.setOnClickListener(clickListener);
        btnLogout.setOnClickListener(clickListener);

    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()){
            case R.id.img_message:
                //我的消息
                break;
            case R.id.tv_name:
            case R.id.img_user_right:
            case R.id.image_head:
                PersonalInfoSetTrainerActivity.go2this(getActivity());
                break;
            case R.id.ll_dynamic://动态
                break;
            case R.id.ll_fen://粉丝
                break;
            case R.id.ll_follow://关注
                break;
            case R.id.ll_zan://获赞
                break;
            case R.id.ll_favorite://收藏
                ConnectPositionListActivity.go2this2(getActivity());
                break;
            case R.id.ll_communicate://沟通过
                ConnectPositionListActivity.go2this(getActivity());
                break;
            case R.id.ll_interview://待面试
                InterviewPositionListActivity.go2this(getActivity());
                break;
            case R.id.ll_card://达人卡
                MasterCardActivity.go2this(getActivity());
                break;
            case R.id.ll_qzgj://求职管理
                break;
            case R.id.ll_zwsc://职位收藏
                ConnectPositionListActivity.go2this2(getActivity());
                break;
            case R.id.ll_follow_store://关注的俱乐部
                CollectStoresActivity.go2this(getActivity());
                break;
            case R.id.ll_study://我的学习
                break;
            case R.id.ll_sign_up://我的报名
                break;
            case R.id.ll_lljl://浏览记录
                break;
            case R.id.ll_set://设置
                SetingActivity.go2this(getActivity());
                break;
            case R.id.ll_yqhy://邀请好友
                InvitationActivity.go2this(getActivity());
                break;
            case R.id.ll_gywm://关于我们
                AboutUsActivity.go2this(getActivity());
                break;
            case R.id.ll_bzyfk://帮助
                HelpActivity.go2this(getActivity());
                break;
            case R.id.btnLogout://退出登录
                clickLogout();
                break;
                default:break;

        }
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


    private void clickLogout() {
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
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            MdlInfo.DataBean bean = resp.getData().getData();
//            tvNun1.setText(bean.getCommunicatedCount() + "");
//            tvNun2.setText(bean.getInterviewedCount() + "");
            tvFavorite.setText(bean.getLikeCount() + "");
//            tvNun4.setText(bean.getWorkHopeCount() + "");
            tvName.setText(bean.getName());
            setImg(bean.getHeadImg(), imageHead);
        }
    }
}

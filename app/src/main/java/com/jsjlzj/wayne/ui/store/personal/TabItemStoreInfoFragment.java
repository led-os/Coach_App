package com.jsjlzj.wayne.ui.store.personal;

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
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.ui.publicac.about.AboutUsActivity;
import com.jsjlzj.wayne.ui.publicac.help.HelpActivity;
import com.jsjlzj.wayne.ui.publicac.mine.InvitationActivity;
import com.jsjlzj.wayne.ui.publicac.mine.MineSignUpActivity;
import com.jsjlzj.wayne.ui.store.personal.manage.ConnectListActivity;
import com.jsjlzj.wayne.ui.store.personal.manage.InterviewListActivity;
import com.jsjlzj.wayne.ui.store.personal.set.PersonalInfoSetActivity;
import com.jsjlzj.wayne.ui.store.personal.set.SetingActivity;
import com.jsjlzj.wayne.ui.store.talent.position.PositionSelectActivity;
import com.jsjlzj.wayne.utils.Utility;
import com.jsjlzj.wayne.widgets.dialog.CommonDialog;
import com.jsjlzj.wayne.widgets.img.CimageView;

import butterknife.BindView;

/**
 * 门店 ---我的界面
 */
public class TabItemStoreInfoFragment extends MVPBaseFragment<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView {

    @BindView(R.id.img_message)
    ImageView imgMessage;
    @BindView(R.id.image_head)
    CimageView imageHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sign)
    TextView tvSign;
    @BindView(R.id.tv_dynamic)
    TextView tvDynamic;
    @BindView(R.id.ll_communicate)
    LinearLayout llCommunicate;
    @BindView(R.id.tv_interview)
    TextView tvInterview;
    @BindView(R.id.tv_communicate)
    TextView tvCommunicate;
    @BindView(R.id.ll_interview)
    LinearLayout llInterview;
    @BindView(R.id.tv_favorite)
    TextView tvFavorite;
    @BindView(R.id.ll_favorite)
    LinearLayout llFavorite;
    @BindView(R.id.tv_zwgl)
    TextView tvZwgl;
    @BindView(R.id.ll_zwgl)
    LinearLayout llZwgl;
    @BindView(R.id.img_recommend)
    ImageView imgRecommend;
    @BindView(R.id.ll_store)
    LinearLayout llStore;
    @BindView(R.id.ll_sign_up)
    LinearLayout llSignUp;
    @BindView(R.id.ll_follow)
    LinearLayout llFollow;
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
        TabItemStoreInfoFragment fragment = new TabItemStoreInfoFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_talent_tab_store;
    }

    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
    }

    @Override
    protected void initViewAndControl(View view) {
        imgMessage.setOnClickListener(clickListener);
        imageHead.setOnClickListener(clickListener);
        tvName.setOnClickListener(clickListener);
        tvSign.setOnClickListener(clickListener);
        llCommunicate.setOnClickListener(clickListener);
        llInterview.setOnClickListener(clickListener);
        llFavorite.setOnClickListener(clickListener);
        llZwgl.setOnClickListener(clickListener);

        llStore.setOnClickListener(clickListener);
        llSignUp.setOnClickListener(clickListener);
        llFollow.setOnClickListener(clickListener);
        llYqhy.setOnClickListener(clickListener);
        llSet.setOnClickListener(clickListener);
        llGywm.setOnClickListener(clickListener);
        llBzyfk.setOnClickListener(clickListener);
        btnLogout.setOnClickListener(clickListener);

    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.img_message:
                //我的消息
                break;
            case R.id.tv_name:
            case R.id.img_user_right:
            case R.id.image_head:
                PersonalInfoSetActivity.go2this(getActivity());
                break;
            case R.id.ll_communicate://沟通过
                ConnectListActivity.go2this(getActivity());
                break;
            case R.id.ll_interview://待面试
                InterviewListActivity.go2this(getActivity());
                break;
            case R.id.ll_favorite://收藏的达人
                ConnectListActivity.go2this2(getActivity());
                break;
            case R.id.ll_zwgl://职位管理
                PositionSelectActivity.go2this(getActivity());
                break;
            case R.id.ll_sign_up://我的报名
                MineSignUpActivity.go2this(getActivity());
                break;
            case R.id.ll_follow://我的关注
                break;
            case R.id.ll_yqhy://邀请好友
                InvitationActivity.go2this(getActivity());
                break;
            case R.id.ll_set://设置
                SetingActivity.go2this(getActivity());
                break;
            case R.id.ll_gywm://关于我们
                AboutUsActivity.go2this(getActivity());
                break;
            case R.id.ll_bzyfk://帮助与反馈
                HelpActivity.go2this(getActivity());
                break;
            case R.id.btnLogout:
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
        presenter.getMyInfo(null);

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

    public void showGetMyInfo(MdlBaseHttpResp<MdlInfo> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            MdlInfo.DataBean bean = resp.getData().getData();
            tvCommunicate.setText(bean.getCommunicatedCount() + "");
            tvInterview.setText(bean.getInterviewedCount() + "");
            tvFavorite.setText(bean.getLikeCount() + "");
            tvZwgl.setText(bean.getPositionCount() + "");
            tvSign.setText(bean.getStoreUserName() + "·" + bean.getStoreUserPosition());
            tvName.setText(bean.getStoreName());
            setImg(bean.getStoreUserHeadImg(), imageHead);
        }
    }
}
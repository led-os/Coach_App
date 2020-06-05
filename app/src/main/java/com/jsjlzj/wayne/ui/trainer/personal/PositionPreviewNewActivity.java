package com.jsjlzj.wayne.ui.trainer.personal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.ItemsBean;
import com.jsjlzj.wayne.entity.trainer.MdlDetailT;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.ui.store.talent.position.RecruitActivity;
import com.jsjlzj.wayne.ui.yunxin.YunXingUtil;
import com.jsjlzj.wayne.utils.GlidUtils;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.dialog.CommonDialog;
import com.jsjlzj.wayne.widgets.dialog.ReportDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 达人卡预览
 */
public class PositionPreviewNewActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {

    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.img_add_pic)
    ImageView imgAddPic;
    @BindView(R.id.tv_nick_name)
    TextView tvNickName;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.tv_online_resume)
    TextView tvOnlineResume;
    @BindView(R.id.view_online)
    View viewOnline;
    @BindView(R.id.ll_online_resume)
    LinearLayout llOnlineResume;
    @BindView(R.id.tv_video)
    TextView tvVideo;
    @BindView(R.id.view_video)
    View viewVideo;
    @BindView(R.id.ll_video)
    LinearLayout llVideo;
    @BindView(R.id.fragment)
    FrameLayout fragment;
    @BindView(R.id.img_favorite)
    ImageView imgFavorite;
    @BindView(R.id.img_jubao)
    ImageView imgJubao;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_item)
    LinearLayout llItem;
    @BindView(R.id.tv_to_communicate)
    TextView tvToCommunicate;
    @BindView(R.id.img_title)
    ImageView imgTitle;

    private FragmentTransaction fragmentTransition;
    private OnlineResumeFragment onlineResumeFragment;
    private PositionVideoFragment positionVideoFragment;
    private String id;
    private Map<Object, Object> map;
    private List<ItemsBean> list = new ArrayList<>();
    private boolean isLikeFlag;
    private MdlDetailT.DataBean data;
    private CommonDialog dialog;
    private List<MdlDetailT.DataBean.WorkHopeListBean> workHopeList = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_position_preview_new;
    }

    public static void go2this(Activity context, String id) {
        Intent intent = new Intent(context, PositionPreviewNewActivity.class);
        intent.putExtra("id", id);
        context.startActivityForResult(intent, RecruitActivity.FLAG_RECRUIT_CONTENT);
    }


    @Override
    protected void initViewAndControl() {
        btnBack.setOnClickListener(clickListener);
        imgFavorite.setOnClickListener(clickListener);
        imgJubao.setOnClickListener(clickListener);
        llOnlineResume.setOnClickListener(clickListener);
        llVideo.setOnClickListener(clickListener);
        tvToCommunicate.setOnClickListener(clickListener);
        id = getIntent().getStringExtra("id");
        showOnlineFragment();
        if (TextUtils.isEmpty(id)) {
            presenter.getDetailT(null);
            tvToCommunicate.setVisibility(View.GONE);
            imgJubao.setVisibility(View.GONE);
            imgFavorite.setVisibility(View.GONE);
        } else {
            if (null == map) map = new HashMap<>();
            map.put("id", id);
            tvToCommunicate.setVisibility(View.VISIBLE);
            imgJubao.setVisibility(View.VISIBLE);
            imgFavorite.setVisibility(View.VISIBLE);
            presenter.detailCV(map);
        }
        if (MyApp.mdlDict != null && MyApp.mdlDict.getPosition_tipoff() != null) {
            list.addAll(MyApp.mdlDict.getPosition_tipoff().getItems());
        }
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.img_favorite:
                if (isLikeFlag) {
                    presenter.cancelCVLike(map);
                } else {
                    presenter.saveCVLike(map);
                }
                break;
            case R.id.img_jubao:
                ReportDialog dialog1 = new ReportDialog(PositionPreviewNewActivity.this, code ->
                        LogAndToastUtil.toast("举报成功"), list);
                dialog1.show();
                break;
            case R.id.tv_to_communicate:
                if (TextUtils.isEmpty(MyApp.positionId)) {
                    dialog = new CommonDialog(PositionPreviewNewActivity.this, "您还没有发布招聘职位,快去发布吧", new CommonDialog.ClickListener() {
                        @Override
                        public void clickConfirm() {
                            dialog.dismiss();
                        }

                        @Override
                        public void clickCancel() {
                            RecruitActivity.go2this(PositionPreviewNewActivity.this, "");

                        }
                    });
                    dialog.show();
                    dialog.setCancel("确定");
                    dialog.setConfirm("下次再说");
                } else {
                    if (workHopeList != null && workHopeList.size() > 0) {
                        YunXingUtil.toChatRoom(view.getContext(), data.getYunXinAccount(),
                                workHopeList.get(0).getId(),
                                workHopeList.get(0).getPosition(),
                                MyApp.positionId,
                                MyApp.isTrainer);
                    }
                }
                break;
            case R.id.ll_online_resume:
                tvOnlineResume.setTextColor(ContextCompat.getColor(this, R.color.color_222222));
                tvVideo.setTextColor(ContextCompat.getColor(this, R.color.color_999999));
                viewOnline.setVisibility(View.VISIBLE);
                viewVideo.setVisibility(View.GONE);
                showOnlineFragment();
                break;
            case R.id.ll_video:
                tvOnlineResume.setTextColor(ContextCompat.getColor(this, R.color.color_999999));
                tvVideo.setTextColor(ContextCompat.getColor(this, R.color.color_222222));
                viewOnline.setVisibility(View.GONE);
                viewVideo.setVisibility(View.VISIBLE);
                showPositionVideoFragment();
                break;
            default:
                break;
        }
    }


    public void showOnlineFragment() {
        fragmentTransition = getSupportFragmentManager().beginTransaction();
        if (positionVideoFragment != null) {
            fragmentTransition.hide(positionVideoFragment);
        }
        if (onlineResumeFragment == null) {
            onlineResumeFragment = new OnlineResumeFragment();
            fragmentTransition.add(R.id.fragment, onlineResumeFragment);
        } else {
            fragmentTransition.show(onlineResumeFragment);
        }
        fragmentTransition.commit();
    }

    public void showPositionVideoFragment() {
        fragmentTransition = getSupportFragmentManager().beginTransaction();
        if (onlineResumeFragment != null) {
            fragmentTransition.hide(onlineResumeFragment);
        }
        if (positionVideoFragment == null) {
            positionVideoFragment = new PositionVideoFragment();
            if(data != null && data.getTeachVideos() != null){
                Bundle bundle = new Bundle();
                bundle.putSerializable(ExtraConstant.EXTRA_DATA, (Serializable) data.getTeachVideos());
                positionVideoFragment.setArguments(bundle);
            }
            fragmentTransition.add(R.id.fragment, positionVideoFragment);
        } else {
            fragmentTransition.show(positionVideoFragment);
        }
        fragmentTransition.commit();
    }


    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }


    @Override
    public void showDetailCV(MdlBaseHttpResp<MdlDetailT> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            setUi(resp);
        } else {
            LogAndToastUtil.toast(this, resp.getMsg());
        }
    }

    public void setUi(MdlBaseHttpResp<MdlDetailT> resp) {
        data = resp.getData().getData();
        if(!TextUtils.isEmpty(data.getLifePhotos())){
            String[] list = data.getLifePhotos().split(",");
            GlidUtils.setGrid(this,list[0],imgTitle);
        }
        GlidUtils.setCircleGrid(this,data.getHeadImg(),imgAddPic);
        if(TextUtils.isEmpty(data.getEnglishName())){
            tvNickName.setText(data.getName());
        }else {
            tvNickName.setText(data.getName()+"("+data.getEnglishName()+")");
        }
        tvState.setText(data.getWorkStatus());
        tvDes.setText(data.getContent());
        if (data.isLike()) {
            imgFavorite.setImageDrawable(getResources().getDrawable(R.drawable.collected));
            isLikeFlag = true;
        } else {
            imgFavorite.setImageDrawable(getResources().getDrawable(R.drawable.ic_dianzang));
        }
        if (TextUtils.isEmpty(id)) {
            workHopeList = data.getWorkHopeList();
        } else {
            workHopeList.add(data.getWorkHope());
        }
        if (onlineResumeFragment != null) {
            onlineResumeFragment.setData(data);
        }
    }

    @Override
    public void getDetailT(MdlBaseHttpResp<MdlDetailT> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            setUi(resp);
        } else {
            LogAndToastUtil.toast(this, resp.getMsg());
        }
    }

    @Override
    public void showCVSaveLike(MdlBaseHttpResp resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData()) {
            LogAndToastUtil.toast("收藏成功");
            isLikeFlag = true;
            imgFavorite.setImageDrawable(getResources().getDrawable(R.drawable.collected));
        } else {
            LogAndToastUtil.toast(resp.getMsg());
        }
    }

    @Override
    public void showCVCancelLike(MdlBaseHttpResp resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData()) {
            imgFavorite.setImageDrawable(getResources().getDrawable(R.drawable.ic_dianzang));
            isLikeFlag = false;
            LogAndToastUtil.toast("取消成功");
        } else {
            LogAndToastUtil.toast(resp.getMsg());
        }
    }

}

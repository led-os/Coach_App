package com.jsjlzj.wayne.ui.trainer.personal;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.EducationPreAdapter;
import com.jsjlzj.wayne.adapter.TrainerHopeAdapter;
import com.jsjlzj.wayne.adapter.TrainerPreExpAdapter;
import com.jsjlzj.wayne.adapter.TrainerPrePicAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.ItemsBean;
import com.jsjlzj.wayne.entity.trainer.MdlDetailT;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.ui.store.talent.position.RecruitActivity;
import com.jsjlzj.wayne.ui.yunxin.YunXingUtil;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.MyListView;
import com.jsjlzj.wayne.widgets.dialog.CommonDialog;
import com.jsjlzj.wayne.widgets.dialog.ReportDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PositionPreviewActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_position_preview;
    }

    public static void go2this(Activity context, String id) {
        Intent intent = new Intent(context, PositionPreviewActivity.class);
        intent.putExtra("id", id);
        context.startActivityForResult(intent, RecruitActivity.FLAG_RECRUIT_CONTENT);
    }

    private TextView tvTrainerName, tvPosition, tvInfo, tvStatus, trainer_content, btnConfirm;
    private MyListView lvWorkExpricence, lv_workHope, trainer_ivlv, trainer_lvEducation;
    private ImageView image, btnShoucang, btnJubao;
    private TrainerHopeAdapter jobIntentionAdapter;
    private TrainerPreExpAdapter expAdapter;
    private TrainerPrePicAdapter picAdapter;
    private EducationPreAdapter educationAdapter;
    private ScrollView preview_scrollview;
    private List<MdlDetailT.DataBean.WorkExperienceListBean> workExperienceList = new ArrayList<>();
    private List<MdlDetailT.DataBean.EducationExperienceListBean> educationExperienceList = new ArrayList<>();
    private List<MdlDetailT.DataBean.WorkHopeListBean> workHopeList = new ArrayList<>();
    private Map<Object, Object> map;
    private boolean isLikeFlag;
    private List<ItemsBean> list;
    private String id;
    @Override
    protected void initViewAndControl() {
        list=new ArrayList<>();
        id = getIntent().getStringExtra("id");
        tvTrainerName = findView(R.id.tvTrainerName);
        tvPosition = findView(R.id.tvPosition);
        image = findView(R.id.image);
        btnShoucang = findView(R.id.btnShoucang);
        btnJubao = findView(R.id.btnJubao);
        tvStatus = findView(R.id.tvStatus);
        tvInfo = findView(R.id.tvInfo);
        btnConfirm = findView(R.id.btnConfirm);
        btnConfirm.setOnClickListener(clickListener);
        preview_scrollview = findView(R.id.preview_scrollview);
        trainer_content = findView(R.id.trainer_content);
        lv_workHope = findView(R.id.lv_workHope);
        lvWorkExpricence = findView(R.id.lvWorkExpricence);
        trainer_ivlv = findView(R.id.trainer_ivlv);
        trainer_lvEducation = findView(R.id.trainer_lvEducation);
        btnShoucang.setOnClickListener(clickListener);
        btnJubao.setOnClickListener(clickListener);
        findView(R.id.btnBack).setOnClickListener(clickListener);
        jobIntentionAdapter = new TrainerHopeAdapter(this);
        lv_workHope.setAdapter(jobIntentionAdapter);
        expAdapter = new TrainerPreExpAdapter(this);
        lvWorkExpricence.setAdapter(expAdapter);
        educationAdapter = new EducationPreAdapter(this);
        trainer_lvEducation.setAdapter(educationAdapter);
        picAdapter = new TrainerPrePicAdapter(this);
        trainer_ivlv.setAdapter(picAdapter);
        if (TextUtils.isEmpty(id)) {
            presenter.getDetailT(null);
            btnConfirm.setVisibility(View.GONE);
            btnJubao.setVisibility(View.GONE);
            btnShoucang.setVisibility(View.GONE);
        } else {
            if (null == map) map = new HashMap<>();
            map.put("id", id);
            btnConfirm.setVisibility(View.VISIBLE);
            presenter.detailCV(map);
        }
        if(MyApp.mdlDict!=null&&MyApp.mdlDict.getPosition_tipoff()!=null){
            list.addAll(MyApp.mdlDict.getPosition_tipoff().getItems());
        }
    }
    private MdlDetailT.DataBean data;
    private MyViewClickListener clickListener = new MyViewClickListener();
    CommonDialog dialog;
    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnConfirm:
                    if(TextUtils.isEmpty(MyApp.positionId)){
                        dialog=new CommonDialog(PositionPreviewActivity.this, "您还没有发布招聘职位,快去发布吧", new CommonDialog.ClickListener() {
                            @Override
                            public void clickConfirm() {
                                    dialog.dismiss();
                                }

                            @Override
                            public void clickCancel() {
                                RecruitActivity.go2this(PositionPreviewActivity.this,"");

                            }
                        });
                        dialog.show();
                        dialog.setCancel("确定");
                        dialog.setConfirm("下次再说");
                    }else {
                        if (workHopeList != null && workHopeList.size() > 0) {
                            YunXingUtil.toChatRoom(view.getContext(), data.getYunXinAccount(),
                                    workHopeList.get(0).getId(),
                                    workHopeList.get(0).getPosition(),
                                    MyApp.positionId,
                                    MyApp.isTrainer);
                        }
                    }
                    break;
                case R.id.btnBack://返回
                    finish();
                    break;
                case R.id.btnShoucang:
                    if (isLikeFlag) {
                        presenter.cancelCVLike(map);
                    } else {
                        presenter.saveCVLike(map);
                    }
                    break;
                case R.id.btnJubao:
                    ReportDialog dialog=new ReportDialog(PositionPreviewActivity.this, new ReportDialog.ClickListener(){
                        @Override
                        public void clickConfirm(String code) {
                            LogAndToastUtil.toast("举报成功");
                        }
                    },list);
                    dialog.show();
                    break;
            }
        }
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

    @Override
    public void getDetailT(MdlBaseHttpResp<MdlDetailT> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
          setUi(resp);
        }else{
            LogAndToastUtil.toast(this, resp.getMsg());
        }
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
        tvTrainerName.setText(TextUtils.isEmpty(data.getName()) ? "" : data.getName());
        tvPosition.setText(TextUtils.isEmpty(data.getHighestEducationLevel()) ? "学历" : data.getHighestEducationLevel());
        setImg(data.getHeadImg(), image);
        tvStatus.setText(data.getWorkStatus());
        tvInfo.setText(data.getAge() + "岁  " + data.getHighestEducationLevel());
        trainer_content.setText(data.getAdvantage());
        if (!TextUtils.isEmpty(data.getLifePhotos())) {
            List<String> list = Arrays.asList(data.getLifePhotos().split(","));
            picAdapter.setData(list);
            picAdapter.notifyDataSetChanged();
        }
        if(data.isLike()){
            btnShoucang.setImageDrawable(getResources().getDrawable(R.drawable.collected));
            isLikeFlag=true;
        }else{
            btnShoucang.setImageDrawable(getResources().getDrawable(R.drawable.uncollected));
        }
        if(TextUtils.isEmpty(id)) {
            workHopeList = data.getWorkHopeList();
        }else{
            workHopeList.add(data.getWorkHope());
        }
        workExperienceList = data.getWorkExperienceList();
        educationExperienceList = data.getEducationExperienceList();
        jobIntentionAdapter.setData(workHopeList);
        expAdapter.setData(workExperienceList);
        educationAdapter.setData(educationExperienceList);
    }

    @Override
    public void showCVSaveLike(MdlBaseHttpResp resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData()) {
            LogAndToastUtil.toast("收藏成功");
            isLikeFlag=true;
            btnShoucang.setImageDrawable(getResources().getDrawable(R.drawable.collected));
        }else{
            LogAndToastUtil.toast(resp.getMsg());
        }
    }

    @Override
    public void showCVCancelLike(MdlBaseHttpResp resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData()) {
            btnShoucang.setImageDrawable(getResources().getDrawable(R.drawable.uncollected));
            isLikeFlag=false;
            LogAndToastUtil.toast("取消成功");
        }else{
            LogAndToastUtil.toast(resp.getMsg());
        }
    }
}

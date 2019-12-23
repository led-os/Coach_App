package com.jsjlzj.wayne.ui.trainer.publicac;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.trainer.MdlDetailT;
import com.jsjlzj.wayne.entity.trainer.MdlsaveAdvantage;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.ui.store.talent.position.recruit.RecruitSkillActivity;
import com.jsjlzj.wayne.ui.store.talent.position.recruit.RecruitTypeActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.dialog.DateYMDialog;
import com.jsjlzj.wayne.widgets.dialog.EditDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 达人卡  添加工作经验
 */
public class AddExpActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {
    public final static int FLAG_RECRUIT_CONTENT = 0x001;
    private Map<Object, Object> param;


    private String companyName;
    private String startDate;
    private String endDate;
    private String id;
    private String isHide="0";//0 1
    private String positionTypeId;
    private List<String> skillTags=new ArrayList();
    private String workContent;

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, AddExpActivity.class);
        intent.putExtra("isResult", "");
        context.startActivity(intent);
    }

    public static void go2this(Activity context,MdlDetailT.DataBean.WorkExperienceListBean workExperienceListBean) {
        Intent intent = new Intent(context, AddExpActivity.class);
        intent.putExtra("isResult", "isResult");
        intent.putExtra("object", workExperienceListBean);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_trainer_add_exp;
    }


    private Switch btnHide;

    private TextView tvStoreName, tvJobStartTime, tvJobEndTime, tvPositionName, tvJonContent, tvSkill;


    @Override
    protected void initViewAndControl() {
        tvStoreName = findView(R.id.tvStoreName);//俱乐部名称
        tvJobStartTime = findView(R.id.tvJobStartTime);//开始时间
        tvJobEndTime = findView(R.id.tvJobEndTime);//结束时间
        tvPositionName = findView(R.id.tvPositionName);//职位名称
        tvJonContent = findView(R.id.tvJonContent);//工作内容
        tvSkill = findView(R.id.tvSkill);//技能标签

        btnHide = findView(R.id.btnHide);//是否隐藏简历

        tvStoreName.setOnClickListener(clickListener);
        tvJobStartTime.setOnClickListener(clickListener);
        tvJobEndTime.setOnClickListener(clickListener);
        tvPositionName.setOnClickListener(clickListener);
        tvJonContent.setOnClickListener(clickListener);
        tvSkill.setOnClickListener(clickListener);
        findView(R.id.btnBack).setOnClickListener(clickListener);
        findView(R.id.btnRight).setOnClickListener(clickListener);
        btnHide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        isHide="1";
                    }else {
                        isHide="0";
                    }
            }
        });


        if (!TextUtils.isEmpty(getIntent().getStringExtra("isResult"))){
            MdlDetailT.DataBean.WorkExperienceListBean workExperienceListBean = (MdlDetailT.DataBean.WorkExperienceListBean) getIntent().getSerializableExtra("object");
            companyName=workExperienceListBean.getCompanyName();
            startDate=workExperienceListBean.getStartDate();
            endDate=workExperienceListBean.getEndDate();
            id=workExperienceListBean.getId();
            isHide="0";
            positionTypeId=workExperienceListBean.getPositionTypeId();
            skillTags=workExperienceListBean.getSkillTags();
            workContent=workExperienceListBean.getWorkContent();
            tvStoreName.setText(companyName);
            tvJobStartTime.setText(startDate);
            tvJobEndTime.setText(endDate);
            tvPositionName.setText(workExperienceListBean.getPositionType());
            tvJonContent.setText(workContent);
            String s="";
            for (int i = 0; i < skillTags.size(); i++) {
                if (i==0){
                    s= skillTags.get(i);
                }else {
                    s=s+"."+skillTags.get(i);
                }
            }
            tvSkill.setText(s);
            if(null==workExperienceListBean.getIsHide()||workExperienceListBean.getIsHide().equals("0")){
                btnHide.setChecked(false);
                isHide="0";
            }else {
                btnHide.setChecked(true);
                isHide="1";
            }
        }
    }


    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnBack:
                    finish();
                    break;
                case R.id.btnRight://保存
                    if(TextUtils.isEmpty(companyName)){
                        LogAndToastUtil.toast(AddExpActivity.this, "请填写俱乐部名称");
                    }else if(TextUtils.isEmpty(startDate)){
                        LogAndToastUtil.toast(AddExpActivity.this, "请填添加开始时间");
                    } else if(TextUtils.isEmpty(endDate)){
                        LogAndToastUtil.toast(AddExpActivity.this, "请填添加结束时间");
                    }else if(TextUtils.isEmpty(positionTypeId)){
                        LogAndToastUtil.toast(AddExpActivity.this, "请填写职位名称");
                    }else if(skillTags.size()==0){
                        LogAndToastUtil.toast(AddExpActivity.this, "请填写俱乐部名称");
                    }else if(TextUtils.isEmpty(workContent)){
                        LogAndToastUtil.toast(AddExpActivity.this, "请填工作内容");
                    }else{
                        if (null == param) param = new HashMap<>();
                        param.put("companyName", companyName);
                        param.put("endDate", endDate);
                        param.put("id", id);
                        param.put("isHide", isHide);
                        param.put("positionTypeId", positionTypeId);
                        param.put("skillTags", skillTags);
                        param.put("startDate", startDate);
                        param.put("workContent", workContent);
                        presenter.saveWorkExperienceT(param);
                    }
                    break;
                case R.id.tvStoreName://俱乐部名称
                    showEditDialog();
                    break;
                case R.id.tvJobStartTime://开始时间
                    showStartDateYMDialog();
                    break;
                case R.id.tvJobEndTime://结束时间
                    showEndDateYMDialog();
                    break;
                case R.id.tvPositionName://职位名称
                    RecruitTypeActivity.go2this(AddExpActivity.this);
                    break;
                case R.id.tvJonContent://工作内容
                    AdvantageActivity.go2this(AddExpActivity.this,workContent,"简单介绍一下日常工作内容","工作内容");
                    break;
                case R.id.tvSkill://技能标签
                    RecruitSkillActivity.go2this(AddExpActivity.this,null);
                    break;
            }
        }
    }
    @Override
    public void saveWorkExperienceT(MdlBaseHttpResp<MdlsaveAdvantage> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            finish();
        } else {
            LogAndToastUtil.toast(this, resp.getMsg());
        }
    }

    private void showEditDialog() {
        new EditDialog(this,"", new EditDialog.ClickListener() {
            @Override
            public void clickConfirm(String s) {
                tvStoreName.setText(s);
                companyName=s;
            }

            @Override
            public void clickCancel() {

            }
        }).show();
    }
    private void showStartDateYMDialog() {
        new DateYMDialog(this, false, new DateYMDialog.ClickListener() {
            @Override
            public void clickConfirm(String year, String month) {
                tvJobStartTime.setText(year+"-"+month);
                startDate=year+"-"+month;
            }
        }).show();
    }
    private void showEndDateYMDialog() {
        new DateYMDialog(this, true, new DateYMDialog.ClickListener() {
            @Override
            public void clickConfirm(String year, String month) {
                tvJobEndTime.setText(year+"-"+month);
                endDate=year+"-"+month;
            }
        }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        switch (resultCode) {
            case FLAG_RECRUIT_CONTENT://工作内容
                String content = data.getStringExtra("data");
                if (content == null) content = "";
                if (tvJonContent != null) tvJonContent.setText(content);
                workContent=content;
                break;
            case 100://职位名称
                 tvPositionName.setText(data.getStringExtra("name"));
                 positionTypeId = data.getIntExtra("id",0)+"";
                break;
            case 101://技能标签
                Bundle bundle = data.getBundleExtra("bundle");
                skillTags = (List<String>) bundle.getSerializable("list");
                String s="";
                for (int i = 0; i < skillTags.size(); i++) {
                    if (i==0){
                        s=skillTags.get(i);
                    }else {
                        s=s+"."+skillTags.get(i);
                    }
                }
                tvSkill.setText(s);

                break;
        }
    }
}
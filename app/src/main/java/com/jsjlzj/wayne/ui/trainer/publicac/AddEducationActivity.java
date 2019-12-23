package com.jsjlzj.wayne.ui.trainer.publicac;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.ItemsBean;
import com.jsjlzj.wayne.entity.store.MdlDict;
import com.jsjlzj.wayne.entity.trainer.MdlDetailT;
import com.jsjlzj.wayne.entity.trainer.MdlsaveAdvantage;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.dialog.DateYMDialog;
import com.jsjlzj.wayne.widgets.dialog.EducationDialog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 达人卡  设置
 */
public class AddEducationActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {
    public final static int FLAG_RECRUIT_CONTENT = 0x001;
    private Map<Object, Object> param;

    private String educationLevel;
    private String educationLevelCode;//学历code
    private String endDate;
    private String id = "";
    private String major;
    private String schoolName;
    private String startDate;

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, AddEducationActivity.class);
        intent.putExtra("isResult", "");
        context.startActivity(intent);
    }

    public static void go2this(Activity context, MdlDetailT.DataBean.EducationExperienceListBean educationExperienceListBean) {
        Intent intent = new Intent(context, AddEducationActivity.class);
        intent.putExtra("isResult", "isResult");
        intent.putExtra("object", educationExperienceListBean);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_trainer_add_education;
    }


    private EditText edSchool, edMajor;
    private TextView tvEducation, tvJobStartTime, tvJobEndTime;
    private String[] datas=new String[7];
    private List<ItemsBean> itemsBean;

    @Override
    protected void initViewAndControl() {
        edSchool = findView(R.id.edSchool);//学校名称
        tvEducation = findView(R.id.tvEducation);//学历
        edMajor = findView(R.id.edMajor);//专业
        tvJobStartTime = findView(R.id.tvJobStartTime);//开始时间
        tvJobEndTime = findView(R.id.tvJobEndTime);//结束时间

        tvJobStartTime.setOnClickListener(clickListener);
        tvJobEndTime.setOnClickListener(clickListener);
        tvEducation.setOnClickListener(clickListener);
        findView(R.id.btnBack).setOnClickListener(clickListener);
        findView(R.id.btnRight).setOnClickListener(clickListener);
        if(MyApp.mdlDict!=null&&MyApp.mdlDict.getEducation_level()!=null){
            MdlDict.DataBean.SalaryRequiredBean bean=MyApp.mdlDict.getEducation_level();
            if(bean!=null&&bean.getItems()!=null) {
                itemsBean = MyApp.mdlDict.getEducation_level().getItems();
                for (int i = 0; i < bean.getItems().size(); i++) {
                    datas[i] = bean.getItems().get(i).getName();
                }
            }
        }else{
            presenter.getAll(null);
        }

        if (!TextUtils.isEmpty(getIntent().getStringExtra("isResult"))){
            MdlDetailT.DataBean.EducationExperienceListBean educationExperienceListBean = (MdlDetailT.DataBean.EducationExperienceListBean) getIntent().getSerializableExtra("object");
            educationLevel=educationExperienceListBean.getEducationLevel();
            educationLevelCode=educationExperienceListBean.getEducationLevelCode();
            endDate=educationExperienceListBean.getEndDate();
            id=educationExperienceListBean.getId();
            major=educationExperienceListBean.getMajor();
            schoolName=educationExperienceListBean.getSchoolName();
            startDate=educationExperienceListBean.getStartDate();
            edSchool.setText(schoolName);
            tvEducation.setText(educationLevel);
            edMajor.setText(major);
            tvJobStartTime.setText(startDate);
            tvJobEndTime.setText(endDate);
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
                    schoolName = edSchool.getText().toString().trim();
                    major = edMajor.getText().toString().trim();
                    if (TextUtils.isEmpty(schoolName)) {
                        LogAndToastUtil.toast(AddEducationActivity.this, "请输入学校名");
                    } else if (TextUtils.isEmpty(educationLevelCode)) {
                        LogAndToastUtil.toast(AddEducationActivity.this, "请选择学历");
                    } else if (TextUtils.isEmpty(major)) {
                        LogAndToastUtil.toast(AddEducationActivity.this, "请输入专业名称");
                    } else if (TextUtils.isEmpty(startDate)) {
                        LogAndToastUtil.toast(AddEducationActivity.this, "请选择开始时间");
                    } else if (TextUtils.isEmpty(endDate)) {
                        LogAndToastUtil.toast(AddEducationActivity.this, "请选择结束时间");
                    } else {
                        if (null == param) param = new HashMap<>();
                        param.put("educationLevel", educationLevel);
                        param.put("educationLevelCode", educationLevelCode);
                        param.put("id", id);
                        param.put("major", major);
                        param.put("schoolName", schoolName);
                        param.put("startDate", startDate);
                        param.put("endDate", endDate);
                        presenter.saveEducationExperienceT(param);
                    }

                    break;
                case R.id.tvJobStartTime://开始时间
                    showStartDateYMDialog();
                    break;
                case R.id.tvJobEndTime://结束时间
                    showEndDateYMDialog();
                    break;
                case R.id.tvEducation://学历
                    showEducationDialog();
                    break;
            }
        }
    }

    @Override
    public void saveEducationExperienceT(MdlBaseHttpResp<MdlsaveAdvantage> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            finish();
        } else {
            LogAndToastUtil.toast(this, resp.getMsg());
        }
    }


    private void showEducationDialog() {
        new EducationDialog(this, new EducationDialog.ClickListener() {
            @Override
            public void clickConfirm(String data, int position) {
                tvEducation.setText(data);
                educationLevel = data;
                educationLevelCode = itemsBean.get(position).getCode();
            }
        },datas).show();
    }

    private void showStartDateYMDialog() {
        new DateYMDialog(this, false, new DateYMDialog.ClickListener() {
            @Override
            public void clickConfirm(String year, String month) {
                tvJobStartTime.setText(year + "." + month);
                startDate = year + "-" + month;
            }
        }).show();
    }

    private void showEndDateYMDialog() {
        new DateYMDialog(this, true, new DateYMDialog.ClickListener() {
            @Override
            public void clickConfirm(String year, String month) {
                tvJobEndTime.setText(year + "." + month);
                endDate = year + "-" + month;
            }
        }).show();
    }
    @Override
    public void showResultgetAll(MdlBaseHttpResp<MdlDict> resp){
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            MyApp.mdlDict=resp.getData().getData();
            MdlDict.DataBean.SalaryRequiredBean bean=resp.getData().getData().getStaff_num();
            if(null!=bean) {
                itemsBean = bean.getItems();
                if (itemsBean != null && itemsBean.size() > 0) {
                    for (int i = 0; i < itemsBean.size(); i++) {
                        datas[i] = itemsBean.get(i).getName();
                    }
                }
            }
        }
    }
}
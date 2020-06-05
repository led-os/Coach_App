package com.jsjlzj.wayne.ui.trainer.personal;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.EducationAdapter;
import com.jsjlzj.wayne.adapter.ExpAdapter;
import com.jsjlzj.wayne.adapter.JobIntentionAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.trainer.MdlDetailT;
import com.jsjlzj.wayne.entity.trainer.MdlWorkStatus;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.ui.trainer.personal.set.PersonalInfoSetTrainerActivity;
import com.jsjlzj.wayne.ui.trainer.publicac.AddEducationActivity;
import com.jsjlzj.wayne.ui.trainer.publicac.AddExpActivity;
import com.jsjlzj.wayne.ui.trainer.publicac.AddJobIntentionActivity;
import com.jsjlzj.wayne.ui.trainer.publicac.AdvantageActivity;
import com.jsjlzj.wayne.ui.trainer.publicac.JobIntentionActivity;
import com.jsjlzj.wayne.ui.trainer.publicac.PositionPhotoActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.MyListView;
import com.jsjlzj.wayne.widgets.dialog.PositinStateDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 达人卡  设置
 */
public class TrainerInfoSetActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {
    public final static int FLAG_RECRUIT_CONTENT = 0x001;

    private String workStatusCode;

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, TrainerInfoSetActivity.class);
        intent.putExtra("isResult", "");
        context.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //简历详情
        presenter.getDetailT(null);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_trainer_info_set;
    }


    private ImageView image;
    private TextView tvName, tvPhoto, tvPositionState, tvContent1, tvContent2, tvContent3, tvAdvantage;
    private MyListView rvJobIntention, rvExp, rvEducation;
    private TextView tvStoreName, tvStoreContent, tvStorePhoto, tvStoreTime, tvStoreWelfare, tvStoreAddress;
    private JobIntentionAdapter jobIntentionAdapter;
    private ExpAdapter expAdapter;
    private EducationAdapter educationAdapter;
    private List<MdlDetailT.DataBean.WorkExperienceListBean> workExperienceList = new ArrayList<>();
    private List<MdlDetailT.DataBean.EducationExperienceListBean> educationExperienceList = new ArrayList<>();
    private List<MdlDetailT.DataBean.WorkHopeListBean> workHopeList = new ArrayList<>();
    private MdlDetailT.DataBean data;
    private String advantage="";
    @Override
    protected void initViewAndControl() {
        image = findView(R.id.image);//头像
        tvName = findView(R.id.tvName);//名称
        tvPhoto = findView(R.id.tvPhoto);//照片
        tvContent1 = findView(R.id.tvContent1);//经验年限
        tvContent2 = findView(R.id.tvContent2);//年龄
        tvContent3 = findView(R.id.tvContent3);//文凭

        tvAdvantage = findView(R.id.tvAdvantage);

        tvPositionState = findView(R.id.tvPositionState);//求职状态
        rvJobIntention = findView(R.id.rvJobIntention);//求职期望列表
        rvExp = findView(R.id.rvExp);//工作经验列表
        rvEducation = findView(R.id.rvEducation);//教育经历列表
        //求职期望列表
        jobIntentionAdapter = new JobIntentionAdapter(this);
        rvJobIntention.setAdapter(jobIntentionAdapter);
        rvJobIntention.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AddJobIntentionActivity.go2this(TrainerInfoSetActivity.this, workHopeList.get(position));
            }
        });
        //工作经验列表
        expAdapter = new ExpAdapter(this);
        rvExp.setAdapter(expAdapter);
        rvExp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AddExpActivity.go2this(TrainerInfoSetActivity.this, workExperienceList.get(position));
            }
        });
        //教育经历列表
        educationAdapter = new EducationAdapter(this);
        rvEducation.setAdapter(educationAdapter);
        rvEducation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AddEducationActivity.go2this(TrainerInfoSetActivity.this, educationExperienceList.get(position));
            }
        });


        image.setOnClickListener(clickListener);//
        tvName.setOnClickListener(clickListener);//
        findView(R.id.llContent).setOnClickListener(clickListener);//
        tvPhoto.setOnClickListener(clickListener);
        tvAdvantage.setOnClickListener(clickListener);

        findView(R.id.llPositionState).setOnClickListener(clickListener);
        findView(R.id.btnJobIntention).setOnClickListener(clickListener);
        findView(R.id.btnAddPosition).setOnClickListener(clickListener);
        findView(R.id.btnAddExp).setOnClickListener(clickListener);
        findView(R.id.btnAddEducation).setOnClickListener(clickListener);


        findView(R.id.btnBack).setOnClickListener(clickListener);
        findView(R.id.btnRight).setOnClickListener(clickListener);


    }


    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnBack:
                    finish();
                    break;
                case R.id.tvPhoto:
                    if(null!=data&&!TextUtils.isEmpty(data.getLifePhotos())){
                        PositionPhotoActivity.go2this(TrainerInfoSetActivity.this, data.getLifePhotos());
                    }else {
                        PositionPhotoActivity.go2this(TrainerInfoSetActivity.this, null);
                    }
                    break;
                case R.id.btnRight://预览

                    PositionPreviewNewActivity.go2this(TrainerInfoSetActivity.this,"");
                    break;
                case R.id.tvName://修改个人信息
                case R.id.image://修改个人信息
                case R.id.llContent://修改个人信息
                    PersonalInfoSetTrainerActivity.go2this(TrainerInfoSetActivity.this);//ok
                    break;
                case R.id.tvAdvantage://个人优势
                    AdvantageActivity.go2this(TrainerInfoSetActivity.this, advantage,"可以简单介绍一下个人优势，个人发展状况等。","个人优势");
                    break;
                case R.id.llPositionState://求职状态
                    showEducationDialog();
                    break;
                case R.id.btnJobIntention://求职意向
                    JobIntentionActivity.go2this(TrainerInfoSetActivity.this);
                    break;
                case R.id.btnAddPosition://添加求职期望
                    AddJobIntentionActivity.go2this(TrainerInfoSetActivity.this);
                    break;
                case R.id.btnAddExp://添加工作经验
                    AddExpActivity.go2this(TrainerInfoSetActivity.this);
                    break;
                case R.id.btnAddEducation://添加教育经历
                    AddEducationActivity.go2this(TrainerInfoSetActivity.this);
                    break;
            }
        }
    }

    //   求职状态
    private void showEducationDialog() {
        PositinStateDialog positinStateDialog=  new PositinStateDialog(this, new PositinStateDialog.ClickListener() {
            @Override
            public void clickConfirm(String data, String code) {
                workStatusCode = code;
                Map<Object, Object> param = new HashMap<>();
                param.put("workStatusCode", code);
                param.put("workStatus", data);

                presenter.saveWorkStatusT(param);
            }
        });
        positinStateDialog.show();
        positinStateDialog.setTitle("选择求职状态");
    }

    @Override
    public void saveWorkStatusT(MdlBaseHttpResp<MdlWorkStatus> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            MdlWorkStatus.DataBean data = resp.getData().getData();
            tvPositionState.setText(data.getWorkStatus());
            workStatusCode = data.getWorkStatusCode();
        } else {
            LogAndToastUtil.toast(this, resp.getMsg());
        }
    }

    @Override
    public void getDetailT(MdlBaseHttpResp<MdlDetailT> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            data = resp.getData().getData();
            tvContent1.setText(TextUtils.isEmpty(data.getWorkYears())?"经验年限":data.getWorkYears());
            tvContent2.setText(TextUtils.isEmpty(data.getAge())?"年龄":data.getAge());
            tvContent3.setText(data.getHighestEducationLevel());
            if (!TextUtils.isEmpty(data.getName())) {
                tvName.setText(data.getName());
            }
            if (!TextUtils.isEmpty(data.getHeadImg())) {
                Glide.with(this).load(data.getHeadImg()).into(image);
            }
            if (TextUtils.isEmpty(data.getAdvantage())) {
                tvAdvantage.setText("添加个人优势");
            } else {
            advantage=data.getAdvantage();
                tvAdvantage.setText("去修改");
            }
            if (!TextUtils.isEmpty(data.getLifePhotos())) {
                List<String> list = Arrays.asList(data.getLifePhotos().split(","));
                tvPhoto.setText("已添加" + list.size() + "张");
            } else {
                tvPhoto.setText("未添加");
            }
            if (!TextUtils.isEmpty(data.getWorkStatus())) {
                tvPositionState.setText(data.getWorkStatus());
                workStatusCode = data.getWorkStatusCode();
            } else {
                tvPositionState.setText("添加求职状态");
            }
            workHopeList = data.getWorkHopeList();
            workExperienceList = data.getWorkExperienceList();
            educationExperienceList = data.getEducationExperienceList();
            jobIntentionAdapter.setData(workHopeList);
            expAdapter.setData(workExperienceList);
            educationAdapter.setData(educationExperienceList);
        } else {
            LogAndToastUtil.toast(this, resp.getMsg());
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        switch (requestCode) {
            case FLAG_RECRUIT_CONTENT://个人优势
                String content = data.getStringExtra("data");
//                if (content == null) content = "";
//                if (tvAdvantage != null) tvAdvantage.setText(content);
                if (TextUtils.isEmpty(content)) {
                    tvAdvantage.setText("添加个人优势");
                } else {
                    tvAdvantage.setText("去修改");
                }

                break;
        }
    }
}
package com.jsjlzj.wayne.ui.store.talent.position;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.ItemsBean;
import com.jsjlzj.wayne.entity.store.MdlDict;
import com.jsjlzj.wayne.entity.store.MdlPosition;
import com.jsjlzj.wayne.entity.store.MdlPositionDetail;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.ui.store.talent.position.recruit.RecruitContentActivity;
import com.jsjlzj.wayne.ui.store.talent.position.recruit.RecruitCoordinateSelectActivity;
import com.jsjlzj.wayne.ui.store.talent.position.recruit.RecruitSkillActivity;
import com.jsjlzj.wayne.ui.store.talent.position.recruit.RecruitTypeActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.Utility;
import com.jsjlzj.wayne.widgets.dialog.EducationDialog;
import com.jsjlzj.wayne.widgets.dialog.ExperienceDialog;
import com.jsjlzj.wayne.widgets.dialog.SalaryDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.jsjlzj.wayne.entity.Login.MdlDict;

/**
 * 招聘  发布
 */
public class RecruitActivity extends MVPBaseActivity<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView {
    public final static int FLAG_RECRUIT_CONTENT = 0x001;
    public final static int FLAG_RECRUIT_RECRUITSKILL = 0x002;
    public final static int FLAG_RECRUIT_ADDRESS = 0x003;
    public final static int FLAG_RECRUIT_RECRUITTYPE = 0x004;
    private List<String> label;
    private String area;
    private String address;
    private String province;
    private String city;
    private String coordinate;
    private int salaryMin;
    private int salaryMax;
    private int status=2;
    private int positionTypeId;
    private  String id="";
    public static void go2this(Activity context,String id) {
        Intent intent = new Intent(context, RecruitActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_recruit;
    }


    private String[] datas=new String[7];
    private String[] datasExp=new String[7];
    private List<ItemsBean> itemsBean;
    private List<ItemsBean> itemsBeanExp;
    private TextView tvRecruitName, tvType, tvSkill, tvExp, tvEducation, tvSalary, tvContent, tvCoordinate;

    @Override
    protected void initViewAndControl() {
        MyApp.ME.dm = Utility.getDisplayScreenSize(this);
        tvRecruitName = findView(R.id.tvRecruitName);
        tvType = findView(R.id.tvType);
        tvSkill = findView(R.id.tvSkill);
        tvExp = findView(R.id.tvExp);
        tvEducation = findView(R.id.tvEducation);
        tvSalary = findView(R.id.tvSalary);
        tvContent = findView(R.id.tvContent);
        tvCoordinate = findView(R.id.tvCoordinate);
        if (null == label) label = new ArrayList<>();
        findView(R.id.llRecruitName).setOnClickListener(clickListener);
        findView(R.id.llType).setOnClickListener(clickListener);
        findView(R.id.llSkill).setOnClickListener(clickListener);
        findView(R.id.llExp).setOnClickListener(clickListener);
        findView(R.id.llEducation).setOnClickListener(clickListener);
        findView(R.id.llSalary).setOnClickListener(clickListener);
        findView(R.id.llContent).setOnClickListener(clickListener);
        findView(R.id.llCoordinate).setOnClickListener(clickListener);
        findView(R.id.btnBack).setOnClickListener(clickListener);
        findView(R.id.btnConfirm).setOnClickListener(clickListener);
        findView(R.id.btnKeep).setOnClickListener(clickListener);
       id =getIntent().getStringExtra("id");
        if(!TextUtils.isEmpty(id)) {
            Map<Object, Object> map = new HashMap<>();
            map.put("id", id);
            presenter.getPositionDetail(map);
        }
        if(MyApp.mdlDict!=null&&MyApp.mdlDict.getEducation_level()!=null){
            MdlDict.DataBean.SalaryRequiredBean bean=MyApp.mdlDict.getEducation_level();
            if(bean!=null&&bean.getItems()!=null) {
                itemsBean = MyApp.mdlDict.getEducation_level().getItems();
                for (int i = 0; i < bean.getItems().size(); i++) {
                    datas[i] = bean.getItems().get(i).getName();
                }
            }
            MdlDict.DataBean.SalaryRequiredBean beanYears=MyApp.mdlDict.getWork_years();
            if(beanYears!=null&&beanYears.getItems()!=null) {
                itemsBeanExp = beanYears.getItems();
                for (int i = 0; i < beanYears.getItems().size(); i++) {
                    datasExp[i] = beanYears.getItems().get(i).getName();
                }
            }

        }else{
            presenter.getAll(null);
        }
    }

    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
    }

    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.llRecruitName://我要招聘
                    RecruitTypeActivity.go2this(RecruitActivity.this);
                    break;
                case R.id.llType://职位类型
                    RecruitTypeActivity.go2this(RecruitActivity.this);
                    break;
                case R.id.llSkill://技能要求
                    RecruitSkillActivity.go2this(RecruitActivity.this, label);
                    break;
                case R.id.llExp://经验
                    showExperienceDialog();
                    break;
                case R.id.llEducation://学历
                    showEducationDialog();
                    break;
                case R.id.llSalary://薪资范围
                    showSalaryDialog();
                    break;
                case R.id.llContent://职位描述
                    String content = tvContent.getText().toString();
                    if ("请选择职位描述".equals(content)) {
                        RecruitContentActivity.go2this(RecruitActivity.this, "");

                    } else {
                        RecruitContentActivity.go2this(RecruitActivity.this, content);
                    }
                    break;
                case R.id.llCoordinate://工作地点
                    RecruitCoordinateSelectActivity.go2this(RecruitActivity.this);
                    break;
                case R.id.btnConfirm://发布职位
                    submitInfo();
                    break;
                case R.id.btnKeep://存草稿箱
                    status=1;
                    submitInfo();
                    break;
                case R.id.btnBack://返回
                    finish();
                    break;
            }
        }
    }

    Map<Object, Object> map;
    private String lowestEducationLevel;
    private String workYearsCode;
    private void submitInfo() {
        if (null == map) map = new HashMap<>();
        if (TextUtils.isEmpty(area) || TextUtils.isEmpty(city) || TextUtils.isEmpty(coordinate) || TextUtils.isEmpty(province) || TextUtils.isEmpty(address)) {
            LogAndToastUtil.toast("地址信息有误，请重新选择");
            return;
        }
        String content = tvContent.getText().toString();
        if ("请选择职位描述".equals(content)) {
            content = "";
            LogAndToastUtil.toast("请选择职位描述");
            return;
        }
        if (positionTypeId == 0) {
            LogAndToastUtil.toast("请选择职位类型");
            return;
        }
        if(TextUtils.isEmpty(workYearsCode)){
            LogAndToastUtil.toast("请选择经验");
            return;
        }
        if(TextUtils.isEmpty(lowestEducationLevel)){
            LogAndToastUtil.toast("请选择学历");
            return;
        }
        map.put("area", area);
        map.put("city", city);
        map.put("content", content);
        map.put("coordinate", coordinate);
        map.put("lowestEducationLevelCode", lowestEducationLevel);
        map.put("positionTypeId", positionTypeId);
        map.put("province", province);
        map.put("salaryMax", salaryMax);
        map.put("salaryMin", salaryMin);
        map.put("skillRequired", label);
        map.put("status", status);
        map.put("workAddress", address);
        map.put("id", id);
        map.put("workYearsCode", workYearsCode);
        presenter.savePosition(map);
    }

    private void showEducationDialog() {
        new EducationDialog(this, new EducationDialog.ClickListener() {
            @Override
            public void clickConfirm(String data,int position) {
                tvEducation.setText(data);
                lowestEducationLevel = itemsBean.get(position).getCode();

            }
        },datas).show();
    }

    private void showExperienceDialog() {
        new ExperienceDialog(this, new ExperienceDialog.ClickListener() {
            @Override
            public void clickConfirm(String data,int position) {
                tvExp.setText(data);
                workYearsCode=itemsBeanExp.get(position).getCode();
            }
        },datasExp).show();
    }

    private void showSalaryDialog() {
        new SalaryDialog(this, new SalaryDialog.ClickListener() {
            @Override
            public void clickConfirm(String min, String max) {
                if (min.contains("K")) {
                    salaryMin = Integer.valueOf(min.substring(0, min.indexOf("K")));
                }
                if (max.contains("K")) {
                    salaryMax = Integer.valueOf(max.substring(0, max.indexOf("K")));
                }
                tvSalary.setText(min + "-" + max);
            }
        }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        switch (requestCode) {
            case FLAG_RECRUIT_CONTENT://职位描述
                String content = data.getStringExtra("data");
                if (TextUtils.isEmpty(content)) content = "";
                if (tvContent != null) tvContent.setText(content);
                break;
            case FLAG_RECRUIT_RECRUITSKILL:
                label.clear();
                Bundle bundle = data.getBundleExtra("bundle");
                List<String> stringList = (List<String>) bundle.getSerializable("list");
                if (null != stringList && stringList.size() > 0) {
                    label.addAll(stringList);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < label.size(); i++) {
                        sb.append(label.get(i) + "·");
                    }
                    if (sb.length() > 0) {
                        tvSkill.setText(sb.toString().subSequence(0, sb.toString().length() - 1));
                    }
                }
                break;
            case FLAG_RECRUIT_ADDRESS:
                area = data.getStringExtra("area");
                city = data.getStringExtra("city");
                coordinate = data.getStringExtra("coordinate");
                province = data.getStringExtra("province");
                address = data.getStringExtra("storeAddress")+data.getStringExtra("storeDoorplate");
                tvCoordinate.setText(TextUtils.isEmpty(address) ? "" : address);
                break;
            case FLAG_RECRUIT_RECRUITTYPE:
                tvRecruitName.setText(data.getStringExtra("name"));
                tvType.setText(data.getStringExtra("type"));
                positionTypeId = data.getIntExtra("id", 0);
                break;
        }
    }

    @Override
    public void showPositionSave(MdlBaseHttpResp<MdlPosition> resp) {
        if(resp.getStatus()== HttpConstant.R_HTTP_OK&&null!=resp.getData()&&null!=resp.getData().getData()){
            LogAndToastUtil.toast("提交成功");
            finish();
        }else{
            LogAndToastUtil.toast(resp.getMsg());
        }
    }

    @Override
    public void showPositionDetail(MdlBaseHttpResp<MdlPositionDetail> resp) {
        if(resp.getStatus()== HttpConstant.R_HTTP_OK&&null!=resp.getData()&&null!=resp.getData().getData()){
            MdlPositionDetail.DataBean bean= resp.getData().getData();
            MdlPosition.DataBean dataBean= bean.getPosition();
            if(null!=dataBean){
                tvRecruitName.setText(dataBean.getRecruitmentTypeName());
                tvType.setText(dataBean.getName());
                tvSalary.setText(dataBean.getSalaryMin()+"-"+dataBean.getSalaryMax()+"K");
                tvCoordinate.setText(dataBean.getWorkAddress());
                tvEducation.setText(dataBean.getLowestEducationLevel());
                tvExp.setText(dataBean.getWorkYears());
                tvContent.setText(dataBean.getContent());
                StringBuilder sb=new StringBuilder();
                if(null!=dataBean.getSkillRequired()&&dataBean.getSkillRequired().size()>0){
                    for (int i = 0; i <dataBean.getSkillRequired().size(); i++) {
                        sb.append(dataBean.getSkillRequired().get(i)+"·");
                    }
                    tvSkill.setText(sb.toString().subSequence(0,sb.toString().length()-1));
                }
                area=dataBean.getArea();
                city=dataBean.getCity();
                coordinate=dataBean.getCoordinate();
                positionTypeId=dataBean.getPositionTypeId();
                province=dataBean.getProvince();
                salaryMax=dataBean.getSalaryMax();
                salaryMin=dataBean.getSalaryMin();
                label=dataBean.getSkillRequired();
                address=dataBean.getWorkAddress();
                workYearsCode=dataBean.getWorkYearsCode();
                lowestEducationLevel = dataBean.getLowestEducationLevelCode();
            }
        }
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
            MdlDict.DataBean.SalaryRequiredBean beanYears=MyApp.mdlDict.getWork_years();
            if(beanYears!=null&&beanYears.getItems()!=null) {
                itemsBeanExp = beanYears.getItems();
                for (int i = 0; i < beanYears.getItems().size(); i++) {
                    datasExp[i] = beanYears.getItems().get(i).getName();
                }
            }
        }
    }


}
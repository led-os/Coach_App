package com.jsjlzj.wayne.ui.trainer.publicac;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.trainer.MdlDetailT;
import com.jsjlzj.wayne.entity.trainer.MdlsaveAdvantage;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.ui.publicac.AddressActivity;
import com.jsjlzj.wayne.ui.store.talent.position.recruit.RecruitTypeActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.Utility;
import com.jsjlzj.wayne.widgets.dialog.SalaryDialog;

import java.util.HashMap;
import java.util.Map;

/**
 * 添加求职期望
 */
public class AddJobIntentionActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {
    public final static int FLAG_RECRUIT_CONTENT = 0x001;

    private Map<Object, Object> param;
    //todo
    private String cityId = "";
    private String id = "";
    private String positionTypeId;
    private String salaryMax;
    private String salaryMin;

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, AddJobIntentionActivity.class);
        intent.putExtra("isResult", "");
        context.startActivity(intent);
    }

    public static void go2this(Activity context, MdlDetailT.DataBean.WorkHopeListBean workHopeListBean) {
        Intent intent = new Intent(context, AddJobIntentionActivity.class);
        intent.putExtra("isResult", "isResult");
        intent.putExtra("object", workHopeListBean);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_jobintention_add;
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

    private TextView tvPosition, tvAddress, tvSalary;

    @Override
    protected void initViewAndControl() {
        MyApp.ME.dm = Utility.getDisplayScreenSize(this);
        tvPosition = findView(R.id.tvPosition);
        tvAddress = findView(R.id.tvAddress);
        tvSalary = findView(R.id.tvSalary);
        findView(R.id.llType).setOnClickListener(clickListener);
        findView(R.id.llSkill).setOnClickListener(clickListener);
        findView(R.id.llExp).setOnClickListener(clickListener);
        findView(R.id.btnConfirm).setOnClickListener(clickListener);
        findView(R.id.btnBack).setOnClickListener(clickListener);


        if (!TextUtils.isEmpty(getIntent().getStringExtra("isResult"))) {
            MdlDetailT.DataBean.WorkHopeListBean workHopeListBean = (MdlDetailT.DataBean.WorkHopeListBean) getIntent().getSerializableExtra("object");
            positionTypeId = workHopeListBean.getPositionTypeId();
            cityId = workHopeListBean.getCityId();
            id = workHopeListBean.getId();
            salaryMax = workHopeListBean.getSalaryMax();
            salaryMin = workHopeListBean.getSalaryMin();

            tvPosition.setText(workHopeListBean.getPosition());
            tvAddress.setText(workHopeListBean.getProvince() + " " + workHopeListBean.getCity());
            tvSalary.setText(workHopeListBean.getSalaryMin() + "-" + workHopeListBean.getSalaryMax() + "k");
        }
    }


    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.llType://期望职位
                    RecruitTypeActivity.go2this(AddJobIntentionActivity.this);
                    break;
                case R.id.llSkill://工作城市
                    AddressActivity.go2this(AddJobIntentionActivity.this);
//                    showSAddressDialog();
                    break;
                case R.id.llExp://薪资要求
                    showSalaryDialog();
                    break;
                case R.id.btnBack://返回
                    finish();
                    break;
                case R.id.btnConfirm://保存

                    if (TextUtils.isEmpty(positionTypeId)) {
                        LogAndToastUtil.toast(AddJobIntentionActivity.this, "请选择期望职位");
                    } else if (TextUtils.isEmpty(cityId)) {
                        LogAndToastUtil.toast(AddJobIntentionActivity.this, "请选择工作地点");
                    } else if (TextUtils.isEmpty(salaryMin)) {
                        LogAndToastUtil.toast(AddJobIntentionActivity.this, "请选择薪资要求");
                    } else {
                        if (null == param) param = new HashMap<>();
                        param.put("cityId", cityId);
                        param.put("id", id);
                        param.put("positionTypeId", positionTypeId);
                        param.put("salaryMax", salaryMax);
                        param.put("salaryMin", salaryMin);
                        presenter.saveWorkHopeT(param);
                    }
                    break;

            }
        }
    }

    @Override
    public void saveWorkHopeT(MdlBaseHttpResp<MdlsaveAdvantage> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            LogAndToastUtil.toast("保存成功");
            finish();
        } else {
            LogAndToastUtil.toast(this, resp.getMsg());
        }
    }

    private void showSalaryDialog() {
        new SalaryDialog(this, new SalaryDialog.ClickListener() {
            @Override
            public void clickConfirm(String min, String max) {
                String n = min.replace("K", "");
                n = n.replace("以上", "");
                String x = max.replace("K", "");
                x = x.replace("以上", "");
                tvSalary.setText(n + "-" + x + "k");
                salaryMin = n;
                salaryMax = x;
            }
        }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        if (resultCode == RESULT_OK && requestCode == 7) {
            cityId = data.getStringExtra("cityId");
            String province = data.getStringExtra("province");
            String city = data.getStringExtra("city");
            String area = TextUtils.isEmpty(data.getStringExtra("area"))?"":data.getStringExtra("area");
            tvAddress.setText(province + city + area);
        }
        switch (resultCode) {
            case 100://职位名称
                String type = data.getStringExtra("type");
                tvPosition.setText(type + " " + data.getStringExtra("name"));
                positionTypeId = data.getIntExtra("id", 0) + "";
                break;

        }

    }

}
package com.jsjlzj.wayne.ui.store.home.amoy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.entity.DataBean;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.DataCheckUtils;
import com.jsjlzj.wayne.utils.LogAndToastUtil;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: SignUpActivity
 * @Description: 我要报名
 * @Author: 曾海强
 * @CreateDate:
 */
public class SignUpActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView, TextWatcher {

    public static final int REQUEST_CODE = 0x141;

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.et_recommend)
    EditText etRecommend;
    @BindView(R.id.ll_recommend)
    LinearLayout llRecommend;
    @BindView(R.id.et_recommend_phone)
    EditText etRecommendPhone;
    @BindView(R.id.ll_recommend_phone)
    LinearLayout llRecommendPhone;

    /**
     * 0 :淘学报名   1： 赛事报名
     */
    private int type;
    private String courseId;
    private Map<Object, Object> map = new HashMap<>();


    public static void go2this(Activity context, String schoolId, String courseId, int type) {
        Intent intent = new Intent(context, SignUpActivity.class);
        intent.putExtra(ExtraConstant.EXTRA_TITLE, courseId);
        intent.putExtra(ExtraConstant.EXTRA_SCHOOL_ID, schoolId);
        intent.putExtra(ExtraConstant.EXTRA_SHOW_TYPE, type);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_sign_up;
    }

    @Override
    protected void initViewAndControl() {
        courseId = getIntent().getStringExtra(ExtraConstant.EXTRA_TITLE);
        type = getIntent().getIntExtra(ExtraConstant.EXTRA_SHOW_TYPE, 0);
        String schoolId = getIntent().getStringExtra(ExtraConstant.EXTRA_SCHOOL_ID);
        LogAndToastUtil.log(schoolId + "title====courseId" + courseId);
        if (MyApp.isTrainer) {
            initTitle("我要报名");
            tvCommit.setText("提交报名");
            llRecommend.setVisibility(View.VISIBLE);
            llRecommendPhone.setVisibility(View.VISIBLE);
        } else {
            initTitle("我要推荐");
            tvCommit.setText("提交推荐");
        }
        tvCommit.setOnClickListener(clickListener);
        etName.addTextChangedListener(this);
        etPhone.addTextChangedListener(this);
    }


    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }


    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        if (view.getId() == R.id.tv_commit) {
            if (TextUtils.isEmpty(etName.getText().toString())) {
                LogAndToastUtil.toast(this, "请输入您的姓名！");
                return;
            }
            if (TextUtils.isEmpty(etPhone.getText().toString())) {
                LogAndToastUtil.toast(this, "请输入您的电话！");
                return;
            }
            if (!DataCheckUtils.checkPhone(etPhone.getText().toString())) {
                LogAndToastUtil.toast(this, "手机号格式错误，请重新输入！");
                return;
            }
            if(!TextUtils.isEmpty(etRecommend.getText().toString()) || !TextUtils.isEmpty(etRecommendPhone.getText().toString())){
                if (TextUtils.isEmpty(etRecommend.getText().toString())) {
                    LogAndToastUtil.toast(this, "请填写推荐人姓名！");
                    return;
                }
                if (TextUtils.isEmpty(etRecommendPhone.getText().toString())) {
                    LogAndToastUtil.toast(this, "请填写推荐人电话！");
                    return;
                }
                if (!DataCheckUtils.checkPhone(etRecommendPhone.getText().toString())) {
                    LogAndToastUtil.toast(this, "手机号格式错误，请重新输入！");
                    return;
                }
            }
            map.put("name", etName.getText().toString());
            map.put("mobile", etPhone.getText().toString());
            if(!TextUtils.isEmpty(etRecommend.getText().toString())){
                map.put("referee",etRecommend.getText().toString());
            }
            if(!TextUtils.isEmpty(etRecommendPhone.getText().toString())){
                map.put("refereeName",etRecommendPhone.getText().toString());
            }
            map.put("id", courseId);
            if (type == 0) {
                presenter.getAmoySiguUp(map);
            } else {
                presenter.getMatchSiguUp(map);
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (!TextUtils.isEmpty(etName.getText().toString())
                && !TextUtils.isEmpty(etPhone.getText().toString())) {
            tvCommit.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_solid_222222_21));
        } else {
            tvCommit.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_solid_dddddd_21));
        }
    }

    @Override
    public void amoySignUpSuccess(MdlBaseHttpResp<DataBean> resp) {
        LogAndToastUtil.toast(this, resp.getMsg());
        SignUpResultActivity.go2this(this, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            finish();
        }
    }

}

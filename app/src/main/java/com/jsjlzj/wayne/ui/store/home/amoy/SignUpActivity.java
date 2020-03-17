package com.jsjlzj.wayne.ui.store.home.amoy;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.netease.nim.uikit.common.ToastHelper;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

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

    private String courseId;
    private Map<Object, Object> map = new HashMap<>();


    public static void go2this(Activity context, String schoolId, String courseId) {
        Intent intent = new Intent(context, SignUpActivity.class);
        intent.putExtra(ExtraConstant.EXTRA_TITLE, courseId);
        intent.putExtra(ExtraConstant.EXTRA_SCHOOL_ID, schoolId);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_sign_up;
    }

    @Override
    protected void initViewAndControl() {
        courseId = getIntent().getStringExtra(ExtraConstant.EXTRA_TITLE);
        String schoolId = getIntent().getStringExtra(ExtraConstant.EXTRA_SCHOOL_ID);
        LogAndToastUtil.log(schoolId + "title====courseId" + courseId);
        initTitle("我要报名");
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
            map.put("name", etName.getText().toString());
            map.put("mobile", etPhone.getText().toString());
            map.put("id", courseId);
            presenter.getAmoySiguUp(map);
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
            tvCommit.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_solid_0091ff_21));
        } else {
            tvCommit.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_solid_dddddd_21));

        }
    }

    @Override
    public void amoySignUpSuccess(MdlBaseHttpResp<String> resp) {
        ToastHelper.showToast(this, "报名成功");
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

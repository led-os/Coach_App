package com.jsjlzj.wayne.ui.store.personal.set;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.utils.keyboard.KeyboardUtil;
import com.jsjlzj.wayne.widgets.ToastUtils;
import com.netease.nim.uikit.common.ToastHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description: 改变用户信息
 *
 * @author: 曾海强
 * CreateDate: 2019/9/9 14:44
 */
public class ChangeUserInfoActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {

    public static final int TYPE_NAME = 0;
    public static final int TYPE_ENGLISH_NAME = 1;
    private int type;

    @BindView(R.id.tv_type)
    TextView tvType;//修改的类型名称
    @BindView(R.id.et_value)
    EditText etValue;//修改的类型值
    @BindView(R.id.iv_closed)
    ImageView ivClosed;//修改值清空

    public static void go2this(Activity context,int requestCode) {
        Intent intent = new Intent(context, ChangeUserInfoActivity.class);
        context.startActivityForResult(intent,requestCode);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_change_user_info;
    }

    @Override
    protected void initViewAndControl() {
        type = getIntent().getIntExtra(ExtraConstant.EXTRA_SHOW_TYPE, TYPE_NAME);
        String nameDes = getIntent().getStringExtra(ExtraConstant.EXTRA_NAME);
        initRightTitle("修改信息",getResources().getString(R.string.confirm));
        switch (type) {
            case TYPE_NAME:
                tvType.setText("姓名");
                etValue.setText(nameDes);
                etValue.setHint("请输入姓名");
                break;
            case TYPE_ENGLISH_NAME:
                tvType.setText("英文名");
                etValue.setText(nameDes);
                etValue.setHint("请输入英文名");
                break;
            default:
                break;
        }

        etValue.setSelection(etValue.getText().length());
        KeyboardUtil.openKeyboard(etValue, this);
        etValue.requestFocus();
        etValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etValue.getText().length() > 0) {
                    ivClosed.setVisibility(View.VISIBLE);
                } else {
                    ivClosed.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }


    @Override
    public void onChangeSucceed() {
        ToastUtils.showToast(this, "修改成功");
        finish();
    }

    @Override
    public void onChangeFailed(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            ToastHelper.showToast(this, msg);
        }
    }

    @OnClick({R.id.iv_closed, R.id.tv_right_btn})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_closed:
                etValue.setText("");
                break;
            case R.id.tv_right_btn://提交修改信息
                submit();
                break;
            default:
                break;
        }
    }

    private void submit() {
        String data = etValue.getText().toString();
        if (TextUtils.isEmpty(data) && type == TYPE_NAME) {
            ToastUtils.showToast(this, "请输入姓名");
            return;
        }
        if (TextUtils.isEmpty(data) && type == TYPE_ENGLISH_NAME) {
            ToastUtils.showToast(this, "请输入英文名");
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(ExtraConstant.EXTRA_NAME,etValue.getText().toString());
        setResult(RESULT_OK);
        finish();

    }

    @Override
    public void onBackPressed() {
        KeyboardUtil.closeKeyboard(etValue, this);
        super.onBackPressed();
    }


}

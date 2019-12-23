package com.jsjlzj.wayne.ui.store.personal.storeinfo.set;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.MdlStoreInfo;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.ui.store.talent.position.RecruitActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SPUtil;
import com.jsjlzj.wayne.utils.Utility;

import java.util.HashMap;
import java.util.Map;

/**
 * 招聘  职位描述
 */
public class StoreContentActivity extends MVPBaseActivity<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView {
    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
    }

    public static void go2this(Activity context, String content) {
        if (content == null) content = "";
        Intent intent = new Intent(context, StoreContentActivity.class);
        intent.putExtra("content", content);
        context.startActivityForResult(intent, RecruitActivity.FLAG_RECRUIT_CONTENT);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_store_info_content;
    }


    private TextView tvCount;
    private EditText editText;
    private String content;

    @Override
    protected void initViewAndControl() {

        tvCount = findView(R.id.tvCount);
        editText = findView(R.id.editText);
        findView(R.id.btnBack).setOnClickListener(clickListener);
        findView(R.id.btnKeep).setOnClickListener(clickListener);
        editText.addTextChangedListener(watcherEdPhone);

        content = getIntent().getStringExtra("content");
        if (TextUtils.isEmpty(content)) content = SPUtil.getRecruitContent();
        if (!TextUtils.isEmpty(content)) {
            editText.setText(content);
            editText.setSelection(content.length());
        }
    }

    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnKeep://保存
                    String editTextStr = Utility.getEditTextStr(editText);
                    if (TextUtils.isEmpty(editTextStr)) {
                        LogAndToastUtil.toast("描述不可为空！");
                        return;
                    }
                    submitInfo(editTextStr);

//                    SPUtil.saveRecruitContent(editTextStr);
//                    Intent intent = new Intent();
//                    intent.putExtra("data", editTextStr);
//                    setResult(RecruitActivity.FLAG_RECRUIT_CONTENT, intent);
//                    finish();
                    break;
                case R.id.btnBack://返回
                    finish();
                    break;
            }
        }
    }

    private Map<Object, Object> mapInfo;

    private void submitInfo(String companyProfile) {
        if (null == mapInfo) mapInfo = new HashMap<>();
        mapInfo.put("companyProfile", companyProfile);
        presenter.saveCompanyProfile(mapInfo);
    }

    private TextWatcher watcherEdPhone = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (null != s) {
                tvCount.setText(s.length() + "/5000");
            } else {
                tvCount.setText("0/5000");
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    @Override
    public void showSaveCompanyProfile(MdlBaseHttpResp<MdlStoreInfo> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            LogAndToastUtil.toast(this, "保存成功");
            finish();
        } else {
            LogAndToastUtil.toast(this, resp.getMsg());
        }

    }
}
package com.jsjlzj.wayne.ui.trainer.publicac;

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
import com.jsjlzj.wayne.entity.trainer.MdlsaveAdvantage;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.ui.store.talent.position.RecruitActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.Utility;

import java.util.HashMap;
import java.util.Map;

/**
 * 个人优势
 */
public class AdvantageActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {
    private Map<Object, Object> param;

    public static void go2this(Activity context, String content,String advantageInfo,String title) {
        if(content==null)content="";
        Intent intent = new Intent(context, AdvantageActivity.class);
        intent.putExtra("content", content);
        intent.putExtra("advantageInfo", advantageInfo);
        intent.putExtra("title", title);
        context.startActivityForResult(intent,RecruitActivity.FLAG_RECRUIT_CONTENT);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_trainer_advantage;
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

    private TextView tvCount,toolbarTitle,tvAdvantageInfo;
    private EditText editText;
    private String content;
    private String advantageInfo,title;

    @Override
    protected void initViewAndControl() {

        tvCount = findView(R.id.tvCount);
        editText = findView(R.id.editText);
        toolbarTitle = findView(R.id.toolbarTitle);
        tvAdvantageInfo = findView(R.id.tvAdvantageInfo);
        findView(R.id.btnBack).setOnClickListener(clickListener);
        findView(R.id.btnKeep).setOnClickListener(clickListener);
        editText.addTextChangedListener(watcherEdPhone);

        content = getIntent().getStringExtra("content");
        advantageInfo = getIntent().getStringExtra("advantageInfo");
        title = getIntent().getStringExtra("title");
        toolbarTitle.setText(title);
        tvAdvantageInfo.setText(advantageInfo);
        if (TextUtils.isEmpty(content)) content = "";
        if (!TextUtils.isEmpty(content)) editText.setText(content);
    }

    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnKeep://保存
//                    String editTextStr = Utility.getEditTextStr(tvCount);
//                    if (editTextStr == null) {
//                        LogAndToastUtil.toast("描述不可为空！");
//                        break;
//                    }
//                    SPUtil.saveRecruitContent(editTextStr);
//                    Intent intent = new Intent();
//                    intent.putExtra("data", editTextStr);
//                    setResult(RecruitActivity.FLAG_RECRUIT_CONTENT, intent);
//                    LogAndToastUtil.toast("保存成功");
//                    finish();
                    String editTextStr = Utility.getEditTextStr(editText);
                    Intent intent = new Intent();
                    intent.putExtra("data", editTextStr);
                    setResult(RecruitActivity.FLAG_RECRUIT_CONTENT, intent);

                    if (TextUtils.isEmpty(editTextStr)) {
                        LogAndToastUtil.toast("描述不可为空！");
                        break;
                    }
                    if (null == param) param = new HashMap<>();
                    param.put("advantage", editTextStr);
                    presenter.saveAdvantage(param);
                    break;
                case R.id.btnBack://返回
                    finish();
                    break;
            }
        }
    }

    @Override
    public void saveAdvantage(MdlBaseHttpResp<MdlsaveAdvantage> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            finish();
        } else {
            LogAndToastUtil.toast(this, resp.getMsg());
        }
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


}
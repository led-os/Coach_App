package com.jsjlzj.wayne.ui.publicac.help;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.MdlInfo;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 帮助
 */
public class HelpFeedbackProblemActivity extends MVPBaseActivity<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView {

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, HelpFeedbackProblemActivity.class);
        intent.putExtra("isResult", "");
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_help_feedbackproblem;
    }
    private EditText edContent,edPhone;



    @Override
    protected void initViewAndControl() {
        edContent=findView(R.id.edContent);
        edPhone=findView(R.id.edPhone);
        findView(R.id.btnBack).setOnClickListener(clickListener);
        findView(R.id.btnRight).setOnClickListener(clickListener);
    }

    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
    }
    Map<Object,Object> map=null;
    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnBack:
                    finish();
                    break;
                case R.id.btnRight:
                    String content=edContent.getText().toString().trim();
                    String phone=edPhone.getText().toString().trim();
                    if(TextUtils.isEmpty(content)){
                        LogAndToastUtil.toast("请输入建议");
                        return;
                    }
                    if(TextUtils.isEmpty(phone)){
                        LogAndToastUtil.toast("请输入手机号");
                        return;
                    }
                    if(null==map)map=new HashMap<>();
                    map.put("content",content);
                    map.put("mobile",phone);
                    presenter.questionBack(map);
                    break;
            }
        }
    }

    public void questionBack(MdlBaseHttpResp<MdlInfo> resp) {
        if(resp.getStatus()== HttpConstant.R_HTTP_OK&&null!=resp.getData()&&null!=resp.getData().getData()){
            LogAndToastUtil.toast("保存成功");
            finish();
        }else{
            LogAndToastUtil.toast(resp.getMsg());
        }
    }
}
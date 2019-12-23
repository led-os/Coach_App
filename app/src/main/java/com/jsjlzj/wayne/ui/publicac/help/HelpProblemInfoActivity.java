package com.jsjlzj.wayne.ui.publicac.help;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.Login.MdlQuestion;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;

import java.io.Serializable;

/**
 * 帮助
 */
public class HelpProblemInfoActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {

    public static void go2this(Activity context, MdlQuestion.DataBean bean) {
        Intent intent = new Intent(context, HelpProblemInfoActivity.class);
        intent.putExtra("bean", (Serializable) bean);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_help_problem_info;
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }
    private TextView tvTop,tvButtom;

    @Override
    protected void initViewAndControl() {
        findView(R.id.btnBack).setOnClickListener(clickListener);
        tvTop=findView(R.id.tvTop);
        tvButtom=findView(R.id.tvButtom);
        MdlQuestion.DataBean bean= (MdlQuestion.DataBean) getIntent().getSerializableExtra("bean");
        if(bean!=null){
            tvTop.setText(TextUtils.isEmpty(bean.getTitle())?"问: ":bean.getTitle());
            tvButtom.setText(TextUtils.isEmpty(bean.getContent())?"答:":bean.getContent());
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
            }
        }
    }
}
package com.jsjlzj.wayne.ui.publicac.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: PostureAuthenActivity
 * @Description: 体态达人认证
 * @Author: 曾海强
 * @CreateDate:
 */
public class PostureAuthenActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {


    @BindView(R.id.tv_is_bind)
    TextView tvIsBind;
    @BindView(R.id.tv_fens_1000)
    TextView tvFens1000;
    @BindView(R.id.tv_public_15)
    TextView tvPublic15;
    @BindView(R.id.tv_dynamic)
    TextView tvDynamic;
    @BindView(R.id.tv_zan)
    TextView tvZan;
    @BindView(R.id.tv_apply)
    TextView tvApply;

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, PostureAuthenActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_posture_authen;
    }

    @Override
    protected void initViewAndControl() {
        initTitle("体态达人认证");

        tvApply.setOnClickListener(clickListener);
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        if(view.getId() == R.id.tv_apply){

        }
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

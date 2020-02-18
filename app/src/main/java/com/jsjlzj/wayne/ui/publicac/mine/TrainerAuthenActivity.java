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
  *
  * @ClassName:      TrainerAuthenActivity
  * @Description:    教练认证
  * @Author:         曾海强
  * @CreateDate:
  */
public class TrainerAuthenActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {


    @BindView(R.id.tv_to_upload)
    TextView tvToUpload;
    @BindView(R.id.tv_to_modify)
    TextView tvToModify;
    @BindView(R.id.tv_to_public)
    TextView tvToPublic;
    @BindView(R.id.tv_apply)
    TextView tvApply;

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, TrainerAuthenActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_trainer_authen;
    }

    @Override
    protected void initViewAndControl() {
        initTitle("教练认证");

        tvToModify.setOnClickListener(clickListener);
        tvToPublic.setOnClickListener(clickListener);
        tvToUpload.setOnClickListener(clickListener);
        tvApply.setOnClickListener(clickListener);

    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.tv_to_upload://去上传
                break;
            case R.id.tv_to_modify://去修改
                break;
            case R.id.tv_to_public://去发布
                break;
            case R.id.tv_apply://去申请
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

package com.jsjlzj.wayne.ui.store.home.amoy;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;

import butterknife.BindView;

/**
 * @ClassName: SignUpResultActivity
 * @Description: 报名结果页
 * @Author: 曾海强
 * @CreateDate:
 */
public class SignUpResultActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    @BindView(R.id.tv_back)
    TextView tvBack;

    public static void go2this(Activity context,int requestCode) {
        Intent intent = new Intent(context, SignUpResultActivity.class);
        context.startActivityForResult(intent,requestCode);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_sign_up_result;
    }

    @Override
    protected void initViewAndControl() {
        initTitle("报名结果");
        tvBack.setOnClickListener(clickListener);
    }


    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        if(view.getId() == R.id.tv_back){
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

}

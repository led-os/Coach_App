package com.jsjlzj.wayne.ui.publicac.report;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.relizelogin.LoginActivityPresenter;
import com.jsjlzj.wayne.ui.mvp.relizelogin.LoginActivityView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName: ReportTypeActivity
 * @Description: 举报类型
 * @Author: 曾海强
 * @CreateDate:
 */
public class ReportTypeActivity extends MVPBaseActivity<LoginActivityView, LoginActivityPresenter> implements LoginActivityView{

    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.tv_4)
    TextView tv4;
    @BindView(R.id.tv_5)
    TextView tv5;
    @BindView(R.id.tv_6)
    TextView tv6;
    @BindView(R.id.tv_7)
    TextView tv7;
    @BindView(R.id.tv_8)
    TextView tv8;
    @BindView(R.id.tv_9)
    TextView tv9;

    public static void go2this(Context context) {
        Intent intent = new Intent(context, ReportTypeActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_report_type;
    }

    @Override
    protected void initViewAndControl() {
        initTitle("举报类型");
    }


    @Override
    protected LoginActivityPresenter createPresenter() {
        return new LoginActivityPresenter(this);
    }



    @OnClick({R.id.rel_1, R.id.rel_2, R.id.rel_3, R.id.rel_4, R.id.rel_5, R.id.rel_6, R.id.rel_7, R.id.rel_8, R.id.rel_9})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rel_1:
                ReportContentActivity.go2this(this,tv1.getText().toString());
                break;
            case R.id.rel_2:
                ReportContentActivity.go2this(this,tv2.getText().toString());
                break;
            case R.id.rel_3:
                ReportContentActivity.go2this(this,tv3.getText().toString());
                break;
            case R.id.rel_4:
                ReportContentActivity.go2this(this,tv4.getText().toString());
                break;
            case R.id.rel_5:
                ReportContentActivity.go2this(this,tv5.getText().toString());
                break;
            case R.id.rel_6:
                ReportContentActivity.go2this(this,tv6.getText().toString());
                break;
            case R.id.rel_7:
                ReportContentActivity.go2this(this,tv7.getText().toString());
                break;
            case R.id.rel_8:
                ReportContentActivity.go2this(this,tv8.getText().toString());
                break;
            case R.id.rel_9:
                ReportContentActivity.go2this(this,tv9.getText().toString());
                break;
        }
    }
}

package com.jsjlzj.wayne.ui.store.home.study;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: QuestionBankActivity
 * @Description: 国智题库
 * @Author: 曾海强
 * @CreateDate:
 */
public class QuestionBankActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    @BindView(R.id.rel_chapter)
    RelativeLayout relChapter;
    @BindView(R.id.rel_test)
    RelativeLayout relTest;
    @BindView(R.id.rel_record)
    RelativeLayout relRecord;

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, QuestionBankActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_question_bank;
    }

    @Override
    protected void initViewAndControl() {
        initTitle(getResources().getString(R.string.question_bank));
        relChapter.setOnClickListener(clickListener);
        relTest.setOnClickListener(clickListener);
        relRecord.setOnClickListener(clickListener);
    }


    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            //章节答题
            case R.id.rel_chapter:
                ChapterActivity.go2this(this);
                break;
            case R.id.rel_test:
                MockExamActivity.go2this(this);
                break;
            case R.id.rel_record:
                AchievementRecordActivity.go2this(this);
                break;
            default:
                break;
        }
    }

    @Override
    protected HomePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

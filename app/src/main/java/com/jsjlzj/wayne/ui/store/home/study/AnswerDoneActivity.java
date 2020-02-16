package com.jsjlzj.wayne.ui.store.home.study;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;

import butterknife.BindView;

public class AnswerDoneActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    @BindView(R.id.tv_fen)
    TextView tvFen;
    @BindView(R.id.tv_error_subject)
    TextView tvErrorSubject;
    @BindView(R.id.tv_again)
    TextView tvAgain;
    @BindView(R.id.img_head)
    ImageView imgHead;

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, AnswerDoneActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_answer_done;
    }

    @Override
    protected void initViewAndControl() {
        initTitle(getResources().getString(R.string.online_subject));
        imgHead.setOnClickListener(clickListener);
        tvErrorSubject.setOnClickListener(clickListener);
        tvAgain.setOnClickListener(clickListener);
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()){
            case R.id.img_head:
                break;
            case R.id.tv_error_subject:
                AnswerActivity.go2this(this,1);
                finish();
                break;
            case R.id.tv_again:
                AnswerActivity.go2this(this,2);
                finish();
                break;
        }
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }
}

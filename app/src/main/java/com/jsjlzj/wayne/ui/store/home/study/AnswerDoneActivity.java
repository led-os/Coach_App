package com.jsjlzj.wayne.ui.store.home.study;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.entity.store.learn.DoneChapterBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.GlidUtils;
import com.jsjlzj.wayne.utils.SPUtil;

import butterknife.BindView;

public class AnswerDoneActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    @BindView(R.id.tv_fen)
    TextView tvFen;
    @BindView(R.id.tv_error_subject)
    TextView tvErrorSubject;
    @BindView(R.id.tv_again)
    TextView tvAgain;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.img_head)
    ImageView imgHead;

    public static void go2this(Activity context, DoneChapterBean bean) {
        Intent intent = new Intent(context, AnswerDoneActivity.class);
        intent.putExtra(ExtraConstant.EXTRA_DATA,bean);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_answer_done;
    }

    @Override
    protected void initViewAndControl() {
        initTitle(getResources().getString(R.string.online_subject));
        DoneChapterBean bean = (DoneChapterBean) getIntent().getSerializableExtra(ExtraConstant.EXTRA_DATA);
        if(bean != null && bean.getData() != null){
            tvFen.setText(bean.getData().getScore()+"分");
            tvDes.setText("恭喜你答对"+bean.getData().getCorrectCount()+"道题目，请您再接再厉");
        }
        imgHead.setOnClickListener(clickListener);
        tvErrorSubject.setOnClickListener(clickListener);
        tvAgain.setOnClickListener(clickListener);
        MdlUser.MdlUserBean userBean = SPUtil.getUserFromSP();
        if(userBean != null){
            GlidUtils.setCircleGrid(this,userBean.getHeadImg(),imgHead);
        }
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()){
            case R.id.img_head:
                break;
            case R.id.tv_error_subject:
                AnswerActivity.go2this(this,4);
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

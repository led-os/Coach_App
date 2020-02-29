package com.jsjlzj.wayne.ui.store.home.study;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.GlidUtils;
import com.jsjlzj.wayne.utils.SPUtil;
import com.jsjlzj.wayne.widgets.TimeCounter;

import butterknife.BindView;

/**
 * @ClassName: MockExamActivity
 * @Description: 模拟考试
 * @Author: 曾海强
 * @CreateDate:
 */
public class MockExamActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    @BindView(R.id.tv_know)
    TextView tvKnow;
    @BindView(R.id.rel_des)
    RelativeLayout relDes;
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.rel_count_time)
    RelativeLayout relCountTime;

    private TimeCounter timeCounter;


    public static void go2this(Activity context) {
        Intent intent = new Intent(context, MockExamActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_mock_exam;
    }

    @Override
    protected void initViewAndControl() {
        initTitle(getResources().getString(R.string.mock_exam));
        tvKnow.setOnClickListener(clickListener);
        MdlUser.MdlUserBean bean = SPUtil.getUserFromSP();
        if(bean != null){
            GlidUtils.setCircleGrid(this,bean.getHeadImg(),imgHead);
            tvName.setText(bean.getName());
        }
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        if(view.getId() == R.id.tv_know){
            relCountTime.setVisibility(View.VISIBLE);
            relDes.setVisibility(View.GONE);
            timeCounter = new TimeCounter(4000, 1000, tvTime, R.string.zero, 0, () -> {
                AnswerActivity.go2this(MockExamActivity.this,2);
                finish();
            });
            timeCounter.start();
        }
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

}

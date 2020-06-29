package com.jsjlzj.wayne.ui.store.home.mine;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.entity.store.MdlInfo;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.SPUtil;

import androidx.annotation.Nullable;
import butterknife.BindView;

import static com.jsjlzj.wayne.ui.store.home.mine.ApplyLeaderActivity.REQUEST_CODE_APPLY;

/**
  *
  * @ClassName:      LeaderDesActivity
  * @Description:    申请成为团长界面  介绍页
  * @Author:         曾海强
  * @CreateDate:
  */
public class LeaderDesActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    @BindView(R.id.tv_commit)
    TextView tvCommit;
    /**
     * 0 : 未审核  1 ：审核中   2 ：未审核
     */
    private int state ;

    public static void go2this(Activity activity,int state){
        activity.startActivity(new Intent(activity,LeaderDesActivity.class)
                    .putExtra("state",state));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_leader_des;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initRightTitle("申请成为团长",R.drawable.ic_profit_order_info);
        state = getIntent().getIntExtra("state",0);
        if(state == 0 || state == 2){
            tvCommit.setText("立即申请");
            tvCommit.setOnClickListener(view -> {
                ApplyLeaderActivity.go2this(this, REQUEST_CODE_APPLY);
            });
        }else if (state == 1){
            tvCommit.setText("审核中");
        }
        mRightBtn.setOnClickListener(view ->
                WebViewContainerActivity.go2this(LeaderDesActivity.this,"收益说明", HttpConstant.WEB_URL_BENEFIT_INFO, WebViewContainerFragment.TYPE_PROFIT
                ));

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE_APPLY){
            tvCommit.setText("审核中");
        }
    }
}

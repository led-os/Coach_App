package com.jsjlzj.wayne.ui.publicac.mine;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;

import butterknife.BindView;

/**
 * @ClassName: InvitationActivity
 * @Description: 邀请好友界面
 * @Author: 曾海强
 * @CreateDate:
 */
public class InvitationActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {

    @BindView(R.id.img_code)
    ImageView imgCode;
    @BindView(R.id.tv_invitation)
    TextView tvInvitation;

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, InvitationActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_invitation;
    }

    @Override
    protected void initViewAndControl() {
        initRightTitle("邀请好友","我的邀请");
        mRightTv.setTextColor(ContextCompat.getColor(this,R.color.color_4F9BFA));
        mRightTv.setTextSize(15);
        mRightTv.setOnClickListener(clickListener);
        tvInvitation.setOnClickListener(clickListener);
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()){
            case R.id.tv_right_btn://我的邀请
                MineInvitationActivity.go2this(this);
                break;
            case R.id.tv_invitation://分享专属海报
                break;
        }
    }
}

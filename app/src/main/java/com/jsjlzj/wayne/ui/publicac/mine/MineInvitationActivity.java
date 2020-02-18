package com.jsjlzj.wayne.ui.publicac.mine;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.mine.MineInvitationAdapter;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;

import java.util.ArrayList;

import butterknife.BindView;

 /**
  *
  * @ClassName:      MineInvitationActivity
  * @Description:    我的邀请
  * @Author:         曾海强
  * @CreateDate:
  */
public class MineInvitationActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {


    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.tv_trainer)
    TextView tvTrainer;
    @BindView(R.id.tv_store)
    TextView tvStore;
    @BindView(R.id.rv_mine_invitation)
    RecyclerView rvMineInvitation;


    private MineInvitationAdapter adapter;

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, MineInvitationActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_mine_invitation;
    }

    @Override
    protected void initViewAndControl() {
        initTitle("我的邀请");
        tvAll.setOnClickListener(clickListener);
        tvTrainer.setOnClickListener(clickListener);
        tvStore.setOnClickListener(clickListener);

        rvMineInvitation.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MineInvitationAdapter(this,new ArrayList<>());
        rvMineInvitation.setAdapter(adapter);
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.tv_all://全部
                tvAll.setTextColor(ContextCompat.getColor(this,R.color.color_4F9BFA));
                tvStore.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                tvTrainer.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                break;
            case R.id.tv_trainer://教练
                tvAll.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                tvStore.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                tvTrainer.setTextColor(ContextCompat.getColor(this,R.color.color_4F9BFA));
                break;
            case R.id.tv_store://俱乐部
                tvAll.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                tvStore.setTextColor(ContextCompat.getColor(this,R.color.color_4F9BFA));
                tvTrainer.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                break;

        }
    }

}

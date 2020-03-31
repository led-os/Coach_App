package com.jsjlzj.wayne.ui.publicac.mine;

import android.app.Activity;
import android.content.Intent;

import androidx.fragment.app.FragmentTransaction;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.ui.store.home.community.CommunityItemFragment;
 /**
  *
  * @ClassName:      MineDynamicActivity
  * @Description:    我的动态
  * @Author:         曾海强
  * @CreateDate:
  */
public class MineDynamicActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {



    public static void go2this(Activity context) {
        Intent intent = new Intent(context, MineDynamicActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_mine_dynamic;
    }

    @Override
    protected void initViewAndControl() {
        initTitle("我的动态");
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        CommunityItemFragment fragment = CommunityItemFragment.getInstance(4,"");
        fragmentTransaction.add(R.id.frame_layout,fragment);
        fragmentTransaction.commit();

    }


     @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }


 }

package com.jsjlzj.wayne.ui.publicac.mine;

import android.app.Activity;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.mine.MineStudyAdapter;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;

import java.util.ArrayList;

import butterknife.BindView;

/**
  *
  * @ClassName:      MineStudyActivity
  * @Description:    我的学习
  * @Author:         曾海强
  * @CreateDate:
  */
public class MineStudyActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {


     @BindView(R.id.rv_study)
     RecyclerView rvStudy;

     public static void go2this(Activity context) {
         Intent intent = new Intent(context, MineStudyActivity.class);
         context.startActivity(intent);
     }


     @Override
     protected int getLayoutResId() {
         return R.layout.activity_mine_study;
     }

     @Override
     protected void initViewAndControl() {
         initTitle("我的学习");

         rvStudy.setLayoutManager(new LinearLayoutManager(this));
         MineStudyAdapter adapter = new MineStudyAdapter(this, new ArrayList<>());
         rvStudy.setAdapter(adapter);


     }

     @Override
     protected TalentTabFragmentPresenter createPresenter() {
         return new TalentTabFragmentPresenter(this);
     }


 }

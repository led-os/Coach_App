package com.jsjlzj.wayne.ui.publicac.mine;

import android.app.Activity;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.search.SearchUserAdapter;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;

import java.util.ArrayList;

import butterknife.BindView;

/**
  *
  * @ClassName:      MineFansActivity
  * @Description:    我的粉丝/关注
  * @Author:         曾海强
  * @CreateDate:
  */
public class MineFansActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {


     @BindView(R.id.rv_fans)
     RecyclerView rvFans;

    /**
     * 0:我的粉丝  1 ： 我的关注
     */
    private int type;


     public static void go2this(Activity context,int type) {
         Intent intent = new Intent(context, MineFansActivity.class);
         intent.putExtra(ExtraConstant.EXTRA_SHOW_TYPE,type);
         context.startActivity(intent);
     }


     @Override
     protected int getLayoutResId() {
         return R.layout.activity_mine_fans;
     }

     @Override
     protected void initViewAndControl() {
         type = getIntent().getIntExtra(ExtraConstant.EXTRA_SHOW_TYPE,0);
         if(type == 0){
             initTitle("我的粉丝");
         }else {
             initTitle("我的关注");
         }

         rvFans.setLayoutManager(new LinearLayoutManager(this));
         SearchUserAdapter adapter = new SearchUserAdapter(this, new ArrayList<>());
         rvFans.setAdapter(adapter);


     }

     @Override
     protected TalentTabFragmentPresenter createPresenter() {
         return new TalentTabFragmentPresenter(this);
     }


 }

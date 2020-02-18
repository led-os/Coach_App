package com.jsjlzj.wayne.ui.publicac.mine;

import android.app.Activity;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.search.SearchAdapter;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;

import java.util.ArrayList;

import butterknife.BindView;
 /**
  *
  * @ClassName:      MineDynamicActivity
  * @Description:    我的动态
  * @Author:         曾海强
  * @CreateDate:
  */
public class MineDynamicActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {


    @BindView(R.id.rv_dynamic)
    RecyclerView rvDynamic;

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

        rvDynamic.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        SearchAdapter adapter = new SearchAdapter(this, new ArrayList<>());
        rvDynamic.setAdapter(adapter);


    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }


}

package com.jsjlzj.wayne.ui.store.home.study;

import android.app.Activity;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.home.ChapterAdapter;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;

import java.util.ArrayList;

import butterknife.BindView;

/**
  *
  * @ClassName:      ChapterActivity
  * @Description:    章节答题
  * @Author:         曾海强
  * @CreateDate:
  */
public class ChapterActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView, ChapterAdapter.OnItemClickListener {

    @BindView(R.id.rv_chapter)
    RecyclerView rvChapter;

    private ChapterAdapter adapter;

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, ChapterActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_chapter;
    }

    @Override
    protected void initViewAndControl() {
        initTitle(getResources().getString(R.string.chapter_subject));
        rvChapter.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChapterAdapter(this,new ArrayList<>());
        adapter.setListener(this);
        rvChapter.setAdapter(adapter);
    }


    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public void onItemClick(String str) {
        AnswerActivity.go2this(this,0);
    }
}

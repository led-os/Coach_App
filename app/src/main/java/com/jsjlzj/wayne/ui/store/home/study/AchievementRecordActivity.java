package com.jsjlzj.wayne.ui.store.home.study;

import android.app.Activity;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.home.AchievementRecordAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.learn.AnswerRecordBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

 /**
  *
  * @ClassName:      AchievementRecordActivity
  * @Description:    成绩记录
  * @Author:         曾海强
  * @CreateDate:
  */
public class AchievementRecordActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView, AchievementRecordAdapter.OnItemClickListener {

    @BindView(R.id.rv_record)
    RecyclerView rvChapter;

    private AchievementRecordAdapter adapter;

    private List<AnswerRecordBean.DataBean> list = new ArrayList<>();

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, AchievementRecordActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_achievement_record;
    }

    @Override
    protected void initViewAndControl() {
        initTitle(getResources().getString(R.string.achievement_record));
        rvChapter.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AchievementRecordAdapter(this,list);
        adapter.setListener(this);
        rvChapter.setAdapter(adapter);
        presenter.getAnswerRecord();
    }


    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public void onItemClick(AnswerRecordBean.DataBean str) {

    }


     @Override
     public void getAnswerRecordListSuccess(MdlBaseHttpResp<AnswerRecordBean> resp) {
         if(resp.getStatus() == HttpConstant.R_HTTP_OK && resp.getData() != null){
             List<AnswerRecordBean.DataBean> list = resp.getData().getData();
             if(list != null && list.size() > 0){
                 this.list = list;
                 adapter.setData(this.list);
             }
         }
     }
 }

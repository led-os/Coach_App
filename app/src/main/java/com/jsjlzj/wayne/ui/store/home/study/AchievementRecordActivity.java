package com.jsjlzj.wayne.ui.store.home.study;

import android.app.Activity;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.home.AchievementRecordAdapter;
import com.jsjlzj.wayne.entity.store.AchievementBean;
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

    private List<AchievementBean> list = new ArrayList<>();

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
        list.add(new AchievementBean(89,"第一次","2019年11月17日 14:27"));
        list.add(new AchievementBean(96,"第二次","2019年11月17日 14:27"));
        list.add(new AchievementBean(56,"第三次","2019年11月17日 14:27"));
        list.add(new AchievementBean(62,"第四次","2019年11月17日 14:27"));
        list.add(new AchievementBean(78,"第五次","2019年11月17日 14:27"));
        rvChapter.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AchievementRecordAdapter(this,list);
        adapter.setListener(this);
        rvChapter.setAdapter(adapter);
    }


    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public void onItemClick(AchievementBean str) {

    }
}

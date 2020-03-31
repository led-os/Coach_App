package com.jsjlzj.wayne.ui.publicac.mine;

import android.app.Activity;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.mine.MineStudyAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.trainer.MineStudyBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @ClassName: MineStudyActivity
 * @Description: 我的学习
 * @Author: 曾海强
 * @CreateDate:
 */
public class MineStudyActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {


    @BindView(R.id.rv_study)
    RecyclerView rvStudy;

    private List<MineStudyBean.DataBean> studyBeans = new ArrayList<>();
    private MineStudyAdapter adapter;

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
        adapter = new MineStudyAdapter(this, studyBeans);
        rvStudy.setAdapter(adapter);

        presenter.getStudyList();
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }


    @Override
    public void getStudyListSuccess(MdlBaseHttpResp<MineStudyBean> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK) {
            List<MineStudyBean.DataBean> list = resp.getData().getData();
            if (list != null && list.size() > 0) {
                hideEmpty();
                adapter.setData(list);
            } else {
                // 无数据
                showEmpty(R.id.rel_empty, 0, null);
            }
        }
    }
}

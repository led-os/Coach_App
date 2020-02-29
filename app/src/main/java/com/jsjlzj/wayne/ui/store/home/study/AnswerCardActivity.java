package com.jsjlzj.wayne.ui.store.home.study;

import android.app.Activity;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.home.AnswerCardAdapter;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.entity.store.learn.AnswerBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.widgets.CustomGridLayoutManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @ClassName: AnswerCardActivity
 * @Description: 答题卡
 * @Author: 曾海强
 * @CreateDate:
 */
public class AnswerCardActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView, AnswerCardAdapter.OnItemClickListener {

    @BindView(R.id.rv_answer_card)
    RecyclerView rvAnswerCard;
    private List<AnswerBean> answerBeans = new ArrayList<>();
    private AnswerCardAdapter answerCardAdapter;
    private int curPos;

    public static void go2this(Activity context,List<AnswerBean> list,int curPos) {
        Intent intent = new Intent(context, AnswerCardActivity.class);
        intent.putExtra(ExtraConstant.EXTRA_DATA, (Serializable) list);
        intent.putExtra(ExtraConstant.EXTRA_HANDLE_POSITION,curPos);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_answer_card;
    }

    @Override
    protected void initViewAndControl() {
        initTitle("国职题库答题卡");
        initList();
    }

    private void initList() {
        answerBeans = (List<AnswerBean>) getIntent().getSerializableExtra(ExtraConstant.EXTRA_DATA);
        if(answerBeans != null && answerBeans.size() > 0){
            answerCardAdapter = new AnswerCardAdapter(answerBeans, this);
            CustomGridLayoutManager curriculumAdapterLayoutManager = answerCardAdapter.getLayoutManager(this);
            answerCardAdapter.setListener(this);
            rvAnswerCard.setLayoutManager(curriculumAdapterLayoutManager);
            rvAnswerCard.setAdapter(answerCardAdapter);
        }else {
            finish();
        }

    }


    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }



    @Override
    public void onItemClick(AnswerBean bean) {
        System.out.println("================================================"+bean.getName());
        AnswerActivity.go2this(this,3,bean);
    }
}

package com.jsjlzj.wayne.ui.store.home.study;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.home.AnswerAdapter;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.entity.store.AnswerBean;
import com.jsjlzj.wayne.entity.store.SelectBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.widgets.TimeCounter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @ClassName: AnswerActivity
 * @Description: 在线答题，章节答题，模拟考试
 * @Author: 曾海强
 * @CreateDate:
 */
public class AnswerActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView, View.OnClickListener {

    @BindView(R.id.tv_subject)
    TextView tvSubject;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rv_answer)
    RecyclerView rvAnswer;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.tv_answer)
    TextView tvAnswer;
    @BindView(R.id.tv_analysis)
    TextView tvAnalysis;
    @BindView(R.id.rel_analysis)
    RelativeLayout relAnalysis;
    @BindView(R.id.rel_all)
    RelativeLayout relAll;
    @BindView(R.id.ll_right)
    LinearLayout llRight;
    @BindView(R.id.tv_error_subject)
    TextView tvErrorSubject;
    @BindView(R.id.tv_again_challenge)
    TextView tvAgainChallenge;
    @BindView(R.id.tv_progress)
    TextView tvProgress;
    @BindView(R.id.tv_previous)
    TextView tvPrevious;

    private List<AnswerBean> answerBeans = new ArrayList<>();
    private AnswerBean currentAnswer;
    private int currPos;
    private AnswerAdapter adapter;

    /**
     * 0 ： 章节答题   1 ： 错题回顾   2 模拟考试  3 : 单个题目
     */
    private int showType;


    public static void go2this(Activity context, int showType) {
        Intent intent = new Intent(context, AnswerActivity.class);
        intent.putExtra(ExtraConstant.EXTRA_SHOW_TYPE, showType);
        context.startActivity(intent);
    }

    public static void go2this(Activity context, int showType, AnswerBean bean) {
        Intent intent = new Intent(context, AnswerActivity.class);
        intent.putExtra(ExtraConstant.EXTRA_SHOW_TYPE, showType);
        intent.putExtra(ExtraConstant.EXTRA_ANSWER_BEAN,bean);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_answer;
    }

    @Override
    protected void initViewAndControl() {
        showType = getIntent().getIntExtra(ExtraConstant.EXTRA_SHOW_TYPE, 0);
        initRightTitle(getResources().getString(R.string.online_subject), getResources().getString(R.string.menu));
        mRightTv.setTextColor(ContextCompat.getColor(this, R.color.color_232323));
        mRightTv.setTextSize(15);

        if(showType == 2){
            TimeCounter timeCounter = new TimeCounter(60*60*1000*2,1000,mTitleTv,R.string.time_over,1,()->{
                // TODO: 2020/2/15 考试时间到 
            });
            timeCounter.start();
        }
        List<SelectBean> option1 = new ArrayList<>();
        option1.add(new SelectBean("A 小腿在踝关节处屈", false));
        option1.add(new SelectBean("B 小腿在踝关节处伸", false));
        option1.add(new SelectBean("C 足在踝关节处屈", false));
        option1.add(new SelectBean("D 足在踝关节处伸", false));
        List<SelectBean> option2 = new ArrayList<>();
        option2.add(new SelectBean("A 正确", false));
        option2.add(new SelectBean("B 错误", false));
        List<SelectBean> option3 = new ArrayList<>();
        option3.add(new SelectBean("", false));
        answerBeans.add(new AnswerBean(0, 1, "1、接踵练习，运动环节向下运动时，描述正确的是：", option1, 0, "B", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈"));
        answerBeans.add(new AnswerBean(1, 2, "1、接踵练习，运动环节向下运动时，描述正确的是：", option1, 1, "B,D", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈"));
        answerBeans.add(new AnswerBean(2, 3, "1、接踵练习，运动环节向下运动时，描述正确的是：", option3, 3, "123456", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈"));
        answerBeans.add(new AnswerBean(3, 4, "1、接踵练习，运动环节向下运动时，描述正确的是：", option1, 0, "C", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈"));
        answerBeans.add(new AnswerBean(4, 5, "1、接踵练习，运动环节向下运动时，描述正确的是：", option2, 2, "C", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈"));

        currentAnswer = answerBeans.get(currPos);
        adapter = new AnswerAdapter(this, currentAnswer.getOptions());
        adapter.setType(currentAnswer.getType());
        rvAnswer.setLayoutManager(new LinearLayoutManager(this));
        rvAnswer.setAdapter(adapter);

        refreshActivity();
        tvNext.setOnClickListener(this);
        mRightTv.setOnClickListener(this);
        relAll.setOnClickListener(this);
        tvErrorSubject.setOnClickListener(this);
        tvAgainChallenge.setOnClickListener(this);
        tvPrevious.setOnClickListener(this);
    }


    /**
     * 刷新界面
     */
    private void refreshActivity() {
        switch (currentAnswer.getType()) {
            case 0:
                tvSubject.setText("一、单选题");
                break;
            case 1:
                tvSubject.setText("二、多选题");
                break;
            case 2:
                tvSubject.setText("三、判断题");
                break;
            case 3:
                tvSubject.setText("四、填空题题");
                break;
            default:
                break;
        }
        tvName.setText(currentAnswer.getName());
        if(currentAnswer.getTopicNum() > 0){
            tvProgress.setText(currentAnswer.getTopicNum()+"/5");
        }
        adapter.setType(currentAnswer.getType());
        adapter.setData(currentAnswer.getOptions());
        switch (showType) {
            case 0:
                tvPrevious.setVisibility(View.GONE);
                tvProgress.setVisibility(View.GONE);
                relAnalysis.setVisibility(View.GONE);
                break;
            case 1:
                mRightTv.setVisibility(View.GONE);
                if(currPos == 0 || currPos == answerBeans.size() -1){
                    tvPrevious.setVisibility(View.GONE);
                    if(currPos == answerBeans.size() -1){
                        tvNext.setText(getResources().getString(R.string.done));
                    }else {
                        tvNext.setText(getResources().getString(R.string.next_subject));
                    }
                }else {
                    tvPrevious.setVisibility(View.VISIBLE);
                }
                tvProgress.setVisibility(View.VISIBLE);
                relAnalysis.setVisibility(View.VISIBLE);
                tvAnalysis.setText(currentAnswer.getAnalysis());
                tvAnswer.setText(currentAnswer.getAnswer());
                break;
            case 2:
                mRightTv.setText(getResources().getString(R.string.answer_card));
                if(currPos == 0 || currPos == answerBeans.size() -1){
                    tvPrevious.setVisibility(View.GONE);
                    if(currPos == answerBeans.size() -1){
                        tvNext.setText(getResources().getString(R.string.done));
                    } else {
                        tvNext.setText(getResources().getString(R.string.next_subject));
                    }
                }else {
                    tvPrevious.setVisibility(View.VISIBLE);
                }
                tvProgress.setVisibility(View.VISIBLE);
                relAnalysis.setVisibility(View.GONE);
                break;
        }


    }

    /**
     * 获取下一道题
     *
     * @param pos
     * @return
     */
    private void getCurrentAnswer(int pos,boolean isAdd) {
        if (answerBeans != null && answerBeans.size() > 0) {
            if(isAdd){
                if (pos < answerBeans.size() - 1) {
                    currPos = pos + 1;
                } else {
                    currPos = 0;
                }
            }else {
                if (pos > 0) {
                    currPos = pos - 1;
                } else {
                    currPos = answerBeans.size() - 1;
                }
            }
            currentAnswer = answerBeans.get(currPos);
        }
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    private boolean isShowMenu;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_right_btn:
                if(showType == 0){
                    if(isShowMenu){
                        isShowMenu = false;
                        llRight.setVisibility(View.GONE);
                    }else {
                        isShowMenu = true;
                        llRight.setVisibility(View.VISIBLE);
                    }
                }else {
                    if(getResources().getString(R.string.answer_card).equals(mRightTv.getText().toString())){
                        //答题卡
                        AnswerCardActivity.go2this(this);
                    }
                }

                break;
            case R.id.tv_next:
                llRight.setVisibility(View.GONE);
                isShowMenu = false;
                getCurrentAnswer(currPos,true);
                refreshActivity();
                if(showType == 2 && tvNext.getText().toString().equals(getResources().getString(R.string.done))){
                    //完成模拟考试
                    AnswerDoneActivity.go2this(this);
                }
                break;
                //错题回顾
            case R.id.tv_error_subject:
                AnswerActivity.go2this(this,1);
                break;
                //重新挑战
            case R.id.tv_again_challenge:
                break;
            case R.id.rel_all:
                llRight.setVisibility(View.GONE);
                isShowMenu = false;
                break;
            case R.id.tv_previous:
                getCurrentAnswer(currPos,false);
                refreshActivity();
                break;
            default:
                break;
        }

    }
}

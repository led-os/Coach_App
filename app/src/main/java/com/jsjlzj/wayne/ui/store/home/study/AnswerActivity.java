package com.jsjlzj.wayne.ui.store.home.study;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.home.AnswerAdapter;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.learn.ChapterListBean;
import com.jsjlzj.wayne.entity.store.learn.SelectBean;
import com.jsjlzj.wayne.entity.store.learn.AnswerBean;
import com.jsjlzj.wayne.entity.store.learn.ChapterSubjectListBean;
import com.jsjlzj.wayne.entity.store.learn.DoneChapterBean;
import com.jsjlzj.wayne.entity.store.learn.ExamSubjectListBean;
import com.jsjlzj.wayne.entity.store.learn.SubmitAnswerBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.widgets.TimeCounter;
import com.netease.nim.uikit.common.ToastHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Map<Object,Object> map = new HashMap<>();
    private AnswerBean currentAnswer;
    private List<SelectBean> selectList = new ArrayList<>();
    private List<SubmitAnswerBean> submitAnswerList = new ArrayList<>();
    private int currPos;
    private AnswerAdapter adapter;

    /**
     * 0 ： 章节答题   1 ： 章节错题回顾   2 模拟考试  3 : 单个题目   4：模拟考试错题回顾
     */
    private int showType;
    /**
     * 章节id
     */
    private int chapterId;
    /**
     * 是否显示菜单
     */
    private boolean isShowMenu;
    private boolean isCommitAnswer = true;

    public static void go2this(Activity context, int showType,int chapterId) {
        Intent intent = new Intent(context, AnswerActivity.class);
        intent.putExtra(ExtraConstant.EXTRA_SHOW_TYPE, showType);
        intent.putExtra(ExtraConstant.EXTRA_CHAPTER_ID,chapterId);
        context.startActivity(intent);
    }

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
        switch (showType){
            case 0:
                chapterId = getIntent().getIntExtra(ExtraConstant.EXTRA_CHAPTER_ID,0);
                map.put("id",chapterId);
                presenter.getChapterSubjectList(map);
                break;
            case 1:
                chapterId = getIntent().getIntExtra(ExtraConstant.EXTRA_CHAPTER_ID,0);
                map.put("id",chapterId);
                presenter.getWrongSubjectList(map);
                break;
            case 2:
                presenter.getExamSubjectList();
                TimeCounter timeCounter = new TimeCounter(60*60*1000*2,1000,mTitleTv,R.string.time_over,1,()->{
                    // TODO: 2020/2/15 考试时间到
                });
                timeCounter.start();
                break;
            case 3:
                currentAnswer = (AnswerBean) getIntent().getSerializableExtra(ExtraConstant.EXTRA_ANSWER_BEAN);
                adapter = new AnswerAdapter(this,selectList);
                adapter.setType(currentAnswer.getType());
                rvAnswer.setLayoutManager(new LinearLayoutManager(this));
                rvAnswer.setAdapter(adapter);
                refreshActivity();
                break;
            case 4:
                presenter.getTestResult();
                break;
        }
        tvNext.setOnClickListener(this);
        mRightTv.setOnClickListener(this);
        relAll.setOnClickListener(this);
        tvErrorSubject.setOnClickListener(this);
        tvAgainChallenge.setOnClickListener(this);
        tvPrevious.setOnClickListener(this);
    }


    @Override
    public void getChapterSubjectListSuccess(MdlBaseHttpResp<ChapterSubjectListBean> resp) {
        if(resp.getData() != null){
            initDaDa(resp.getData().getData(),resp.getStatus());
        }
    }

    @Override
    public void getExamSubjectListSuccess(MdlBaseHttpResp<ExamSubjectListBean> resp) {
        if(resp.getData()!= null && resp.getData().getData() != null){
            initDaDa(resp.getData().getData().getTopics(),resp.getStatus());
        }
    }

    public void initDaDa(List<AnswerBean> list,int status){
        if (status == HttpConstant.R_HTTP_OK && null != list && list.size() > 0) {
            answerBeans = list;
            currentAnswer = answerBeans.get(currPos);
            adapter = new AnswerAdapter(this,selectList);
            adapter.setType(currentAnswer.getType());
            rvAnswer.setLayoutManager(new LinearLayoutManager(this));
            rvAnswer.setAdapter(adapter);
            refreshActivity();
        }
    }

    @Override
    public void submitExamAnswerSuccess(MdlBaseHttpResp<DoneChapterBean> resp) {
        AnswerDoneActivity.go2this(this,resp.getData());
        finish();
    }

    @Override
    public void saveAnswerSuccess(MdlBaseHttpResp<String> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK ){
            ToastHelper.showToast(this,"提交成功");
        }
    }


    @Override
    public void getChapterListSuccess(MdlBaseHttpResp<ChapterListBean> resp) {
        // TODO: 2020/2/29 获取单个列表成功 
    }

    /**
     * 刷新界面
     */
    private void refreshActivity() {
        switch (currentAnswer.getType()) {
            case 1:
                tvSubject.setText("一、单选题");
                break;
            case 2:
                tvSubject.setText("二、多选题");
                break;
            case 3:
                tvSubject.setText("三、判断题");
                break;
            case 4:
                tvSubject.setText("四、填空题题");
                break;
            default:
                break;
        }
        tvName.setText(currentAnswer.getTopicNum() + "、"+currentAnswer.getName());
        tvProgress.setText((currPos+1)+"/"+answerBeans.size());
        selectList = JSONObject.parseArray(currentAnswer.getOptions(), SelectBean.class);

        adapter.setType(currentAnswer.getType());
        if(currentAnswer.getType() == 4 && selectList == null){
            selectList = new ArrayList<>();
            selectList.add(new SelectBean("","",false,currentAnswer.getAnswer()));
        }else {
            for (int i = 0; i < selectList.size(); i++) {
                SelectBean bean = selectList.get(i);
                bean.setAnswer(currentAnswer.getAnswer());
            }
        }

        switch (showType) {
            case 3:
                tvPrevious.setVisibility(View.GONE);
                tvProgress.setVisibility(View.GONE);
                relAnalysis.setVisibility(View.GONE);
                tvNext.setVisibility(View.GONE);
                adapter.setData(selectList,currentAnswer.getWrongAnswer());
                break;
            case 0:
                tvPrevious.setVisibility(View.GONE);
                tvProgress.setVisibility(View.GONE);
                relAnalysis.setVisibility(View.GONE);
                adapter.setData(selectList);
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
                relAnalysis.setVisibility(View.GONE);
                tvAnalysis.setText(currentAnswer.getAnalysis());
                tvAnswer.setText(currentAnswer.getAnswer());
                adapter.setData(selectList);
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
                adapter.setData(selectList);
                break;
//            case 3:
//                tvPrevious.setVisibility(View.GONE);
//                tvProgress.setVisibility(View.GONE);
//                relAnalysis.setVisibility(View.GONE);
//                adapter.setData(selectList);
//                break;
            case 4:
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
                relAnalysis.setVisibility(View.VISIBLE);
                tvAnalysis.setText(currentAnswer.getAnalysis());
                tvAnswer.setText(currentAnswer.getAnswer());
                adapter.setCorrectAnswer(true);
                adapter.setData(selectList);
                break;
        }

    }

    /**
     * 获取下一道题
     *
     * @param pos
     * @return
     */
    private void getCurrentAnswer(int pos,boolean isNext) {
        if (answerBeans != null && answerBeans.size() > 0) {
            if(isNext){
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
                        AnswerCardActivity.go2this(this,answerBeans,currPos);
                    }
                }

                break;
            case R.id.tv_next:
                llRight.setVisibility(View.GONE);
                isShowMenu = false;
                if(isCommitAnswer){
                    String answer = adapter.getResult();
                    if(TextUtils.isEmpty(answer)){
                        return;
                    }
                    if(showType == 0 || showType == 2){
                        isCommitAnswer = true;
                        map.clear();
                        if(showType == 0){
                            map.put("answer",answer);
                            map.put("result",getResult(answer));
                            map.put("topicId",currentAnswer.getId());
                            presenter.getSaveAnswerRecord(map);
                        }else {
                            currentAnswer.setAnswer(true);
                            currentAnswer.setWrongAnswer(adapter.getResult());
                            submitAnswerList.add(new SubmitAnswerBean(answer,currentAnswer.getId()));
                        }
                        if(showType == 2 && tvNext.getText().toString().equals(getResources().getString(R.string.done))){
                            //完成模拟考试
                            map.clear();
                            map.put("answers",submitAnswerList);
                            presenter.submitExamAnswer(map);
                            return;
                        }
                        getCurrentAnswer(currPos,true);
                        refreshActivity();
                    }else if(showType == 1){
                        isCommitAnswer = false;
                        adapter.setCorrectAnswer(true);
                        relAnalysis.setVisibility(View.VISIBLE);
                    }else if(showType == 4){
                        isCommitAnswer = true;
                        getCurrentAnswer(currPos,true);
                        refreshActivity();
                        if(showType == 4 && tvNext.getText().toString().equals(getResources().getString(R.string.done))){
                            finish();
                        }
                    }
                }else {
                    isCommitAnswer = true;
                    if(showType == 0 || showType == 2){
                        getCurrentAnswer(currPos,true);
                        refreshActivity();
                    }else if(showType == 1){
                        getCurrentAnswer(currPos,true);
                        adapter.setCorrectAnswer(false);
                        refreshActivity();
                    }

                    if(showType == 1 && tvNext.getText().toString().equals(getResources().getString(R.string.done))){
                        finish();
                    }
                }
                break;
                //错题回顾
            case R.id.tv_error_subject:
                AnswerActivity.go2this(this,1,chapterId);
                break;
                //重新挑战
            case R.id.tv_again_challenge:
                getCurrentAnswer(answerBeans.size() -1 ,true);
                refreshActivity();
                break;
            case R.id.rel_all:
                llRight.setVisibility(View.GONE);
                isShowMenu = false;
                break;
            case R.id.tv_previous:
                getCurrentAnswer(currPos,false);
                adapter.setCorrectAnswer(false);
                refreshActivity();
                break;
            default:
                break;
        }

    }


    /**
     * 判断是否正确
     * @param answer 答案
     * @return 0 ：错误  1 正确
     */
    private int getResult(String answer){
        int result;
        if(answer.equals(currentAnswer.getAnswer())){
            result = 1;
        }else {
            result = 0;
        }
        return result;
    }

}

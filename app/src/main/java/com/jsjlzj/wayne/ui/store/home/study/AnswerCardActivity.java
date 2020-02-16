package com.jsjlzj.wayne.ui.store.home.study;

import android.app.Activity;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.home.AnswerCardAdapter;
import com.jsjlzj.wayne.entity.store.AnswerBean;
import com.jsjlzj.wayne.entity.store.SelectBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.widgets.CustomGridLayoutManager;

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

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, AnswerCardActivity.class);
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
        answerBeans.add(new AnswerBean(0, 1, "1、接踵练习，运动环节向下运动时，描述正确的是：", option1, 0, "B", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈",true));
        answerBeans.add(new AnswerBean(1, 2, "1、接踵练习，运动环节向下运动时，描述正确的是：", option1, 0, "B", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈",false));
        answerBeans.add(new AnswerBean(2, 3, "1、接踵练习，运动环节向下运动时，描述正确的是：", option1, 0, "B", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈",true));
        answerBeans.add(new AnswerBean(3, 4, "1、接踵练习，运动环节向下运动时，描述正确的是：", option1, 0, "B,D", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈",true));
        answerBeans.add(new AnswerBean(4, 5, "1、接踵练习，运动环节向下运动时，描述正确的是：", option1, 0, "B,D", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈",true));
        answerBeans.add(new AnswerBean(5, 6, "1、接踵练习，运动环节向下运动时，描述正确的是：", option1, 0, "B,D", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈",true));
        answerBeans.add(new AnswerBean(6, 7, "1、接踵练习，运动环节向下运动时，描述正确的是：", option3, 1, "123456", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈",true));
        answerBeans.add(new AnswerBean(7, 8, "1、接踵练习，运动环节向下运动时，描述正确的是：", option1, 1, "C", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈",true));
        answerBeans.add(new AnswerBean(8, 9, "1、接踵练习，运动环节向下运动时，描述正确的是：", option1, 1, "C", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈",true));
        answerBeans.add(new AnswerBean(9, 10, "1、接踵练习，运动环节向下运动时，描述正确的是：", option1, 1, "C", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈",true));
        answerBeans.add(new AnswerBean(10, 11, "1、接踵练习，运动环节向下运动时，描述正确的是：", option1, 1, "C", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈",false));
        answerBeans.add(new AnswerBean(11, 12, "1、接踵练习，运动环节向下运动时，描述正确的是：", option1, 1, "C", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈",false));
        answerBeans.add(new AnswerBean(12, 13, "1、接踵练习，运动环节向下运动时，描述正确的是：", option2, 1, "C", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈",true));
        answerBeans.add(new AnswerBean(13, 14, "1、接踵练习，运动环节向下运动时，描述正确的是：", option2, 1, "C", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈",true));
        answerBeans.add(new AnswerBean(14, 15, "1、接踵练习，运动环节向下运动时，描述正确的是：", option2, 1, "C", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈",true));
        answerBeans.add(new AnswerBean(15, 16, "1、接踵练习，运动环节向下运动时，描述正确的是：", option2, 1, "C", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈",true));
        answerBeans.add(new AnswerBean(16, 17, "1、接踵练习，运动环节向下运动时，描述正确的是：", option2, 1, "C", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈",true));
        answerBeans.add(new AnswerBean(17, 18, "1、接踵练习，运动环节向下运动时，描述正确的是：", option2, 1, "C", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈",true));
        answerBeans.add(new AnswerBean(18, 19, "1、接踵练习，运动环节向下运动时，描述正确的是：", option2, 2, "C", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈",true));
        answerBeans.add(new AnswerBean(19, 20, "1、接踵练习，运动环节向下运动时，描述正确的是：", option2, 2, "C", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈",true));
        answerBeans.add(new AnswerBean(20, 21, "1、接踵练习，运动环节向下运动时，描述正确的是：", option2, 2, "C", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈",true));
        answerBeans.add(new AnswerBean(21, 22, "1、接踵练习，运动环节向下运动时，描述正确的是：", option2, 2, "C", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈",true));
        answerBeans.add(new AnswerBean(22, 23, "1、接踵练习，运动环节向下运动时，描述正确的是：", option2, 2, "C", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈",true));
        answerBeans.add(new AnswerBean(23, 24, "1、接踵练习，运动环节向下运动时，描述正确的是：", option2, 3, "C", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈",true));
        answerBeans.add(new AnswerBean(24, 25, "1、接踵练习，运动环节向下运动时，描述正确的是：", option2, 3, "C", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈",true));
        answerBeans.add(new AnswerBean(25, 26, "1、接踵练习，运动环节向下运动时，描述正确的是：", option2, 3, "C", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈",true));
        answerBeans.add(new AnswerBean(26, 27, "1、接踵练习，运动环节向下运动时，描述正确的是：", option2, 3, "C", "接踵练习，运动环节向下运动时，描述正确的是：足在踝关节处屈",true));

        answerCardAdapter = new AnswerCardAdapter(answerBeans, this);
        CustomGridLayoutManager curriculumAdapterLayoutManager = answerCardAdapter.getLayoutManager(this);
        answerCardAdapter.setListener(this);
        rvAnswerCard.setLayoutManager(curriculumAdapterLayoutManager);
        rvAnswerCard.setAdapter(answerCardAdapter);
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

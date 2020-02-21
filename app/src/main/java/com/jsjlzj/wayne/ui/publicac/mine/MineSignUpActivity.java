package com.jsjlzj.wayne.ui.publicac.mine;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.mine.MineSignUpAdapter;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * @ClassName: MineSignUpActivity
 * @Description: 我的报告
 * @Author: 曾海强
 * @CreateDate:
 */
public class MineSignUpActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {


    @BindView(R.id.tv_all_sign_up)
    TextView tvAllSignUp;
    @BindView(R.id.tv_match)
    TextView tvMatch;
    @BindView(R.id.tv_course)
    TextView tvCourse;
    @BindView(R.id.rv_sign_up)
    RecyclerView rvSignUp;

    private MineSignUpAdapter adapter;
    private List<String> allList = new ArrayList<>();

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, MineSignUpActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_mine_sign_up;
    }

    @Override
    protected void initViewAndControl() {
        initTitle("我的报名");

        tvCourse.setOnClickListener(clickListener);
        tvMatch.setOnClickListener(clickListener);
        tvAllSignUp.setOnClickListener(clickListener);
        rvSignUp.setLayoutManager(new LinearLayoutManager(this));
        allList.add("1");
        allList.add("2");
        allList.add("1");
        allList.add("2");
        allList.add("2");
        allList.add("1");
        allList.add("1");
        allList.add("1");
        adapter = new MineSignUpAdapter(this, allList);
        rvSignUp.setAdapter(adapter);
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }


    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()){
            case R.id.tv_all_sign_up://全部报名
                tvAllSignUp.setTextColor(ContextCompat.getColor(this,R.color.color_4F9BFA));
                tvCourse.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                tvMatch.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                allList.clear();
                allList.add("1");
                allList.add("2");
                allList.add("1");
                allList.add("2");
                allList.add("2");
                allList.add("1");
                allList.add("1");
                allList.add("1");
                adapter.setData(allList);
                break;
            case R.id.tv_match://赛事报名
                tvAllSignUp.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                tvCourse.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                tvMatch.setTextColor(ContextCompat.getColor(this,R.color.color_4F9BFA));
                allList.clear();
                allList.add("1");
                allList.add("1");
                allList.add("1");
                allList.add("1");
                allList.add("1");
                adapter.setData(allList);
                break;
            case R.id.tv_course://课程报名
                tvAllSignUp.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                tvCourse.setTextColor(ContextCompat.getColor(this,R.color.color_4F9BFA));
                tvMatch.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                allList.clear();
                allList.add("2");
                allList.add("2");
                allList.add("2");
                adapter.setData(allList);
                break;
        }
    }
}

package com.jsjlzj.wayne.ui.trainer.personal;


import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.EducationPreAdapter;
import com.jsjlzj.wayne.adapter.JobIntentionAdapter;
import com.jsjlzj.wayne.adapter.TrainerPreExpAdapter;
import com.jsjlzj.wayne.entity.trainer.MdlDetailT;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.widgets.MyListView;

import butterknife.BindView;

/**
 * @ClassName: OnlineResumeFragment
 * @Description: 在线简历
 * @Author: 曾海强
 * @CreateDate:
 */
public class OnlineResumeFragment extends MVPBaseFragment<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {

    @BindView(R.id.btn_job_intention)
    TextView btnJobIntention;
    @BindView(R.id.rv_job_intention)
    MyListView rvJobIntention;
    @BindView(R.id.rv_exp)
    MyListView rvExp;
    @BindView(R.id.rv_education)
    MyListView rvEducation;
    @BindView(R.id.rv_authentication_img)
    RecyclerView rvAuthenticationImg;
    /**
     * 数据
     */
    private MdlDetailT.DataBean data;
    /**
     * 求职意愿
     */
    private JobIntentionAdapter jobIntentionAdapter;
    private TrainerPreExpAdapter expAdapter;
    private EducationPreAdapter educationAdapter;

    public OnlineResumeFragment() {
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_online_resume;
    }

    @Override
    protected void initViewAndControl(View view) {

    }


    public void setData(MdlDetailT.DataBean data) {
        if(data == null){
            return;
        }
        this.data = data;
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        jobIntentionAdapter = new JobIntentionAdapter(getActivity());
        rvJobIntention.setAdapter(jobIntentionAdapter);
        jobIntentionAdapter.setData(data.getWorkHopeList());

        expAdapter = new TrainerPreExpAdapter(getActivity());
        rvExp.setAdapter(expAdapter);
        expAdapter.setData(data.getWorkExperienceList());

        educationAdapter = new EducationPreAdapter(getActivity());
        rvEducation.setAdapter(educationAdapter);
        educationAdapter.setData(data.getEducationExperienceList());
    }

    @Override
    protected void fragment2Front() {

    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }


}

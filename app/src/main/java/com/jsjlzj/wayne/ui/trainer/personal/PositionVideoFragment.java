package com.jsjlzj.wayne.ui.trainer.personal;


import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.mine.PositionVideoAdapter;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.entity.store.home.VideoBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @ClassName: PositionVideoFragment
 * @Description: 教学视频
 * @Author: 曾海强
 * @CreateDate:
 */
public class PositionVideoFragment extends MVPBaseFragment<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {


    @BindView(R.id.rv_video)
    RecyclerView rvVideo;

    private PositionVideoAdapter adapter;
    private List<VideoBean> videoBeans = new ArrayList<>();


    public PositionVideoFragment() {}


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_position_video;
    }

    @Override
    protected void initViewAndControl(View view) {
        if(getArguments() != null){
            videoBeans = (List<VideoBean>) getArguments().getSerializable(ExtraConstant.EXTRA_DATA);
        }
        rvVideo.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new PositionVideoAdapter(getActivity(),videoBeans);
        rvVideo.setAdapter(adapter);
        if(videoBeans == null || videoBeans.size() <= 0){
            showEmpty(R.id.rel_empty,0,null);
        }
    }

    @Override
    protected void fragment2Front() {

    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }


}

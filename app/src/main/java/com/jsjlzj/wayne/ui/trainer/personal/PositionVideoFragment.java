package com.jsjlzj.wayne.ui.trainer.personal;


import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.mine.PositionVideoAdapter;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;

import java.util.ArrayList;

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

    public PositionVideoFragment() {
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_position_video;
    }

    @Override
    protected void initViewAndControl(View view) {
        rvVideo.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new PositionVideoAdapter(getActivity(),new ArrayList<>());
        rvVideo.setAdapter(adapter);
    }

    @Override
    protected void fragment2Front() {

    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }


}

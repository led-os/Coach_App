package com.jsjlzj.wayne.ui.store.home;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.home.HomeStudyAdapter;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.ui.store.home.amoy.SignUpActivity;
import com.jsjlzj.wayne.ui.store.home.study.QuestionBankActivity;
import com.jsjlzj.wayne.widgets.LocalImageHolderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @description 学习
 * @date: 2019/12/30
 * @author: 曾海强
 */
public class TabItemStudyFragment extends MVPBaseFragment<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView, HomeStudyAdapter.OnItemClickListener {

    @BindView(R.id.scroll_banner)
    ConvenientBanner scrollBanner;
    @BindView(R.id.rv_study)
    RecyclerView rvStudy;

    private HomeStudyAdapter adapter;

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, TabItemStudyFragment.class);
        context.startActivity(intent);
    }

    public static Fragment getInstance() {
        TabItemStudyFragment fragment = new TabItemStudyFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_tab_item_study;
    }


    @Override
    protected void initViewAndControl(View view) {
//        initBanner();
        rvStudy.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HomeStudyAdapter(getActivity(), new ArrayList<>());
        adapter.setListener(this);
        rvStudy.setAdapter(adapter);
    }


    @Override
    protected void fragment2Front() {
    }


    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
    }

    @Override
    public void onItemClick(String str, int pos) {
        if (pos == 1) {
            QuestionBankActivity.go2this(getActivity());
        } else if(pos == 0){

        }
    }

    private void initBanner() {
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.ic_avatars);
        images.add(R.drawable.ic_avatars);
        images.add(R.drawable.ic_avatars);
        scrollBanner.setPages(
                new CBViewHolderCreator() {
                    @Override
                    public LocalImageHolderView createHolder(View itemView) {
                        return new LocalImageHolderView(itemView);
                    }

                    @Override
                    public int getLayoutId() {
                        return R.layout.item_localimage;
                    }
                }, images)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.bg_circle_ccfffff_6, R.drawable.bg_circle_4f9bfa_6})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(position -> {
                    SignUpActivity.go2this(getActivity());
                })
                .setCanLoop(true);
    }


    @Override
    public void onResume() {
        super.onResume();
        scrollBanner.startTurning();
    }

    @Override
    public void onPause() {
        super.onPause();
        scrollBanner.stopTurning();
    }

}

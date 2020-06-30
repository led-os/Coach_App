package com.jsjlzj.wayne.ui.store.home;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.home.HomeStudyAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.home.BannerBean;
import com.jsjlzj.wayne.entity.store.learn.LearnBean;
import com.jsjlzj.wayne.entity.store.learn.LibraryBean;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
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
public class TabItemStudyFragment extends MVPBaseFragment<HomeView, HomePresenter> implements HomeView, HomeStudyAdapter.OnItemClickListener {

    @BindView(R.id.scroll_banner)
    ConvenientBanner scrollBanner;
    @BindView(R.id.rv_study)
    RecyclerView rvStudy;

    private List<BannerBean> images = new ArrayList<>();
    private List<LibraryBean> libraryBeans = new ArrayList<>();
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
        rvStudy.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HomeStudyAdapter(getActivity(), libraryBeans);
        adapter.setListener(this);
        rvStudy.setAdapter(adapter);
        presenter.getLearnData();
    }


    @Override
    protected void fragment2Front() {
    }


    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public void onItemClick(LibraryBean bean, int pos) {
        if(bean.getId() == -1){
            QuestionBankActivity.go2this(getActivity());
        }else if(bean.getId() == -2){
            WebViewContainerActivity.go2this(getActivity(),bean.getTitle(),HttpConstant.WEB_URL_SCAN_SCORE,
                    WebViewContainerFragment.TYPE_SCAN_SCORE);
        }else {
            WebViewContainerActivity.go2this(getActivity(),bean.getTitle(),HttpConstant.WEB_URL_COURSE_INTRODUCE + bean.getId(),
                    WebViewContainerFragment.TYPE_COURSE_INTRODUCE);
        }

    }

    private void initBanner() {
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
                    BannerBean bean = images.get(position);
                    if(!TextUtils.isEmpty(bean.getLink())){
                        WebViewContainerActivity.go2this(getActivity(),bean.getTitle(),bean.getLink(), WebViewContainerFragment.TYPE_BANNER_LINK_URL);
                    }
                })
                .setCanLoop(images.size() > 1);
    }

    @Override
    public void getLearnDataSuccess(MdlBaseHttpResp<LearnBean> resp) {
        LearnBean.DataBean bean = resp.getData().getData();
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != bean) {
            if (null != bean.getBanner() && bean.getBanner().size() > 0) {
                images = bean.getBanner();
                if (images != null && images.size() > 0) {
                    initBanner();
                }
            }

            if (null != bean.getLibrary() && bean.getLibrary().size() > 0) {
                libraryBeans = bean.getLibrary();
                if (libraryBeans != null && libraryBeans.size() > 0) {
                    libraryBeans.add(new LibraryBean("",-1,"国职题库"));
                    libraryBeans.add(new LibraryBean("",-2,"国职考试成绩查询"));
                    adapter.setData(libraryBeans);
                }
            }
        }
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

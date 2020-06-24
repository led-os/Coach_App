package com.jsjlzj.wayne.ui.store.find;


import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.find.ClassRecommendAdapter;
import com.jsjlzj.wayne.adapter.recycler.find.CourserFindAdapter;
import com.jsjlzj.wayne.adapter.recycler.find.DayStudyAdapter;
import com.jsjlzj.wayne.adapter.recycler.find.FreeExperAdapter;
import com.jsjlzj.wayne.adapter.recycler.find.MotionFindAdapter;
import com.jsjlzj.wayne.adapter.recycler.find.QuestionFindAdapter;
import com.jsjlzj.wayne.adapter.recycler.find.VipSelectAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.find.FindLessonBean;
import com.jsjlzj.wayne.entity.find.OptimizationData1Bean;
import com.jsjlzj.wayne.entity.find.OptimizationData2Bean;
import com.jsjlzj.wayne.entity.store.home.BannerBean;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.ui.store.home.ContentFragmentTitleActivity;
import com.jsjlzj.wayne.widgets.LocalImageHolderView;
import com.jsjlzj.wayne.widgets.MyViewPager;
import com.jsjlzj.wayne.widgets.NestedRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @ClassName: 优选fragment
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/04/18
 */
public class OptimizationFragment extends MVPBaseFragment<HomeView, HomePresenter> implements HomeView, OnRefreshListener {


    @BindView(R.id.scroll_banner)
    ConvenientBanner scrollBanner;
    @BindView(R.id.ll_amoy)
    LinearLayout llAmoy;
    @BindView(R.id.ll_state_duty)
    LinearLayout llStateDuty;

    @BindView(R.id.ll_match)
    LinearLayout llMatch;
    @BindView(R.id.ll_information)
    LinearLayout llInformation;
    @BindView(R.id.img_open_vip)
    ImageView imgOpenVip;
    @BindView(R.id.tv_free_more)
    TextView tvFreeMore;
    @BindView(R.id.img_video_right)
    ImageView imgVideoRight;
    @BindView(R.id.rv_free)
    NestedRecyclerView rvFree;
    @BindView(R.id.rv_day_study)
    NestedRecyclerView rvDayStudy;
    @BindView(R.id.tv_content_title)
    TextView tvContentTitle;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.rv_vip_select)
    NestedRecyclerView rvVipSelect;
    @BindView(R.id.tv_motion)
    TextView tvMotion;
    @BindView(R.id.tv_motion_more)
    TextView tvMotionMore;
    @BindView(R.id.rv_motion)
    NestedRecyclerView rvMotion;
    @BindView(R.id.tv_content_title_2)
    TextView tvContentTitle2;
    @BindView(R.id.rv_content_2)
    NestedRecyclerView rvContent2;
    @BindView(R.id.tv_scan_all)
    TextView tvScanAll;
    @BindView(R.id.rv_class_recommend)
    RecyclerView rvClassRecommend;
    @BindView(R.id.tv_open_vip)
    TextView tvOpenVip;
    @BindView(R.id.img_close)
    ImageView imgClose;
    @BindView(R.id.rel_vip)
    RelativeLayout relVip;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.ll_store)
    LinearLayout llStore;
    @BindView(R.id.ll_state_ydxh)
    LinearLayout llStateYdxh;
    @BindView(R.id.ll_ydk)
    LinearLayout llYdk;
    @BindView(R.id.ll_bk)
    LinearLayout llBk;
    private MyViewPager myViewPager;

    private List<BannerBean> images = new ArrayList<>();
    private List<FindLessonBean> freeExperList = new ArrayList();
    private FreeExperAdapter freeExperAdapter;
    private DayStudyAdapter dayStudyAdapter;
    private QuestionFindAdapter questionFindAdapter;
    private VipSelectAdapter vipSelectAdapter;
    private MotionFindAdapter motionFindAdapter;
    private CourserFindAdapter courserFindAdapter;
    private ClassRecommendAdapter classRecommendAdapter;

    public static OptimizationFragment getInstance(MyViewPager myViewPager) {
        OptimizationFragment fragment = new OptimizationFragment(myViewPager);
        return fragment;
    }

    public OptimizationFragment() {
    }

    public OptimizationFragment(MyViewPager myViewPager) {
        this.myViewPager = myViewPager;
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_optimization;
    }

    @Override
    protected void initViewAndControl(View view) {
        initRecycler();
        presenter.getOptimizationData1();
        presenter.getOptimizationData2();
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(this);
        llAmoy.setOnClickListener(clickListener);
        llStateDuty.setOnClickListener(clickListener);
        llMatch.setOnClickListener(clickListener);
        llInformation.setOnClickListener(clickListener);
        imgOpenVip.setOnClickListener(clickListener);
        tvFreeMore.setOnClickListener(clickListener);
        tvMotionMore.setOnClickListener(clickListener);
        tvScanAll.setOnClickListener(clickListener);
        imgClose.setOnClickListener(clickListener);
        tvOpenVip.setOnClickListener(clickListener);
        llStore.setOnClickListener(clickListener);
        llStateYdxh.setOnClickListener(clickListener);
        llYdk.setOnClickListener(clickListener);
        llBk.setOnClickListener(clickListener);
    }

    private void initRecycler() {
        freeExperAdapter = new FreeExperAdapter(getActivity(), freeExperList);
        rvFree.setNestedpParent(myViewPager);
        rvFree.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        rvFree.setAdapter(freeExperAdapter);

        dayStudyAdapter = new DayStudyAdapter(getActivity(), new ArrayList<>(), new ArrayList<>());
        rvDayStudy.setNestedpParent(myViewPager);
        dayStudyAdapter.setListener((bean, pos) -> {
            if (pos == 1) {
                MoreLessonActivity.go2this(getActivity(), "热门听课榜", 2, 0);
            } else {
                MoreLessonActivity.go2this(getActivity(), "热门课程榜", 1, 0);
            }
        });
        rvDayStudy.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        rvDayStudy.setAdapter(dayStudyAdapter);

        rvContent.setNestedScrollingEnabled(false);
        rvContent.setHasFixedSize(true);
        questionFindAdapter = new QuestionFindAdapter(getActivity(), new ArrayList<>());
        rvContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvContent.setAdapter(questionFindAdapter);

        vipSelectAdapter = new VipSelectAdapter(getActivity(), new ArrayList<>());
        rvVipSelect.setNestedpParent(myViewPager);
        rvVipSelect.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        rvVipSelect.setAdapter(vipSelectAdapter);

        motionFindAdapter = new MotionFindAdapter(getActivity(), new ArrayList<>());
        rvMotion.setHasFixedSize(true);
        rvMotion.setNestedScrollingEnabled(false);
        rvMotion.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvMotion.setAdapter(motionFindAdapter);

        courserFindAdapter = new CourserFindAdapter(getActivity(), new ArrayList<>());
        rvContent2.setNestedpParent(myViewPager);
        rvContent2.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        rvContent2.setAdapter(courserFindAdapter);

        classRecommendAdapter = new ClassRecommendAdapter(getActivity(), new ArrayList<>());
        rvClassRecommend.setHasFixedSize(true);
        rvClassRecommend.setNestedScrollingEnabled(false);
        rvClassRecommend.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvClassRecommend.setAdapter(classRecommendAdapter);
    }

    @Override
    protected void fragment2Front() {

    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
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
//                    BannerBean bean = images.get(position);
//                    WebViewContainerActivity.go2this(getActivity(),bean.getTitle(),bean.getLink(), WebViewContainerFragment.TYPE_BANNER_LINK_URL);
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


    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.ll_amoy://淘学
                ContentFragmentTitleActivity.go2this(getActivity(), 0);
                break;
            case R.id.ll_state_duty://国职
                ContentFragmentTitleActivity.go2this(getActivity(), 1);
                break;
//            case R.id.ll_shopping://商城
//                ((MainActivity)getActivity()).setShowPosition(1);
//                break;
            case R.id.ll_match://赛事
                ContentFragmentTitleActivity.go2this(getActivity(), 3);
                break;
            case R.id.ll_information://资讯
                ContentFragmentTitleActivity.go2this(getActivity(), 4);
                break;
            case R.id.img_open_vip://开会员
                break;
            case R.id.tv_free_more://更多免费体验
                MoreLessonActivity.go2this(getActivity(), "免费体验", 0, 0);
                break;
            case R.id.tv_motion_more://了解运动更多
                MoreLessonActivity.go2this(getActivity(), "了解运动", 4, 0);
                break;
            case R.id.tv_scan_all://查看更多课程
                MoreLessonActivity.go2this(getActivity(), "4门课程", 5, 0);
                break;
            case R.id.img_close://关闭
                relVip.setVisibility(View.GONE);
                break;
            case R.id.tv_open_vip://开通vip
                WebViewContainerActivity.go2this(getActivity(), "会员中心", HttpConstant.WEB_URL_NEW_MEMBER_CENTER,
                        WebViewContainerFragment.TYPE_NEW_MEMBER_CENTER);
                break;
            case R.id.ll_store://俱乐部
                FindStoreActivity.go2this(getContext());
                break;
            case R.id.ll_state_ydxh://运动协会
                FindStoreEvaluateActivity.go2this(getActivity());
                break;
            case R.id.ll_ydk://运动库
                break;
            case R.id.ll_bk://百科
                break;
            default:
                break;
        }
    }


    @Override
    public void getOptimizationData1Success(MdlBaseHttpResp<OptimizationData1Bean> resp) {
        refreshLayout.finishRefresh();
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && resp.getData() != null && resp.getData().getData() != null) {
            images = resp.getData().getData().getBannerList();
            initBanner();
            if (resp.getData().getData().getFreeExperienceList() != null) {
                freeExperList.clear();
                freeExperList.addAll(resp.getData().getData().getFreeExperienceList());
                freeExperAdapter.setData(resp.getData().getData().getFreeExperienceList());
            }
            if (resp.getData().getData().getHotList() != null && resp.getData().getData().getHotListeningList() != null) {
                dayStudyAdapter.setData(resp.getData().getData().getHotList(), resp.getData().getData().getHotListeningList());
            }
            if (resp.getData().getData().isVip()) {
                relVip.setVisibility(View.GONE);
            } else {
                relVip.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void getOptimizationData2Success(MdlBaseHttpResp<OptimizationData2Bean> resp) {
        refreshLayout.finishRefresh();
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && resp.getData() != null && resp.getData().getData() != null) {
            if (resp.getData().getData().getJianzhiList() != null) {
                questionFindAdapter.setData(resp.getData().getData().getJianzhiList());
            }
            if (resp.getData().getData().getJingxuanList() != null) {
                vipSelectAdapter.setData(resp.getData().getData().getJingxuanList());
            }
            if (resp.getData().getData().getYundongList() != null) {
                motionFindAdapter.setData(resp.getData().getData().getYundongList());
            }
            if (resp.getData().getData().getFourList() != null) {
                courserFindAdapter.setData(resp.getData().getData().getFourList());
            }
            if (resp.getData().getData().getCategoryList() != null) {
                classRecommendAdapter.setData(resp.getData().getData().getCategoryList());
            }
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        presenter.getOptimizationData1();
        presenter.getOptimizationData2();
    }
}

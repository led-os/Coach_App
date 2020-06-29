package com.jsjlzj.wayne.ui.store.home;

import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.home.CurriculumAdapter;
import com.jsjlzj.wayne.adapter.recycler.home.HomeInformationAdapter;
import com.jsjlzj.wayne.adapter.recycler.home.HomeLikeAdapter;
import com.jsjlzj.wayne.adapter.recycler.home.HomeVideoAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.home.BannerBean;
import com.jsjlzj.wayne.entity.store.home.CategoryBean;
import com.jsjlzj.wayne.entity.store.home.RecommendBean;
import com.jsjlzj.wayne.entity.store.home.VideoBean;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.ui.store.home.recommend.AllClassicActivity;
import com.jsjlzj.wayne.ui.store.home.recommend.ClassicDetailActivity;
import com.jsjlzj.wayne.ui.store.list.MoreDataActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.CustomGridLayoutManager;
import com.jsjlzj.wayne.widgets.LocalImageHolderView;
import com.jsjlzj.wayne.widgets.MyViewPager;
import com.jsjlzj.wayne.widgets.NestedRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @description 首页
 * @date: 2020/01/14
 * @author: 曾海强
 */
public class RecommendFragment extends MVPBaseFragment<HomeView, HomePresenter> implements HomeView, HomeVideoAdapter.OnClassicItemListener, CurriculumAdapter.OnItemClickListener, HomeLikeAdapter.OnItemClickListener, HomeInformationAdapter.OnItemClickListener {


    @BindView(R.id.scroll_banner)
    ConvenientBanner scrollBanner;
    @BindView(R.id.tv_video_right)
    TextView tvVideoRight;
    @BindView(R.id.rv_video)
    NestedRecyclerView rvVideo;
    @BindView(R.id.rel_video)
    RelativeLayout relVideo;
    @BindView(R.id.rv_curriculum)
    RecyclerView rvCurriculum;
    @BindView(R.id.tv_like)
    TextView tvLike;
    @BindView(R.id.rv_like)
    RecyclerView rvLike;
    @BindView(R.id.tv_hot_right)
    TextView tvHotRight;
    @BindView(R.id.rv_hot)
    RecyclerView rvHot;
    private HomeVideoAdapter homeVideoAdapter;
    private CurriculumAdapter curriculumAdapter;
    private HomeLikeAdapter homeLikeAdapter;
    private HomeInformationAdapter informationAdapter;


    private List<BannerBean> images = new ArrayList<>();
    private List<CategoryBean> videoList = new ArrayList<>();
    private List<RecommendBean.LessonBean> lessonList = new ArrayList<>();
    private List<VideoBean> likeList = new ArrayList<>();
    private List<RecommendBean.InformationBean> informationList = new ArrayList<>();
    private MyViewPager myViewPager;

    public RecommendFragment() {
    }

    public RecommendFragment(MyViewPager myViewPager) {
        this.myViewPager = myViewPager;
    }

    public static RecommendFragment getInstance(MyViewPager myViewPager) {
        RecommendFragment fragment = new RecommendFragment(myViewPager);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initViewAndControl(View view) {
        initRecycler();
    }

    private void initRecycler() {
        rvVideo.setHasFixedSize(true);
        rvVideo.setNestedScrollingEnabled(false);
        rvVideo.setNestedpParent(myViewPager);
        rvVideo.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        homeVideoAdapter = new HomeVideoAdapter(getActivity(), videoList,0);
        homeVideoAdapter.setListener(this);
        rvVideo.setAdapter(homeVideoAdapter);

        rvCurriculum.setHasFixedSize(true);
        rvCurriculum.setNestedScrollingEnabled(false);
        curriculumAdapter = new CurriculumAdapter(lessonList, getActivity());
        CustomGridLayoutManager curriculumAdapterLayoutManager = curriculumAdapter.getLayoutManager(getActivity());
        rvCurriculum.setLayoutManager(curriculumAdapterLayoutManager);
        curriculumAdapter.setListener(this);
        rvCurriculum.setAdapter(curriculumAdapter);


        rvLike.setHasFixedSize(true);
        rvLike.setNestedScrollingEnabled(false);
        homeLikeAdapter = new HomeLikeAdapter(getActivity(), likeList,1);
        homeLikeAdapter.setShowBigOne(true);
        rvLike.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeLikeAdapter.setListener(this);
        rvLike.setAdapter(homeLikeAdapter);

        rvHot.setHasFixedSize(true);
        rvHot.setNestedScrollingEnabled(false);
        informationAdapter = new HomeInformationAdapter(getActivity(), informationList);
        informationAdapter.setListener(this);
        rvHot.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvHot.setAdapter(informationAdapter);

        presenter.getRecommendData();
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
                    BannerBean bean = images.get(position);
                    if(!TextUtils.isEmpty(bean.getLink())){
                        WebViewContainerActivity.go2this(getActivity(),bean.getTitle(),bean.getLink(), WebViewContainerFragment.TYPE_BANNER_LINK_URL);
                    }
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


    @OnClick({R.id.tv_video_right, R.id.img_video_right, R.id.tv_like_right, R.id.img_like_right, R.id.tv_hot_right, R.id.img_hot_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_video_right:
            case R.id.img_video_right:
                AllClassicActivity.go2this(getActivity());
                break;
            case R.id.tv_like_right:
            case R.id.img_like_right:
                break;
            case R.id.tv_hot_right:
            case R.id.img_hot_right:
                MoreDataActivity.go2this(getActivity(),"热门资讯",0);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(CategoryBean data) {
        //点击视频分类条目
        ClassicDetailActivity.go2this(getActivity(), data.getName(),data.getUrl(),data.getId());
    }


    @Override
    public void getHomeRecommendSuccess(MdlBaseHttpResp<RecommendBean> resp) {
        RecommendBean.DataBean bean = resp.getData().getData();
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != bean) {
            if(null != bean.getBanner() && bean.getBanner().size() > 0){
                images = bean.getBanner();
                if(images != null && images.size() >0){
                    initBanner();
                }
            }

            if(null != bean.getCategory() && bean.getCategory().size() > 0){
                videoList.clear();
                videoList.addAll(bean.getCategory());
                homeVideoAdapter.setData(videoList);
            }

            if(null != bean.getLesson() && bean.getLesson().size() > 0){
                lessonList.clear();
                lessonList.addAll(bean.getLesson());
                curriculumAdapter.setData(lessonList);
            }

            if(null != bean.getVideo() && bean.getVideo().size() > 0){
                likeList.clear();
                likeList.addAll(bean.getVideo());
                homeLikeAdapter.setData(bean.getVideo());
            }
            if(null != bean.getInformation() && bean.getInformation().size() > 0){
                informationList.clear();
                informationList.addAll(bean.getInformation());
                informationAdapter.setData(bean.getInformation());
            }

        } else {
            LogAndToastUtil.toast(resp.getMsg());
        }
    }

    @Override
    public void onItemClick(RecommendBean.LessonBean bean) {
        //点击课程条目
        WebViewContainerActivity.go2this(getActivity(),bean.getTitle(),HttpConstant.WEB_URL_COURSE_DETAIL+bean.getId(),
                WebViewContainerFragment.TYPE_COURSE_DETAIL);
    }

    @Override
    public void onItemClick(VideoBean bean) {
        //点击猜你喜欢条目
        WebViewContainerActivity.go2this(getActivity(),bean.getName(), HttpConstant.WEB_URL_DYNAMIC_DETAIL+bean.getId(),
                WebViewContainerFragment.TYPE_DYNAMIC_DETAIL);
    }

    @Override
    public void onHeadClick(VideoBean bean) {
        //点击头像
        WebViewContainerActivity.go2this(getActivity(),bean.getName(), HttpConstant.WEB_URL_USER_INFO+bean.getChannelId(),
                WebViewContainerFragment.TYPE_USER_INFO);
    }

    @Override
    public void onItemClick(RecommendBean.InformationBean bean) {
        //点击热门资讯条目
        WebViewContainerActivity.go2this(getActivity(),bean.getName(), HttpConstant.WEB_URL_AETICLE_DETAIL+bean.getId(),
                WebViewContainerFragment.TYPE_ARTICLE_DETAIL);
    }
}

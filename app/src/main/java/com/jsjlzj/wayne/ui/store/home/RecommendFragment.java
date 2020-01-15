package com.jsjlzj.wayne.ui.store.home;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.home.HomeVideoAdapter;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.widgets.LocalImageHolderView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @description 首页
 * @date: 2020/01/14
 * @author: 曾海强
 */
public class RecommendFragment extends MVPBaseFragment<HomeView, HomePresenter> implements HomeView {


    @BindView(R.id.scroll_banner)
    ConvenientBanner scrollBanner;
    @BindView(R.id.tv_video_right)
    TextView tvVideoRight;
    @BindView(R.id.rv_video)
    RecyclerView rvVideo;
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

    public RecommendFragment() {
    }

    public static RecommendFragment getInstance() {
        RecommendFragment fragment = new RecommendFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initViewAndControl(View view) {
        initBanner();
        initRecycler();
    }

    private void initRecycler() {
        rvVideo.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        homeVideoAdapter = new HomeVideoAdapter(getActivity());
        rvVideo.setAdapter(homeVideoAdapter);
    }

    @Override
    protected void fragment2Front() {
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
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
                .setPageIndicator(new int[]{R.drawable.bg_circle_98a1ac_4, R.drawable.bg_circle_ffffff_4})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
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
                break;
            case R.id.tv_like_right:
            case R.id.img_like_right:
                break;
            case R.id.tv_hot_right:
            case R.id.img_hot_right:
                break;
                default:break;
        }
    }
}

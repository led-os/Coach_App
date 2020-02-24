package com.jsjlzj.wayne.ui.store.home;


import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.home.DriedTypeAdapter;
import com.jsjlzj.wayne.adapter.recycler.home.HomeLikeAdapter;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.widgets.LocalImageHolderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

 /**
  *
  * @ClassName:      干货
  * @Description:    java类作用描述
  * @Author:         曾海强
  * @CreateDate:
  */
public class DriedFoodFragment extends MVPBaseFragment<HomeView, HomePresenter> implements HomeView {

    @BindView(R.id.scroll_banner)
    ConvenientBanner scrollBanner;
    @BindView(R.id.rv_state)
    RecyclerView rvState;
    @BindView(R.id.rv_like)
    RecyclerView rvLike;

    private String[] mTitles = new String[3];
    private List<MVPBaseFragment> fragments = new ArrayList<>();
    private DriedTypeAdapter driedTypeAdapter;
    private HomeLikeAdapter homeLikeAdapter;

    public DriedFoodFragment() {
    }

    public static DriedFoodFragment getInstance() {
        DriedFoodFragment fragment = new DriedFoodFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_dried_food;
    }

    @Override
    protected void initViewAndControl(View view) {
//        initBanner();
        initRecycler();
    }

    @Override
    protected void fragment2Front() {
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    private void initRecycler() {
        rvState.setHasFixedSize(true);
        rvState.setNestedScrollingEnabled(false);
        driedTypeAdapter = new DriedTypeAdapter(getActivity(),0);
        rvState.setLayoutManager(new GridLayoutManager(getActivity(),4));
        rvState.setAdapter(driedTypeAdapter);

        rvLike.setHasFixedSize(true);
        rvLike.setNestedScrollingEnabled(false);
        homeLikeAdapter = new HomeLikeAdapter(getActivity(),new ArrayList<>());
        homeLikeAdapter.setAllOne(true);
        rvLike.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvLike.setAdapter(homeLikeAdapter);
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

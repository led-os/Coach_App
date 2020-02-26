package com.jsjlzj.wayne.ui.store.home;


import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.home.DriedTypeAdapter;
import com.jsjlzj.wayne.adapter.recycler.home.InformationAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.home.AmoySchoolBean;
import com.jsjlzj.wayne.entity.store.home.BannerBean;
import com.jsjlzj.wayne.entity.store.home.CategoryBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.widgets.LocalImageHolderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
  *
  * @ClassName:      InformationFragment
  * @Description:    资讯fragment
  * @Author:         曾海强
  * @CreateDate:
  */
public class InformationFragment extends MVPBaseFragment<HomeView, HomePresenter> implements HomeView {

     @BindView(R.id.scroll_banner)
     ConvenientBanner scrollBanner;
     @BindView(R.id.rv_state)
     RecyclerView rvState;
     @BindView(R.id.rv_like)
     RecyclerView rvLike;

     private String[] mTitles = new String[3];
     private List<MVPBaseFragment> fragments = new ArrayList<>();
     private DriedTypeAdapter driedTypeAdapter;
     private InformationAdapter informationAdapter;
     private List<BannerBean> images = new ArrayList<>();
     private List<CategoryBean> categoryList = new ArrayList<>();

     public InformationFragment() {
     }

     public static InformationFragment getInstance() {
         InformationFragment fragment = new InformationFragment();
         return fragment;
     }

     @Override
     protected int getLayoutResId() {
         return R.layout.fragment_information;
     }

     @Override
     protected void initViewAndControl(View view) {
         initRecycler();
         presenter.getInformationData();
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
         driedTypeAdapter = new DriedTypeAdapter(getActivity(),categoryList);
         rvState.setLayoutManager(new GridLayoutManager(getActivity(),4));
         rvState.setAdapter(driedTypeAdapter);

         rvLike.setHasFixedSize(true);
         rvLike.setNestedScrollingEnabled(false);
         informationAdapter = new InformationAdapter(getActivity(),new ArrayList<>());
         rvLike.setLayoutManager(new LinearLayoutManager(getActivity()));
         rvLike.setAdapter(informationAdapter);
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
                     // TODO: 2020/2/26
                 })
                 .setCanLoop(true);
     }



    @Override
    public void getDriedFoodSuccess(MdlBaseHttpResp<AmoySchoolBean> resp) {
        AmoySchoolBean.DataBean bean = resp.getData().getData();
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != bean) {
            if (null != bean.getBanner() && bean.getBanner().size() > 0) {
                images = bean.getBanner();
                if (images != null && images.size() > 0) {
                    initBanner();
                }
            }

            if (null != bean.getCategory() && bean.getCategory().size() > 0) {
                categoryList = bean.getCategory();
                if (categoryList != null && categoryList.size() > 0) {
                    driedTypeAdapter.setData(categoryList);
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

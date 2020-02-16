package com.jsjlzj.wayne.ui.store.home.recommend;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.home.ClassDetailAdapter;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.ui.publicac.report.ReportTypeActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.LocalImageHolderView;
import com.jsjlzj.wayne.widgets.dialog.ShareDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

 /**
  *
  * @ClassName:      ClassicDetailActivity
  * @Description:    单个类型详情
  * @Author:         曾海强
  * @CreateDate:
  */
public class ClassicDetailActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView, ClassDetailAdapter.OnClassicDetailListener {

    @BindView(R.id.scroll_banner)
    ConvenientBanner scrollBanner;
    @BindView(R.id.tv_news)
    TextView tvNews;
    @BindView(R.id.tv_hot)
    TextView tvHot;
    @BindView(R.id.rv_classic_detail)
    RecyclerView rvClassicDetail;

    private ClassDetailAdapter adapter;

    public static void go2this(Activity context,String title) {
        Intent intent = new Intent(context, ClassicDetailActivity.class);
        intent.putExtra(ExtraConstant.EXTRA_TITLE,title);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_classic_detail;
    }


    @Override
    protected void initViewAndControl() {
        String title = getIntent().getStringExtra(ExtraConstant.EXTRA_TITLE);
        initTitle(title);
        initBanner();
        adapter = new ClassDetailAdapter(this, new ArrayList<>());
        adapter.setListener(this);
        rvClassicDetail.setHasFixedSize(true);
        rvClassicDetail.setNestedScrollingEnabled(false);
        rvClassicDetail.setLayoutManager(new LinearLayoutManager(this));
        rvClassicDetail.setAdapter(adapter);
        tvNews.setOnClickListener(clickListener);
        tvHot.setOnClickListener(clickListener);
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

     @Override
     protected void onMultiClick(View view) {
         super.onMultiClick(view);
         switch (view.getId()){
             case R.id.tv_news:
                 tvNews.setTextColor(ContextCompat.getColor(this,R.color.color_4F9BFA));
                 tvHot.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                 break;
             case R.id.tv_hot:
                 tvHot.setTextColor(ContextCompat.getColor(this,R.color.color_4F9BFA));
                 tvNews.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                 break;
         }
     }

     @Override
     public void onClickHead(String str) {

     }

     @Override
     public void onFavorite(String str) {
         new ShareDialog(this, index -> {
             if(index == 2){
                 ReportTypeActivity.go2this(this);
             }else {
                 //0:微信好友  1:朋友圈
                 LogAndToastUtil.showWait(ClassicDetailActivity.this,""+index);
             }
         }).show();
     }

     @Override
     public void onPlayVideo(String str) {

     }

     @Override
     public void onClickZan(String str) {

     }

     @Override
     public void onClickMessage(String str) {

     }

     @Override
     public void onClickMark(String str) {

     }
 }

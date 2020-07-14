package com.jsjlzj.wayne.ui.store.wiki;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.wiki.WikiMenuAdapter;
import com.jsjlzj.wayne.entity.store.home.BannerBean;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.widgets.CustomXRecyclerView;
import com.jsjlzj.wayne.widgets.LocalImageHolderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: 百科
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate:
 */
public class WikiActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView, WikiMenuAdapter.OnItemClickListener {


    @BindView(R.id.scroll_banner)
    ConvenientBanner scrollBanner;
    @BindView(R.id.rv_menu)
    RecyclerView rvMenu;
    @BindView(R.id.rv_wiki)
    CustomXRecyclerView rvWiki;

    private WikiMenuAdapter wikiMenuAdapter;
    private List<BannerBean> images = new ArrayList<>();


    public static void go2this(Context context) {
        context.startActivity(new Intent(context, WikiActivity.class));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_wiki;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initRightTitle("健身百科",R.drawable.ic_search);
        mRightBtn.setOnClickListener(clickListener);
        List<String> list = new ArrayList<>();
        list.add("推荐");
        list.add("饮食");
        list.add("减脂");
        list.add("增肌");
        list.add("朔型");
        list.add("跑步");
        list.add("瑜伽");
        list.add("体态");
        list.add("舞蹈");
        wikiMenuAdapter = new WikiMenuAdapter(this,list);
        rvMenu.setLayoutManager(new LinearLayoutManager(this));
        rvMenu.setAdapter(wikiMenuAdapter);
        wikiMenuAdapter.setListener(this);
    }


    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        if(view.getId() == R.id.btn_title_right){

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
                        WebViewContainerActivity.go2this(this, bean.getTitle(), bean.getLink(), WebViewContainerFragment.TYPE_BANNER_LINK_URL);
                    }
                })
                .setCanLoop(images.size() > 1);
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
    public void onItemClick(String bean) {
        // TODO: 2020/7/14 点击跳转
    }
}
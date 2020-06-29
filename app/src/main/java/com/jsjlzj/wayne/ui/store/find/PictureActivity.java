package com.jsjlzj.wayne.ui.store.find;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.find.PictureAdapter;
import com.jsjlzj.wayne.adapter.recycler.find.VideoAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.find.PictureListBean;
import com.jsjlzj.wayne.entity.find.VideoListBean;
import com.jsjlzj.wayne.entity.store.home.VideoBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.CustomXRecyclerView;
import com.netease.nim.uikit.common.ToastHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: 图片列表
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate:
 */
public class PictureActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView, XRecyclerView.LoadingListener {


    @BindView(R.id.rv_pic)
    CustomXRecyclerView rvPic;
    private String title;
    private String storeId;
    private int pageNo=1;
    private int pageCount;
    private boolean isRefresh;
    /**
     * 0 :  商家图片列表   1 ： 商家视频列表    2 ： 网友图片列表   3 ： 网友视频列表
     */
    private int type;
    private PictureAdapter pictureAdapter;
    private VideoAdapter videoAdapter;
    private List<String> pictureList = new ArrayList<>();
    private List<VideoListBean.DataBean.ResultBean> videoList = new ArrayList<>();

    public static void go2this(Context context,String storeId, String title,int type) {
        context.startActivity(new Intent(context, PictureActivity.class)
                .putExtra("type",type)
                .putExtra("storeId",storeId)
                .putExtra("title", title));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_picture;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        type = getIntent().getIntExtra("type",0);
        title = getIntent().getStringExtra("title");
        storeId = getIntent().getStringExtra("storeId");
        initTitle(title);
        rvPic.setPullRefreshEnabled(true);
        rvPic.setLoadingMoreEnabled(true);
        rvPic.setLoadingListener(this);
        rvPic.setLayoutManager(new GridLayoutManager(this,2));
        if(type == 1 || type == 3){
            videoAdapter = new VideoAdapter(this,videoList);
            rvPic.setAdapter(videoAdapter);
        }else {
            pictureAdapter = new PictureAdapter(this,pictureList);
            rvPic.setAdapter(pictureAdapter);
        }
        loadData(true);

    }


    private void loadData(boolean isRefresh){
        this.isRefresh = isRefresh;
        if (isRefresh) {
            pageNo = 1;
        }
        if(type == 0){
            presenter.getClubPicList(storeId,pageNo, HttpConstant.PAGE_SIZE_NUMBER);
        }else if(type == 1){
            presenter.getClubVideoList(storeId,pageNo, HttpConstant.PAGE_SIZE_NUMBER);
        }else if(type == 2){
            presenter.getClubCommentPicList(storeId,pageNo, HttpConstant.PAGE_SIZE_NUMBER);
        }else if(type == 3){
            presenter.getClubCommentVideoList(storeId,pageNo, HttpConstant.PAGE_SIZE_NUMBER);
        }
    }


    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public void onLoadMore() {
        if (pageNo < pageCount ) {
            pageNo++;
            loadData(false);
        } else {
            ToastHelper.showToast(this, getString(R.string.has_no_more_data));
        }
    }



    @Override
    public void getClubPicListSuccess(MdlBaseHttpResp<PictureListBean> resp) {
        rvPic.refreshComplete();
        rvPic.loadMoreComplete();
        if (resp.getStatus() == HttpConstant.R_HTTP_OK) {
            pageNo = resp.getData().getData().getPageNo();
            int totalCount = resp.getData().getData().getTotalCount();
            int a = totalCount % HttpConstant.PAGE_SIZE_NUMBER;
            if (a == 0) {
                pageCount = totalCount / HttpConstant.PAGE_SIZE_NUMBER;
            } else {
                pageCount = (totalCount / HttpConstant.PAGE_SIZE_NUMBER) + 1;
            }
            List<String> list = resp.getData().getData().getResult();
            if (list != null && list.size() > 0) {
                if (isRefresh) {
                    pictureList.clear();
                }
                pictureList.addAll(list);
                pictureAdapter.setData(pictureList);
                hideEmpty();
            } else if (isRefresh) {
                // 无数据
                showEmpty(R.id.rel_empty, 0, null);
            }
        }
    }

    @Override
    public void getClubVideoListSuccess(MdlBaseHttpResp<VideoListBean> resp) {
        rvPic.refreshComplete();
        rvPic.loadMoreComplete();
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp) {
            pageNo = resp.getData().getData().getPageNo();
            int totalCount = resp.getData().getData().getTotalCount();
            int a = totalCount % HttpConstant.PAGE_SIZE_NUMBER;
            if (a == 0) {
                pageCount = totalCount / HttpConstant.PAGE_SIZE_NUMBER;
            } else {
                pageCount = (totalCount / HttpConstant.PAGE_SIZE_NUMBER) + 1;
            }
            List<VideoListBean.DataBean.ResultBean> list = resp.getData().getData().getResult();
            if (list != null && list.size() > 0) {
                if (isRefresh) {
                    videoList.clear();
                }
                videoList.addAll(list);
                videoAdapter.setData(videoList);
                hideEmpty();
            } else if (isRefresh) {
                // 无数据
                showEmpty(R.id.rel_empty, 0, null);
            }
        }
    }

    @Override
    public void getClubCommentPicListSuccess(MdlBaseHttpResp<PictureListBean> resp) {
        rvPic.refreshComplete();
        rvPic.loadMoreComplete();
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp) {
            pageNo = resp.getData().getData().getPageNo();
            int totalCount = resp.getData().getData().getTotalCount();
            int a = totalCount % HttpConstant.PAGE_SIZE_NUMBER;
            if (a == 0) {
                pageCount = totalCount / HttpConstant.PAGE_SIZE_NUMBER;
            } else {
                pageCount = (totalCount / HttpConstant.PAGE_SIZE_NUMBER) + 1;
            }
            List<String> list = resp.getData().getData().getResult();
            if (list != null && list.size() > 0) {
                if (isRefresh) {
                    pictureList.clear();
                }
                pictureList.addAll(list);
                pictureAdapter.setData(pictureList);
                hideEmpty();
            } else if (isRefresh) {
                // 无数据
                showEmpty(R.id.rel_empty, 0, null);
            }
        }
    }


    @Override
    public void getClubCommentVideoListSuccess(MdlBaseHttpResp<VideoListBean> resp) {
        rvPic.refreshComplete();
        rvPic.loadMoreComplete();
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp) {
            pageNo = resp.getData().getData().getPageNo();
            int totalCount = resp.getData().getData().getTotalCount();
            int a = totalCount % HttpConstant.PAGE_SIZE_NUMBER;
            if (a == 0) {
                pageCount = totalCount / HttpConstant.PAGE_SIZE_NUMBER;
            } else {
                pageCount = (totalCount / HttpConstant.PAGE_SIZE_NUMBER) + 1;
            }
            List<VideoListBean.DataBean.ResultBean> list = resp.getData().getData().getResult();
            if (list != null && list.size() > 0) {
                if (isRefresh) {
                    videoList.clear();
                }
                videoList.addAll(list);
                videoAdapter.setData(videoList);
                hideEmpty();
            } else if (isRefresh) {
                // 无数据
                showEmpty(R.id.rel_empty, 0, null);
            }
        }
    }
}

package com.jsjlzj.wayne.ui.store.find;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.find.PictureAdapter;
import com.jsjlzj.wayne.adapter.recycler.find.VideoAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.find.PictureListBean;
import com.jsjlzj.wayne.entity.find.VideoListBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;

import butterknife.BindView;

/**
 * @description PictureVideoFragment
 * @date: 2020/06/28
 * @author: 曾海强
 */
public class PictureVideoFragment extends MVPBaseFragment<HomeView, HomePresenter> implements HomeView {

    @BindView(R.id.tv_business_type)
    TextView tvBusinessType;
    @BindView(R.id.tv_bus_num)
    TextView tvBusNum;
    @BindView(R.id.img_bus_type)
    ImageView imgBusType;
    @BindView(R.id.rv_business)
    RecyclerView rvBusiness;
    @BindView(R.id.tv_net_type)
    TextView tvNetType;
    @BindView(R.id.tv_net_num)
    TextView tvNetNum;
    @BindView(R.id.img_net_type)
    ImageView imgNetType;
    @BindView(R.id.rv_net)
    RecyclerView rvNet;
    @BindView(R.id.ll_business)
    LinearLayout llBusiness;
    @BindView(R.id.ll_net)
    LinearLayout llNet;
    /**
     * 0 : 图片   1 ：视频
     */
    private int type;
    private String storeId;
    private int pageNo = 1;

    public static PictureVideoFragment getInstance(int type, String storeId) {
        PictureVideoFragment fragment = new PictureVideoFragment(type, storeId);
        return fragment;
    }

    public PictureVideoFragment() {
    }

    private PictureVideoFragment(int type, String storeId) {
        this.type = type;
        this.storeId = storeId;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_picture_video;
    }

    @Override
    protected void initViewAndControl(View view) {
        loadData();
        if(type == 0){
            tvBusinessType.setText("商家图片");
            tvNetType.setText("网友图片");
        }else {
            tvBusinessType.setText("商家视频");
            tvNetType.setText("网友视频");
        }
        tvBusNum.setOnClickListener(clickListener);
        imgBusType.setOnClickListener(clickListener);
        tvNetNum.setOnClickListener(clickListener);
        imgNetType.setOnClickListener(clickListener);
    }

    @Override
    protected void fragment2Front() {}


    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }


    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()){
            case R.id.tv_bus_num:
            case R.id.img_bus_type:
                if(type == 0){
                    PictureActivity.go2this(getActivity(),storeId,"商家图片",0);
                }else {
                    PictureActivity.go2this(getActivity(),storeId,"商家视频",1);
                }
                break;
            case R.id.tv_net_num:
            case R.id.img_net_type:
                if(type == 0){
                    PictureActivity.go2this(getActivity(),storeId,"网友图片",2);
                }else {
                    PictureActivity.go2this(getActivity(),storeId,"网友视频",3);
                }
                break;
        }
    }

    private void loadData() {
        if (type == 0) {
            presenter.getClubPicList(storeId, pageNo, 4);
            presenter.getClubCommentPicList(storeId, pageNo, 6);
        } else if (type == 1) {
            presenter.getClubVideoList(storeId, pageNo, 4);
            presenter.getClubCommentVideoList(storeId, pageNo, 6);
        }
    }


    @Override
    public void getClubPicListSuccess(MdlBaseHttpResp<PictureListBean> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && resp.getData().getData() != null) {
            if(resp.getData().getData().getTotalCount() == 0){
                llBusiness.setVisibility(View.GONE);
                rvBusiness.setVisibility(View.GONE);
                return;
            }
            tvBusNum.setText(resp.getData().getData().getTotalCount()+"张");
            rvBusiness.setLayoutManager(new GridLayoutManager(getActivity(),2));
            PictureAdapter adapter1 = new PictureAdapter(getActivity(),resp.getData().getData().getResult());
            rvBusiness.setAdapter(adapter1);
        }
    }

    @Override
    public void getClubVideoListSuccess(MdlBaseHttpResp<VideoListBean> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && resp.getData().getData() != null) {
            if(resp.getData().getData().getTotalCount() == 0){
                llBusiness.setVisibility(View.GONE);
                rvBusiness.setVisibility(View.GONE);
                return;
            }
            tvBusNum.setText(resp.getData().getData().getTotalCount()+"个");
            rvBusiness.setLayoutManager(new GridLayoutManager(getActivity(),2));
            VideoAdapter adapter2 = new VideoAdapter(getActivity(),resp.getData().getData().getResult());
            rvBusiness.setAdapter(adapter2);

        }
    }


    @Override
    public void getClubCommentPicListSuccess(MdlBaseHttpResp<PictureListBean> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && resp.getData().getData() != null) {
            if(resp.getData().getData().getTotalCount() == 0){
                llNet.setVisibility(View.GONE);
                rvNet.setVisibility(View.GONE);
                return;
            }
            tvNetNum.setText(resp.getData().getData().getTotalCount()+"张");
            rvNet.setLayoutManager(new GridLayoutManager(getActivity(),2));
            PictureAdapter adapter3 = new PictureAdapter(getActivity(),resp.getData().getData().getResult());
            rvNet.setAdapter(adapter3);
        }
    }

    @Override
    public void getClubCommentVideoListSuccess(MdlBaseHttpResp<VideoListBean> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && resp.getData().getData() != null) {
            if(resp.getData().getData().getTotalCount() == 0){
                llNet.setVisibility(View.GONE);
                rvNet.setVisibility(View.GONE);
                return;
            }
            tvNetNum.setText(resp.getData().getData().getTotalCount()+"个");
            rvNet.setLayoutManager(new GridLayoutManager(getActivity(),2));
            VideoAdapter adapter4 = new VideoAdapter(getActivity(),resp.getData().getData().getResult());
            rvNet.setAdapter(adapter4);
        }
    }
}

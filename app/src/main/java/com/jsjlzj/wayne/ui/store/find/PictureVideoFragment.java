package com.jsjlzj.wayne.ui.store.find;

import android.view.View;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
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

    @BindView(R.id.tv_name)
    TextView tvName;
    /**
     * 0 : 图片   1 ：视频
     */
    private int type;
    private String storeId;
    private int pageNo=1;

    public static PictureVideoFragment getInstance(int type,String storeId) {
        PictureVideoFragment fragment = new PictureVideoFragment(type,storeId);
        return fragment;
    }

    public PictureVideoFragment() {}

    private PictureVideoFragment(int type,String storeId) {
        this.type = type;
        this.storeId = storeId;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_picture_video;
    }

    @Override
    protected void initViewAndControl(View view) {
        tvName.setText("fragment"+type);
        loadData();
    }

    @Override
    protected void fragment2Front() {}

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }


    private void loadData(){
        LogAndToastUtil.log("==================="+storeId);
        if(type == 0){
            presenter.getClubPicList(storeId,pageNo,4);
            presenter.getClubCommentPicList(storeId,pageNo,6);
        }else if(type == 1){
            presenter.getClubVideoList(storeId,pageNo,4);
            presenter.getClubCommentVideoList(storeId,pageNo,6);
        }
    }


    @Override
    public void getClubPicListSuccess(MdlBaseHttpResp<PictureListBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK && resp.getData().getData() != null){

        }
    }

    @Override
    public void getClubVideoListSuccess(MdlBaseHttpResp<VideoListBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK && resp.getData().getData() != null){

        }
    }
}

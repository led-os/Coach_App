package com.jsjlzj.wayne.ui.store.find;

import android.view.View;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.GlidUtils;
import com.jsjlzj.wayne.utils.imgeutils.ImagePreviewView;
import com.jsjlzj.wayne.utils.imgeutils.PreviewerViewPager;

import butterknife.BindView;

/**
 * @ClassName: 图片详情也
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate:
 */
public class PictureDetailFragment extends MVPBaseFragment<HomeView, HomePresenter> implements HomeView, ImagePreviewView.OnReachBorderListener {

    @BindView(R.id.img_preview)
    ImagePreviewView imgPreview;
    private PreviewerViewPager vp;

    public PictureDetailFragment() {
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_picture_detail;
    }

    @Override
    protected void initViewAndControl(View view) {
        String imgUrl = getArguments().getString(ExtraConstant.EXTRA_PIC_URL);
        imgPreview.setOnReachBorderListener(this);
        GlidUtils.setGrid(getActivity(),imgUrl,imgPreview);
    }

    @Override
    protected void fragment2Front() {
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public void onReachBorder(boolean isReached) {
        vp.isInterceptable(isReached);
    }

    public void setViewPager(PreviewerViewPager vp) {
        this.vp = vp;
    }
}

package com.jsjlzj.wayne.ui.store.find;

import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;

import butterknife.BindView;

/**
 * @ClassName: 图片视频界面
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate:
 */
public class FindPicVideoActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.tv_pic)
    TextView tvPic;
    @BindView(R.id.img_pic)
    ImageView imgPic;
    @BindView(R.id.tv_video)
    TextView tvVideo;
    @BindView(R.id.img_video)
    ImageView imgVideo;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_find_pic_video;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {

    }

}

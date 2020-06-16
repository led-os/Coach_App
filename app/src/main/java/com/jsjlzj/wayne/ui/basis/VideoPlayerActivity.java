package com.jsjlzj.wayne.ui.basis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;

/**
 * @description 视频播放器
 * @date: 2020/06/15
 * @author: 曾海强
 */
public class VideoPlayerActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView{

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_video_player;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {

    }


}

package com.jsjlzj.wayne.ui.store.find;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.yuyh.library.imgsel.common.Constant;

import androidx.fragment.app.FragmentTransaction;
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

    private String clubId;
    /**
     * 1:图片   2 ：视频
     */
    private int type;
    private PictureVideoFragment pictureFragment;
    private PictureVideoFragment videoFragment;

    public static void go2this(Activity activity, String clubId, int type) {
        activity.startActivity(new Intent(activity, FindPicVideoActivity.class)
                .putExtra("clubId", clubId)
                .putExtra("type", type));
    }

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
        clubId = getIntent().getStringExtra("clubId");
        type = getIntent().getIntExtra("type", 1);
        if (type == 1) {
            imgPic.setVisibility(View.VISIBLE);
            imgVideo.setVisibility(View.GONE);
            showPicFragment();
        } else {
            imgPic.setVisibility(View.GONE);
            imgVideo.setVisibility(View.VISIBLE);
            showVideoFragment();
        }
        btnBack.setOnClickListener(clickListener);
        tvPic.setOnClickListener(clickListener);
        tvVideo.setOnClickListener(clickListener);
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_pic:
                imgPic.setVisibility(View.VISIBLE);
                imgVideo.setVisibility(View.GONE);
                showPicFragment();
                break;
            case R.id.tv_video:
                imgPic.setVisibility(View.GONE);
                imgVideo.setVisibility(View.VISIBLE);
                showVideoFragment();
                break;
            default:
                break;
        }
    }

    private void showPicFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(videoFragment != null){
            fragmentTransaction.hide(videoFragment);
        }
        if (pictureFragment == null) {
            pictureFragment = PictureVideoFragment.getInstance(0,clubId);
            fragmentTransaction.add(R.id.frame_layout, pictureFragment);
        } else {
            fragmentTransaction.show(pictureFragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }


    private void showVideoFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(pictureFragment != null){
            fragmentTransaction.hide(pictureFragment);
        }
        if (videoFragment == null) {
            videoFragment = PictureVideoFragment.getInstance(1,clubId);
            fragmentTransaction.add(R.id.frame_layout, videoFragment);
        } else {
            fragmentTransaction.show(videoFragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

}

package com.jsjlzj.wayne.ui.store.find;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.FragmentStateAdapter;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.GlidUtils;
import com.jsjlzj.wayne.utils.imgeutils.ImagePreviewView;
import com.jsjlzj.wayne.utils.imgeutils.PreviewerViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PictureDetailActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView, ViewPager.OnPageChangeListener {

    @BindView(R.id.vp_image)
    PreviewerViewPager vpImage;
    @BindView(R.id.tv_index)
    TextView tvIndex;
    @BindView(R.id.iv_save)
    ImageView ivSave;
    @BindView(R.id.btn_back)
    ImageView btnBack;
    private List<String> imgUrlList = new ArrayList<>();
    private int curPos;

    public static void go2this(Context context, ArrayList<String> imageUrlList,int pos){
        context.startActivity(new Intent(context,PictureDetailActivity.class)
                    .putStringArrayListExtra("imageUrlList",imageUrlList)
                    .putExtra("curPos",pos));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_picture_detail;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        imgUrlList = getIntent().getStringArrayListExtra("imageUrlList");
        curPos = getIntent().getIntExtra("curPos",1);
        FragmentStateAdapter adapter = new FragmentStateAdapter(getSupportFragmentManager(),imgUrlList,vpImage);
        tvIndex.setText((curPos+1)+"/"+imgUrlList.size());
        vpImage.addOnPageChangeListener(this);
        vpImage.setAdapter(adapter);
        vpImage.setCurrentItem(curPos);
        vpImage.setOffscreenPageLimit(3);
        btnBack.setOnClickListener(clickListener);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        tvIndex.setText((position+1)+"/"+imgUrlList.size());
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        if(view.getId() == R.id.btn_back){
            finish();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {}
}

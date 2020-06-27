package com.jsjlzj.wayne.ui.store.find;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: 图片列表
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate:
 */
public class PictureActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {


    @BindView(R.id.rv_pic)
    RecyclerView rvPic;
    private String title;

    public static void go2this(Context context, String title) {
        context.startActivity(new Intent(context, PictureActivity.class)
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
        title = getIntent().getStringExtra("title");
        initTitle(title);
    }


}

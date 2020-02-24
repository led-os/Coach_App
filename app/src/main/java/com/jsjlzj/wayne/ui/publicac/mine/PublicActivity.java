package com.jsjlzj.wayne.ui.publicac.mine;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.ImageSelectAdapter;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.ui.store.home.community.AddExpressionActivity;
import com.jsjlzj.wayne.utils.SelectImageUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @ClassName: PublicActivity
 * @Description: 社区发布动态
 * @Author: 曾海强
 * @CreateDate:
 */
public class PublicActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView, ImageSelectAdapter.OnImageClickListener {

    public static final int IMG_SIZE = 9;

    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_public)
    TextView tvPublic;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.rv_public_img)
    RecyclerView rvPublicImg;
    @BindView(R.id.ll_location)
    LinearLayout llLocation;
    @BindView(R.id.tv_scan_type)
    TextView tvScanType;
    @BindView(R.id.ll_dskj)
    LinearLayout llDskj;
    @BindView(R.id.img_face)
    ImageView imgFace;
    @BindView(R.id.ll_add_emji)
    LinearLayout llAddEmji;


    private List<String> imgUrls;
    private ImageSelectAdapter adapter;
    private int position;
    private List<String> uploadImages = new ArrayList<>();

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, PublicActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_public;
    }

    @Override
    protected void initViewAndControl() {
        llLocation.setOnClickListener(clickListener);
        llDskj.setOnClickListener(clickListener);
        llAddEmji.setOnClickListener(clickListener);
        tvPublic.setOnClickListener(clickListener);
        tvCancel.setOnClickListener(clickListener);
        initRecycler();
    }

    private void initRecycler() {
        rvPublicImg.setLayoutManager(new GridLayoutManager(this, 3));
        imgUrls = new ArrayList<>();
        // 默认的一张空白占位图
        imgUrls.add("");
        adapter = new ImageSelectAdapter(this, imgUrls);
        rvPublicImg.setAdapter(adapter);
        adapter.setListener(this);
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.ll_location://所在地址
                break;
            case R.id.ll_dskj://对谁可见
                break;
            case R.id.ll_add_emji://添加表情
                AddExpressionActivity.go2this(this);
                break;
            case R.id.tv_public://发布
                break;
            case R.id.tv_cancel://取消
                finish();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(this, requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        presenter.onRequestPermissionsResult(this, requestCode, grantResults);
    }


    /**
     * 选择图片
     * @param position 点击的角标
     */
    @Override
    public void onImageClick(int position) {
        presenter.autoObtainStoragePermission(this, position);
    }

    @Override
    public void onRemoveImgClick(int position) {
        // 删除图片
        imgUrls.remove(position);
        if (imgUrls.size() < IMG_SIZE) {
            // 如果最后一张已经是空白图的话不操作，否则添加一张空白图
            if (!imgUrls.get(imgUrls.size() - 1).equals("")) {
                imgUrls.add("");
            }
        }
        adapter.notifyDataSetChanged();
    }


    @Override
    public void selectPhoto(int position) {
        SelectImageUtils.selectPhoto(this, getString(R.string.takephoto), false, true, 1);
    }

    @Override
    public void onUploadSuccess(String imgUrl, int position) {
//        presenter.uploadImage(imgUrl);
        this.position = position;
        if (imgUrls.size() < position) {
            imgUrls.add(imgUrl);
            // 替换图片
        } else if (imgUrls.size() >= position) {
            imgUrls.set(position, imgUrl);
        }
        if (imgUrls.size() < IMG_SIZE) {
            // 如果最后一张已经是空白图的话不操作，否则添加一张空白图
            if (!imgUrls.get(imgUrls.size() - 1).equals("")) {
                imgUrls.add("");
            }
        }
        adapter.notifyDataSetChanged();
    }
}

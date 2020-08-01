package com.jsjlzj.wayne.ui.store.personal.storeinfo.set;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.BaseAdapterHelper;
import com.jsjlzj.wayne.adapter.recycler.ImageSelectAdapter;
import com.jsjlzj.wayne.adapter.recycler.MyRecyclerAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.constant.MyPermissionConstant;
import com.jsjlzj.wayne.data.http.HttpDataBasis;
import com.jsjlzj.wayne.entity.Login.MdlUpload;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.MdlStoreInfo;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.ui.store.talent.position.RecruitActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SelectImageUtils;
import com.jsjlzj.wayne.utils.permission.PermissionUtil;
import com.jsjlzj.wayne.widgets.MyRecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 照片
 */
public class StorePhotoActivity extends MVPBaseActivity<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView, ImageSelectAdapter.OnImageClickListener {
    public static final int IMG_SIZE = 9;

    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
    }

    public static void go2this(Activity context, List<MdlStoreInfo.DataBean.CompanyImagesBean> list) {
        Intent intent = new Intent(context, StorePhotoActivity.class);
        intent.putExtra("list", (Serializable) list);
        context.startActivityForResult(intent, RecruitActivity.FLAG_RECRUIT_CONTENT);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_store_info_photo;
    }

    private RecyclerView gridView;
    private List<String> imgUrls;
    private ImageSelectAdapter adapter;
    private int position;
    private List<MdlStoreInfo.DataBean.CompanyImagesBean> inPathList;//显示list
    private List<String> uploadImages = new ArrayList<>();
    private List<String> thumpUploadImages = new ArrayList<>();


    @Override
    protected void initViewAndControl() {
        findView(R.id.btnBack).setOnClickListener(clickListener);
        findView(R.id.btnKeep).setOnClickListener(clickListener);
        gridView = findView(R.id.rvPhone);
        gridView.setLayoutManager(new GridLayoutManager(this, 3));
        imgUrls = new ArrayList<>();
        inPathList = (List<MdlStoreInfo.DataBean.CompanyImagesBean>) getIntent().getSerializableExtra("list");
        if (inPathList == null) {
            inPathList = new ArrayList<>();
            // 默认的一张空白占位图
            imgUrls.add("");
        }else {
            for (MdlStoreInfo.DataBean.CompanyImagesBean bean: inPathList) {
                uploadImages.add(bean.getOriginal());
                thumpUploadImages.add(bean.getThumbnail());
                imgUrls.add(bean.getOriginal());
            }
            if(imgUrls.size() < 9){
                imgUrls.add("");
            }
        }
        initRecycle();
    }


    private void initRecycle() {
        adapter = new ImageSelectAdapter(this, imgUrls);
        gridView.setAdapter(adapter);
        adapter.setListener(this);
    }

    Map<Object, Object> map = null;
    private MyViewClickListener clickListener = new MyViewClickListener();
    private MdlStoreInfo.DataBean.CompanyImagesBean bean;

    /**
     * 选择图片
     *
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


    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            if (null == map) map = new HashMap<>();
            switch (view.getId()) {
                case R.id.btnKeep://保存
                    submitPics();
                    break;
                case R.id.btnBack://返回
                    finish();
                    break;
            }
        }
    }


    public void submitPics() {
        inPathList.clear();
        for (int i = 0; i < uploadImages.size(); i++) {
            MdlStoreInfo.DataBean.CompanyImagesBean bean = new MdlStoreInfo.DataBean.CompanyImagesBean();
            bean.setOriginal(uploadImages.get(i));
            bean.setThumbnail(thumpUploadImages.get(i));
            inPathList.add(bean);
        }
        map.put("companyImages", inPathList);
        presenter.saveCompanyImage(map);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(this, requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        presenter.onRequestPermissionsResult(this, requestCode, grantResults);
    }


    @Override
    public void selectPhoto(int position) {
        SelectImageUtils.selectPhoto(this, getString(R.string.takephoto), false, true, 1);
    }

    @Override
    public void onUploadSuccess(String imgUrl, int position) {
        presenter.upload(imgUrl);
        this.position = position;
    }

    @Override
    public void showUpload(MdlBaseHttpResp<MdlUpload> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && resp.getData() != null) {
            MdlUpload.DataBean bean = resp.getData().getData();
            if (imgUrls.size() < position) {
                imgUrls.add(bean.getUrl());
                // 替换图片
            } else {
                imgUrls.set(position, bean.getUrl());
            }
            if (imgUrls.size() < IMG_SIZE) {
                // 如果最后一张已经是空白图的话不操作，否则添加一张空白图
                if (!imgUrls.get(imgUrls.size() - 1).equals("")) {
                    imgUrls.add("");
                }
            }
            adapter.notifyDataSetChanged();

            // 添加图片
            if (uploadImages.size() <= position) {
                uploadImages.add(bean.getUrl());
                thumpUploadImages.add(bean.getUrl());
                // 替换图片
            } else {
                uploadImages.set(position, bean.getUrl());
                thumpUploadImages.set(position,bean.getUrl());
            }
        }

    }

    public void showSaveCompanyImage(MdlBaseHttpResp<MdlStoreInfo> resp) {
        this.hideLoading();
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            LogAndToastUtil.toast("保存成功");
            finish();
        } else {
            LogAndToastUtil.toast(resp.getMsg());
        }
    }

}
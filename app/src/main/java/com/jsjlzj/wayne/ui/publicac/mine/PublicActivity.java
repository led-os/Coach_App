package com.jsjlzj.wayne.ui.publicac.mine;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.ImageSelectAdapter;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.DataBean;
import com.jsjlzj.wayne.entity.Login.MdlUpload;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.address.LocationBean;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.ui.publicac.MapActivity;
import com.jsjlzj.wayne.ui.store.home.community.AddExpressionActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SelectImageUtils;
import com.jsjlzj.wayne.widgets.dialog.SexDialog;
import com.netease.nim.uikit.common.ToastHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @ClassName: PublicActivity
 * @Description: 社区发布动态
 * @Author: 曾海强
 * @CreateDate:
 */
public class PublicActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView, ImageSelectAdapter.OnImageClickListener {

    public static final int REQUEST_CODE_SELECT_STATE = 10000;
    public static final int REQUEST_CODE_SELECT_LOCATION = 10001;
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
    @BindView(R.id.tv_location)
    TextView tvLocation;


    private List<String> imgUrls;
    private ImageSelectAdapter adapter;
    private Map<Object,Object> map = new HashMap<>();
    private int position;
    private List<String> uploadImages = new ArrayList<>();
    private String moodLabel = "0";
    private int previewStatus = 1;
    private String locationCoordinate;
    private String locationAddress;

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
        locationCoordinate = MyApp.getApp().getLongitude()+","+MyApp.getApp().getLatitude();
        locationAddress =  MyApp.getApp().getCurPosition();
        tvLocation.setText(locationAddress);
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
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.ll_location://所在地址
                MapActivity.go2this(this,REQUEST_CODE_SELECT_LOCATION);
                break;
            case R.id.ll_dskj://对谁可见
                new SexDialog(this,"公开","仅自己可见", isMan -> {
                    previewStatus = isMan;
                    tvScanType.setText(isMan == 1 ? "公开" : "仅自己可见");
                }).show();
                break;
            case R.id.ll_add_emji://添加表情
                AddExpressionActivity.go2this(this,REQUEST_CODE_SELECT_STATE);
                break;
            case R.id.tv_public://发布
                publicDynamic();
                break;
            case R.id.tv_cancel://取消
                finish();
                break;
        }
    }

    private void publicDynamic() {
        if(TextUtils.isEmpty(etTitle.getText().toString())){
            ToastHelper.showToast(this,R.string.please_in_public_content);
            return;
        }
        if(uploadImages.size() == 0){
            ToastHelper.showToast(this,R.string.please_select_picture);
            return;
        }
        String[] imageStr = new String[uploadImages.size()];
        for (int i =0;i < uploadImages.size();i++) {
            imageStr[i] = uploadImages.get(i);
        }
        map.clear();
        map.put("images",imageStr);
        map.put("locationAddress",locationAddress);
        map.put("locationCoordinate",locationCoordinate);
        map.put("moodLabel",moodLabel);
        map.put("name",etTitle.getText().toString());
        map.put("previewStatus",previewStatus);
        map.put("videoCoverUrl","");
        map.put("videoDuration","");
        map.put("videoUrl","");
        presenter.publicDynamic(map);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(this, requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_SELECT_STATE && resultCode == RESULT_OK){
            int imgRes = data.getIntExtra(ExtraConstant.EXTRA_DATA,R.drawable.face_01);
            moodLabel = String.valueOf(data.getIntExtra(ExtraConstant.EXTRA_POSITION,0));
            if(imgRes == 0){
                imgFace.setVisibility(View.GONE);
            }else {
                imgFace.setVisibility(View.VISIBLE);
                imgFace.setImageDrawable(ContextCompat.getDrawable(this,imgRes));
            }
        }else if(requestCode == REQUEST_CODE_SELECT_LOCATION && resultCode == RESULT_OK){
            LocationBean bean = (LocationBean) data.getSerializableExtra(ExtraConstant.EXTRA_LOCATION);
            if(bean != null){
                locationAddress = bean.getCity();
                locationCoordinate = bean.getLongitude()+","+bean.getLatitude();
                tvLocation.setText(locationAddress);
            }
        }
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
    public void publicDynamicSuccess(MdlBaseHttpResp<DataBean> resp) {
        LogAndToastUtil.toast(this,"发布成功");
        finish();
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
        if(resp.getStatus() == HttpConstant.R_HTTP_OK && resp.getData() != null){
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
                // 替换图片
            } else {
                uploadImages.set(position, bean.getUrl());
            }
        }

    }
}

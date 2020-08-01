package com.jsjlzj.wayne.ui.store.personal.storeinfo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.Login.MdlUpload;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.MdlStoreInfo;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.ui.store.personal.storeinfo.set.StoreContentActivity;
import com.jsjlzj.wayne.ui.store.personal.storeinfo.set.StoreInfoPreviewActivity;
import com.jsjlzj.wayne.ui.store.personal.storeinfo.set.StorePhotoActivity;
import com.jsjlzj.wayne.ui.store.personal.storeinfo.set.StoreTimeActivity;
import com.jsjlzj.wayne.ui.store.personal.storeinfo.set.StoreWelfareActivity;
import com.jsjlzj.wayne.ui.store.talent.position.recruit.RecruitCoordinateSelectActivity;
import com.jsjlzj.wayne.utils.ImageUtil;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SelectImageUtils;
import com.jsjlzj.wayne.widgets.dialog.EditDialog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.utils.MyFileProviderUtil;

/**
 * 编辑俱乐部信息
 */
public class StoreInfoSetActivity extends MVPBaseActivity<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView {
    private MdlStoreInfo.DataBean bean;
    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
    }

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, StoreInfoSetActivity.class);

        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_store_info;
    }



    private ImageView image;
    private TextView tvStoreName,tvStoreContent,tvStorePhoto,tvStoreTime,tvStoreWelfare,tvStoreAddress;

    @Override
    protected void initViewAndControl() {
        tvStoreName=findView(R.id.tvStoreName);
        tvStoreContent=findView(R.id.tvStoreContent);
        tvStorePhoto=findView(R.id.tvStorePhoto);
        tvStoreTime=findView(R.id.tvStoreTime);
        tvStoreWelfare=findView(R.id.tvStoreWelfare);
        tvStoreAddress=findView(R.id.tvStoreAddress);
        image=findView(R.id.image);

        tvStoreName.setOnClickListener(clickListener);
        tvStoreContent.setOnClickListener(clickListener);
        tvStorePhoto.setOnClickListener(clickListener);
        tvStoreTime.setOnClickListener(clickListener);
        tvStoreWelfare.setOnClickListener(clickListener);
        tvStoreAddress.setOnClickListener(clickListener);
        image.setOnClickListener(clickListener);
        findView(R.id.btnBack).setOnClickListener(clickListener);
        findView(R.id.btnRight).setOnClickListener(clickListener);

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getStoreInfo(null);

    }

    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnBack:
                    finish();
                    break;
                case R.id.btnRight://预览
                    StoreInfoPreviewActivity.go2this(StoreInfoSetActivity.this,"");
                    break;
                case R.id.tvStoreName://店名
                    showEditDialog();
                    break;
                case R.id.tvStoreContent://描述
                    String text="";
                    if(null!=bean){
                        text=bean.getCompanyProfile();
                    }
                    StoreContentActivity.go2this(StoreInfoSetActivity.this,text);
                    break;
                case R.id.tvStorePhoto://照片
                    if(null!=bean&&null!=bean.getCompanyImages()) {
                        StorePhotoActivity.go2this(StoreInfoSetActivity.this,bean.getCompanyImages());
                    }else{
                        StorePhotoActivity.go2this(StoreInfoSetActivity.this,null);
                    }
                    break;
                case R.id.tvStoreTime://上班时间
                    if(null!=bean) {
                        StoreTimeActivity.go2this(StoreInfoSetActivity.this,TextUtils.isEmpty(bean.getWorkTimeStart())?"":bean.getWorkTimeStart(),TextUtils.isEmpty(bean.getWorkTimeEnd())
                                ?"":bean.getWorkTimeEnd(),TextUtils.isEmpty(bean.getRestTimeCode())?"":bean.getRestTimeCode(),TextUtils.isEmpty(bean.getWorkOvertimeCode())?"":bean.getWorkOvertimeCode());
                    }else{
                        StoreTimeActivity.go2this(StoreInfoSetActivity.this,"","","","");
                    }
                    break;
                case R.id.tvStoreWelfare://福利
                    if(null!=bean&&null!=bean.getCompanyBenefits()) {
                        StoreWelfareActivity.go2this(StoreInfoSetActivity.this, bean.getCompanyBenefits());
                    }else{
                        StoreWelfareActivity.go2this(StoreInfoSetActivity.this,null);
                    }
                    break;
                case R.id.tvStoreAddress://门店位置
                    RecruitCoordinateSelectActivity.go2this2(StoreInfoSetActivity.this,bean.getStoreAddress(),bean.getStoreDoorplate());
                    break;
                case R.id.image:
                    presenter.autoObtainStoragePermission(StoreInfoSetActivity.this,0);
                    break;
            }
        }
    }

    private static final int HEAD_PIC = 10000;
    private static final int CROP_HEAD_PIC = 10001;
    private String cropHeadPicPath,imgUrl;

    @Override
    public void selectPhoto(int position) {
        SelectImageUtils.selectPhoto(this, getString(R.string.takephoto), false, true, 1);
    }

    @Override
    public void onUploadSuccess(String imgUrl, int position) {
        cropHeadPicPath = imgUrl;
        presenter.upload(imgUrl);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        presenter.onRequestPermissionsResult(this, requestCode, grantResults);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(this,requestCode,resultCode,data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case HEAD_PIC:
                    if (data != null) {
                        ArrayList<String> photos =
                                data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                        String path = photos.get(0);

                        Uri uri = MyFileProviderUtil.getUriForFile(this, path);
                        cropHeadPicPath = ImageUtil.cropTeamLogoPic(this, uri, CROP_HEAD_PIC);
                    }
                    break;
                case CROP_HEAD_PIC:
                    if (data != null) {
//                        image.setImageBitmap(ImageTool.createImageThumbnail(cropHeadPicPath));
                        presenter.upload(cropHeadPicPath);
                    }
                    break;
            }
        }

    }

    @Override
    public void showStoreInfo(MdlBaseHttpResp<MdlStoreInfo> resp) {
        if(resp.getStatus()==HttpConstant.R_HTTP_OK&&null!=resp.getData()&&null!=resp.getData().getData()) {
            bean=resp.getData().getData();
            tvStoreName.setText(TextUtils.isEmpty(bean.getStoreName())?"":bean.getStoreName());
            tvStoreContent.setText(TextUtils.isEmpty(bean.getCompanyProfile())?"":bean.getCompanyProfile());
            if(!TextUtils.isEmpty(bean.getWorkTimeStart())&&!TextUtils.isEmpty(bean.getWorkTimeEnd())) {
                tvStoreTime.setText("上午"+bean.getWorkTimeStart()+"-下午"+bean.getWorkTimeEnd()+" "+bean.getRestTime()+" "+bean.getWorkOvertime());
            }
            if(null!=bean.getCompanyBenefits()&&bean.getCompanyBenefits().size()>0) {
                tvStoreWelfare.setText("已选择/已选"+bean.getCompanyBenefits().size()+"项");
            }
                setImg(bean.getBrandLogo(), image);
            if(bean.getCompanyImages()!=null&&bean.getCompanyImages().size()>0) {
                tvStorePhoto.setText("已添加/添加" + bean.getCompanyImages().size() + "张图片");
            }
            tvStoreAddress.setText(TextUtils.isEmpty(bean.getStoreAddress())?"":bean.getStoreAddress());
        }
    }

    Map<Object,Object> map=null;
    @Override
    public void showUpload(MdlBaseHttpResp<MdlUpload> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK&&null!=resp.getData()&&null!=resp.getData().getData()) {
            imgUrl = resp.getData().getData().getUrl();
            if(null==map)map=new HashMap();
            if(!TextUtils.isEmpty(imgUrl)){
                setImg(cropHeadPicPath, image);
                map.put("brandLogo", imgUrl);
                presenter.saveBrandLogo(map);
//                String[] pics = imgUrl.split("/");
//                if (pics != null) {
//
//                }
            }
        }else{
            LogAndToastUtil.toast(resp.getMsg());
        }
    }
    private void showEditDialog(){
        new EditDialog(this, "请输入门店名称", new EditDialog.ClickListener() {
            @Override
            public void clickConfirm(String s) {
                if(!TextUtils.isEmpty(s))tvStoreName.setText(s);
            }

            @Override
            public void clickCancel() {

            }
        }).show();
    }

    @Override
    public void showSaveBrandLogo(MdlBaseHttpResp<MdlStoreInfo> resp) {
        if(resp.getStatus()==HttpConstant.R_HTTP_OK&&null!=resp.getData()&&null!=resp.getData().getData()){
            LogAndToastUtil.toast(this,"保存成功");
        }else{
            LogAndToastUtil.toast(this,resp.getMsg());
        }
    }
}
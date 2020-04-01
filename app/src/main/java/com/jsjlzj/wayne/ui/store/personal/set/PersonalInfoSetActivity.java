package com.jsjlzj.wayne.ui.store.personal.set;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.constant.MyPermissionConstant;
import com.jsjlzj.wayne.entity.Login.MdlUpload;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.utils.GlidUtils;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SelectImageUtils;
import com.jsjlzj.wayne.utils.permission.PermissionUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * 用户个人信息
 */
public class PersonalInfoSetActivity extends MVPBaseActivity<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView {
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.edName)
    EditText edName;
    @BindView(R.id.edPosition)
    EditText edPosition;
    @BindView(R.id.edWechat)
    EditText edWechat;

    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
    }

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, PersonalInfoSetActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_store_personl_info_set;
    }

//    public MdlUser.MdlUserBean user;

    @Override
    protected void initViewAndControl() {
        initTitle("修改资料");
        mRightTv.setTextColor(ContextCompat.getColor(this, R.color.color_4F9BFA));
        mRightTv.setTextSize(15);
        mRightTv.setText("保存");
        mRightTv.setVisibility(View.VISIBLE);


        image.setOnClickListener(clickListener);
        edName.setOnClickListener(clickListener);
        edPosition.setOnClickListener(clickListener);
        mRightTv.setOnClickListener(clickListener);
        edWechat.setOnClickListener(clickListener);
        presenter.selectStoreUserInfo(null);
    }

    private void setEditAble(boolean able) {
        if (able) {
            edName.setFocusableInTouchMode(true);
            edName.setFocusable(true);
            edName.requestFocus();
            image.setClickable(true);
            edPosition.setFocusableInTouchMode(true);
            edPosition.setFocusable(true);
            edPosition.requestFocus();

            edWechat.setFocusableInTouchMode(true);
            edWechat.setFocusable(true);
            edWechat.requestFocus();
        } else {
            edName.setFocusable(false);
            edName.setFocusableInTouchMode(false);
            image.setClickable(false);
            edPosition.setFocusable(false);
            edPosition.setFocusableInTouchMode(false);

            edWechat.setFocusable(false);
            edWechat.setFocusableInTouchMode(false);

        }
    }


    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.tv_right_btn:
//                btnKeep.setSelected(!btnKeep.isSelected());
//                btnKeep.setText(btnKeep.isSelected() ? "保存" : "编辑");
//                setEditAble(btnKeep.isSelected());
//                if (!btnKeep.isSelected()) {//保存信息
//                }
                keepInfo();
                break;
            case R.id.image:
                presenter.autoObtainStoragePermission(this,0);
//                clickSelectHeadPic();
                break;
        }
    }

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


    private Map<Object, Object> map;
    private String imgUrl;

    private void keepInfo() {
        if (map == null) map = new HashMap<>();

        String strName = edName.getText().toString();
        String strPosition = edPosition.getText().toString();
        String strWechat = edWechat.getText().toString();
        if (TextUtils.isEmpty(strName)) {
            LogAndToastUtil.toast("名字不能为空");
            return;
        }
        if (TextUtils.isEmpty(strPosition)) {
            strPosition = "";
        }
        if (TextUtils.isEmpty(strWechat)) {
            strWechat = "";
        }
        map.put("name", strName);
        map.put("position", strPosition);
        map.put("wxid", strWechat);
        if (!TextUtils.isEmpty(imgUrl)) {
            map.put("headImg", imgUrl);
        }
        presenter.saveStoreUserInfo(map);
    }

    @Override
    public void showUpload(MdlBaseHttpResp<MdlUpload> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            imgUrl = resp.getData().getData().getUrl();
            GlidUtils.setCircleGrid(this,cropHeadPicPath,image);
        }
    }

    @Override
    public void showSaveStoreUserInfo(MdlBaseHttpResp<MdlUser> resp) {
        LogAndToastUtil.toast(this, "修改成功");
        finish();

    }

    private static final int HEAD_PIC = 10000;
    private static final int CROP_HEAD_PIC = 10001;
    private String cropHeadPicPath;

    private void clickSelectHeadPic() {
        PermissionUtil.checkPermission(this, MyPermissionConstant.READ_EXTERNAL_STORAGE + HEAD_PIC, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }


//    @Override
//    public void permissionSuccess(int permissionReqCode) {
//        super.permissionSuccess(permissionReqCode);
//        switch (permissionReqCode) {
//            case MyPermissionConstant.READ_EXTERNAL_STORAGE + HEAD_PIC:
//                PhotoPicker.builder()
//                        .setPhotoCount(0)
//                        .setShowCamera(true)
//                        .setShowGif(false)
//                        .setPreviewEnabled(false)
//                        .start(this, HEAD_PIC);
//                break;
//        }
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(this,requestCode,resultCode,data);
//        if (resultCode == Activity.RESULT_OK) {
//            switch (requestCode) {
//                case HEAD_PIC:
//                    if (data != null) {
//                        ArrayList<String> photos =
//                                data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
//                        String path = photos.get(0);
//
//                        Uri uri = MyFileProviderUtil.getUriForFile(this, path);
//                        cropHeadPicPath = ImageUtil.cropTeamLogoPic(this, uri, CROP_HEAD_PIC);
//                    }
//                    break;
//                case CROP_HEAD_PIC:
//                    if (data != null) {
////                        image.setImageBitmap(ImageTool.createImageThumbnail(cropHeadPicPath));
//                        presenter.upload(cropHeadPicPath);
//                    }
//                    break;
//            }
//        }

    }

    @Override
    public void showSelectStoreUserInfo(MdlBaseHttpResp<MdlUser> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            MdlUser.MdlUserBean user = resp.getData().getData();
            edName.setText(TextUtils.isEmpty(user.getName()) ? "" : user.getName());
            edPosition.setText(TextUtils.isEmpty(user.getPosition()) ? "" : user.getPosition());
            edWechat.setText(TextUtils.isEmpty(user.getWxId()) ? "" : user.getWxId());
            imgUrl = user.getHeadImg();
            GlidUtils.setCircleGrid(this,imgUrl,image);
//            setEditAble(false);
        } else {
            LogAndToastUtil.toast(this, resp.getMsg());
        }

    }

}
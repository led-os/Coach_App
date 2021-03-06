package com.jsjlzj.wayne.ui.store.attestation;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.Login.MdlUpload;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.MdlStoreInfo;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.basis.LoginRoleSelectActivity;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.utils.GlidUtils;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SelectImageUtils;
import com.jsjlzj.wayne.utils.Utility;

import java.util.HashMap;
import java.util.Map;

import static com.jsjlzj.wayne.ui.store.attestation.AttestationInfoActivity.REQUESTCODE;

/**
 * 编辑门店信息
 */
public class AttestationActivity extends MVPBaseActivity<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView {
    private Map<Object, Object> param;
    private String storeName;
    private String businessLicense;
    private String staffNumCode;

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, AttestationActivity.class);
        context.startActivity(intent);
    }

    private static final int HEAD_PIC = 10;
    private static final int CROP_HEAD_PIC = 12;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_attestation;
    }


    private EditText edName, edStoreName, edPositionName, edWxId;
    private ImageView imCamera;
    private TextView edContent,btnService;

    @Override
    protected void initViewAndControl() {
        edContent = findView(R.id.edContent);
        edName = findView(R.id.edName);
        edStoreName = findView(R.id.edStoreName);
        edPositionName = findView(R.id.edPositionName);
        edWxId = findView(R.id.edWxId);
        btnService = findView(R.id.btnService);
        imCamera = findView(R.id.imCamera);
        edContent.setClickable(false);
        imCamera.setOnClickListener(clickListener);
        findView(R.id.btnConfirm).setOnClickListener(clickListener);
        findView(R.id.btnBack).setOnClickListener(clickListener);
        btnService.setOnClickListener(clickListener);
        edContent.setOnClickListener(clickListener);
        edStoreName.setOnClickListener(clickListener);
    }

    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
    }

    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.imCamera://照相机
                    clickSelectHeadPic();
                    break;
                case R.id.btnService:
                    LoginRoleSelectActivity.go2This(AttestationActivity.this,true);
                    break;
                case R.id.btnConfirm://
                    done();
                    break;
                case R.id.btnBack://返回
                    finish();
                    break;
//                case R.id.edContent://俱乐部介绍
//                    CharSequence text = edContent.getText();
//                    StoreContentActivity.go2this(AttestationActivity.this,text.toString());
//                    break;
                case R.id.edStoreName://俱乐部名称
                    AttestationInfoActivity.go2this(AttestationActivity.this, 0);
                    break;
            }
        }
    }

    private void clickSelectHeadPic() {
        presenter.autoObtainStoragePermission(this, 0);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        presenter.onRequestPermissionsResult(this, requestCode, grantResults);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(this, requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUESTCODE:
                    businessLicense = data.getStringExtra("businessLicense");
                    staffNumCode = data.getStringExtra("staffNumCode");
                    storeName = data.getStringExtra("storeName");
                    edStoreName.setText(storeName);
                    break;
            }
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


    private String imUrl, cropHeadPicPath;

    private void done() {
//        String edContentStr = Utility.getEditTextStr(edContent);
        String edNameStr = Utility.getEditTextStr(edName);
//        String edStoreNameStr = Utility.getEditTextStr(edStoreName);
        String edPositionNameStr = Utility.getEditTextStr(edPositionName);
        String edWxIdStr = Utility.getEditTextStr(edWxId);
//        if (TextUtils.isEmpty(edContentStr)) {
//            LogAndToastUtil.toast(this, "创建俱乐部名片 不能为空");
//            return;
//        }
        if (TextUtils.isEmpty(edNameStr)) {
            LogAndToastUtil.toast(this, "姓名 不能为空");
            return;
        }
//        if (TextUtils.isEmpty(edStoreNameStr)){
//            LogAndToastUtil.toast(this, "俱乐部名称 不能为空");
//            return;
//        }
        if (TextUtils.isEmpty(edPositionNameStr)) {
            LogAndToastUtil.toast(this, "我的职位 不能为空");
            return;
        }
        if (TextUtils.isEmpty(edWxIdStr)) {
            LogAndToastUtil.toast(this, "微信号 不能为空");
            return;
        }
        if (TextUtils.isEmpty(imUrl)) {
            if (TextUtils.isEmpty(cropHeadPicPath))
                LogAndToastUtil.toast(this, "头像 不能为空");
            else LogAndToastUtil.toast(this, "图片 正在上传请稍后");
            return;
        }
        if (null == param) param = new HashMap<>();
        param.put("businessLicense", businessLicense);
        param.put("headImg", imUrl);
        param.put("name", edNameStr);
        param.put("position", edPositionNameStr);
        param.put("staffNumCode", staffNumCode);
        param.put("storeName", storeName);
        param.put("wxid", edWxIdStr);
        presenter.saveStoreAuth(param);
    }

    @Override
    public void showUpload(MdlBaseHttpResp<MdlUpload> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            imUrl = resp.getData().getData().getUrl();
            if (!TextUtils.isEmpty(imUrl)) {
                GlidUtils.setCircleGrid(this,imUrl,imCamera);
            }
        } else {
            LogAndToastUtil.toast(this, resp.getMsg());
        }
    }

    @Override
    public void showSaveStoreAuth(MdlBaseHttpResp<MdlStoreInfo> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            AttestationInfoActivity.go2this(AttestationActivity.this, 1);
        } else {
            LogAndToastUtil.toast(this, resp.getMsg());
        }
    }
}
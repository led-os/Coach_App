package com.jsjlzj.wayne.ui.store.attestation;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.constant.MyPermissionConstant;
import com.jsjlzj.wayne.entity.Login.MdlUpload;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.ItemsBean;
import com.jsjlzj.wayne.entity.store.MdlDict;
import com.jsjlzj.wayne.entity.store.MdlStoreInfo;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.Utility;
import com.jsjlzj.wayne.utils.permission.PermissionUtil;
import com.jsjlzj.wayne.widgets.dialog.NumberDialog;
import com.jsjlzj.wayne.widgets.dialog.PhoneDialog;

import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;

/**
 * 俱乐部信息
 */
public class AttestationInfoActivity extends MVPBaseActivity<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView {
    private String staffNumCode;
    private int isFlag;
    private String[] datas=new String[6];

    public static void go2this(Activity context, int isFlag) {
        Intent intent = new Intent(context, AttestationInfoActivity.class);
        intent.putExtra("isFlag", isFlag);
        context.startActivityForResult(intent, REQUESTCODE);
    }

    private static final int HEAD_PIC = 10000;
    public static final int REQUESTCODE = 1;
    private static final int CROP_HEAD_PIC = 10001;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_attestation_info;
    }


    private EditText edName;
    private TextView tvNumber;
    private ImageView image, btnIcAdd, btnIcClose;
    private View btnTips, btnService, btnConfirm, bgView;
    private List<ItemsBean> itemsBean;
    @Override
    protected void initViewAndControl() {
        MyApp.ME.dm = Utility.getDisplayScreenSize(this);
        if(MyApp.mdlDict!=null&&MyApp.mdlDict.getStaff_num()!=null){
            MdlDict.DataBean.SalaryRequiredBean bean=MyApp.mdlDict.getStaff_num();
            if(bean!=null&&bean.getItems()!=null) {
                itemsBean = MyApp.mdlDict.getStaff_num().getItems();
                for (int i = 0; i < bean.getItems().size(); i++) {
                    datas[i] = bean.getItems().get(i).getName();
                }
            }
        }else{
            presenter.getAll(null);
        }
        edName = findView(R.id.edName);
        tvNumber = findView(R.id.tvNumber);
        btnIcAdd = findView(R.id.btnIcAdd);
        btnIcClose = findView(R.id.btnIcClose);
        image = findView(R.id.image);
        btnTips = findView(R.id.btnTips);
        btnService = findView(R.id.btnService);
        btnConfirm = findView(R.id.btnConfirm);
        bgView = findView(R.id.bgView);

        bgView.setOnClickListener(clickListener);
        btnConfirm.setOnClickListener(clickListener);
        tvNumber.setOnClickListener(clickListener);
        btnService.setOnClickListener(clickListener);
        btnIcAdd.setOnClickListener(clickListener);
        btnIcClose.setOnClickListener(clickListener);
        findView(R.id.btnBack).setOnClickListener(clickListener);
        isFlag = getIntent().getIntExtra("isFlag",0);

        if (isFlag==1) {
            presenter.getStoreInfo(null);
            edName.setEnabled(false);
            tvNumber.setClickable(false);
            btnTips.setVisibility(View.VISIBLE);
//            btnService.setVisibility(View.VISIBLE);
            bgView.setVisibility(View.VISIBLE);
            btnConfirm.setVisibility(View.GONE);
        }
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
                case R.id.bgView:
                    break;
//                case R.id.btnService:
//                    showPhoneDialog();
//                    break;
                case R.id.tvNumber:
                    showNumberDialog();
                    break;
                case R.id.btnIcAdd://
                    clickSelectHeadPic();
                    break;
                case R.id.btnIcClose://
                    image.setVisibility(View.GONE);
                    btnIcAdd.setVisibility(View.VISIBLE);
                    btnIcClose.setVisibility(View.GONE);
                    imUrl = "";
                    break;
                case R.id.btnConfirm://

                    done();
                    break;
                case R.id.btnBack://返回
                    finish();
                    break;
            }
        }
    }

    private void showNumberDialog() {
        new NumberDialog(this, datas, new NumberDialog.ClickListener() {
            @Override
            public void clickConfirm(String data, int position) {
                tvNumber.setText(data);
                staffNumCode = itemsBean.get(position).getCode();
            }
        }).show();
    }

    private void showPhoneDialog() {
        new PhoneDialog(this, new PhoneDialog.ClickListener() {
            @Override
            public void clickConfirm(String data) {
                clickCallPhone();
            }
        }).show();
    }

    private void clickSelectHeadPic() {
        PermissionUtil.checkPermission(this, MyPermissionConstant.READ_EXTERNAL_STORAGE + HEAD_PIC, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    private void clickCallPhone() {
        PermissionUtil.checkPermission(
                this,
                MyPermissionConstant.CALL_PHONE,
                Manifest.permission.CALL_PHONE);
    }

    @Override
    public void permissionSuccess(int permissionReqCode) {
        super.permissionSuccess(permissionReqCode);
        switch (permissionReqCode) {
            case MyPermissionConstant.READ_EXTERNAL_STORAGE + HEAD_PIC:
                PhotoPicker.builder()
                        .setPhotoCount(0)
                        .setShowCamera(true)
                        .setShowGif(false)
                        .setPreviewEnabled(false)
                        .start(this, HEAD_PIC);
                break;
            case MyPermissionConstant.CALL_PHONE:
                Intent intent = new Intent();
                //设置拨打电话的动作
                intent.setAction(Intent.ACTION_DIAL);//ACTION_DIAL  ACTION_CALL
                //设置拨打电话的号码
                intent.setData(Uri.parse("tel:" + getString(R.string.phone)));
                //开启打电话的意图
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case HEAD_PIC:
                    if (data != null) {
                        ArrayList<String> photos =
                                data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                        String path = photos.get(0);

//                        Uri uri = MyFileProviderUtil.getUriForFile(this, path);
//                        cropHeadPicPath = ImageUtil.cropTeamLogoPic(this, uri, CROP_HEAD_PIC);
                        cropHeadPicPath=path;
                        image.setVisibility(View.VISIBLE);
                        btnIcAdd.setVisibility(View.GONE);
                        btnIcClose.setVisibility(View.VISIBLE);
                        presenter.upload(cropHeadPicPath);
                    }
                    break;

            }
        }

    }


    private String imUrl, cropHeadPicPath;

    private void done() {
        Intent intent = new Intent();
        String edNameStr = Utility.getEditTextStr(edName);
        if (TextUtils.isEmpty(edNameStr)) {
            LogAndToastUtil.toast(this, "姓名 不能为空");
            return;
        }
        if (TextUtils.isEmpty(staffNumCode)) {
            LogAndToastUtil.toast(this, "规模 不能为空");
            return;
        }
        if (TextUtils.isEmpty(imUrl)) {
            if (TextUtils.isEmpty(cropHeadPicPath))
                LogAndToastUtil.toast(this, "营业执照 不能为空");
            else LogAndToastUtil.toast(this, "图片 正在上传请稍后");
            return;
        }
        intent.putExtra("storeName", edNameStr);
        intent.putExtra("staffNumCode", staffNumCode);
        intent.putExtra("businessLicense", imUrl);
        setResult(RESULT_OK, intent);
        finish();

    }
    @Override
    public void showStoreInfo(MdlBaseHttpResp<MdlStoreInfo> resp) {
        if(resp.getStatus()==HttpConstant.R_HTTP_OK&&null!=resp.getData()&&null!=resp.getData().getData()) {
            MdlStoreInfo.DataBean bean=resp.getData().getData();
            edName.setText(bean.getStoreName());
            tvNumber.setText(bean.getStaffNum());
            image.setVisibility(View.VISIBLE);
            btnIcAdd.setVisibility(View.GONE);
            setImg(bean.getBusinessLicense(), image);
        }
    }

    @Override
    public void showUpload(MdlBaseHttpResp<MdlUpload> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            if(!TextUtils.isEmpty(resp.getData().getData().getUrl())) {
                String[] imgs = resp.getData().getData().getUrl().split("/");
                if (imgs.length != 0) {
                    imUrl = imgs[imgs.length - 1];
                    setImg(cropHeadPicPath, image);
                }
            }
        } else {
            LogAndToastUtil.toast(this, resp.getMsg());
        }
    }

    @Override
    public void showResultgetAll(MdlBaseHttpResp<MdlDict> resp){
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            MyApp.mdlDict=resp.getData().getData();
            MdlDict.DataBean.SalaryRequiredBean bean=resp.getData().getData().getStaff_num();
            if(null!=bean) {
                itemsBean = bean.getItems();
                if (itemsBean != null && itemsBean.size() > 0) {
                    for (int i = 0; i < itemsBean.size(); i++) {
                        datas[i] = itemsBean.get(i).getName();
                    }
                }
            }
        }
        }
}
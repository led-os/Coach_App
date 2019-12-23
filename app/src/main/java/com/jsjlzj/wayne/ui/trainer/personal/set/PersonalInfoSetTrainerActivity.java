package com.jsjlzj.wayne.ui.trainer.personal.set;

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
import com.jsjlzj.wayne.entity.trainer.MdlDetailT;
import com.jsjlzj.wayne.entity.trainer.MdlsaveAdvantage;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.utils.ImageUtil;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.permission.PermissionUtil;
import com.jsjlzj.wayne.widgets.dialog.DateDialog;
import com.jsjlzj.wayne.widgets.dialog.EducationDialog;
import com.jsjlzj.wayne.widgets.dialog.SexDialog;
import com.jsjlzj.wayne.widgets.photo.ImageTool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.utils.MyFileProviderUtil;

/**
 * 编辑个人信息  教练端
 */
public class PersonalInfoSetTrainerActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {


    public static void go2this(Activity context) {
        Intent intent = new Intent(context, PersonalInfoSetTrainerActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_trainer_personl_info_set;
    }

    private ImageView image;
    private TextView btnKeep, tvSex, tvBirthday, tvEducation, tvJobTime;
    private EditText edName, edWechat;
    private String[] datas=new String[7];
    private List<ItemsBean> itemsBean;

    @Override
    protected void initViewAndControl() {
        image = findView(R.id.image);
        btnKeep = findView(R.id.btnKeep);
        edName = findView(R.id.edName);
        edWechat = findView(R.id.edWechat);
        tvSex = findView(R.id.tvSex);
        tvBirthday = findView(R.id.tvBirthday);
        tvEducation = findView(R.id.tvEducation);
        tvJobTime = findView(R.id.tvJobTime);


        findView(R.id.btnBack).setOnClickListener(clickListener);
        btnKeep.setOnClickListener(clickListener);
        image.setOnClickListener(clickListener);

        tvSex.setOnClickListener(clickListener);
        tvBirthday.setOnClickListener(clickListener);
        tvEducation.setOnClickListener(clickListener);
        tvJobTime.setOnClickListener(clickListener);
        setInfo();
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

    private void setInfo() {
        presenter.getDetailT(null);
        setEditAble(false);
        if(MyApp.mdlDict!=null&&MyApp.mdlDict.getEducation_level()!=null){
            MdlDict.DataBean.SalaryRequiredBean bean=MyApp.mdlDict.getEducation_level();
            if(bean!=null&&bean.getItems()!=null) {
                itemsBean = MyApp.mdlDict.getEducation_level().getItems();
                for (int i = 0; i < bean.getItems().size(); i++) {
                    datas[i] = bean.getItems().get(i).getName();
                }
            }
        }else{
            presenter.getAll(null);
        }
    }

    private void setEditAble(boolean able) {
        if (able) {
            edName.setFocusableInTouchMode(true);
            edName.setFocusable(true);
            edName.requestFocus();
            edWechat.setFocusableInTouchMode(true);
            edWechat.setFocusable(true);
            edWechat.requestFocus();
            //up-by
            image.setClickable(true);
            tvSex.setClickable(true);
            tvBirthday.setClickable(true);
            tvEducation.setClickable(true);
            tvJobTime.setClickable(true);
        } else {
            edName.setFocusable(false);
            edName.setFocusableInTouchMode(false);
            edWechat.setFocusable(false);
            edWechat.setFocusableInTouchMode(false);
            //up-by
            image.setClickable(false);
            tvSex.setClickable(false);
            tvBirthday.setClickable(false);
            tvEducation.setClickable(false);
            tvJobTime.setClickable(false);
        }
    }


    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.tvSex://性别
                    showSexDialog();
                    break;
                case R.id.tvEducation://学历
                    showEducationDialog();
                    break;
                case R.id.tvBirthday://生日
                    showDateDialog(tvBirthday);
                    break;
                case R.id.tvJobTime://参加工作时间
                    showDateDialog(tvJobTime);
                    break;
                case R.id.btnBack:
                    finish();
                    break;
                case R.id.btnKeep:
                    btnKeep.setSelected(!btnKeep.isSelected());
                    btnKeep.setText(btnKeep.isSelected() ? "保存" : "编辑");
                    setEditAble(btnKeep.isSelected());
                    if (!btnKeep.isSelected()) {//保存信息
                        keepInfo();
                    }
                    break;
                case R.id.image:
                    clickSelectHeadPic();
                    break;
            }
        }
    }

    private Map<Object, Object> map;
    private String birth;
    private String educationLevelCode;
    private String headImg;
    private String joinWorkDate;
    private int sexCode;

    private void keepInfo() {
        if (map == null) map = new HashMap<>();

        String strName = edName.getText().toString();
        String strWechat = edWechat.getText().toString();
        birth=tvBirthday.getText().toString();
        joinWorkDate=tvJobTime.getText().toString();
//        if (TextUtils.isEmpty(headImg)) {
//            LogAndToastUtil.toast("请上传头像");
//            return;
//        }
//        if (TextUtils.isEmpty(strName)) {
//            LogAndToastUtil.toast("请输入名字");
//            return;
//        }
//        if (TextUtils.isEmpty(birth)) {
//            LogAndToastUtil.toast("请选择生日");
//            return;
//        }
//
//        if (TextUtils.isEmpty(educationLevelCode)) {
//            LogAndToastUtil.toast("请选择学历");
//            return;
//        }
//        if (TextUtils.isEmpty(joinWorkDate)) {
//            LogAndToastUtil.toast("请选择参加工作时间");
//            return;
//        }
//        if (sexCode==0) {
//            LogAndToastUtil.toast("请选择性别");
//            return;
//        }
        map.put("name", strName);
        map.put("wxId", strWechat);
        map.put("birth", birth);
        map.put("educationLevelCode", educationLevelCode);
        map.put("headImg", headImg);
        map.put("joinWorkDate", joinWorkDate);
        map.put("sexCode", sexCode);

        presenter.saveCvBaseInfoT(map);
    }

    @Override
    public void saveCvBaseInfoT(MdlBaseHttpResp<MdlsaveAdvantage> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            LogAndToastUtil.toast("保存成功");
            finish();
        } else {
            LogAndToastUtil.toast(resp.getMsg());
        }
    }

//    @Override
//    public void showSaveStoreUserInfo(MdlBaseHttpResp<MdlUser> resp) {
//
//    }

    private static final int HEAD_PIC = 10000;
    private static final int CROP_HEAD_PIC = 10001;
    private String cropHeadPicPath;

    private void clickSelectHeadPic() {
        PermissionUtil.checkPermission(this, MyPermissionConstant.READ_EXTERNAL_STORAGE + HEAD_PIC, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
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

                        Uri uri = MyFileProviderUtil.getUriForFile(this, path);
                        cropHeadPicPath = ImageUtil.cropTeamLogoPic(this, uri, CROP_HEAD_PIC);
                    }
                    break;
                case CROP_HEAD_PIC:
                    if (data != null) {
                            presenter.upload(cropHeadPicPath);
                    }
                    break;
            }
        }

    }

    //学历
    private void showEducationDialog() {
        new EducationDialog(this, new EducationDialog.ClickListener() {
            @Override
            public void clickConfirm(String data, int position) {
                tvEducation.setText(data);
                educationLevelCode = itemsBean.get(position).getCode();
            }
        },datas).show();
    }

    //性别
    private void showSexDialog() {
        new SexDialog(this, new SexDialog.ClickListener() {
            @Override
            public void clickConfirm(int isMan) {
                sexCode=isMan;
                tvSex.setText(isMan == 1 ? "男" : "女");
            }
        }).show();
    }

    //生日
    private void showDateDialog(TextView textView) {
        new DateDialog(this, new DateDialog.ClickListener() {
            @Override
            public void clickConfirm(int year, int month, int day) {
                textView.setText(year + "-" + month + "-" + day);
            }
        }).show();
    }


    @Override
    public void showUpload(MdlBaseHttpResp<MdlUpload> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            headImg = resp.getData().getData().getUrl();
            if (!TextUtils.isEmpty(headImg)) {
                image.setImageBitmap(ImageTool.createImageThumbnail(cropHeadPicPath));
                String[] imgs = headImg.split("/");
                if (imgs.length != 0) {
                    headImg = imgs[imgs.length - 1];
                }
            }
        }else{
            LogAndToastUtil.toast(resp.getMsg());
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

    public void  getDetailT(MdlBaseHttpResp<MdlDetailT> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            MdlDetailT.DataBean user=resp.getData().getData();
            edName.setText(TextUtils.isEmpty(user.getName() ) ? "":user.getName());
            edWechat.setText(TextUtils.isEmpty(user.getWxId()) ? "":user.getWxId());
            setImg(user.getHeadImg(),image);
            tvSex.setText(user.getSex());
            sexCode=user.getSexCode();
            tvBirthday.setText(user.getBirth());
            tvJobTime.setText(user.getJoinWorkDate());
            tvEducation.setText(user.getHighestEducationLevel());
            educationLevelCode=user.getHighestEducationLevelCode();
        }
        }
}
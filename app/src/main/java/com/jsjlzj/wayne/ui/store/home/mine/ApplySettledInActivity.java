package com.jsjlzj.wayne.ui.store.home.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.ImageSelectAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.DataBean;
import com.jsjlzj.wayne.entity.Login.MdlUpload;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.ui.publicac.AddressActivity;
import com.jsjlzj.wayne.ui.store.shopping.ShoppingEvaluateActivity;
import com.jsjlzj.wayne.utils.GlidUtils;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SelectImageUtils;
import com.jsjlzj.wayne.widgets.dialog.EducationDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.GridLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: ApplySettledInActivity
 * @Description: 申请厂家入驻
 * @Author: 曾海强
 * @CreateDate:
 */
public class ApplySettledInActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    @BindView(R.id.et_cpy_name)
    EditText etCpyName;
    @BindView(R.id.et_brand_name)
    EditText etBrandName;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.ll_location)
    LinearLayout llLocation;
    @BindView(R.id.et_detail_location)
    EditText etDetailLocation;
    @BindView(R.id.et_charge)
    EditText etCharge;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_weixin)
    EditText etWeixin;
    @BindView(R.id.et_trademark)
    EditText etTrademark;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.ll_type)
    LinearLayout llType;
    @BindView(R.id.img_yyzz)
    ImageView imgYyzz;
    @BindView(R.id.rel_yyzz)
    RelativeLayout relYyzz;
    @BindView(R.id.img_scgh)
    ImageView imgScgh;
    @BindView(R.id.rel_scgh)
    RelativeLayout relScgh;
    @BindView(R.id.tv_commit)
    TextView tvCommit;


    private String province,provinceCode,city,cityCode,area,areaCode;
    private String yyzzUrl,scghUrl;
    private int authenticationPos;
    private String[] typeStrs = new String[4];
    private int typePos ;

    public static void go2this(Activity activity) {
        activity.startActivity(new Intent(activity, ApplySettledInActivity.class));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_apply_settled_in;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initTitle("申请入驻");
        typeStrs = getResources().getStringArray(R.array.settled_in_type_list);
        llLocation.setOnClickListener(clickListener);
        llType.setOnClickListener(clickListener);
        tvCommit.setOnClickListener(clickListener);
        relYyzz.setOnClickListener(clickListener);
        relScgh.setOnClickListener(clickListener);
    }



    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()){
            case R.id.ll_location:
                AddressActivity.go2this(this);
                break;
            case R.id.ll_type:
                new EducationDialog(this, new EducationDialog.ClickListener() {
                    @Override
                    public void clickConfirm(String data, int position) {
                        typePos = position+1;
                        tvType.setText(data);
                    }
                }, typeStrs).show();
                break;
            case R.id.tv_commit:
                applySettledIn();
                break;
            case R.id.rel_yyzz:
                presenter.autoObtainStoragePermission(this, 0);
                break;
            case R.id.rel_scgh:
                presenter.autoObtainStoragePermission(this, 1);
                break;
            default:break;
        }
    }



    private void applySettledIn(){
        if(TextUtils.isEmpty(etCpyName.getText().toString())){
            LogAndToastUtil.toast("请输入真实企业名称");
            return;
        }
        if(TextUtils.isEmpty(etBrandName.getText().toString())){
            LogAndToastUtil.toast("请输入真实品牌名称");
            return;
        }

        if(TextUtils.isEmpty(tvLocation.getText().toString()) || "请选择".equals(tvLocation.getText().toString())){
            LogAndToastUtil.toast("请选择所在地区");
            return;
        }
        if(TextUtils.isEmpty(etDetailLocation.getText().toString())){
            LogAndToastUtil.toast("请输入详细地址");
            return;
        }
        if(TextUtils.isEmpty(etCharge.getText().toString())){
            LogAndToastUtil.toast("请输入业务负责人姓名");
            return;
        }
        if(TextUtils.isEmpty(etPhone.getText().toString())){
            LogAndToastUtil.toast("请输入手机号");
            return;
        }
        if(TextUtils.isEmpty(etWeixin.getText().toString())){
            LogAndToastUtil.toast("请输入真实微信号");
            return;
        }
        if(TextUtils.isEmpty(etTrademark.getText().toString())){
            LogAndToastUtil.toast("请输入商标注册书上的商标号");
            return;
        }
        if(TextUtils.isEmpty(tvType.getText().toString()) || "请选择".equals(tvType.getText().toString())){
            LogAndToastUtil.toast("请选择套餐类型");
            return;
        }
        if(TextUtils.isEmpty(yyzzUrl)){
            LogAndToastUtil.toast("请选择营业执照图片");
            return;
        }
        if(TextUtils.isEmpty(scghUrl)){
            LogAndToastUtil.toast("请选择商标注册证书图片");
            return;
        }
       Map<Object,Object> map = new HashMap<>();
        map.put("address",etDetailLocation.getText().toString());
        map.put("province",province);
        map.put("provinceId",provinceCode);
        map.put("city",city);
        map.put("cityId",cityCode);
        map.put("area",area);
        map.put("areaId",areaCode);
        map.put("brandName",etBrandName.getText().toString());
        map.put("businessLicense",yyzzUrl);
        map.put("trademarkCertificate",scghUrl);
        map.put("name",etCpyName.getText().toString());
        map.put("packageType",typePos);
        map.put("personMobile",etPhone.getText().toString());
        map.put("personName",etCharge.getText().toString());
        map.put("personWx",etWeixin.getText().toString());
        map.put("trademarkNo",etTrademark.getText().toString());
       presenter.applySettledIn(map);
   }


    @Override
    public void selectPhoto(int position) {
        SelectImageUtils.selectPhoto(this, getString(R.string.takephoto), false, true, 1);
    }

    @Override
    public void onUploadSuccess(String path, int position) {
        LogAndToastUtil.log("==============="+position);
        this.authenticationPos = position;
        presenter.upload(path);
    }

    @Override
    public void showUpload(MdlBaseHttpResp<MdlUpload> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && resp.getData() != null) {
            MdlUpload.DataBean bean = resp.getData().getData();
            if(authenticationPos == 0){
                yyzzUrl = bean.getUrl();
                imgYyzz.setScaleType(ImageView.ScaleType.FIT_XY);
                GlidUtils.setGrid(this,yyzzUrl,imgYyzz);
            }else {
                scghUrl = bean.getUrl();
                imgScgh.setScaleType(ImageView.ScaleType.FIT_XY);
                GlidUtils.setGrid(this,scghUrl,imgScgh);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        presenter.onRequestPermissionsResult(this, requestCode, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(this,requestCode,resultCode,data);
        if(resultCode == RESULT_OK){
            if(requestCode == 7){
                province = data.getStringExtra("province");
                provinceCode = data.getStringExtra("provinceId");
                city = data.getStringExtra("city");
                cityCode = data.getStringExtra("cityId");
                area = data.getStringExtra("area");
                areaCode = data.getStringExtra("areaId");
                tvLocation.setText(province+" "+city+" "+area);
            }
        }
    }

    @Override
    public void getMessageSuccess(MdlBaseHttpResp<DataBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            LogAndToastUtil.toast("申请成功");
            finish();
        }
    }
}

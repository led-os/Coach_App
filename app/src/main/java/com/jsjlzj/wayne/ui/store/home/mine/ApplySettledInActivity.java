package com.jsjlzj.wayne.ui.store.home.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.DataBean;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.ui.publicac.AddressActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;

import java.util.HashMap;
import java.util.Map;

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
        llLocation.setOnClickListener(clickListener);
        llType.setOnClickListener(clickListener);
        tvCommit.setOnClickListener(clickListener);
    }


    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()){
            case R.id.ll_location:
                AddressActivity.go2this(this);
                break;
            case R.id.ll_type:
                break;
            case R.id.tv_type:
                applySettledIn();
                break;
            default:break;
        }
    }

    private void applySettledIn(){
//       {
//           "address": "地址",
//               "area": "区",
//               "areaId": "区ID",
//               "brandName": "品牌名称",
//               "businessLicense": "营业执照",
//               "city": "市",
//               "cityId": "市ID",
//               "name": "名称",
//               "packageType": "套餐类型(1-4)",
//               "personMobile": "负责人电话",
//               "personName": "负责人",
//               "personWx": "负责人微信",
//               "province": "省",
//               "provinceId": "省ID",
//               "trademarkCertificate": "商标证书",
//               "trademarkNo": "商标号"
//       }
       Map<Object,Object> map = new HashMap<>();
//        map.put("address",et)
       presenter.applyCashout(map);
   }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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

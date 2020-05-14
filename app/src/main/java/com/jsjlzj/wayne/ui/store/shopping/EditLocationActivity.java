package com.jsjlzj.wayne.ui.store.shopping;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.DataBean;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.shopping.LocationListBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.ui.publicac.AddressActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
* @description 编辑地址页面
* @date: 2020/05/12
* @author: 曾海强
*/
public class EditLocationActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.et_location_detail)
    EditText etLocationDetail;
    @BindView(R.id.img_default)
    ImageView imgDefault;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.tv_delete)
    TextView tvDelete;

    private boolean isOpen;
    private LocationListBean.DataBean bean;
    private Map<Object,Object> map = new HashMap<>();
    private String province,provinceCode,city,cityCode,area,areaCode;


    public static void go2this(Activity activity, int requestCode, LocationListBean.DataBean bean){
        activity.startActivityForResult(new Intent(activity,EditLocationActivity.class).putExtra("location",bean),requestCode);

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_edit_location;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initTitle("编辑地址");
        bean = (LocationListBean.DataBean) getIntent().getSerializableExtra("location");
        if(bean != null){
            etName.setText(bean.getUserName());
            etPhone.setText(bean.getPhone());
            tvLocation.setText(bean.getProvince()+" "+bean.getCity()+" "+bean.getDistrict());
            etLocationDetail.setText(bean.getDetail());
            if(bean.getIsDefault() == 1){
                imgDefault.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.component_on));
            }else {
                imgDefault.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.component_off));
            }
            tvDelete.setVisibility(View.VISIBLE);
        }else {
            tvDelete.setVisibility(View.GONE);
        }
        tvLocation.setOnClickListener(clickListener);
        tvSave.setOnClickListener(clickListener);
        imgDefault.setOnClickListener(clickListener);
        tvDelete.setOnClickListener(clickListener);
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()){
            case R.id.tv_location:
                AddressActivity.go2this(this);
                break;
            case R.id.tv_save:

                saveLocation();
                break;
            case R.id.img_default:
                if(isOpen){
                    isOpen = false;
                    imgDefault.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.component_off));
                }else {
                    isOpen = true;
                    imgDefault.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.component_on));
                }
                break;
            case R.id.tv_delete:
                break;
            default:
                break;
        }
    }

    /**
     * 保存地址或修改地址
     */
    private void saveLocation() {
        if(TextUtils.isEmpty(etName.getText().toString())){
            LogAndToastUtil.toast("请输入联系人姓名");
            return;
        }
        if(TextUtils.isEmpty(etPhone.getText().toString())){
            LogAndToastUtil.toast("请输入手机号");
            return;
        }
        if(TextUtils.isEmpty(tvLocation.getText().toString())){
            LogAndToastUtil.toast("请选择所在地区");
            return;
        }
        if(TextUtils.isEmpty(etLocationDetail.getText().toString())){
            LogAndToastUtil.toast("请输入详细地址");
            return;
        }
        map.clear();
        map.put("isDefault",isOpen ? 1 : 0 );
        map.put("city",city);
        map.put("cityCode",cityCode);
        map.put("detail",etLocationDetail.getText().toString());
        map.put("district",area);
        map.put("districtCode",areaCode);
        map.put("phone",etPhone.getText().toString());
        map.put("province",province);
        map.put("provinceCode",provinceCode);
        map.put("userName",etName.getText().toString());
        presenter.saveLocation(map);
    }

    @Override
    public void saveLocationSuccess(MdlBaseHttpResp<DataBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            LogAndToastUtil.toast("保存成功");
            setResult(RESULT_OK);
            finish();
        }
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
}

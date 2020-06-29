package com.jsjlzj.wayne.ui.store.home.mine;

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
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.ui.publicac.AddressActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * @ClassName: ApplyLeaderActivity
 * @Description: 申请成为团长
 * @Author: 曾海强
 * @CreateDate: 2020/05/13
 */
public class ApplyLeaderActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    public static final int REQUEST_CODE_APPLY = 10002;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_select_area)
    TextView tvSelectArea;
    @BindView(R.id.img_select_area)
    ImageView imgSelectArea;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_wx)
    EditText etWx;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    private String province, provinceCode, city, cityCode, area, areaCode;
    private Map<Object,Object> map = new HashMap<>();

    public static void go2this(Activity activity,int requestCode) {
        activity.startActivityForResult(new Intent(activity, ApplyLeaderActivity.class),requestCode);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_apply_leader;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initTitle("成为团长");
        imgSelectArea.setOnClickListener(clickListener);
        tvSelectArea.setOnClickListener(clickListener);
        tvCommit.setOnClickListener(clickListener);
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.img_select_area:
            case R.id.tv_select_area:
                AddressActivity.go2this(this);
                break;
            case R.id.tv_commit:
                commitApply();
                break;
            default:
                break;
        }
    }

    private void commitApply() {
        if(TextUtils.isEmpty(etName.getText().toString())){
            LogAndToastUtil.toast("请输入真实姓名");
            return;
        }
        if(TextUtils.isEmpty(etCode.getText().toString())){
            LogAndToastUtil.toast("请输入真实身份证号");
            return;
        }
        if(TextUtils.isEmpty(province)){
            LogAndToastUtil.toast("请选择所在地区");
            return;
        }
        if(TextUtils.isEmpty(etPhone.getText().toString())){
            LogAndToastUtil.toast("请输入手机号");
            return;
        }
        if(TextUtils.isEmpty(etWx.getText().toString())){
            LogAndToastUtil.toast("请输入真实微信号");
            return;
        }
        map.clear();
        map.put("province",province);
        map.put("provinceId",provinceCode);
        map.put("city",city);
        map.put("cityId",cityCode);
        map.put("area",area);
        map.put("areaId",areaCode);
        map.put("idNo",etCode.getText().toString());
        map.put("idUrl","");
        map.put("mobile",etPhone.getText().toString());
        map.put("realName",etName.getText().toString());
        map.put("wx",etWx.getText().toString());
        presenter.applyLeader(map);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 7) {
                province = data.getStringExtra("province");
                provinceCode = data.getStringExtra("provinceId");
                city = data.getStringExtra("city");
                cityCode = data.getStringExtra("cityId");
                area = data.getStringExtra("area");
                areaCode = data.getStringExtra("areaId");
                tvSelectArea.setText(province + " " + city + " " + area);
                tvSelectArea.setTextColor(ContextCompat.getColor(this,R.color.color_333333));
            }
        }
    }


    @Override
    public void getMessageSuccess(MdlBaseHttpResp<DataBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            LogAndToastUtil.toast("申请成功");
            setResult(RESULT_OK);
            finish();
        }
    }
}

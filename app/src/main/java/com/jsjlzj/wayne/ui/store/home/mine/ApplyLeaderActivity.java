package com.jsjlzj.wayne.ui.store.home.mine;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.ui.publicac.AddressActivity;

import butterknife.BindView;

/**
 * @ClassName: ApplyLeaderActivity
 * @Description: 申请成为团长
 * @Author: 曾海强
 * @CreateDate: 2020/05/13
 */
public class ApplyLeaderActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

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
    private String province,provinceCode,city,cityCode,area,areaCode;
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
        switch (view.getId()){
            case R.id.img_select_area:
            case R.id.tv_select_area:
                AddressActivity.go2this(this);
                break;
            case R.id.tv_commit:
                break;
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
                tvSelectArea.setText(province+" "+city+" "+area);
            }
        }
    }
}

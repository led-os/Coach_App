package com.jsjlzj.wayne.ui.store.talent.position.recruit;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.MdlStoreInfo;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.ui.store.talent.position.recruit.coordinate.MapActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.Utility;

import java.util.HashMap;
import java.util.Map;

import static com.jsjlzj.wayne.ui.store.talent.position.RecruitActivity.FLAG_RECRUIT_ADDRESS;

/**
 * 招聘  职位描述
 */
public class RecruitCoordinateSelectActivity extends MVPBaseActivity<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView {
    private String area;
    private String address;
    private String province;
    private String city;
    private String coordinate;
    private String location;
    private String homeCode;

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, RecruitCoordinateSelectActivity.class);
        intent.putExtra("type", 1);
        context.startActivityForResult(intent,FLAG_RECRUIT_ADDRESS);
    }

    public static void go2this2(Activity context,String location,String homeCode) {
        Intent intent = new Intent(context, RecruitCoordinateSelectActivity.class);
        intent.putExtra("type", 2);
        intent.putExtra("location",location);
        intent.putExtra("homeCode",homeCode);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_recruit_coordinate_select;
    }


    private TextView tvRecruitCoordinate, edRecruitCoordinateContent;
    private EditText edDoorNumber;
    private int type;

    @Override
    protected void initViewAndControl() {
        type = getIntent().getIntExtra("type", 1);
        tvRecruitCoordinate = findView(R.id.tvRecruitCoordinate);
        edRecruitCoordinateContent = findView(R.id.edRecruitCoordinateContent);
        edDoorNumber = findView(R.id.edDoorNumber);
        location = getIntent().getStringExtra("location");
        homeCode = getIntent().getStringExtra("homeCode");
        tvRecruitCoordinate.setText(location);
        edDoorNumber.setText(homeCode);
        edDoorNumber.setSelection(homeCode.length());
        postponeEnterTransition();
        tvRecruitCoordinate.setOnClickListener(clickListener);
        findView(R.id.btnBack).setOnClickListener(clickListener);
        findView(R.id.btnKeep).setOnClickListener(clickListener);

    }

    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
    }

    Map<Object, Object> map = null;
    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.tvRecruitCoordinate://选择位置
                    MapActivity.go2this(RecruitCoordinateSelectActivity.this);
                    break;
                case R.id.btnKeep://保存
//                    String edRecruitCoordinateContentStr = Utility.getEditTextStr(edRecruitCoordinateContent);
                    String edDoorNumberStr = Utility.getEditTextStr(edDoorNumber);
//                    if (TextUtils.isEmpty(edRecruitCoordinateContentStr)){
//                        LogAndToastUtil.toast("俱乐部地址描述不能为空！");
//                        return;
//                    }
                    if (TextUtils.isEmpty(coordinate) || TextUtils.isEmpty(address)) {
                        LogAndToastUtil.toast("俱乐部地址不能为空！");
                        return;
                    }
                    if (TextUtils.isEmpty(edDoorNumberStr)) {
                        LogAndToastUtil.toast("地址不能为空！");
                        return;
                    }
                    switch (type) {
                        case 1:
                            Intent intent=new Intent();
                            intent.putExtra("area",area);
                            intent.putExtra("city",city);
                            intent.putExtra("coordinate",coordinate);
                            intent.putExtra("province",province);
                            intent.putExtra("storeAddress",address);
                            intent.putExtra("storeDoorplate",edDoorNumberStr);
                            setResult(RESULT_OK,intent);
                            finish();
                            break;
                        case 2:
                            if (null == map) map = new HashMap<>();
                            map.put("area", area);
                            map.put("city", city);
                            map.put("coordinate", coordinate);
                            map.put("province", province);
                            map.put("storeAddress", address);
                            map.put("storeDoorplate", edDoorNumberStr);
                            presenter.saveStoreAddress(map);
                            break;
                    }
                    break;
                case R.id.btnBack://返回
                    finish();
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogAndToastUtil.log("====requestCode"+requestCode+resultCode+"==="+data);
        if (resultCode == RESULT_OK  && requestCode == 1) {
            area = data.getStringExtra("area");
            city = data.getStringExtra("city");
            coordinate = data.getStringExtra("coordinate");
            province = data.getStringExtra("province");
            address = data.getStringExtra("address");

            tvRecruitCoordinate.setText(address);
        }
    }

    public void showSaveStoreAddress(MdlBaseHttpResp<MdlStoreInfo> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            LogAndToastUtil.toast("保存成功！");
            finish();
        } else {
            LogAndToastUtil.toast(resp.getMsg());
        }
    }
}
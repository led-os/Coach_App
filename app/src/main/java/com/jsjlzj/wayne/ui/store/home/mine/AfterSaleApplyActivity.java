package com.jsjlzj.wayne.ui.store.home.mine;


import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.ImageSelectAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.DataBean;
import com.jsjlzj.wayne.entity.Login.MdlUpload;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.shopping.MineOrderPageBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.DateUtil;
import com.jsjlzj.wayne.utils.GlidUtils;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SelectImageUtils;
import com.jsjlzj.wayne.widgets.dialog.EducationDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @description 申请售后
 * @date: 2020/05/27
 * @author: 曾海强
 */
public class AfterSaleApplyActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    public static final int IMG_SIZE = 3;

    @BindView(R.id.img_pic)
    ImageView imgPic;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_attribute)
    TextView tvAttribute;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.ll_state)
    LinearLayout llState;
    @BindView(R.id.ll_reason)
    LinearLayout llReason;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_reason)
    TextView tvReason;
    @BindView(R.id.ll_money)
    LinearLayout llMoney;
    @BindView(R.id.et_desc)
    EditText etDesc;
    @BindView(R.id.rv_authentication_img)
    RecyclerView rvAuthenticationImg;
    @BindView(R.id.tv_commit)
    TextView tvCommit;

    private MineOrderPageBean.DataBean.ResultBean.ListBean bean;
    private List<String> authenticationUrls = new ArrayList<>();
    private String[] reasonStrs = new String[5];
    private ImageSelectAdapter authentictionAdapter;
    private int authenticationPos;
    private float returnMoney;

    public static void go2this(Context activity, MineOrderPageBean.DataBean.ResultBean.ListBean productId) {
        activity.startActivity(new Intent(activity, AfterSaleApplyActivity.class).putExtra("product_data", productId));
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_after_sale_apply;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initTitle("申请退款");
        bean = (MineOrderPageBean.DataBean.ResultBean.ListBean) getIntent().getSerializableExtra("product_data");
        returnMoney = bean.getProductCount() * bean.getProductPrice() - bean.getTotalDiscountAmount();
        tvMoney.setText(getResources().getString(R.string.chinese_money) + DateUtil.getTwoDotByFloat(returnMoney));
        GlidUtils.setGrid(this,bean.getProductPic(),imgPic);
        tvName.setText(bean.getName());
        JSONArray array = JSON.parseArray(bean.getProductSpec());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < array.size(); i++) {
            JSONObject jsonObject = JSONObject.parseObject(array.get(i).toString());
            String value = jsonObject.getString("value");
            stringBuilder.append(value);
            stringBuilder.append(" ");
        }
        tvAttribute.setText(stringBuilder.toString());
        tvNum.setText("x"+bean.getProductCount());
        llReason.setOnClickListener(clickListener);
        tvCommit.setOnClickListener(clickListener);
        reasonStrs = getResources().getStringArray(R.array.reason_type_list);
        initRecycler();
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.ll_reason://退货原因
                showReasonSelect();
                break;
            case R.id.tv_commit://提交
                applyAfterSale();
                break;
            default:
                break;
        }
    }

    private void initRecycler() {
        rvAuthenticationImg.setLayoutManager(new GridLayoutManager(this, IMG_SIZE));
        authenticationUrls = new ArrayList<>();
        // 默认的一张空白占位图
        authenticationUrls.add("");
        authentictionAdapter = new ImageSelectAdapter(this, authenticationUrls);
        authentictionAdapter.setListener(new ImageSelectAdapter.OnImageClickListener() {
            @Override
            public void onImageClick(int position) {
                presenter.autoObtainStoragePermission(AfterSaleApplyActivity.this, position);
            }

            @Override
            public void onRemoveImgClick(int position) {
                // 删除图片
                authenticationUrls.remove(position);
                if (authenticationUrls.size() < IMG_SIZE) {
                    // 如果最后一张已经是空白图的话不操作，否则添加一张空白图
                    if (!authenticationUrls.get(authenticationUrls.size() - 1).equals("")) {
                        authenticationUrls.add("");
                    }
                }
                authentictionAdapter.notifyDataSetChanged();
            }
        });
        rvAuthenticationImg.setAdapter(authentictionAdapter);
    }
    //选择退款原因
    private void showReasonSelect() {
        new EducationDialog(this, new EducationDialog.ClickListener() {
            @Override
            public void clickConfirm(String data, int position) {
                tvReason.setText(data);
            }
        }, reasonStrs).show();
    }

    private void applyAfterSale() {
        if(TextUtils.isEmpty(tvReason.getText().toString())){
            LogAndToastUtil.toast("请选择退款原因");
            return;
        }
        StringBuilder submitList = new StringBuilder();
        for (int i = 0; i < authenticationUrls.size(); i++) {
            if (!TextUtils.isEmpty(authenticationUrls.get(i))) {
                submitList.append(authenticationUrls.get(i) + ",");
            }
        }
        if (submitList.length() == 0) {
            LogAndToastUtil.toast("请上传图片");
            return;
        }
        Map<Object, Object> map = new HashMap<>();

        map.put("aftersaleReason", tvReason.getText().toString());
        map.put("orderCode", bean.getOrderCode());
        map.put("orderProductId", bean.getOrderProductId());
        map.put("pic", submitList.toString().substring(0, submitList.toString().length() - 1));
        map.put("refundAmount", returnMoney);
        if (!TextUtils.isEmpty(etDesc.getText().toString())) {
            map.put("remark", etDesc.getText().toString());
        }
        presenter.saveAfterSale(map);
    }


    @Override
    public void selectPhoto(int position) {
        SelectImageUtils.selectPhoto(this, getString(R.string.takephoto), false, true, 1);
    }

    @Override
    public void onUploadSuccess(String path, int position) {
        this.authenticationPos = position;
        presenter.upload(path);
    }


    @Override
    public void showUpload(MdlBaseHttpResp<MdlUpload> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && resp.getData() != null) {
            MdlUpload.DataBean bean = resp.getData().getData();
            if (authenticationUrls.size() < authenticationPos) {
                authenticationUrls.add(bean.getUrl());
                // 替换图片
            } else {
                authenticationUrls.set(authenticationPos, bean.getUrl());
            }
            if (authenticationUrls.size() < IMG_SIZE) {
                // 如果最后一张已经是空白图的话不操作，否则添加一张空白图
                if (!authenticationUrls.get(authenticationUrls.size() - 1).equals("")) {
                    authenticationUrls.add("");
                }
            }
            authentictionAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        presenter.onRequestPermissionsResult(this, requestCode, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(this, requestCode, resultCode, data);
    }


    @Override
    public void getMessageSuccess(MdlBaseHttpResp<DataBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            LogAndToastUtil.toast("提交成功");
            finish();
        }
    }
}

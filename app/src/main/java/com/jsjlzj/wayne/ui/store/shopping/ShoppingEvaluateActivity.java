package com.jsjlzj.wayne.ui.store.shopping;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.jsjlzj.wayne.utils.GlidUtils;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SelectImageUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: ShoppingEvaluateActivity
 * @Description: 商品评价页面
 * @Author: 曾海强
 * @CreateDate:
 */
public class ShoppingEvaluateActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    public static final int IMG_SIZE = 3;
    @BindView(R.id.img_pic)
    ImageView imgPic;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_attribute)
    TextView tvAttribute;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.ll_good)
    LinearLayout llGood;
    @BindView(R.id.ll_midpin)
    LinearLayout llMidpin;
    @BindView(R.id.ll_lose)
    LinearLayout llLose;
    @BindView(R.id.et_evaluate)
    EditText etEvaluate;
    @BindView(R.id.rv_authentication_img)
    RecyclerView rvAuthenticationImg;
    @BindView(R.id.img_1)
    ImageView img1;
    @BindView(R.id.img_2)
    ImageView img2;
    @BindView(R.id.img_3)
    ImageView img3;
    @BindView(R.id.img_4)
    ImageView img4;
    @BindView(R.id.img_5)
    ImageView img5;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.img_good)
    ImageView imgGood;
    @BindView(R.id.tv_good)
    TextView tvGood;
    @BindView(R.id.img_midpiin)
    ImageView imgMidpiin;
    @BindView(R.id.tv_midpin)
    TextView tvMidpin;
    @BindView(R.id.img_lose)
    ImageView imgLose;
    @BindView(R.id.tv_lose)
    TextView tvLose;

    private MineOrderPageBean.DataBean.ResultBean.ListBean bean;
    private List<String> authenticationUrls;
    private ImageSelectAdapter authentictionAdapter;
    private int authenticationPos;
    private Map<Object, Object> map = new HashMap<>();
    /**
     * 0:好评 1:中 2:差",
     */
    private int levelType;
    private int star = 1;

    public static void go2this(Context activity, MineOrderPageBean.DataBean.ResultBean.ListBean productId) {
        activity.startActivity(new Intent(activity, ShoppingEvaluateActivity.class).putExtra("product_data", productId));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_shopping_evaluate;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initTitle("评价");
        bean = (MineOrderPageBean.DataBean.ResultBean.ListBean) getIntent().getSerializableExtra("product_data");
        JSONArray array = JSON.parseArray(bean.getProductSpec());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < array.size(); i++) {
            JSONObject jsonObject = JSONObject.parseObject(array.get(i).toString());
            String value = jsonObject.getString("value");
            stringBuilder.append(value);
            stringBuilder.append(" ");
        }
        tvAttribute.setText(stringBuilder.toString());
        tvName.setText(bean.getName());
        GlidUtils.setGrid(this, bean.getProductPic(), imgPic);
        tvNum.setText("x" + bean.getProductCount());
        llGood.setOnClickListener(clickListener);
        llMidpin.setOnClickListener(clickListener);
        llLose.setOnClickListener(clickListener);
        img1.setOnClickListener(clickListener);
        img2.setOnClickListener(clickListener);
        img3.setOnClickListener(clickListener);
        img4.setOnClickListener(clickListener);
        img5.setOnClickListener(clickListener);
        tvCommit.setOnClickListener(clickListener);

        initRecycler();
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
                presenter.autoObtainStoragePermission(ShoppingEvaluateActivity.this, position);
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


    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.ll_good:
                levelType = 0;
                imgGood.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_good));
                tvGood.setTextColor(ContextCompat.getColor(this,R.color.color_f1404b));
                imgMidpiin.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_midpin));
                tvMidpin.setTextColor(ContextCompat.getColor(this,R.color.color_999999));
                imgLose.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_lose));
                tvLose.setTextColor(ContextCompat.getColor(this,R.color.color_999999));
                break;
            case R.id.ll_midpin:
                imgGood.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_good_noselect));
                tvGood.setTextColor(ContextCompat.getColor(this,R.color.color_999999));
                imgMidpiin.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_midpin_select));
                tvMidpin.setTextColor(ContextCompat.getColor(this,R.color.color_F7B500));
                imgLose.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_lose));
                tvLose.setTextColor(ContextCompat.getColor(this,R.color.color_999999));
                break;
            case R.id.ll_lose:
                imgGood.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_good_noselect));
                tvGood.setTextColor(ContextCompat.getColor(this,R.color.color_999999));
                imgMidpiin.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_midpin));
                tvMidpin.setTextColor(ContextCompat.getColor(this,R.color.color_999999));
                imgLose.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_lose_select));
                tvLose.setTextColor(ContextCompat.getColor(this,R.color.color_777777));
                break;
            case R.id.img_1:
                star = 1;
                img1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_xing));
                img2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_xing_no));
                img3.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_xing_no));
                img4.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_xing_no));
                img5.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_xing_no));
                break;
            case R.id.img_2:
                star = 2;
                img1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_xing));
                img2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_xing));
                img3.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_xing_no));
                img4.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_xing_no));
                img5.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_xing_no));
                break;
            case R.id.img_3:
                star = 3;
                img1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_xing));
                img2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_xing));
                img3.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_xing));
                img4.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_xing_no));
                img5.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_xing_no));
                break;
            case R.id.img_4:
                star = 4;
                img1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_xing));
                img2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_xing));
                img3.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_xing));
                img4.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_xing));
                img5.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_xing_no));
                break;
            case R.id.img_5:
                star = 5;
                img1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_xing));
                img2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_xing));
                img3.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_xing));
                img4.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_xing));
                img5.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_evaluate_xing));
                break;
            case R.id.tv_commit:
                commitEvaluate();
                break;
        }
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


    private void commitEvaluate() {
        if (TextUtils.isEmpty(etEvaluate.getText())) {
            LogAndToastUtil.toast("请填写评论内容");
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
        map.clear();
        map.put("content", etEvaluate.getText().toString());
        map.put("levelType", levelType);
        map.put("star", star);
        map.put("productId", bean.getSkuId());
        map.put("orderCode",bean.getOrderCode());
        map.put("pics", submitList.toString().substring(0, submitList.toString().length() - 1));
        presenter.commitEvaluateOrder(map);
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

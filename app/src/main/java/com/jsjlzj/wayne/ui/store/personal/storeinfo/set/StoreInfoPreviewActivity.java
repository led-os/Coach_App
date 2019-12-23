package com.jsjlzj.wayne.ui.store.personal.storeinfo.set;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.google.android.flexbox.FlexboxLayout;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.BaseAdapterHelper;
import com.jsjlzj.wayne.adapter.recycler.MyRecyclerAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.ItemsBean;
import com.jsjlzj.wayne.entity.store.MdlStoreInfo;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.ui.store.personal.storeinfo.ImageViewActivity;
import com.jsjlzj.wayne.ui.store.talent.position.RecruitActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.dialog.ReportDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 预览
 */
public class StoreInfoPreviewActivity extends MVPBaseActivity<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView {
    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
    }

    public static void go2this(Activity context, String id) {
        Intent intent = new Intent(context, StoreInfoPreviewActivity.class);
        intent.putExtra("id", id);

        context.startActivityForResult(intent, RecruitActivity.FLAG_RECRUIT_CONTENT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_store_info_preview;
    }

    private RecyclerView recyclerViewPics;
    private MyRecyclerAdapter<MdlStoreInfo.DataBean.CompanyImagesBean> adapter;
    private ImageView image, btnShoucang, btnJubao;
    private TextView tvStoreContent, tvRestTime, tvOverTime, tvSkillTitle, tvClubName, tvTime, tvUsName, tvCapital, store_content, tvStoreName, tvNumber, tvWorkTime, store_ivPics, addressTv;
    private FlexboxLayout flexbox;
    private MapView mapView;
    private BaiduMap mBaiduMap;
    private Map<Object, Object> map;
    private boolean isLikeFlag = false;
    private MdlStoreInfo.DataBean bean;
    private List<ItemsBean> list;
    private List<String> listPics;

    @Override
    protected void initViewAndControl() {
        String id = getIntent().getStringExtra("id");
        list = new ArrayList<>();
        listPics = new ArrayList<>();
        tvStoreContent = findView(R.id.tvStoreContent);
        tvStoreName = findView(R.id.tvStoreName);
        tvNumber = findView(R.id.tvNumber);
        tvWorkTime = findView(R.id.tvWorkTime);
        btnShoucang = findView(R.id.btnShoucang);
        btnJubao = findView(R.id.btnJubao);
        tvRestTime = findView(R.id.tvRestTime);
        tvOverTime = findView(R.id.tvOverTime);
        tvSkillTitle = findView(R.id.tvSkillTitle);
        mapView = findView(R.id.mapView);
        image = findView(R.id.image);
        tvClubName = findView(R.id.tvClubName);
        tvUsName = findView(R.id.tvUsName);
        tvTime = findView(R.id.tvTime);
        tvCapital = findView(R.id.tvCapital);
        addressTv = findView(R.id.addressTv);
        store_content = findView(R.id.store_content);
        store_ivPics = findView(R.id.store_ivPics);
        flexbox = findView(R.id.flexbox);
        recyclerViewPics = findView(R.id.recyclerViewPics);
        mBaiduMap = mapView.getMap();
        mBaiduMap.getUiSettings().setAllGesturesEnabled(false);
        mapView.showZoomControls(false);
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (null != bean) {
                    NavigationActivity.go2this(StoreInfoPreviewActivity.this, bean.getStoreAddress(), bean.getCoordinate());
                } else {
                    NavigationActivity.go2this(StoreInfoPreviewActivity.this, "", "");
                }
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });
        btnShoucang.setOnClickListener(clickListener);
        addressTv.setOnClickListener(clickListener);
        btnJubao.setOnClickListener(clickListener);
        findView(R.id.btnBack).setOnClickListener(clickListener);
        initAdapter();
        if (null == map) map = new HashMap<>();
        if (TextUtils.isEmpty(id)) {
            btnShoucang.setVisibility(View.GONE);
            btnJubao.setVisibility(View.GONE);
            presenter.getStoreInfo(null);
        } else {
            btnShoucang.setVisibility(View.VISIBLE);
            btnJubao.setVisibility(View.VISIBLE);
            map.put("id", id);
            presenter.getStoreDetail(map);
        }
        if (MyApp.mdlDict != null && MyApp.mdlDict.getPosition_tipoff() != null) {
            list.addAll(MyApp.mdlDict.getPosition_tipoff().getItems());
        }
    }

    private void initAdapter() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPics.setLayoutManager(manager);
        adapter = new MyRecyclerAdapter<MdlStoreInfo.DataBean.CompanyImagesBean>(this, R.layout.item_pics) {
            @Override
            public int getItemCount() {
                return super.getItemCount();
            }

            @Override
            public void onUpdate(BaseAdapterHelper helper, MdlStoreInfo.DataBean.CompanyImagesBean item, int position) {
                if (item != null)
                    setImg(item.getThumbnail(), helper.getView(R.id.iv_pic));
                helper.setOnClickListener(R.id.iv_pic, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ImageViewActivity.go2this(StoreInfoPreviewActivity.this, position, listPics);

                    }
                });


            }
        };
        recyclerViewPics.setAdapter(adapter);

    }

    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnBack://返回
                    finish();
                    break;
                case R.id.btnShoucang:
                    if (isLikeFlag) {
                        presenter.cancelStoreLike(map);
                    } else {
                        presenter.saveStoreInfoLike(map);
                    }
                    break;
                case R.id.addressTv:
                    if (null != bean) {
                        NavigationActivity.go2this(StoreInfoPreviewActivity.this, bean.getStoreAddress(), bean.getCoordinate());
                    } else {
                        NavigationActivity.go2this(StoreInfoPreviewActivity.this, "", "");
                    }
                    break;
                case R.id.btnJubao:
                    ReportDialog dialog = new ReportDialog(StoreInfoPreviewActivity.this, new ReportDialog.ClickListener() {
                        @Override
                        public void clickConfirm(String code) {
                            LogAndToastUtil.toast("举报成功");
                        }
                    }, list);
                    dialog.show();
                    break;
            }
        }
    }

    public void showStoreInfo(MdlBaseHttpResp<MdlStoreInfo> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            showUi(resp);
        } else {
            LogAndToastUtil.toast(resp.getMsg());
        }
    }

    @Override
    public void showResultStoreDetail(MdlBaseHttpResp<MdlStoreInfo> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            showUi(resp);
        } else {
            LogAndToastUtil.toast(resp.getMsg());
        }
    }

    public void showUi(MdlBaseHttpResp<MdlStoreInfo> resp) {
        bean = resp.getData().getData();
        if (null != bean) {
            setImg(bean.getBrandLogo(), image);
            if (TextUtils.isEmpty(bean.getCompanyProfile())) {
                tvStoreContent.setVisibility(View.GONE);
                store_content.setVisibility(View.GONE);
            } else {
                tvStoreContent.setVisibility(View.VISIBLE);
                store_content.setVisibility(View.VISIBLE);
                tvStoreContent.setText(bean.getCompanyProfile());

            }
            if (bean.isLike()) {
                isLikeFlag = true;
                btnShoucang.setImageDrawable(getResources().getDrawable(R.drawable.collected));
            } else {
                btnShoucang.setImageDrawable(getResources().getDrawable(R.drawable.ic_dianzang));
            }
            tvStoreName.setText(TextUtils.isEmpty(bean.getStoreName()) ? "" : bean.getStoreName());
            tvNumber.setText(TextUtils.isEmpty(bean.getStaffNum()) ? "" : bean.getStaffNum());
            tvWorkTime.setText(bean.getWorkTimeStart() + "-" + bean.getWorkTimeEnd());
            tvRestTime.setText(TextUtils.isEmpty(bean.getRestTime()) ? "" : bean.getRestTime());
            tvOverTime.setText(TextUtils.isEmpty(bean.getWorkOvertime()) ? "" : bean.getWorkOvertime());
            tvClubName.setText(TextUtils.isEmpty(bean.getCompanyName()) ? "暂无" : bean.getCompanyName());
            tvUsName.setText(TextUtils.isEmpty(bean.getCompanyLegalPerson()) ? "暂无" : bean.getCompanyLegalPerson());
            tvTime.setText(TextUtils.isEmpty(bean.getRegisteredDate()) ? "暂无" : bean.getRegisteredDate());
            tvCapital.setText(TextUtils.isEmpty(bean.getRegisteredCapital()) ? "暂无" : bean.getRegisteredCapital());
            flexbox.removeAllViews();
            if (bean.getCompanyBenefits() != null && bean.getCompanyBenefits().size() > 0) {
                tvSkillTitle.setVisibility(View.VISIBLE);
                flexbox.setVisibility(View.VISIBLE);
                for (int i = 0; i < bean.getCompanyBenefits().size(); i++) {
                    TextView tv = new TextView(this);
                    FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.setMargins(0, 0, 30, 30);
                    tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.dr_bg_tv_g));
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
                    tv.setPadding(30, 14, 30, 14);
                    tv.setTextColor(getResources().getColor(R.color.white));
                    tv.setText(bean.getCompanyBenefits().get(i));
                    tv.setLayoutParams(params);
                    flexbox.addView(tv);
                }
            } else {
                tvSkillTitle.setVisibility(View.GONE);
                flexbox.setVisibility(View.GONE);
            }
            if (bean.getCompanyImages() == null || bean.getCompanyImages().size() == 0) {
                recyclerViewPics.setVisibility(View.GONE);
                store_ivPics.setVisibility(View.GONE);
            } else {
                recyclerViewPics.setVisibility(View.VISIBLE);
                store_ivPics.setVisibility(View.VISIBLE);
                adapter.setData(bean.getCompanyImages());
                adapter.notifyDataSetChanged();
                for (int i = 0; i < bean.getCompanyImages().size(); i++) {

                    listPics.add(bean.getCompanyImages().get(i).getOriginal());
                }
            }
            addressTv.setText(bean.getStoreAddress());
            if (!TextUtils.isEmpty(bean.getCoordinate())) {
                String[] latLngStr = bean.getCoordinate().split(",");
                if (latLngStr != null&&latLngStr.length==2) {
                    LatLng latLng = new LatLng(Double.valueOf(latLngStr[1]), Double.valueOf(latLngStr[0]));
                    MapStatus mapStatus = new MapStatus.Builder()
                            .target(latLng)
                            .zoom(18)
                            .build();
                    MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
                    mBaiduMap.setMapStatus(mapStatusUpdate);
                    BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.ic_address_pointer);
                    MarkerOptions markerOptions = new MarkerOptions().icon(bitmapDescriptor).position(latLng);
                    mBaiduMap.addOverlay(markerOptions);
                }
            }
        }
    }

    public void showStorePositionList(MdlBaseHttpResp resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData()) {
            LogAndToastUtil.toast("收藏成功");
            isLikeFlag = true;
            btnShoucang.setImageDrawable(getResources().getDrawable(R.drawable.collected));
        } else {
            LogAndToastUtil.toast(resp.getMsg());
        }
    }

    public void showCancelPositionStoreList(MdlBaseHttpResp resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData()) {
            isLikeFlag = false;
            LogAndToastUtil.toast("取消成功");
            btnShoucang.setImageDrawable(getResources().getDrawable(R.drawable.ic_dianzang));
        } else {
            LogAndToastUtil.toast(resp.getMsg());
        }
    }

}
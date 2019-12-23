package com.jsjlzj.wayne.ui.store.talent.position.recruit;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.BaseAdapterHelper;
import com.jsjlzj.wayne.adapter.recycler.MyRecyclerAdapter;
import com.jsjlzj.wayne.adapter.recycler.WrapContentLinearLayoutManager;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.ui.publicac.AddressActivity;
import com.jsjlzj.wayne.widgets.MyRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MapSearchActivity extends MVPBaseActivity<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView , OnGetGeoCoderResultListener {

    public static void go2this(Activity context, String province, String city, String area,double lat,double lon) {
        Intent intent = new Intent(context, MapSearchActivity.class);
        intent.putExtra("province", province);
        intent.putExtra("city", city);
        intent.putExtra("area", area);
        intent.putExtra("lon", lon);
        intent.putExtra("lat", lat);
        context.startActivityForResult(intent, 1);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_map_search;
    }

    private EditText edAddress;
    private TextView btnAddress,address_delete;
    private MyRecyclerView recyclerView;
    private MyRecyclerAdapter<PoiInfo> sugAdapter;
    private String city;
    private String address;
    private String area;
    private String province;
    private double lat, lon;
    private PoiSearch poiSearch;
    private int pageNo=0;
    private List<PoiInfo> listAll;

    @Override
    protected void initViewAndControl() {
        address_delete=findView(R.id.address_delete);
        address_delete.setOnClickListener(clickListener);
        btnAddress = findView(R.id.btnAddress);
        recyclerView = findView(R.id.address_reclerView);
        btnAddress.setOnClickListener(clickListener);
        edAddress = findView(R.id.edAddress);
        listAll=new ArrayList<>();
        LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        area = getIntent().getStringExtra("area");
        province = getIntent().getStringExtra("province");
        city = getIntent().getStringExtra("city");
        lon = getIntent().getDoubleExtra("lon",0);
        lat = getIntent().getDoubleExtra("lat",0);
        if (!TextUtils.isEmpty(city)) {
            btnAddress.setText(city);
        } else {
            btnAddress.setText("城市");
        }
        poiSearch = PoiSearch.newInstance();
//        presenter.getAddressSearch();
        initRecycler();
    }

    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
    }

    private void initRecycler() {
        edAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(s.toString())){
                    listAll.clear();
                    address_delete.setVisibility(View.GONE);
                }else{
                    poiSearch.searchInCity((new PoiCitySearchOption().city(city).keyword(edAddress.getText().toString())).pageNum(pageNo).pageCapacity(10));
                    address_delete.setVisibility(View.VISIBLE);
                }
            }
        });
        poiSearch.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                if(poiResult.getAllPoi()!=null) {
                    listAll.addAll(poiResult.getAllPoi());
                    sugAdapter.notifyDataSetChanged();
                }else{
                    listAll.clear();
                }
            }

            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

            }

            @Override
            public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
        });
        sugAdapter = new MyRecyclerAdapter<PoiInfo>(this, R.layout.item_questionslist) {
            @Override
            public void onUpdate(BaseAdapterHelper helper, PoiInfo info, int position) {
                //r.add(info.key + " " + info.city + info.district);
                helper.setText(R.id.tvPositionName, "" + info.getCity()+info.getArea()+info.getName());
                helper.setOnClickListener(R.id.tvPositionName, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        city = info.city;
                        area = info.area;
                        province = info.province;
                        address = info.address;
                        LatLng latLng=info.getLocation();
                        lon=latLng.longitude;
                        lat=latLng.latitude;
                        Intent intent=new Intent();
                        intent.putExtra("area",area);
                        intent.putExtra("city",city);
                        intent.putExtra("coordinate",lon+","+lat);
                        intent.putExtra("province",province);
                        intent.putExtra("address",address);
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                });

            }
        };
        sugAdapter.setData(listAll);
        recyclerView.setAdapter(sugAdapter);
        recyclerView.setLoadingMoreEnabled(false);
        recyclerView.setPullRefreshEnabled(false);

    }


    private MyViewClickListener clickListener = new MyViewClickListener();

    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

    }

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.address_delete://删除历史记录
                    break;
                case R.id.btnAddress://选择地址
                    AddressActivity.go2this(MapSearchActivity.this);
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
        if(resultCode==RESULT_OK&&data!=null){
            switch (requestCode){
                case 7:
                    province = data.getStringExtra("province");
                    city = data.getStringExtra("city");
                    area = data.getStringExtra("area");
                    btnAddress.setText(city);
//                    setResult(RESULT_OK);
//                    finish();
                    break;
            }
        }
    }
}

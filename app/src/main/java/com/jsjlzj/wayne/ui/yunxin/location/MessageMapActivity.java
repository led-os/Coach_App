package com.jsjlzj.wayne.ui.yunxin.location;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.WrapContentLinearLayoutManager;
import com.jsjlzj.wayne.constant.MyPermissionConstant;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.utils.permission.PermissionUtil;
import com.netease.nim.uikit.api.model.location.LocationProvider;

import java.util.List;

/**
 * 招聘  职位描述
 */
public class MessageMapActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView, OnGetSuggestionResultListener, OnGetGeoCoderResultListener {
    private static LocationProvider.Callback callback;

    public static void go2this(Context context, LocationProvider.Callback callback) {
        MessageMapActivity.callback= callback;
        Intent intent = new Intent(context, MessageMapActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_message_map;
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }


    private MapView mapView;
    private BaiduMap mBaiduMap;
    private LocationClient mLocationClient;
    private double lat, lon;
    private String addressInfo; // 对应的地址信息
    private GeoCoder geoCoder;

    @Override
    protected void initViewAndControl() {
        findView(R.id.btnBack).setOnClickListener(clickListener);
        findView(R.id.btnOK).setOnClickListener(clickListener);

        LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mapView = findView(R.id.bmapView);
        mBaiduMap = mapView.getMap();
        PermissionUtil.checkPermission(this,
                MyPermissionConstant.LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION);

    }

    @Override
    public void permissionSuccess(int permissionReqCode) {
        super.permissionSuccess(permissionReqCode);
        switch (permissionReqCode) {
            case MyPermissionConstant.LOCATION:
                initMap();
                break;
        }
    }

    private void initMap() {

        mBaiduMap.setMyLocationEnabled(true);
        //透明mark

        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        //卫星地图
        //baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
        //空白地图, 基础地图瓦片将不会被渲染。在地图类型中设置为NONE，将不会使用流量下载基础地图瓦片图层。使用场景：与瓦片图层一起使用，节省流量，提升自定义瓦片图下载速度。
        //baiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);
        //开启交通图
        mBaiduMap.setTrafficEnabled(true);
        //关闭缩放按钮
        mapView.showZoomControls(false);

        MapStatus.Builder builder = new MapStatus.Builder();
        builder.zoom(18.0f);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

        mBaiduMap.setMyLocationConfigeration(
                new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, null));

        //定位初始化
        mLocationClient = new LocationClient(this);
        //注册LocationListener监听器
        mLocationClient.registerLocationListener(new MyLocationListener());
        initLocation();
        //开启地图定位图层
        mLocationClient.start();
        mBaiduMap.setOnMapStatusChangeListener(new MyStatusListener());

    }

    private MyViewClickListener clickListener = new MyViewClickListener();



    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnOK:
                    sendLocation();
                    finish();
                    break;
                case R.id.btnBack://返回
                    finish();
                    break;
            }
        }
    }

    private void sendLocation() {
        Intent intent = new Intent();
        intent.putExtra(LocationExtras.LATITUDE, lat);
        intent.putExtra(LocationExtras.LONGITUDE, lon);
        addressInfo = TextUtils.isEmpty(addressInfo) ? getString(R.string.location_address_unkown) : addressInfo;
        intent.putExtra(LocationExtras.ADDRESS, addressInfo);
        intent.putExtra(LocationExtras.ZOOM_LEVEL,  mBaiduMap.getMapStatus().zoom);
        intent.putExtra(LocationExtras.IMG_URL, getStaticMapUrl());

        if (callback != null) {
            callback.onSuccess(lon, lat, addressInfo);
        }
    }
    private String getStaticMapUrl() {
        StringBuilder urlBuilder = new StringBuilder(LocationExtras.STATIC_MAP_URL_1);
        urlBuilder.append(lat);
        urlBuilder.append(",");
        urlBuilder.append(lon);
        urlBuilder.append(LocationExtras.STATIC_MAP_URL_2);
        return urlBuilder.toString();
    }
    /**
     * 定位初始化设置
     */
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");
        // 设置是否需要地址信息，默认为无地址
        option.setIsNeedAddress(true);
        // 设置是否需要返回位置语义化信息，可以在BDLocation.getLocationDescribe()中得到数据，ex:"在天安门附近"，
        // 可以用作地址信息的补充
        option.setIsNeedLocationDescribe(true);
        // 设置是否需要返回位置POI信息，可以在BDLocation.getPoiList()中得到数据
        option.setIsNeedLocationPoiList(true);
        // /**         * 设置定位模式 Battery_Saving 低功耗模式 Device_Sensors 仅设备(Gps)模式 Hight_Accuracy
        // * 高精度模式         */
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        // 设置是否打开gps进行定位
        option.setOpenGps(true);
        // 设置扫描间隔，单位是毫秒 当<1000(1s)时，定时定位无效
        option.setScanSpan(1000);

        mLocationClient.setLocOption(option);
    }

    private void setMapLoc() {
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(6)
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .latitude(lat)
                .longitude(lon).build();
        // 设置定位数据
        mBaiduMap.setMyLocationData(locData);
    }

    private void setMapLoc(LatLng latLng) {
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(10)
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .latitude(lat)
                .longitude(lon).build();
        // 设置定位数据
        lat = latLng.latitude;
        lon = latLng.longitude;
        addressInfo="";
        mBaiduMap.setMyLocationData(locData);
    }

    private boolean isFrist = true;
    private LatLng locationLatLng;

    public class MyLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mapView == null) {
                return;
            }
            lat = location.getLatitude();
            lon = location.getLongitude();

            // 定位数据
            MyLocationData data = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    .direction(location.getDirection())
                    .latitude(location.getLatitude())
                    .longitude(location.getLongitude())
                    .build();
            mBaiduMap.setMyLocationData(data);
            locationLatLng = new LatLng(location.getLatitude(), location.getLongitude());

            if (isFrist) {
                isFrist = false;
                MapStatusUpdate msu = MapStatusUpdateFactory.newLatLngZoom(locationLatLng, 18.0f);
                mBaiduMap.animateMapStatus(msu);
                setMapLoc();
            }
            addressInfo = location.getAddrStr();

            // 创建GeoCoder实例对象
            geoCoder = GeoCoder.newInstance();
            // 发起反地理编码请求(经纬度->地址信息)
            ReverseGeoCodeOption reverseGeoCodeOption = new ReverseGeoCodeOption();
            // 设置反地理编码位置坐标
            reverseGeoCodeOption.location(new LatLng(location.getLatitude(), location.getLongitude()));
            geoCoder.reverseGeoCode(reverseGeoCodeOption);
            // 设置查询结果监听者
            geoCoder.setOnGetGeoCodeResultListener(MessageMapActivity.this);
        }
    }

    public class MyStatusListener implements BaiduMap.OnMapStatusChangeListener {
        @Override
        public void onMapStatusChangeStart(MapStatus mapStatus) {

        }

        @Override
        public void onMapStatusChangeStart(MapStatus mapStatus, int i) {

        }

        @Override
        public void onMapStatusChange(MapStatus mapStatus) {

        }

        //地图状态改变结束
        public void onMapStatusChangeFinish(MapStatus status) {
            //改变结束之后，获取地图可视范围的中心点坐标
            LatLng latLng = status.target;
            //拿到经纬度之后，就可以反地理编码获取地址信息了
            geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
            geoCoder.setOnGetGeoCodeResultListener(MessageMapActivity.this);
            setMapLoc(latLng);
        }
    }

    @Override
    public void onGetSuggestionResult(SuggestionResult suggestionResult) {
        if (suggestionResult == null || suggestionResult.getAllSuggestions() == null) {
            return;
        }
    }


    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
        addressInfo= reverseGeoCodeResult.getAddress();
        List<PoiInfo> poiInfos = reverseGeoCodeResult.getPoiList();
        if (poiInfos != null && !"".equals(poiInfos)) {
            mLocationClient.stop();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        mBaiduMap.setMyLocationEnabled(false);
        mapView.onDestroy();
        callback = null;
        if (geoCoder != null) {

            geoCoder.destroy();

        }
    }
}
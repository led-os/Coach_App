package com.jsjlzj.wayne.ui.store.personal.storeinfo.set;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.ui.store.talent.position.RecruitActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.dialog.MapDialog;

import java.text.DecimalFormat;
import java.util.List;

public class NavigationActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {
    public static void go2this(Activity context, String address, String coordinate) {
        Intent intent = new Intent(context, NavigationActivity.class);
        intent.putExtra("address", address);
        intent.putExtra("coordinate", coordinate);
        context.startActivityForResult(intent, RecruitActivity.FLAG_RECRUIT_CONTENT);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_navigation;
    }

    private TextView tvDistance, tvAddress;
    private String address, coordinate;
    private MapView mapView;
    private BaiduMap mBaiduMap;
    private String[] latLngStr;
    private double lat,lon;

    @Override
    protected void initViewAndControl() {
        tvAddress = findView(R.id.tvAddress);
        tvDistance = findView(R.id.tvDistance);
        mapView = findView(R.id.mapView);
        findView(R.id.btnConfirm).setOnClickListener(clickListener);
        findView(R.id.btnBack).setOnClickListener(clickListener);
        mBaiduMap = mapView.getMap();
        address = getIntent().getStringExtra("address");
        coordinate = getIntent().getStringExtra("coordinate");
        LogAndToastUtil.log(coordinate+"======address"+address);
        tvAddress.setText(address);
        latLngStr = coordinate.split(",");
        if (latLngStr != null && latLngStr.length == 2) {
            lat=Double.valueOf(latLngStr[1]);
            lon=Double.valueOf(latLngStr[0]);
            LatLng latLng = new LatLng(lat,lon );
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
        initLocation();
    }

    private void initLocation() {
        //定位服务的客户端。宿主程序在客户端声明此类，并调用，目前只支持在主线程中启动
        LocationClient locationClient = new LocationClient(getApplicationContext());
//声明LocationClient类实例并配置定位参数
        LocationClientOption locationOption = new LocationClientOption();
        MyLocationListener myLocationListener = new MyLocationListener();
//注册监听函数
        locationClient.registerLocationListener(myLocationListener);
//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        locationOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
        locationOption.setCoorType("gcj02");
//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        locationOption.setIgnoreKillProcess(true);
//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        locationOption.setIsNeedLocationDescribe(true);
//可选，默认false，设置是否开启Gps定位
        locationOption.setOpenGps(true);
//可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        locationOption.setIsNeedAltitude(false);
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者，该模式下开发者无需再关心定位间隔是多少，定位SDK本身发现位置变化就会及时回调给开发者
        locationOption.setOpenAutoNotifyMode();
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者
        locationOption.setOpenAutoNotifyMode(3000, 1, LocationClientOption.LOC_SENSITIVITY_HIGHT);
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        locationClient.setLocOption(locationOption);
//开始定位
        locationClient.start();
    }

    /**
     * 实现定位回调
     */
    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取经纬度相关（常用）的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

            //获取纬度信息
            double latitude = location.getLatitude();
            //获取经度信息
            double longitude = location.getLongitude();
            LatLng fromLatLng = new LatLng(latitude, longitude);
            if (lat != 0 && lon != 0) {
                LatLng toLatLng = new LatLng(lat, lon);
                String dis = new DecimalFormat("0.00").format(DistanceUtil.getDistance(fromLatLng, toLatLng) / 1000);
                tvDistance.setText("距离当前距离" + dis + "km");
            }
        }
    }

    private MyViewClickListener clickListener = new MyViewClickListener();


    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnBack://返回
                    finish();
                    break;
                case R.id.btnConfirm:
                    MapDialog dialog = new MapDialog(NavigationActivity.this, new MapDialog.ClickListener() {
                        @Override
                        public void clickConfirm(int isMan) {
                            Intent intent;
                            switch (isMan){
                                case 1:
                                    Uri uri = Uri.parse("baidumap://map/direction?destination=latlng:"+lat+","+ lon+"|name:"+address+"&mode=driving");
                                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                                    break;
                                case 2:
                                    uri = Uri.parse("amapuri://route/plan/?dlat="+lat+"&dlon="+lon+"&dname="+address+"&dev=0&t=0");
//                                    String pathUrl = "qqmap://map/routeplan?type=drive&to=" + address + "&tocoord=" + lat + "," + lon + "&policy=2&referer=myapp";
//                                    intent = new Intent();
                                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
//                                    intent.setData(Uri.parse(pathUrl));
                                    break;
                            }
                        }
                    });
                    boolean isBaidu = isInstalled("com.baidu.BaiduMap");
                    boolean isMini = isInstalled("com.autonavi.minimap");
                    if (isBaidu || isMini) {
                        dialog.show();
                        if (!isBaidu) {
                            dialog.setVisible();
                        } else if (!isMini) {
                            dialog.setVisibleBtn2();
                        }
                    }
                    break;
            }
        }
    }

    private boolean isInstalled(String packageName) {
        PackageManager manager = getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> installedPackages = manager.getInstalledPackages(0);
        if (installedPackages != null) {
            for (PackageInfo info : installedPackages) {
                if (info.packageName.equals(packageName))
                    return true;
            }
        }
        return false;
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }
}

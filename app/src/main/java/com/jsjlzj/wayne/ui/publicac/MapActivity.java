package com.jsjlzj.wayne.ui.publicac;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.mine.POILocationAdapter;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.constant.MyPermissionConstant;
import com.jsjlzj.wayne.entity.address.LocationBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.permission.PermissionUtil;
import com.jsjlzj.wayne.widgets.CustomXRecyclerView;
import com.jsjlzj.wayne.widgets.SearchBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author: zenghaiqiang
 * date: 2018/8/30
 * description: 显示地图页面
 */
public class MapActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView,SearchBarView.EditTextCallback, OnGetGeoCoderResultListener, POILocationAdapter.OnItemClickListener, XRecyclerView.LoadingListener {


    public static final int PERMISSION_TITLE = 123465;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.search_bar_title)
    SearchBarView searchBarTitle;
    @BindView(R.id.tv_no_show)
    TextView tvNoShow;
    @BindView(R.id.tv_curr_city)
    TextView tvCurrCity;
    @BindView(R.id.rv_poi_info)
    CustomXRecyclerView rvPoiInfo;


    public static void go2this(Activity context, int requestCode){
        Intent intent = new Intent(context,MapActivity.class);
//        intent.putExtra(ExtraConstant.EXTRA_LAT,lat);
//        intent.putExtra(ExtraConstant.EXTRA_LNG,lng);
        context.startActivityForResult(intent,requestCode);

    }


    private double lng, lat;
    private LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private LatLng curLatLng;
    private POILocationAdapter adapter;
    private List<LocationBean> list = new ArrayList<>();
    private PoiSearch mPoiSearch;
    private int curPage, totalPage;
    private String searchKey = "住宅";
    private boolean isRefresh = true;
    /**
     * 0 : 反地理编码检索   1 ：POI检索
     */
    private int type;
    private GeoCoder geocode;
    private ReverseGeoCodeOption options;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_map;
    }

    @Override
    protected void initViewAndControl() {
        lat = getIntent().getDoubleExtra(ExtraConstant.EXTRA_LAT, 0);
        lng = getIntent().getDoubleExtra(ExtraConstant.EXTRA_LNG, 0);
        searchBarTitle.setSearchEtHint(R.string.search_poi_location);
        searchBarTitle.setOnEditTextChangeListener(this);
        PermissionUtil.checkPermission(this, MyPermissionConstant.READ_EXTERNAL_STORAGE + PERMISSION_TITLE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
    }


    @Override
    public void permissionSuccess(int permissionReqCode) {
        super.permissionSuccess(permissionReqCode);
        switch (permissionReqCode) {
            case MyPermissionConstant.READ_EXTERNAL_STORAGE + PERMISSION_TITLE:
                initRecycler();
                initReseverLocation();
                initLocationConfig();
                break;
        }
    }

    @Override
    public void permissionFail(int permissionReqCode) {
        super.permissionFail(permissionReqCode);
        finish();
    }


    private void initReseverLocation() {
        geocode = GeoCoder.newInstance();
        //新建查询对象要查询的条件
        options = new ReverseGeoCodeOption();
        // 发起反地理编码请求
        geocode.setOnGetGeoCodeResultListener(this);
    }

    private void initRecycler() {
        rvPoiInfo.setPullRefreshEnabled(false);
        rvPoiInfo.setLoadingMoreEnabled(true);
        rvPoiInfo.setLoadingListener(this);
        rvPoiInfo.setLayoutManager(new LinearLayoutManager(this));
        adapter = new POILocationAdapter(this, list);
        adapter.setListener(this);
        rvPoiInfo.setAdapter(adapter);
    }


    /**
     * 初始化定位设置
     */
    private void initLocationConfig() {
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，设置定位模式，默认高精度
        //LocationMode.Hight_Accuracy：高精度；
        //LocationMode. Battery_Saving：低功耗；
        //LocationMode. Device_Sensors：仅使用设备；
        option.setCoorType("bd0911");
        //可选，设置返回经纬度坐标类型，默认GCJ02
        //GCJ02：国测局坐标；
        //BD09ll：百度经纬度坐标；
        //BD09：百度墨卡托坐标；
        //海外地区定位，无需设置坐标类型，统一返回WGS84类型坐标
        option.setScanSpan(1000);
        //可选，设置发起定位请求的间隔，int类型，单位ms
        //如果设置为0，则代表单次定位，即仅定位一次，默认为0
        //如果设置非0，需设置1000ms以上才有效
        option.setOpenGps(true);
        //可选，设置是否使用gps，默认false
        //使用高精度和仅用设备两种定位模式的，参数必须设置为true
        option.setLocationNotify(true);
        //可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false
        option.setIgnoreKillProcess(false);
        //可选，定位SDK内部是一个service，并放到了独立进程。
        //设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)
        option.SetIgnoreCacheException(false);
        //可选，设置是否收集Crash信息，默认收集，即参数为false
        option.setWifiCacheTimeOut(5 * 60 * 1000);
        //可选，V7.2版本新增能力
        //如果设置了该接口，首次启动定位时，会先判断当前Wi-Fi是否超出有效期，若超出有效期，会先重新扫描Wi-Fi，然后定位
        option.setEnableSimulateGps(false);
        //可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false
        option.setIsNeedAddress(true);
        option.setIsNeedLocationPoiList(true);
        mLocationClient.setLocOption(option);


        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(listener);
        //mLocationClient为第二步初始化过的LocationClient对象
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        // 开启定位
        mLocationClient.start();
    }


    OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener() {
        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            if (poiResult != null && poiResult.getAllPoi() != null) {
                totalPage = poiResult.getTotalPageNum();
                getPoiResultSuccess(poiResult.getAllPoi());
            }
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {
        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
        }

        //废弃
        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

        }
    };


    /**
     * 点击条目
     *
     * @param bean
     */
    @Override
    public void onLocationClick(LocationBean bean) {
        Intent intent = new Intent();
        intent.putExtra(ExtraConstant.EXTRA_LOCATION, bean);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onEditTextChange(String str) {
        searchKey = str;
        type = 1;
        loadData(true);
    }

    @Override
    public void onEditFinish(String str) {
    }

    @Override
    public void onClickSoftWare(String str) {
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        if (curPage < totalPage) {
            curPage++;
            type = 1;
            loadData(false);
        }
    }


    /**
     * 定位信息回调
     */
    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取经纬度相关（常用）的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
            if (location == null) {
                return;
            }
            //收到定位信息后停止定位
            mLocationClient.stop();
            curLatLng = new LatLng(location.getLatitude(), location.getLongitude());
            loadData(true);
        }
    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult result) {
    }

    /**
     * 反地理编码回调
     *
     * @param result
     */
    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            return;
        }
        getPoiResultSuccess(result.getPoiList());
    }

    /**
     * 获取周边信息成功
     *
     * @param poiInfos
     */
    private void getPoiResultSuccess(List<PoiInfo> poiInfos) {
        if (poiInfos == null || poiInfos.size() <= 0) {
            return;
        }
        if (isRefresh) {
            list.clear();
        }
        for (PoiInfo bean : poiInfos) {
            list.add(new LocationBean(bean.name, bean.getAddress(), bean.getLocation().longitude, bean.getLocation().latitude));
        }
        adapter.notifyDataSetChanged();
    }


    public void loadData(boolean isRefresh) {
        this.isRefresh = isRefresh;
        if (isRefresh) {
            curPage = 0;
        }
        if (type == 0) {
            options.location(curLatLng);
            geocode.reverseGeoCode(options);
        }
    }




    @Override
    protected void onDestroy() {
        if (mLocationClient != null) {
            mLocationClient.stop();
        }
        if (mPoiSearch != null) {
            mPoiSearch.destroy();
        }
        super.onDestroy();
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    /**
     * 补充：计算两点之间真实距离
     *
     * @return 米
     */
    public static double getDistance(double longitude1, double latitude1, double longitude2, double latitude2) {
        // 维度
        double lat1 = (Math.PI / 180) * latitude1;
        double lat2 = (Math.PI / 180) * latitude2;
        // 经度
        double lon1 = (Math.PI / 180) * longitude1;
        double lon2 = (Math.PI / 180) * longitude2;
        // 地球半径
        double R = 6371;
        // 两点间距离 km，如果想要米的话，结果*1000就可以了
        double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * R;
        return d * 1000;
    }

}

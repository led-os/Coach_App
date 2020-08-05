package com.jsjlzj.wayne.ui.publicac;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.LatLng;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.SortAdapter;
import com.jsjlzj.wayne.adapter.recycler.SelectLocationAdapter;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.entity.address.CityListBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.AnalysisUtil;
import com.jsjlzj.wayne.utils.CharacterParser;
import com.jsjlzj.wayne.widgets.SideBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @ClassName: ShopPoiActivity
 * @Description: 城市选择页面
 * @Author: 曾海强
 * @CreateDate:
 */
public class ShopPoiActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView, SelectLocationAdapter.OnItemClickListener {

    public static final int REQUEST_CODE = 10010;

    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.lv_city)
    ListView lvCity;
    @BindView(R.id.dialog)
    TextView dialog;
    @BindView(R.id.side_bar)
    SideBar sideBar;
    private TextView tvCurrent;
    private CharacterParser characterParser;

    private SortAdapter adapter;
    private String cityName = "";
    private List<CityListBean> cityList = new ArrayList<>();
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private LatLng curLatLng;

    public static void go2this(Activity context,String cityName,int reqCode) {
        Intent intent = new Intent(context, ShopPoiActivity.class);
        intent.putExtra(ExtraConstant.EXTRA_NAME,cityName);
        context.startActivityForResult(intent,reqCode);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_shop_poi;
    }

    @Override
    protected void initViewAndControl() {
        initTitle("定位");
        initData();
        initView();
        initLocationConfig();
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
        //mLocationClient为第二步初始化过的LocationClient对象
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        // 开启定位
        mLocationClient.start();
    }



    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    private void initData() {
        cityName = getIntent().getStringExtra(ExtraConstant.EXTRA_NAME);
        String city_str = AnalysisUtil.getJson("cityList.json", this);
        JSONArray cityArray = null;
        tvLocation.setText(cityName);
        try {
            cityArray = JSONArray.parseArray(city_str);
            cityList = cityArray.toJavaList(CityListBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        sideBar.setTextView(dialog);
        // 设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(s -> {
            // 该字母首次出现的位置
            int position = adapter.getPositionForSection(s);
            if (position != -1) {
                lvCity.setSelection(position);
            }
        });

        adapter = new SortAdapter(this, cityList);
        adapter.setListener(this);
        lvCity.setAdapter(adapter);
    }


    @Override
    public void onItemClick(String string) {
        Intent data = new Intent();
        data.putExtra(ExtraConstant.EXTRA_NAME,string);
        setResult(RESULT_OK,data);
        finish();
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
            cityName = location.getCity();
            if(!TextUtils.isEmpty(cityName)){
                tvLocation.setText(cityName);
            }
        }
    }

}

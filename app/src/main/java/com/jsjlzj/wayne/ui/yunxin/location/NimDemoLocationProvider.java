package com.jsjlzj.wayne.ui.yunxin.location;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;

import com.jsjlzj.wayne.ui.store.personal.storeinfo.set.NavigationActivity;
import com.netease.nim.uikit.api.model.location.LocationProvider;
import com.netease.nim.uikit.common.ui.dialog.EasyAlertDialog;
import com.netease.nim.uikit.common.util.log.LogUtil;

/**
 * Created by zhoujianghua on 2015/8/11.
 */
public class NimDemoLocationProvider implements LocationProvider {
    public static String LATITUDE = "latitude";
    public static String LONGITUDE = "longitude";
    public static String ADDRESS = "address";

    @Override
    public void requestLocation(final Context context, Callback callback) {
//        if (!isLocationEnabled(context)) {
        if (!NimLocationManager.isLocationEnable(context)) {
            final EasyAlertDialog alertDialog = new EasyAlertDialog(context);
            alertDialog.setMessage("位置服务未开启");
            alertDialog.addNegativeButton("取消", EasyAlertDialog.NO_TEXT_COLOR, EasyAlertDialog.NO_TEXT_SIZE,
                    new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });
            alertDialog.addPositiveButton("设置", EasyAlertDialog.NO_TEXT_COLOR, EasyAlertDialog.NO_TEXT_SIZE,
                    new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                            Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            try {
                                context.startActivity(intent);
                            } catch (Exception e) {
                                LogUtil.e("LOC", "start ACTION_LOCATION_SOURCE_SETTINGS error");
                            }

                        }
                    });
            alertDialog.show();
            return;
        }
//
//        LocationAmapActivity.start(context, callback);
        MessageMapActivity.go2this(context, callback);
    }

    @Override
    public void openMap(Context context, double longitude, double latitude, String address) {

        NavigationActivity.go2this((Activity) context,  address,  longitude+","+latitude);
//        Intent intent = new Intent(context, NavigationActivity.class);
//        intent.putExtra(LONGITUDE, longitude);
//        intent.putExtra(LATITUDE, latitude);
//        intent.putExtra(ADDRESS, address);
//        context.startActivity(intent);
    }

    public boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }


    }
}

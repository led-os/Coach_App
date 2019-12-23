package com.jsjlzj.wayne.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.constant.MyPermissionConstant;
import com.jsjlzj.wayne.enums.EnumQrCode;
import com.jsjlzj.wayne.utils.permission.PermissionUtil;
import com.lib.zxing.activity.CaptureActivity;
import com.lib.zxing.activity.CodeUtils;

import java.util.HashMap;

public class ScanUtil {
    public static final int SCAN_QR_CODE_REQ = 0XA001;
    public static final String QR_CODE_TYPE = "type";
    public static final String QR_CODE_CONTENT = "content";
    private Object context;

    @EnumQrCode.QrCodeType
    private String target;

    public void scan(Object object, @EnumQrCode.QrCodeType String target) {
        this.context = object;
        this.target = target;
        PermissionUtil.checkPermission(object, MyPermissionConstant.CAMERA, Manifest.permission.CAMERA);
    }

    public void scan(Object object) {
        scan(object, EnumQrCode.QR_TYPE_NO_LIMIT);
    }

    public void go2Capture() {
        Intent intent = new Intent();
        if (context instanceof Fragment) {
            Fragment fragment = (Fragment) context;
            intent.setClass(fragment.getContext(), CaptureActivity.class);
            fragment.startActivityForResult(intent, SCAN_QR_CODE_REQ);
        } else if (context instanceof Activity) {
            Activity activity = (Activity) context;
            intent.setClass(activity, CaptureActivity.class);
            activity.startActivityForResult(intent, SCAN_QR_CODE_REQ);
        } else {
            throw new IllegalArgumentException("非法上下文对象获取权限信息");
        }
    }

    /**
     * 根据二维码结果跳转
     *
     * @param data
     */
    public Intent handleResult2Intent(Intent data) {
        if (data == null) {
            return null;
        }
        Bundle bundle = data.getExtras();
        if (bundle == null) {
            return null;
        }
        String result = bundle.getString(CodeUtils.RESULT_STRING);
        if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
            LogAndToastUtil.log("二维码解析：%s", result);
            return parseQrCodeIntent(result);
        } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
            LogAndToastUtil.log("二维码解析失败");
        }
        return null;
    }

    /***
     * 只获取二维码内容
     * @param data
     */
    public HashMap<String, String> handleResult2Map(Intent data) {
        if (data == null) {
            return null;
        }
        Bundle bundle = data.getExtras();
        if (bundle == null) {
            return null;
        }
        if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
            String result = bundle.getString(CodeUtils.RESULT_STRING);
            LogAndToastUtil.log("二维码解析：%s", result);
            return parseQrCode2Map(result);
        } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
            LogAndToastUtil.log("二维码解析失败");
            return null;
        }
        return null;
    }

    private HashMap<String, String> parseQrCode2Map(String result) {
        if (result.contains("lightid=")) {
            result = result.split("lightid=")[1];
            if (result.contains("&src=")) {
                result = result.split("&src=")[0];
            }
        } else if (result.contains("?")) {
            result = result.split("\\?")[1];
        }


        HashMap<String, String> resMap = JsonUtil.fromJson(result, new TypeToken<HashMap<String, String>>() {
        }.getType());
        if (resMap == null) {
            return null;
        }
        final String content = resMap.get(QR_CODE_CONTENT);
        final String type = resMap.get(QR_CODE_TYPE);
        if (TextUtils.isEmpty(content) || TextUtils.isEmpty(type) || (!EnumQrCode.QR_TYPE_NO_LIMIT.equals(target) && !target.equals(type))) {
            return null;
        }
        return resMap;
    }

    private Intent parseQrCodeIntent(String result) {
        if (result.contains("lightid=")) {
            result = result.split("lightid=")[1];
            if (result.contains("&src=")) {
                result = result.split("&src=")[0];
            }
        } else if (result.contains("?")) {
            result = result.split("\\?")[1];
        }


        HashMap<String, String> resMap = JsonUtil.fromJson(result, new TypeToken<HashMap<String, String>>() {
        }.getType());
        if (resMap == null) {
            return null;
        }
        final String content = resMap.get(QR_CODE_CONTENT);
        final String type = resMap.get(QR_CODE_TYPE);
        if (TextUtils.isEmpty(content) || TextUtils.isEmpty(type) || (!EnumQrCode.QR_TYPE_NO_LIMIT.equals(target) && !target.equals(type))) {
            return null;
        }

        Class<?> cls = null;

        switch (type) {
            case EnumQrCode.QR_TYPE_WEB_LOGIN://登录
//                cls = WebLoginConfirmActivity.class;
                break;
            case EnumQrCode.QR_TYPE_TEAM_DETAIL://团队
//                cls = JoinTeamActivity.class;
                break;
            case EnumQrCode.QR_TYPE_TRICOLOUR_LED://三色灯
//                cls = BindTricolourLightActivity.class;
                break;
            case EnumQrCode.QR_TYPE_MACHINE://机床
                return null;
            default:
                return null;
        }
        if (cls == null) return null;

        Intent intent = new Intent();
        if (context instanceof Fragment) {
            Fragment fragment = (Fragment) context;
            intent.setClass(fragment.getContext(), cls);
        } else if (context instanceof Activity) {
            Activity activity = (Activity) context;
            intent.setClass(activity, cls);
        }
        intent.putExtra(ExtraConstant.EXTRA_QR_CODE_TYPE, type);
        intent.putExtra(ExtraConstant.EXTRA_QR_CODE_CONTENT, content);

        return intent;
    }
}

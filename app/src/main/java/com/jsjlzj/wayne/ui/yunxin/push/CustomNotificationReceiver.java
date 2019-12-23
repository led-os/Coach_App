package com.jsjlzj.wayne.ui.yunxin.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.netease.nimlib.sdk.NimIntent;
import com.netease.nimlib.sdk.msg.model.CustomNotification;

import java.util.Map;

public class CustomNotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // {"type":  "InterviewNotice", ext:{"sHeadImg":俱乐部头像， "tHeadImg": 教练头像 ， "storeName": 俱乐部名称, "id": 面试ID}}
        String action = context.getPackageName() + NimIntent.ACTION_RECEIVE_CUSTOM_NOTIFICATION;
        if (action.equals(intent.getAction())) {
            // 从 intent 中取出自定义通知， intent 中只包含了一个 CustomNotification 对象
            CustomNotification notification = (CustomNotification)
                    intent.getSerializableExtra(NimIntent.EXTRA_BROADCAST_MSG);
            // 第三方 APP 在此处理自定义通知：存储，处理，展示给用户等
            String content = notification.getContent();
            if (!TextUtils.isEmpty(content)) {
                try {
                    Map<String, String> stringMap = new Gson().fromJson(content, new TypeToken<Map<String, String>>() {
                    }.getType());





                } catch (Exception e) {

                }
            }

//            Log.i("demo", "receive custom notification: " + notification.getContent()
//                    + " from :" + notification.getSessionId() + "/" + notification.getSessionType());
        }
    }
}
package com.jsjlzj.wayne.ui.yunxin.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder;
import com.meizu.cloud.pushsdk.platform.message.PushSwitchStatus;
import com.meizu.cloud.pushsdk.platform.message.RegisterStatus;
import com.meizu.cloud.pushsdk.platform.message.SubAliasStatus;
import com.meizu.cloud.pushsdk.platform.message.SubTagsStatus;
import com.meizu.cloud.pushsdk.platform.message.UnRegisterStatus;

public class MeiZuPushReceiver extends BroadcastReceiver {
    /**
     * 以下这些方法运行在非 UI 线程中, 与魅族 SDK 的 MzPushMessageReceiver 方法一一对应。
     * 当开发者自身也接入魅族推送，则应将继承 MzPushMessageReceiver 改为继承 MeiZuPushReceiver，其他不变
     */

    @Override
    public final void onReceive(Context context, Intent intent) {
    }

    public void onRegister(Context context, String pushId) {
    }

    public void onUnRegister(Context context, boolean success) {
    }

    public void onPushStatus(Context context, PushSwitchStatus pushSwitchStatus) {
    }

    public void onRegisterStatus(Context context, RegisterStatus registerStatus) {
    }

    public void onUnRegisterStatus(Context context, UnRegisterStatus unRegisterStatus) {
    }

    public void onSubTagsStatus(Context context, SubTagsStatus subTagsStatus) {
    }

    public void onSubAliasStatus(Context context, SubAliasStatus subAliasStatus) {
    }

    public void onNotificationClicked(Context context, String title, String content, String selfDefineContentString) {
    }

    public void onNotificationArrived(Context context, String title, String content, String selfDefineContentString) {
    }

    public void onNotifyMessageArrived(Context context, String message) {
    }

    public void onNotificationDeleted(Context context, String title, String content, String selfDefineContentString) {
    }

    public void onUpdateNotificationBuilder(PushNotificationBuilder pushNotificationBuilder) {
    }
}
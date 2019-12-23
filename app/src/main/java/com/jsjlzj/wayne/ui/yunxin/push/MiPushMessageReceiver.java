package com.jsjlzj.wayne.ui.yunxin.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;

public class MiPushMessageReceiver extends BroadcastReceiver {
    /**
     * 以下这些方法运行在非 UI 线程中, 与小米SDK PushMessageReceiver 方法一一对应。
     * 开发者如果自身也需要接入小米推送，则应将继承 PushMessageReceiver 改为继承 MiPushMessageReceiver
     */

    @Override
    public final void onReceive(Context context, Intent intent) {
    }

    public void onReceivePassThroughMessage(Context context, MiPushMessage message) {
    }

    public void onNotificationMessageClicked(Context context, MiPushMessage message) {
    }

    public void onNotificationMessageArrived(Context context, MiPushMessage message) {
    }

    public void onReceiveRegisterResult(Context context, MiPushCommandMessage message) {
    }

    public void onCommandResult(Context context, MiPushCommandMessage message) {
    }
}
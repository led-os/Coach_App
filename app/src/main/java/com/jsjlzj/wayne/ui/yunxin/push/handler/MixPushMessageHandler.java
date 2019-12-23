package com.jsjlzj.wayne.ui.yunxin.push.handler;

import android.content.Context;

import java.util.Map;

public interface MixPushMessageHandler {

    /**
     * 第三方推送通知栏点击之后的回调方法，（对于华为推送，这个方法并不能保证一定会回调）
     *
     * @param context
     * @param payload IMessage 中的用户设置的自定义pushPayload {@link com.netease.nimlib.sdk.msg.model.IMMessage}
     * @return true 表示开发者自行处理第三方推送通知栏点击事件，SDK将不再处理; false 表示仍然使用SDK提供默认的点击后的跳转
     */
    boolean onNotificationClicked(Context context, Map<String, String> payload);

    /**
     * 华为推送通知清除接口，利用该接口开发者可以自定义清除华为推送通知。
     * 因为华为推送 SDK 没有清除通知栏接口，对于华为推送消息，云信 SDK 默认调用了清除应用所有通知栏的接口。
     * 如果开发者需要自定义，可以使用这个接口做清除处理
     *
     * @return 返回true表示开发者处理了清除工作，云信 SDK 不再处理，返回false 则相反
     */
    boolean cleanHuaWeiNotifications();

}
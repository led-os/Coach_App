package com.jsjlzj.wayne.ui.yunxin.push;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.coloros.mcssdk.mode.AppMessage;
import com.coloros.mcssdk.mode.SptDataMessage;

public class OppoPushMessageService  extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 普通消息
     * @param context
     * @param appMessage
     */
    public void processMessage(Context context, AppMessage appMessage) {


    }

    /**
     * oppo 官方目前还不支持透传消息
     *
     * @param context
     * @param sptDataMessage
     */
    public void processMessage(Context context, SptDataMessage sptDataMessage) {


    }
}
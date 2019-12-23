package com.jsjlzj.wayne.utils.eventbus;


import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class EnumEventBus {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            LOGIN_OK,
            LOGOUT_OK,
            LOGIN_PHONE,
            LOGIN_PWD,
            LOGIN_EMAIL,
            PUSH_REGISTER_OK,
            PUSH_MSG_CENTER,
            CONSOLE_UPDATE_TRICOLOUR_LIGHT,
            CONSOLE_ADD_MACHINE_2_REFRESH,
            PERSON_INFO_CHANGE,
            MACHINE_RATE_COUNT_0,
            MACHINE_RATE_COUNT_1,
            MACHINE_RATE_COUNT_2,
            MACHINE_RATE_COUNT_3,
            MESSAGE_AGENT,
            MESSAGE_TO_TEAM,
            OBSERVER_FIRST,
            TEAM_CHANGE,

            MESSAGE_LINK,
            MESSAGE_PHONE,
            MESSAGE_PHONE_AGREE,
            MESSAGE_PHONE_REFUSE,
            MESSAGE_WECHAT,
            MESSAGE_WECHAT_AGREE,
            MESSAGE_WECHAT_REFUSE,
            MESSAGE_PHONE_AGREE_SEND,
            MESSAGE_PHONE_AGREE_CALL,
            MESSAGE_SEND_MESSAGE
    })
    public @interface EventBusCmd {
    }

    public static final int LOGIN_OK = 1;
    public static final int LOGOUT_OK = 1 << 1;
    public static final int LOGIN_PHONE = 1 << 2;
    public static final int LOGIN_PWD = 1 << 3;
    public static final int LOGIN_EMAIL = 1 << 4;
    public static final int PUSH_REGISTER_OK = 1 << 5;
    public static final int PUSH_MSG_CENTER = 1 << 6;
    public static final int CONSOLE_UPDATE_TRICOLOUR_LIGHT = 0XB001;
    public static final int CONSOLE_ADD_MACHINE_2_REFRESH = 0XB002;
    public static final int PERSON_INFO_CHANGE = 0XB003;


    public static final int MACHINE_RATE_COUNT_0 = 0XB004;
    public static final int MACHINE_RATE_COUNT_1 = 0XB005;
    public static final int MACHINE_RATE_COUNT_2 = 0XB006;
    public static final int MACHINE_RATE_COUNT_3 = 0XB007;

    public static final int MESSAGE_AGENT = 0XB008;
    public static final int MESSAGE_TO_TEAM = 0XB009;
    public static final int OBSERVER_FIRST = 0XB010;
    public static final int TEAM_CHANGE = 0XB011;

    public static final int MESSAGE_LINK = 0XB021;//建立连接
    public static final int MESSAGE_PHONE = 0XB022;//交换手机号
    public static final int MESSAGE_PHONE_AGREE = 0XB023;//同意交换手机号
    public static final int MESSAGE_PHONE_AGREE_SEND = 0XB033;//同意交换手机号
    public static final int MESSAGE_PHONE_AGREE_CALL = 0XB043;//同意交换手机号
    public static final int MESSAGE_PHONE_REFUSE = 0XB024;//拒绝交换手机号
    public static final int MESSAGE_WECHAT = 0XB025;//交换微信
    public static final int MESSAGE_WECHAT_AGREE = 0XB026;//同意交换微信
    public static final int MESSAGE_WECHAT_REFUSE = 0XB027;//拒绝交换微信
    public static final int MESSAGE_SEND_MESSAGE = 0XB028;//拒绝交换微信
}

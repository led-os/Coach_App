package com.jsjlzj.wayne.enums;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class EnumMessageType {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            PREPARE_MATERIAL,
            REPLACE_MOULD,
            STATEMENT,
            FAULT,
            INCOME,
            INVITE_ME_JOIN_TEAM,
            REPLY_FEEDBACK,
            PENDING_ORDER,
            POPULARIZE,
            JOIN_TEAM_SUCCESS,
            JOIN_TEAM_APPLY,
            PENDING_AGENT

    })
    public @interface MsgType {
    }

    /**
     * 备料
     */
    public static final int PREPARE_MATERIAL = 1;
    /**
     * 换模
     */
    public static final int REPLACE_MOULD = 2;
    /**
     * 报表
     */
    public static final int STATEMENT = 3;
    /**
     * 故障
     */
    public static final int FAULT = 4;
    /**
     * 收入
     */
    public static final int INCOME = 5;
    /**
     * 被邀请加入团队
     */
    /*
     /邀请       申请者
     */
    public static final int INVITE_ME_JOIN_TEAM = 6;
    /*
        /加入成功    申请者
     */
    public static final int JOIN_TEAM_SUCCESS = 16;
    /*
        /申请加入    管理
     */
    public static final int JOIN_TEAM_APPLY = 26;
    /**
     * 对“建议反馈”的回复
     */
    public static final int REPLY_FEEDBACK = 7;
    /**
     * 订单挂起
     */
    public static final int PENDING_ORDER = 8;

    /**
     * 观察员
     */
    public static final int PENDING_AGENT = 15;

    /**
     * 这一期不做了
     * 黄龙州（web后台）发的推送<p/>
     * 推广
     */
    public static final int POPULARIZE = 100;


}

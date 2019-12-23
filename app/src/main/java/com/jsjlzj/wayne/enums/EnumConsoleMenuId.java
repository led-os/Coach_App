package com.jsjlzj.wayne.enums;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class EnumConsoleMenuId {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            MACHINE_MANAGER_ID, WORK_ORDER_SCHEDULE_ID, MACHINE_SCHEDULE_ID, LABOR_MONITORING_ID, MACHINE_PREPARE_MATERIAL_ID, WORK_ORDER_PREPARE_MATERIAL_ID, MACHINE_OPERATION_ID, RECORD_CHECK_ID
    })
    public @interface MenuId {
    }

    /**
     * 机床操作ID
     */
    public static final int MACHINE_OPERATION_ID = 1;
    /**
     * 机床备料ID
     */
    public static final int MACHINE_PREPARE_MATERIAL_ID = 2;
    /**
     * 工单备料ID
     */
    public static final int WORK_ORDER_PREPARE_MATERIAL_ID = 3;
    /**
     * 机床进度ID
     */
    public static final int MACHINE_SCHEDULE_ID = 4;
    /**
     * 机床状态ID
     */
    public static final int MACHINE_STATE_ID = 47;
    /**
     * 当班机床进度ID
     */
    public static final int MACHINE_SCHEDULE_CLASSES_ID = 57;
    /**
     * 记录审批ID
     */
    public static final int RECORD_CHECK_ID = 5;
    /**
     * 效率统计ID
     */
    public static final int LABOR_MONITORING_ID = 6;
    /**
     * 工单进度ID
     */
    public static final int WORK_ORDER_SCHEDULE_ID = 7;
    /**
     * 机床管理ID
     */
    public static final int MACHINE_MANAGER_ID = 8;


}

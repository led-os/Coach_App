package com.jsjlzj.wayne.enums;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class EnumMachineOperationStatus {
    //机位任务(machine_produce)状态:1 待排产  2排产确认       3待换模  4换摸中  5待加工  6 加工中  7加工完成 8 挂起
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            WAIT_4_REPLACE_MOULD, DOING_REPLACE_MOULD, REPLACE_MOULD_WAITING, WAIT_4_PROCESS, PROCESSING, PROCESS_WAITING, COMPLETED, HANGUP, INTEND_WAIT, WAIT_4_PROCESS_WAIT, PROCESS_FAULT, REPLACE_MOULD_FAULT
    })
    public @interface Status {
    }

    /**
     * 待换模
     */
    public static final int WAIT_4_REPLACE_MOULD = 3;//2
    /**
     * 换模中
     */
    public static final int DOING_REPLACE_MOULD = 4;//3
    /**
     * 换模等待
     */
    public static final int REPLACE_MOULD_WAITING = 9;
    /**
     * 待加工
     */
    public static final int WAIT_4_PROCESS =5;// 4
    /**
     * 加工中
     */
    public static final int PROCESSING = 6;//5
    /**
     * 加工中等待
     */
    public static final int PROCESS_WAITING = 11;
    /**
     * 已完成
     */
    public static final int COMPLETED = 7;//6
    public static final int HANGUP = 8;
    /**
     * 故障
     */
//    public static final int FAULT = 7;
    /**
     * （换模前）准备中等待
     */
    public static final int INTEND_WAIT = 18;//8;
    /**
     * 待加工等待
     */
    public static final int WAIT_4_PROCESS_WAIT = 10;

    /**
     * 加工故障
     */
    public static final int PROCESS_FAULT = 12;
    /**
     * 换模故障
     */
    public static final int REPLACE_MOULD_FAULT = 13;
}

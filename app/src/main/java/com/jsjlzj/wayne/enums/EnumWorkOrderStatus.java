package com.jsjlzj.wayne.enums;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class EnumWorkOrderStatus {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            ALLOCATE_WAIT,ALLOCATING,ALLOCATED,SCHEDULIING,COMPLETE
    })
    public @interface JoinType {
    }

    public static final int ALLOCATE_WAIT = 1;//待分配
    public static final int ALLOCATING = 2;//分配确认
    public static final int ALLOCATED = 3;//已分配
    public static final int SCHEDULIING = 4;//排产确认
    public static final int COMPLETE = 5;//加工完成
}

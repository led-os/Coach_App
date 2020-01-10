package com.jsjlzj.wayne.enums;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class EnumMachineOperationActionStatus {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            START_REPLACE_MOULD, END_REPLACE_MOULD, START_PROCESS, END_PROCESS
    })
    public @interface Status {
    }
    public static final int START_REPLACE_MOULD = 2301;
    public static final int END_REPLACE_MOULD = 2302;
    public static final int START_PROCESS = 4501;
    public static final int END_PROCESS = 4502;
}

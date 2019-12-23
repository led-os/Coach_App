package com.jsjlzj.wayne.enums;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class EnumRecordCheckReportType {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            WAIT_REPORT, END_PRODUCE_REPORT, REPLACE_MOULD
    })
    public @interface Type {
    }

    public static final int WAIT_REPORT = 1;
    public static final int REPLACE_MOULD = 2;
    public static final int END_PRODUCE_REPORT = 3;

}

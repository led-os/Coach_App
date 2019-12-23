package com.jsjlzj.wayne.enums;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class EnumLaborMonitoringSearchType {
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            DAY, WEEK, MONTH, QUARTER
    })
    public @interface Type {
    }

    public static final String DAY = "1";
    public static final String WEEK = "2";
    public static final String MONTH = "3";
    public static final String QUARTER = "4";

}

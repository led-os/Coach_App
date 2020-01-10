package com.jsjlzj.wayne.enums;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class EnumReplaceMouldStatus {
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            REPLACE_FAIL, REPLACE_SUCCESS
    })
    public @interface Status {
    }

    public static final String REPLACE_FAIL = "FAIL";
    public static final String REPLACE_SUCCESS = "SUCCESS ";

}

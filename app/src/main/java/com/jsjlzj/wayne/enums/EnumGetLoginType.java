package com.jsjlzj.wayne.enums;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class EnumGetLoginType {
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            LOGIN_USE_CHECK_CODE, LOGIN_USE_PWD
    })
    public @interface CheckCodeType{
    }


    public static final String LOGIN_USE_CHECK_CODE = "1";
    public static final String LOGIN_USE_PWD = "2";
}

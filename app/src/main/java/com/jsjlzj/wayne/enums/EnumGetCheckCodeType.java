package com.jsjlzj.wayne.enums;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class EnumGetCheckCodeType {
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            PHONE_REGISTER_TYPE, PHONE_FIND_PWD, EMAIL_FIND_PWD
    })
    public @interface CheckCodeType{
    }

    public static final String PHONE_REGISTER_TYPE = "1";
    public static final String PHONE_FIND_PWD = "2";
    public static final String EMAIL_FIND_PWD = "3";
    public static final String EMAIL_BIND_CODE_TYPE = "4";

}

package com.jsjlzj.wayne.enums;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class EnumFindPwdType {
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            FIND_BY_PHONE, FIND_BY_EMAIL
    })
    public @interface FindPwdType{
    }


    public static final String FIND_BY_PHONE = "1";
    public static final String FIND_BY_EMAIL = "2";
}

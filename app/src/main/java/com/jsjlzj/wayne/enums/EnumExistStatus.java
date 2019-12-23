package com.jsjlzj.wayne.enums;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class EnumExistStatus {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            NOT_EXIST, EXIST
    })
    public @interface Status {
    }

    public static final int NOT_EXIST = 0;
    public static final int EXIST = 1;

}

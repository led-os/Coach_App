package com.jsjlzj.wayne.enums;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class EnumCheckStatus {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            NOT_CHECK, HAD_CHECK
    })
    public @interface Status {
    }

    public static final int NOT_CHECK = 0;
    public static final int HAD_CHECK = 1;

}

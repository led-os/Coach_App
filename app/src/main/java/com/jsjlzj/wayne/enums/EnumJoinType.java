package com.jsjlzj.wayne.enums;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class EnumJoinType {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            NOT_JOIN,HAD_JOIN
    })
    public @interface JoinType {
    }

    public static final int NOT_JOIN = 0;
    public static final int HAD_JOIN = 1;

}

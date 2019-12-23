package com.jsjlzj.wayne.enums;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class EnumExpandType {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            EXPAND_TYPE_NOW, EXPAND_TYPE_HISTORY,EXPAND_TYPE_FUTURE
    })
    public @interface Type {
    }

    /**
     * 历史记录 等于0
     */
    public static final int EXPAND_TYPE_HISTORY = 0;
    /**
     * 当前 等于1
     */
    public static final int EXPAND_TYPE_NOW = 1;
    /**
     * 将来 大于1
     */
    public static final int EXPAND_TYPE_FUTURE = 2;


}

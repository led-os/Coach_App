package com.jsjlzj.wayne.enums;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class EnumMachineType {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            AUTO, MANUAL, PULSE
    })
    public @interface Type {
    }
    public static final int AUTO = 1;
    public static final int MANUAL = 2;
    public static final int PULSE = 3;
}

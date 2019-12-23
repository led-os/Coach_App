package com.jsjlzj.wayne.enums;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class EnumTricolourStatus {
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            SWITCH_OFF, RED, YELLOW, GREEN, USELESS, USEED
    })
    public @interface Status {
    }

    //000:关灯,001:绿灯,010:黄灯,100:红灯, 111:无效
    public static final String SWITCH_OFF = "000";
    public static final String GREEN = "001";
    public static final String YELLOW = "010";
    public static final String RED = "100";
    public static final String USELESS = "111";
    public static final String USEED = "222";

}

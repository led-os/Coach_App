package com.jsjlzj.wayne.enums;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class EnumUploadPic {
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            USER_HEAD_PIC, TEAM_HEAD_PIC, TEAM_IMAGE, MACHINE_IMAGE, MACHINE_REPAIR_IMAGE,FEEDBACK_IMAGE
    })
    public @interface PicType {
    }

    public static final String USER_HEAD_PIC = "1";
    public static final String TEAM_HEAD_PIC = "2";
    public static final String TEAM_IMAGE = "3";
    public static final String MACHINE_IMAGE = "4";
    public static final String FEEDBACK_IMAGE = "5";
    public static final String MACHINE_REPAIR_IMAGE = "6";
}

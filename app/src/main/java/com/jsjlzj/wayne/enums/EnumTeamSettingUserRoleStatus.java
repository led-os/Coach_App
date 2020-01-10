package com.jsjlzj.wayne.enums;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class EnumTeamSettingUserRoleStatus {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            ROLE_DEFAULT, ROLE_SETTING, ROLE_CANCEL_SETTING
    })
    public @interface RoleStatus {
    }

    public static final int ROLE_DEFAULT = -1;
    public static final int ROLE_SETTING = 1;
    public static final int ROLE_CANCEL_SETTING = 0;
}

package com.jsjlzj.wayne.enums;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class EnumTeamRoleId {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            TEAM_ROLE_SENIOR,TEAM_ROLE_PMC, TEAM_ROLE_MACHINIST, TEAM_ROLE_REPLACE_MOULD, TEAM_ROLE_MATERIALS_PREPARING_WORKER
    })
    public @interface RoleType {
    }

    public static final int TEAM_ROLE_ADMIN = 1;
    public static final int TEAM_ROLE_PMC = 2;
    public static final int TEAM_ROLE_SENIOR = 3;
    public static final int TEAM_ROLE_MACHINIST = 8;
    public static final int TEAM_ROLE_REPLACE_MOULD = 6;
    public static final int TEAM_ROLE_MATERIALS_PREPARING_WORKER = 7;

}

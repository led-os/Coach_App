package com.jsjlzj.wayne.enums;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class EnumTeamMemberSearchType {
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            TEAM_MEMBER_SEARCH_TYPE_MEMBER, TEAM_MEMBER_SEARCH_TYPE_ADMIN, TEAM_MEMBER_SEARCH_TYPE_PMC, TEAM_MEMBER_SEARCH_TYPE_PMC_SENIOR
    })
    public @interface Type {
    }

    /**成员管理，查询成员时，"is"的值*/
    public static final String TEAM_MEMBER_SEARCH_TYPE_MEMBER = "0";
    /**团队设置-高管，查询成员时，"is"的值*/
    public static final String TEAM_MEMBER_SEARCH_TYPE_ADMIN = "1";
    /**团队设置-PMC，查询成员时，"is"的值*/
    public static final String TEAM_MEMBER_SEARCH_TYPE_PMC = "2";
    /**团队设置-高管，查询成员时，"is"的值*/
    public static final String TEAM_MEMBER_SEARCH_TYPE_PMC_SENIOR = "3";

}

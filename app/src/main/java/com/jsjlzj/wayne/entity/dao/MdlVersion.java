package com.jsjlzj.wayne.entity.dao;

import java.util.Date;

public class MdlVersion {

    private boolean forcedUpdate;//是否强制恒信
    private boolean lastVersion;//是否最新版本
    private String versionCode;//最新版本号
    private String versionUrl;//url
    private long cxkDate;

    public boolean isForcedUpdate() {
        return forcedUpdate;
    }

    public void setForcedUpdate(boolean forcedUpdate) {
        this.forcedUpdate = forcedUpdate;
    }

    public boolean isLastVersion() {
        return lastVersion;
    }

    public void setLastVersion(boolean lastVersion) {
        this.lastVersion = lastVersion;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionUrl() {
        return versionUrl;
    }

    public void setVersionUrl(String versionUrl) {
        this.versionUrl = versionUrl;
    }

    public long getCxkDate() {
        return cxkDate;
    }

    public void setCxkDate(long cxkDate) {
        this.cxkDate = cxkDate;
    }

    @Override
    public String toString() {
        return "{" +
                "\"forcedUpdate\":" + forcedUpdate +
                ", \"lastVersion\":" + lastVersion +
                ", \"versionCode\":\'" + versionCode + "\'" +
                ", \"versionUrl\":\'" + versionUrl + "\'" +
                ", \"cxkDate\":" + cxkDate +
                '}';
    }
}

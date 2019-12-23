package com.jsjlzj.wayne.utils.permission;


public interface MyPermissionResultListener {
    void permissionSuccess(int permissionReqCode);
    void permissionFail(int permissionReqCode);
}

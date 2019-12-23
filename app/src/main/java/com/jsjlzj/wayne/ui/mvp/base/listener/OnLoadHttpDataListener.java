package com.jsjlzj.wayne.ui.mvp.base.listener;

/**
 * Created by Administrator on 2018/4/17.
 */

public interface OnLoadHttpDataListener<T> {
    void onSuccess(int code, T data);
    void onFailure(int code, Throwable e);
}

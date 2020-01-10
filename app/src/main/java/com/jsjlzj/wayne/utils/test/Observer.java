package com.jsjlzj.wayne.utils.test;

import androidx.annotation.NonNull;

public abstract class Observer<T> {
    //组件状态
    static final int STATE_ACTIVE=1;//活跃
    static final int STATE_ONPAUSE=2;//暂停
    static final int STATE_DESTORY=3;//销毁
    static final int STATE_INIT=0;//初始

    private int state =STATE_ACTIVE;

    public abstract  void onChanged(@NonNull T t);

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

}

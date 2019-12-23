package com.jsjlzj.wayne.utils.test;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class LiveDataBus {

    //MutableLiveData --->  LiveData
    private Map<String, MutableLiveData<Object>> bus;

    private static LiveDataBus instance = new LiveDataBus();

    public LiveDataBus() {
        this.bus = new HashMap<>();
    }

    public static LiveDataBus get() {
        return instance;
    }

    public <T> MutableLiveData<T> getChannel(String target, Class<T> type) {
        if (!bus.containsKey(target)) {
            bus.put(target, new MutableLiveData<>());
        }
        return (MutableLiveData<T>) bus.get(target);
    }

    public MutableLiveData<Object> getChannel(String target) {
        return getChannel(target, Object.class);
    }


    private void test(AppCompatActivity context) {
        //订阅
        LiveDataBus.get().getChannel("ss", String.class).observe(context, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        //接收
        LiveDataBus.get().getChannel("key_test").setValue(true);
    }

}

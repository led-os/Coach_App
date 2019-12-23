package com.jsjlzj.wayne.utils.eventbus;


/**
 * Created by Administrator on 2018/4/16.
 */

public class MdlEventBus {
    public @EnumEventBus.EventBusCmd int eventType;
    public Object data;

    public MdlEventBus(@EnumEventBus.EventBusCmd int eventType){
        this.eventType = eventType;
    }

    public MdlEventBus(@EnumEventBus.EventBusCmd int eventType, Object data) {
        this.eventType = eventType;
        this.data = data;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

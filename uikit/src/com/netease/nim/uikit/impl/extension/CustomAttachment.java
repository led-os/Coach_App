package com.netease.nim.uikit.impl.extension;

import com.alibaba.fastjson.JSONObject;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;

/**
 * Created by zhoujianghua on 2015/4/9.
 */
public abstract class CustomAttachment implements MsgAttachment {

    protected static final String KEY_INTENVIEWID = "intenviewID";
    protected static final String KEY_POSITIONID = "positionID";
    protected static final String KEY_INTENVIEWNAME = "intenviewName";

    protected int type;
    protected String intenviewID;//  求职意向id
    protected String positionID;//  职位ID
    protected String intenviewName;// 意向职位名称

    public CustomAttachment(int type) {
        this.type = type;
    }

    public void fromJson(JSONObject data) {
        if (data != null) {
            parseData(data);
        }
    }

    @Override
    public String toJson(boolean send) {
        return CustomAttachParser.packData(type, packData());
    }

    public int getType() {
        return type;
    }

    protected abstract void parseData(JSONObject data);

    protected abstract JSONObject packData();



    public String getIntenviewID() {
        return intenviewID;
    }

    public void setIntenviewID(String intenviewID) {
        this.intenviewID = intenviewID;
    }

    public String getPositionID() {
        return positionID;
    }

    public void setPositionID(String positionID) {
        this.positionID = positionID;
    }

    public String getIntenviewName() {
        return intenviewName;
    }

    public void setIntenviewName(String intenviewName) {
        this.intenviewName = intenviewName;
    }
}

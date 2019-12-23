package com.netease.nim.uikit.impl.extension.myattchment;

import com.alibaba.fastjson.JSONObject;
import com.netease.nim.uikit.impl.extension.CustomAttachment;
import com.netease.nim.uikit.impl.extension.CustomAttachmentType;

public class WechatAttachment extends CustomAttachment {
    //data: { intenviewID : 求职意向id,  positionID : 职位ID，intenviewName : 意向职位名称，, ownWeChat : 自己微信号}
    private String title = "交换微信号";//  求职意向id
    private String ownWeChat;// 自己手机号

    private static final String KEY_OWNWECHAT = "ownWeChat";

    public WechatAttachment() {
        super(CustomAttachmentType.Wechat);
    }

    @Override
    protected void parseData(JSONObject data) {
        intenviewID = data.getString(KEY_INTENVIEWID);
        positionID = data.getString(KEY_POSITIONID);
        intenviewName = data.getString(KEY_INTENVIEWNAME);
        ownWeChat = data.getString(KEY_OWNWECHAT);
    }

    @Override
    protected JSONObject packData() {
        JSONObject data = new JSONObject();
        data.put(KEY_INTENVIEWID, intenviewID);
        data.put(KEY_POSITIONID, positionID);
        data.put(KEY_INTENVIEWNAME, intenviewName);
        data.put(KEY_OWNWECHAT, ownWeChat);
        return data;
    }

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

    public String getOwnWeChat() {
        return ownWeChat;
    }

    public void setOwnWeChat(String ownWeChat) {
        this.ownWeChat = ownWeChat;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

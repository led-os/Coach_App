package com.netease.nim.uikit.impl.extension.myattchment;

import com.alibaba.fastjson.JSONObject;
import com.netease.nim.uikit.impl.extension.CustomAttachment;
import com.netease.nim.uikit.impl.extension.CustomAttachmentType;

public class WechatAgreeAttachment extends CustomAttachment {
    //data: { intenviewID: 求职意向id,  positionID : 职位ID，intenviewName : 意向职位名称，, ownWeChat : 自己微信号 ，otherWeChat : 对方微信号  }
    private String title = "同意交换微信号的请求";//  您已成功拒绝了对方交换电话的请求

    private String ownWeChat;// 自己手机号
    private String otherWeChat;// 自己手机号


    private static final String KEY_OWNWECHAT = "ownWeChat";
    private static final String KEY_OTHERWECHAT= "otherWeChat";

    public WechatAgreeAttachment() {
        super(CustomAttachmentType.Wechat_Agree);
    }

    @Override
    protected void parseData(JSONObject data) {
        intenviewID = data.getString(KEY_INTENVIEWID);
        positionID = data.getString(KEY_POSITIONID);
        intenviewName = data.getString(KEY_INTENVIEWNAME);
        ownWeChat = data.getString(KEY_OWNWECHAT);
        otherWeChat = data.getString(KEY_OTHERWECHAT);
    }

    @Override
    protected JSONObject packData() {
        JSONObject data = new JSONObject();
        data.put(KEY_INTENVIEWID, intenviewID);
        data.put(KEY_POSITIONID, positionID);
        data.put(KEY_INTENVIEWNAME, intenviewName);
        data.put(KEY_OWNWECHAT, ownWeChat);
        data.put(KEY_OTHERWECHAT, otherWeChat);
        return data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getOtherWeChat() {
        return otherWeChat;
    }

    public void setOtherWeChat(String otherWeChat) {
        this.otherWeChat = otherWeChat;
    }
}

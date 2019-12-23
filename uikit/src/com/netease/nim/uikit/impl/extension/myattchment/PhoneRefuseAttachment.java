package com.netease.nim.uikit.impl.extension.myattchment;

import com.alibaba.fastjson.JSONObject;
import com.netease.nim.uikit.impl.extension.CustomAttachment;
import com.netease.nim.uikit.impl.extension.CustomAttachmentType;

public class PhoneRefuseAttachment extends CustomAttachment {
    //{ intenviewID: 求职意向id,  positionID: 职位ID，intenviewName:意向职位名称，ownMebile:自己手机号，otherMebile: 对方手机号  }
    private String title = "拒绝交换电话的请求";//  您已成功拒绝了对方交换电话的请求



    private static final String KEY_OWNMEBILE = "ownMebile";
    private static final String KEY_OTHERMEBILE= "otherMebile";

    public PhoneRefuseAttachment() {
        super(CustomAttachmentType.Phone_refuse);
    }

    @Override
    protected void parseData(JSONObject data) {
        intenviewID = data.getString(KEY_INTENVIEWID);
        positionID = data.getString(KEY_POSITIONID);
        intenviewName = data.getString(KEY_INTENVIEWNAME);
    }

    @Override
    protected JSONObject packData() {
        JSONObject data = new JSONObject();
        data.put(KEY_INTENVIEWID, intenviewID);
        data.put(KEY_POSITIONID, positionID);
        data.put(KEY_INTENVIEWNAME, intenviewName);
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

}

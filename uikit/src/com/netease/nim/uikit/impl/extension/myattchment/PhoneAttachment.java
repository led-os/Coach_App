package com.netease.nim.uikit.impl.extension.myattchment;

import com.alibaba.fastjson.JSONObject;
import com.netease.nim.uikit.impl.extension.CustomAttachment;
import com.netease.nim.uikit.impl.extension.CustomAttachmentType;

public class PhoneAttachment extends CustomAttachment {
    //data: { intenviewID: 求职意向id,  positionID: 职位ID，intenviewName :意向职位名称, ownMebile: 手机号}
    //ownMebile:自己手机号，intenviewID:求职意向id, positionD:职位id, intenviewName: 意向职位名称，
    private String title = "交换手机号";//  求职意向id
    private String ownMobile;// 自己手机号


    private static final String KEY_OWNMEBILE = "ownMobile";//ownMobile

    public PhoneAttachment() {
        super(CustomAttachmentType.Phone);
    }

    @Override
    protected void parseData(JSONObject data) {
        intenviewID = data.getString(KEY_INTENVIEWID);
        positionID = data.getString(KEY_POSITIONID);
        intenviewName = data.getString(KEY_INTENVIEWNAME);
        ownMobile = data.getString(KEY_OWNMEBILE);
    }

    @Override
    protected JSONObject packData() {
        JSONObject data = new JSONObject();
        data.put(KEY_INTENVIEWID, intenviewID);
        data.put(KEY_POSITIONID, positionID);
        data.put(KEY_INTENVIEWNAME, intenviewName);
        data.put(KEY_OWNMEBILE, ownMobile);
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

    public String getOwnMebile() {
        return ownMobile;
    }

    public void setOwnMebile(String ownMebile) {
        this.ownMobile = ownMebile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

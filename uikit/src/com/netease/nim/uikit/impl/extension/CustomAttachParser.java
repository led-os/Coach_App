package com.netease.nim.uikit.impl.extension;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netease.nim.uikit.impl.extension.myattchment.PhoneAgreeAttachment;
import com.netease.nim.uikit.impl.extension.myattchment.PhoneAttachment;
import com.netease.nim.uikit.impl.extension.myattchment.PhoneRefuseAttachment;
import com.netease.nim.uikit.impl.extension.myattchment.WechatAgreeAttachment;
import com.netease.nim.uikit.impl.extension.myattchment.WechatAttachment;
import com.netease.nim.uikit.impl.extension.myattchment.WechatRefuseAttachment;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachmentParser;

/**
 * Created by zhoujianghua on 2015/4/9.
 */
public class CustomAttachParser implements MsgAttachmentParser {

    private static final String KEY_TYPE = "type";
    private static final String KEY_DATA = "data";

    @Override
    public MsgAttachment parse(String json) {
        CustomAttachment attachment = null;
        try {
            JSONObject object = JSON.parseObject(json);
            int type = object.getInteger(KEY_TYPE);
            JSONObject data = object.getJSONObject(KEY_DATA);
            switch (type) {
                case CustomAttachmentType.link:
                    break;
                case CustomAttachmentType.Phone:
                    attachment = new PhoneAttachment();
                    break;
                case CustomAttachmentType.Phone_Agree:
                    attachment = new PhoneAgreeAttachment();
                    break;
                case CustomAttachmentType.Phone_refuse:
                    attachment = new PhoneRefuseAttachment();
                    break;
                case CustomAttachmentType.Wechat:
                    attachment = new WechatAttachment();
                    break;
                case CustomAttachmentType.Wechat_Agree:
                    attachment = new WechatAgreeAttachment();
                    break;
                case CustomAttachmentType.Wechat_refuse:
                    attachment = new WechatRefuseAttachment();
                    break;
                default:
                    attachment = new DefaultCustomAttachment();
                    break;
            }

            if (attachment != null) {
                attachment.fromJson(data);
            }
        } catch (Exception e) {

        }

        return attachment;
    }

    public static String packData(int type, JSONObject data) {
        JSONObject object = new JSONObject();
        object.put(KEY_TYPE, type);
        if (data != null) {
            object.put(KEY_DATA, data);
        }

        return object.toJSONString();
    }
}

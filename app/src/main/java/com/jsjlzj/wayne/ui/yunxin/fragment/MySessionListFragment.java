package com.jsjlzj.wayne.ui.yunxin.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.yunxin.YunXingUtil;
import com.netease.nim.uikit.impl.extension.CustomAttachment;
import com.netease.nim.uikit.impl.extension.CustomAttachmentType;
import com.jsjlzj.wayne.ui.yunxin.reminder.ReminderManager;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.netease.nim.uikit.business.recent.RecentContactsCallback;
import com.netease.nim.uikit.business.recent.RecentContactsFragment;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.RecentContact;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MySessionListFragment extends RecentContactsFragment {


    public static Fragment getInstance(boolean isTrainer) {
        MySessionListFragment fragment = new MySessionListFragment();
        fragment.setContainerId(R.id.messages_fragment);
        fragment.setTrainer(isTrainer);
        return fragment;
    }

    private MdlUser.MdlUserBean user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frgment_messagelist, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        user = MyApp.ME.user;
        callback = new RecentContactsCallback() {
            @Override
            public void onRecentContactsLoaded() {
                // 最近联系人列表加载完毕
            }

            @Override
            public void onUnreadCountChange(int unreadCount) {
                ReminderManager.getInstance().updateSessionUnreadNum(unreadCount);
            }

            //todo 消息跳转
            @Override
            public void onItemClick(RecentContact recent) {
                // 回调函数，以供打开会话窗口时传入定制化参数，或者做其他动作
                MsgAttachment attachment = recent.getAttachment();
                Map<String, Object> extension = recent.getExtension();
                LogAndToastUtil.log("" + attachment + attachment + "   " + extension);
                switch (recent.getSessionType()) {
                    case P2P:
                        if (user != null) {
                            if (attachment != null && attachment instanceof CustomAttachment) {
                                CustomAttachment customAttachment = (CustomAttachment) attachment;
                                YunXingUtil.toChatRoom(getContext(), recent.getContactId(),
                                        customAttachment.getIntenviewID(),
                                        customAttachment.getIntenviewName(),
                                        customAttachment.getPositionID(),
                                        isTrainer);
                            } else if (extension != null) {
                                YunXingUtil.toChatRoom(getContext(), recent.getContactId(),
                                        extension.get("intenviewID").toString(),
                                        extension.get("intenviewName").toString(),
                                        extension.get("positionID").toString(),
                                        isTrainer);
                            } else {
                                YunXingUtil.toChatRoom(getContext(), recent.getContactId(),
                                        null,
                                        null,
                                        null,
                                        isTrainer);
                            }
                        }
                        break;
                    default:
                        break;
                }
            }

            //todo 自定义消息消息
            @Override
            public String getDigestOfAttachment(RecentContact recentContact, MsgAttachment attachment) {
                // 设置自定义消息的摘要消息，展示在最近联系人列表的消息缩略栏上
                // 当然，你也可以自定义一些内建消息的缩略语，例如图片，语音，音视频会话等，自定义的缩略语会被优先使用。
                if (attachment instanceof CustomAttachment) {
                    CustomAttachment customAttachment = (CustomAttachment) attachment;
                    switch (customAttachment.getType()) {
                        case CustomAttachmentType.Phone:
                            return "[交换手机号]";
                        case CustomAttachmentType.Phone_Agree:
                            return "[同意交换手机号]";
                        case CustomAttachmentType.Phone_refuse:
                            return "[拒绝交换手机号]";
                        case CustomAttachmentType.Wechat:
                            return "[交换微信号]";
                        case CustomAttachmentType.Wechat_Agree:
                            return "[同意交换微信号]";
                        case CustomAttachmentType.Wechat_refuse:
                            return "[拒绝交换微信号]";
                    }
                }
                return null;
            }

            @Override
            public String getDigestOfTipMsg(RecentContact recent) {
                String msgId = recent.getRecentMessageId();
                List<String> uuids = new ArrayList<>(1);
                uuids.add(msgId);
                List<IMMessage> msgs = NIMClient.getService(MsgService.class).queryMessageListByUuidBlock(uuids);
                if (msgs != null && !msgs.isEmpty()) {
                    IMMessage msg = msgs.get(0);
                    Map<String, Object> content = msg.getRemoteExtension();
                    if (content != null && !content.isEmpty()) {
                        return (String) content.get("content");
                    }
                }

                return null;
            }
        };
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


}

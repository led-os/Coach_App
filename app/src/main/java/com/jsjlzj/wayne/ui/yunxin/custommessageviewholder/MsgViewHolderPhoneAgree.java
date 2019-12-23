package com.jsjlzj.wayne.ui.yunxin.custommessageviewholder;

import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jsjlzj.wayne.R;
import com.netease.nim.uikit.impl.extension.myattchment.PhoneAgreeAttachment;
import com.jsjlzj.wayne.utils.eventbus.EnumEventBus;
import com.jsjlzj.wayne.utils.eventbus.EventBusManager;
import com.jsjlzj.wayne.utils.eventbus.MdlEventBus;
import com.netease.nim.uikit.business.session.viewholder.MsgViewHolderBase;
import com.netease.nim.uikit.common.ui.recyclerview.adapter.BaseMultiItemFetchLoadAdapter;

public class MsgViewHolderPhoneAgree extends MsgViewHolderBase {
    private TextView tvItemPhoneAgree,
            btnSendItemPhone,
            btnCallItemPhoneAgree,
            btnCopeItemPhoneAgree;

    public MsgViewHolderPhoneAgree(BaseMultiItemFetchLoadAdapter adapter) {
        super(adapter);
    }

    @Override
    protected int getContentResId() {
        return R.layout.item_message_phone_agree;
    }

    // 是否显示头像，默认为显示
    protected boolean isShowHeadImage() {
        return false;
    }

    //是否显示名字
    protected boolean shouldDisplayNick() {
        return false;
    }
    @Override
    protected void inflateContentView() {
        tvItemPhoneAgree = findViewById(R.id.tvItemPhoneAgree);
        btnSendItemPhone = findViewById(R.id.btnSendItemPhone);
        btnCallItemPhoneAgree = findViewById(R.id.btnCallItemPhoneAgree);
        btnCopeItemPhoneAgree = findViewById(R.id.btnCopeItemPhoneAgree);
    }

    @Override
    protected void bindContentView() {
        if (!(message.getAttachment() instanceof PhoneAgreeAttachment)) return;
        PhoneAgreeAttachment attachment = (PhoneAgreeAttachment) message.getAttachment();
        if (!isReceivedMessage()) {// 消息方向，自己发送的
            tvItemPhoneAgree.setText("对方的手机号是:" + attachment.getOtherMebile());
        } else {
            tvItemPhoneAgree.setText("对方的手机号是:" + attachment.getOwnMebile());
        }

        btnSendItemPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBusManager.post(new MdlEventBus(EnumEventBus.MESSAGE_PHONE_AGREE_SEND, message));
            }
        });
        btnCallItemPhoneAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBusManager.post(new MdlEventBus(EnumEventBus.MESSAGE_PHONE_AGREE_CALL, message));
            }
        });
        btnCopeItemPhoneAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAgreeAttachment attachment = (PhoneAgreeAttachment) message.getAttachment();
                ClipboardManager cm = (ClipboardManager) v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
// 将文本内容放到系统剪贴板里。
                cm.setText(isReceivedMessage() ? attachment.getOwnMebile() : attachment.getOtherMebile());
                Toast.makeText(v.getContext(), "复制成功!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected int leftBackground() {
        return R.color.transparent;
    }

    @Override
    protected int rightBackground() {
        return R.color.transparent;
    }

    @Override
    protected void onItemClick() {
//        // 拆红包
//        RedPacketAttachment attachment = (RedPacketAttachment) message.getAttachment();
//        BaseMultiItemFetchLoadAdapter adapter = getAdapter();
//        ModuleProxy proxy = null;
//        if (adapter instanceof MsgAdapter) {
//            proxy = ((MsgAdapter) adapter).getContainer().proxy;
//        } else if (adapter instanceof ChatRoomMsgAdapter) {
//            proxy = ((ChatRoomMsgAdapter) adapter).getContainer().proxy;
//        }
    }
}

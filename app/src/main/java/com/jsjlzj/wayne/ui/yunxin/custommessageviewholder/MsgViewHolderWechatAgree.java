package com.jsjlzj.wayne.ui.yunxin.custommessageviewholder;

import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jsjlzj.wayne.R;
import com.netease.nim.uikit.impl.extension.myattchment.WechatAgreeAttachment;
import com.netease.nim.uikit.business.session.viewholder.MsgViewHolderBase;
import com.netease.nim.uikit.common.ui.recyclerview.adapter.BaseMultiItemFetchLoadAdapter;

public class MsgViewHolderWechatAgree extends MsgViewHolderBase {
    private TextView tvItemPhoneAgree,
            btnCopyItemWechatAgree;

    public MsgViewHolderWechatAgree(BaseMultiItemFetchLoadAdapter adapter) {
        super(adapter);
    }

    @Override
    protected int getContentResId() {
        return R.layout.item_message_wechat_agree;
    }
    // 是否显示头像，默认为显示
    protected boolean isShowHeadImage() {
        return false;
    }

    //是否显示名字
    protected boolean shouldDisplayNick() { return false; }
    @Override
    protected void inflateContentView() {
        tvItemPhoneAgree = findViewById(R.id.tvItemPhoneAgree);
        btnCopyItemWechatAgree = findViewById(R.id.btnCopyItemWechatAgree);
    }

    @Override
    protected void bindContentView() {
        WechatAgreeAttachment attachment = (WechatAgreeAttachment) message.getAttachment();
        if (!isReceivedMessage()) {// 消息方向，自己发送的
            tvItemPhoneAgree.setText("对方的微信号是:" + attachment.getOtherWeChat());
        } else {
            tvItemPhoneAgree.setText("对方的微信号是:" + attachment.getOwnWeChat());
        }
        btnCopyItemWechatAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WechatAgreeAttachment attachment = (WechatAgreeAttachment) message.getAttachment();
                ClipboardManager cm = (ClipboardManager) v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
// 将文本内容放到系统剪贴板里。
                cm.setText(isReceivedMessage() ? attachment.getOwnWeChat() : attachment.getOtherWeChat());
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

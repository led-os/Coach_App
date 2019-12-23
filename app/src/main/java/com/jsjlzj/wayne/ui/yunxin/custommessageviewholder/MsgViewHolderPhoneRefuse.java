package com.jsjlzj.wayne.ui.yunxin.custommessageviewholder;

import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.netease.nim.uikit.business.session.viewholder.MsgViewHolderBase;
import com.netease.nim.uikit.common.ui.recyclerview.adapter.BaseMultiItemFetchLoadAdapter;

public class MsgViewHolderPhoneRefuse extends MsgViewHolderBase {
    private String strMy="您已成功拒绝了对方交换电话的请求",strOther="对方已成功拒绝了您交换电话的请求";
    private TextView tvItemPhoneRefuse;

    public MsgViewHolderPhoneRefuse(BaseMultiItemFetchLoadAdapter adapter) {
        super(adapter);
    }

    @Override
    protected int getContentResId() {
        return R.layout.item_message_phone_refuse;
    }
    // 是否显示头像，默认为显示
    protected boolean isShowHeadImage() {
        return false;
    }

    //是否显示名字
    protected boolean shouldDisplayNick() { return false; }
    @Override
    protected void inflateContentView() {
        tvItemPhoneRefuse= findViewById(R.id.tvItemPhoneRefuse);
    }
    @Override
    protected void bindContentView() {
        if (!isReceivedMessage()) {// 消息方向，自己发送的
            tvItemPhoneRefuse.setText(strMy);
        } else {
            tvItemPhoneRefuse.setText(strOther);
        }
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

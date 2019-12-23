package com.jsjlzj.wayne.ui.yunxin.custommessageviewholder;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.utils.eventbus.EnumEventBus;
import com.jsjlzj.wayne.utils.eventbus.EventBusManager;
import com.jsjlzj.wayne.utils.eventbus.MdlEventBus;
import com.netease.nim.uikit.business.session.viewholder.MsgViewHolderBase;
import com.netease.nim.uikit.common.ui.recyclerview.adapter.BaseMultiItemFetchLoadAdapter;

public class MsgViewHolderWechat extends MsgViewHolderBase {
    private String strMy="我想要和您交换微信，您是否同意",strOther="对方想要和您交换微信，您是否同意";
    private ConstraintLayout llItemPhone;
    private TextView btnAgreeItemPhone, btnRefuseItemPhone,tvItemPhone,tvTishi;

    public MsgViewHolderWechat(BaseMultiItemFetchLoadAdapter adapter) {
        super(adapter);
    }

    @Override
    protected int getContentResId() {
        return R.layout.item_message_wechat;
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
        llItemPhone = findViewById(R.id.llItemPhone);
        btnAgreeItemPhone = findViewById(R.id.btnAgreeItemPhone);
        btnRefuseItemPhone = findViewById(R.id.btnRefuseItemPhone);
        tvItemPhone= findViewById(R.id.tvItemPhone);
        tvTishi= findViewById(R.id.tvTishi);
    }
    @Override
    protected void bindContentView() {
        if (!isReceivedMessage()) {// 消息方向，自己发送的
            tvItemPhone.setText(strMy);
            tvTishi.setVisibility(View.VISIBLE);
            llItemPhone.setVisibility(View.GONE);
        } else {
            tvTishi.setVisibility(View.GONE);
            llItemPhone.setVisibility(View.VISIBLE);
            tvItemPhone.setText(strOther);
            btnAgreeItemPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBusManager.post(new MdlEventBus(EnumEventBus.MESSAGE_WECHAT_AGREE,message));
                }
            });
            btnRefuseItemPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBusManager.post(new MdlEventBus(EnumEventBus.MESSAGE_WECHAT_REFUSE,message));
                }
            });
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

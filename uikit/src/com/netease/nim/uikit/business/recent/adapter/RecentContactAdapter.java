package com.netease.nim.uikit.business.recent.adapter;

import androidx.recyclerview.widget.RecyclerView;

import com.netease.nim.uikit.R;
import com.netease.nim.uikit.business.recent.RecentContactsCallback;
import com.netease.nim.uikit.business.recent.holder.CommonRecentViewHolder;
import com.netease.nim.uikit.business.recent.holder.SuperTeamRecentViewHolder;
import com.netease.nim.uikit.business.recent.holder.TeamRecentViewHolder;
import com.netease.nim.uikit.common.ui.recyclerview.adapter.BaseMultiItemQuickAdapter;
import com.netease.nim.uikit.common.ui.recyclerview.holder.BaseViewHolder;
import com.netease.nim.uikit.impl.extension.CustomAttachment;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.netease.nimlib.sdk.uinfo.UserService;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by huangjun on 2016/12/11.
 */

public class RecentContactAdapter extends BaseMultiItemQuickAdapter<RecentContact, BaseViewHolder> {

    interface ViewType {
        int VIEW_TYPE_COMMON = 1;
        int VIEW_TYPE_TEAM = 2;
        int VIEW_TYPE_SUPER_TEAM = 3;
    }

    private RecentContactsCallback callback;

    public RecentContactAdapter(RecyclerView recyclerView, List<RecentContact> data) {
        super(recyclerView, data);
        addItemType(ViewType.VIEW_TYPE_COMMON, R.layout.nim_recent_contact_list_item, CommonRecentViewHolder.class);
        addItemType(ViewType.VIEW_TYPE_TEAM, R.layout.nim_recent_contact_list_item, TeamRecentViewHolder.class);
        addItemType(ViewType.VIEW_TYPE_SUPER_TEAM, R.layout.nim_recent_contact_list_item, SuperTeamRecentViewHolder.class);
    }

    public RecentContactAdapter(RecyclerView recyclerView, List<RecentContact> data, boolean isTrainer) {
        super(recyclerView, data);
        this.isTrainer = isTrainer;
        addItemType(ViewType.VIEW_TYPE_COMMON, R.layout.nim_recent_contact_list_item, CommonRecentViewHolder.class);
        addItemType(ViewType.VIEW_TYPE_TEAM, R.layout.nim_recent_contact_list_item, TeamRecentViewHolder.class);
        addItemType(ViewType.VIEW_TYPE_SUPER_TEAM, R.layout.nim_recent_contact_list_item, SuperTeamRecentViewHolder.class);
    }


    @Override
    protected int getViewType(RecentContact item) {
        return item.getSessionType() == SessionTypeEnum.Team ? ViewType.VIEW_TYPE_TEAM : ViewType.VIEW_TYPE_COMMON;
    }

    @Override
    protected String getItemKey(RecentContact item) {
        StringBuilder sb = new StringBuilder();
        if (isTrainer) {
            NimUserInfo user = NIMClient.getService(UserService.class).getUserInfo(item.getFromAccount());
            if (user != null && null != user.getExtensionMap()) {
                Map<String, Object> map = user.getExtensionMap();
                String storeName = (String) map.get("storeName");
                String position = (String) map.get("position");
                sb.append(storeName+"_"+position);
            }
        } else {
            if (item.getMsgType() == MsgTypeEnum.custom) {
                CustomAttachment customAttachment = (CustomAttachment) item.getAttachment();
                sb.append( customAttachment.getIntenviewName());
            }
        }
        return sb.toString();
    }

    public RecentContactsCallback getCallback() {
        return callback;
    }

    public void setCallback(RecentContactsCallback callback) {
        this.callback = callback;
    }
}

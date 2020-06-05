package com.jsjlzj.wayne.ui.yunxin.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.MyPermissionConstant;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.message.MdlIsBothReply;
import com.jsjlzj.wayne.ui.AppManager;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MyBaseMessageActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizelogin.LoginActivityPresenter;
import com.jsjlzj.wayne.ui.mvp.relizelogin.LoginActivityView;
import com.jsjlzj.wayne.ui.store.personal.storeinfo.InterviewActivity;
import com.jsjlzj.wayne.ui.trainer.personal.PositionPreviewActivity;
import com.jsjlzj.wayne.ui.trainer.personal.PositionPreviewNewActivity;
import com.jsjlzj.wayne.ui.trainer.publicac.PositionInfoStoreActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.eventbus.EnumEventBus;
import com.jsjlzj.wayne.utils.eventbus.EventBusManager;
import com.jsjlzj.wayne.utils.eventbus.MdlEventBus;
import com.jsjlzj.wayne.utils.permission.MyPermissionResultListener;
import com.jsjlzj.wayne.utils.permission.PermissionUtil;
import com.jsjlzj.wayne.widgets.dialog.CommonPhoneDialog;
import com.jsjlzj.wayne.widgets.dialog.CommonWechatDialog;
import com.jsjlzj.wayne.widgets.dialog.MessageCloseDialog;
import com.jsjlzj.wayne.widgets.dialog.MessageStoreCloseDialog;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.api.model.contact.ContactChangedObserver;
import com.netease.nim.uikit.api.model.main.OnlineStateChangeObserver;
import com.netease.nim.uikit.api.model.session.SessionCustomization;
import com.netease.nim.uikit.api.model.user.UserInfoObserver;
import com.netease.nim.uikit.business.session.constant.Extras;
import com.netease.nim.uikit.business.session.fragment.MessageFragment;
import com.netease.nim.uikit.business.session.module.list.MyMsgListener;
import com.netease.nim.uikit.business.session.module.list.MyMsgSendListener;
import com.netease.nim.uikit.business.uinfo.UserInfoHelper;
import com.netease.nim.uikit.common.activity.ToolBarOptions;
import com.netease.nim.uikit.impl.NimUIKitImpl;
import com.netease.nim.uikit.impl.extension.CustomAttachment;
import com.netease.nim.uikit.impl.extension.CustomAttachmentType;
import com.netease.nim.uikit.impl.extension.myattchment.PhoneAgreeAttachment;
import com.netease.nim.uikit.impl.extension.myattchment.PhoneAttachment;
import com.netease.nim.uikit.impl.extension.myattchment.PhoneRefuseAttachment;
import com.netease.nim.uikit.impl.extension.myattchment.WechatAgreeAttachment;
import com.netease.nim.uikit.impl.extension.myattchment.WechatAttachment;
import com.netease.nim.uikit.impl.extension.myattchment.WechatRefuseAttachment;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.MsgServiceObserve;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;
import com.netease.nimlib.sdk.msg.constant.MsgDirectionEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.CustomNotification;
import com.netease.nimlib.sdk.msg.model.IMMessage;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 点对点聊天界面
 * <p/>
 * Created by huangjun on 2015/2/1.
 */
public class P2PMessageActivity2 extends MyBaseMessageActivity<LoginActivityView, LoginActivityPresenter> implements LoginActivityView, MyPermissionResultListener {
    private boolean isResume = false;
    private boolean isTrainer = false;
    private boolean bothReply = false;
    private static String intenviewID = "1";//  求职意向id
    private static String positionID = "1";//  职位ID
    private static String intenviewName = "1";// 意向职位名称
    //"yunXinAccount":"devtrainer_100057","yunXinToken":"f2352149d6fc2b23fae8f8f763ac31ed" 18000000000

    public static void start(Context context, String contactId, SessionCustomization customization, IMMessage anchor,
                             String intenviewID1, String intenviewName1, String positionID1,
                             boolean isTrainer) {
        Intent intent = new Intent();
        intent.putExtra(Extras.EXTRA_ACCOUNT, contactId);
        intent.putExtra(Extras.EXTRA_CUSTOMIZATION, customization);
        intenviewID = intenviewID1;
        intenviewName = intenviewName1;
        positionID = positionID1;
        intent.putExtra("isTrainer", isTrainer);
        intent.putExtra("intenviewID", intenviewID1);
        intent.putExtra("intenviewName", intenviewName1);
        intent.putExtra("positionID", positionID1);
        if (anchor != null) {
            intent.putExtra(Extras.EXTRA_ANCHOR, anchor);
        }
        intent.setClass(context, P2PMessageActivity2.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        context.startActivity(intent);
    }

    private TextView toolbarTitle;
    private String strTitle;

    @Override
    protected LoginActivityPresenter createPresenter() {
        return new LoginActivityPresenter(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_message_1;
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    public void setToolBar(int toolBarId, ToolBarOptions options) {
        if (toolbarTitle == null) toolbarTitle = findView(R.id.toolbarTitle);
        if (options.titleId != 0) {
            strTitle = getText(options.titleId).toString();
            toolbarTitle.setText(strTitle);
        }
        if (!TextUtils.isEmpty(options.titleString)) {
            strTitle = options.titleString;
            toolbarTitle.setText(strTitle);
        }
    }

    public void setToolBar(int toolbarId, int titleId, int logoId) {
        if (toolbarTitle == null) toolbarTitle = findView(R.id.toolbarTitle);
        strTitle = getText(titleId).toString();
        toolbarTitle.setText(strTitle);
    }

    @Override
    public void setTitle(CharSequence title) {
        if (toolbarTitle == null) toolbarTitle = findView(R.id.toolbarTitle);
        strTitle = title.toString();
        toolbarTitle.setText(strTitle);
    }

    private MdlUser.MdlUserBean user;

    public void setSubTitle(String subTitle) {
        if (toolbarTitle == null) toolbarTitle = findView(R.id.toolbarTitle);
        strTitle = subTitle;
        toolbarTitle.setText(strTitle);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBusManager.register(this);
        AppManager.getAppManager().addActivity(this);
        AppManager.getAppManager().addActivity(this);
        user = MyApp.user;
        intenviewID = getIntent().getStringExtra("intenviewID");
        positionID = getIntent().getStringExtra("positionID");
        intenviewName = getIntent().getStringExtra("intenviewName");

        isTrainer = getIntent().getBooleanExtra("isTrainer", false);
        // 单聊特例话数据，包括个人信息，
        requestBuddyInfo();
        displayOnlineState();
        registerObservers(true);
        findView(R.id.btnBack).setOnClickListener(clickListener);
        findView(R.id.btnMore).setOnClickListener(clickListener);
        if (isTrainer) {
            findView(R.id.layoutBtnTrainer).setVisibility(View.VISIBLE);
            findView(R.id.layoutBtnStore).setVisibility(View.GONE);


            findView(R.id.btnTrainerPhone).setOnClickListener(clickListener);
            findView(R.id.btnTrainerWeChat).setOnClickListener(clickListener);
            findView(R.id.btnTrainerPositionInfo).setOnClickListener(clickListener);
            findView(R.id.btnTrainerClose).setOnClickListener(clickListener);

            findView(R.id.btnTrainerPhone2).setOnClickListener(clickListener);
            findView(R.id.btnTrainerWeChat2).setOnClickListener(clickListener);
            findView(R.id.btnTrainerPositionInfo2).setOnClickListener(clickListener);
            findView(R.id.btnTrainerClose2).setOnClickListener(clickListener);
        } else {
            findView(R.id.layoutBtnTrainer).setVisibility(View.GONE);
            findView(R.id.layoutBtnStore).setVisibility(View.VISIBLE);

            findView(R.id.btnStorePhone).setOnClickListener(clickListener);
            findView(R.id.btnStoreWeChat).setOnClickListener(clickListener);
            findView(R.id.btnStoreDarencard).setOnClickListener(clickListener);
            findView(R.id.btnStoreMianshi).setOnClickListener(clickListener);
            findView(R.id.btnStoreClose).setOnClickListener(clickListener);

            findView(R.id.btnStorePhone2).setOnClickListener(clickListener);
            findView(R.id.btnStoreWeChat2).setOnClickListener(clickListener);
            findView(R.id.btnStoreDarencard2).setOnClickListener(clickListener);
            findView(R.id.btnStoreMianshi2).setOnClickListener(clickListener);
            findView(R.id.btnStoreClose2).setOnClickListener(clickListener);
        }
//        if (intenviewID != null) getIsbothreply();
    }

    private Map<String, Object> mapIsbothreply = new HashMap<>();

    private void getIsbothreply() {
        mapIsbothreply.put("positionId", positionID);
        mapIsbothreply.put("workHopeId", intenviewID);
        presenter.messageIsbothreply(mapIsbothreply);
    }

    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnTrainerPhone://教练 交换电话
                case R.id.btnTrainerPhone2://教练 交换电话
                {
                    if (!bothReply) {
                        getIsbothreply();
                        return;
                    }
                    if (user == null) {
                        LogAndToastUtil.toast(view.getContext(), "用户信息不全,无法发送此类消息");
                        return;
                    }
                    showCommonPhoneDialog(new CommonPhoneDialog.ClickListener() {
                        @Override
                        public void clickConfirm() {
                            PhoneAttachment attachment = new PhoneAttachment();
                            attachment.setIntenviewID(intenviewID);
                            attachment.setIntenviewName(intenviewName);
                            attachment.setPositionID(positionID);
                            attachment.setOwnMebile(user.getMobile());
                            // 以下 "图文链接：" + attachment.getTitle() 用来显示app消息推送时，图片显示的内容。
                            IMMessage message = MessageBuilder.createCustomMessage(sessionId, SessionTypeEnum.P2P, attachment.getTitle(), attachment);
                            messageFragment.sendMessage2(message);
                        }
                    });
                }
                break;
                case R.id.btnTrainerWeChat://教练 交换微信
                case R.id.btnTrainerWeChat2://教练 交换微信
                {
                    if (!bothReply) {
                        getIsbothreply();
                        return;
                    }
                    if (user == null) {
                        LogAndToastUtil.toast(view.getContext(), "用户信息不全,无法发送此类消息");
                        return;
                    }
                    if (TextUtils.isEmpty(user.getWxId())) {
                        showCommonWechatDialog(new CommonWechatDialog.ClickListener() {
                            @Override
                            public void clickConfirm(String wechat) {
                                if (!TextUtils.isEmpty(wechat)) {
                                    WechatAttachment attachment = new WechatAttachment();
                                    attachment.setIntenviewID(intenviewID);
                                    attachment.setIntenviewName(intenviewName);
                                    attachment.setPositionID(positionID);
                                    attachment.setOwnWeChat(wechat);
                                    IMMessage message = MessageBuilder.createCustomMessage(sessionId, SessionTypeEnum.P2P, attachment.getTitle(), attachment);
                                    messageFragment.sendMessage2(message);
                                    saveWechat(wechat);
                                }
                            }
                        });
                    } else {
                        WechatAttachment attachment = new WechatAttachment();
                        attachment.setIntenviewID(intenviewID);
                        attachment.setIntenviewName(intenviewName);
                        attachment.setPositionID(positionID);
                        attachment.setOwnWeChat(user.getWxId());
                        IMMessage message = MessageBuilder.createCustomMessage(sessionId, SessionTypeEnum.P2P, attachment.getTitle(), attachment);
                        messageFragment.sendMessage2(message);
                    }
                }
                break;
                case R.id.btnTrainerPositionInfo://教练 职位信息
                case R.id.btnTrainerPositionInfo2://教练 职位信息
                    PositionInfoStoreActivity.go2this(P2PMessageActivity2.this, intenviewID);
                    break;
                case R.id.btnTrainerClose://教练 不敢兴趣
                case R.id.btnTrainerClose2://教练 不敢兴趣
                {
                    new MessageCloseDialog(P2PMessageActivity2.this,
                            "对该门店设置不感兴趣，三个月内系统不再通知他发来的任何消息，您可以在聊天中设置取消设置",
                            new MessageCloseDialog.ClickListener() {
                                @Override
                                public void clickConfirm() {
                                    Map<String, Object> map = new HashMap<>();
                                    map.put("positionId", positionID);
                                    map.put("workHopeId", intenviewID);
                                    presenter.messagePosistionUnsuitable(map);
//                                    YunXingUtil.addToBlackList(sessionId);
                                }
                            }).show();
                    break;
                }
                case R.id.btnStorePhone://门店 交换电话
                case R.id.btnStorePhone2://门店 交换电话
                {
                    if (!bothReply) {
                        getIsbothreply();
                        return;
                    }
                    if (user == null) {
                        LogAndToastUtil.toast(view.getContext(), "用户信息不全,无法发送此类消息");
                        return;
                    }
                    showCommonPhoneDialog(new CommonPhoneDialog.ClickListener() {
                        @Override
                        public void clickConfirm() {
                            PhoneAttachment attachment = new PhoneAttachment();
                            attachment.setIntenviewID(intenviewID);
                            attachment.setIntenviewName(intenviewName);
                            attachment.setPositionID(positionID);
                            attachment.setOwnMebile(user.getMobile());
                            // 以下 "图文链接：" + attachment.getTitle() 用来显示app消息推送时，图片显示的内容。
                            IMMessage message = MessageBuilder.createCustomMessage(sessionId, SessionTypeEnum.P2P, attachment.getTitle(), attachment);
                            messageFragment.sendMessage2(message);
                        }
                    });
                }
                break;
                case R.id.btnStoreWeChat://门店 交换微信
                case R.id.btnStoreWeChat2://门店 交换微信
                {
                    if (!bothReply) {
                        getIsbothreply();
                        return;
                    }
                    if (user == null) {
                        LogAndToastUtil.toast(view.getContext(), "用户信息不全,无法发送此类消息");
                        return;
                    }
                    if (TextUtils.isEmpty(user.getWxId())) {
                        showCommonWechatDialog(new CommonWechatDialog.ClickListener() {
                            @Override
                            public void clickConfirm(String wechat) {
                                if (!TextUtils.isEmpty(wechat)) {
                                    WechatAttachment attachment = new WechatAttachment();
                                    attachment.setIntenviewID(intenviewID);
                                    attachment.setIntenviewName(intenviewName);
                                    attachment.setPositionID(positionID);
                                    attachment.setOwnWeChat(wechat);
                                    IMMessage message = MessageBuilder.createCustomMessage(sessionId, SessionTypeEnum.P2P, attachment.getTitle(), attachment);
                                    messageFragment.sendMessage2(message);
                                    saveWechat(wechat);
                                }
                            }
                        });
                    } else {
                        WechatAttachment attachment = new WechatAttachment();
                        attachment.setIntenviewID(intenviewID);
                        attachment.setIntenviewName(intenviewName);
                        attachment.setPositionID(positionID);
                        attachment.setOwnWeChat(user.getWxId());
                        IMMessage message = MessageBuilder.createCustomMessage(sessionId, SessionTypeEnum.P2P, attachment.getTitle(), attachment);
                        messageFragment.sendMessage2(message);
                    }
                }
                break;
                case R.id.btnStoreDarencard://门店 达人卡
                case R.id.btnStoreDarencard2://门店 达人卡
                    PositionPreviewNewActivity.go2this(P2PMessageActivity2.this, intenviewID);
                    break;
                case R.id.btnStoreMianshi://门店 面试
                case R.id.btnStoreMianshi2://门店 面试
                    InterviewActivity.go2this(P2PMessageActivity2.this, intenviewID, intenviewName, positionID,sessionId);
                    break;
                case R.id.btnStoreClose://门店 不合适
                    if (MyApp.mdlDict == null || MyApp.mdlDict.getCv_unsuitable() == null || MyApp.mdlDict.getCv_unsuitable().getItems().size() <= 0)
                        return;
                    new MessageStoreCloseDialog(P2PMessageActivity2.this, new MessageStoreCloseDialog.ClickListener() {
                        @Override
                        public void clickConfirm(int index) {
                            Map<String, Object> map3 = new HashMap<>();
                            Map<String, Object> map4 = new HashMap<>();
                            map4.put("code", "1");
                            map4.put("name", "学历不符");
                            map4.put("items", null);
                            map3.put("cvUnsuitableCode", MyApp.mdlDict.getCv_unsuitable().getItems().get(0));
                            map3.put("positionId", positionID);
                            map3.put("workHopeId", intenviewID);
                            presenter.messageCVUnsuitable(map3);
//                            YunXingUtil.addToBlackList(sessionId);
                        }
                    }).show();
                    break;
                case R.id.btnMore://更多
                    break;
                case R.id.btnBack://返回
                    finish();
                    break;
            }
        }
    }

    private void showCommonWechatDialog(CommonWechatDialog.ClickListener listener) {
        new CommonWechatDialog(this, listener).show();
    }

    private void showCommonPhoneDialog(CommonPhoneDialog.ClickListener listener) {
        new CommonPhoneDialog(this, listener).show();
    }

    private String strPhone;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MdlEventBus event) {
        if (!(event.data instanceof IMMessage)) return;
        IMMessage message = (IMMessage) event.data;
        switch (event.eventType) {
            case EnumEventBus.MESSAGE_SEND_MESSAGE:
                break;
            case EnumEventBus.MESSAGE_LINK:
                break;
            case EnumEventBus.MESSAGE_PHONE_AGREE: {
                PhoneAttachment attachment1 = (PhoneAttachment) message.getAttachment();
                if (message.getDirect() == MsgDirectionEnum.In) {
                    if (user == null) {
                        return;
                    }
                    PhoneAgreeAttachment attachment = new PhoneAgreeAttachment();
                    attachment.setIntenviewID(intenviewID);
                    attachment.setIntenviewName(intenviewName);
                    attachment.setPositionID(positionID);
                    attachment.setOwnMebile(user.getMobile());
                    attachment.setOtherMebile(attachment1.getOwnMebile() + "");
                    IMMessage message2 = MessageBuilder.createCustomMessage(sessionId, SessionTypeEnum.P2P, attachment.getTitle(), attachment);
                    messageFragment.sendMessage2(message2);
                }
            }
            break;
            case EnumEventBus.MESSAGE_PHONE_REFUSE: {
                PhoneRefuseAttachment attachment = new PhoneRefuseAttachment();
                attachment.setIntenviewID(intenviewID);
                attachment.setIntenviewName(intenviewName);
                attachment.setPositionID(positionID);
                IMMessage message2 = MessageBuilder.createCustomMessage(sessionId, SessionTypeEnum.P2P, attachment.getTitle(), attachment);
                messageFragment.sendMessage2(message2);
            }
            break;
            case EnumEventBus.MESSAGE_PHONE_AGREE_SEND: {
                PhoneAgreeAttachment phoneAgreeAttachment = (PhoneAgreeAttachment) message.getAttachment();
                if (message.getDirect() == MsgDirectionEnum.In)//别人发的
                {
                    strPhone = phoneAgreeAttachment.getOwnMebile();
                } else {
                    strPhone = phoneAgreeAttachment.getOtherMebile();
                }
                clickSendPhone();
            }
            break;
            case EnumEventBus.MESSAGE_PHONE_AGREE_CALL: {
                PhoneAgreeAttachment phoneAgreeAttachment = (PhoneAgreeAttachment) message.getAttachment();
                if (message.getDirect() == MsgDirectionEnum.In)//别人发的
                {
                    strPhone = phoneAgreeAttachment.getOwnMebile();
                } else {
                    strPhone = phoneAgreeAttachment.getOtherMebile();
                }
                clickCallPhone();
            }
            break;
            case EnumEventBus.MESSAGE_WECHAT_AGREE: {
                WechatAttachment attachment1 = (WechatAttachment) message.getAttachment();
                if (message.getDirect() == MsgDirectionEnum.In) {
                    if (user == null)
                        return;
                    if (TextUtils.isEmpty(user.getWxId())) {
                        showCommonWechatDialog(new CommonWechatDialog.ClickListener() {
                            @Override
                            public void clickConfirm(String wechat) {
                                if (!TextUtils.isEmpty(wechat)) {
                                    WechatAgreeAttachment attachment = new WechatAgreeAttachment();
                                    attachment.setIntenviewID(intenviewID);
                                    attachment.setIntenviewName(intenviewName);
                                    attachment.setPositionID(positionID);
                                    attachment.setOwnWeChat(wechat);
                                    attachment.setOtherWeChat(attachment1.getOwnWeChat());
                                    IMMessage message2 = MessageBuilder.createCustomMessage(sessionId, SessionTypeEnum.P2P, attachment.getTitle(), attachment);
                                    messageFragment.sendMessage2(message2);
                                    saveWechat(wechat);
                                }
                            }
                        });
                    } else {
                        WechatAgreeAttachment attachment = new WechatAgreeAttachment();
                        attachment.setIntenviewID(intenviewID);
                        attachment.setIntenviewName(intenviewName);
                        attachment.setPositionID(positionID);
                        attachment.setOwnWeChat(user.getWxId());
                        attachment.setOtherWeChat(attachment1.getOwnWeChat());
                        IMMessage message2 = MessageBuilder.createCustomMessage(sessionId, SessionTypeEnum.P2P, attachment.getTitle(), attachment);
                        messageFragment.sendMessage2(message2);
                    }
                }
            }
            break;
            case EnumEventBus.MESSAGE_WECHAT_REFUSE: {
                WechatRefuseAttachment attachment = new WechatRefuseAttachment();
                attachment.setIntenviewID(intenviewID);
                attachment.setIntenviewName(intenviewName);
                attachment.setPositionID(positionID);
                IMMessage message2 = MessageBuilder.createCustomMessage(sessionId, SessionTypeEnum.P2P, attachment.getTitle(), attachment);
                messageFragment.sendMessage2(message2);
            }
            break;
        }
    }


    private void clickCallPhone() {
        PermissionUtil.checkPermission(
                this,
                MyPermissionConstant.CALL_PHONE,
                Manifest.permission.CALL_PHONE);
    }

    private void clickSendPhone() {
        PermissionUtil.checkPermission(
                this,
                MyPermissionConstant.SEND_PHONE,
                Manifest.permission.CALL_PHONE);
    }

    Map<String, Object> mapWechat;

    private void saveWechat(String wxId) {
        if (mapWechat == null) mapWechat = new HashMap<>();
        mapWechat.put("wxId", positionID);
        presenter.messageSaveWechat(mapWechat);
    }

    @Override
    public void permissionSuccess(int permissionReqCode) {
        switch (permissionReqCode) {
            case MyPermissionConstant.CALL_PHONE:
                Intent intent = new Intent();
                //设置拨打电话的动作
                intent.setAction(Intent.ACTION_DIAL);//ACTION_DIAL  ACTION_CALL
                //设置拨打电话的号码
                intent.setData(Uri.parse("tel:" + strPhone));
                //开启打电话的意图
                startActivity(intent);
                break;
            case MyPermissionConstant.SEND_PHONE:
                Intent intent1 = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + strPhone));
                intent1.putExtra("sms_body", " ");
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void permissionFail(int permissionReqCode) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        registerObservers(false);
        AppManager.getAppManager().finishActivity(this);
        EventBusManager.unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isResume = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isResume = false;
    }

    private void requestBuddyInfo() {
        setTitle(UserInfoHelper.getUserTitleName(sessionId, SessionTypeEnum.P2P));
    }

    private void displayOnlineState() {
        if (!NimUIKitImpl.enableOnlineState()) {
            return;
        }
        String detailContent = NimUIKitImpl.getOnlineStateContentProvider().getDetailDisplay(sessionId);
        setSubTitle(detailContent);
    }


    /**
     * 命令消息接收观察者
     */
    private Observer<CustomNotification> commandObserver = new Observer<CustomNotification>() {
        @Override
        public void onEvent(CustomNotification message) {
            if (!sessionId.equals(message.getSessionId()) || message.getSessionType() != SessionTypeEnum.P2P) {
                return;
            }
            showCommandMessage(message);
        }
    };


    /**
     * 用户信息变更观察者
     */
    private UserInfoObserver userInfoObserver = new UserInfoObserver() {
        @Override
        public void onUserInfoChanged(List<String> accounts) {
            if (!accounts.contains(sessionId)) {
                return;
            }
            requestBuddyInfo();
        }
    };

    /**
     * 好友资料变更（eg:关系）
     */
    private ContactChangedObserver friendDataChangedObserver = new ContactChangedObserver() {
        @Override
        public void onAddedOrUpdatedFriends(List<String> accounts) {
            setTitle(UserInfoHelper.getUserTitleName(sessionId, SessionTypeEnum.P2P));
        }

        @Override
        public void onDeletedFriends(List<String> accounts) {
            setTitle(UserInfoHelper.getUserTitleName(sessionId, SessionTypeEnum.P2P));
        }

        @Override
        public void onAddUserToBlackList(List<String> account) {
            setTitle(UserInfoHelper.getUserTitleName(sessionId, SessionTypeEnum.P2P));
        }

        @Override
        public void onRemoveUserFromBlackList(List<String> account) {
            setTitle(UserInfoHelper.getUserTitleName(sessionId, SessionTypeEnum.P2P));
        }
    };

    /**
     * 好友在线状态观察者
     */
    private OnlineStateChangeObserver onlineStateChangeObserver = new OnlineStateChangeObserver() {
        @Override
        public void onlineStateChange(Set<String> accounts) {
            if (!accounts.contains(sessionId)) {
                return;
            }
            // 按照交互来展示
            displayOnlineState();
        }
    };

    private void registerObservers(boolean register) {
        NIMClient.getService(MsgServiceObserve.class).observeCustomNotification(commandObserver, register);
        NimUIKit.getUserInfoObservable().registerObserver(userInfoObserver, register);
        NimUIKit.getContactChangedObservable().registerObserver(friendDataChangedObserver, register);
        if (NimUIKit.enableOnlineState()) {
            NimUIKit.getOnlineStateChangeObservable().registerOnlineStateChangeListeners(onlineStateChangeObserver, register);
        }
    }


    protected void showCommandMessage(CustomNotification message) {
        if (!isResume) {
            return;
        }
        String content = message.getContent();
        try {
            JSONObject json = JSON.parseObject(content);
            int id = json.getIntValue("id");
            if (id == 1) {
                // 正在输入
//                ToastHelper.showToastLong(P2PMessageActivity2.this, "对方正在输入...");
            } else {
//                ToastHelper.showToast(P2PMessageActivity2.this, "command: " + content);
            }
        } catch (Exception ignored) {

        }
    }

    private Map<String, Object> mapSendMessage;


    @Override
    protected MessageFragment fragment() {
        Bundle arguments = getIntent().getExtras();
        arguments.putSerializable(Extras.EXTRA_TYPE, SessionTypeEnum.P2P);
        MessageFragment fragment = new MessageFragment(intenviewID, positionID, intenviewName, new MyMsgListener() {
            @Override
            public void onCall(IMMessage imMessage) {
                if (imMessage != null) {
                    MsgAttachment attachment1 = imMessage.getAttachment();
                    if (attachment1 != null) {
                        if (attachment1 instanceof CustomAttachment) {
                            CustomAttachment customAttachment = (CustomAttachment) attachment1;
                            switch (customAttachment.getType()) {
                                case CustomAttachmentType.link:
                                case CustomAttachmentType.Phone:
                                case CustomAttachmentType.Phone_Agree:
                                case CustomAttachmentType.Phone_refuse:
                                case CustomAttachmentType.Wechat:
                                case CustomAttachmentType.Wechat_Agree:
                                case CustomAttachmentType.Wechat_refuse:
                                    setTile(customAttachment.getIntenviewID(), customAttachment.getIntenviewName(), customAttachment.getPositionID());
                                    break;
                            }
                        } else {
                            setTile(imMessage.getRemoteExtension());
                        }
                    } else {
                        setTile(imMessage.getRemoteExtension());
                    }

                }
            }
        },
                new MyMsgSendListener() {
                    @Override
                    public void onSend() {
                        if (mapSendMessage == null) mapSendMessage = new HashMap<>();
                        mapSendMessage.put("positionId", positionID);
                        mapSendMessage.put("workHopeId", intenviewID);
                        presenter.messageSaveMessage(mapSendMessage);
                    }
                });
        fragment.setArguments(arguments);
        fragment.setContainerId(R.id.message_fragment_container);
        return fragment;
    }

    private void setTile(String intenviewID, String intenviewName, String positionID) {
        if (!TextUtils.isEmpty(intenviewName)) {
            this.intenviewName = intenviewName;
            toolbarTitle.setText(strTitle + " " + intenviewName);
            if (messageFragment != null) messageFragment.setIntenviewName(intenviewName);
        }
        if (!TextUtils.isEmpty(intenviewID)) {
            this.intenviewID = intenviewID;
            if (messageFragment != null) messageFragment.setIntenviewID(intenviewID);
        }
        if (!TextUtils.isEmpty(positionID)) {
            this.positionID = positionID;
            if (messageFragment != null) messageFragment.setPositionID(positionID);
        }
    }

    private void setTile(Map<String, Object> remoteExtension) {
        if (remoteExtension == null) return;
        if (remoteExtension.containsKey("intenviewName")) {
            this.intenviewName = remoteExtension.get("intenviewName") + "";
            toolbarTitle.setText(strTitle + " " + intenviewName);
            if (messageFragment != null) messageFragment.setIntenviewName(intenviewName);
        }
        if (remoteExtension.containsKey("intenviewID")) {
            this.intenviewID = remoteExtension.get("intenviewID") + "";
            if (messageFragment != null) messageFragment.setIntenviewID(intenviewID);
        }
        if (remoteExtension.containsKey("positionID")) {
            this.positionID = remoteExtension.get("positionID") + "";
            if (messageFragment != null) messageFragment.setPositionID(positionID);
        }

    }

    @Override
    protected boolean enableSensor() {
        return true;
    }


    @Override
    public void messageIsbothreply(MdlBaseHttpResp<MdlIsBothReply> resp) {
        if (resp != null && resp.data != null && resp.getData().getData() != null) {
            bothReply = resp.getData().getData().getBothReply() == 1;
            if (resp.getData().getData().getBothReply() == 1) {
                if (isTrainer) {
                    findView(R.id.btnTrainerPhone).setSelected(isTrainer);
                    findView(R.id.btnTrainerWeChat).setSelected(isTrainer);
                } else {
                    findView(R.id.btnStorePhone).setSelected(isTrainer);
                    findView(R.id.btnStoreWeChat).setSelected(isTrainer);
                }
            } else {
                LogAndToastUtil.toast(getApplicationContext(), "双方回复后才能操作");
                bothReply = false;
            }
        }
    }

    @Override
    public void messageCVUnsuitable(MdlBaseHttpResp resp) {
        finish();
    }

    @Override
    public void messagePosistionUnsuitable(MdlBaseHttpResp resp) {
        finish();
    }
}

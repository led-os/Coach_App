package com.jsjlzj.wayne.ui.yunxin;

import android.content.Context;

import com.jsjlzj.wayne.ui.yunxin.fragment.P2PMessageActivity2;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.business.session.module.MsgForwardFilter;
import com.netease.nim.uikit.business.session.module.MsgRevokeFilter;
import com.netease.nim.uikit.impl.customization.DefaultP2PSessionCustomization;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.RequestCallbackWrapper;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.friend.FriendService;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.uinfo.UserService;
import com.netease.nimlib.sdk.uinfo.constant.UserInfoFieldEnum;

import java.util.HashMap;
import java.util.Map;

public class YunXingUtil {
    //302
//token 错误或者账号不存在都会导致 302 错误码。
//这种情况一般发生于用户在其他设备上修改了密码。
//PWD_ERROR
//408
//1、连接建立成功，SDK 发出登录请求后网易云通信服务器一直
//没有响应，那么 30s 后将导致登录超时。
//2、登录成功之前，调用服务器相关请求接口
//（由于与网易云通信服务器连接尚未建立成功，会导致发包超时）
//UNLOGIN
//415
//网络断开或者与网易云通信服务器建立连接失败
//NET_BROKEN
//416
//请求过频错误，
//为了防止开发者错误的调用导致服务器压力过大， SDK 做了频控限制，
//并有一分钟的惩罚时间，过了惩罚时间后接口可以再次正常调用。
//UNLOGIN
//417
//一般由一端登录导致自动登录失败导致。
//这种情况发生于非强制登录模式下已有一端在线而当前设备进行自动登录(设置为只允许一端同时登录时)，
//出于安全方面的考虑，云信服务器判定当前端需要重新手动输入用户密码进行登录，故拒绝登录。
//KICKOUT
//1000
//登录成功之前，调用本地数据库相关接口（手动登录的情况下数据库未打开）
//UNLOGIN
    public static void LoginYunxing(LoginInfo info, RequestCallback<LoginInfo> callback) {
        NIMClient.getService(AuthService.class).login(info).setCallback(callback);
    }

//"yunXinAccount":"devtrainer_100057","yunXinToken":"f2352149d6fc2b23fae8f8f763ac31ed" 18000000000
//"yunXinAccount":"devstore_100039","yunXinToken":"00a3271586a1a6698a7608800301919d"   18700000000

    public static void LogoutYunxing() {
        NIMClient.getService(AuthService.class).logout();
    }

    public static void setUserInfo(String name, String avatar, RequestCallbackWrapper<Void> callbackWrapper) {
        Map<UserInfoFieldEnum, Object> userInfo = new HashMap<>(1);
        userInfo.put(UserInfoFieldEnum.Name, name);
        userInfo.put(UserInfoFieldEnum.AVATAR, avatar);
        NIMClient.getService(UserService.class).updateUserInfo(userInfo)
                .setCallback(callbackWrapper);
    }

    //发送普通文本消息
    public static void sendText(String account, String text,String intenviewID,String positionID,String intenviewName) {
        // 该帐号为示例，请先注册
        SessionTypeEnum sessionType = SessionTypeEnum.P2P;
        IMMessage textMessage = MessageBuilder.createTextMessage(account, sessionType, text);
        // 发送给对方  如果是发送失败后重发，标记为true，否则填false
        Map map =new HashMap();
        map.put("intenviewID",intenviewID);
        map.put("positionID",positionID);
        map.put("intenviewName",intenviewName);
        textMessage.setRemoteExtension(map);
        NIMClient.getService(MsgService.class).sendMessage(textMessage, false);
    }
    //调到聊天页面
    public static void toChatRoom(Context context, String otherAccount,
                                  String intenviewID,String intenviewName,String positionID,
                                  boolean isTrainer) {
        //        NimUIKit.startP2PSession(context, otherAccount, null);

        P2PMessageActivity2.start(context, otherAccount, new DefaultP2PSessionCustomization(), null,
                intenviewID,intenviewName,positionID,
                isTrainer);
        NimUIKit.setMsgForwardFilter(new MsgForwardFilter() {
            @Override
            public boolean shouldIgnore(IMMessage message) {
                return false;
            }
        });
        NimUIKit.setMsgRevokeFilter(new MsgRevokeFilter() {
            @Override
            public boolean shouldIgnore(IMMessage message) {
                return false;
            }
        });
    }

    public static void addToBlackList(String account){
        NIMClient.getService(FriendService .class).addToBlackList(account)
                .setCallback(new RequestCallback<Void>(){
                    @Override
                    public void onSuccess(Void aVoid) {

                    }

                    @Override
                    public void onFailed(int i) {

                    }

                    @Override
                    public void onException(Throwable throwable) {

                    }
                });
    }


}

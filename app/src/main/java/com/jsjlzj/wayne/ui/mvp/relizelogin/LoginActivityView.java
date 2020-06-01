package com.jsjlzj.wayne.ui.mvp.relizelogin;


import com.jsjlzj.wayne.entity.DataBean;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.address.MalAddressProvince;
import com.jsjlzj.wayne.entity.message.MdlIsBothReply;
import com.jsjlzj.wayne.entity.store.MdlDict;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BaseView;


public interface LoginActivityView extends BaseView {
    default void showResultInfo(MdlBaseHttpResp resp){}//账号信息


    default void showResultSwitchIdentity(MdlBaseHttpResp resp){}//切换身份
    default void showResultGetByOpenid(MdlBaseHttpResp<MdlUser> resp){}//微信openid查用户
    default void showResultLogin(MdlBaseHttpResp<MdlUser> resp){}//密码登录
    default void showResultLoginBySmsCode(MdlBaseHttpResp<MdlUser> resp){}//验证码登录
    default void showResultLoginByWeChat(MdlBaseHttpResp<MdlUser> resp){}//微信登录
    default void showResultLogout(MdlBaseHttpResp resp){}//登出
    default void showResultRegister(MdlBaseHttpResp resp){}//注册
    default void showResultResetPwd(MdlBaseHttpResp<MdlUser> resp){}//重置密码
    default void showResultSendCode(MdlBaseHttpResp resp){}//发送验证码
    default void showSelectStoreUserInfo(MdlBaseHttpResp<MdlUser> resp) {};//查询个人信息
    default void showResultUntyingWeChat(MdlBaseHttpResp<MdlUser> resp){}//微信解绑
    default void bindWeChat(MdlBaseHttpResp<MdlUser> resp) {}//绑定微信
    default void showResultgetAll(MdlBaseHttpResp<MdlDict> resp){}//获取数据字典
    default void showResultAllArea(MdlBaseHttpResp<MalAddressProvince> resp){}//获取所有地区信息
    //设置密码
    default void setPsd(MdlBaseHttpResp<MdlUser> resp) {
    }
    //修改手机号
    default void changeMobile(MdlBaseHttpResp<MdlUser> resp) {
    }

    //门店 不合适
    default void messageCVUnsuitable(MdlBaseHttpResp resp) {
    }
    //教练 不敢兴趣

    default void messagePosistionUnsuitable(MdlBaseHttpResp resp) {
    }
    //是否回复过
    default void messageIsbothreply(MdlBaseHttpResp<MdlIsBothReply> resp) {
    }
    //保存普通消息
    default void messageSaveMessage(MdlBaseHttpResp resp) {
    }
    //保存微信号
    default void messageSaveWechat(MdlBaseHttpResp resp) {
    }
    //修改密码
    default void updatePsd(MdlBaseHttpResp<MdlUser> resp) {
    }

    default void getIsFinishInfoSuccess(MdlBaseHttpResp<DataBean> resp){}
}

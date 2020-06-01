package com.jsjlzj.wayne.ui.mvp.relizelogin;


import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.data.http.HttpDataLogin;
import com.jsjlzj.wayne.data.http.HttpDataStroe;
import com.jsjlzj.wayne.entity.DataBean;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnLoadHttpDataListener;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BaseModel;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentModel;
import com.jsjlzj.wayne.utils.LogAndToastUtil;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class LoginActivityModel extends BaseModel {

    public void getCheckCodeByEmail(final int code, Map param, final OnLoadHttpDataListener listener){
//        HttpDataBasis.getInstance().getCheckCode(param, new Observer<MdlBaseHttpResp>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                LoginActivity.this.disposable = d;
//            }
//
//            @Override
//            public void onNext(MdlBaseHttpResp mdlBaseHttpResp) {
//                listener.onSuccess(code, mdlBaseHttpResp);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                listener.onFailure(code, e);
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
    }

    public void getAll( int code,Map param, final OnLoadHttpDataListener listener){
        HttpDataLogin.getInstance().getAll(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                LoginActivityModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp mdlBaseHttpResp) {
                listener.onSuccess(code, mdlBaseHttpResp);
            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(code, e);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void selectStoreUserInfo(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().selectStoreUserInfo(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                LoginActivityModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp mdlBaseHttpResp) {
                listener.onSuccess(code, mdlBaseHttpResp);
            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(code, e);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void unBindWeChat(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataLogin.getInstance().unBindWeChat(param, new Observer<MdlBaseHttpResp<MdlUser>>() {
            @Override
            public void onSubscribe(Disposable d) {
                LoginActivityModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<MdlUser> mdlBaseHttpResp) {
                listener.onSuccess(code, mdlBaseHttpResp);
            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(code, e);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void changeMobile(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataLogin.getInstance().changeMobile(param, new Observer<MdlBaseHttpResp<MdlUser>>() {
            @Override
            public void onSubscribe(Disposable d) {
                LoginActivityModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<MdlUser> mdlBaseHttpResp) {
                listener.onSuccess(code, mdlBaseHttpResp);
            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(code, e);
            }

            @Override
            public void onComplete() {

            }
        });
    }
    public void getSmes( Map param, final OnLoadHttpDataListener listener){
        HttpDataLogin.getInstance().getSmes(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                LoginActivityModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp mdlBaseHttpResp) {
                if(mdlBaseHttpResp.status!= HttpConstant.R_HTTP_OK)  LogAndToastUtil.log("获取验证码失败："+mdlBaseHttpResp.getMsg());
            }

            @Override
            public void onError(Throwable e) {
                LogAndToastUtil.log("获取验证码失败："+e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void getByOpenid(final int code, String openid, final OnLoadHttpDataListener listener){
        HttpDataLogin.getInstance().getByOpenId(openid, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                LoginActivityModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp mdlBaseHttpResp) {
                listener.onSuccess(code, mdlBaseHttpResp);
            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(code, e);
            }

            @Override
            public void onComplete() {

            }
        });
    }
    public void loginBySmes(final int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataLogin.getInstance().loginBySmes(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                LoginActivityModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp mdlBaseHttpResp) {
                listener.onSuccess(code, mdlBaseHttpResp);
            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(code, e);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void updatePsd(final int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataLogin.getInstance().updatePsd(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                LoginActivityModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp mdlBaseHttpResp) {
                listener.onSuccess(code, mdlBaseHttpResp);
            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(code, e);
            }

            @Override
            public void onComplete() {

            }
        });
    }


    public void bindWeChat(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataLogin.getInstance().bindWeChat(param, new Observer<MdlBaseHttpResp<MdlUser>>() {
            @Override
            public void onSubscribe(Disposable d) {
                LoginActivityModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<MdlUser> mdlBaseHttpResp) {
                listener.onSuccess(code, mdlBaseHttpResp);
            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(code, e);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void setPsd(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataLogin.getInstance().setPsd(param, new Observer<MdlBaseHttpResp<MdlUser>>() {
            @Override
            public void onSubscribe(Disposable d) {
                LoginActivityModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<MdlUser> mdlBaseHttpResp) {
                listener.onSuccess(code, mdlBaseHttpResp);
            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(code, e);
            }

            @Override
            public void onComplete() {

            }
        });
    }
    public void loginByWX(final int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataLogin.getInstance().loginByWX(param, new Observer<MdlBaseHttpResp<MdlUser>>() {
            @Override
            public void onSubscribe(Disposable d) {
                LoginActivityModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<MdlUser> mdlBaseHttpResp) {
                listener.onSuccess(code, mdlBaseHttpResp);
            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(code, e);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void loginByPwd(final int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataLogin.getInstance().loginByPwd(param, new Observer<MdlBaseHttpResp<MdlUser>>() {
            @Override
            public void onSubscribe(Disposable d) {
                LoginActivityModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<MdlUser> mdlBaseHttpResp) {
                listener.onSuccess(code, mdlBaseHttpResp);
            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(code, e);
            }

            @Override
            public void onComplete() {

            }
        });
    }    public void resetPwd(final int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataLogin.getInstance().resetPwd(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                LoginActivityModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp mdlBaseHttpResp) {
                listener.onSuccess(code, mdlBaseHttpResp);
            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(code, e);
            }

            @Override
            public void onComplete() {

            }
        });
    }



    public void getIsFinishInfo(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataLogin.getInstance().getIsFinishInfo(param, new Observer<MdlBaseHttpResp<DataBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                LoginActivityModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<DataBean> mdlBaseHttpResp) {
                listener.onSuccess(code, mdlBaseHttpResp);
            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(code, e);
            }

            @Override
            public void onComplete() {

            }
        });
    }


    public void getAllArea( int code,Map param, final OnLoadHttpDataListener listener){
        HttpDataLogin.getInstance().getAreaAll(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                LoginActivityModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp mdlBaseHttpResp) {
                listener.onSuccess(code, mdlBaseHttpResp);
            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(code, e);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 聊天室
     */

    //门店：不适合
    public void messageCVUnsuitable( int code,Map param, final OnLoadHttpDataListener listener){
        HttpDataLogin.getInstance().messageCVUnsuitable(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                LoginActivityModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp mdlBaseHttpResp) {
                listener.onSuccess(code, mdlBaseHttpResp);
            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(code, e);
            }

            @Override
            public void onComplete() {

            }
        });
    }
    //教练 不敢兴趣
    public void messagePosistionUnsuitable( int code,Map param, final OnLoadHttpDataListener listener){
        HttpDataLogin.getInstance().messagePosistionUnsuitable(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                LoginActivityModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp mdlBaseHttpResp) {
                listener.onSuccess(code, mdlBaseHttpResp);
            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(code, e);
            }

            @Override
            public void onComplete() {

            }
        });
    }
    //是否回复过
    public void messageIsbothreply( int code,Map param, final OnLoadHttpDataListener listener){
        HttpDataLogin.getInstance().messageIsbothreply(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                LoginActivityModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp mdlBaseHttpResp) {
                listener.onSuccess(code, mdlBaseHttpResp);
            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(code, e);
            }

            @Override
            public void onComplete() {

            }
        });
    }
    //保存普通消息
    public void messageSaveMessage( int code,Map param, final OnLoadHttpDataListener listener){
        HttpDataLogin.getInstance().messageSaveMessage(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                LoginActivityModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp mdlBaseHttpResp) {
                listener.onSuccess(code, mdlBaseHttpResp);
            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(code, e);
            }

            @Override
            public void onComplete() {

            }
        });
    }
    //保存微信号
    public void messageSaveWechat( int code,Map param, final OnLoadHttpDataListener listener){
        HttpDataLogin.getInstance().messageSaveWechat(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                LoginActivityModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp mdlBaseHttpResp) {
                listener.onSuccess(code, mdlBaseHttpResp);
            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(code, e);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}

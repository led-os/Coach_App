package com.jsjlzj.wayne.data.http;


import com.jsjlzj.wayne.data.api.LoginService;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/4/17.
 */

public class HttpDataLogin extends BaseHttpData {
    private static class SingletonHolder {
        private static final HttpDataLogin INSTANCE = new HttpDataLogin();
    }

    public static HttpDataLogin getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private LoginService service = create(LoginService.class);

    public void refreshUrl() {
        service=null;
        service = create(LoginService.class);
    }

    /**
     * 聊天室
     */
    //门店：不适合
    public void messageCVUnsuitable(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.messageCVUnsuitable(body);
        setSubscribe(observable, observer);
    }
    //教练 不敢兴趣
    public void messagePosistionUnsuitable(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.messagePosistionUnsuitable(body);
        setSubscribe(observable, observer);
    }
    //是否回复过
    public void messageIsbothreply(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.messageIsbothreply(body);
        setSubscribe(observable, observer);
    }
    //保存普通消息
    public void messageSaveMessage(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.messageSaveMessage(body);
        setSubscribe(observable, observer);
    }
    //保存微信号
    public void messageSaveWechat(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.messageSaveWechat(body);
        setSubscribe(observable, observer);
    }




    /**
     * 其他
     */

    public void getSmes(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.getSmes(body);
        setSubscribe(observable, observer);
    }

    public void getAll(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.getAll(body);
        setSubscribe(observable, observer);
    }

    public void loginBySmes(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.loginBySmes(body);
        setSubscribe(observable, observer);
    }
    public void updatePsd(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.updatePsd(body);
        setSubscribe(observable, observer);
    }

    public void getByOpenId(String openid, Observer observer) {
        Observable observable = service.byGetOpen(openid);
        setSubscribe(observable, observer);
    }


    public void faqList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.faqList(body);
        setSubscribe(observable, observer);
    }

    public void loginByPwd(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.loginByPwd(body);
        setSubscribe(observable, observer);
    }

    public void loginByWX(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.loginByWX(body);
        setSubscribe(observable, observer);
    }


    public void selectByStatus(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.loginByPwd(body);
        setSubscribe(observable, observer);
    }
    public void resetPwd(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.resetPwd(body);
        setSubscribe(observable, observer);
    }


    public void switchIdentity(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.switchIdentity(body);
        setSubscribe(observable, observer);
    }

    public void changeMobile(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.changeMobile(body);
        setSubscribe(observable, observer);
    }

    public void bindWeChat(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.bindWechat(body);
        setSubscribe(observable, observer);
    }

    public void unBindWeChat(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.unbindWeChat(body);
        setSubscribe(observable, observer);
    }

    public void questionBack(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.questionBack(body);
        setSubscribe(observable, observer);
    }

    public void setPsd(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.setpwd(body);
        setSubscribe(observable, observer);
    }

    public void getAreaAll(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.getAreaAll(body);
        setSubscribe(observable, observer);
    }


}

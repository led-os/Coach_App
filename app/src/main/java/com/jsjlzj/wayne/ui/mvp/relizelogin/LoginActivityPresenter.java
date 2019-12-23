package com.jsjlzj.wayne.ui.mvp.relizelogin;

import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BaseModel;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BasePresenter;

import java.util.Map;


public class LoginActivityPresenter extends BasePresenter<LoginActivityView> {
    private static final int REQ_LOGIN_SMES = 3;
    private static final int REQ_LOGIN_PWD = 4;
    private static final int REQ_RESET_PWD = 5;
    private static final int REQ_SET_PSD = 6;
    private static final int REQ_CHANGE_MOBILE = 7;
    private static final int REQ_GETBY_OPENID = 8;
    private static final int REQ_LOGIN_WX = 9;
    private static final int REQ_DICT_GETALL = 10;
    private static final int REQ_ALL_AREA = 11;
    private static final int REQ_UNBIND = 12;
    private static final int REQ_GETUSER = 13;
    private static final int REQ_BINDWECHAT = 14;
    private static final int REQ_FIND_PWD = 1;
    private static final int REQ_GET_CHECK_CODE = 2;
    private static final int REQ_UPDATE_PSD = 15;

    private static final int MESSAGE_CV_UNSUITABLE = 41;//门店：不适合
    private static final int MESSAGE_POSISTION_UNSUITABLE = 42;//教练 不敢兴趣
    private static final int MESSAGE_ISBOTHREPLY = 43;//是否回复过
    private static final int MESSAGE_SAVE_MESSAGE = 44;//保存普通消息
    private static final int MESSAGE_SAVE_WECHAT = 45;//保存微信号


    private LoginActivityModel model;

    public LoginActivityPresenter(LoginActivityView view) {
        this.view = view;
        this.model = new LoginActivityModel();
    }

    public void findPwd(Map param) {
        if (model != null) {
            view.showLoading();
//            model.findPwdByEmail(REQ_FIND_PWD, param, this);
        }
    }

    public void getSmes(Map param) {
        if (model != null) {
            model.getSmes(param, this);
        }
    }

    public void changeMobile(Map param) {
        if (model != null) {
            view.showLoading();
            model.changeMobile(REQ_CHANGE_MOBILE, param, this);
        }
    }

    public void getAll(Map param) {
        if (model != null) {
            view.showLoading();
            model.getAll(REQ_DICT_GETALL, param, this);
        }
    }

    public void setPsd(Map param) {
        if (model != null) {
            view.showLoading();
            model.setPsd(REQ_SET_PSD, param, this);
        }
    }

    public void selectStoreUserInfo(Map param) {
        if (model != null) {
            view.showLoading();
            model.selectStoreUserInfo(REQ_GETUSER, param, this);
        }
    }

    public void loginBySmes(Map param) {
        if (model != null) {
            view.showLoading();
            model.loginBySmes(REQ_LOGIN_SMES, param, this);
        }
    }

    public void updatePsd(Map param) {
        if (model != null) {
            view.showLoading();
            model.updatePsd(REQ_UPDATE_PSD, param, this);
        }
    }


    public void getByOpenid(String openid) {
        if (model != null) {
            view.showLoading();
            model.getByOpenid(REQ_GETBY_OPENID, openid, this);
        }
    }


    public void loginByPwd(Map param) {
        if (model != null) {
            view.showLoading();
            model.loginByPwd(REQ_LOGIN_PWD, param, this);
        }
    }

    public void loginByWX(Map param) {
        if (model != null) {
            view.showLoading();
            model.loginByWX(REQ_LOGIN_WX, param, this);
        }
    }
    //绑定微信
    public void bindWeChat(Map param) {
        if (model != null) {
            view.showLoading();
            model.bindWeChat(REQ_BINDWECHAT, param, this);
        }
    }
    //解绑微信
    public void unBindWeChat(Map param) {
        if (model != null) {
            view.showLoading();
            model.unBindWeChat(REQ_UNBIND, param, this);
        }
    }

    public void resetPwd(Map param) {
        if (model != null) {
            view.showLoading();
            model.resetPwd(REQ_RESET_PWD, param, this);
        }
    }

    public void getAllArea(Map param) {
        if (model != null) {
//            view.showLoading();
            model.getAllArea(REQ_ALL_AREA, param, this);
        }
    }

    /**
     * 聊天室
     */
    //门店：不适合
    public void messageCVUnsuitable(Map param) {
        if (model != null) {
//            view.showLoading();
            model.messageCVUnsuitable(MESSAGE_CV_UNSUITABLE, param, this);
        }
    }
    //教练 不敢兴趣
    public void messagePosistionUnsuitable(Map param) {
        if (model != null) {
//            view.showLoading();
            model.messagePosistionUnsuitable(MESSAGE_POSISTION_UNSUITABLE, param, this);
        }
    }
    //是否回复过
    public void messageIsbothreply(Map param) {
        if (model != null) {
//            view.showLoading();
            model.messageIsbothreply(MESSAGE_ISBOTHREPLY, param, this);
        }
    }
    //保存普通消息
    public void messageSaveMessage(Map param) {
        if (model != null) {
//            view.showLoading();
            model.messageSaveMessage(MESSAGE_SAVE_MESSAGE, param, this);
        }
    }
    //保存微信号
    public void messageSaveWechat(Map param) {
        if (model != null) {
//            view.showLoading();
            model.messageSaveWechat(MESSAGE_SAVE_WECHAT, param, this);
        }
    }

    @Override
    protected BaseModel getMode() {
        return model;
    }

    @Override
    protected void responseSuccess(int code, MdlBaseHttpResp resp) {
        view.hideLoading();
        switch (code) {
            case REQ_FIND_PWD:
                break;
            case REQ_GET_CHECK_CODE:
                break;
            case REQ_LOGIN_SMES:
                view.showResultLoginBySmsCode(resp);
                break;
            case REQ_LOGIN_PWD:
                view.showResultLogin(resp);
                break;
            case REQ_RESET_PWD:
                view.showResultResetPwd(resp);
                break;
            case REQ_DICT_GETALL:
                view.showResultgetAll(resp);
                break;
            case REQ_ALL_AREA:
                view.showResultAllArea(resp);
                break;
            case REQ_GETBY_OPENID:
                view.showResultGetByOpenid(resp);
                break;
            case REQ_LOGIN_WX:
                view.showResultLoginByWeChat(resp);
                break;
            case REQ_UNBIND:
                view.showResultUntyingWeChat(resp);
                break;
            case REQ_GETUSER:
                view.showSelectStoreUserInfo(resp);
                break;
            case REQ_BINDWECHAT:
                view.bindWeChat(resp);
                break;
            case REQ_SET_PSD:
                view.setPsd(resp);
                break;
            case REQ_CHANGE_MOBILE:
                view.changeMobile(resp);
                break;
            case MESSAGE_CV_UNSUITABLE:
                view.messageCVUnsuitable(resp);
                break;
            case MESSAGE_POSISTION_UNSUITABLE:
                view.messagePosistionUnsuitable(resp);
                break;
            case MESSAGE_ISBOTHREPLY:
                view.messageIsbothreply(resp);
                break;
            case MESSAGE_SAVE_MESSAGE:
                view.messageSaveMessage(resp);
                break;
            case MESSAGE_SAVE_WECHAT:
                view.messageSaveWechat(resp);
                break;
            case REQ_UPDATE_PSD:
                view.updatePsd(resp);
                break;
        }
    }
}

package com.jsjlzj.wayne.ui.mvp.address;


import com.jsjlzj.wayne.data.http.HttpDataLogin;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnLoadHttpDataListener;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BaseModel;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class AddressModel extends BaseModel {

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

    public void getAllArea( int code,Map param, final OnLoadHttpDataListener listener){
        HttpDataLogin.getInstance().getAreaAll(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                AddressModel.this.disposable = d;
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
                AddressModel.this.disposable = d;
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

    public void loginByPwd(final int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataLogin.getInstance().loginByPwd(param, new Observer<MdlBaseHttpResp<MdlUser>>() {
            @Override
            public void onSubscribe(Disposable d) {
                AddressModel.this.disposable = d;
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
                AddressModel.this.disposable = d;
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

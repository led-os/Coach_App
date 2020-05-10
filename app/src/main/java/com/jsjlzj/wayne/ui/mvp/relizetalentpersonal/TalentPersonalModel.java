package com.jsjlzj.wayne.ui.mvp.relizetalentpersonal;


import com.jsjlzj.wayne.data.http.HttpDataBasis;
import com.jsjlzj.wayne.data.http.HttpDataLogin;
import com.jsjlzj.wayne.data.http.HttpDataStroe;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.find.FindCategoryBean;
import com.jsjlzj.wayne.entity.store.MdlBenefits;
import com.jsjlzj.wayne.entity.store.MdlStoreInfo;
import com.jsjlzj.wayne.entity.trainer.BannerAll;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnLoadHttpDataListener;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BaseModel;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class TalentPersonalModel extends BaseModel {
    public void getStoreInfo(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().getStoreInfo(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
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
    public void getStoreDetail(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().getStoreDetail(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
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
    public void getAll( int code,Map param, final OnLoadHttpDataListener listener){
        HttpDataLogin.getInstance().getAll(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
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

    public void searchPosition(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().searchPosition(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
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

    public void getSystemCompanyBenefits(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().getSystemCompanyBenefits(param, new Observer<MdlBaseHttpResp<MdlBenefits>>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<MdlBenefits> mdlBaseHttpResp) {
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
    public void saveBrandLogo(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().saveBrandLogo(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
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
    public void saveCompanyBenefits(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().saveCompanyBenefits(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
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
    public void saveCompanyImage(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().saveCompanyImage(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
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
    public void saveCompanyProfile(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().saveCompanyProfile(param, new Observer<MdlBaseHttpResp<MdlStoreInfo>>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<MdlStoreInfo> mdlBaseHttpResp) {
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
    public void saveStoreAddress(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().saveStoreAddress(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
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
    public void saveWorkTime(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().saveWorkTime(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
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
    public void saveMyInfo(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().getMyInfo(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
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

    public void changePositionStatus(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().changePositionStatus(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
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
    public void getPositionDetail(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().getPositionDetail(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
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
    public void getPositionType(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().getPositionType(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
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
    public void getPublishPositionTypeList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().getPublishPositionTypeList(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
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
    public void getSystemSkillRequired(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().getSystemSkillRequired(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
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
    public void queryByStatus(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().queryByStatus(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
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
    public void savePosition(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().savePosition(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
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

    public void searchCV(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().searchCV(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
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

    public void switchIdentity(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataLogin.getInstance().switchIdentity(param, new Observer<MdlBaseHttpResp<MdlUser>>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
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

    public void questionBack(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataLogin.getInstance().questionBack(param, new Observer<MdlBaseHttpResp<MdlUser>>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
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

    public void saveStoreAuth(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().saveStoreAuth(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
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
    public void saveStoreUserInfo(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().saveStoreUserInfo(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
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
                TalentPersonalModel.this.disposable = d;
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


    public void saveAddressSearch(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().saveAddressSearch(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
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


    public void getAddressSearch(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().selectAddressSearch(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
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

    //    public void upload(int code, @EnumUploadPic.PicType String type, String path, final OnLoadHttpDataListener listener) {
//        HttpDataBasis.getInstance().upload(type, path, new Observer<MdlBaseHttpResp>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                TalentPersonalModel.this.disposable = d;
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
//    }
    public void upload(int code, String path, final OnLoadHttpDataListener listener) {
        HttpDataBasis.getInstance().upload( path, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
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

    public void commCV(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().commCV(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
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
    public void cancelStoreLike(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().cancelStoreLike(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
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
    public void saveStoreInfoLike(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().saveStoreInfoLike(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
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

    public void getCVLike(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().getCVLike(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
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
    public void saveCommCV(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().saveCommCV(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
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


    public void getRecommendPic(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().getRecommendPic(param, new Observer<MdlBaseHttpResp<BannerAll>>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<BannerAll> mdlBaseHttpResp) {
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
    public void getCategoryList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().getCategoryList(param, new Observer<MdlBaseHttpResp<FindCategoryBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<FindCategoryBean> mdlBaseHttpResp) {
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
    public void tipOff(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().tipOff(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentPersonalModel.this.disposable = d;
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

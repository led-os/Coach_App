package com.jsjlzj.wayne.ui.mvp.relizetalent;


import com.jsjlzj.wayne.data.http.HttpDataBasis;
import com.jsjlzj.wayne.data.http.HttpDataLogin;
import com.jsjlzj.wayne.data.http.HttpDataStroe;
import com.jsjlzj.wayne.entity.DataBean;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.shopping.ShoppingNumBean;
import com.jsjlzj.wayne.entity.store.home.CategoryPageBean;
import com.jsjlzj.wayne.entity.store.home.VideoPageBean;
import com.jsjlzj.wayne.entity.store.search.ChannelPageBean;
import com.jsjlzj.wayne.entity.trainer.BannerAll;
import com.jsjlzj.wayne.entity.trainer.InvitationBean;
import com.jsjlzj.wayne.entity.trainer.InvitationCodeBean;
import com.jsjlzj.wayne.entity.trainer.MineStudyBean;
import com.jsjlzj.wayne.entity.trainer.SignUpPageBean;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnLoadHttpDataListener;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BaseModel;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class TalentTabFragmentModel extends BaseModel {

    public void upload(int code, String path, final OnLoadHttpDataListener listener) {
        HttpDataBasis.getInstance().upload( path, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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


    public void uploadVideo(int code, String path, final OnLoadHttpDataListener listener) {
        HttpDataBasis.getInstance().uploadVideo( path, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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

    public void detailCV(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().detailCV(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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
                TalentTabFragmentModel.this.disposable = d;
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
    public void faqList(final int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataLogin.getInstance().faqList(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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

    public void selectByStatus(final int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataLogin.getInstance().loginByPwd(param, new Observer<MdlBaseHttpResp<MdlUser>>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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
                TalentTabFragmentModel.this.disposable = d;
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
     * 教练端-简历模块
     * 接口:创建达人简历,简历详情,职位类型列表,达人-我的
     */
    public void deleteEducationExperienceT(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().deleteEducationExperienceT(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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
    public void deleteWorkExperienceT(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().deleteWorkExperienceT(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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

    public void getPositionTypeList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().getPositionTypeList(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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
        HttpDataStroe.getInstance().getIsFinishInfo(param, new Observer<MdlBaseHttpResp<DataBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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


    public void deleteWorkHopeT(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().deleteWorkHopeT(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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
    public void getDetailT(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().getDetailT(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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
    public void getPositionTypeT(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().getPositionTypeT(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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
    public void getWorkHopeListT(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().getWorkHopeListT(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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
    public void myselfT(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().myselfT(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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
    public void saveAdvantage(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().saveAdvantage(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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
    public void saveCertificatePhotosT(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().saveCertificatePhotosT(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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
    public void saveCvBaseInfoT(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().saveCvBaseInfoT(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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
    public void saveEducationExperienceT(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().saveEducationExperienceT(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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
    public void saveLifePhotosT(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().saveLifePhotosT(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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

    public void saveTeachVideo(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().saveTeachVideo(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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
    public void saveWorkExperienceT(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().saveWorkExperienceT(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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
    public void saveWorkHopeT(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().saveWorkHopeT(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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
    public void saveWorkStatusT(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().saveWorkStatusT(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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

    public void cancelPositionLike(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().cancelPositionLike(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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



    public void getTrainerPositionDetail(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().getTrainerPositionDetail(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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

    public void getPositionCommList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().getPositionCommList(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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

    public void getPositionLikeList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().getPositionLikeList(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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



    public void getStorePositionList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().getStorePositionList(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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

    public void savePositionLike(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().savePositionLike(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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
                TalentTabFragmentModel.this.disposable = d;
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

    public void getStoreLikeList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().getStoreLikeList(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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
    public void saveCVLike(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().saveCVLike(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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
    public void cancelCV(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().cancelCV(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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
    public void tipOff(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().tipOff(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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

    public void getInterViewTrainer(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().getInterViewTrainer(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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

    public void getInterViewStore(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().getInterViewStore(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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

    public void getInterViewDetail(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().getInterViewDetail(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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

    public void getInterViewInfo(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().getInterViewInfo(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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


    public void getInterViewCancel(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().getInterViewCancel(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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


    public void getInterViewStatus(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().getInterViewStatus(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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


    public void sendInterView(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().sendInterView(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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
                TalentTabFragmentModel.this.disposable = d;
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

    public void getMineDynamicList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().getMineDynamicList(param, new Observer<MdlBaseHttpResp<VideoPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<VideoPageBean> mdlBaseHttpResp) {
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

    public void getMineFansList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().getMineFansList(param, new Observer<MdlBaseHttpResp<ChannelPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<ChannelPageBean> mdlBaseHttpResp) {
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

    public void getMineFollowList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataStroe.getInstance().getMineFollowList(param, new Observer<MdlBaseHttpResp<ChannelPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<ChannelPageBean> mdlBaseHttpResp) {
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


    public void clickCollect(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataStroe.getInstance().clickCollect(param, new Observer<MdlBaseHttpResp<DataBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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


    public void cancelCollect(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataStroe.getInstance().cancelCollect(param, new Observer<MdlBaseHttpResp<DataBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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


    public void clickFollow(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataStroe.getInstance().clickFollow(param, new Observer<MdlBaseHttpResp<CategoryPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<CategoryPageBean> mdlBaseHttpResp) {
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


    public void cancelFollow(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataStroe.getInstance().cancelFollow(param, new Observer<MdlBaseHttpResp<DataBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
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

    public void getLearnList(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataStroe.getInstance().getLearnList(param, new Observer<MdlBaseHttpResp<MineStudyBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<MineStudyBean> mdlBaseHttpResp) {
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


   public void getInvitationCode(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataStroe.getInstance().getInvitationCode(param, new Observer<MdlBaseHttpResp<InvitationCodeBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<InvitationCodeBean> mdlBaseHttpResp) {
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

   public void getInvitationList(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataStroe.getInstance().getInvitationList(param, new Observer<MdlBaseHttpResp<InvitationBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<InvitationBean> mdlBaseHttpResp) {
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

   public void getSignUpList(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataStroe.getInstance().getSignUpList(param, new Observer<MdlBaseHttpResp<SignUpPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<SignUpPageBean> mdlBaseHttpResp) {
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

   public void getShoppingNum(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataStroe.getInstance().getShoppingNum(param, new Observer<MdlBaseHttpResp<ShoppingNumBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                TalentTabFragmentModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<ShoppingNumBean> mdlBaseHttpResp) {
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

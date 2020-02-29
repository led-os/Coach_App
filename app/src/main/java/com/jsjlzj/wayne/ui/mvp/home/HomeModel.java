package com.jsjlzj.wayne.ui.mvp.home;

import com.jsjlzj.wayne.data.http.HttpDataHome;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.home.AmoySchoolBean;
import com.jsjlzj.wayne.entity.store.home.CategoryPageBean;
import com.jsjlzj.wayne.entity.store.home.RecommendBean;
import com.jsjlzj.wayne.entity.store.home.VideoPageBean;
import com.jsjlzj.wayne.entity.store.learn.ChapterListBean;
import com.jsjlzj.wayne.entity.store.learn.ChapterSubjectListBean;
import com.jsjlzj.wayne.entity.store.learn.DoneChapterBean;
import com.jsjlzj.wayne.entity.store.learn.ExamSubjectListBean;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnLoadHttpDataListener;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BaseModel;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @ClassName: HomeModel
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/1/14 15:58
 */
public class HomeModel extends BaseModel {


    public void getHomeRecommendData(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataHome.getInstance().getRecommendData(param, new Observer<MdlBaseHttpResp<RecommendBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<RecommendBean> mdlBaseHttpResp) {
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


    public void getAmoySchoolData(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataHome.getInstance().getAmoySchoolData(param, new Observer<MdlBaseHttpResp<AmoySchoolBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<AmoySchoolBean> mdlBaseHttpResp) {
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


    public void getMatchData(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataHome.getInstance().getMatchData(param, new Observer<MdlBaseHttpResp<AmoySchoolBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<AmoySchoolBean> mdlBaseHttpResp) {
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



    public void getAmoyList(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataHome.getInstance().getAmoyList(param, new Observer<MdlBaseHttpResp<CategoryPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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


    public void getMatchList(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataHome.getInstance().getMatchList(param, new Observer<MdlBaseHttpResp<CategoryPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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
    public void getDriedFoodData(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataHome.getInstance().getDriedFoodData(param, new Observer<MdlBaseHttpResp<AmoySchoolBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<AmoySchoolBean> mdlBaseHttpResp) {
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


    public void getDriedFoodList(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataHome.getInstance().getDriedFoodList(param, new Observer<MdlBaseHttpResp<VideoPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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


    public void getInformationList(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataHome.getInstance().getInformationList(param, new Observer<MdlBaseHttpResp<VideoPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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

    public void getInformationData(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataHome.getInstance().getInformationData(param, new Observer<MdlBaseHttpResp<AmoySchoolBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<AmoySchoolBean> mdlBaseHttpResp) {
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


   public void getProductData(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataHome.getInstance().getProductData(param, new Observer<MdlBaseHttpResp<AmoySchoolBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<AmoySchoolBean> mdlBaseHttpResp) {
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


    public void getProductList(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataHome.getInstance().getProductList(param, new Observer<MdlBaseHttpResp<CategoryPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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


    public void getAllClassicList(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataHome.getInstance().getAllClassicList(param, new Observer<MdlBaseHttpResp<CategoryPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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


    public void getOrganizationList(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataHome.getInstance().getOrganizationList(param, new Observer<MdlBaseHttpResp<CategoryPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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


    public void getVideoList(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataHome.getInstance().getVideoList(param, new Observer<MdlBaseHttpResp<CategoryPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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

    public void clickZan(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataHome.getInstance().clickZan(param, new Observer<MdlBaseHttpResp<CategoryPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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

    public void cancelZan(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataHome.getInstance().cancelZan(param, new Observer<MdlBaseHttpResp<CategoryPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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


    public void clickCollect(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataHome.getInstance().clickCollect(param, new Observer<MdlBaseHttpResp<CategoryPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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


    public void cancelCollect(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataHome.getInstance().cancelCollect(param, new Observer<MdlBaseHttpResp<CategoryPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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

    public void clickFollow(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataHome.getInstance().clickFollow(param, new Observer<MdlBaseHttpResp<CategoryPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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
        HttpDataHome.getInstance().cancelFollow(param, new Observer<MdlBaseHttpResp<CategoryPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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


    public void getLearnData(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataHome.getInstance().getLearnData(param, new Observer<MdlBaseHttpResp<CategoryPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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


    public void getChapterList(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataHome.getInstance().getChapterList(param, new Observer<MdlBaseHttpResp<ChapterListBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<ChapterListBean> mdlBaseHttpResp) {
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


    public void getChapterSubjectList(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataHome.getInstance().getChapterSubjectList(param, new Observer<MdlBaseHttpResp<ChapterSubjectListBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<ChapterSubjectListBean> mdlBaseHttpResp) {
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

    public void getSaveAnswerRecord(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataHome.getInstance().getSaveAnswerRecord(param, new Observer<MdlBaseHttpResp<String>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<String> mdlBaseHttpResp) {
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


    public void getWrongSubjectList(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataHome.getInstance().getWrongSubjectList(param, new Observer<MdlBaseHttpResp<ChapterSubjectListBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<ChapterSubjectListBean> mdlBaseHttpResp) {
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

    public void getExamSubjectList(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataHome.getInstance().getExamSubjectList(param, new Observer<MdlBaseHttpResp<ExamSubjectListBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<ExamSubjectListBean> mdlBaseHttpResp) {
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


    public void submitExamAnswer(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataHome.getInstance().submitExamAnswer(param, new Observer<MdlBaseHttpResp<DoneChapterBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<DoneChapterBean> mdlBaseHttpResp) {
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

    public void doneChapterAnswer(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataHome.getInstance().doneChapterAnswer(param, new Observer<MdlBaseHttpResp<DoneChapterBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<DoneChapterBean> mdlBaseHttpResp) {
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


    public void getTestResult(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataHome.getInstance().getTestResult(param, new Observer<MdlBaseHttpResp<ExamSubjectListBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<ExamSubjectListBean> mdlBaseHttpResp) {
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

    public void getCurrentSubject(int code, Map param, final OnLoadHttpDataListener listener){
        HttpDataHome.getInstance().getCurrentSubject(param, new Observer<MdlBaseHttpResp<ChapterListBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<ChapterListBean> mdlBaseHttpResp) {
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

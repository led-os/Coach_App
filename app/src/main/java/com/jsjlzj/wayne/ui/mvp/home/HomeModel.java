package com.jsjlzj.wayne.ui.mvp.home;

import com.jsjlzj.wayne.data.http.HttpDataHome;
import com.jsjlzj.wayne.entity.DataBean;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.find.CashOutPageBean;
import com.jsjlzj.wayne.entity.find.CurrencyBean;
import com.jsjlzj.wayne.entity.find.CurrencyDetailPageBean;
import com.jsjlzj.wayne.entity.find.FindLessonPageBean;
import com.jsjlzj.wayne.entity.find.JiFenPageBean;
import com.jsjlzj.wayne.entity.find.MineProfitBean;
import com.jsjlzj.wayne.entity.find.OptimizationData1Bean;
import com.jsjlzj.wayne.entity.find.OptimizationData2Bean;
import com.jsjlzj.wayne.entity.shopping.BankCardBean;
import com.jsjlzj.wayne.entity.shopping.BankCardItemBean;
import com.jsjlzj.wayne.entity.shopping.EnableCouponBean;
import com.jsjlzj.wayne.entity.shopping.LocationListBean;
import com.jsjlzj.wayne.entity.shopping.MineCouponBean;
import com.jsjlzj.wayne.entity.shopping.ShoppingCarBean;
import com.jsjlzj.wayne.entity.shopping.ShoppingPageBean;
import com.jsjlzj.wayne.entity.store.home.AmoySchoolBean;
import com.jsjlzj.wayne.entity.store.home.CategoryPageBean;
import com.jsjlzj.wayne.entity.store.home.RecommendBean;
import com.jsjlzj.wayne.entity.store.home.VideoPageBean;
import com.jsjlzj.wayne.entity.store.learn.AnswerRecordBean;
import com.jsjlzj.wayne.entity.store.learn.ChapterListBean;
import com.jsjlzj.wayne.entity.store.learn.ChapterSubjectListBean;
import com.jsjlzj.wayne.entity.store.learn.DoneChapterBean;
import com.jsjlzj.wayne.entity.store.learn.ExamSubjectListBean;
import com.jsjlzj.wayne.entity.store.search.SearchBean;
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


    public void getHomeRecommendData(int code, Map param, final OnLoadHttpDataListener listener) {
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


    public void getAmoySchoolData(int code, Map param, final OnLoadHttpDataListener listener) {
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


    public void getMatchData(int code, Map param, final OnLoadHttpDataListener listener) {
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


    public void getAmoyList(int code, Map param, final OnLoadHttpDataListener listener) {
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


    public void getMatchList(int code, Map param, final OnLoadHttpDataListener listener) {
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

    public void getDriedFoodData(int code, Map param, final OnLoadHttpDataListener listener) {
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


    public void getDriedFoodList(int code, Map param, final OnLoadHttpDataListener listener) {
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


    public void getInformationList(int code, Map param, final OnLoadHttpDataListener listener) {
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

    public void getInformationData(int code, Map param, final OnLoadHttpDataListener listener) {
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


    public void getProductData(int code, Map param, final OnLoadHttpDataListener listener) {
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


    public void getProductList(int code, Map param, final OnLoadHttpDataListener listener) {
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


    public void getAllClassicList(int code, Map param, final OnLoadHttpDataListener listener) {
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


    public void getOrganizationList(int code, Map param, final OnLoadHttpDataListener listener) {
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


    public void getVideoList(int code, Map param, final OnLoadHttpDataListener listener) {
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

    public void clickZan(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().clickZan(param, new Observer<MdlBaseHttpResp<DataBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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

    public void cancelZan(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().cancelZan(param, new Observer<MdlBaseHttpResp<DataBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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


    public void clickCollect(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().clickCollect(param, new Observer<MdlBaseHttpResp<DataBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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


    public void cancelCollect(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().cancelCollect(param, new Observer<MdlBaseHttpResp<DataBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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

    public void clickFollow(int code, Map param, final OnLoadHttpDataListener listener) {
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


    public void cancelFollow(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().cancelFollow(param, new Observer<MdlBaseHttpResp<DataBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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


    public void deleteDynamic(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().deleteDynamic(param, new Observer<MdlBaseHttpResp<DataBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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


    public void getLearnData(int code, Map param, final OnLoadHttpDataListener listener) {
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


    public void getChapterList(int code, Map param, final OnLoadHttpDataListener listener) {
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


    public void getChapterSubjectList(int code, Map param, final OnLoadHttpDataListener listener) {
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

    public void getSaveAnswerRecord(int code, Map param, final OnLoadHttpDataListener listener) {
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


    public void getWrongSubjectList(int code, Map param, final OnLoadHttpDataListener listener) {
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

    public void getExamSubjectList(int code, Map param, final OnLoadHttpDataListener listener) {
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


    public void submitExamAnswer(int code, Map param, final OnLoadHttpDataListener listener) {
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

    public void doneChapterAnswer(int code, Map param, final OnLoadHttpDataListener listener) {
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


    public void getTestResult(int code, Map param, final OnLoadHttpDataListener listener) {
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

    public void getCurrentSubject(int code, Map param, final OnLoadHttpDataListener listener) {
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


    public void getAnswerRecord(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getAnswerRecord(param, new Observer<MdlBaseHttpResp<AnswerRecordBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<AnswerRecordBean> mdlBaseHttpResp) {
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


    public void getSearchData(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getSearchData(param, new Observer<MdlBaseHttpResp<SearchBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<SearchBean> mdlBaseHttpResp) {
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


    public void getDynamicList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getDynamicList(param, new Observer<MdlBaseHttpResp<VideoPageBean>>() {
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

    public void getMineDynamicList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getMineDynamicList(param, new Observer<MdlBaseHttpResp<VideoPageBean>>() {
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


    public void getAmoySignUp(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getAmoySignUp(param, new Observer<MdlBaseHttpResp<DataBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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

    public void getMatchSignUp(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getMatchSignUp(param, new Observer<MdlBaseHttpResp<DataBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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

    public void publicDynamic(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().publicDynamic(param, new Observer<MdlBaseHttpResp<DataBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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

    public void getCollectDynamicList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getCollectDynamicList(param, new Observer<MdlBaseHttpResp<VideoPageBean>>() {
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


    public void getCollectVideoList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getCollectVideoList(param, new Observer<MdlBaseHttpResp<VideoPageBean>>() {
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


    public void getCollectInformationList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getCollectInformationList(param, new Observer<MdlBaseHttpResp<VideoPageBean>>() {
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


    public void getOptimizationData1(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getOptimizationData1(param, new Observer<MdlBaseHttpResp<OptimizationData1Bean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<OptimizationData1Bean> mdlBaseHttpResp) {
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


    public void getOptimizationData2(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getOptimizationData2(param, new Observer<MdlBaseHttpResp<OptimizationData2Bean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<OptimizationData2Bean> mdlBaseHttpResp) {
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


    public void getCashOutList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getCashOutList(param, new Observer<MdlBaseHttpResp<CashOutPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<CashOutPageBean> mdlBaseHttpResp) {
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

    public void getMineProfit(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getMineProfit(param, new Observer<MdlBaseHttpResp<MineProfitBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<MineProfitBean> mdlBaseHttpResp) {
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

    public void getRecommendCategoryList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getRecommendCategoryList(param, new Observer<MdlBaseHttpResp<FindLessonPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<FindLessonPageBean> mdlBaseHttpResp) {
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

    public void getFreeExperCategoryList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getFreeExperCategoryList(param, new Observer<MdlBaseHttpResp<FindLessonPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<FindLessonPageBean> mdlBaseHttpResp) {
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

    public void getHotCategoryList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getHotCategoryList(param, new Observer<MdlBaseHttpResp<FindLessonPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<FindLessonPageBean> mdlBaseHttpResp) {
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

    public void getHotListeningCategoryList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getHotListeningCategoryList(param, new Observer<MdlBaseHttpResp<FindLessonPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<FindLessonPageBean> mdlBaseHttpResp) {
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


    public void getJianzhiCategoryList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getJianzhiCategoryList(param, new Observer<MdlBaseHttpResp<FindLessonPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<FindLessonPageBean> mdlBaseHttpResp) {
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

    public void getMotionCategoryList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getMotionCategoryList(param, new Observer<MdlBaseHttpResp<FindLessonPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<FindLessonPageBean> mdlBaseHttpResp) {
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

    public void getFourLessonCategoryList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getFourLessonCategoryList(param, new Observer<MdlBaseHttpResp<FindLessonPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<FindLessonPageBean> mdlBaseHttpResp) {
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

    public void getJifenList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getJifenList(param, new Observer<MdlBaseHttpResp<JiFenPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<JiFenPageBean> mdlBaseHttpResp) {
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

    public void getSearchCategoryList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getSearchCategoryList(param, new Observer<MdlBaseHttpResp<FindLessonPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<FindLessonPageBean> mdlBaseHttpResp) {
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

    public void getLocationList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getLocationList(param, new Observer<MdlBaseHttpResp<LocationListBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<LocationListBean> mdlBaseHttpResp) {
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

    public void saveLocation(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().saveLocation(param, new Observer<MdlBaseHttpResp<DataBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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

    public void getCurrencyList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getCurrencyList(param, new Observer<MdlBaseHttpResp<CurrencyBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<CurrencyBean> mdlBaseHttpResp) {
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

    public void getCurrencyDetailList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getCurrencyDetailList(param, new Observer<MdlBaseHttpResp<CurrencyDetailPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<CurrencyDetailPageBean> mdlBaseHttpResp) {
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

    public void getGroupProductList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getGroupProductList(param, new Observer<MdlBaseHttpResp<ShoppingPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<ShoppingPageBean> mdlBaseHttpResp) {
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

    public void getSearchProductList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getSearchProductList(param, new Observer<MdlBaseHttpResp<ShoppingPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<ShoppingPageBean> mdlBaseHttpResp) {
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

    public void getTimeSkillProductList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getTimeSkillProductList(param, new Observer<MdlBaseHttpResp<ShoppingPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<ShoppingPageBean> mdlBaseHttpResp) {
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

    public void addShoppingCar(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().addShoppingCar(param, new Observer<MdlBaseHttpResp<DataBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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

    public void deleteCar(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().deleteCar(param, new Observer<MdlBaseHttpResp<DataBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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


    public void updateBynum(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().updateBynum(param, new Observer<MdlBaseHttpResp<ShoppingCarBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<ShoppingCarBean> mdlBaseHttpResp) {
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

    public void applyLeader(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().applyLeader(param, new Observer<MdlBaseHttpResp<DataBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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

    public void getBankCardList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getBankCardList(param, new Observer<MdlBaseHttpResp<BankCardBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<BankCardBean> mdlBaseHttpResp) {
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

    public void deleteBankCard(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().deleteBankCard(param, new Observer<MdlBaseHttpResp<BankCardBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<BankCardBean> mdlBaseHttpResp) {
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

    public void getBankCardInfo(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getBankCardInfo(param, new Observer<MdlBaseHttpResp<BankCardItemBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<BankCardItemBean> mdlBaseHttpResp) {
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

    public void applyCashout(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().applyCashout(param, new Observer<MdlBaseHttpResp<DataBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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

    public void getShoppingCarList(int code, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getShoppingCarList(new Observer<MdlBaseHttpResp<ShoppingCarBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<ShoppingCarBean> mdlBaseHttpResp) {
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

    public void getMineCouponList(int useStatus, int code, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getMineCouponList(useStatus, new Observer<MdlBaseHttpResp<MineCouponBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<MineCouponBean> mdlBaseHttpResp) {
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

    public void getEnableCouponList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getEnableCouponList(param, new Observer<MdlBaseHttpResp<EnableCouponBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<EnableCouponBean> mdlBaseHttpResp) {
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


    public void upload(int code, String path, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().upload(path, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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
        HttpDataHome.getInstance().uploadVideo(path, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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

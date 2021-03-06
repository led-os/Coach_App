package com.jsjlzj.wayne.ui.mvp.home;

import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.data.http.HttpDataHome;
import com.jsjlzj.wayne.data.http.HttpDataStroe;
import com.jsjlzj.wayne.entity.DataBean;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.find.CashOutPageBean;
import com.jsjlzj.wayne.entity.find.CommentBean;
import com.jsjlzj.wayne.entity.find.CommentDetailBean;
import com.jsjlzj.wayne.entity.find.CurrencyBean;
import com.jsjlzj.wayne.entity.find.CurrencyDetailPageBean;
import com.jsjlzj.wayne.entity.find.FindCategoryBean;
import com.jsjlzj.wayne.entity.find.FindLessonDetailBean;
import com.jsjlzj.wayne.entity.find.FindLessonPageBean;
import com.jsjlzj.wayne.entity.find.FindStoreBannerBean;
import com.jsjlzj.wayne.entity.find.FindStoreConditionBean;
import com.jsjlzj.wayne.entity.find.FindStorePageBean;
import com.jsjlzj.wayne.entity.find.FindStoreRecommendBean;
import com.jsjlzj.wayne.entity.find.FindTrainerBean;
import com.jsjlzj.wayne.entity.find.JiFenPageBean;
import com.jsjlzj.wayne.entity.find.MineProfitBean;
import com.jsjlzj.wayne.entity.find.OptimizationData1Bean;
import com.jsjlzj.wayne.entity.find.OptimizationData2Bean;
import com.jsjlzj.wayne.entity.find.PictureListBean;
import com.jsjlzj.wayne.entity.find.VideoListBean;
import com.jsjlzj.wayne.entity.shopping.AfterSaleDetailBean;
import com.jsjlzj.wayne.entity.shopping.AfterSalePageBean;
import com.jsjlzj.wayne.entity.shopping.BankCardBean;
import com.jsjlzj.wayne.entity.shopping.BankCardItemBean;
import com.jsjlzj.wayne.entity.shopping.CommitOrderBean;
import com.jsjlzj.wayne.entity.shopping.LocationListBean;
import com.jsjlzj.wayne.entity.shopping.LogisticsBean;
import com.jsjlzj.wayne.entity.shopping.MineCouponBean;
import com.jsjlzj.wayne.entity.shopping.MineOrderPageBean;
import com.jsjlzj.wayne.entity.shopping.OrderDetailBean;
import com.jsjlzj.wayne.entity.shopping.PayResultBean;
import com.jsjlzj.wayne.entity.shopping.ProfitOrderPageBean;
import com.jsjlzj.wayne.entity.shopping.ShoppingCarBean;
import com.jsjlzj.wayne.entity.shopping.ShoppingDetailBean;
import com.jsjlzj.wayne.entity.shopping.ShoppingListBean;
import com.jsjlzj.wayne.entity.shopping.ShoppingPageBean;
import com.jsjlzj.wayne.entity.shopping.VipDataBean;
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
import com.jsjlzj.wayne.entity.wiki.WikiBean;
import com.jsjlzj.wayne.entity.wiki.WikiCategoryBean;
import com.jsjlzj.wayne.entity.wiki.WikiRecommendBean;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnLoadHttpDataListener;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BaseModel;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalModel;
import com.jsjlzj.wayne.utils.LogAndToastUtil;

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

    public void getSmes(Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getSmes(param, new Observer<MdlBaseHttpResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp mdlBaseHttpResp) {
                if (mdlBaseHttpResp.status != HttpConstant.R_HTTP_OK)
                    LogAndToastUtil.log("获取验证码失败：" + mdlBaseHttpResp.getMsg());
            }

            @Override
            public void onError(Throwable e) {
                LogAndToastUtil.log("获取验证码失败：" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

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


    public void getCategoryList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getCategoryList(param, new Observer<MdlBaseHttpResp<FindCategoryBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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


    public void getV4InformationList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getV4InformationList(param, new Observer<MdlBaseHttpResp<VideoPageBean>>() {
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

    public void modifyLocation(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().modifyLocation(param, new Observer<MdlBaseHttpResp<DataBean>>() {
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

    public void deleteLocation(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().deleteLocation(param, new Observer<MdlBaseHttpResp<DataBean>>() {
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

    public void getSearchNewProductList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getSearchNewProductList(param, new Observer<MdlBaseHttpResp<ShoppingListBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<ShoppingListBean> mdlBaseHttpResp) {
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

    public void getSearchHotProductList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getSearchHotProductList(param, new Observer<MdlBaseHttpResp<ShoppingListBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<ShoppingListBean> mdlBaseHttpResp) {
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

    public void getTimeSkillHint(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getTimeSkillHint(param, new Observer<MdlBaseHttpResp<DataBean>>() {
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


    public void toPayOrder(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().toPayOrder(param, new Observer<MdlBaseHttpResp<CommitOrderBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<CommitOrderBean> mdlBaseHttpResp) {
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

    public void getOrderList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getOrderList(param, new Observer<MdlBaseHttpResp<MineOrderPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<MineOrderPageBean> mdlBaseHttpResp) {
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

    public void getProfitOrderList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getProfitOrderList(param, new Observer<MdlBaseHttpResp<ProfitOrderPageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<ProfitOrderPageBean> mdlBaseHttpResp) {
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

    public void getAfterOrderList(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getAfterOrderList(param, new Observer<MdlBaseHttpResp<AfterSalePageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<AfterSalePageBean> mdlBaseHttpResp) {
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
   public void getAfterOrderCancel(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getAfterOrderCancel(param, new Observer<MdlBaseHttpResp<DataBean>>() {
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

   public void getOrderDetail(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getOrderDetail(param, new Observer<MdlBaseHttpResp<OrderDetailBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<OrderDetailBean> mdlBaseHttpResp) {
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

   public void getCancelAfterSale(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getCancelAfterSale(param, new Observer<MdlBaseHttpResp<DataBean>>() {
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

   public void saveAfterSale(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().saveAfterSale(param, new Observer<MdlBaseHttpResp<DataBean>>() {
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

   public void getOrderAfterDetail(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getOrderAfterDetail(param, new Observer<MdlBaseHttpResp<AfterSaleDetailBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<AfterSaleDetailBean> mdlBaseHttpResp) {
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

   public void getOrderCancel(int code, String orderCode, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getOrderCancel(orderCode, new Observer<MdlBaseHttpResp<DataBean>>() {
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

    public void searchPayResult(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().searchPayResult(param, new Observer<MdlBaseHttpResp<PayResultBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<PayResultBean> mdlBaseHttpResp) {
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


    public void confirmOrder(int code, String orderCode, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().confirmOrder(orderCode, new Observer<MdlBaseHttpResp<DataBean>>() {
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

    public void commitEvaluateOrder(int code, Map params, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().commitEvaluateOrder(params, new Observer<MdlBaseHttpResp<DataBean>>() {
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

    public void getLogisticsInfo(int code, Map params, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getLogisticsInfo(params, new Observer<MdlBaseHttpResp<LogisticsBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<LogisticsBean> mdlBaseHttpResp) {
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

    public void setPayPassword(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().setPayPassword(param, new Observer<MdlBaseHttpResp<MdlUser>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
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

    public void getCourserDetail(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getCourserDetail(param, new Observer<MdlBaseHttpResp<FindLessonDetailBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<FindLessonDetailBean> mdlBaseHttpResp) {
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

    public void buyCourserByCurrency(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().buyCourserByCurrency(param, new Observer<MdlBaseHttpResp<DataBean>>() {
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

    public void commitVipOrder(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().commitVipOrder(param, new Observer<MdlBaseHttpResp<VipDataBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<VipDataBean> mdlBaseHttpResp) {
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


    public void getStoreBanner(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getStoreBanner(param, new Observer<MdlBaseHttpResp<FindStoreBannerBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<FindStoreBannerBean> mdlBaseHttpResp) {
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


    public void getWikiHomeData(int code,final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getWikiHomeData(new Observer<MdlBaseHttpResp<WikiBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<WikiBean> mdlBaseHttpResp) {
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

    public void getWikiHomeRecommendData(int code,final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getWikiHomeRecommendData(new Observer<MdlBaseHttpResp<WikiRecommendBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<WikiRecommendBean> mdlBaseHttpResp) {
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


    public void getWikiHomeCategoryData(int code,int parentId,final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getWikiHomeCategoryData(parentId,new Observer<MdlBaseHttpResp<WikiCategoryBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<WikiCategoryBean> mdlBaseHttpResp) {
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


    public void getCommitComment(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getCommitComment(param, new Observer<MdlBaseHttpResp<CommentBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<CommentBean> mdlBaseHttpResp) {
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


    public void getCommentDetail(int code, int param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getCommentDetail(param, new Observer<MdlBaseHttpResp<CommentDetailBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<CommentDetailBean> mdlBaseHttpResp) {
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

    public void getClubPicList(int code, String storeId,int pageNo,int pageSize, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getClubPicList(storeId,pageNo,pageSize, new Observer<MdlBaseHttpResp<PictureListBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<PictureListBean> mdlBaseHttpResp) {
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
   public void getClubVideoList(int code, String storeId,int pageNo,int pageSize, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getClubVideoList(storeId,pageNo,pageSize, new Observer<MdlBaseHttpResp<VideoListBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<VideoListBean> mdlBaseHttpResp) {
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


    public void getClubCommentPicList(int code, String storeId,int pageNo,int pageSize, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getClubCommentPicList(storeId,pageNo,pageSize, new Observer<MdlBaseHttpResp<PictureListBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<PictureListBean> mdlBaseHttpResp) {
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
    public void getClubCommentVideoList(int code, String storeId,int pageNo,int pageSize, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getClubCommentVideoList(storeId,pageNo,pageSize, new Observer<MdlBaseHttpResp<VideoListBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<VideoListBean> mdlBaseHttpResp) {
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


    public void getFindStoreListCondition(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getFindStoreListCondition(param, new Observer<MdlBaseHttpResp<FindStorePageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<FindStorePageBean> mdlBaseHttpResp) {
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

    public void getBusinessDistrict(int code, Map params , final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getBusinessDistrict(params, new Observer<MdlBaseHttpResp<FindStorePageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<FindStorePageBean> mdlBaseHttpResp) {
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

    public void getFindTrainerList(int code, int storeId, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getFindTrainerList(storeId, new Observer<MdlBaseHttpResp<FindTrainerBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<FindTrainerBean> mdlBaseHttpResp) {
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

    public void getFindStoreRecommendList(int code, int commendId, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getFindStoreRecommendList(commendId, new Observer<MdlBaseHttpResp<FindStoreRecommendBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<FindStoreRecommendBean> mdlBaseHttpResp) {
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

    public void getFindStoreCondition(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getFindStoreCondition(param, new Observer<MdlBaseHttpResp<FindStoreConditionBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<FindStoreConditionBean> mdlBaseHttpResp) {
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

    public void getShoppingDetail(int code, int param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getShoppingDetail(param, new Observer<MdlBaseHttpResp<ShoppingDetailBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<ShoppingDetailBean> mdlBaseHttpResp) {
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

    public void applySettledIn(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().applySettledIn(param, new Observer<MdlBaseHttpResp<DataBean>>() {
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


    public void saveBankCard(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().saveBankCard(param, new Observer<MdlBaseHttpResp<DataBean>>() {
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

    public void commitOrder(Map params, int code, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().commitOrder(params, new Observer<MdlBaseHttpResp<ShoppingCarBean>>() {
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

    public void commitOrder2(Map params, int code, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().commitOrder2(params, new Observer<MdlBaseHttpResp<CommitOrderBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                HomeModel.this.disposable = d;
            }

            @Override
            public void onNext(MdlBaseHttpResp<CommitOrderBean> mdlBaseHttpResp) {
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
        HttpDataHome.getInstance().getEnableCouponList(param, new Observer<MdlBaseHttpResp<MineCouponBean>>() {
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


    public void getEnableCoupon(int code, Map param, final OnLoadHttpDataListener listener) {
        HttpDataHome.getInstance().getEnableCoupon(param, new Observer<MdlBaseHttpResp<MineCouponBean>>() {
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

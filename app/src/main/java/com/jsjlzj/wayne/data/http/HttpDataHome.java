package com.jsjlzj.wayne.data.http;

import com.jsjlzj.wayne.data.api.StoreService;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import okhttp3.RequestBody;

/**
 * @ClassName: HttpDataHome
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/24 10:49
 */
public class HttpDataHome extends BaseHttpData {
    private static class SingletonHolder {
        private static final HttpDataHome INSTANCE = new HttpDataHome();
    }

    public static HttpDataHome getInstance() {
        return HttpDataHome.SingletonHolder.INSTANCE;
    }

    private StoreService service = create(StoreService.class);

    public void refreshUrl() {
        service=null;
        service = create(StoreService.class);
    }

    /**
     * 获取首页推荐数据
     * @param params
     * @param observer
     */
    public void getRecommendData(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestRecommend(body);
        setSubscribe(observable, observer);
    }

    /**
     * 获取首页淘学数据
     * @param params
     * @param observer
     */
    public void getAmoySchoolData(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestAmoySchool(body);
        setSubscribe(observable, observer);
    }


   /**
     * 获取首页赛事数据
     * @param params
     * @param observer
     */
    public void getMatchData(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestMatch(body);
        setSubscribe(observable, observer);
    }


   /**
     * 获取首页淘学列表数据
     * @param params
     * @param observer
     */
    public void getAmoyList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestAmoyList(body);
        setSubscribe(observable, observer);
    }


   /**
     * 获取首页赛事列表数据
     * @param params
     * @param observer
     */
    public void getMatchList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestMatchList(body);
        setSubscribe(observable, observer);
    }


   /**
     * 获取首页干货数据
     * @param params
     * @param observer
     */
    public void getDriedFoodData(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestDriedFood(body);
        setSubscribe(observable, observer);
    }

   /**
     * 获取首页干货列表数据
     * @param params
     * @param observer
     */
    public void getDriedFoodList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestDriedFoodList(body);
        setSubscribe(observable, observer);
    }
   /**
     * 获取首页资讯数据
     * @param params
     * @param observer
     */
    public void getInformationData(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestInformation(body);
        setSubscribe(observable, observer);
    }
   /**
     * 获取首页资讯列表数据
     * @param params
     * @param observer
     */
    public void getInformationList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestInformationList(body);
        setSubscribe(observable, observer);
    }
   /**
     * 获取首页产品数据
     * @param params
     * @param observer
     */
    public void getProductData(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestProduce(body);
        setSubscribe(observable, observer);
    }
   /**
     * 获取首页产品列表数据
     * @param params
     * @param observer
     */
    public void getProductList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestProductList(body);
        setSubscribe(observable, observer);
    }


   /**
     * 获取所有视频类别数据
     * @param params
     * @param observer
     */
    public void getAllClassicList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestAllClassicList(body);
        setSubscribe(observable, observer);
    }

   /**
     * 获取对应categoryId的热门学校（机构）数据
     * @param params
     * @param observer
     */
    public void getOrganizationList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestOrganizationList(body);
        setSubscribe(observable, observer);
    }
   /**
     * 获取视频列表数据
     * @param params
     * @param observer
     */
    public void getVideoList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestVideoList(body);
        setSubscribe(observable, observer);
    }
   /**
     * 点赞
     * @param params
     * @param observer
     */
    public void clickZan(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestClickZan(body);
        setSubscribe(observable, observer);
    }   /**
     * 取消点赞
     * @param params
     * @param observer
     */
    public void cancelZan(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestCancelZan(body);
        setSubscribe(observable, observer);
    }


    /**
     * 收藏
     * @param params
     * @param observer
     */
    public void clickCollect(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestClickCollect(body);
        setSubscribe(observable, observer);
    }

    /**
     * 取消收藏
     * @param params
     * @param observer
     */
    public void cancelCollect(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestCancelCollect(body);
        setSubscribe(observable, observer);
    }


    /**
     * 收藏
     * @param params
     * @param observer
     */
    public void clickFollow(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestClickFollow(body);
        setSubscribe(observable, observer);
    }

    /**
     * 取消收藏
     * @param params
     * @param observer
     */
    public void cancelFollow(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestCancelFollow(body);
        setSubscribe(observable, observer);
    }

    /**
     * 获取学习数据
     * @param params
     * @param observer
     */
    public void getLearnData(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestLearnData(body);
        setSubscribe(observable, observer);
    }

  /**
     * 获取章节列表数据
     * @param params
     * @param observer
     */
    public void getChapterList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestChapterList(body);
        setSubscribe(observable, observer);
    }

  /**
     * 获取章节题目列表数据
     * @param params
     * @param observer
     */
    public void getChapterSubjectList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestChapterSubjectList(body);
        setSubscribe(observable, observer);
    }

  /**
     * 保存答题记录
     * @param params
     * @param observer
     */
    public void getSaveAnswerRecord(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestSaveAnswerRecord(body);
        setSubscribe(observable, observer);
    }

  /**
     * 章节错误题目列表
     * @param params
     * @param observer
     */
    public void getWrongSubjectList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestWrongSubjectList(body);
        setSubscribe(observable, observer);
    }


    /**
     * 章节错误题目列表
     * @param params
     * @param observer
     */
    public void getExamSubjectList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestExamSubjectList(body);
        setSubscribe(observable, observer);
    }


    /**
     * 模拟考试-提交答案
     * @param params
     * @param observer
     */
    public void submitExamAnswer(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestSubjectExamAnswer(body);
        setSubscribe(observable, observer);
    }


    /**
     * 模拟考试-提交答案
     * @param params
     * @param observer
     */
    public void doneChapterAnswer(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestDoneChapterAnswer(body);
        setSubscribe(observable, observer);
    }


    /**
     * 模拟考试-错题集
     * @param params
     * @param observer
     */
    public void getTestResult(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestErrorResult(body);
        setSubscribe(observable, observer);
    }

    /**
     * 模拟考试-错题集
     * @param params
     * @param observer
     */
    public void getCurrentSubject(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestCurrentSubject(body);
        setSubscribe(observable, observer);
    }



}

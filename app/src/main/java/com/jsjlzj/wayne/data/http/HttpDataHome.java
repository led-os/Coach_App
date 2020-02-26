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


}

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
     * 获取首页推荐数据
     * @param params
     * @param observer
     */
    public void getAmoySchoolData(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestAmoySchool(body);
        setSubscribe(observable, observer);
    }


}

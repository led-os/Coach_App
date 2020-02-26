package com.jsjlzj.wayne.data.http;


import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SPUtil;
import com.jsjlzj.wayne.utils.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/4/16.
 */

public class HttpManager {
    private static final String USER_AGENT;
    private static final String Version;
    private static final String device;

    private String token;

    static {
        USER_AGENT = Utility.getUserAgent();
        device= Utility.getIMEI(MyApp.ME.getApplicationContext());
        Version = Utility.getVerName(MyApp.ME.getApplicationContext())+"";
    }

    public void setToken(String token){
        this.token = token;
    }

    private static final class MyHolder {
        private static final HttpManager INSTANCE = new HttpManager();
    }

    private HttpManager(){
        token = SPUtil.getTokenFromSP();
    }

    public static HttpManager getInstance() {
        return MyHolder.INSTANCE;
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }

    private Retrofit retrofit;

    public void init() {
        CookieJar cookieJar = new CookieJar() {
            //Cookie缓存区
            private final Map<String, List<Cookie>> cookiesMap = new HashMap<>();

            @Override
            public void saveFromResponse(HttpUrl arg0, List<Cookie> arg1) {
                //移除相同的url的Cookie
                String host = arg0.host();
                List<Cookie> cookiesList = cookiesMap.get(host);
                if (cookiesList != null) {
                    cookiesMap.remove(host);
                }
                //再重新天添加
                cookiesMap.put(host, arg1);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl arg0) {
                List<Cookie> cookiesList = cookiesMap.get(arg0.host());
                //注：这里不能返回null，否则会报NULLException的错误。
                //原因：当Request 连接到网络的时候，OkHttp会调用loadForRequest()
                return cookiesList != null ? cookiesList : new ArrayList<Cookie>();
            }
        };

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("mode", "ANDROID")
                        .header("device", device)
                        .header("Token", "123")
                        .header("version",Version)
                        //.header("Accept", "application/vnd.yourapi.v1.full+json")
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        };
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogAndToastUtil.log("日志",message);
            }
        });
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        GsonBuilder builder = new GsonBuilder();

        // Register an adapter to manage the date types as long values
        builder.registerTypeAdapter(Date.class,
                (JsonDeserializer<Date>) (json, typeoft, context) -> new Date(json.getAsJsonPrimitive().getAsLong()));


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(20000L, TimeUnit.MILLISECONDS)
                .writeTimeout(2000L, TimeUnit.MILLISECONDS)
                .connectTimeout(20000L, TimeUnit.MILLISECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(logInterceptor)
                .addInterceptor(new TokenInterceptor())
                .cookieJar(cookieJar)
                //其他配置
                .build();

                retrofit = new Retrofit.Builder()
                .baseUrl(HttpConstant.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(builder.create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    public void changeUrl(String url){
        HttpConstant.BASE_URL = url;
        init();
        HttpDataBasis.getInstance().refreshUrl();
        SPUtil.saveURL(HttpConstant.BASE_URL);
    }
}

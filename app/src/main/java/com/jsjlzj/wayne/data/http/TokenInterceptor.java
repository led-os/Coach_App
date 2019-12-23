package com.jsjlzj.wayne.data.http;

import android.text.TextUtils;

import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.data.api.BasisService;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.enums.EnumGetLoginType;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.utils.SPUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        if (isTokenExpired(response) && !TextUtils.isEmpty(SPUtil.getPwdFromSP())) {//根据和服务端的约定判断token过期
            //同步请求方式，获取最新的Token

            String newToken = getNewToken();
            //使用新的Token，创建新的请求
            Request newRequest = chain.request()
                    .newBuilder()
                    .header("token", newToken)
                    .header("Cookie", "JSESSIONID=" + newToken)
                    .build();
            //重新请求
            return chain.proceed(newRequest);
        }
        return response;
    }

    /**
     * 根据Response，判断Token是否失效
     *
     * @param response
     * @return
     */
    private boolean isTokenExpired(Response response) {
        return response.code() == HttpConstant.R_TOKEN_EXPIRE;
    }

    /**
     * 同步请求方式，获取最新的Token
     *
     * @return
     */
    private String getNewToken() throws IOException {
        // 通过一个特定的接口获取新的token，此处要用到同步的retrofit请求

        String account = TextUtils.isEmpty(SPUtil.getAccountFromSP()) ? SPUtil.getPhoneFromSP() : SPUtil.getAccountFromSP();

        Map<String, String> params = new HashMap<>();
        params.put("type", EnumGetLoginType.LOGIN_USE_PWD);
        params.put("password", SPUtil.getPwdFromSP());
        params.put("account", account);

        BasisService basisService = HttpManager.getInstance().getRetrofit().create(BasisService.class);
        Call<MdlBaseHttpResp<MdlUser>> call = basisService.getToken(HttpUtil.GenJsonParamRequestBody(params));
        MdlBaseHttpResp<MdlUser> resp = call.execute().body();

//        MdlUser mdlUser = (MdlUser) resp.getData().getData();
        MdlUser mdlUser = (MdlUser) resp.getData();
        MyApp.ME.user = mdlUser.getData();


        String token = mdlUser.getData().getToken();
        SPUtil.saveToken2SP(token);

        return token;
    }
}

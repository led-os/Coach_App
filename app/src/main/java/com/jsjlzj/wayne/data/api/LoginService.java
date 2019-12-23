package com.jsjlzj.wayne.data.api;


import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.Login.MdlQuestion;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.address.MalAddressProvince;
import com.jsjlzj.wayne.entity.dao.MdlVersion;
import com.jsjlzj.wayne.entity.message.MdlIsBothReply;
import com.jsjlzj.wayne.entity.store.MdlDict;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

//import com.jsjlzj.wayne.entity.Login.MdlDict;

/**
 * Created by Administrator on 2018/6/11.
 */

public interface LoginService {
    /**
     * 聊天室
     */
    //门店：不适合
    @POST(HttpConstant.API_MESSAGE_CV_UNSUITABLE)
    Observable<MdlBaseHttpResp> messageCVUnsuitable(@Body RequestBody requestBody);
    //教练 不敢兴趣
    @POST(HttpConstant.API_MESSAGE_POSISTION_UNSUITABLE)
    Observable<MdlBaseHttpResp> messagePosistionUnsuitable(@Body RequestBody requestBody);
    //是否回复过
    @POST(HttpConstant.API_MESSAGE_ISBOTHREPLY)
    Observable<MdlBaseHttpResp<MdlIsBothReply>> messageIsbothreply(@Body RequestBody requestBody);
    //保存普通消息
    @POST(HttpConstant.API_MESSAGE_SAVE_MESSAGE)
    Observable<MdlBaseHttpResp> messageSaveMessage(@Body RequestBody requestBody);
    //保存微信号
    @POST(HttpConstant.API_MESSAGE_SAVE_WECHAT)
    Observable<MdlBaseHttpResp> messageSaveWechat(@Body RequestBody requestBody);

    /**
     *
     */

    @POST(HttpConstant.API_GET_CHECK_CODE)
    Observable<MdlBaseHttpResp<MdlVersion>> getVersion(@Body RequestBody requestBody);

    @POST(HttpConstant.API_LOGIN)
    Call<MdlBaseHttpResp<MdlUser>> getToken(@Body RequestBody requestBody);

    @POST(HttpConstant.API_DICT_GETALL)
    Observable<MdlBaseHttpResp<MdlDict>> getAll(@Body RequestBody requestBody);


    @GET(HttpConstant.API_GET_TEST+"/{id}")
    Observable<String> getTest(@Path("id") Integer id);

    @POST(HttpConstant.API_GET_SMES)
    Observable<MdlBaseHttpResp> getSmes(@Body RequestBody requestBody);

    @GET(HttpConstant.API_LOGIN_GETOPENID)
    Observable<MdlBaseHttpResp<MdlUser>> byGetOpen(@Query(("openid")) String openid);

    @POST(HttpConstant.API_LOGIN_SMES)
    Observable<MdlBaseHttpResp<MdlUser>> loginBySmes(@Body RequestBody requestBody);

    @POST(HttpConstant.API_UPDATEPSD)
    Observable<MdlBaseHttpResp<MdlUser>> updatePsd(@Body RequestBody requestBody);

    @POST(HttpConstant.API_FAQ_LIST)
    Observable<MdlBaseHttpResp<MdlQuestion>> faqList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_LOGIN)
    Observable<MdlBaseHttpResp<MdlUser>> loginByPwd(@Body RequestBody requestBody);

    @POST(HttpConstant.API_LOGIN_WX)
    Observable<MdlBaseHttpResp<MdlUser>> loginByWX(@Body RequestBody requestBody);

    @POST(HttpConstant.API_RESET_PWD)
    Observable<MdlBaseHttpResp<MdlUser>> resetPwd(@Body RequestBody requestBody);

    @POST(HttpConstant.API_CHANGE_MOBILE)
    Observable<MdlBaseHttpResp<MdlUser>> changeMobile(@Body RequestBody requestBody);

    @POST(HttpConstant.API_BIND_WECHAT)
    Observable<MdlBaseHttpResp<MdlUser>> bindWechat(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SETPWD)
    Observable<MdlBaseHttpResp<MdlUser>> setpwd(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SWITCHIDENTITY)
    Observable<MdlBaseHttpResp<MdlUser>> switchIdentity(@Body RequestBody requestBody);

    @POST(HttpConstant.API_UNBIND_WECHAT)
    Observable<MdlBaseHttpResp<MdlUser>> unbindWeChat(@Body RequestBody requestBody);

    @POST(HttpConstant.API_QUESTION_BACK)
    Observable<MdlBaseHttpResp<MdlUser>> questionBack(@Body RequestBody requestBody);

    @POST(HttpConstant.API_GETAREAALL)
    Observable<MdlBaseHttpResp<MalAddressProvince>> getAreaAll(@Body RequestBody requestBody);
}

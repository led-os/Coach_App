package com.jsjlzj.wayne.data.api;


import com.google.gson.JsonObject;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.Login.MdlUpload;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.dao.MdlVersion;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2018/6/11.
 */

public interface BasisService {
    @POST(HttpConstant.API_YUNXIN_TEST)
    Call<JsonObject> yunxinTest(@Body RequestBody requestBody);

    @POST(HttpConstant.API_YUNXIN_UPDATA)
    Observable<MdlBaseHttpResp<MdlVersion>> yunxinUpta(@Body RequestBody requestBody);



    @POST(HttpConstant.API_GET_CHECK_CODE)
    Observable<MdlBaseHttpResp<MdlVersion>> getVersion(@Body RequestBody requestBody);

    @POST(HttpConstant.API_LOGIN)
    Call<MdlBaseHttpResp<MdlUser>> getToken(@Body RequestBody requestBody);


    @GET(HttpConstant.API_GET_TEST+"/{id}")
    Observable<String> getTest(@Path("id") Integer id);

    @POST(HttpConstant.API_POST_TEST)
    Observable<Map> postTest(@Body RequestBody requestBody);

    @Multipart
    @POST(HttpConstant.API_UPLOAD)//上次文件
//    Observable<MdlBaseHttpResp<MdlUpload>> upload(@Part("type") RequestBody type, @Part MultipartBody.Part file);
    Observable<MdlBaseHttpResp<MdlUpload>> upload( @Part MultipartBody.Part file);

    @Multipart
    @POST(HttpConstant.API_UPLOAD_VIDEO)//上次视频文件
//    Observable<MdlBaseHttpResp<MdlUpload>> upload(@Part("type") RequestBody type, @Part MultipartBody.Part file);
    Observable<MdlBaseHttpResp<MdlUpload>> uploadVideo( @Part MultipartBody.Part file);
}

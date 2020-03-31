package com.jsjlzj.wayne.data.http;


import com.jsjlzj.wayne.data.api.BasisService;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;

import java.io.File;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.functions.Function;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/4/17.
 */

public class HttpDataBasis extends BaseHttpData {
    private static class SingletonHolder {
        private static final HttpDataBasis INSTANCE = new HttpDataBasis();
    }

    public static HttpDataBasis getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private BasisService service = create(BasisService.class);

    public void refreshUrl() {
        service=null;
        service = create(BasisService.class);
    }

    public void getTest(Integer id, Observer observer) {
        Observable observable = service.getTest(id);
        setSubscribe(observable, observer);
    }

    public void postTest(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.postTest(body);
        setSubscribe(observable, observer);
    }



    public void getStatisticOEEMachine(Map params, Function<MdlBaseHttpResp, MdlBaseHttpResp> function, Observer<MdlBaseHttpResp> observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.getVersion(body);
        setSubscribe(observable,function, observer);
    }

//    public void upload(@EnumUploadPic.PicType String type, String picPath, Observer<MdlBaseHttpResp> observer) {
//        File file = new File(picPath);
//        RequestBody reqFile = RequestBody.create(MediaType.parse("image/png"), file);
//        MultipartBody.Part photo = MultipartBody.Part.createFormData("file", file.getName(), reqFile);//pic为key
//        RequestBody reqType = RequestBody.create(MediaType.parse("text/plain"), type);
//        Observable observable = service.upload(reqType, photo);
//        setSubscribe(observable, observer);
//    }
    public void upload(String picPath, Observer observer) {
        File file = new File(picPath);
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/png"), file);
        MultipartBody.Part photo = MultipartBody.Part.createFormData("file", file.getName(), reqFile);//pic为key
        Observable observable = service.upload( photo);
        setSubscribe(observable, observer);
    }


    public void uploadVideo(String picPath, Observer observer) {
        File file = new File(picPath);
        RequestBody reqFile = RequestBody.create(MediaType.parse("video/mp4"), file);
        MultipartBody.Part photo = MultipartBody.Part.createFormData("file", file.getName(), reqFile);//pic为key
        Observable observable = service.uploadVideo( photo);
        setSubscribe(observable, observer);
    }
}

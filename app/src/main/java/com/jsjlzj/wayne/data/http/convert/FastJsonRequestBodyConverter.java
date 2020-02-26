package com.jsjlzj.wayne.data.http.convert;

import com.alibaba.fastjson.JSON;

import org.jetbrains.annotations.NotNull;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Created by pantianxiong on 2018/4/21.
 * 描述：
 */
public class FastJsonRequestBodyConverter<T> implements Converter<T, RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    @Override
    public RequestBody convert(@NotNull T value) {
        return RequestBody.create(MEDIA_TYPE, JSON.toJSONBytes(value));
    }

}

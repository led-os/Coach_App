package com.jsjlzj.wayne.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.Login.MdlUser;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by Liu on 2018/3/12.
 */

public class JsonUtil {
    private static Gson gson = new Gson();

    public static<T> T fromJson(String resp, Type type){
        try{

            T model = gson.fromJson(resp, type);
            return model;
        }catch (Exception e){
            e.printStackTrace();
            LogAndToastUtil.log("json parse error");
            return null;
        }

    }
    public static<T> T fromJson(JsonObject obj, Type type){
        try{

            T model = gson.fromJson(obj, type);
            return model;
        }catch (Exception e){
            e.printStackTrace();
            LogAndToastUtil.log("json parse error");
            return null;
        }

    }

    public static<T> T fromJson(String resp, Class<T> cls) {
        try{
            T model = gson.fromJson(resp, cls);
            return model;
        }catch (Exception e){
            e.printStackTrace();
            LogAndToastUtil.log("json parse error");
            return null;
        }
    }
    public static<T> T fromJson(JsonObject obj, Class<T> cls) {
        try{
            T model = gson.fromJson(obj, cls);
            return model;
        }catch (Exception e){
            e.printStackTrace();
            LogAndToastUtil.log("json parse error");
            return null;
        }
    }

    public static String createJson(MdlUser.MdlUserBean user){
        return gson.toJson(user);
    }
    public static String createJson(Map map){
        return gson.toJson(map);
    }
    public static String createJson(MdlBaseHttpResp resp){
        return gson.toJson(resp);
    }
}

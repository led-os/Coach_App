package com.jsjlzj.wayne.data.http;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class HttpUtil {
    public static RequestBody GenJsonParamRequestBody(Map param) {

        String jsonObj = JsonUtil.createJson(param);
        try {
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObj);
            return requestBody;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String arrayToJson(List list) {
        Gson g = new Gson();
        return g.toJson(list);
    }


    public static Map<String, Object> obj2Map(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields(); // 获取对象对应类中的所有属性域
        for (int i = 0; i < fields.length; i++) {
            Object object = null;
            String varName = fields[i].getName();
            //varName = varName.toUpperCase();///将key置为大写，默认为对象的属性
            //boolean accessFlag = fields[i].isAccessible(); // 获取原来的访问控制权限
            fields[i].setAccessible(true);// 修改访问控制权限
            try {
                object = fields[i].get(obj); // 获取在对象中属性fields[i]对应的对象中的变量
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            } finally {
                map.put(varName, object);
                //fields[i].setAccessible(accessFlag);// 恢复访问控制权限
            }
        }
        return map;
    }
}

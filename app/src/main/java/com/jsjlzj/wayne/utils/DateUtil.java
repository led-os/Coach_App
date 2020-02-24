package com.jsjlzj.wayne.utils;

/**
 * @ClassName: DateUtil
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/15 23:36
 */
public class DateUtil {

    public static String getDownTimer(long remain){
        if(remain == 0){
            return "00:00";
        }
        StringBuilder result = new StringBuilder();
        remain = remain / 1000;
        long house,min,ss;
        if(remain > 60*60){
            house = remain / (60*60);
            long minss = remain % (60*60);
            ss = minss % 60;
            min = minss / 60;
            if(house < 10){
                result.append("0");
            }
            result.append(house+":");
            if(min < 10){
                result.append("0");
            }
            result.append(min+":");
            if(ss < 10){
                result.append("0");
            }
            result.append(ss);
        }else if(remain > 60){
            min = remain / 60;
            ss = remain % 60;
            if(min < 10){
                result.append("0");
            }
            result.append(min);
            result.append(":");
            if(ss < 10){
                result.append("0");
            }
            result.append(ss);
        }else if(remain > 1){
            if(remain < 10){
                result.append("0");
            }
            result.append(remain);
        }
        return result.toString();
    }
}

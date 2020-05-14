package com.jsjlzj.wayne.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: DateUtil
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/15 23:36
 */
public class DateUtil {


    public static String getNumByInteger(int integer){
        String result;
        if(integer > 10000000){
            float yi = integer*1.0f / 100000000;
            BigDecimal   b   =   new BigDecimal(yi);
            double   f2   =   b.setScale(2,   RoundingMode.HALF_UP).doubleValue();
            return f2+"亿";
        }else if(integer > 1000){
            float wan = integer *1.0f / 10000;
            BigDecimal   b   =   new BigDecimal(wan);
            double   f1   =   b.setScale(2,   RoundingMode.HALF_UP).doubleValue();
            return f1+"W";
        } else {
            return String.valueOf(integer);
        }
    }

    /**
     * 时间毫秒值转换指定格式时间
     *
     * @param timeInMillis
     * @param dateFormat
     * @return
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

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
            result.append("00:");
            if(remain < 10){
                result.append("0");
            }
            result.append(remain);
        }
        return result.toString();
    }
}

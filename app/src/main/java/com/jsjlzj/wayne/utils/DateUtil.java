package com.jsjlzj.wayne.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
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

    /**
     * 将指定格式的字符串转换成
     * @param time
     * @return
     */
    public static long getLongTimeByStyle(String time,String style){
        Date date = null;
        try {
            SimpleDateFormat format= new SimpleDateFormat(style);
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 考试时间倒计时
     * @param remain
     * @return
     */
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


    /**
     * 支付倒计时
     * @param remain 付款 14:59
     * @return
     */
    public static String getPayDownTimer(long remain){
        if(remain == 0){
            return "00:00";
        }
        StringBuilder result = new StringBuilder();
        remain = remain / 1000;
        long house,min,ss;
        if(remain > 60){
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


    /**
     * 订单详情 支付倒计时
     * @param remain 付款 14:59
     * @return
     */
    public static String getPayDownTimerFromDetail(long remain){
        if(remain <= 0){
            return "0秒";
        }
        StringBuilder result = new StringBuilder();
        remain = remain / 1000;
        long min,ss;
        if(remain > 60){
            min = remain / 60;
            if(min < 10){
                result.append("0");
            }
            result.append(min);
            result.append("分钟");
        }else if(remain > 1){
            if(remain < 10){
                result.append("0");
            }
            result.append(remain);
            result.append("秒");
        }
        return result.toString();
    }


    /**
     * 商城销售金额统一转换
     * @param f
     * @return
     */
    public static String getTwoDotByFloat(float f){
        //构造方法的字符格式这里如果小数不足2位,会以0补足.
        DecimalFormat decimalFormat =new DecimalFormat("0.00");
        //format 返回的是字符串
        String distanceString = decimalFormat.format(f/100);
        return distanceString;
    }


    /**
     * 蜂隐币统一转换
     * @param f
     * @return
     */
    public static String getTwoDotByFloatFY(float f){
        //构造方法的字符格式这里如果小数不足2位,会以0补足.
        DecimalFormat decimalFormat =new DecimalFormat("0.00");
        //format 返回的是字符串
        String distanceString = decimalFormat.format(f);
        return distanceString;
    }
}

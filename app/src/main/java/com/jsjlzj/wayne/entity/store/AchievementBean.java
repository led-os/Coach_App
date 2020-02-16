package com.jsjlzj.wayne.entity.store;

/**
 * @ClassName: AchievementBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/14 16:23
 */
public class AchievementBean {

    /**
     * 分数
     */
    private int fraction;

    private String time;

    private String number;

    public AchievementBean() {
    }

    public AchievementBean(int fraction, String number,String time) {
        this.fraction = fraction;
        this.time = time;
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getFraction() {
        return fraction;
    }

    public void setFraction(int fraction) {
        this.fraction = fraction;
    }
}

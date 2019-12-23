package com.jsjlzj.wayne.entity.store;

public class CalendarBean {
    private String interviewDate;
    private String interviewTime;
    private boolean isToDay;
    private boolean isNextMonthFlag;
    private boolean isShowTextColor;

    public boolean isShowTextColor() {
        return isShowTextColor;
    }

    public void setShowTextColor(boolean showTextColor) {
        isShowTextColor = showTextColor;
    }

    public CalendarBean(String interviewDate, String interviewTime, boolean isToDay, boolean isNextMonthFlag) {
        this.interviewDate = interviewDate;
        this.interviewTime = interviewTime;
        this.isToDay = isToDay;
        this.isNextMonthFlag = isNextMonthFlag;
    }

    public boolean isNextMonthFlag() {
        return isNextMonthFlag;
    }

    public void setNextMonthFlag(boolean nextMonthFlag) {
        isNextMonthFlag = nextMonthFlag;
    }

    public String getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(String interviewDate) {
        this.interviewDate = interviewDate;
    }

    public String getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(String interviewTime) {
        this.interviewTime = interviewTime;
    }

    public boolean isToDay() {
        return isToDay;
    }

    public void setToDay(boolean toDay) {
        isToDay = toDay;
    }


}

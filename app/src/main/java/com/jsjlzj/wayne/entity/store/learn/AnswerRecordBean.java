package com.jsjlzj.wayne.entity.store.learn;

import java.util.List;

/**
 * @ClassName: AnswerRecordBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/29 21:41
 */
public class AnswerRecordBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * endTime : string
         * score : 0
         * startTime : string
         * times : 0
         */

        private String endTime;
        private int score;
        private String startTime;
        private int times;

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public int getTimes() {
            return times;
        }

        public void setTimes(int times) {
            this.times = times;
        }
    }
}

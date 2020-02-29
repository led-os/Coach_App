package com.jsjlzj.wayne.entity.store.learn;

import java.io.Serializable;

/**
 * @ClassName: DoneChapterBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/29 17:21
 */
public class DoneChapterBean implements Serializable {

    /**
     * data : {"correctCount":0,"wrongCount":0}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * correctCount : 0
         * wrongCount : 0
         * "score": "分数",
         */

        private int correctCount;
        private int wrongCount;
        private int score;

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getCorrectCount() {
            return correctCount;
        }

        public void setCorrectCount(int correctCount) {
            this.correctCount = correctCount;
        }

        public int getWrongCount() {
            return wrongCount;
        }

        public void setWrongCount(int wrongCount) {
            this.wrongCount = wrongCount;
        }
    }
}

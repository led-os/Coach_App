package com.jsjlzj.wayne.entity.store.learn;

import java.util.List;

/**
 * @ClassName: ExamSubjectListBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/29 12:39
 */
public class ExamSubjectListBean {


    /**
     * data : {"topics":[{"id":1006,"topicNum":3,"name":"1+1=2?","type":3,"options":"[{\"k\":\"A\",\"c\":\"正确\"},{\"k\":\"B\",\"c\":\"错误\"}]","answer":"","analysis":"","wrongAnswer":""},{"id":1007,"topicNum":4,"name":"1+1=?","type":4,"options":null,"answer":"","analysis":"","wrongAnswer":""},{"id":1009,"topicNum":3,"name":"3333333333333333333","type":2,"options":"[{\"k\":\"A\",\"c\":\"333333333333333\"},{\"k\":\"B\",\"c\":\"33333\"},{\"k\":\"C\",\"c\":\"33\"},{\"k\":\"D\",\"c\":\"3333\"}]","answer":"","analysis":"","wrongAnswer":""},{"id":1010,"topicNum":7,"name":"接踵练习，运动环节向下运动时，描述正确的是?","type":1,"options":"[{\"k\":\"A\",\"c\":\"小腿在踝关节处屈\"},{\"k\":\"B\",\"c\":\"小腿在踝关节处伸\"},{\"k\":\"C\",\"c\":\"足在踝关节处屈\"},{\"k\":\"D\",\"c\":\"足在踝关节处伸\"}]","answer":"","analysis":"","wrongAnswer":""}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<AnswerBean> topics;

        public List<AnswerBean> getTopics() {
            return topics;
        }

        public void setTopics(List<AnswerBean> topics) {
            this.topics = topics;
        }
    }
}

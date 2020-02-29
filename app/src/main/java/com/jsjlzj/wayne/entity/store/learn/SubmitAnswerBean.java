package com.jsjlzj.wayne.entity.store.learn;

/**
 * @ClassName: SubmitAnswerBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/29 14:22
 */
public class SubmitAnswerBean {
    private String answer;
    private long topicId;

    public SubmitAnswerBean() {
    }

    public SubmitAnswerBean(String answer, long topicId) {
        this.answer = answer;
        this.topicId = topicId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }
}

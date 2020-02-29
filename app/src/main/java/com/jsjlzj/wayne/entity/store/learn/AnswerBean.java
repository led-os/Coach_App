package com.jsjlzj.wayne.entity.store.learn;

import java.io.Serializable;

/**
 * @ClassName: AnswerBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/14 23:54
 */
public class AnswerBean implements Serializable {

    private long id;
    /**
     * 题目类型
     */
    private int topicNum;

    /**
     * 题目标题
     */
    private String name;


    /**
     * 选项
     */
    private String options;

    /**
     * 1 单选  2 ： 多选   3 ： 判断   4 填空
     */
    private int type ;

    /**
     * 正确答案
     */
    private String answer;

    /**
     * 分析结果
     */
    private String analysis;

    private String wrongAnswer;
    /**
     * 是否已经答题
     */
    private boolean isAnswer;

    private boolean isTitleType;

    public boolean isTitleType() {
        return isTitleType;
    }

    public void setTitleType(boolean titleType) {
        isTitleType = titleType;
    }

    public boolean isAnswer() {
        return isAnswer;
    }

    public void setAnswer(boolean answer) {
        isAnswer = answer;
    }

    public AnswerBean() {
    }

    public AnswerBean(long id, int topicNum, String name, String options, int type, String answer, String analysis) {
        this.id = id;
        this.topicNum = topicNum;
        this.name = name;
        this.options = options;
        this.type = type;
        this.answer = answer;
        this.analysis = analysis;
    }

    public AnswerBean(long id, int topicNum, String name, String options, int type, String answer, String analysis,boolean isAnswer) {
        this.id = id;
        this.topicNum = topicNum;
        this.name = name;
        this.options = options;
        this.type = type;
        this.answer = answer;
        this.analysis = analysis;
        this.isAnswer = isAnswer;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTopicNum() {
        return topicNum;
    }

    public void setTopicNum(int topicNum) {
        this.topicNum = topicNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getWrongAnswer() {
        return wrongAnswer;
    }

    public void setWrongAnswer(String wrongAnswer) {
        this.wrongAnswer = wrongAnswer;
    }
}

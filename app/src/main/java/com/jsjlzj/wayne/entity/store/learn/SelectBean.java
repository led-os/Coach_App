package com.jsjlzj.wayne.entity.store.learn;

/**
 * @ClassName: SelectBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/15 12:11
 */
public class SelectBean {

    private String k;

    private String c;

    private boolean isSelect;

    private String answer;

    public SelectBean() {
    }


    public SelectBean(String k, String c, boolean isSelect,String answer) {
        this.k = k;
        this.c = c;
        this.isSelect = isSelect;
        this.answer = answer;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

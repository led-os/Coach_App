package com.jsjlzj.wayne.entity.trainer;

/**
 * @ClassName: ExpressionBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/20 9:47
 */
public class ExpressionBean {

    private int expressionRes;

    private String expressionDes;

    public ExpressionBean() {
    }

    public ExpressionBean(int expressionRes, String expressionDes) {
        this.expressionRes = expressionRes;
        this.expressionDes = expressionDes;
    }

    public int getExpressionRes() {
        return expressionRes;
    }

    public void setExpressionRes(int expressionRes) {
        this.expressionRes = expressionRes;
    }

    public String getExpressionDes() {
        return expressionDes;
    }

    public void setExpressionDes(String expressionDes) {
        this.expressionDes = expressionDes;
    }
}

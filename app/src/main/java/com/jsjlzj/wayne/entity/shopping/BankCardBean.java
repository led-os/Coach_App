package com.jsjlzj.wayne.entity.shopping;

import java.io.Serializable;

/**
 * @ClassName: BankCardBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/14 15:57
 */
public class BankCardBean implements Serializable {

    /**
     * bankName : 银行名称
     * branchName : 支行名称
     * cardNo : 卡号
     * id : id
     * userName : 用户姓名
     */

    private String bankName;
    private String branchName;
    private String cardNo;
    private int id;
    private String userName;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

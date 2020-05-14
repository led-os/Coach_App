package com.jsjlzj.wayne.entity.shopping;

/**
 * @ClassName: BankCardItemBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/14 16:09
 */
public class BankCardItemBean {

    /**
     * data : {"bankName":"银行名称","branchName":"支行名称","cardNo":"卡号","id":"id","userName":"用户姓名"}
     */

    private BankCardBean data;

    public BankCardBean getData() {
        return data;
    }

    public void setData(BankCardBean data) {
        this.data = data;
    }
}

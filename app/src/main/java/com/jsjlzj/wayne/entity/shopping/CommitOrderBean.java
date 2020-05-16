package com.jsjlzj.wayne.entity.shopping;

/**
 * @ClassName: CommitOrderBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/15 17:22
 */
public class CommitOrderBean {

    /**
     * data : {"orderCode":"订单号","payAmount":"支付金额"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * orderCode : 订单号
         * payAmount : 支付金额
         * outTradeNo: 第三方支付的流水号
         */

        private String orderCode;
        private String payAmount;
        private String outTradeNo;

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public String getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(String payAmount) {
            this.payAmount = payAmount;
        }

        public String getOutTradeNo() {
            return outTradeNo;
        }

        public void setOutTradeNo(String outTradeNo) {
            this.outTradeNo = outTradeNo;
        }
    }
}

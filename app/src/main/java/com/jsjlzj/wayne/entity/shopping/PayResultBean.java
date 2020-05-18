package com.jsjlzj.wayne.entity.shopping;

/**
 * @ClassName: PayResultBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/18 23:15
 */
public class PayResultBean {

    /**
     * data : {"orderCode":"200518231440479"}
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
         * orderCode : 200518231440479
         */

        private String orderCode;

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }
    }
}

package com.jsjlzj.wayne.entity.shopping;

import java.util.List;

/**
 * @ClassName: OrderDetailBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/26 22:34
 */
public class OrderDetailBean {

    /**
     * data : {"createTime":"下单时间","endPayTime":"取消时间-YYYY-MM-DD hh:mm:ss","expressPath":"快递最新轨迹","orderCode":"订单号","orderList":"订单信息列表","payCode":"支付交易号","payTime":"支付时间","receiverAddress":"收货地址","receiverName":"收货人","receiverPhone":"收货电话","showStatus":"订单状态 0,待支付|WAITPAY;1,已取消|CANCLEED;2,待发货|WAITSEND;3,待收货|WAITRECEIVED;5,已完成（待收货）；6,交易关闭|CLOSE","time":"取消时间","totalDiscountAmount":"总优惠-元"}
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
         * createTime : 下单时间
         * endPayTime : 取消时间-YYYY-MM-DD hh:mm:ss
         * expressPath : 快递最新轨迹
         * orderCode : 订单号
         * orderList : 订单信息列表
         * payCode : 支付交易号
         * payTime : 支付时间
         * receiverAddress : 收货地址
         * receiverName : 收货人
         * receiverPhone : 收货电话
         * showStatus : 订单状态 0,待支付|WAITPAY;1,已取消|CANCLEED;2,待发货|WAITSEND;3,待收货|WAITRECEIVED;5,已完成（待收货）；6,交易关闭|CLOSE
         * time : 取消时间
         * totalDiscountAmount : 总优惠-元
         */

        private String createTime;
        private String endPayTime;
        private String expressPath;
        private String orderCode;
        private List<MineOrderPageBean.DataBean.ResultBean.ListBean> orderList;
        private String payCode;
        private String payTime;
        private String receiverAddress;
        private String receiverName;
        private String receiverPhone;
        private int showStatus;
        private String time;
        private float totalDiscountAmount;


        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getEndPayTime() {
            return endPayTime;
        }

        public void setEndPayTime(String endPayTime) {
            this.endPayTime = endPayTime;
        }

        public String getExpressPath() {
            return expressPath;
        }

        public void setExpressPath(String expressPath) {
            this.expressPath = expressPath;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public List<MineOrderPageBean.DataBean.ResultBean.ListBean> getOrderList() {
            return orderList;
        }

        public void setOrderList(List<MineOrderPageBean.DataBean.ResultBean.ListBean> orderList) {
            this.orderList = orderList;
        }

        public String getPayCode() {
            return payCode;
        }

        public void setPayCode(String payCode) {
            this.payCode = payCode;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public String getReceiverAddress() {
            return receiverAddress;
        }

        public void setReceiverAddress(String receiverAddress) {
            this.receiverAddress = receiverAddress;
        }

        public String getReceiverName() {
            return receiverName;
        }

        public void setReceiverName(String receiverName) {
            this.receiverName = receiverName;
        }

        public String getReceiverPhone() {
            return receiverPhone;
        }

        public void setReceiverPhone(String receiverPhone) {
            this.receiverPhone = receiverPhone;
        }

        public int getShowStatus() {
            return showStatus;
        }

        public void setShowStatus(int showStatus) {
            this.showStatus = showStatus;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public float getTotalDiscountAmount() {
            return totalDiscountAmount;
        }

        public void setTotalDiscountAmount(float totalDiscountAmount) {
            this.totalDiscountAmount = totalDiscountAmount;
        }
    }
}

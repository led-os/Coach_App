package com.jsjlzj.wayne.entity.shopping;

/**
 * @ClassName: AfterSaleDetailBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/27 16:36
 */
public class AfterSaleDetailBean {

    /**
     * data : {"aftersaleReason":"退货原因","cancleTime":"撤销时间","createTime":"申请时间（申请提交 ）","discountAmount":"单个商品优惠金额 （分单位 需要 /100）","id":"订单商品ID","isEva":"是否评价#0,未评价|NO;1,已评价|YES","name":"名称","notpassTime":"审核不通过时间（退款失败确定时间）","orderCode":"订单号","phone":"客服电话","productCount":"数量","productPic":"图片地址","productPrice":"单价 （分单位 需要 /100）","productSpec":"规格","productStatus":"商品状态#1,已取消|已取消页面和撤销申请页面;7,申请退款|等待卖家处理页面;8,审核通过|商家同意页面;9,审核不通过|审核不通过页面;10,退款中|等待卖家处理页面;12,退款成功|商家同意页面;13,等待退款|等待卖家处理页面","refundAmount":"退款金额（分单位 需要 /100）","refundSuccessTime":"提款确定时间 （退款成功）"}
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
         * aftersaleReason : 退货原因
         * cancleTime : 撤销时间
         * createTime : 申请时间（申请提交 ）
         * discountAmount : 单个商品优惠金额 （分单位 需要 /100）
         * id : 订单商品ID
         * isEva : 是否评价#0,未评价|NO;1,已评价|YES
         * name : 名称
         * notpassTime : 审核不通过时间（退款失败确定时间）
         * orderCode : 订单号
         * phone : 客服电话
         * productCount : 数量
         * productPic : 图片地址
         * productPrice : 单价 （分单位 需要 /100）
         * productSpec : 规格
         * productStatus : 商品状态#1,已取消|已取消页面和撤销申请页面;7,申请退款|等待卖家处理页面;8,审核通过|商家同意页面;9,审核不通过|审核不通过页面;10,退款中|等待卖家处理页面;12,退款成功|商家同意页面;13,等待退款|等待卖家处理页面
         * refundAmount : 退款金额（分单位 需要 /100）
         * refundSuccessTime : 提款确定时间 （退款成功）
         */

        private String aftersaleReason;
        private String cancleTime;
        private String createTime;
        private String discountAmount;
        private String id;
        private String isEva;
        private String name;
        private String notpassTime;
        private String orderCode;
        private String phone;
        private int productCount;
        private String productPic;
        private float productPrice;
        private String productSpec;
        /**
         *  "商品状态#1,已取消|已取消页面和撤销申请页面;7,申请退款|等待卖家处理页面;8,审核通过|商家同意页面;9,审核不通过|审核不通过页面;10,退款中|等待卖家处理页面;12,退款成功|商家同意页面;13,等待退款|等待卖家处理页面",
         */
        private int productStatus;
        private float refundAmount;
        private String refundSuccessTime;

        public String getAftersaleReason() {
            return aftersaleReason;
        }

        public void setAftersaleReason(String aftersaleReason) {
            this.aftersaleReason = aftersaleReason;
        }

        public String getCancleTime() {
            return cancleTime;
        }

        public void setCancleTime(String cancleTime) {
            this.cancleTime = cancleTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDiscountAmount() {
            return discountAmount;
        }

        public void setDiscountAmount(String discountAmount) {
            this.discountAmount = discountAmount;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIsEva() {
            return isEva;
        }

        public void setIsEva(String isEva) {
            this.isEva = isEva;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNotpassTime() {
            return notpassTime;
        }

        public void setNotpassTime(String notpassTime) {
            this.notpassTime = notpassTime;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getProductCount() {
            return productCount;
        }

        public void setProductCount(int productCount) {
            this.productCount = productCount;
        }

        public String getProductPic() {
            return productPic;
        }

        public void setProductPic(String productPic) {
            this.productPic = productPic;
        }

        public float getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(float productPrice) {
            this.productPrice = productPrice;
        }

        public String getProductSpec() {
            return productSpec;
        }

        public void setProductSpec(String productSpec) {
            this.productSpec = productSpec;
        }

        public int getProductStatus() {
            return productStatus;
        }

        public void setProductStatus(int productStatus) {
            this.productStatus = productStatus;
        }

        public float getRefundAmount() {
            return refundAmount;
        }

        public void setRefundAmount(float refundAmount) {
            this.refundAmount = refundAmount;
        }

        public String getRefundSuccessTime() {
            return refundSuccessTime;
        }

        public void setRefundSuccessTime(String refundSuccessTime) {
            this.refundSuccessTime = refundSuccessTime;
        }
    }
}

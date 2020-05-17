package com.jsjlzj.wayne.entity.shopping;

/**
 * @ClassName: MineOrderBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/17 14:12
 */
public class MineOrderBean {
    /**
     * aftersaleType : 售后使用字段（维权类型 0，退货；1换货）
     * createTime : 下单时间
     * discountAmount : 单个商品优惠金额
     * isEva : 是否评价#0,未评价|NO;1,已评价|YES
     * name : 名称
     * orderCode : 订单号
     * payAmount : 实付总额
     * payCode : 支付交易号
     * productCount : 数量
     * productPic : 图片地址
     * productPrice : 单价（分单位需要转换下元 /100）
     * productSpec : 规格
     * receiverAddress : 收货地址
     * receiverName : 收货人
     * receiverPhone : 收货电话
     * sendTime : 发货时间
     * showStatus : 状态（看数字）：0,待支付|WAITPAY;1,已取消|CANCLEED;2,待发货|WAITSEND;3,待收货|WAITRECEIVED;4,待评价|RECEIVED;5,已完成|FINISH;6,交易关闭|CLOSE8
     * skuId : 商品ID
     * totalDiscountAmount : 总订单优惠金额（分单位需要转换下元 /100）
     */

    private int aftersaleType;
    private Long createTime;
    private String discountAmount;
    private int isEva;
    private String name;
    private String orderCode;
    private String payAmount;
    private String payCode;
    private String productCount;
    private String productPic;
    private String productPrice;
    private String productSpec;
    private String receiverAddress;
    private String receiverName;
    private String receiverPhone;
    private String sendTime;
    private int showStatus;
    private String skuId;
    private String totalDiscountAmount;

    public int getAftersaleType() {
        return aftersaleType;
    }

    public void setAftersaleType(int aftersaleType) {
        this.aftersaleType = aftersaleType;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }

    public int getIsEva() {
        return isEva;
    }

    public void setIsEva(int isEva) {
        this.isEva = isEva;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getProductCount() {
        return productCount;
    }

    public void setProductCount(String productCount) {
        this.productCount = productCount;
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductSpec() {
        return productSpec;
    }

    public void setProductSpec(String productSpec) {
        this.productSpec = productSpec;
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

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public int getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(int showStatus) {
        this.showStatus = showStatus;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public void setTotalDiscountAmount(String totalDiscountAmount) {
        this.totalDiscountAmount = totalDiscountAmount;
    }
}

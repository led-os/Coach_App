package com.jsjlzj.wayne.entity.shopping;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: MineOrderPageBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/17 14:11
 */
public class MineOrderPageBean {


    /**
     * data : {"pageNo":1,"pageSize":10,"totalCount":10,"result":[{"orderCode":200519222965039,"showStatus":2,"productCount":1,"payAmount":100,"list":[{"orderCode":200519222965039,"showStatus":null,"name":null,"productPic":"https://jh-tiyun-test.oss-cn-hangzhou.aliyuncs.com/file/20200519211654802_88GC853K.png","productSpec":"[{\"key\":\"容量\",\"value\":\"128G\"},{\"key\":\"颜色\",\"value\":\"玫瑰金\"}]","productPrice":100,"discountAmount":0,"productCount":1,"payAmount":null,"isEva":0,"sendTime":null,"totalDiscountAmount":null,"skuId":null,"payCode":null,"createTime":1589898559000,"receiverName":null,"receiverPhone":null,"receiverAddress":null,"aftersaleType":null,"orderProductId":1041}]}]}
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
         * pageNo : 1
         * pageSize : 10
         * totalCount : 10
         * result : [{"orderCode":200519222965039,"showStatus":2,"productCount":1,"payAmount":100,"list":[{"orderCode":200519222965039,"showStatus":null,"name":null,"productPic":"https://jh-tiyun-test.oss-cn-hangzhou.aliyuncs.com/file/20200519211654802_88GC853K.png","productSpec":"[{\"key\":\"容量\",\"value\":\"128G\"},{\"key\":\"颜色\",\"value\":\"玫瑰金\"}]","productPrice":100,"discountAmount":0,"productCount":1,"payAmount":null,"isEva":0,"sendTime":null,"totalDiscountAmount":null,"skuId":null,"payCode":null,"createTime":1589898559000,"receiverName":null,"receiverPhone":null,"receiverAddress":null,"aftersaleType":null,"orderProductId":1041}]}]
         */

        private int pageNo;
        private int pageSize;
        private int totalCount;
        private List<ResultBean> result;

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public static class ResultBean {
            /**
             * orderCode : 200519222965039
             * showStatus : 0,待支付|WAITPAY;1,已取消|CANCLEED;2,待发货|WAITSEND;3,待收货|WAITRECEIVED;4,待评价|RECEIVED;5,已完成|FINISH;6,交易关闭|CLOSE8
             * productCount : 1
             * payAmount : 100
             * list : [{"orderCode":200519222965039,"showStatus":null,"name":null,"productPic":"https://jh-tiyun-test.oss-cn-hangzhou.aliyuncs.com/file/20200519211654802_88GC853K.png","productSpec":"[{\"key\":\"容量\",\"value\":\"128G\"},{\"key\":\"颜色\",\"value\":\"玫瑰金\"}]","productPrice":100,"discountAmount":0,"productCount":1,"payAmount":null,"isEva":0,"sendTime":null,"totalDiscountAmount":null,"skuId":null,"payCode":null,"createTime":1589898559000,"receiverName":null,"receiverPhone":null,"receiverAddress":null,"aftersaleType":null,"orderProductId":1041}]
             */

            private String orderCode;
            private int showStatus;
            private int productCount;
            private float payAmount;
            private String time;
            private List<ListBean> list;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getOrderCode() {
                return orderCode;
            }

            public void setOrderCode(String orderCode) {
                this.orderCode = orderCode;
            }

            public int getShowStatus() {
                return showStatus;
            }

            public void setShowStatus(int showStatus) {
                this.showStatus = showStatus;
            }

            public int getProductCount() {
                return productCount;
            }

            public void setProductCount(int productCount) {
                this.productCount = productCount;
            }

            public float getPayAmount() {
                return payAmount;
            }

            public void setPayAmount(float payAmount) {
                this.payAmount = payAmount;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean implements Serializable {
                /**
                 * orderCode : 200519222965039
                 * showStatus : 状态（看数字）：0,待支付|WAITPAY;1,已取消|CANCLEED;2,待发货|WAITSEND;3,待收货|WAITRECEIVED;4,待评价|RECEIVED;5,已完成|FINISH;6,交易关闭|CLOSE8
                 * name : null
                 * productPic : https://jh-tiyun-test.oss-cn-hangzhou.aliyuncs.com/file/20200519211654802_88GC853K.png
                 * productSpec : [{"key":"容量","value":"128G"},{"key":"颜色","value":"玫瑰金"}]
                 * productPrice : 100
                 * discountAmount : 0
                 * productCount : 1
                 * payAmount : null
                 * isEva : 0
                 * sendTime : null
                 * totalDiscountAmount : null
                 * skuId : null
                 * payCode : null
                 * createTime : 1589898559000
                 * receiverName : null
                 * receiverPhone : null
                 * receiverAddress : null
                 * aftersaleType : null
                 * orderProductId : 1041
                 */

                private String orderCode;
                private int showStatus;
                private String name;
                private String productPic;
                private String productSpec;
                private float productPrice;
                private float discountAmount;
                private int productCount;
                private float payAmount;
                private int isEva;
                private String sendTime;
                private float totalDiscountAmount;
                private long skuId;
                private Object payCode;
                private String createTime;
                private Object receiverName;
                private Object receiverPhone;
                private Object receiverAddress;
                private Object aftersaleType;
                private int orderProductId;
                private String payTime;


                public String getPayTime() {
                    return payTime;
                }

                public void setPayTime(String payTime) {
                    this.payTime = payTime;
                }

                public String getOrderCode() {
                    return orderCode;
                }

                public void setOrderCode(String orderCode) {
                    this.orderCode = orderCode;
                }

                public int getShowStatus() {
                    return showStatus;
                }

                public void setShowStatus(int showStatus) {
                    this.showStatus = showStatus;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getProductPic() {
                    return productPic;
                }

                public void setProductPic(String productPic) {
                    this.productPic = productPic;
                }

                public String getProductSpec() {
                    return productSpec;
                }

                public void setProductSpec(String productSpec) {
                    this.productSpec = productSpec;
                }

                public float getProductPrice() {
                    return productPrice;
                }

                public void setProductPrice(float productPrice) {
                    this.productPrice = productPrice;
                }

                public float getDiscountAmount() {
                    return discountAmount;
                }

                public void setDiscountAmount(float discountAmount) {
                    this.discountAmount = discountAmount;
                }

                public int getProductCount() {
                    return productCount;
                }

                public void setProductCount(int productCount) {
                    this.productCount = productCount;
                }

                public float getPayAmount() {
                    return payAmount;
                }

                public void setPayAmount(float payAmount) {
                    this.payAmount = payAmount;
                }

                public int getIsEva() {
                    return isEva;
                }

                public void setIsEva(int isEva) {
                    this.isEva = isEva;
                }

                public String getSendTime() {
                    return sendTime;
                }

                public void setSendTime(String sendTime) {
                    this.sendTime = sendTime;
                }

                public float getTotalDiscountAmount() {
                    return totalDiscountAmount;
                }

                public void setTotalDiscountAmount(float totalDiscountAmount) {
                    this.totalDiscountAmount = totalDiscountAmount;
                }

                public long getSkuId() {
                    return skuId;
                }

                public void setSkuId(long skuId) {
                    this.skuId = skuId;
                }

                public Object getPayCode() {
                    return payCode;
                }

                public void setPayCode(Object payCode) {
                    this.payCode = payCode;
                }

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }

                public Object getReceiverName() {
                    return receiverName;
                }

                public void setReceiverName(Object receiverName) {
                    this.receiverName = receiverName;
                }

                public Object getReceiverPhone() {
                    return receiverPhone;
                }

                public void setReceiverPhone(Object receiverPhone) {
                    this.receiverPhone = receiverPhone;
                }

                public Object getReceiverAddress() {
                    return receiverAddress;
                }

                public void setReceiverAddress(Object receiverAddress) {
                    this.receiverAddress = receiverAddress;
                }

                public Object getAftersaleType() {
                    return aftersaleType;
                }

                public void setAftersaleType(Object aftersaleType) {
                    this.aftersaleType = aftersaleType;
                }

                public int getOrderProductId() {
                    return orderProductId;
                }

                public void setOrderProductId(int orderProductId) {
                    this.orderProductId = orderProductId;
                }
            }
        }
    }
}

package com.jsjlzj.wayne.entity.shopping;

import com.google.gson.annotations.SerializedName;

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
        private String url;
        private WxPayParamBean wxPayParam;

        public WxPayParamBean getWxPayParam() {
            return wxPayParam;
        }

        public void setWxPayParam(WxPayParamBean wxPayParam) {
            this.wxPayParam = wxPayParam;
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

        public String getOutTradeNo() {
            return outTradeNo;
        }

        public void setOutTradeNo(String outTradeNo) {
            this.outTradeNo = outTradeNo;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public class WxPayParamBean{

            /**
             * package : Sign=WXPay
             * appid : wx8bd15d5408120652
             * sign : 6AB9CCB44889B6EED7AADF15EC3DF321
             * partnerid : 1595772031
             * prepayid : wx28163147075889b509f6eaa71222524400
             * noncestr : 8t3JAl6eyEHmMdoG
             * timestamp : 1590654707
             */

            @SerializedName("package")
            private String packageX;
            private String appid;
            private String sign;
            private String partnerid;
            private String prepayid;
            private String noncestr;
            private String timestamp;

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public String getPartnerid() {
                return partnerid;
            }

            public void setPartnerid(String partnerid) {
                this.partnerid = partnerid;
            }

            public String getPrepayid() {
                return prepayid;
            }

            public void setPrepayid(String prepayid) {
                this.prepayid = prepayid;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String noncestr) {
                this.noncestr = noncestr;
            }

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }
        }
    }
}

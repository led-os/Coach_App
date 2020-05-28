package com.jsjlzj.wayne.entity.shopping;

import java.util.List;

/**
 * @ClassName: LogisticsBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/28 15:05
 */
public class LogisticsBean {

    /**
     * data : {"list":"物流列表","receiverAddress":"收货地址","receiverName":"收货人","receiverPhone":"收货电话"}
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
         * list : 物流列表
         * receiverAddress : 收货地址
         * receiverName : 收货人
         * receiverPhone : 收货电话
         */

        private List<LogisticsDetail> list;
        private String receiverAddress;
        private String receiverName;
        private String receiverPhone;

        public List<LogisticsDetail> getList() {
            return list;
        }

        public void setList(List<LogisticsDetail> list) {
            this.list = list;
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

        public class LogisticsDetail{

            private String createTime;//下单时间
            private String exInfo;//物流信息
            private String logisticCode;//快递单号
            private String receiverAddress;//收获地址
            private String receiverName;//收获人
            private String receiverPhone;//收获电话
            private String shipperCode;//快递公司编码

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getExInfo() {
                return exInfo;
            }

            public void setExInfo(String exInfo) {
                this.exInfo = exInfo;
            }

            public String getLogisticCode() {
                return logisticCode;
            }

            public void setLogisticCode(String logisticCode) {
                this.logisticCode = logisticCode;
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

            public String getShipperCode() {
                return shipperCode;
            }

            public void setShipperCode(String shipperCode) {
                this.shipperCode = shipperCode;
            }
        }
    }
}

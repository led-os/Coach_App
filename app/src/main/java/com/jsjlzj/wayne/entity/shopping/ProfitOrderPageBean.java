package com.jsjlzj.wayne.entity.shopping;

import java.util.List;

/**
 * @ClassName: ProfitOrderPageBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/6/4 0:39
 */
public class ProfitOrderPageBean {

    /**
     * data : {"pageNo":"当前页","pageSize":"每页大小","result":[{"createTime":"创建时间","img":"图片","orderAmount":"订单金额","orderNo":"交易订单号","profitAmount":"收益金额","settlementTime":"结算时间","title":"标题"}],"totalCount":"总记录数"}
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
         * pageNo : 当前页
         * pageSize : 每页大小
         * result : [{"createTime":"创建时间","img":"图片","orderAmount":"订单金额","orderNo":"交易订单号","profitAmount":"收益金额","settlementTime":"结算时间","title":"标题"}]
         * totalCount : 总记录数
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
             * createTime : 创建时间
             * img : 图片
             * orderAmount : 订单金额
             * orderNo : 交易订单号
             * profitAmount : 收益金额
             * settlementTime : 结算时间
             * title : 标题
             */

            private String createTime;
            private String img;
            private String orderAmount;
            private String orderNo;
            private String profitAmount;
            private String settlementTime;
            private String title;

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getOrderAmount() {
                return orderAmount;
            }

            public void setOrderAmount(String orderAmount) {
                this.orderAmount = orderAmount;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public String getProfitAmount() {
                return profitAmount;
            }

            public void setProfitAmount(String profitAmount) {
                this.profitAmount = profitAmount;
            }

            public String getSettlementTime() {
                return settlementTime;
            }

            public void setSettlementTime(String settlementTime) {
                this.settlementTime = settlementTime;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}

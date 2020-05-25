package com.jsjlzj.wayne.entity.shopping;

import java.util.List;

/**
 * @ClassName: AfterSalePageBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/25 22:21
 */
public class AfterSalePageBean {

    /**
     * data : {"pageNo":"当前页","pageSize":"每页大小","result":[{"amount":"退款额-元","getMyAftersaleOrderResponseVo":{"amount":"退款额-元","id":"订单商品ID","name":"名称","orderCode":"订单号","productPic":"图片地址","productSpec":"规格"},"orderCode":"订单号","status":"7、8、10、13->退货中，1-〉退货取消，12-》退货成功，   9-〉退货失败"}],"totalCount":"总记录数"}
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
         * result : [{"amount":"退款额-元","getMyAftersaleOrderResponseVo":{"amount":"退款额-元","id":"订单商品ID","name":"名称","orderCode":"订单号","productPic":"图片地址","productSpec":"规格"},"orderCode":"订单号","status":"7、8、10、13->退货中，1-〉退货取消，12-》退货成功，   9-〉退货失败"}]
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
             * amount : 退款额-元
             * getMyAftersaleOrderResponseVo : {"amount":"退款额-元","id":"订单商品ID","name":"名称","orderCode":"订单号","productPic":"图片地址","productSpec":"规格"}
             * orderCode : 订单号
             * status : 7、8、10、13->退货中，1-〉退货取消，12-》退货成功，   9-〉退货失败
             */

            private float amount;
            private GetMyAftersaleOrderResponseVoBean getMyAftersaleOrderResponseVo;
            private String orderCode;
            private int status;

            public float getAmount() {
                return amount;
            }

            public void setAmount(float amount) {
                this.amount = amount;
            }

            public GetMyAftersaleOrderResponseVoBean getGetMyAftersaleOrderResponseVo() {
                return getMyAftersaleOrderResponseVo;
            }

            public void setGetMyAftersaleOrderResponseVo(GetMyAftersaleOrderResponseVoBean getMyAftersaleOrderResponseVo) {
                this.getMyAftersaleOrderResponseVo = getMyAftersaleOrderResponseVo;
            }

            public String getOrderCode() {
                return orderCode;
            }

            public void setOrderCode(String orderCode) {
                this.orderCode = orderCode;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public static class GetMyAftersaleOrderResponseVoBean {
                /**
                 * amount : 退款额-元
                 * id : 订单商品ID
                 * name : 名称
                 * orderCode : 订单号
                 * productPic : 图片地址
                 * productSpec : 规格
                 */

                private float amount;
                private String id;
                private String name;
                private String orderCode;
                private String productPic;
                private String productSpec;

                public float getAmount() {
                    return amount;
                }

                public void setAmount(float amount) {
                    this.amount = amount;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
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
            }
        }
    }
}

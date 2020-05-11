package com.jsjlzj.wayne.entity.shopping;

import java.util.List;

/**
 * @ClassName: ShoppingPageBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/11 17:23
 */
public class ShoppingPageBean {

    /**
     * data : {"pageNo":"当前页","pageSize":"每页大小","result":[{"id":"spuId","name":"产品名称","originalPrice":"市场价","pic":"产品图片","price":"产品价格","promotionPrice":"促销价格","sale":"销量"}],"totalCount":"总记录数"}
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
         * result : [{"id":"spuId","name":"产品名称","originalPrice":"市场价","pic":"产品图片","price":"产品价格","promotionPrice":"促销价格","sale":"销量"}]
         * totalCount : 总记录数
         */

        private int pageNo;
        private int pageSize;
        private int totalCount;
        private List<ShoppingBean> result;

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

        public List<ShoppingBean> getResult() {
            return result;
        }

        public void setResult(List<ShoppingBean> result) {
            this.result = result;
        }
    }
}

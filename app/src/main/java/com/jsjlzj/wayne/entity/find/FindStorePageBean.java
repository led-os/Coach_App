package com.jsjlzj.wayne.entity.find;

import java.util.List;

/**
 * @ClassName: FindStorePageBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/6/25 12:32
 */
public class FindStorePageBean {


    /**
     * data : {"pageNo":"当前页","pageSize":"每页大小","result":[{"businessDistrict":"string","distance":0,"id":0,"lessons":[{"id":0,"name":"string","originPrice":0,"price":0,"type":0}],"newStoreFlag":0,"score":0,"selfDesc":"string","storeLogo":"string","storeName":"string","storeType":0}],"totalCount":"总记录数"}
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
         * result : [{"businessDistrict":"string","distance":0,"id":0,"lessons":[{"id":0,"name":"string","originPrice":0,"price":0,"type":0}],"newStoreFlag":0,"score":0,"selfDesc":"string","storeLogo":"string","storeName":"string","storeType":0}]
         * totalCount : 总记录数
         */

        private int pageNo;
        private int pageSize;
        private int totalCount;
        private List<FindStoreBean> result;

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

        public List<FindStoreBean> getResult() {
            return result;
        }

        public void setResult(List<FindStoreBean> result) {
            this.result = result;
        }

    }
}

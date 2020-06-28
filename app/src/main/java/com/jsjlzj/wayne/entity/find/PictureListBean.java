package com.jsjlzj.wayne.entity.find;

import java.util.List;

/**
 * @ClassName: PictureListBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/6/28 14:44
 */
public class PictureListBean {


    /**
     * data : {"pageNo":"当前页","pageSize":"每页大小","result":["string"],"totalCount":"总记录数"}
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
         * result : ["string"]
         * totalCount : 总记录数
         */

        private int pageNo;
        private int pageSize;
        private int totalCount;
        private List<String> result;

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

        public List<String> getResult() {
            return result;
        }

        public void setResult(List<String> result) {
            this.result = result;
        }
    }
}

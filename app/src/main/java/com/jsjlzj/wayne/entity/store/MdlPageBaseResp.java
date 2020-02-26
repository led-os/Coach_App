package com.jsjlzj.wayne.entity.store;

import java.util.List;

/**
 * @ClassName: MdlPageBaseResp
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/25 14:11
 */
public class MdlPageBaseResp<T> {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public class DataBean {
        private int pageNo;
        private int pageSize;
        private List<T> result;
        private int totalCount;

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

        public List<T> getResult() {
            return result;
        }

        public void setResult(List<T> result) {
            this.result = result;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }
    }
}

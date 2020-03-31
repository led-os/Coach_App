package com.jsjlzj.wayne.entity.store.search;

import java.util.List;

/**
 * @ClassName: ChannelPageBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/3/22 0:46
 */
public class ChannelPageBean {

    /**
     * data : {"pageNo":"当前页","pageSize":"每页大小","result":[{"fansCount":0,"headImg":"string","id":0,"isFollower":false,"name":"string"}],"totalCount":"总记录数"}
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
         * result : [{"fansCount":0,"headImg":"string","id":0,"isFollower":false,"name":"string"}]
         * totalCount : 总记录数
         */

        private int pageNo;
        private int pageSize;
        private int totalCount;
        private List<ChannelListBean> result;

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

        public List<ChannelListBean> getResult() {
            return result;
        }

        public void setResult(List<ChannelListBean> result) {
            this.result = result;
        }

    }
}

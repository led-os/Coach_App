package com.jsjlzj.wayne.entity.find;

import java.util.List;

/**
 * @ClassName: VideoListBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/6/28 15:35
 */
public class VideoListBean {

    /**
     * data : {"pageNo":"当前页","pageSize":"每页大小","result":[{"title":"string","url":"string","videoImg":"string"}],"totalCount":"总记录数"}
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
         * result : [{"title":"string","url":"string","videoImg":"string"}]
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
             * title : string
             * url : string
             * videoImg : string
             */

            private String title;
            private String url;
            private String videoImg;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getVideoImg() {
                return videoImg;
            }

            public void setVideoImg(String videoImg) {
                this.videoImg = videoImg;
            }
        }
    }
}

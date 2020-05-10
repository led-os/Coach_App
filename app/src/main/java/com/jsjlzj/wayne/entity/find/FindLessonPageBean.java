package com.jsjlzj.wayne.entity.find;

import java.util.List;

/**
 * @ClassName: FindLessonPageBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/7 22:22
 */
public class FindLessonPageBean {

    /**
     * data : {"pageNo":"当前页","pageSize":"每页大小","result":[{"categoryId":"类别ID","coverImg":"封面","id":"ID","isFree":"是否免费 1:是 0:否","lessonCount":"课程数(目录数量)","lessonDesc":"课程简介","originPrice":"原价","price":"实际支付价格","studyPersons":"学习人数","teacherAvatar":"导师头像","teacherDesc":"导师简介","teacherName":"导师姓名","title":"标题"}],"totalCount":"总记录数"}
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
         * result : [{"categoryId":"类别ID","coverImg":"封面","id":"ID","isFree":"是否免费 1:是 0:否","lessonCount":"课程数(目录数量)","lessonDesc":"课程简介","originPrice":"原价","price":"实际支付价格","studyPersons":"学习人数","teacherAvatar":"导师头像","teacherDesc":"导师简介","teacherName":"导师姓名","title":"标题"}]
         * totalCount : 总记录数
         */

        private int pageNo;
        private int pageSize;
        private int totalCount;
        private List<FindLessonBean> result;

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

        public List<FindLessonBean> getResult() {
            return result;
        }

        public void setResult(List<FindLessonBean> result) {
            this.result = result;
        }
    }
}

package com.jsjlzj.wayne.entity.find;

import java.util.List;

/**
 * @ClassName: OptimizationData2Bean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/4/30 20:45
 */
public class OptimizationData2Bean {


    /**
     * data : {"categoryList":[{"categoryDesc":"简介","categoryId":"类别ID","coverImg":"图片","lessonList":[{"categoryId":"类别ID","coverImg":"封面","id":"ID","isFree":"是否免费 1:是 0:否","lessonCount":"课程数(目录数量)","lessonDesc":"课程简介","originPrice":"原价","price":"实际支付价格","studyPersons":"学习人数","teacherAvatar":"导师头像","teacherDesc":"导师简介","teacherName":"导师姓名","title":"标题"}],"name":"名称"}],"fourList":[{"categoryId":"类别ID","coverImg":"封面","id":"ID","isFree":"是否免费 1:是 0:否","lessonCount":"课程数(目录数量)","lessonDesc":"课程简介","originPrice":"原价","price":"实际支付价格","studyPersons":"学习人数","teacherAvatar":"导师头像","teacherDesc":"导师简介","teacherName":"导师姓名","title":"标题"}],"jianzhiList":[{"categoryId":"类别ID","coverImg":"封面","id":"ID","isFree":"是否免费 1:是 0:否","lessonCount":"课程数(目录数量)","lessonDesc":"课程简介","originPrice":"原价","price":"实际支付价格","studyPersons":"学习人数","teacherAvatar":"导师头像","teacherDesc":"导师简介","teacherName":"导师姓名","title":"标题"}],"jingxuanList":[{"categoryId":"类别ID","coverImg":"封面","id":"ID","isFree":"是否免费 1:是 0:否","lessonCount":"课程数(目录数量)","lessonDesc":"课程简介","originPrice":"原价","price":"实际支付价格","studyPersons":"学习人数","teacherAvatar":"导师头像","teacherDesc":"导师简介","teacherName":"导师姓名","title":"标题"}],"yundongList":[{"categoryId":"类别ID","coverImg":"封面","id":"ID","isFree":"是否免费 1:是 0:否","lessonCount":"课程数(目录数量)","lessonDesc":"课程简介","originPrice":"原价","price":"实际支付价格","studyPersons":"学习人数","teacherAvatar":"导师头像","teacherDesc":"导师简介","teacherName":"导师姓名","title":"标题"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<CategoryListBean> categoryList;
        private List<FindLessonBean> fourList;
        private List<FindLessonBean> jianzhiList;
        private List<FindLessonBean> jingxuanList;
        private List<FindLessonBean> yundongList;

        public List<CategoryListBean> getCategoryList() {
            return categoryList;
        }

        public void setCategoryList(List<CategoryListBean> categoryList) {
            this.categoryList = categoryList;
        }

        public List<FindLessonBean> getFourList() {
            return fourList;
        }

        public void setFourList(List<FindLessonBean> fourList) {
            this.fourList = fourList;
        }

        public List<FindLessonBean> getJianzhiList() {
            return jianzhiList;
        }

        public void setJianzhiList(List<FindLessonBean> jianzhiList) {
            this.jianzhiList = jianzhiList;
        }

        public List<FindLessonBean> getJingxuanList() {
            return jingxuanList;
        }

        public void setJingxuanList(List<FindLessonBean> jingxuanList) {
            this.jingxuanList = jingxuanList;
        }

        public List<FindLessonBean> getYundongList() {
            return yundongList;
        }

        public void setYundongList(List<FindLessonBean> yundongList) {
            this.yundongList = yundongList;
        }

        public static class CategoryListBean {
            /**
             * categoryDesc : 简介
             * categoryId : 类别ID
             * coverImg : 图片
             * lessonList : [{"categoryId":"类别ID","coverImg":"封面","id":"ID","isFree":"是否免费 1:是 0:否","lessonCount":"课程数(目录数量)","lessonDesc":"课程简介","originPrice":"原价","price":"实际支付价格","studyPersons":"学习人数","teacherAvatar":"导师头像","teacherDesc":"导师简介","teacherName":"导师姓名","title":"标题"}]
             * name : 名称
             */

            private String categoryDesc;
            private int categoryId;
            private String coverImg;
            private String name;
            private List<FindLessonBean> lessonList;

            public String getCategoryDesc() {
                return categoryDesc;
            }

            public void setCategoryDesc(String categoryDesc) {
                this.categoryDesc = categoryDesc;
            }

            public int getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(int categoryId) {
                this.categoryId = categoryId;
            }

            public String getCoverImg() {
                return coverImg;
            }

            public void setCoverImg(String coverImg) {
                this.coverImg = coverImg;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<FindLessonBean> getLessonList() {
                return lessonList;
            }

            public void setLessonList(List<FindLessonBean> lessonList) {
                this.lessonList = lessonList;
            }

        }
    }
}

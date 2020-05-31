package com.jsjlzj.wayne.entity.find;

import com.jsjlzj.wayne.entity.store.home.BannerBean;

import java.util.List;

/**
 * @ClassName: OptimizationData1Bean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/4/30 20:41
 */
public class OptimizationData1Bean {


    /**
     * data : {"bannerList":[{"id":0,"link":"string","sort":0,"title":"string","url":"string"}],"categoryList":[{"categoryDesc":"简介","categoryId":"类别ID","coverImg":"图片","name":"名称"}],"freeExperienceList":[{"categoryId":"类别ID","coverImg":"封面","id":"ID","isFree":"是否免费 1:是 0:否","lessonCount":"课程数(目录数量)","lessonDesc":"课程简介","originPrice":"原价","price":"实际支付价格","studyPersons":"学习人数","teacherAvatar":"导师头像","teacherDesc":"导师简介","teacherName":"导师姓名","title":"标题"}],"hotList":[{"categoryId":"类别ID","coverImg":"封面","id":"ID","isFree":"是否免费 1:是 0:否","lessonCount":"课程数(目录数量)","lessonDesc":"课程简介","originPrice":"原价","price":"实际支付价格","studyPersons":"学习人数","teacherAvatar":"导师头像","teacherDesc":"导师简介","teacherName":"导师姓名","title":"标题"}],"hotListeningList":[{"categoryId":"类别ID","coverImg":"封面","id":"ID","isFree":"是否免费 1:是 0:否","lessonCount":"课程数(目录数量)","lessonDesc":"课程简介","originPrice":"原价","price":"实际支付价格","studyPersons":"学习人数","teacherAvatar":"导师头像","teacherDesc":"导师简介","teacherName":"导师姓名","title":"标题"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<BannerBean> bannerList;
        private List<CategoryListBean> categoryList;
        private List<FindLessonBean> freeExperienceList;
        private List<FindLessonBean> hotList;
        private List<FindLessonBean> hotListeningList;
        private boolean isVip;

        public boolean isVip() {
            return isVip;
        }

        public void setVip(boolean vip) {
            isVip = vip;
        }

        public List<BannerBean> getBannerList() {
            return bannerList;
        }

        public void setBannerList(List<BannerBean> bannerList) {
            this.bannerList = bannerList;
        }

        public List<CategoryListBean> getCategoryList() {
            return categoryList;
        }

        public void setCategoryList(List<CategoryListBean> categoryList) {
            this.categoryList = categoryList;
        }

        public List<FindLessonBean> getFreeExperienceList() {
            return freeExperienceList;
        }

        public void setFreeExperienceList(List<FindLessonBean> freeExperienceList) {
            this.freeExperienceList = freeExperienceList;
        }

        public List<FindLessonBean> getHotList() {
            return hotList;
        }

        public void setHotList(List<FindLessonBean> hotList) {
            this.hotList = hotList;
        }

        public List<FindLessonBean> getHotListeningList() {
            return hotListeningList;
        }

        public void setHotListeningList(List<FindLessonBean> hotListeningList) {
            this.hotListeningList = hotListeningList;
        }


        public static class CategoryListBean {
            /**
             * categoryDesc : 简介
             * categoryId : 类别ID
             * coverImg : 图片
             * name : 名称
             */

            private String categoryDesc;
            private String categoryId;
            private String coverImg;
            private String name;

            public String getCategoryDesc() {
                return categoryDesc;
            }

            public void setCategoryDesc(String categoryDesc) {
                this.categoryDesc = categoryDesc;
            }

            public String getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(String categoryId) {
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
        }

    }
}

package com.jsjlzj.wayne.entity.find;

import java.util.List;

/**
 * @ClassName: FindTrainerBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/6/27 10:22
 */
public class FindTrainerBean  {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1024
         * name : Pac
         * headImg : https://jh-tiyun-test.oss-cn-hangzhou.aliyuncs.com/file/20200622171912372_GY51RRW2.jpg
         * workYears : 7
         * trainerType : 体能训练
         * expertiseArea : 体能训练 减脂 塑型 增肌
         */

        private int id;
        private String name;
        private String headImg;
        private int workYears;
        private String trainerType;
        private String expertiseArea;
        private boolean isSelect;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public int getWorkYears() {
            return workYears;
        }

        public void setWorkYears(int workYears) {
            this.workYears = workYears;
        }

        public String getTrainerType() {
            return trainerType;
        }

        public void setTrainerType(String trainerType) {
            this.trainerType = trainerType;
        }

        public String getExpertiseArea() {
            return expertiseArea;
        }

        public void setExpertiseArea(String expertiseArea) {
            this.expertiseArea = expertiseArea;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }
    }
}

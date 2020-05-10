package com.jsjlzj.wayne.entity.find;

import java.util.List;

/**
 * @ClassName: FindCategoryBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/7 21:51
 */
public class FindCategoryBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * categoryDesc : 简介
         * categoryId : 类别ID
         * coverImg : 图片
         * name : 名称
         */

        private String categoryDesc;
        private int categoryId;
        private String coverImg;
        private String name;

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
    }
}

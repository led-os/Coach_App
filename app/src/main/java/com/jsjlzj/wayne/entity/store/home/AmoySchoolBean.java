package com.jsjlzj.wayne.entity.store.home;

import java.util.List;

/**
 * @ClassName: AmoySchoolBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/24 14:57
 */
public class AmoySchoolBean {

    /**
     * data : {"banner":[{"id":0,"link":"string","sort":0,"title":"string","url":"string"}],"category":[{"coverImg":"string","enrollCount":0,"id":0,"name":"string"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<BannerBean> banner;
        private List<CategoryBean> category;

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<CategoryBean> getCategory() {
            return category;
        }

        public void setCategory(List<CategoryBean> category) {
            this.category = category;
        }

    }
}

package com.jsjlzj.wayne.entity.store.learn;

import com.jsjlzj.wayne.entity.store.home.BannerBean;

import java.util.List;

/**
 * @ClassName: LearnBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/27 20:30
 */
public class LearnBean {

    /**
     * data : {"banner":[{"id":0,"link":"string","sort":0,"title":"string","url":"string"}],"library":[{"coverImg":"string","id":0,"title":"string"}]}
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
        private List<LibraryBean> library;

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<LibraryBean> getLibrary() {
            return library;
        }

        public void setLibrary(List<LibraryBean> library) {
            this.library = library;
        }


    }
}

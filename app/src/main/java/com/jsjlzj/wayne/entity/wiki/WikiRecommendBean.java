package com.jsjlzj.wayne.entity.wiki;

import com.jsjlzj.wayne.entity.store.home.BannerBean;
import com.jsjlzj.wayne.entity.store.home.VideoBean;

import java.util.List;

/**
 * @ClassName: WikiRecommendBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/7/21 22:01
 */
public class WikiRecommendBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * articleList : 文章列表
         * category : {"banners":[{"id":0,"link":"string","sort":0,"title":"string","url":"string"}],"contentCount":0,"followCount":0,"id":"分类id","imageUrl":"string","leftCategoryList":[{"id":"分类id","name":"名称"}],"name":"名称","viewCount":0}
         */

        private List<VideoBean> articleList;
        private CategoryBean category;

        public List<VideoBean> getArticleList() {
            return articleList;
        }

        public void setArticleList(List<VideoBean> articleList) {
            this.articleList = articleList;
        }

        public CategoryBean getCategory() {
            return category;
        }

        public void setCategory(CategoryBean category) {
            this.category = category;
        }

        public static class CategoryBean {
            /**
             * banners : [{"id":0,"link":"string","sort":0,"title":"string","url":"string"}]
             * contentCount : 0
             * followCount : 0
             * id : 分类id
             * imageUrl : string
             * leftCategoryList : [{"id":"分类id","name":"名称"}]
             * name : 名称
             * viewCount : 0
             */

            private int contentCount;
            private int followCount;
            private String id;
            private String imageUrl;
            private String name;
            private int viewCount;
            private List<BannerBean> banners;
            private List<LeftCategoryListBean> leftCategoryList;

            public int getContentCount() {
                return contentCount;
            }

            public void setContentCount(int contentCount) {
                this.contentCount = contentCount;
            }

            public int getFollowCount() {
                return followCount;
            }

            public void setFollowCount(int followCount) {
                this.followCount = followCount;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getViewCount() {
                return viewCount;
            }

            public void setViewCount(int viewCount) {
                this.viewCount = viewCount;
            }

            public List<BannerBean> getBanners() {
                return banners;
            }

            public void setBanners(List<BannerBean> banners) {
                this.banners = banners;
            }

            public List<LeftCategoryListBean> getLeftCategoryList() {
                return leftCategoryList;
            }

            public void setLeftCategoryList(List<LeftCategoryListBean> leftCategoryList) {
                this.leftCategoryList = leftCategoryList;
            }

            public static class LeftCategoryListBean {
                /**
                 * id : 分类id
                 * name : 名称
                 */

                private String id;
                private String name;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
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
}

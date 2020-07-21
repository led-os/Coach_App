package com.jsjlzj.wayne.entity.wiki;

import com.jsjlzj.wayne.entity.store.home.BannerBean;

import java.util.List;

/**
 * @ClassName: WikiCategoryBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/7/21 23:05
 */
public class WikiCategoryBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * items : [{"banners":[{"id":0,"link":"string","sort":0,"title":"string","url":"string"}],"contentCount":0,"followCount":0,"id":"分类id","imageUrl":"string","leftCategoryList":[{"id":"分类id","name":"名称"}],"name":"名称","viewCount":0}]
         * name : 名称
         */

        private String name;
        private List<ItemsBean> items;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
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

            public static class BannersBean {
                /**
                 * id : 0
                 * link : string
                 * sort : 0
                 * title : string
                 * url : string
                 */

                private int id;
                private String link;
                private int sort;
                private String title;
                private String url;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }

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

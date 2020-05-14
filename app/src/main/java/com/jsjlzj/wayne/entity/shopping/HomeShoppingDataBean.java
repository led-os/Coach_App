package com.jsjlzj.wayne.entity.shopping;

import com.jsjlzj.wayne.entity.store.home.BannerBean;

import java.util.List;

/**
 * @ClassName: HomeShoppingDataBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/10 20:48
 */
public class HomeShoppingDataBean {

    /**
     * data : {"activityList":[{"flashPromotionPrice":"商品活动价格","id":"商品ID","name":"商品名","pic":"主图片地址","price":"商品价格","sdate":"string","stimeDown":"string"}],"bannerList":[{"id":0,"link":"string","sort":0,"title":"string","url":"string"}],"categoryList":[{"categoryId":"分类id","icon":"图标","name":"商品分类名称"}],"discountsProductList":[{"id":"spuId","name":"产品名称","originalPrice":"市场价","pic":"产品图片","price":"产品价格","promotionPrice":"促销价格","sale":"销量"}],"hotProductList":[{"id":"spuId","name":"产品名称","originalPrice":"市场价","pic":"产品图片","price":"产品价格","promotionPrice":"促销价格","sale":"销量"}],"newProductList":[{"id":"spuId","name":"产品名称","originalPrice":"市场价","pic":"产品图片","price":"产品价格","promotionPrice":"促销价格","sale":"销量"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ShoppingBean> activityList;//显示秒杀列表
        private List<BannerBean> bannerList;//轮播图
        private List<CategoryListBean> categoryList;//类型分类
        private List<ShoppingBean> discountsProductList;//组合套餐产品列表
        private List<ShoppingBean> hotProductList;//最新产品列表
        private List<ShoppingBean> newProductList;//热门产品列表

        public List<ShoppingBean> getActivityList() {
            return activityList;
        }

        public void setActivityList(List<ShoppingBean> activityList) {
            this.activityList = activityList;
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

        public List<ShoppingBean> getDiscountsProductList() {
            return discountsProductList;
        }

        public void setDiscountsProductList(List<ShoppingBean> discountsProductList) {
            this.discountsProductList = discountsProductList;
        }

        public List<ShoppingBean> getHotProductList() {
            return hotProductList;
        }

        public void setHotProductList(List<ShoppingBean> hotProductList) {
            this.hotProductList = hotProductList;
        }

        public List<ShoppingBean> getNewProductList() {
            return newProductList;
        }

        public void setNewProductList(List<ShoppingBean> newProductList) {
            this.newProductList = newProductList;
        }

        public static class CategoryListBean {
            /**
             * categoryId : 分类id
             * icon : 图标
             * name : 商品分类名称
             */

            private int categoryId;
            private String icon;
            private String name;
            private String keywords;
            private String description;

            public CategoryListBean(int categoryId, String name, String keywords) {
                this.categoryId = categoryId;
                this.name = name;
                this.keywords = keywords;
            }

            public int getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(int categoryId) {
                this.categoryId = categoryId;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getKeywords() {
                return keywords;
            }

            public void setKeywords(String keywords) {
                this.keywords = keywords;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }

    }
}

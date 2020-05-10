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
        private List<ActivityListBean> activityList;
        private List<BannerBean> bannerList;
        private List<CategoryListBean> categoryList;
        private List<DiscountsProductListBean> discountsProductList;
        private List<HotProductListBean> hotProductList;
        private List<NewProductListBean> newProductList;

        public List<ActivityListBean> getActivityList() {
            return activityList;
        }

        public void setActivityList(List<ActivityListBean> activityList) {
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

        public List<DiscountsProductListBean> getDiscountsProductList() {
            return discountsProductList;
        }

        public void setDiscountsProductList(List<DiscountsProductListBean> discountsProductList) {
            this.discountsProductList = discountsProductList;
        }

        public List<HotProductListBean> getHotProductList() {
            return hotProductList;
        }

        public void setHotProductList(List<HotProductListBean> hotProductList) {
            this.hotProductList = hotProductList;
        }

        public List<NewProductListBean> getNewProductList() {
            return newProductList;
        }

        public void setNewProductList(List<NewProductListBean> newProductList) {
            this.newProductList = newProductList;
        }

        public static class ActivityListBean {
            /**
             * flashPromotionPrice : 商品活动价格
             * id : 商品ID
             * name : 商品名
             * pic : 主图片地址
             * price : 商品价格
             * sdate : string
             * stimeDown : string
             */

            private String flashPromotionPrice;
            private String id;
            private String name;
            private String pic;
            private String price;
            private String sdate;
            private String stimeDown;

            public String getFlashPromotionPrice() {
                return flashPromotionPrice;
            }

            public void setFlashPromotionPrice(String flashPromotionPrice) {
                this.flashPromotionPrice = flashPromotionPrice;
            }

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

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getSdate() {
                return sdate;
            }

            public void setSdate(String sdate) {
                this.sdate = sdate;
            }

            public String getStimeDown() {
                return stimeDown;
            }

            public void setStimeDown(String stimeDown) {
                this.stimeDown = stimeDown;
            }
        }


        public static class CategoryListBean {
            /**
             * categoryId : 分类id
             * icon : 图标
             * name : 商品分类名称
             */

            private String categoryId;
            private String icon;
            private String name;

            public String getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(String categoryId) {
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
        }

        public static class DiscountsProductListBean {
            /**
             * id : spuId
             * name : 产品名称
             * originalPrice : 市场价
             * pic : 产品图片
             * price : 产品价格
             * promotionPrice : 促销价格
             * sale : 销量
             */

            private String id;
            private String name;
            private String originalPrice;
            private String pic;
            private String price;
            private String promotionPrice;
            private String sale;

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

            public String getOriginalPrice() {
                return originalPrice;
            }

            public void setOriginalPrice(String originalPrice) {
                this.originalPrice = originalPrice;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getPromotionPrice() {
                return promotionPrice;
            }

            public void setPromotionPrice(String promotionPrice) {
                this.promotionPrice = promotionPrice;
            }

            public String getSale() {
                return sale;
            }

            public void setSale(String sale) {
                this.sale = sale;
            }
        }

        public static class HotProductListBean {
            /**
             * id : spuId
             * name : 产品名称
             * originalPrice : 市场价
             * pic : 产品图片
             * price : 产品价格
             * promotionPrice : 促销价格
             * sale : 销量
             */

            private String id;
            private String name;
            private String originalPrice;
            private String pic;
            private String price;
            private String promotionPrice;
            private String sale;

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

            public String getOriginalPrice() {
                return originalPrice;
            }

            public void setOriginalPrice(String originalPrice) {
                this.originalPrice = originalPrice;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getPromotionPrice() {
                return promotionPrice;
            }

            public void setPromotionPrice(String promotionPrice) {
                this.promotionPrice = promotionPrice;
            }

            public String getSale() {
                return sale;
            }

            public void setSale(String sale) {
                this.sale = sale;
            }
        }

        public static class NewProductListBean {
            /**
             * id : spuId
             * name : 产品名称
             * originalPrice : 市场价
             * pic : 产品图片
             * price : 产品价格
             * promotionPrice : 促销价格
             * sale : 销量
             */

            private String id;
            private String name;
            private String originalPrice;
            private String pic;
            private String price;
            private String promotionPrice;
            private String sale;

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

            public String getOriginalPrice() {
                return originalPrice;
            }

            public void setOriginalPrice(String originalPrice) {
                this.originalPrice = originalPrice;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getPromotionPrice() {
                return promotionPrice;
            }

            public void setPromotionPrice(String promotionPrice) {
                this.promotionPrice = promotionPrice;
            }

            public String getSale() {
                return sale;
            }

            public void setSale(String sale) {
                this.sale = sale;
            }
        }
    }
}

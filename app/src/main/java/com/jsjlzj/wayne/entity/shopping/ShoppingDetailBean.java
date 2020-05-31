package com.jsjlzj.wayne.entity.shopping;

import java.util.List;

/**
 * @ClassName: ShoppingDetailBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/25 14:22
 */
public class ShoppingDetailBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        /**
         * name : 全世界最好的手机,没有之一
         * pic : https://jh-tiyun-test.oss-cn-hangzhou.aliyuncs.com/file/20200515201807400_ZIL43IDP.png
         * publishStatus : 1
         * sale : 0
         * price : 190000
         * promotionPrice : null
         * subTitle : 全世界最好的手机,没有之一1
         * description : 全世界最好的手机,没有之一全世界最好的手机,没有之一全世界最好的手机,没有之一全世界最好的手机,没有之一全世界最好的手机,没有之一全世界最好的手机,没有之一
         * originalPrice : 200000
         * stock : 112
         * unit :
         * serviceId :
         * note :
         * albumPics :
         * detailTitle :
         * detailDesc :
         * detailMobileHtml : <p>1900019000190001900019000190001900019000</p>
         * promotionType : 0
         * skuPrice : 29000
         * skuPic : https://jh-tiyun-test.oss-cn-hangzhou.aliyuncs.com/file/20200515201748408_UBURDBHS.png
         * skuPromotionPrice : 29000
         * spData : [{"key":"容量","value":"128G"},{"key":"颜色","value":"黑色"}]
         * skuId : 188
         */

        private String name;
        private String pic;
        private int publishStatus;
        private int sale;
        private float price;
        private Object promotionPrice;
        private String subTitle;
        private String description;
        private int originalPrice;
        private int stock;
        private String unit;
        private String serviceId;
        private String note;
        private String albumPics;
        private String detailTitle;
        private String detailDesc;
        private String detailMobileHtml;
        private int promotionType;
        private float skuPrice;
        private String skuPic;
        private float skuPromotionPrice;
        private int skuId;
        private String spData;

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

        public int getPublishStatus() {
            return publishStatus;
        }

        public void setPublishStatus(int publishStatus) {
            this.publishStatus = publishStatus;
        }

        public int getSale() {
            return sale;
        }

        public void setSale(int sale) {
            this.sale = sale;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public Object getPromotionPrice() {
            return promotionPrice;
        }

        public void setPromotionPrice(Object promotionPrice) {
            this.promotionPrice = promotionPrice;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(int originalPrice) {
            this.originalPrice = originalPrice;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getServiceId() {
            return serviceId;
        }

        public void setServiceId(String serviceId) {
            this.serviceId = serviceId;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getAlbumPics() {
            return albumPics;
        }

        public void setAlbumPics(String albumPics) {
            this.albumPics = albumPics;
        }

        public String getDetailTitle() {
            return detailTitle;
        }

        public void setDetailTitle(String detailTitle) {
            this.detailTitle = detailTitle;
        }

        public String getDetailDesc() {
            return detailDesc;
        }

        public void setDetailDesc(String detailDesc) {
            this.detailDesc = detailDesc;
        }

        public String getDetailMobileHtml() {
            return detailMobileHtml;
        }

        public void setDetailMobileHtml(String detailMobileHtml) {
            this.detailMobileHtml = detailMobileHtml;
        }

        public int getPromotionType() {
            return promotionType;
        }

        public void setPromotionType(int promotionType) {
            this.promotionType = promotionType;
        }

        public float getSkuPrice() {
            return skuPrice;
        }

        public void setSkuPrice(float skuPrice) {
            this.skuPrice = skuPrice;
        }

        public String getSkuPic() {
            return skuPic;
        }

        public void setSkuPic(String skuPic) {
            this.skuPic = skuPic;
        }

        public float getSkuPromotionPrice() {
            return skuPromotionPrice;
        }

        public void setSkuPromotionPrice(float skuPromotionPrice) {
            this.skuPromotionPrice = skuPromotionPrice;
        }

        public int getSkuId() {
            return skuId;
        }

        public void setSkuId(int skuId) {
            this.skuId = skuId;
        }

        public String getSpData() {
            return spData;
        }

        public void setSpData(String spData) {
            this.spData = spData;
        }

    }
}

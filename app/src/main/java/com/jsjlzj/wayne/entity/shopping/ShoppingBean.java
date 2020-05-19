package com.jsjlzj.wayne.entity.shopping;

/**
 * @ClassName: ShoppingBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/11 16:48
 */
public class ShoppingBean {

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
    private float originalPrice;
    private String pic;
    private float price;
    private String promotionPrice;
    private String sale;
    private String flashPromotionPrice;
    private String sdate;
    private String stimeDown;

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

    public float getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(float originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
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

    public String getFlashPromotionPrice() {
        return flashPromotionPrice;
    }

    public void setFlashPromotionPrice(String flashPromotionPrice) {
        this.flashPromotionPrice = flashPromotionPrice;
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

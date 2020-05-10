package com.jsjlzj.wayne.entity.shopping;

import java.util.List;

/**
 * @ClassName: ShoppingCarBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/4/26 23:41
 */
public class ShoppingCarBean {


    /**
     * productId : 152
     * productName : 烤鸡翅
     * attributeId : 31845
     * attributeName : 晚餐
     * tasteId : null
     * tasteName : null
     * count : 2
     */

    private String productId;
    private String productName;
    private int attributeId;
    private String attributeName;
    private List<Long> tasteIds;
    private String tasteName;
    private int count;
    private String productPic;
    private String prdAttPrice;
    private boolean isSelect;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public List<Long> getTasteIds() {
        return tasteIds;
    }

    public void setTasteIds(List<Long> tasteIds) {
        this.tasteIds = tasteIds;
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public String getPrdAttPrice() {
        return prdAttPrice;
    }

    public void setPrdAttPrice(String prdAttPrice) {
        this.prdAttPrice = prdAttPrice;
    }

    public String getTasteName() {
        return tasteName;
    }

    public void setTasteName(String tasteName) {
        this.tasteName = tasteName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}

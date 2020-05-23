package com.jsjlzj.wayne.entity.shopping;

/**
 * @ClassName: CommitOrderBody
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/15 16:33
 */
public class CommitOrderBody {

    /**
     * buyNum : 0
     * id : 0
     * productId : 0
     * userId : 0
     */

    private int buyCount;
    private int productId;
    private long shoppingCarId;


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }

    public long getShoppingCarId() {
        return shoppingCarId;
    }

    public void setShoppingCarId(long shoppingCarId) {
        this.shoppingCarId = shoppingCarId;
    }
}

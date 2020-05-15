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

    private int buyNum;
    private int id;
    private int productId;
    private int userId;

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

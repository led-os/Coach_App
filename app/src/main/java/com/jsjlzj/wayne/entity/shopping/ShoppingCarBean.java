package com.jsjlzj.wayne.entity.shopping;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: ShoppingCarBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/4/26 23:41
 */
public class ShoppingCarBean {


    /**
     * data : {"couponId":"领取的优惠卷ID","listResults":[{"buyNum":0,"id":0,"price":0,"productId":0,"productName":"string","productType":0,"productUrl":"string","status":0,"stock":0}],"price":"合计"}
     */

    private DataBean data;



    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * couponId : 领取的优惠卷ID
         * listResults : [{"buyNum":0,"id":0,"price":0,"productId":0,"productName":"string","productType":0,"productUrl":"string","status":0,"stock":0}]
         * price : 合计
         */

        private int couponId;
        private String price;
        private List<ListResultsBean> listResults;

        public int getCouponId() {
            return couponId;
        }

        public void setCouponId(int couponId) {
            this.couponId = couponId;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public List<ListResultsBean> getListResults() {
            return listResults;
        }

        public void setListResults(List<ListResultsBean> listResults) {
            this.listResults = listResults;
        }

        public static class ListResultsBean implements Serializable {
            /**
             * buyNum : 0
             * id : 0
             * price : 0
             * productId : 0
             * productName : string
             * productType : 0
             * productUrl : string
             * status : 0
             * stock : 0
             */

            private int buyNum;
            private int id;
            private float price;
            private int productId;
            private String productName;
            private int productType;
            private String productUrl;
            private int status;
            private int stock;
            private boolean isSelect;

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

            public float getPrice() {
                return price;
            }

            public void setPrice(float price) {
                this.price = price;
            }

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public int getProductType() {
                return productType;
            }

            public void setProductType(int productType) {
                this.productType = productType;
            }

            public String getProductUrl() {
                return productUrl;
            }

            public void setProductUrl(String productUrl) {
                this.productUrl = productUrl;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }
        }
    }
}

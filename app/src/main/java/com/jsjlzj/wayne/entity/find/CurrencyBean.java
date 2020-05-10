package com.jsjlzj.wayne.entity.find;

import java.util.List;

/**
 * @ClassName: CurrencyBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/9 14:34
 */
public class CurrencyBean {

    /**
     * data : {"amount":0,"productList":[{"coin":0,"description":"string","id":0,"name":"string","price":0,"productId":"string"}]}
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
         * amount : 0
         * productList : [{"coin":0,"description":"string","id":0,"name":"string","price":0,"productId":"string"}]
         */

        private String amount;
        private List<ProductListBean> productList;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public List<ProductListBean> getProductList() {
            return productList;
        }

        public void setProductList(List<ProductListBean> productList) {
            this.productList = productList;
        }

        public static class ProductListBean {
            /**
             * coin : 0
             * description : string
             * id : 0
             * name : string
             * price : 0
             * productId : string
             */

            private int coin;
            private String description;
            private int id;
            private String name;
            private String price;
            private String productId;

            public int getCoin() {
                return coin;
            }

            public void setCoin(int coin) {
                this.coin = coin;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getProductId() {
                return productId;
            }

            public void setProductId(String productId) {
                this.productId = productId;
            }
        }
    }
}

package com.jsjlzj.wayne.entity.find;

import java.util.List;

/**
 * @ClassName: FindStoreBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/6/25 12:18
 */
public class FindStoreBean {

    /**
     * businessDistrict : string
     * distance : 0
     * id : 0
     * lessons : [{"id":0,"name":"string","originPrice":0,"price":0,"type":0}]
     * newStoreFlag : 0
     * score : 0
     * selfDesc : string
     * storeLogo : string
     * storeName : string
     * storeType : 0
     */

    private String businessDistrict;
    private int distance;
    private int id;
    private int newStoreFlag;
    private float score;
    private String selfDesc;
    private String storeLogo;
    private String storeName;
    private int storeType;
    private List<LessonsBean> lessons;

    public String getBusinessDistrict() {
        return businessDistrict;
    }

    public void setBusinessDistrict(String businessDistrict) {
        this.businessDistrict = businessDistrict;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNewStoreFlag() {
        return newStoreFlag;
    }

    public void setNewStoreFlag(int newStoreFlag) {
        this.newStoreFlag = newStoreFlag;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getSelfDesc() {
        return selfDesc;
    }

    public void setSelfDesc(String selfDesc) {
        this.selfDesc = selfDesc;
    }

    public String getStoreLogo() {
        return storeLogo;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getStoreType() {
        return storeType;
    }

    public void setStoreType(int storeType) {
        this.storeType = storeType;
    }

    public List<LessonsBean> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonsBean> lessons) {
        this.lessons = lessons;
    }

    public static class LessonsBean {
        /**
         * id : 0
         * name : string
         * originPrice : 0
         * price : 0
         * type : 0
         */

        private int id;
        private String name;
        private int originPrice;
        private float price;
        private int type;

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

        public int getOriginPrice() {
            return originPrice;
        }

        public void setOriginPrice(int originPrice) {
            this.originPrice = originPrice;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}

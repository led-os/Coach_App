package com.jsjlzj.wayne.entity.store;

public class MdlInfo {
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean{
        private int communicatedCount;
        private int interviewedCount;
        private int likeCount;
        private int positionCount;
        private String storeName;
        private String storeUserHeadImg;
        private String storeUserName;
        private String storeUserPosition;
        private String name;
        private String headImg;
        private int workHopeCount;

        public int getWorkHopeCount() {
            return workHopeCount;
        }

        public void setWorkHopeCount(int workHopeCount) {
            this.workHopeCount = workHopeCount;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCommunicatedCount() {
            return communicatedCount;
        }

        public void setCommunicatedCount(int communicatedCount) {
            this.communicatedCount = communicatedCount;
        }

        public int getInterviewedCount() {
            return interviewedCount;
        }

        public void setInterviewedCount(int interviewedCount) {
            this.interviewedCount = interviewedCount;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public int getPositionCount() {
            return positionCount;
        }

        public void setPositionCount(int positionCount) {
            this.positionCount = positionCount;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getStoreUserHeadImg() {
            return storeUserHeadImg;
        }

        public void setStoreUserHeadImg(String storeUserHeadImg) {
            this.storeUserHeadImg = storeUserHeadImg;
        }

        public String getStoreUserName() {
            return storeUserName;
        }

        public void setStoreUserName(String storeUserName) {
            this.storeUserName = storeUserName;
        }

        public String getStoreUserPosition() {
            return storeUserPosition;
        }

        public void setStoreUserPosition(String storeUserPosition) {
            this.storeUserPosition = storeUserPosition;
        }
    }
}

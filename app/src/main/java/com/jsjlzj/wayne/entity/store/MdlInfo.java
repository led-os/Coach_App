package com.jsjlzj.wayne.entity.store;

import java.io.Serializable;

public class MdlInfo implements Serializable {
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        //已沟通数
        private int communicatedCount;
        //待面试数
        private int interviewedCount;
        //获赞数
        private int likeCount;
        //发布次数
        private int positionCount;
        //关注数
        private int followerCount;
        //粉丝
        private int fansCount;
        //收藏数
        private int collectCount;
        //发布次数
        private int publishCount;
        //求职意向

        private String storeName;
        private String storeUserHeadImg;
        private String storeUserName;
        private String storeUserPosition;
        private String name;
        //头像
        private String headImg;
        private int workHopeCount;
        //简介
        private String content;
        //1:普通 2:加V
        private int level;
        // 团长级别 1:一级, 0|null无
        private int regimentalLevel;
        //邀请码
        private String userId;
        //可提现金额
        private String withdrawableAmount;


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

        public int getFollowerCount() {
            return followerCount;
        }

        public void setFollowerCount(int followerCount) {
            this.followerCount = followerCount;
        }

        public int getFensCount() {
            return fansCount;
        }

        public void setFensCount(int fensCount) {
            this.fansCount = fensCount;
        }

        public int getCollectCount() {
            return collectCount;
        }

        public void setCollectCount(int collectCount) {
            this.collectCount = collectCount;
        }

        public int getPublishCount() {
            return publishCount;
        }

        public void setPublishCount(int publishCount) {
            this.publishCount = publishCount;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getFansCount() {
            return fansCount;
        }

        public void setFansCount(int fansCount) {
            this.fansCount = fansCount;
        }

        public int getRegimentalLevel() {
            return regimentalLevel;
        }

        public void setRegimentalLevel(int regimentalLevel) {
            this.regimentalLevel = regimentalLevel;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getWithdrawableAmount() {
            return withdrawableAmount;
        }

        public void setWithdrawableAmount(String withdrawableAmount) {
            this.withdrawableAmount = withdrawableAmount;
        }
    }
}

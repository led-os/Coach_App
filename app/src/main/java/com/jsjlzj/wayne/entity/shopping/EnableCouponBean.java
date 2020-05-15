package com.jsjlzj.wayne.entity.shopping;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: EnableCouponBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/13 18:20
 */
public class EnableCouponBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * amount : 0
         * code : string
         * count : 0
         * createBy : 0
         * createTime : 2020-05-13T10:19:22.672Z
         * enableTime : 2020-05-13T10:19:22.672Z
         * endTime : 2020-05-13T10:19:22.672Z
         * id : 0
         * isDel : 0
         * memberLevel : 0
         * minPoint : 0
         * name : string
         * note : string
         * perLimit : 0
         * platform : 0
         * publishCount : 0
         * receiveCount : 0
         * startTime : 2020-05-13T10:19:22.672Z
         * type : 0
         * updateBy : 0
         * updateTime : 2020-05-13T10:19:22.672Z
         * useCount : 0
         * useType : 0
         */

        private float amount;
        private String code;
        private int count;
        private int createBy;
        private String createTime;
        private String enableTime;
        private String endTime;
        private int id;
        private int isDel;
        private int memberLevel;
        private int minPoint;
        private String name;
        private String note;
        private int perLimit;
        private int platform;
        private int publishCount;
        private int receiveCount;
        private String startTime;
        private int type;
        private int updateBy;
        private String updateTime;
        private int useCount;
        private int useType;

        public float getAmount() {
            return amount;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getCreateBy() {
            return createBy;
        }

        public void setCreateBy(int createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getEnableTime() {
            return enableTime;
        }

        public void setEnableTime(String enableTime) {
            this.enableTime = enableTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsDel() {
            return isDel;
        }

        public void setIsDel(int isDel) {
            this.isDel = isDel;
        }

        public int getMemberLevel() {
            return memberLevel;
        }

        public void setMemberLevel(int memberLevel) {
            this.memberLevel = memberLevel;
        }

        public int getMinPoint() {
            return minPoint;
        }

        public void setMinPoint(int minPoint) {
            this.minPoint = minPoint;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public int getPerLimit() {
            return perLimit;
        }

        public void setPerLimit(int perLimit) {
            this.perLimit = perLimit;
        }

        public int getPlatform() {
            return platform;
        }

        public void setPlatform(int platform) {
            this.platform = platform;
        }

        public int getPublishCount() {
            return publishCount;
        }

        public void setPublishCount(int publishCount) {
            this.publishCount = publishCount;
        }

        public int getReceiveCount() {
            return receiveCount;
        }

        public void setReceiveCount(int receiveCount) {
            this.receiveCount = receiveCount;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(int updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getUseCount() {
            return useCount;
        }

        public void setUseCount(int useCount) {
            this.useCount = useCount;
        }

        public int getUseType() {
            return useType;
        }

        public void setUseType(int useType) {
            this.useType = useType;
        }
    }
}

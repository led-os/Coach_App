package com.jsjlzj.wayne.entity.find;

import java.util.List;

/**
 * @ClassName: BusinessDistrictBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/6/25 13:58
 */
public class BusinessDistrictBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        public DataBean() {
        }

        public DataBean(int code, String shortName) {
            this.code = code;
            this.shortName = shortName;
        }

        /**
         * code : 0
         * createBy : 0
         * createTime : 2020-06-25T05:52:23.276Z
         * id : 0
         * isDel : 0
         * lat : string
         * lng : string
         * name : string
         * parentCode : 0
         * shortName : string
         * sort : 0
         * status : 0
         * type : 0
         * updateBy : 0
         * updateTime : 2020-06-25T05:52:23.276Z
         */



        private int code;
        private int createBy;
        private String createTime;
        private int id;
        private int isDel;
        private String lat;
        private String lng;
        private String name;
        private int parentCode;
        private String shortName;
        private int sort;
        private int status;
        private int type;
        private int updateBy;
        private String updateTime;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
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

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getParentCode() {
            return parentCode;
        }

        public void setParentCode(int parentCode) {
            this.parentCode = parentCode;
        }

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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
    }
}

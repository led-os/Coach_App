package com.jsjlzj.wayne.entity.shopping;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: LocationListBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/9 0:48
 */
public class LocationListBean implements Serializable {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * city : string
         * cityCode : string
         * createBy : 0
         * createTime : 2020-05-08T16:43:01.560Z
         * detail : string
         * district : string
         * districtCode : string
         * id : 0
         * isDefault : 0
         * isDel : 0
         * phone : string
         * province : string
         * provinceCode : string
         * updateBy : 0
         * updateTime : 2020-05-08T16:43:01.561Z
         * userId : 0
         * userName : string
         */

        private String city;
        private String cityCode;
        private int createBy;
        private String createTime;
        private String detail;
        private String district;
        private String districtCode;
        private int id;
        private int isDefault;
        private int isDel;
        private String phone;
        private String province;
        private String provinceCode;
        private int updateBy;
        private String updateTime;
        private int userId;
        private String userName;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCityCode() {
            return cityCode;
        }

        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
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

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getDistrictCode() {
            return districtCode;
        }

        public void setDistrictCode(String districtCode) {
            this.districtCode = districtCode;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(int isDefault) {
            this.isDefault = isDefault;
        }

        public int getIsDel() {
            return isDel;
        }

        public void setIsDel(int isDel) {
            this.isDel = isDel;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getProvinceCode() {
            return provinceCode;
        }

        public void setProvinceCode(String provinceCode) {
            this.provinceCode = provinceCode;
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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}

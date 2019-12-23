package com.jsjlzj.wayne.entity.store;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.List;

public class MdlStoreInfo {

    /**
     * area : 区
     * brandLogo : 品牌logo
     * businessLicense : 营业执照(多张以逗号拼接)
     * city : 市
     * companyBenefits : ["string"]
     * companyImages : [{"original":"原图地址","thumbnail":"缩略图地址"}]
     * companyLegalPerson : 企业法人
     * companyName : 公司全称
     * companyProfile : 公司介绍(以逗号拼接)
     * coordinate : 经纬度
     * id : 门店Id
     * province : 省
     * registeredCapital : 注册资本
     * registeredCapitalCode : 注册资本code
     * registeredDate : 注册时间:如, 2018-10-20
     * restTime : 休息时间
     * restTimeCode : 休息时间code(字典code为:rest_time)
     * staffNum : 员工数量
     * staffNumCode : 员工数量code(字典code为:staff_num)
     * status : 状态1:未审核2:审核失败3:审核通过
     * storeAddress : 门店地址
     * storeArea : 门店面积
     * storeDoorplate : 门牌号
     * storeName : 门店名称
     * storeTelephone : 门店电话
     * workOvertime : 加班情况
     * workOvertimeCode : 加班情况code(字典code为:work_overtime)
     * workTimeEnd : 上班时间_结束
     * workTimeStart : 上班时间_开始
     */
    public DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean{

    private String area;
    private String brandLogo;
    private String businessLicense;
    private String city;
    private String companyLegalPerson;
    private String companyName;
    private String companyProfile;
    private String coordinate;
    private String id;
    private String province;
    private String registeredCapital;
    private String registeredCapitalCode;
    private String registeredDate;
    private String restTime;
    private String restTimeCode;
    private String staffNum;
    private String staffNumCode;
    private String status;
    private String storeAddress;
    private String storeArea;
    private String storeDoorplate;
    private String storeName;
    private String storeTelephone;
    private String workOvertime;
    private String workOvertimeCode;
    private String workTimeEnd;
    private String workTimeStart;
    private List<String> companyBenefits;
    private List<CompanyImagesBean> companyImages;
    private boolean isLike;

        public boolean isLike() {
            return isLike;
        }

        public void setLike(boolean like) {
            isLike = like;
        }

        public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompanyLegalPerson() {
        return companyLegalPerson;
    }

    public void setCompanyLegalPerson(String companyLegalPerson) {
        this.companyLegalPerson = companyLegalPerson;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyProfile() {
        return companyProfile;
    }

    public void setCompanyProfile(String companyProfile) {
        this.companyProfile = companyProfile;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(String registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public String getRegisteredCapitalCode() {
        return registeredCapitalCode;
    }

    public void setRegisteredCapitalCode(String registeredCapitalCode) {
        this.registeredCapitalCode = registeredCapitalCode;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getRestTime() {
        return restTime;
    }

    public void setRestTime(String restTime) {
        this.restTime = restTime;
    }

    public String getRestTimeCode() {
        if(TextUtils.isEmpty(restTimeCode)){
            return "";
        }
        return restTimeCode;
    }

    public void setRestTimeCode(String restTimeCode) {
        this.restTimeCode = restTimeCode;
    }

    public String getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(String staffNum) {
        this.staffNum = staffNum;
    }

    public String getStaffNumCode() {
        return staffNumCode;
    }

    public void setStaffNumCode(String staffNumCode) {
        this.staffNumCode = staffNumCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreArea() {
        return storeArea;
    }

    public void setStoreArea(String storeArea) {
        this.storeArea = storeArea;
    }

    public String getStoreDoorplate() {
        return storeDoorplate;
    }

    public void setStoreDoorplate(String storeDoorplate) {
        this.storeDoorplate = storeDoorplate;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreTelephone() {
        return storeTelephone;
    }

    public void setStoreTelephone(String storeTelephone) {
        this.storeTelephone = storeTelephone;
    }

    public String getWorkOvertime() {
        return workOvertime;
    }

    public void setWorkOvertime(String workOvertime) {
        this.workOvertime = workOvertime;
    }

    public String getWorkOvertimeCode() {
        if(TextUtils.isEmpty(workOvertimeCode)){
            return "";
        }
        return workOvertimeCode;
    }

    public void setWorkOvertimeCode(String workOvertimeCode) {
        this.workOvertimeCode = workOvertimeCode;
    }

    public String getWorkTimeEnd() {
        return workTimeEnd;
    }

    public void setWorkTimeEnd(String workTimeEnd) {
        this.workTimeEnd = workTimeEnd;
    }

    public String getWorkTimeStart() {
        return workTimeStart;
    }

    public void setWorkTimeStart(String workTimeStart) {
        this.workTimeStart = workTimeStart;
    }

    public List<String> getCompanyBenefits() {
        return companyBenefits;
    }

    public void setCompanyBenefits(List<String> companyBenefits) {
        this.companyBenefits = companyBenefits;
    }

    public List<CompanyImagesBean> getCompanyImages() {
        return companyImages;
    }

    public void setCompanyImages(List<CompanyImagesBean> companyImages) {
        this.companyImages = companyImages;
    }

    public static class CompanyImagesBean implements Serializable{
        /**
         * original : 原图地址
         * thumbnail : 缩略图地址
         */

        private String original;
        private String thumbnail;

        public String getOriginal() {
            return original;
        }

        public void setOriginal(String original) {
            this.original = original;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }


        @Override
        public boolean equals(Object obj) {
            if(this==obj) return true;
            if(obj==null) return false;
            if(getClass()!=obj.getClass())return false;
            CompanyImagesBean bean= (CompanyImagesBean) obj;
            if(!original.equals(bean.getOriginal()))return false;
            if(!thumbnail.equals(bean.getThumbnail()))return false;
            if(original.equals(bean.getOriginal())&&thumbnail.equals(bean.getThumbnail()))return true;
            return false;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public String toString() {
            return "{" +
                    "\"original\":\"" + original + "\"" +
                    ", \"thumbnail\":\"" + thumbnail + "\"" +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "{" +
                "\"area\":\"" + area + "\"" +
                ", \"brandLogo\":\"" + brandLogo + "\"" +
                ", \"businessLicense\":\"" + businessLicense + "\"" +
                ", \"city\":\"" + city + "\"" +
                ", \"companyLegalPerson\":\"" + companyLegalPerson + "\"" +
                ", \"companyName\":\"" + companyName + "\"" +
                ", \"companyProfile\":\"" + companyProfile + "\"" +
                ", \"coordinate\":\"" + coordinate + "\"" +
                ", \"id\":\"" + id + "\"" +
                ", \"province\":\"" + province + "\"" +
                ", \"registeredCapital\":\"" + registeredCapital + "\"" +
                ", \"registeredCapitalCode\":\"" + registeredCapitalCode + "\"" +
                ", \"registeredDate\":\"" + registeredDate + "\"" +
                ", \"restTime\":\"" + restTime + "\"" +
                ", \"restTimeCode\":\"" + restTimeCode + "\"" +
                ", \"staffNum\":\"" + staffNum + "\"" +
                ", \"staffNumCode\":\"" + staffNumCode + "\"" +
                ", \"status\":\"" + status + "\"" +
                ", \"storeAddress\":\"" + storeAddress + "\"" +
                ", \"storeArea\":\"" + storeArea + "\"" +
                ", \"storeDoorplate\":\"" + storeDoorplate + "\"" +
                ", \"storeName\":\"" + storeName + "\"" +
                ", \"storeTelephone\":\"" + storeTelephone + "\"" +
                ", \"workOvertime\":\"" + workOvertime + "\"" +
                ", \"workOvertimeCode\":\"" + workOvertimeCode + "\"" +
                ", \"workTimeEnd\":\"" + workTimeEnd + "\"" +
                ", \"workTimeStart\":\"" + workTimeStart + "\"" +
                ", \"companyBenefits\":" + companyBenefits +
                ", \"companyImages\":" + companyImages +
                '}';
    }

    }
}

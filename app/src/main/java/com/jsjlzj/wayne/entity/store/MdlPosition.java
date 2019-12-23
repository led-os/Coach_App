package com.jsjlzj.wayne.entity.store;

import java.util.List;

public class MdlPosition {

    /**
     * area : 区
     * areaId : 区ID
     * city : 市
     * cityId : 市ID
     * content : 职位描述
     * coordinate : 经纬度
     * createTime : 创建时间
     * id : id
     * lowestEducationLevel : 最低学历
     * lowestEducationLevelCode : 最低学历code
     * name : 职位名称
     * positionTypeId : 职位类型ID
     * province : 省
     * provinceId : 省ID
     * publishTime : 发布时间
     * recruitmentTypeId : 招聘类型id
     * recruitmentTypeName : 招聘类型名称
     * salaryMax : 薪资要求-max
     * salaryMin : 薪资要求-min
     * skillRequired : ["string"]
     * status : 状态 1:待开放; 2:招聘中; 3:审核失败; 4:已关闭
     * workAddress : 工作地址
     * workYears : 经验要求
     * workYearsCode : 经验要求code
     */
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public class DataBean{


    private String area;
    private String areaId;
    private String city="";
    private String cityId;
    private String content;
    private String coordinate;
    private String createTime;
    private String id;
    private String lowestEducationLevel="";
    private String lowestEducationLevelCode;
    private String name;
    private int  positionTypeId;
    private String province;
    private String provinceId;
    private String publishTime;
    private String recruitmentTypeId;
    private String recruitmentTypeName;
    private int salaryMax;
    private int salaryMin;
    private String status;
    private String workAddress;
    private String workYears="";
    private String workYearsCode;
    private List<String> skillRequired;
    private String storeName;
    private String storeUserName;
    private String storeUserHeadImg;
    private String storeUserPosition;
    private boolean isLike;

        public boolean isLike() {
            return isLike;
        }

        public void setLike(boolean like) {
            isLike = like;
        }

        public String getStoreUserPosition() {
            return storeUserPosition;
        }

        public void setStoreUserPosition(String storeUserPosition) {
            this.storeUserPosition = storeUserPosition;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getStoreUserName() {
            return storeUserName;
        }

        public void setStoreUserName(String storeUserName) {
            this.storeUserName = storeUserName;
        }

        public String getStoreUserHeadImg() {
            return storeUserHeadImg;
        }

        public void setStoreUserHeadImg(String storeUserHeadImg) {
            this.storeUserHeadImg = storeUserHeadImg;
        }

        @Override
    public String toString() {
        return "{" +
                "\"area\":\"" + area + "\"" +
                ", \"areaId\":\"" + areaId + "\"" +
                ", \"city\":\"" + city + "\"" +
                ", \"cityId\":\"" + cityId + "\"" +
                ", \"content\":\"" + content + "\"" +
                ", \"coordinate\":\"" + coordinate + "\"" +
                ", \"createTime\":\"" + createTime + "\"" +
                ", \"id\":\"" + id + "\"" +
                ", \"lowestEducationLevel\":\"" + lowestEducationLevel + "\"" +
                ", \"lowestEducationLevelCode\":\"" + lowestEducationLevelCode + "\"" +
                ", \"name\":\"" + name + "\"" +
                ", \"positionTypeId\":\"" + positionTypeId + "\"" +
                ", \"province\":\"" + province + "\"" +
                ", \"provinceId\":\"" + provinceId + "\"" +
                ", \"publishTime\":\"" + publishTime + "\"" +
                ", \"recruitmentTypeId\":\"" + recruitmentTypeId + "\"" +
                ", \"recruitmentTypeName\":\"" + recruitmentTypeName + "\"" +
                ", \"salaryMax\":\"" + salaryMax + "\"" +
                ", \"salaryMin\":\"" + salaryMin + "\"" +
                ", \"status\":\"" + status + "\"" +
                ", \"workAddress\":\"" + workAddress + "\"" +
                ", \"workYears\":\"" + workYears + "\"" +
                ", \"workYearsCode\":\"" + workYearsCode + "\"" +
                ", \"skillRequired\":" + skillRequired +
                '}';
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLowestEducationLevel() {
        return lowestEducationLevel;
    }

    public void setLowestEducationLevel(String lowestEducationLevel) {
        this.lowestEducationLevel = lowestEducationLevel;
    }

    public String getLowestEducationLevelCode() {
        return lowestEducationLevelCode;
    }

    public void setLowestEducationLevelCode(String lowestEducationLevelCode) {
        this.lowestEducationLevelCode = lowestEducationLevelCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPositionTypeId() {
        return positionTypeId;
    }

    public void setPositionTypeId(int positionTypeId) {
        this.positionTypeId = positionTypeId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getRecruitmentTypeId() {
        return recruitmentTypeId;
    }

    public void setRecruitmentTypeId(String recruitmentTypeId) {
        this.recruitmentTypeId = recruitmentTypeId;
    }

    public String getRecruitmentTypeName() {
        return recruitmentTypeName;
    }

    public void setRecruitmentTypeName(String recruitmentTypeName) {
        this.recruitmentTypeName = recruitmentTypeName;
    }

    public int getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(int salaryMax) {
        this.salaryMax = salaryMax;
    }

    public int getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(int salaryMin) {
        this.salaryMin = salaryMin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getWorkYears() {
        return workYears;
    }

    public void setWorkYears(String workYears) {
        this.workYears = workYears;
    }

    public String getWorkYearsCode() {
        return workYearsCode;
    }

    public void setWorkYearsCode(String workYearsCode) {
        this.workYearsCode = workYearsCode;
    }

    public List<String> getSkillRequired() {
        return skillRequired;
    }

    public void setSkillRequired(List<String> skillRequired) {
        this.skillRequired = skillRequired;
    }
}}

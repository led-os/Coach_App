package com.jsjlzj.wayne.entity.store;

import java.util.List;

public class MdlPositionDetail {


        /**
         * position : {"area":"区","areaId":"区ID","city":"市","cityId":"市ID","content":"职位描述","coordinate":"经纬度","createTime":"创建时间","id":"id","lowestEducationLevel":"最低学历","lowestEducationLevelCode":"最低学历code","name":"职位名称","positionTypeId":"职位类型ID","province":"省","provinceId":"省ID","publishTime":"发布时间","recruitmentTypeId":"招聘类型id","recruitmentTypeName":"招聘类型名称","salaryMax":"薪资要求-max","salaryMin":"薪资要求-min","skillRequired":["string"],"status":"状态 1:待开放; 2:招聘中; 3:审核失败; 4:已关闭","workAddress":"工作地址","workYears":"经验要求","workYearsCode":"经验要求code"}
         * storeAdmin : {"storeUserHeadImg":"门店管理员头像","storeUserName":"门店管理员名称","storeUserPosition":"门店管理员职位"}
         * storeInfo : {"area":"区","brandLogo":"品牌logo","businessLicense":"营业执照(多张以逗号拼接)","city":"市","companyBenefits":["string"],"companyImages":[{"original":"原图地址","thumbnail":"缩略图地址"}],"companyLegalPerson":"企业法人","companyName":"公司全称","companyProfile":"公司介绍(以逗号拼接)","coordinate":"经纬度","id":"门店Id","province":"省","registeredCapital":"注册资本","registeredCapitalCode":"注册资本code","registeredDate":"注册时间:如, 2018-10-20","restTime":"休息时间","restTimeCode":"休息时间code(字典code为:rest_time)","staffNum":"员工数量","staffNumCode":"员工数量code(字典code为:staff_num)","status":"状态1:未审核2:审核失败3:审核通过","storeAddress":"门店地址","storeArea":"门店面积","storeDoorplate":"门牌号","storeName":"门店名称","storeTelephone":"门店电话","workOvertime":"加班情况","workOvertimeCode":"加班情况code(字典code为:work_overtime)","workTimeEnd":"上班时间_结束","workTimeStart":"上班时间_开始"}
         */
        private DataBean data;

        public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public class DataBean{
            private String yunXinAccount;
        private MdlPosition.DataBean position;
        private StoreAdminBean storeAdmin;
        private StoreInfoBean storeInfo;

        public MdlPosition.DataBean getPosition() {
            return position;
        }

        public void setPosition(MdlPosition.DataBean position) {
            this.position = position;
        }

        public StoreAdminBean getStoreAdmin() {
            return storeAdmin;
        }

        public void setStoreAdmin(StoreAdminBean storeAdmin) {
            this.storeAdmin = storeAdmin;
        }

        public StoreInfoBean getStoreInfo() {
            return storeInfo;
        }

        public void setStoreInfo(StoreInfoBean storeInfo) {
            this.storeInfo = storeInfo;
        }

        public String getYunXinAccount() {
            return yunXinAccount;
        }

        public void setYunXinAccount(String yunXinAccount) {
            this.yunXinAccount = yunXinAccount;
        }

        public  class StoreAdminBean {
            /**
             * storeUserHeadImg : 门店管理员头像
             * storeUserName : 门店管理员名称
             * storeUserPosition : 门店管理员职位
             */

            private String storeUserHeadImg;
            private String storeUserName;
            private String storeUserPosition;

            @Override
            public String toString() {
                return "{" +
                        "\"storeUserHeadImg\":\"" + storeUserHeadImg + "\"" +
                        ", \"storeUserName\":\"" + storeUserName + "\"" +
                        ", \"storeUserPosition\":\"" + storeUserPosition + "\"" +
                        '}';
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

        public class StoreInfoBean {
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

            public  class CompanyImagesBean {
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
                public String toString() {
                    return "{" +
                            "\"original\":\"" + original + "\"" +
                            ", \"thumbnail\":\"" + thumbnail + "\"" +
                            '}';
                }
            }
        }

        @Override
        public String toString() {
            return "{" +
                    "\"position\":" + position +
                    ", \"storeAdmin\":" + storeAdmin +
                    ", \"storeInfo\":" + storeInfo +
                    '}';
        }
    }
}

package com.jsjlzj.wayne.entity.store;

import java.util.List;

public class MdlCV {
        /**
         * pageNo : 当前页
         * pageSize : 每页大小
         * result : [{"city":"市","cityId":"市ID","cvId":"简历ID","headImg":"头像","highestEducationLevel":"最高学历","highestEducationLevelCode":"最高学历code","id":"工作期望ID","industry":"行业","joinWorkDate":"参加工作时间","name":"姓名","position":"职位","province":"省","provinceId":"省ID","salaryMax":"薪资要求-max","salaryMin":"薪资要求-min","updateTime":"更新时间","userId":"userId","workYears":"经验要求","workYearsCode":"经验要求code"}]
         * totalCount : 总记录数
         */
        private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public class DataBean{
        private String pageNo;
        private String pageSize;
        private String totalCount;
        private List<ResultBean> result;

        @Override
        public String toString() {
            return "{" +
                    "\"pageNo\":\"" + pageNo + "\"" +
                    ", \"pageSize\":\"" + pageSize + "\"" +
                    ", \"totalCount\":\"" + totalCount + "\"" +
                    ", \"result\":" + result +
                    '}';
        }

        public String getPageNo() {
            return pageNo;
        }

        public void setPageNo(String pageNo) {
            this.pageNo = pageNo;
        }

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public  class ResultBean {
            /**
             * city : 市
             * cityId : 市ID
             * cvId : 简历ID
             * headImg : 头像
             * highestEducationLevel : 最高学历
             * highestEducationLevelCode : 最高学历code
             * id : 工作期望ID
             * industry : 行业
             * joinWorkDate : 参加工作时间
             * name : 姓名
             * position : 职位
             * province : 省
             * provinceId : 省ID
             * salaryMax : 薪资要求-max
             * salaryMin : 薪资要求-min
             * updateTime : 更新时间
             * userId : userId
             * workYears : 经验要求
             * workYearsCode : 经验要求code
             */

            private String city;
            private String cityId;
            private String cvId;
            private String headImg;
            private String age;
            private String highestEducationLevel;
            private String highestEducationLevelCode;
            private String id;
            private String industry;
            private String joinWorkDate;
            private String name;
            private String position;
            private String province;
            private String brandLogo;
            private String provinceId;
            private String salaryMax;
            private String salaryMin;
            private String updateTime;
            private String userId;
            private String workYears;
            private String workYearsCode;
            private String lowestEducationLevel;
            private int status;
            private String advantage;
            private List<String> skillTags;
            private String createTime;
            private String companyName;
            private String storeAddress;
            private String staffNum;

            public String getBrandLogo() {
                return brandLogo;
            }

            public void setBrandLogo(String brandLogo) {
                this.brandLogo = brandLogo;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getStoreAddress() {
                return storeAddress;
            }

            public void setStoreAddress(String storeAddress) {
                this.storeAddress = storeAddress;
            }

            public String getStaffNum() {
                return staffNum;
            }

            public void setStaffNum(String staffNum) {
                this.staffNum = staffNum;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getAdvantage() {
                return advantage;
            }

            public void setAdvantage(String advantage) {
                this.advantage = advantage;
            }

            public List<String> getSkillTags() {
                return skillTags;
            }

            public void setSkillTags(List<String> skillTags) {
                this.skillTags = skillTags;
            }

            public String getAge() {
                return age;
            }

            public void setAge(String age) {
                this.age = age;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getLowestEducationLevel() {
                return lowestEducationLevel;
            }

            public void setLowestEducationLevel(String lowestEducationLevel) {
                this.lowestEducationLevel = lowestEducationLevel;
            }

            @Override
            public String toString() {
                return "{" +
                        "\"city\":\"" + city + "\"" +
                        ", \"cityId\":\"" + cityId + "\"" +
                        ", \"cvId\":\"" + cvId + "\"" +
                        ", \"headImg\":\"" + headImg + "\"" +
                        ", \"highestEducationLevel\":\"" + highestEducationLevel + "\"" +
                        ", \"highestEducationLevelCode\":\"" + highestEducationLevelCode + "\"" +
                        ", \"id\":\"" + id + "\"" +
                        ", \"industry\":\"" + industry + "\"" +
                        ", \"joinWorkDate\":\"" + joinWorkDate + "\"" +
                        ", \"name\":\"" + name + "\"" +
                        ", \"position\":\"" + position + "\"" +
                        ", \"province\":\"" + province + "\"" +
                        ", \"provinceId\":\"" + provinceId + "\"" +
                        ", \"salaryMax\":\"" + salaryMax + "\"" +
                        ", \"salaryMin\":\"" + salaryMin + "\"" +
                        ", \"updateTime\":\"" + updateTime + "\"" +
                        ", \"userId\":\"" + userId + "\"" +
                        ", \"workYears\":\"" + workYears + "\"" +
                        ", \"workYearsCode\":\"" + workYearsCode + "\"" +
                        '}';
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

            public String getCvId() {
                return cvId;
            }

            public void setCvId(String cvId) {
                this.cvId = cvId;
            }

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }

            public String getHighestEducationLevel() {
                return highestEducationLevel;
            }

            public void setHighestEducationLevel(String highestEducationLevel) {
                this.highestEducationLevel = highestEducationLevel;
            }

            public String getHighestEducationLevelCode() {
                return highestEducationLevelCode;
            }

            public void setHighestEducationLevelCode(String highestEducationLevelCode) {
                this.highestEducationLevelCode = highestEducationLevelCode;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIndustry() {
                return industry;
            }

            public void setIndustry(String industry) {
                this.industry = industry;
            }

            public String getJoinWorkDate() {
                return joinWorkDate;
            }

            public void setJoinWorkDate(String joinWorkDate) {
                this.joinWorkDate = joinWorkDate;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
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

            public String getSalaryMax() {
                return salaryMax;
            }

            public void setSalaryMax(String salaryMax) {
                this.salaryMax = salaryMax;
            }

            public String getSalaryMin() {
                return salaryMin;
            }

            public void setSalaryMin(String salaryMin) {
                this.salaryMin = salaryMin;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
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
        }
    }
}


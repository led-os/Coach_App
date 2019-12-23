package com.jsjlzj.wayne.entity.store;

import java.util.List;

public class MdlDetailCV {


        /**
         * advantage : 我的优势
         * birth : 出生日期,如:1990-12-29
         * certificatePhotos : 证书照片(多个地址以逗号拼接)
         * educationExperienceList : [{"educationLevel":"学历","educationLevelCode":"学历code","endDate":"结束时间 如:2018-07","id":"id值,新增为空,修改必填","major":"专业","schoolName":"学校名称","startDate":"开始时间 如:2018-07"}]
         * headImg : 头像地址
         * highestEducationLevel : 最高学历
         * highestEducationLevelCode : 最高学历code
         * joinWorkDate : 参加工作时间,如:2018-10-10
         * lifePhotos : 生活照片(多个地址以逗号拼接)
         * name : 姓名
         * sex : 性别
         * sexCode : 性别code
         * updateTime : 更新时间
         * workExperienceList : [{"companyName":"公司名称","dept":"所属部门","endDate":"结束时间,如: 2018-10","id":"id值,新增为空,修改必填","isHide":"对该公司隐藏简历(0:否 1:是)","positionType":"职位类型","startDate":"开始时间,如: 2012-10","workContent":"工作内容"}]
         * workHope : {"city":"工作城市-市","cityId":"工作城市-市ID","id":"id值,新增为空,修改必填","industry":"期望行业","position":"期望职位","province":"工作城市-省","provinceId":"工作城市-省ID","salaryMax":"薪资要求-max, 如: 10","salaryMin":"薪资要求-min 如: 5"}
         * workStatus : 求职状态
         * workStatusCode : 求职状态code
         * workYears : 经验要求
         * workYearsCode : 经验要求code
         * wxId : 微信号
         */

        private String advantage;
        private String birth;
        private String certificatePhotos;
        private String headImg;
        private String highestEducationLevel;
        private String highestEducationLevelCode;
        private String joinWorkDate;
        private String lifePhotos;
        private String name;
        private String sex;
        private String sexCode;
        private String updateTime;
        private WorkHopeBean workHope;
        private String workStatus;
        private String workStatusCode;
        private String workYears;
        private String workYearsCode;
        private String wxId;
        private List<EducationExperienceListBean> educationExperienceList;
        private List<WorkExperienceListBean> workExperienceList;

        @Override
        public String toString() {
            return "{" +
                    "\"advantage\":\"" + advantage + "\"" +
                    ", \"birth\":\"" + birth + "\"" +
                    ", \"certificatePhotos\":\"" + certificatePhotos + "\"" +
                    ", \"headImg\":\"" + headImg + "\"" +
                    ", \"highestEducationLevel\":\"" + highestEducationLevel + "\"" +
                    ", \"highestEducationLevelCode\":\"" + highestEducationLevelCode + "\"" +
                    ", \"joinWorkDate\":\"" + joinWorkDate + "\"" +
                    ", \"lifePhotos\":\"" + lifePhotos + "\"" +
                    ", \"name\":\"" + name + "\"" +
                    ", \"sex\":\"" + sex + "\"" +
                    ", \"sexCode\":\"" + sexCode + "\"" +
                    ", \"updateTime\":\"" + updateTime + "\"" +
                    ", \"workHope\":" + workHope +
                    ", \"workStatus\":\"" + workStatus + "\"" +
                    ", \"workStatusCode\":\"" + workStatusCode + "\"" +
                    ", \"workYears\":\"" + workYears + "\"" +
                    ", \"workYearsCode\":\"" + workYearsCode + "\"" +
                    ", \"wxId\":\"" + wxId + "\"" +
                    ", \"educationExperienceList\":" + educationExperienceList +
                    ", \"workExperienceList\":" + workExperienceList +
                    '}';
        }

        public String getAdvantage() {
            return advantage;
        }

        public void setAdvantage(String advantage) {
            this.advantage = advantage;
        }

        public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public String getCertificatePhotos() {
            return certificatePhotos;
        }

        public void setCertificatePhotos(String certificatePhotos) {
            this.certificatePhotos = certificatePhotos;
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

        public String getJoinWorkDate() {
            return joinWorkDate;
        }

        public void setJoinWorkDate(String joinWorkDate) {
            this.joinWorkDate = joinWorkDate;
        }

        public String getLifePhotos() {
            return lifePhotos;
        }

        public void setLifePhotos(String lifePhotos) {
            this.lifePhotos = lifePhotos;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getSexCode() {
            return sexCode;
        }

        public void setSexCode(String sexCode) {
            this.sexCode = sexCode;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public WorkHopeBean getWorkHope() {
            return workHope;
        }

        public void setWorkHope(WorkHopeBean workHope) {
            this.workHope = workHope;
        }

        public String getWorkStatus() {
            return workStatus;
        }

        public void setWorkStatus(String workStatus) {
            this.workStatus = workStatus;
        }

        public String getWorkStatusCode() {
            return workStatusCode;
        }

        public void setWorkStatusCode(String workStatusCode) {
            this.workStatusCode = workStatusCode;
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

        public String getWxId() {
            return wxId;
        }

        public void setWxId(String wxId) {
            this.wxId = wxId;
        }

        public List<EducationExperienceListBean> getEducationExperienceList() {
            return educationExperienceList;
        }

        public void setEducationExperienceList(List<EducationExperienceListBean> educationExperienceList) {
            this.educationExperienceList = educationExperienceList;
        }

        public List<WorkExperienceListBean> getWorkExperienceList() {
            return workExperienceList;
        }

        public void setWorkExperienceList(List<WorkExperienceListBean> workExperienceList) {
            this.workExperienceList = workExperienceList;
        }

        public static class WorkHopeBean {
            /**
             * city : 工作城市-市
             * cityId : 工作城市-市ID
             * id : id值,新增为空,修改必填
             * industry : 期望行业
             * position : 期望职位
             * province : 工作城市-省
             * provinceId : 工作城市-省ID
             * salaryMax : 薪资要求-max, 如: 10
             * salaryMin : 薪资要求-min 如: 5
             */

            private String city;
            private String cityId;
            private String id;
            private String industry;
            private String position;
            private String province;
            private String provinceId;
            private String salaryMax;
            private String salaryMin;

            @Override
            public String toString() {
                return "{" +
                        "\"city\":\"" + city + "\"" +
                        ", \"cityId\":\"" + cityId + "\"" +
                        ", \"id\":\"" + id + "\"" +
                        ", \"industry\":\"" + industry + "\"" +
                        ", \"position\":\"" + position + "\"" +
                        ", \"province\":\"" + province + "\"" +
                        ", \"provinceId\":\"" + provinceId + "\"" +
                        ", \"salaryMax\":\"" + salaryMax + "\"" +
                        ", \"salaryMin\":\"" + salaryMin + "\"" +
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
        }

        public static class EducationExperienceListBean {
            /**
             * educationLevel : 学历
             * educationLevelCode : 学历code
             * endDate : 结束时间 如:2018-07
             * id : id值,新增为空,修改必填
             * major : 专业
             * schoolName : 学校名称
             * startDate : 开始时间 如:2018-07
             */

            private String educationLevel;
            private String educationLevelCode;
            private String endDate;
            private String id;
            private String major;
            private String schoolName;
            private String startDate;

            @Override
            public String toString() {
                return "{" +
                        "\"educationLevel\":\"" + educationLevel + "\"" +
                        ", \"educationLevelCode\":\"" + educationLevelCode + "\"" +
                        ", \"endDate\":\"" + endDate + "\"" +
                        ", \"id\":\"" + id + "\"" +
                        ", \"major\":\"" + major + "\"" +
                        ", \"schoolName\":\"" + schoolName + "\"" +
                        ", \"startDate\":\"" + startDate + "\"" +
                        '}';
            }

            public String getEducationLevel() {
                return educationLevel;
            }

            public void setEducationLevel(String educationLevel) {
                this.educationLevel = educationLevel;
            }

            public String getEducationLevelCode() {
                return educationLevelCode;
            }

            public void setEducationLevelCode(String educationLevelCode) {
                this.educationLevelCode = educationLevelCode;
            }

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMajor() {
                return major;
            }

            public void setMajor(String major) {
                this.major = major;
            }

            public String getSchoolName() {
                return schoolName;
            }

            public void setSchoolName(String schoolName) {
                this.schoolName = schoolName;
            }

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
            }
        }

        public static class WorkExperienceListBean {
            /**
             * companyName : 公司名称
             * dept : 所属部门
             * endDate : 结束时间,如: 2018-10
             * id : id值,新增为空,修改必填
             * isHide : 对该公司隐藏简历(0:否 1:是)
             * positionType : 职位类型
             * startDate : 开始时间,如: 2012-10
             * workContent : 工作内容
             */

            private String companyName;
            private String dept;
            private String endDate;
            private String id;
            private String isHide;
            private String positionType;
            private String startDate;
            private String workContent;

            @Override
            public String toString() {
                return "{" +
                        "\"companyName\":\"" + companyName + "\"" +
                        ", \"dept\":\"" + dept + "\"" +
                        ", \"endDate\":\"" + endDate + "\"" +
                        ", \"id\":\"" + id + "\"" +
                        ", \"isHide\":\"" + isHide + "\"" +
                        ", \"positionType\":\"" + positionType + "\"" +
                        ", \"startDate\":\"" + startDate + "\"" +
                        ", \"workContent\":\"" + workContent + "\"" +
                        '}';
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getDept() {
                return dept;
            }

            public void setDept(String dept) {
                this.dept = dept;
            }

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIsHide() {
                return isHide;
            }

            public void setIsHide(String isHide) {
                this.isHide = isHide;
            }

            public String getPositionType() {
                return positionType;
            }

            public void setPositionType(String positionType) {
                this.positionType = positionType;
            }

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
            }

            public String getWorkContent() {
                return workContent;
            }

            public void setWorkContent(String workContent) {
                this.workContent = workContent;
            }
        }
    }

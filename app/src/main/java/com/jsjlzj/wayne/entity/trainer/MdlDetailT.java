package com.jsjlzj.wayne.entity.trainer;

import com.jsjlzj.wayne.entity.store.home.VideoBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Describe
 * Created by Bby on 2019-09-08
 * Email bby0856@163.com
 */
public class MdlDetailT {

    /**
     * data : {"advantage":"我的优势","age":"年龄","birth":"出生日期,如:1990-12-29","certificatePhotos":"证书照片(多个地址以逗号拼接)","educationExperienceList":[{"educationLevel":"学历","educationLevelCode":"学历code","endDate":"结束时间 如:2018-07","id":"id值,新增为空,修改必填","major":"专业","schoolName":"学校名称","startDate":"开始时间 如:2018-07"}],"headImg":"头像地址","highestEducationLevel":"最高学历","highestEducationLevelCode":"最高学历code","joinWorkDate":"参加工作时间,如:2018-10-10","lifePhotos":"生活照片(多个地址以逗号拼接)","name":"姓名","sex":"性别","sexCode":"性别code","workExperienceList":[{"companyName":"公司名称","endDate":"结束时间,如: 2018-10","id":"id值,新增为空,修改必填","isHide":"对该公司隐藏简历(0:否 1:是)","positionType":"职位类型","positionTypeId":"职位类型ID","recruitmentTypeId":"招聘类型id","recruitmentTypeName":"招聘类型名称","skillTags":["string"],"startDate":"开始时间,如: 2012-10","workContent":"工作内容"}],"workHopeList":[{"city":"工作城市-市","cityId":"工作城市-市ID","id":"id值,新增为空,修改必填","position":"期望职位","positionTypeId":"职位类型ID","province":"工作城市-省","provinceId":"工作城市-省ID","recruitmentTypeId":"招聘类型id","recruitmentTypeName":"招聘类型名称","salaryMax":"薪资要求-max, 如: 10","salaryMin":"薪资要求-min 如: 5"}],"workStatus":"求职状态","workStatusCode":"求职状态code","workYears":"工作年限","wxId":"微信号"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * advantage : 我的优势
         * age : 年龄
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
         * workExperienceList : [{"companyName":"公司名称","endDate":"结束时间,如: 2018-10","id":"id值,新增为空,修改必填","isHide":"对该公司隐藏简历(0:否 1:是)","positionType":"职位类型","positionTypeId":"职位类型ID","recruitmentTypeId":"招聘类型id","recruitmentTypeName":"招聘类型名称","skillTags":["string"],"startDate":"开始时间,如: 2012-10","workContent":"工作内容"}]
         * workHopeList : [{"city":"工作城市-市","cityId":"工作城市-市ID","id":"id值,新增为空,修改必填","position":"期望职位","positionTypeId":"职位类型ID","province":"工作城市-省","provinceId":"工作城市-省ID","recruitmentTypeId":"招聘类型id","recruitmentTypeName":"招聘类型名称","salaryMax":"薪资要求-max, 如: 10","salaryMin":"薪资要求-min 如: 5"}]
         * workStatus : 求职状态
         * workStatusCode : 求职状态code
         * workYears : 工作年限
         * wxId : 微信号
         */

        private String advantage;
        private String age;
        private String birth;
        private String certificatePhotos;
        private String headImg;
        private String highestEducationLevel;
        private String highestEducationLevelCode;
        private String joinWorkDate;
        private String lifePhotos;
        private String name;
        private String sex;
        private int sexCode;
        private String workStatus;
        private String workStatusCode;
        private String workYears;
        private String wxId;
        private List<EducationExperienceListBean> educationExperienceList;
        private List<WorkExperienceListBean> workExperienceList;
        private List<WorkHopeListBean> workHopeList;
        private WorkHopeListBean workHope;
        private boolean isLike;
        private String yunXinAccount;
        private String englishName;
        private String content;
        private String wxName;
        private List<VideoBean> teachVideos;

        public String getWxName() {
            return wxName;
        }

        public void setWxName(String wxName) {
            this.wxName = wxName;
        }

        public String getEnglishName() {
            return englishName;
        }

        public void setEnglishName(String englishName) {
            this.englishName = englishName;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public WorkHopeListBean getWorkHope() {
            return workHope;
        }

        public void setWorkHope(WorkHopeListBean workHope) {
            this.workHope = workHope;
        }

        public String getYunXinAccount() {
            return yunXinAccount;
        }

        public void setYunXinAccount(String yunXinAccount) {
            this.yunXinAccount = yunXinAccount;
        }


        public boolean isLike() {
            return isLike;
        }

        public void setLike(boolean like) {
            isLike = like;
        }

        public String getAdvantage() {
            return advantage;
        }

        public void setAdvantage(String advantage) {
            this.advantage = advantage;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
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

        public int getSexCode() {
            return sexCode;
        }

        public void setSexCode(int sexCode) {
            this.sexCode = sexCode;
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

        public List<WorkHopeListBean> getWorkHopeList() {
            if(workHopeList==null)workHopeList=new ArrayList<>();
            return workHopeList;
        }

        public void setWorkHopeList(List<WorkHopeListBean> workHopeList) {
            this.workHopeList = workHopeList;
        }

        public List<VideoBean> getTeachVideos() {
            return teachVideos;
        }

        public void setTeachVideos(List<VideoBean> teachVideos) {
            this.teachVideos = teachVideos;
        }

        public static class EducationExperienceListBean implements Serializable{
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

        public static class WorkExperienceListBean implements Serializable {
            /**
             * companyName : 公司名称
             * endDate : 结束时间,如: 2018-10
             * id : id值,新增为空,修改必填
             * isHide : 对该公司隐藏简历(0:否 1:是)
             * positionType : 职位类型
             * positionTypeId : 职位类型ID
             * recruitmentTypeId : 招聘类型id
             * recruitmentTypeName : 招聘类型名称
             * skillTags : ["string"]
             * startDate : 开始时间,如: 2012-10
             * workContent : 工作内容
             */

            private String companyName;
            private String endDate;
            private String id;
            private String isHide;
            private String positionType;
            private String positionTypeId;
            private String recruitmentTypeId;
            private String recruitmentTypeName;
            private String startDate;
            private String workContent;
            private List<String> skillTags;

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
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

            public String getPositionTypeId() {
                return positionTypeId;
            }

            public void setPositionTypeId(String positionTypeId) {
                this.positionTypeId = positionTypeId;
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

            public List<String> getSkillTags() {
                return skillTags;
            }

            public void setSkillTags(List<String> skillTags) {
                this.skillTags = skillTags;
            }
        }

        public static class WorkHopeListBean implements Serializable{
            /**
             * city : 工作城市-市
             * cityId : 工作城市-市ID
             * id : id值,新增为空,修改必填
             * position : 期望职位
             * positionTypeId : 职位类型ID
             * province : 工作城市-省
             * provinceId : 工作城市-省ID
             * recruitmentTypeId : 招聘类型id
             * recruitmentTypeName : 招聘类型名称
             * salaryMax : 薪资要求-max, 如: 10
             * salaryMin : 薪资要求-min 如: 5
             */

            private String city;
            private String cityId;
            private String id;
            private String position;
            private String positionTypeId;
            private String province;
            private String provinceId;
            private String recruitmentTypeId;
            private String recruitmentTypeName;
            private String salaryMax;
            private String salaryMin;

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

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getPositionTypeId() {
                return positionTypeId;
            }

            public void setPositionTypeId(String positionTypeId) {
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
    }
}

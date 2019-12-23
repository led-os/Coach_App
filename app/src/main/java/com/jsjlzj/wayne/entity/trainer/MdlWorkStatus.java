package com.jsjlzj.wayne.entity.trainer;

/**
 * Describe
 * Created by Bby on 2019-09-09
 * Email bby0856@163.com
 */
public class MdlWorkStatus {

    /**
     * data : {"headImg":"http://47.97.126.0:8000/20190908085933_MIEHACR2.png","name":"by","sexCode":"","sex":"","birth":"","age":0,"joinWorkDate":"","workYears":0,"wxId":"","advantage":"fdasfsdfafasfdas","certificatePhotos":"","lifePhotos":"http://47.97.126.0:8000/20190908142556_USHU97F5.png","highestEducationLevelCode":null,"highestEducationLevel":"","workStatusCode":"3","workStatus":"在职-考虑机会","workHopeList":null,"workExperienceList":null,"educationExperienceList":null}
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
         * headImg : http://47.97.126.0:8000/20190908085933_MIEHACR2.png
         * name : by
         * sexCode :
         * sex :
         * birth :
         * age : 0
         * joinWorkDate :
         * workYears : 0
         * wxId :
         * advantage : fdasfsdfafasfdas
         * certificatePhotos :
         * lifePhotos : http://47.97.126.0:8000/20190908142556_USHU97F5.png
         * highestEducationLevelCode : null
         * highestEducationLevel :
         * workStatusCode : 3
         * workStatus : 在职-考虑机会
         * workHopeList : null
         * workExperienceList : null
         * educationExperienceList : null
         */

        private String headImg;
        private String name;
        private String sexCode;
        private String sex;
        private String birth;
        private int age;
        private String joinWorkDate;
        private int workYears;
        private String wxId;
        private String advantage;
        private String certificatePhotos;
        private String lifePhotos;
        private Object highestEducationLevelCode;
        private String highestEducationLevel;
        private String workStatusCode;
        private String workStatus;
        private Object workHopeList;
        private Object workExperienceList;
        private Object educationExperienceList;

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

        public String getSexCode() {
            return sexCode;
        }

        public void setSexCode(String sexCode) {
            this.sexCode = sexCode;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getJoinWorkDate() {
            return joinWorkDate;
        }

        public void setJoinWorkDate(String joinWorkDate) {
            this.joinWorkDate = joinWorkDate;
        }

        public int getWorkYears() {
            return workYears;
        }

        public void setWorkYears(int workYears) {
            this.workYears = workYears;
        }

        public String getWxId() {
            return wxId;
        }

        public void setWxId(String wxId) {
            this.wxId = wxId;
        }

        public String getAdvantage() {
            return advantage;
        }

        public void setAdvantage(String advantage) {
            this.advantage = advantage;
        }

        public String getCertificatePhotos() {
            return certificatePhotos;
        }

        public void setCertificatePhotos(String certificatePhotos) {
            this.certificatePhotos = certificatePhotos;
        }

        public String getLifePhotos() {
            return lifePhotos;
        }

        public void setLifePhotos(String lifePhotos) {
            this.lifePhotos = lifePhotos;
        }

        public Object getHighestEducationLevelCode() {
            return highestEducationLevelCode;
        }

        public void setHighestEducationLevelCode(Object highestEducationLevelCode) {
            this.highestEducationLevelCode = highestEducationLevelCode;
        }

        public String getHighestEducationLevel() {
            return highestEducationLevel;
        }

        public void setHighestEducationLevel(String highestEducationLevel) {
            this.highestEducationLevel = highestEducationLevel;
        }

        public String getWorkStatusCode() {
            return workStatusCode;
        }

        public void setWorkStatusCode(String workStatusCode) {
            this.workStatusCode = workStatusCode;
        }

        public String getWorkStatus() {
            return workStatus;
        }

        public void setWorkStatus(String workStatus) {
            this.workStatus = workStatus;
        }

        public Object getWorkHopeList() {
            return workHopeList;
        }

        public void setWorkHopeList(Object workHopeList) {
            this.workHopeList = workHopeList;
        }

        public Object getWorkExperienceList() {
            return workExperienceList;
        }

        public void setWorkExperienceList(Object workExperienceList) {
            this.workExperienceList = workExperienceList;
        }

        public Object getEducationExperienceList() {
            return educationExperienceList;
        }

        public void setEducationExperienceList(Object educationExperienceList) {
            this.educationExperienceList = educationExperienceList;
        }
    }
}

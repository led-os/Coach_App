package com.jsjlzj.wayne.entity.store;

import java.util.List;

public class MdlInterView {
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
            private String headImg;
            private String id;
            private String name;
            private String positionId;
            private String positionName;
            private String salaryMax;
            private String salaryMin;
            private String storeName;
            private String storeStatus;
            private String trainerStatus;
            private String workHopeId;
            private String interviewTime;

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPositionId() {
                return positionId;
            }

            public void setPositionId(String positionId) {
                this.positionId = positionId;
            }

            public String getPositionName() {
                return positionName;
            }

            public void setPositionName(String positionName) {
                this.positionName = positionName;
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

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public String getStoreStatus() {
                return storeStatus;
            }

            public void setStoreStatus(String storeStatus) {
                this.storeStatus = storeStatus;
            }

            public String getTrainerStatus() {
                return trainerStatus;
            }

            public void setTrainerStatus(String trainerStatus) {
                this.trainerStatus = trainerStatus;
            }

            public String getWorkHopeId() {
                return workHopeId;
            }

            public void setWorkHopeId(String workHopeId) {
                this.workHopeId = workHopeId;
            }

            public String getInterviewTime() {
                return interviewTime;
            }

            public void setInterviewTime(String interviewTime) {
                this.interviewTime = interviewTime;
            }
        }

    }
}


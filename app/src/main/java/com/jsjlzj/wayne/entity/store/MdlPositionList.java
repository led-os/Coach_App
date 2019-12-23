package com.jsjlzj.wayne.entity.store;

import java.util.List;

public class MdlPositionList {


    /**
     * pageNo : 当前页
     * pageSize : 每页大小
     * result : [{"area":"区","areaId":"区ID","city":"市","cityId":"市ID","content":"职位描述","coordinate":"经纬度","createTime":"创建时间","id":"id","lowestEducationLevel":"最低学历","lowestEducationLevelCode":"最低学历code","name":"职位名称","positionTypeId":"职位类型ID","province":"省","provinceId":"省ID","publishTime":"发布时间","recruitmentTypeId":"招聘类型id","recruitmentTypeName":"招聘类型名称","salaryMax":"薪资要求-max","salaryMin":"薪资要求-min","skillRequired":["string"],"status":"状态 1:待开放; 2:招聘中; 3:审核失败; 4:已关闭","workAddress":"工作地址","workYears":"经验要求","workYearsCode":"经验要求code"}]
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
    private List<MdlPosition.DataBean> result;

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

        public List<MdlPosition.DataBean> getResult() {
            return result;
        }

        public void setResult(List<MdlPosition.DataBean> result) {
            this.result = result;
        }

        @Override
    public String toString() {
        return "{" +
                "\"pageNo\":\"" + pageNo + "\"" +
                ", \"pageSize\":\"" + pageSize + "\"" +
                ", \"totalCount\":\"" + totalCount + "\"" +
                ", \"result\":" + result +
                '}';
    }}
}

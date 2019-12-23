package com.jsjlzj.wayne.entity.store;

import java.util.List;

public class MdlPositionType {

    /**
     * id : 类型id
     * name : 类型名称
     * positionTypeList : [null]
     */
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean{

    private String id;
    private String name;
    private List<PositionTypeBean> positionTypeList;

        public DataBean(String id, String name) {
            this.id = id;
            this.name = name;
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

        public List<PositionTypeBean> getPositionTypeList() {
            return positionTypeList;
        }

        public void setPositionTypeList(List<PositionTypeBean> positionTypeList) {
            this.positionTypeList = positionTypeList;
        }

    public class PositionTypeBean {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }}
}

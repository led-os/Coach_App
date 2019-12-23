package com.jsjlzj.wayne.entity.store;

import java.util.List;

public class MdlSkillRequired {

        /**
         * id : 技能要求id
         * name : 技能要求名称
         */
        private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public class DataBean{

        private String id;
        private String name;

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

        @Override
        public String toString() {
            return "{" +
                    "\"id\":\"" + id + "\"" +
                    ", \"name\":\"" + name + "\"" +
                    '}';
        }
        }
    }


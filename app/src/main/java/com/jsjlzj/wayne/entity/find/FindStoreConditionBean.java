package com.jsjlzj.wayne.entity.find;

import java.util.List;

/**
 * @ClassName: FindStoreConditionBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/6/25 11:55
 */
public class FindStoreConditionBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * children : [null]
         * id : 0
         * name : string
         */

        private int id;
        private String name;
        private List<DataBean> children;
        private int selectId = -1;

        public int getSelectId() {
            return selectId;
        }

        public void setSelectId(int selectId) {
            this.selectId = selectId;
        }

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

        public List<DataBean> getChildren() {
            return children;
        }

        public void setChildren(List<DataBean> children) {
            this.children = children;
        }


        public class ChildBean {
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
        }
    }
}

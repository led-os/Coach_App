package com.jsjlzj.wayne.entity.store;

import java.util.List;

public class MdlBenefits {

    /**
     * id : 公司福利id
     * name : 公司福利名称
     */
    private List<ListBean> data;

    public List<ListBean> getData() {
        return data;
    }

    public void setData(List<ListBean> data) {
        this.data = data;
    }

    public static class ListBean {
//            private String id;
            private String name;
//            public String getId() {
//                return id;
//            }
//
//            public void setId(String id) {
//                this.id = id;
//            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @Override
            public String toString() {
                return "{" +
                        "\"id\":\""  + "\"" +
                        ", \"name\":\"" + name + "\"" +
                        '}';
            }
    }
}

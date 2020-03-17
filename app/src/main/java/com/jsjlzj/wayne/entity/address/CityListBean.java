package com.jsjlzj.wayne.entity.address;

import java.util.List;

/**
 * @ClassName: CityListBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/3/3 11:42
 */
public class CityListBean {


    /**
     * title : A
     * lists : ["阿坝","阿拉善","阿里","安康","安庆","鞍山","安顺","安阳","澳门"]
     */

    private String title;
    private List<String> lists;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getLists() {
        return lists;
    }

    public void setLists(List<String> lists) {
        this.lists = lists;
    }
}

package com.jsjlzj.wayne.entity.store;

/**
 * @ClassName: SelectBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/15 12:11
 */
public class SelectBean {

    private String name;

    private boolean isSelect;

    public SelectBean(String name, boolean isSelect) {
        this.name = name;
        this.isSelect = isSelect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}

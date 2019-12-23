package com.jsjlzj.wayne.entity.store;

public class ItemsBean {
    public ItemsBean(String code, String name, String items, boolean isSelected) {
        this.code = code;
        this.name = name;
        this.items = items;
        this.isSelected = isSelected;
    }

    /**
     * code : <3
     * name : 3K以下
     * items : null
     */

    private String code;
    private String name;
    private String items;
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }
}

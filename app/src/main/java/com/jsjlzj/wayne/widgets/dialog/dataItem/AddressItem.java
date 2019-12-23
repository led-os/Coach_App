package com.jsjlzj.wayne.widgets.dialog.dataItem;

import java.util.Arrays;
import java.util.List;

public class AddressItem {
    private String province;

    List<String> city;

    public AddressItem() {
    }

    public AddressItem(String province, String... citys) {
        this.province = province;
        this.city = Arrays.asList(citys);
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public List<String> getCity() {
        return city;
    }

    public void setCity(List<String> city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return province+"";
    }
}

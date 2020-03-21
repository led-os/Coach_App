package com.jsjlzj.wayne.entity.address;

import java.io.Serializable;

/**
 * @ClassName: LocationBean
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/3/20 23:58
 */
public class LocationBean implements Serializable {

    private String city;
    private String address;
    private double longitude;
    private double latitude;

    public LocationBean() {
    }

    public LocationBean(String city, String address, double longitude, double latitude) {
        this.city = city;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}

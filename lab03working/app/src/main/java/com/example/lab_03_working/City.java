package com.example.lab_03_working;

import java.io.Serializable;

public class City implements Serializable { // need to use Serializable, so that it can be saved into the Bundle
    private String name;
    private String province;
    public City(String name, String province) {
        this.name = name;
        this.province = province;
    }
    public String getName() {
        return name;
    }
    public String getProvince() {
        return province;
    }

    public void setName(String name) { // setters for name of city
        this.name = name;
    }

    public void setProvince(String province) { // setters for name of province
        this.province = province;
    }
}
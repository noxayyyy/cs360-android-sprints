package com.example.listycity;

public class City {
    private String name;
    private String province;

    public City(String name, String province) {
        this.name = name;
        this.province = province;
    }

    public String get_name() {
        return name;
    }

    public String get_province() {
        return province;
    }

    public void set_name(String name) {
        this.name = name;
    }

    public void set_province(String province) {
        this.province = province;
    }
}

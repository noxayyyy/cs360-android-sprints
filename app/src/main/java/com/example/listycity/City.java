package com.example.listycity;

/**
 * This is a class that defins a City
 */
public class City implements Comparable {
    private String city;
    private String province;

    City(String city, String province) {
        this.city = city;
        this.province = province;
    }

    String get_city_name() {
        return this.city;
    }

    String get_province_name() {
        return this.province;
    }

    @Override
    public int compareTo(Object o) {
        City city = (City) o;
        return this.city.compareTo(city.get_city_name());
    }
}
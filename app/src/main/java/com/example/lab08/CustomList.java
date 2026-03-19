package com.example.lab08;

import java.util.ArrayList;
import java.util.List;

public class CustomList {
    private List<City> cities;

    public CustomList() {
        this.cities = new ArrayList<>();
    }

    public void addCity(City city) {
        cities.add(city);
    }

    public boolean hasCity(City city) {
        return cities.contains(city);
    }

    public void delete(City city) throws Exception {
        if (!cities.remove(city)) {
            throw new Exception("City to delete not found.");
        }
    }

    public List<City> get_cities() {
        return cities;
    }

    public int count_cities() {
        return cities.size();
    }
}

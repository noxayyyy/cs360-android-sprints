package com.example.listycity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is a class that keeps a list of City objects
 */
public class CityList {
    private List<City> cities = new ArrayList<City>();

    /**
     * This adds a city to the list if the city does not exist
     *
     * @param city This is a candidate city to add
     */
    public void add(City city) {
        if (cities.contains(city)) {
            throw new IllegalArgumentException();
        }
        cities.add(city);
    }

    /**
     * This returns a sorted list of cities
     *
     * @return Return the sorted list
     */
    public List<City> get_cities() {
        List<City> list = cities;
        Collections.sort(list);
        return list;
    }

    /**
     * This checks if a given city is contained in the list
     *
     * @param city The city to check
     * @return true if city is in the list, false otherwise
     */
    public boolean has_city(City city) {
        return cities.contains(city);
    }

    /**
     * Delete a given city if present in the list
     *
     * @param city The city to delete
     * @throws Exception City is not present in list
     */
    public void delete(City city) throws Exception {
        if (!cities.remove(city)) {
            throw new Exception("City to delete not found.");
        }
    }

    /**
     * Return total count of cities in the list
     *
     * @return Size of cities list
     */
    public int count_cities() {
        return cities.size();
    }
}

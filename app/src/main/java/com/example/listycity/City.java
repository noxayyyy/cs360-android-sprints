package com.example.listycity;

/**
 * This is a class that defins a City
 */
public class City implements Comparable {
    private String city;
    private String province;

    /**
     * Constructor for a City object
     *
     * @param city     Name of the city
     * @param province Name of the province
     */
    City(String city, String province) {
        this.city = city;
        this.province = province;
    }

    /**
     * Returns name of the city
     *
     * @return City name
     */
    String get_city_name() {
        return this.city;
    }

    /**
     * Returns name of the province
     *
     * @return Province name
     */
    String get_province_name() {
        return this.province;
    }

    /**
     * Compare two cities by name as default comparison
     *
     * @param o the reference object with which to compare.
     * @return The value 0 if the argument city name is equal to this name;
     * a value less than 0 if this name is lexicographically less than the argument name;
     * and a value greater than 0 if this name is lexicographically greater than the argument name
     */
    @Override
    public int compareTo(Object o) {
        City city = (City) o;
        return this.city.compareTo(city.get_city_name());
    }

    /**
     * Lexicographically compares two cities to check if they are equal in both city name and province name
     *
     * @param o the reference object with which to compare.
     * @return true if both city name and province name are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        City city = (City) o;
        return this.city.compareTo(city.get_city_name()) == 0 && this.province.compareTo(city.get_province_name()) == 0;
    }
}
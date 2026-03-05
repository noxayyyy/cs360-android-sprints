package com.example.listycity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityListTest {
    private CityList mockCityList() {
        CityList cityList = new CityList();
        cityList.add(mockCity());
        return cityList;
    }

    private City mockCity() {
        return new City("Edmonton", "Alberta");
    }

    @Test
    void testAdd() {
        CityList cityList = mockCityList();
        assertEquals(1, cityList.get_cities().size());
        City city = new City("Regina", "Saskatchewan");
        cityList.add(city);
        assertEquals(2, cityList.get_cities().size());
        assertTrue(cityList.get_cities().contains(city));
    }

    @Test
    void testAddException() {
        CityList cityList = mockCityList();
        City city = new City("Yellowknife", "Northwest Territories");
        cityList.add(city);
        assertThrows(IllegalArgumentException.class, () -> {
            cityList.add(city);
        });
    }

    @Test
    void testGetCities() {
        CityList cityList = mockCityList();
        // This line checks if the first city in the cityList (retrieved by cityList.getCities().get(0))
        // is the same as the city returned by mockCity()
        assertEquals(0, mockCity().compareTo(cityList.get_cities().get(0)));
        // This pushes down the original city
        City city = new City("Charlottetown", "Prince Edward Island");
        cityList.add(city);
        // Now the original city should be at position 1
        assertEquals(0, city.compareTo(cityList.get_cities().get(0)));
        assertEquals(0, mockCity().compareTo(cityList.get_cities().get(1)));
    }

    @Test
    void testHasCity() {
        CityList cityList = mockCityList();
        assertTrue(cityList.has_city(mockCity()));
        assertFalse(cityList.has_city(new City("halabalu", "lmao")));
    }

    @Test
    void testDelete() {
        CityList cityList = mockCityList();

        try {
            cityList.delete(mockCity());
        } catch (Exception e) {
            fail("Failed to find city to delete.");
        }
        assertEquals(0, cityList.get_cities().size());

        Exception exception = assertThrows(Exception.class, () -> cityList.delete(new City("false", "false")));
        assertEquals("City to delete not found.", exception.getMessage());

        cityList.add(new City("test1", "test1"));

        exception = assertThrows(Exception.class, () -> cityList.delete(new City("false", "false")));
        assertEquals("City to delete not found.", exception.getMessage());
    }

    @Test
    void testCountCities() {
        CityList cityList = mockCityList();
        assertEquals(1, cityList.count_cities());

        try {
            cityList.delete(mockCity());
        } catch (Exception e) {
            fail("Failed to find city to delete.");
        }
        assertEquals(0, cityList.count_cities());

        for (int i = 0; i < 10; i++) {
            cityList.add(new City(String.format("City%d", i), String.format("Province%d", i)));
        }
        assertEquals(10, cityList.count_cities());
    }
}
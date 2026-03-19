package com.example.lab08;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomListTest {
    private City mockCity() {
        return new City("Edmonton", "Alberta");
    }

    @Test
    public void testHasCity() {
        CustomList list = new CustomList();
        City calgary = new City("Calgary", "AB");
        list.addCity(calgary);
        assertTrue(list.hasCity(calgary));
    }

    @Test
    void testDelete() {
        CustomList list = new CustomList();
        list.addCity(mockCity());

        try {
            list.delete(mockCity());
        } catch (Exception e) {
            fail("Failed to find city to delete.");
        }
        assertEquals(0, list.get_cities().size());

        Exception exception = assertThrows(Exception.class, () -> list.delete(new City("false", "false")));
        assertEquals("City to delete not found.", exception.getMessage());

        list.addCity(new City("test1", "test1"));

        exception = assertThrows(Exception.class, () -> list.delete(new City("false", "false")));
        assertEquals("City to delete not found.", exception.getMessage());
    }
}
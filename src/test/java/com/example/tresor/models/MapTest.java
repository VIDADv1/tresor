package com.example.tresor.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MapTest {

    private Map map;

    @BeforeEach
    public void setUp() {
        map = new Map(3, 4);
    }

    @Test
    public void testAddMountain() {
        map.addMountain(1, 1);
        assertTrue(map.getCell(1, 1).isMountain());
    }

    @Test
    public void testAddTreasure() {
        map.addTreasure(0, 3, 2);
        assertEquals(2, map.getCell(0, 3).getTreasures());
    }

    @Test
    public void testIsAccessible() {
        map.addMountain(1, 1);
        assertFalse(map.isAccessible(1, 1));
        assertTrue(map.isAccessible(0, 0));
    }
}
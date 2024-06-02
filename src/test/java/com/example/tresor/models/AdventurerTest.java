package com.example.tresor.models;

import static org.junit.jupiter.api.Assertions.*;

import com.example.tresor.enums.Orientation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdventurerTest {

    private Map map;

    @BeforeEach
    public void setUp() {
        map = new Map(3, 4);
        map.addMountain(1, 1);
        map.addTreasure(0, 3, 2);
    }

    @Test
    public void testMovements() {
        Adventurer adventurer = new Adventurer("Indiana", 1, 0, Orientation.NORTH, "AADADA");
        adventurer.move(map);

        assertEquals(2, adventurer.getX());
        assertEquals(1, adventurer.getY());
        assertEquals(Orientation.SOUTH, adventurer.getOrientation());
        assertEquals(0, adventurer.getCollectedTreasures());
    }

    @Test
    public void testBlockedByMountain() {
        Adventurer adventurer = new Adventurer("Lara", 1, 0, Orientation.NORTH, "A");
        adventurer.move(map);

        assertEquals(1, adventurer.getX());
        assertEquals(0, adventurer.getY());
        assertEquals(Orientation.NORTH, adventurer.getOrientation());
    }

    @Test
    public void testCollectTreasure() {
        Adventurer adventurer = new Adventurer("Dora", 0, 2, Orientation.SOUTH, "A");
        adventurer.move(map);

        assertEquals(0, adventurer.getX());
        assertEquals(3, adventurer.getY());
        assertEquals(1, adventurer.getCollectedTreasures());
        assertEquals(1, map.getCell(0, 3).getTreasures());
    }
}
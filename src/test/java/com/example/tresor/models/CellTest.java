package com.example.tresor.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CellTest {

    private Cell cell;

    @BeforeEach
    public void setUp() {
        cell = new Cell();
    }

    @Test
    public void testMountain() {
        cell.setMountain(true);
        assertTrue(cell.isMountain());

        cell.setMountain(false);
        assertFalse(cell.isMountain());
    }

    @Test
    public void testTreasures() {
        cell.setTreasures(3);
        assertEquals(3, cell.getTreasures());

        cell.collectTreasure();
        assertEquals(2, cell.getTreasures());
    }
}
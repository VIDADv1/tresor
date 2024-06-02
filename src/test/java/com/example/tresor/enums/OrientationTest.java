package com.example.tresor.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class OrientationTest {

    @Test
    public void testLeft() {
        assertEquals(Orientation.WEST, Orientation.NORTH.left());
        assertEquals(Orientation.SOUTH, Orientation.WEST.left());
        assertEquals(Orientation.EAST, Orientation.SOUTH.left());
        assertEquals(Orientation.NORTH, Orientation.EAST.left());
    }

    @Test
    public void testRight() {
        assertEquals(Orientation.EAST, Orientation.NORTH.right());
        assertEquals(Orientation.NORTH, Orientation.WEST.right());
        assertEquals(Orientation.WEST, Orientation.SOUTH.right());
        assertEquals(Orientation.SOUTH, Orientation.EAST.right());
    }
}
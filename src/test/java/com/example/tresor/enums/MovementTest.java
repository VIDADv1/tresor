package com.example.tresor.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MovementTest {

    @Test
    public void testFromCode() {
        assertEquals(Movement.ADVANCE, Movement.fromCode('A'));
        assertEquals(Movement.TURN_LEFT, Movement.fromCode('G'));
        assertEquals(Movement.TURN_RIGHT, Movement.fromCode('D'));
    }

    @Test
    public void testFromInvalidCode() {
        assertThrows(IllegalArgumentException.class, () -> Movement.fromCode('X'));
    }
}
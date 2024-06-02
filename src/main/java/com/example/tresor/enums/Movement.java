package com.example.tresor.enums;

public enum Movement {
    ADVANCE('A'), TURN_LEFT('G'), TURN_RIGHT('D');

    private final char code;

    Movement(char code) {
        this.code = code;
    }

    public static Movement fromCode(char code) {
        for (Movement movement : values()) {
            if (movement.code == code) {
                return movement;
            }
        }
        throw new IllegalArgumentException("Unknown movement code: " + code);
    }
}

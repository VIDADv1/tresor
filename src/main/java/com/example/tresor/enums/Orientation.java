package com.example.tresor.enums;

import lombok.Getter;

@Getter
public enum Orientation {
    NORTH('N'), EAST('E'), SOUTH('S'), WEST('O');

    private final char code;

    Orientation(char code) {
        this.code = code;
    }

    public Orientation left() {
        return values()[(ordinal() + 3) % 4];
    }

    public Orientation right() {
        return values()[(ordinal() + 1) % 4];
    }

    public static Orientation fromCode(char code) {
        for (Orientation orientation : values()) {
            if (orientation.code == code) {
                return orientation;
            }
        }
        throw new IllegalArgumentException("Unknown orientation code: " + code);
    }
}
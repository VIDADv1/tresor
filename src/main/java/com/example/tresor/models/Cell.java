package com.example.tresor.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Cell {

    private boolean mountain;
    private int treasures;

    public void collectTreasure() {
        if (treasures > 0) {
            treasures--;
        }
    }
}

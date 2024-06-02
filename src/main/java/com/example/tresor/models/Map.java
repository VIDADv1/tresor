package com.example.tresor.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Map {

    private final int width;
    private final int height;
    private final Cell[][] cells;
    @Setter
    private List<Adventurer> adventurers = new ArrayList<>();

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public void addMountain(int x, int y) {
        cells[y][x].setMountain(true);
    }

    public void addAdventurer(int x, int y) {
        cells[y][x].setAdventurer(true);
    }

    public void addTreasure(int x, int y, int treasures) {
        cells[y][x].setTreasures(treasures);
    }

    public void simulate() {
        while (adventurers.stream().anyMatch(adventurer -> !adventurer.getMovements().isEmpty())) {
            for (Adventurer adventurer : adventurers) {
                adventurer.move(this);
            }
        }
    }

    public boolean isAccessible(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return false;
        }
        return !cells[y][x].isMountain() && !cells[y][x].isAdventurer();
    }

    public Cell getCell(int x, int y) {
        return cells[y][x];
    }
}

package com.example.tresor.models;
import com.example.tresor.enums.Movement;
import com.example.tresor.enums.Orientation;
import lombok.Getter;

import java.util.LinkedList;
import java.util.Queue;

@Getter
public class Adventurer {

    private final String name;
    private int x;
    private int y;
    private Orientation orientation;
    private final Queue<Movement> movements;
    private int collectedTreasures = 0;

    public Adventurer(String name, int x, int y, Orientation orientation, String movementSequence) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        this.movements = parseMovements(movementSequence);
    }

    private Queue<Movement> parseMovements(String movementSequence) {
        Queue<Movement> queue = new LinkedList<>();
        for (char ch : movementSequence.toCharArray()) {
            queue.add(Movement.fromCode(ch));
        }
        return queue;
    }

    public void move(Map map) {
        while (!movements.isEmpty()) {
            Movement movement = movements.poll();
            switch (movement) {
                case ADVANCE:
                    advance(map);
                    break;
                case TURN_LEFT:
                    turnLeft();
                    break;
                case TURN_RIGHT:
                    turnRight();
                    break;
            }
        }
    }

    private void advance(Map map) {
        int newX = x;
        int newY = y;
        switch (orientation) {
            case NORTH:
                newY--;
                break;
            case SOUTH:
                newY++;
                break;
            case EAST:
                newX++;
                break;
            case WEST:
                newX--;
                break;
        }
        if (map.isAccessible(newX, newY)) {
            x = newX;
            y = newY;
            Cell cell = map.getCell(x, y);
            if (cell.getTreasures() > 0) {
                cell.collectTreasure();
                collectedTreasures++;
            }
        }
    }

    private void turnLeft() {
        orientation = orientation.left();
    }

    private void turnRight() {
        orientation = orientation.right();
    }

    @Override
    public String toString() {
        return String.format("A - %s - %d - %d - %s - %d", name, x, y, orientation.getCode(), collectedTreasures);
    }
}

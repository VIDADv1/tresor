package com.example.tresor.processor;

import com.example.tresor.enums.Orientation;
import com.example.tresor.models.Adventurer;
import com.example.tresor.singleton.TresorSingleton;
import org.springframework.batch.item.ItemProcessor;

public class MapItemProcessor implements ItemProcessor<String, Object> {

    @Override
    public Object process(String line) {
        char firstChar = line.charAt(0);

        switch (firstChar) {
            // Processing the infos about the map
            case 'C': {
                String[] parts = line.split(" - ");
                int width = Integer.parseInt(parts[1]);
                int height = Integer.parseInt(parts[2]);
                TresorSingleton.initializeMap(width, height);
                break;
            }
            // Processing the infos about the mountains
            case 'M': {
                String[] parts = line.split(" - ");
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);
                TresorSingleton.getMap().addMountain(x, y);
                break;
            }
            // Processing the infos about the treasures
            case 'T': {
                String[] parts = line.split(" - ");
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);
                int treasures = Integer.parseInt(parts[3]);
                TresorSingleton.getMap().addTreasure(x, y, treasures);
                break;
            }
            // Processing the infos about the adventurers
            case 'A': {
                String[] parts = line.split(" - ");
                String name = parts[1];
                int x = Integer.parseInt(parts[2]);
                int y = Integer.parseInt(parts[3]);
                Orientation orientation = Orientation.fromCode(parts[4].charAt(0));
                String movements = parts[5];
                Adventurer adventurer = new Adventurer(name, x, y, orientation, movements);
                TresorSingleton.getAdventurers().add(adventurer);
                TresorSingleton.getMap().addAdventurer(x, y);
                break;
            }
            default:
                break;
        }

        return null;
    }
}

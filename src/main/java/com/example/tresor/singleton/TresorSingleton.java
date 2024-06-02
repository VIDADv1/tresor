package com.example.tresor.singleton;

import com.example.tresor.models.Adventurer;
import com.example.tresor.models.Map;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("singleton")
public class TresorSingleton {

    @Getter
    private static Map map;

    @Getter
    private static final List<Adventurer> adventurers = new ArrayList<>();

    private TresorSingleton() {
    }

    public static void initializeMap(int width, int height) {
        map = new Map(width, height);
    }

    public static void simulate() {
        map.setAdventurers(adventurers);
        map.simulate();
    }
}

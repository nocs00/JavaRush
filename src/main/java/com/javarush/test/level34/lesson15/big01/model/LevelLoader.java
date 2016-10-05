package com.javarush.test.level34.lesson15.big01.model;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader {

    public LevelLoader(Path levels) {

    }

    public GameObjects getLevel(int level) {
        int centralCoord = Model.FIELD_SELL_SIZE/2;
        Set<Wall> walls = new HashSet<>();
        walls.add(new Wall(4*centralCoord, 4*centralCoord));
        walls.add(new Wall(10*centralCoord, 10*centralCoord));
        walls.add(new Wall(16*centralCoord, 16*centralCoord));
        Set<Box> boxes = new HashSet<>();
        boxes.add(new Box(20*centralCoord, 20*centralCoord));
        boxes.add(new Box(30*centralCoord, 20*centralCoord));
        boxes.add(new Box(20*centralCoord, 30*centralCoord));
        Set<Home> homes = new HashSet<>();
        homes.add(new Home(26*centralCoord, 26*centralCoord));
        homes.add(new Home(20*centralCoord, 26*centralCoord));
        Player player = new Player(30*centralCoord, 30*centralCoord);

        GameObjects gameObjects = new GameObjects(walls, boxes, homes, player);
        return gameObjects;
    }
}

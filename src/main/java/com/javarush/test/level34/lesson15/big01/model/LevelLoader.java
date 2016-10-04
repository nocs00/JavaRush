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
        walls.add(new Wall(5*centralCoord, 5*centralCoord));
        walls.add(new Wall(10*centralCoord, 10*centralCoord));
        walls.add(new Wall(15*centralCoord, 15*centralCoord));
        Set<Box> boxes = new HashSet<>();
        boxes.add(new Box(20*centralCoord, 20*centralCoord));
        Set<Home> homes = new HashSet<>();
        homes.add(new Home(25*centralCoord, 25*centralCoord));
        Player player = new Player(30*centralCoord, 30*centralCoord);

        GameObjects gameObjects = new GameObjects(walls, boxes, homes, player);
        return gameObjects;
    }
}

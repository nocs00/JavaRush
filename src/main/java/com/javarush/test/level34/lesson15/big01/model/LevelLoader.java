package com.javarush.test.level34.lesson15.big01.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.HashSet;

public class LevelLoader {

    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        level = level & 61;

        HashSet<Wall> walls = new HashSet<>();
        HashSet<Box> boxes = new HashSet<>();
        HashSet<Home> homes = new HashSet<>();
        Player player = null;

        try (
                BufferedReader fileReader = new BufferedReader(new FileReader(levels.toString()))
                ) {

            int base = Model.FIELD_SELL_SIZE;
            int offset = base / 2;

            while (!fileReader.readLine().contains("Maze: " + level)) ;
            for (int i = 0; i < 2; i++) fileReader.readLine();
            int levelHeight = Integer.parseInt(fileReader.readLine().split(" ")[2]);
            for (int i = 0; i < 3; i++) fileReader.readLine();

            for (int _y = 0; _y < levelHeight; _y++) {
                String read = fileReader.readLine();
                for (int _x = 0; _x < read.length(); _x++) {

                    int x = _x * base + offset;
                    int y = _y * base + offset;

                    switch (read.charAt(_x)) {
                        case 'X':
                            walls.add(new Wall(x, y));
                            break;
                        case '@':
                            player = new Player(x, y);
                            break;
                        case '*':
                            boxes.add(new Box(x, y));
                            break;
                        case '.':
                            homes.add(new Home(x, y));
                            break;
                        case '&':
                            boxes.add(new Box(x, y));
                            homes.add(new Home(x, y));
                            break;
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new GameObjects(walls, boxes, homes, player);
    }
}
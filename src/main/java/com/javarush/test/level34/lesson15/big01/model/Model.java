package com.javarush.test.level34.lesson15.big01.model;

import com.javarush.test.level34.lesson15.big01.controller.EventListener;

import java.nio.file.Paths;

public class Model {
    public final static int FIELD_SELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(
            Paths.get("/home/user482400/Desktop/repos-git/JavaRushHomeWork/src/main/java/com/javarush/test/level34/lesson15/big01/res/levels.txt"));

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        if (gameObjects == null) {
            gameObjects = levelLoader.getLevel(currentLevel);
        }
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        ++currentLevel;
        restart();
    }

    public void move(Direction direction) {
        if (checkWallCollision(gameObjects.getPlayer(), direction)) return;
        if (checkBoxCollision(direction)) return;

        int delta = FIELD_SELL_SIZE;
        int deltaX = 0;
        int deltaY = 0;

        switch (direction) {
            case UP: deltaY = -delta; break;
            case DOWN: deltaY = delta; break;
            case LEFT: deltaX = -delta; break;
            case RIGHT: deltaX = delta; break;
        }

        gameObjects.getPlayer().move(deltaX, deltaY);
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall wall : gameObjects.getWalls()) {
            if (gameObject.isCollision(wall, direction)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkBoxCollision(Direction direction) {

        if (checkWallCollision(gameObjects.getPlayer(), direction)) {
            return true;
        }

        for (Box box : gameObjects.getBoxes()) {
            if (gameObjects.getPlayer().isCollision(box, direction)) {
                if (checkWallCollision(box, direction)) return true;
                for (Box nextBox : gameObjects.getBoxes()) {
                    if (box.isCollision(nextBox, direction)) return true;
                }

                int delta = FIELD_SELL_SIZE;
                int deltaX = 0;
                int deltaY = 0;

                switch (direction) {
                    case UP: deltaY = -delta; break;
                    case DOWN: deltaY = delta; break;
                    case LEFT: deltaX = -delta; break;
                    case RIGHT: deltaX = delta; break;
                }

                box.move(deltaX, deltaY);
            }
        }

        return false;
    }

    public void checkCompletion() {
        int boxCount = gameObjects.getBoxes().size();
        int homesWithBoxes = 0;
        for (Home home : gameObjects.getHomes()) {
            for (Box box : gameObjects.getBoxes()) {
                if (box.getX() == home.getX()
                        && box.getY() == home.getY()) {
                    homesWithBoxes++;
                    break;
                }
            }
        }
        if (boxCount == homesWithBoxes) {
            eventListener.levelCompleted(currentLevel);
        }
    }
}
package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

import static com.javarush.test.level34.lesson15.big01.model.Direction.*;

public abstract class CollisionObject extends GameObject {
    public CollisionObject(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {

    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        int currentX = this.getX();
        int currentY = this.getY();

        switch (direction) {
            case LEFT:
                currentX -= Model.FIELD_SELL_SIZE;
                break;
            case RIGHT:
                currentX += Model.FIELD_SELL_SIZE;
                break;
            case UP:
                currentY -= Model.FIELD_SELL_SIZE;
                break;
            case DOWN:
                currentY += Model.FIELD_SELL_SIZE;
                break;
        }

        return currentX == gameObject.getX() && currentY == gameObject.getY();
    }
}

package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

public class Box extends CollisionObject implements Movable {
    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(int x, int y) {
        this.setX(x + this.getX());
        this.setY(y + this.getY());
    }

    @Override
    public void draw(Graphics graphics) {
        int x = this.getX()-(this.getWidth()/2);
        int y = this.getY()-(this.getHeight()/2);
        graphics.setColor(Color.ORANGE);
        graphics.fillRect(x, y, this.getWidth(), this.getHeight());
    }
}
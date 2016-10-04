package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

public class Wall extends CollisionObject {
    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        Color brown = new Color(165,42,42);
        graphics.setColor(brown);
        int x = this.getX() - this.getWidth()/2;
        int y = this.getY() - this.getHeight()/2;
        graphics.fillRect(x, y, this.getWidth(), this.getHeight());
    }
}

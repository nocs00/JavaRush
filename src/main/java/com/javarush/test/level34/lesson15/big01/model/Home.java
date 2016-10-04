package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

public class Home extends GameObject {
    public Home(int x, int y) {
        super(x, y, 2, 2);
    }

    @Override
    public void draw(Graphics graphics) {
        int x = this.getX() - this.getWidth()/2;
        int y = this.getY() - this.getHeight()/2;
        graphics.setColor(Color.RED);
        graphics.drawOval(x, y, this.getWidth(), this.getHeight());
    }
}

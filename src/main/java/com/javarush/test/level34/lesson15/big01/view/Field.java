package com.javarush.test.level34.lesson15.big01.view;

import com.javarush.test.level34.lesson15.big01.model.Box;
import com.javarush.test.level34.lesson15.big01.model.Home;
import com.javarush.test.level34.lesson15.big01.model.Player;

import javax.swing.*;
import java.awt.*;

public class Field extends JPanel {
    private View view;

    public Field(View view) {
        this.view = view;
    }

    @Override
    public void paint(Graphics g) {
        Player player = new Player(10, 10);
        Box box = new Box(50,50);
        Home home = new Home(100, 100);
        player.draw(g);
        box.draw(g);
        home.draw(g);
    }
}
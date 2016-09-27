package com.javarush.test.level21.lesson16.big01;

import java.util.ArrayList;

/**
 * Created by pdudenkov on 05.02.2016.
 */
public class Hippodrome
{
    ArrayList<Horse> horses;
    public static Hippodrome game;

    public ArrayList<Horse> getHorses()
    {
        if (horses == null)
            horses = new ArrayList<Horse>();
        return horses;
    }

    public static void main(String[] args)
    {
        game = new Hippodrome();
        Horse h1 = new Horse("horse1", 3, 0);
        Horse h2 = new Horse("horse2", 3, 0);
        Horse h3 = new Horse("horse3", 3, 0);
        game.getHorses().add(h1);
        game.getHorses().add(h2);
        game.getHorses().add(h3);

        game.run();
        game.printWinner();
    }

    public Horse getWinner() {
        if (this.getHorses() == null)
            return null;

        Horse winner = this.getHorses().get(0);

        for (Horse h : this.getHorses()) {
            if (h.getDistance() > winner.getDistance())
                winner = h;
        }
        return winner;
    }

    public void printWinner() {
        System.out.println("Winner is "+this.getWinner().getName()+"!");
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            this.move();
            this.print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {

            }
        }
    }

    public void move() {
        for (Horse horse : this.getHorses()) {
            horse.move();
        }
    }

    public void print() {
        for (Horse horse : this.getHorses()) {
            horse.print();
        }

        System.out.println();
        System.out.println();
    }
}

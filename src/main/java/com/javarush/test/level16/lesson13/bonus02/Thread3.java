package com.javarush.test.level16.lesson13.bonus02;

/**
 * Created by pdudenkov on 23.11.15.
 */
public class Thread3 extends Thread
{
    @Override
    public void run()
    {
        while(true) {
            try {
                this.sleep(500);
                System.out.println("Ура");
            } catch (InterruptedException e) {}

        }
    }
}

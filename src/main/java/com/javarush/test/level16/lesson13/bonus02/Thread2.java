package com.javarush.test.level16.lesson13.bonus02;

/**
 * Created by pdudenkov on 23.11.15.
 */
public class Thread2 extends Thread
{
    @Override
    public void run()
    {
        //while(!this.isInterrupted()) {
            try {
                while (!this.isInterrupted()) {}
                throw new InterruptedException();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            }
        //}
    }
}

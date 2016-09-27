package com.javarush.test.level16.lesson13.bonus02;

/**
 * Created by pdudenkov on 23.11.15.
 */
public class Thread4 extends Thread implements Message
{
    @Override
    public void showWarning()
    {

        try{
            this.interrupt();
            this.join();
        }catch (InterruptedException e){
        }
    }

    @Override
    public void run()
    {
        while (!this.isInterrupted()) {

        }
    }
}

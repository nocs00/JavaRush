package com.javarush.test.level30.lesson04.home01;

import java.util.concurrent.TransferQueue;

/**
 * Created by pdudenkov on 13.07.2016.
 */
public class Consumer implements Runnable
{
    private TransferQueue<ShareItem> queue;

    public Consumer(TransferQueue<ShareItem> queue)
    {
        this.queue = queue;
    }

    @Override
    public void run()
    {
        try
        {
            Thread.sleep(500);
            while (true)
            {
                ShareItem item = queue.take();
                System.out.format("Processing %s\n", item.toString());
            }
        }
        catch (InterruptedException e)
        {
            return;
        }
    }
}

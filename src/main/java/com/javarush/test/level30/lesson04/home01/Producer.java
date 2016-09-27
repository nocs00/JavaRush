package com.javarush.test.level30.lesson04.home01;

import java.util.concurrent.TransferQueue;

/**
 * Created by pdudenkov on 13.07.2016.
 */
public class Producer implements Runnable
{
    private TransferQueue<ShareItem> queue;

    public Producer(TransferQueue<ShareItem> queue)
    {
        this.queue = queue;
    }

    @Override
    public void run()
    {
            for (int i = 0; i < 9; i++)
            {
                String itemDesc = "ShareItem-"+(i+1);
                System.out.format("Элемент '%s' добавлен\n", itemDesc);
                queue.offer(new ShareItem(itemDesc, (i+1)));
                try
                {
                    Thread.sleep(100);
                }
                catch (InterruptedException e)
                {
                    return;
                }
                if (queue.hasWaitingConsumer()) {
                    System.out.println("Consumer в ожидании!");
                }
            }
    }
}

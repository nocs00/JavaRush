package com.javarush.test.level25.lesson05.home01;

/**
 * Created by pdudenkov on 25.02.2016.
 */
public class LoggingStateThread extends Thread
{
    private Thread thread;

    LoggingStateThread(Thread targetThread)
    {
        this.thread = targetThread;
        setDaemon(true);
    }

    @Override
    public void run()
    {
        State state = thread.getState();
        System.out.println(state);
        while (state != State.TERMINATED)
        {
            if (state != thread.getState())
            {
                state = thread.getState();
                System.out.println(state);
            }
        }
    }
}

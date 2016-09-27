package com.javarush.test.level28.lesson06.home01;

/**
 * Created by pdudenkov on 20.03.2016.
 */
public class MyThread extends Thread
{
    private static int priority = 1;

    public MyThread()
    {
        this.setPriority();
    }

    public MyThread(Runnable target)
    {
        super(target);
        this.setPriority();
    }

    public MyThread(ThreadGroup group, Runnable target)
    {
        super(group, target);
        this.setPriority();
    }

    public MyThread(String name)
    {
        super(name);
        this.setPriority();
    }

    public MyThread(ThreadGroup group, String name)
    {
        super(group, name);
        this.setPriority();
    }

    public MyThread(Runnable target, String name)
    {
        super(target, name);
        this.setPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name)
    {
        super(group, target, name);
        this.setPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize)
    {
        super(group, target, name, stackSize);
        this.setPriority();
    }

    private void setPriority() {
        if (MyThread.priority > 10)
            MyThread.priority = 1;
        this.setPriority(MyThread.priority++);
    }
}

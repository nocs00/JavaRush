package com.javarush.test.level14.lesson08.bonus01;

import javax.management.BadStringOperationException;
import java.rmi.activation.ActivationException;
import java.lang.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/* Нашествие эксепшенов
Заполни массив exceptions 10 различными эксепшенами.
Первое исключение уже реализовано в методе initExceptions.
*/

public class Solution
{
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args)
    {
        initExceptions();

        for (Exception exception : exceptions)
        {
            System.out.println(exception);
        }
    }

    private static void initExceptions()
    {   //it's first exception
        try
        {
            float i = 1 / 0;

        } catch (Exception e)
        {
            exceptions.add(e);
        }

        //Add your code here
        try
        {
            ArrayList<String> list = new ArrayList<String>();
            list.get(5);
        } catch (Exception e)
        {
            exceptions.add(e);
        }

        try
        {
            throw new ActivationException();
        } catch (Exception e)
        {
            exceptions.add(e);
        }

        try
        {
            throw new ArithmeticException();
        } catch (Exception e)
        {
            exceptions.add(e);
        }

        try
        {
            throw new ArrayIndexOutOfBoundsException();
        } catch (Exception e)
        {
            exceptions.add(e);
        }

        try
        {
            throw new ArrayStoreException();
        } catch (Exception e)
        {
            exceptions.add(e);
        }

        try
        {
            throw new ClassCastException();
        } catch (Exception e)
        {
            exceptions.add(e);
        }

        try
        {
            throw new ClassNotFoundException();
        } catch (Exception e)
        {
            exceptions.add(e);
        }

        try
        {
            throw new CloneNotSupportedException();
        } catch (Exception e)
        {
            exceptions.add(e);
        }

        try
        {
            throw new IllegalAccessException();
        } catch (Exception e)
        {
            exceptions.add(e);
        }

    }
}

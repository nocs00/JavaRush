package com.javarush.test.level16.lesson13.bonus02;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by pdudenkov on 23.11.15.
 */
public class Thread5 extends Thread
{
    @Override
    public void run()
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Integer summ = 0;
        try {
            while (true) {
                String s = reader.readLine();
                try {
                    Integer i = Integer.parseInt(s);
                    summ += i;
                } catch (Exception e2) {
                    if ("N".equals(s)) break;
                }
            }
            System.out.println(summ);
        } catch (Exception e) {
        }
    }
}

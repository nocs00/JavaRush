package com.javarush.test.level18.lesson03.task03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Самые частые байты
Ввести с консоли имя файла
Найти байт или байты с максимальным количеством повторов
Вывести их на экран через пробел
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        FileInputStream bis = new FileInputStream(filename);

        int[] byteCount = new int[256];

        while (bis.available() > 0) {
            byte b = (byte)bis.read();
            ++byteCount[b+128];
        }

        reader.close();
        bis.close();

        int maxCount = Integer.MIN_VALUE;
        for (int i : byteCount) {
            if (maxCount < i) maxCount = i;
        }
        String space = " ";
        for (int i = 0; i < byteCount.length; i++) {
            if (byteCount[i] == maxCount)
                System.out.print((byte)(i-128)+space);
        }
    }
}

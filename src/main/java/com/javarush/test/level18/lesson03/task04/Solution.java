package com.javarush.test.level18.lesson03.task04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* Самые редкие байты
Ввести с консоли имя файла
Найти байт или байты с минимальным количеством повторов
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

        int minCount = Integer.MAX_VALUE;
        for (int i : byteCount) {
            if (minCount > i) minCount = i;
        }
        String space = " ";
        for (int i = 0; i < byteCount.length; i++) {
            if (byteCount[i] == minCount)
                System.out.print((byte)(i-128)+space);
        }
    }
}

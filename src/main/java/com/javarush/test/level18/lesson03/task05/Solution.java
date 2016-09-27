package com.javarush.test.level18.lesson03.task05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;




/* Сортировка байт
Ввести с консоли имя файла
Считать все байты из файла.
Не учитывая повторений - отсортировать их по байт-коду в возрастающем порядке.
Вывести на экран
Закрыть поток ввода-вывода

Пример байт входного файла
44 83 44

Пример вывода
44 83
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

        String space = " ";
        for (int i = 0; i < byteCount.length; i++) {
            if (byteCount[i] > 0)
                System.out.print((byte)(i-128)+space);
        }
    }
}

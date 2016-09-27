package com.javarush.test.level18.lesson05.task02;

/* Подсчет запятых
С консоли считать имя файла
Посчитать в файле количество символов ',', количество вывести на консоль
Закрыть потоки. Не использовать try-with-resources

Подсказка: нужно сравнивать с ascii-кодом символа ','
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        FileInputStream bis = new FileInputStream(filename);

        int commaCount = 0;
        while (bis.available() > 0) {
            byte b = (byte)bis.read();
            byte comma = (byte)',';
            if (b == comma)
                commaCount++;
        }
        reader.close();
        bis.close();
        System.out.println(commaCount);
    }
}

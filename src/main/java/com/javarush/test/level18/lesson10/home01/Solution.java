package com.javarush.test.level18.lesson10.home01;

/* Английские буквы
В метод main первым параметром приходит имя файла.
Посчитать количество букв английского алфавита, которое есть в этом файле.
Вывести на экран число (количество букв)
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        FileInputStream fis = new FileInputStream(args[0]);
        BufferedReader buffer = new BufferedReader(new InputStreamReader(fis));
        int count = 0;
        while(true) {
            String str = buffer.readLine();
            if (str == null) break;
            if (str.isEmpty()) break;

            for(char c: str.toCharArray()) {
                if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                    count++;
                }
            }
        }
        buffer.close();
        fis.close();
        System.out.println(count);
    }
}

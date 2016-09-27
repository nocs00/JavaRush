package com.javarush.test.level18.lesson10.home02;

/* Пробелы
В метод main первым параметром приходит имя файла.
Вывести на экран соотношение количества пробелов к количеству всех символов. Например, 10.45
1. Посчитать количество всех символов.
2. Посчитать количество пробелов.
3. Вывести на экран п2/п1*100, округлив до 2 знаков после запятой
4. Закрыть потоки. Не использовать try-with-resources
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
        int count = fis.available();
        int countSpaces = 0;
        while(true) {
            String str = buffer.readLine();
            if (str == null) break;
            if (str.isEmpty()) break;

            for(char c: str.toCharArray()) {
                //count++;
                if (c == ' ') {
                    countSpaces++;
                }
            }
        }
        buffer.close();
        fis.close();
        System.out.println(String.format("%.2f", countSpaces/(float)count *100));
    }
}

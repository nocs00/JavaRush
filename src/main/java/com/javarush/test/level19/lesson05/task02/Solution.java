package com.javarush.test.level19.lesson05.task02;

/* Считаем слово
Считать с консоли имя файла.
Файл содержит слова, разделенные знаками препинания.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader buffer =  new BufferedReader(new InputStreamReader(System.in));
        String fname = buffer.readLine();

        final String pattern = "world";
        FileReader file = new FileReader(fname);
        long count = 0;
        StringBuilder builder = new StringBuilder();
        while (file.ready()) {
            char c = (char)file.read();
            if ((c >= 'a' &&  c <= 'z') || (c >= 'A' &&  c <= 'Z')) {
                builder.append(c);
            } else {
                if (builder.toString().equals(pattern)) {
                    count++;
                }
                builder.setLength(0);
            }
        }
        System.out.println(count);
        buffer.close();
        file.close();
    }
}

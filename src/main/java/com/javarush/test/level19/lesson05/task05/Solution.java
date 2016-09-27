package com.javarush.test.level19.lesson05.task05;

/* Пунктуация
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Удалить все знаки пунктуации, включая символы новой строки. Результат вывести во второй файл.
http://ru.wikipedia.org/wiki/%D0%9F%D1%83%D0%BD%D0%BA%D1%82%D1%83%D0%B0%D1%86%D0%B8%D1%8F
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String[] fname = new String[2];
        fname[0] = buffer.readLine();
        fname[1] = buffer.readLine();
        File[] files = new File[2];
        files[0] = new File(fname[0]);
        files[1] = new File(fname[1]);
        buffer.close();

        FileReader fr = new FileReader(files[0]);
        FileWriter fw = new FileWriter(files[1]);
        buffer = new BufferedReader(fr);

        String currentLine = null;
        StringBuilder builder = new StringBuilder();
        while ((currentLine = buffer.readLine()) != null) {
            builder.append(currentLine.replaceAll("\\p{P}", ""));
        }
        fw.write(builder.toString());
        fw.flush();
        fw.close();
        fr.close();
        buffer.close();
    }
}

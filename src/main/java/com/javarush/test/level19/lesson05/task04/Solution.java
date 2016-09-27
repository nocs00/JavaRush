package com.javarush.test.level19.lesson05.task04;

/* Замена знаков
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Заменить все точки "." на знак "!", вывести во второй файл.
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
        char[] buf = new char[100];
        while (fr.ready()) {
            int count = fr.read(buf);
            for (int i = 0; i < count; i++) {
                if (buf[i] == '.') {
                    buf[i] = '!';
                }
            }
            fw.write(buf, 0 , count);
        }
        fr.close();
        fw.close();
    }
}

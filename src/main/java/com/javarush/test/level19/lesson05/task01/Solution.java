package com.javarush.test.level19.lesson05.task01;

/* Четные байты
Считать с консоли 2 имени файла.
Вывести во второй файл все байты с четным индексом.
Пример: второй байт, четвертый байт, шестой байт и т.д.
Закрыть потоки ввода-вывода.
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String[] fname = new String[2];
        fname[0] = buffer.readLine();
        fname[1] = buffer.readLine();
        buffer.close();

        FileInputStream file1 = new FileInputStream(fname[0]);
        FileOutputStream file2 = new FileOutputStream(fname[1]);
        byte[] buf = new byte[100];
        while(file1.available() > 0) {
            int count = file1.read(buf);
            for (int i = 0; i < count; i++) {
                if (i % 2 == 1) {
                    file2.write(buf[i]);
                }
            }
        }
        file1.close();
        file2.close();
    }
}

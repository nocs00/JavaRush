package com.javarush.test.level18.lesson05.task03;

/* Разделение файла
Считать с консоли три имени файла: файл1, файл2, файл3.
Разделить файл1 по следующему критерию:
Первую половину байт записать в файл2, вторую половину байт записать в файл3.
Если в файл1 количество байт нечетное, то файл2 должен содержать бОльшую часть.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename1 = reader.readLine();
        String filename2 = reader.readLine();
        String filename3 = reader.readLine();
        FileInputStream file1 = new FileInputStream(filename1);
        FileOutputStream file2 = new FileOutputStream(filename2);
        FileOutputStream file3 = new FileOutputStream(filename3);

        byte[] buffer2 = new byte[file1.available()/2];
        byte[] buffer1 = new byte[file1.available()-buffer2.length];

        file1.read(buffer1);
        file1.read(buffer2);

        file2.write(buffer1);
        file3.write(buffer2);

        reader.close();
        file1.close();
        file2.close();
        file3.close();
    }
}

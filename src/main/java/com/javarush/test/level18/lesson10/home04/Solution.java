package com.javarush.test.level18.lesson10.home04;

/* Объединение файлов
Считать с консоли 2 имени файла
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String fname1 , fname2 ;
        fname1 = buffer.readLine();
        fname2 = buffer.readLine();
        buffer.close();

        File mFile = new File(fname1);
        FileInputStream reader = new FileInputStream(mFile);
        byte[] bufReader = new byte[reader.available()];
        reader.read(bufReader);
        mFile.delete();
        reader.close();

        FileOutputStream fos = new FileOutputStream(mFile);


        FileInputStream fis = new FileInputStream(fname2);
        byte[] buf = new byte[100];
        while(fis.available() > 0) {
            int count = fis.read(buf);
            fos.write(buf, 0, count);
        }
        fos.write(bufReader);
        fos.flush();
        fos.close();
        fis.close();
    }
}

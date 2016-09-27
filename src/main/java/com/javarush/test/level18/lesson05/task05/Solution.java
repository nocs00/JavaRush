package com.javarush.test.level18.lesson05.task05;

/* DownloadException
1 Считывать с консоли имена файлов.
2 Если файл меньше 1000 байт, то:
2.1 Закрыть потоки
2.2 выбросить исключение DownloadException
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws DownloadException {
        ArrayList<String> filenames;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            try {
                String filename = reader.readLine();
                FileInputStream file = new FileInputStream(filename);
                if (file.available() < 1000) {
                    file.close();
                    reader.close();
                    throw new DownloadException();
                }
            } catch (IOException e) {

            }

        }
    }

    public static class DownloadException extends Exception{

    }
}

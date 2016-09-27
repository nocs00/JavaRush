package com.javarush.test.level18.lesson10.home08;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* Нити и байты
Читайте с консоли имена файлов, пока не будет введено слово "exit"
Передайте имя файла в нить ReadThread
Нить ReadThread должна найти байт, который встречается в файле максимальное число раз, и добавить его в словарь resultMap,
где параметр String - это имя файла, параметр Integer - это искомый байт.
Закрыть потоки. Не использовать try-with-resources
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            String fname = reader.readLine();
            if (fname == null) break;
            if (fname == "exit") break;
            new ReadThread(fname).start();
        }
        reader.close();
    }

    public static class ReadThread extends Thread {
        private String fname;
        public ReadThread(String fileName) {
            //implement constructor body
            fname = fileName;
        }
        // implement file reading here - реализуйте чтение из файла тут
        private void fileReading() throws IOException {
            FileInputStream fis = new FileInputStream(fname);
            long[] bytes = new long[256];
            while (fis.available() > 0) {
                bytes[fis.read()]++;
            }
            fis.close();
            long max = 0;
            int pos = 0;
            for (int i = 0; i < bytes.length; i++) {
                if (bytes[i] > max)  {
                    max = bytes[i];
                    pos = i;
                }
            }

            resultMap.put(fname, pos);
        }

        @Override
        public void run()
        {
            try
            {
                fileReading();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

package com.javarush.test.level18.lesson10.home10;

/* Собираем файл
Собираем файл из кусочков
Считывать с консоли имена файлов
Каждый файл имеет имя: [someName].partN. Например, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.
Имена файлов подаются в произвольном порядке. Ввод заканчивается словом "end"
В папке, где находятся все прочтенные файлы, создать файл без приставки [.partN]. Например, Lion.avi
В него переписать все байты из файлов-частей используя буфер.
Файлы переписывать в строгой последовательности, сначала первую часть, потом вторую, ..., в конце - последнюю.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, String> files = new TreeMap<Integer, String>();

        String fname=null;
        String result=null;
        while(!(fname=buffer.readLine()).equals("end")) {
            if (result==null || result.equals("end")) result = fname.split(".part")[0];
            files.put(Integer.parseInt(fname.split("part")[1]), fname);
        }
        buffer.close();

        FileOutputStream resultFile = new FileOutputStream(result);
        for (Map.Entry<Integer, String> entry : files.entrySet()) {
            FileInputStream partFile = new FileInputStream(entry.getValue());
            byte[] buf = new byte[100];
            while (partFile.available() > 0) {
                int count = partFile.read(buf);
                resultFile.write(buf, 0, count);
            }
            partFile.close();
        }
        resultFile.close();
    }
}

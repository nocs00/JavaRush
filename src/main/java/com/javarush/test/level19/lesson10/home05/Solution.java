package com.javarush.test.level19.lesson10.home05;

/* Слова с цифрами
В метод main первым параметром приходит имя файла1, вторым - файла2.
Файл1 содержит строки со слов, разделенные пробелом.
Записать через пробел в Файл2 все слова, которые содержат цифры, например, а1 или abc3d
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Solution {
    public static void main(String[] args) throws Exception {
        String[] fname = new String[2];
        fname[0] = args[0];
        fname[1] = args[1];

        BufferedReader buffer = new BufferedReader(new FileReader(fname[0]));
        FileWriter fw = new FileWriter(fname[1]);
        String line = null;
        boolean begin = true;
        while((line=buffer.readLine())!=null) {
            for (String word : line.split(" ")) {
                if (containDigit(word)) {
                    if (!begin) fw.write(" ");
                    else begin = !begin;
                    fw.write(word);
                }
            }
        }
        buffer.close();
        fw.close();
    }

    public static boolean containDigit(String s) {
        for (Character c : s.toCharArray()) {
            if (c >= '0' && c <= '9') return true;
        }
        return false;
    }
}

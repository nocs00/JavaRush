package com.javarush.test.level19.lesson10.home07;

/* Длинные слова
В метод main первым параметром приходит имя файла1, вторым - файла2
Файл1 содержит слова, разделенные пробелом.
Записать через запятую в Файл2 слова, длина которых строго больше 6
Закрыть потоки. Не использовать try-with-resources

Пример выходных данных:
длинное,короткое,аббревиатура
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Solution {
    public static void main(String[] args) throws Exception {
        String fname1 = args[0];
        String fname2 = args[1];

        BufferedReader reader = new BufferedReader(new FileReader(fname1));
        FileWriter fw = new FileWriter(fname2);
        String line = null;
        boolean start = true;
        while ((line = reader.readLine())!=null) {

            for (String word : line.split(" ")) {
                if (word.length() > 6) {
                    if (start) start = !start;
                    else fw.write(",");
                    fw.write(word);
                }
            }
        }
        reader.close();
        fw.close();
    }
}

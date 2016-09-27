package com.javarush.test.level19.lesson10.home04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Ищем нужные строки
Считать с консоли имя файла.
Вывести в консоль все строки из файла, которые содержат всего 2 слова из списка words
Закрыть потоки. Не использовать try-with-resources

Пример: words содержит слова А, Б, В
Строки:
В Б А Д  //3 слова из words, не подходит
Д А Д    //1 слово из words, не подходит
Д А Б Д  //2 слова - подходит, выводим
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final String fname = reader.readLine();

        BufferedReader fileReader = new BufferedReader(new FileReader(fname));
        String line = null;
        while ((line = fileReader.readLine()) != null) {
            int count = 0;
            for (String s : line.split(" ")) {
                if (words.contains(s))
                    count++;
            }
            if (count == 2) {
                System.out.println(line);
            }
        }
        reader.close();
        fileReader.close();
    }
}

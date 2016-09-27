package com.javarush.test.level22.lesson09.task01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* Обращенные слова
В методе main с консоли считать имя файла, который содержит слова, разделенные пробелами.
Найти в тексте все пары слов, которые являются обращением друг друга. Добавить их в result.
Порядок слов first/second не влияет на тестирование.
Использовать StringBuilder.
Пример содержимого файла
рот тор торт о
о тот тот тот
Вывод:
рот тор
о о
тот тот
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final String fname = reader.readLine();
        reader.close();
        BufferedReader fileReader = new BufferedReader(new FileReader(fname));

        ArrayList<String> allWords = new ArrayList<String>();
        String line = null;
        StringBuilder sb = null;
        while ((line = fileReader.readLine())!= null) {
            String[] words = line.split(" ");
            Collections.addAll(allWords, words);
        }
        fileReader.close();

        for (int i = 0; i < allWords.size()-1; i++)
        {
            sb = new StringBuilder(allWords.get(i));
            String find = sb.reverse().toString();
            for (int i1 = i+1; i1 < allWords.size(); i1++)
            {
                if (find.equals(allWords.get(i1))) {
                    result.add(new Pair(allWords.get(i), allWords.get(i1)));
                    allWords.remove(i1);
                    allWords.remove(i);
                    i--;
                    i1--;
                }
            }
        }

    }

    public static class Pair {
        String first;
        String second;

        public Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}

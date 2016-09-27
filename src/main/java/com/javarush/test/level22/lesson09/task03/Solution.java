package com.javarush.test.level22.lesson09.task03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/* Составить цепочку слов
В методе main считайте с консоли имя файла, который содержит слова, разделенные пробелом.
В методе getLine используя StringBuilder расставить все слова в таком порядке,
чтобы последняя буква данного слова совпадала с первой буквой следующего не учитывая регистр.
Каждое слово должно участвовать 1 раз.
Метод getLine должен возвращать любой вариант.
Слова разделять пробелом.
В файле не обязательно будет много слов.

Пример тела входного файла:
Киев Нью-Йорк Амстердам Вена Мельбурн

Результат:
Амстердам Мельбурн Нью-Йорк Киев Вена
*/
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final String fname = reader.readLine();
        reader.close();
        BufferedReader fileReader = new BufferedReader(new FileReader(fname));

        ArrayList<String> allWords = new ArrayList<String>();
        String line = null;
        while ((line = fileReader.readLine())!= null) {
            String[] words = line.split(" ");
            Collections.addAll(allWords, words);
        }
        fileReader.close();

        StringBuilder result = getLine(allWords.toArray(new String[allWords.size()]));
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        ArrayList<String> allWords = new ArrayList<String>();
        Collections.addAll(allWords, words);

        while (!correctLine(allWords))
            Collections.shuffle(allWords);

        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (String word : allWords)
        {
            if (!first)
                result.append(" ");
            first = false;
            result.append(word);
        }

        return result;
    }

    public static boolean correctLine(ArrayList<String> allWords) {
        for (int i = 0; i < allWords.size()-1; i++)
        {
            if (Character.toLowerCase(allWords.get(i).charAt(allWords.get(i).length()-1))!= Character.toLowerCase(allWords.get(i+1).charAt(0)))
                return false;
        }
        return true;
    }
}

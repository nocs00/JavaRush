package com.javarush.test.level13.lesson11.bonus01;

/* Сортировка четных чисел из файла
1. Ввести имя файла с консоли.
2. Прочитать из него набор чисел.
3. Вывести на консоль только четные, отсортированные по возрастанию.
Пример ввода:
5
8
11
3
2
10
Пример вывода:
2
8
10
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Solution
{
    public static boolean isDigit(int arg) {
        if (arg >= (int)'0' && arg <= (int)'9') return true;
        else return false;
    }

    public static void main(String[] args) throws IOException
    {
        // напишите тут ваш код
        String filepath = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        filepath = reader.readLine();
        if (filepath.isEmpty()) return;

        InputStream file = new FileInputStream(filepath);


        ArrayList<Integer> numbers = new ArrayList<Integer>();
        int n = 0;
        StringBuffer currentNumber = new StringBuffer();
        boolean insertable = false;

        while(file.available() > 0) {
            insertable = false;
            n = file.read();
            if (isDigit(n)) {
                currentNumber.append((char)n);
                if (file.available() > 0) continue;
            } else if (currentNumber.length() > 0) {
                n = Integer.parseInt(currentNumber.toString());
                currentNumber.delete(0, currentNumber.length()); //clear buffer
                insertable = true;
            }

            if (file.available() <= 0 && currentNumber.length() > 0) {
                n = Integer.parseInt(currentNumber.toString());
                currentNumber.delete(0, currentNumber.length()); //clear buffer
                insertable = true;
            }

            if (n % 2 == 0 && insertable) numbers.add(n);
        }

        Collections.sort(numbers);

        for (Integer i : numbers) {
            System.out.println(i);
        }
    }
}
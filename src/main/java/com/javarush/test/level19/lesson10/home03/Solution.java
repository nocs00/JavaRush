package com.javarush.test.level19.lesson10.home03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* Хуан Хуанович
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя день месяц год
где [имя] - может состоять из нескольких слов, разделенных пробелами, и имеет тип String
[день] - int, [месяц] - int, [год] - int
данные разделены пробелами

Заполнить список PEOPLE импользуя данные из файла
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Иванов Иван Иванович 31 12 1987
Вася 15 5 2013
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws Exception {
        final String fname = args[0];


        BufferedReader buffer = new BufferedReader(new FileReader(fname));
        String currentLine = null;
        while ((currentLine=buffer.readLine()) != null) {
            String name = "";
            int day, month, year;
            int i = 0;
            while (!isNum(currentLine.split(" ")[i])) {
                name += currentLine.split(" ")[i] + " ";
                i++;
            }
            name = name.substring(0, name.length()-1);
            day = Integer.parseInt(currentLine.split(" ")[i]);
            i++;
            month = Integer.parseInt(currentLine.split(" ")[i]);
            i++;
            year = Integer.parseInt(currentLine.split(" ")[i]);
            SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
            Date d = format.parse((day < 10 ? "0" : "") + currentLine.split(" ")[i-2] + (month < 10 ? "0" : "") + currentLine.split(" ")[i-1] + currentLine.split(" ")[i]);
            Person person = new Person(name, d);
            PEOPLE.add(person);
        }
        buffer.close();
    }

    public static boolean isNum(String what) {
        try {
            Integer.parseInt(what);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}

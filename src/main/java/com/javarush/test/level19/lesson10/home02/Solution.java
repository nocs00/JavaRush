package com.javarush.test.level19.lesson10.home02;

/* Самый богатый
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Вывести в консоль имена, у которых максимальная сумма
Имена разделять пробелом либо выводить с новой строки
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 0.501
Иванов 1.35
Петров 0.85

Пример вывода:
Петров
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws Exception {
        String fname = args[0];


        TreeMap<String, Double> deals = new TreeMap<String, Double>();
        BufferedReader buffer = new BufferedReader(new FileReader(fname));
        String currentLine = null;
        while ((currentLine = buffer.readLine()) != null) {
            String name = currentLine.split(" ")[0];
            double value = Double.parseDouble(currentLine.split(" ")[1]);
            Double currentValue = deals.get(name);

            if (currentValue == null) {
                deals.put(name, value);
            } else {
                deals.put(name, value+currentValue);
            }
        }
        buffer.close();

        Double max = 0d;
        for (Map.Entry entry : deals.entrySet()) {
            if (max < (Double)entry.getValue()) {
                max = (Double)entry.getValue();
            }
        }
        for (Map.Entry entry : deals.entrySet()) {
            if (max == (Double)entry.getValue()) {
                System.out.println(entry.getKey());
            }
        }

    }
}

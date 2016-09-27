package com.javarush.test.level19.lesson10.home01;

/* Считаем зарплаты
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Все данные вывести в консоль, предварительно отсортировав в возрастающем порядке по имени
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 2
Сидоров 6
Иванов 1.35
Петров 3.1

Пример вывода:
Иванов 1.35
Петров 5.1
Сидоров 6.0
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws Exception {
        //String fname = args[0];
        String fname = "prices.txt";

        TreeMap<String, Double> deals = new TreeMap<String, Double>();
        BufferedReader buffer = new BufferedReader(new FileReader(fname));
        String currentLine = null;
        while ((currentLine = buffer.readLine()) != null) {
            String name = currentLine.split(" ")[0];
            double value = Double.parseDouble(currentLine.split(" ")[1]);
            Double currentValue = deals.get(name);

//            for (Map.Entry entry : deals.entrySet()) {
//                String tmp = (String)entry.getKey();
//                int cmp = tmp.compareTo(name);
//                if (cmp == 0) {
//                    Double newValue = (Double)entry.getValue() + value;
//                }
//            }

            //'\uFEFF' BOM Symbol

            if (currentValue == null) {
                deals.put(name, value);
            } else {
                deals.put(name, value+currentValue);
            }
        }
        buffer.close();

        for (Map.Entry entry : deals.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}

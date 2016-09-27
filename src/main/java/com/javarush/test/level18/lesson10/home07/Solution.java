package com.javarush.test.level18.lesson10.home07;

/* Поиск данных внутри файла
Считать с консоли имя файла
Найти в файле информацию, которая относится к заданному id, и вывести ее на экран в виде, в котором она записана в файле.
Программа запускается с одним параметром: id (int)
Закрыть потоки. Не использовать try-with-resources

В файле данные разделены пробелом и хранятся в следующей последовательности:
id productName price quantity

где id - int
productName - название товара, может содержать пробелы, String
price - цена, double
quantity - количество, int

Информация по каждому товару хранится в отдельной строке
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        InputStream stream = System.in;
        BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));
        String fname = buffer.readLine();
        stream.close();
        buffer.close();

        int id = Integer.parseInt(args[0]);
        BufferedReader bufferFile = new BufferedReader(new FileReader(fname));
        while(true) {
            String currentLine = bufferFile.readLine();
            if (currentLine == null) break;
            if (Integer.parseInt(currentLine.split(" ")[0]) != id) continue;
            System.out.println(currentLine);
        }
        bufferFile.close();

    }
}

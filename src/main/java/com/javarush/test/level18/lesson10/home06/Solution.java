package com.javarush.test.level18.lesson10.home06;

/* Встречаемость символов
Программа запускается с одним параметром - именем файла, который содержит английский текст.
Посчитать частоту встречания каждого символа.
Отсортировать результат по возрастанию кода ASCII (почитать в инете). Пример: ','=44, 's'=115, 't'=116
Вывести на консоль отсортированный результат:
[символ1]  частота1
[символ2]  частота2
Закрыть потоки. Не использовать try-with-resources

Пример вывода:
, 19
- 7
f 361
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        String fname = args[0];
        FileInputStream fis = new FileInputStream(fname);

        long[] chars = new long[256];
        while (fis.available() > 0) {
            chars[fis.read()]++;
        }
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 0)// || i == 10)
                continue;
            else {
                System.out.println(""+(char)i+" "+chars[i]);
            }
        }
        fis.close();
    }
}

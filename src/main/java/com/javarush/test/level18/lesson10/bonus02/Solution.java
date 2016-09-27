package com.javarush.test.level18.lesson10.bonus02;

/* Прайсы
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается со следующим набором параметров:
-c productName price quantity
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-c  - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id, найденный в файле

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        String operation = args[0];
        String productName = args[1];
        String price = args[2];
        String quantity = args[3];

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        //String fname = buffer.readLine();
        String fname = "prices.txt";
        final File file = new File(fname);
        file.createNewFile();
        buffer.close();

        buffer = new BufferedReader(new FileReader(file));
        int maxid=0;
        String currentRow=null;
        while ((currentRow = buffer.readLine()) != null) {
            int cur = Integer.parseInt(currentRow.substring(0, 8).split(" ")[0]);
            if (cur > maxid) maxid = cur;
        }
        maxid++;
        buffer.close();

        currentRow = "";
        if (operation.equals("-c"))  {
            currentRow += padding(String.valueOf(maxid), 8);
            currentRow += padding(productName, 30);
            currentRow += padding(price, 8);
            currentRow += padding(quantity, 4);
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        writer.write(currentRow);
        writer.write("\r\n");
        writer.flush();
        writer.close();
    }

    public static String padding(String input, int len) {
        int num = len - input.length();
        StringBuilder output = new StringBuilder(input);
        if (num < 0) return output.toString().substring(0, len);
        for (int i = 0; i < num; i++) {
            output.append(" ");
        }
        return output.toString();
    }
}

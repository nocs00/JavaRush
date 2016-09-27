package com.javarush.test.level18.lesson10.bonus03;

/* Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-u  - обновляет данные товара с заданным id
-d  - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

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
        String idstr = args[1];
        String productName = null;
        String price = null;
        String quantity = null;
        if (operation.equals("-u")) {
            productName = args[2];
            price = args[3];
            quantity = args[4];
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String fname = buffer.readLine();
        final File file = new File(fname);
        file.createNewFile();
        buffer.close();

        StringBuilder inFile = new StringBuilder();
        buffer = new BufferedReader(new FileReader(file));
        if (operation.equals("-u")) {
            String currentRow = null;
            while ((currentRow=buffer.readLine()) != null) {
                int id = Integer.parseInt(idstr);
                int idcurrent = Integer.parseInt(currentRow.substring(0,8).split(" ")[0]);
                if (id == idcurrent) {
                    currentRow = "";
                    currentRow += padding(idstr, 8);
                    currentRow += padding(productName, 30);
                    currentRow += padding(price, 8);
                    currentRow += padding(quantity, 4);
                }
                if (inFile.length() != 0) inFile.append("\r\n");
                inFile.append(currentRow);
            }
        } else if (operation.equals("-d")) {
            String currentRow = null;
            while ((currentRow=buffer.readLine()) != null) {
                int id = Integer.parseInt(idstr);
                int idcurrent = Integer.parseInt(currentRow.substring(0,8).split(" ")[0]);
                if (id == idcurrent) continue;
                if (inFile.length() != 0) inFile.append("\r\n");
                inFile.append(currentRow);
            }
        }
        buffer.close();

        RandomAccessFile clearFile = new RandomAccessFile(file, "rw");
        clearFile.setLength(0);
        clearFile.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        writer.write(inFile.toString());
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

package com.javarush.test.level18.lesson10.home05;

/* Округление чисел
Считать с консоли 2 имени файла
Первый файл содержит вещественные(дробные) числа, разделенные пробелом. Например, 3.1415
Округлить числа до целых и записать через пробел во второй файл
Закрыть потоки. Не использовать try-with-resources
Принцип округления:
3.49 - 3
3.50 - 4
3.51 - 4
-3.49 - -3
-3.50 - -3
-3.51 - -4
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String fname1 , fname2 ;
        fname1 = buffer.readLine();
        fname2 = buffer.readLine();
        buffer.close();

        FileInputStream inputFloatFile = new FileInputStream(fname1);
        FileOutputStream outputIntFile = new FileOutputStream(fname2);

        String floatString = "";
        ArrayList<Integer> values = new ArrayList<>();
        while (inputFloatFile.available() > 0) {
            char c = (char)inputFloatFile.read();
            if (c != ' ') {
                floatString += c;
            } else {
                Integer i = (int) Float.parseFloat(floatString);
                if (((i>0)&&(Float.parseFloat(floatString)-i >= 0.5d)) || ((i<0)&&(Float.parseFloat(floatString)-i < -0.5d))) {
                    if (i > 0) i++;
                    else i--;
                }
                values.add(i);
                floatString = "";
            }
        }
        Integer i = (int) Float.parseFloat(floatString);
        if (((i>0)&&(Float.parseFloat(floatString)-i >= 0.5d)) || ((i<0)&&(Float.parseFloat(floatString)-i < -0.5d))) {
            if (i > 0) i++;
            else i--;
        }
        values.add(i);

        for (int j = 0; j < values.size(); j++) {
            outputIntFile.write(values.get(j).toString().getBytes());
            if (j != values.size()-1) {
                outputIntFile.write(32);
            }
        }
        inputFloatFile.close();
        outputIntFile.close();
    }
}

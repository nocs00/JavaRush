package com.javarush.test.level18.lesson10.bonus01;

/* Шифровка
Придумать механизм шифровки/дешифровки

Программа запускается с одним из следующих наборов параметров:
-e fileName fileOutputName
-d fileName fileOutputName
где
fileName - имя файла, который необходимо зашифровать/расшифровать
fileOutputName - имя файла, куда необходимо записать результат шифрования/дешифрования
-e - ключ указывает, что необходимо зашифровать данные
-d - ключ указывает, что необходимо расшифровать данные
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        String operation = args[0];
        String fname = args[1];
        String storage = args[2];

        FileInputStream reader = new FileInputStream(fname);
        FileOutputStream writer = new FileOutputStream(storage);
        byte[] buf = new byte[100];

        while (reader.available() > 0) {
            int count = reader.read(buf);
            byte[] resBuf=null;
            if (operation.equals("-e")) {
                resBuf = encrypt(buf);
            } else if (operation.equals("-d")) {
                resBuf = decrypt(buf);
            }
            writer.write(resBuf,0,count);
        }
    }

    public static byte[] encrypt(byte[] input) {
        byte[] output = new byte[input.length];

        for (int i = 0; i < input.length; i++) {
            output[i] = (byte)(input[i] + 1);
        }

        return output;
    }

    public static byte[] decrypt(byte[] input) {
        byte[] output = new byte[input.length];

        for (int i = 0; i < input.length; i++) {
            output[i] = (byte)(input[i] - 1);
        }

        return output;
    }
}

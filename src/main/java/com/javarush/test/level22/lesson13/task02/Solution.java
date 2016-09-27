package com.javarush.test.level22.lesson13.task02;

import java.io.*;
import java.nio.charset.Charset;

/* Смена кодировки
В метод main первым параметром приходит имя файла, тело которого в кодировке Windows-1251.
В метод main вторым параметром приходит имя файла, в который необходимо записать содержимое первого файла в кодировке UTF-8.
*/
public class Solution {
    static String win1251TestString = "РќР°СЂСѓС€РµРЅРёРµ РєРѕРґРёСЂРѕРІРєРё РєРѕРЅСЃРѕР»Рё?"; //only for your testing

    public static void main(String[] args) throws IOException {
        final String fnameIn = args[0];
        final String fnameOut = args[1];

        File fileIn = new File(fnameIn);
        File fileOut = new File(fnameOut);

        FileInputStream reader  = new FileInputStream(fileIn);
        FileOutputStream writer = new FileOutputStream(fileOut);
        byte[] buffer = new byte[1000];
        while (reader.available() > 0) {
            int count = reader.read(buffer);
            String s = new String(buffer, 0, count, "UTF-8");
            byte[] buffer2 = s.getBytes(Charset.forName("Windows-1251"));
            writer.write(buffer2);
        }
        reader.close();
        writer.flush();
        writer.close();
    }
}

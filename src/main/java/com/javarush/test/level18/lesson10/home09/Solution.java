package com.javarush.test.level18.lesson10.home09;

/* Файлы и исключения
Читайте с консоли имена файлов
Если файла не существует (передано неправильное имя файла), то
перехватить исключение FileNotFoundException, вывести в консоль переданное неправильное имя файла и завершить работу программы.
Закрыть потоки. Не использовать try-with-resources
Не используйте System.exit();
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader rdr = new BufferedReader(new InputStreamReader(System.in));
        String f = null;
        FileInputStream file = null;

        try {
            while (true) {
                f = rdr.readLine();
                if (f == null) break;
                file = new FileInputStream(f);
            }
        } catch (FileNotFoundException e) {
            System.out.println(f);

        } catch (IOException e) {

        } finally {
            try {
                if (rdr != null) rdr.close();
                if (file != null) file.close();
            } catch (IOException e) {

            }
        }
    }
}

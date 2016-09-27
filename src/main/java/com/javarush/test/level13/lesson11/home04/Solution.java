package com.javarush.test.level13.lesson11.home04;

/* Запись в файл
1. Прочесть с консоли имя файла.
2. Считывать строки с консоли, пока пользователь не введет строку "exit".
3. Вывести абсолютно все введенные строки в файл, каждую строчку с новой стороки.
*/
import java.io.*;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        String filepath = "";

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        filepath = reader.readLine();

        if (filepath.isEmpty()) return;

        OutputStream file = new FileOutputStream(filepath);
        String fileline = "";
        boolean begin = true;

        while (true) {
//            if (!begin)
//                file.write("\n".getBytes());
//            else {
//                begin = false;
//                file.write(filepath.getBytes());
//            }
            fileline = reader.readLine();
            if (fileline.equals("exit"))
            {
                file.write(fileline.getBytes());
                break;
            }

            file.write(fileline.getBytes());
            file.write('\n');
        }

        reader.close();
        file.close();
    }
}

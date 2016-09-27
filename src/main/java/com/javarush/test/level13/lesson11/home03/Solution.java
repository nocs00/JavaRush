package com.javarush.test.level13.lesson11.home03;

/* Чтение файла
1. Считать с консоли имя файла.
2. Вывести в консоль(на экран) содержимое файла.
3. Не забыть освободить ресурсы. Закрыть поток чтения с файла и поток ввода с клавиатуры.
*/
import java.io.*;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        //add your code here
        String filepath = "";

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        filepath = reader.readLine();
        if (filepath.isEmpty()) return;

        InputStream inputstream = new FileInputStream(filepath);

        int data = inputstream.read();
        while(data != -1) {
            //do something with data...
            Character c = new Character((char)data);
            System.out.print(c);

            data = inputstream.read();
        }
        inputstream.close();
        reader.close();
    }
}

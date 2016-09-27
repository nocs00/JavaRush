package com.javarush.test.level17.lesson10.home09;

import java.io.*;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;

/* Транзакционность
Сделать метод joinData транзакционным, т.е. если произошел сбой, то данные не должны быть изменены.
1. Считать с консоли 2 имени файла
2. Считать построчно данные из файлов. Из первого файла - в allLines, из второго - в forRemoveLines
В методе joinData:
3. Если список allLines содержит все строки из forRemoveLines, то удалить из списка allLines все строки, которые есть в forRemoveLines
4. Если список allLines НЕ содержит каких-либо строк, которые есть в forRemoveLines, то
4.1. очистить allLines от данных
4.2. выбросить исключение CorruptedDataException
Метод joinData должен вызываться в main. Все исключения обработайте в методе main.
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) {
        String file1 = null, file2 = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try
        {
            file1 = reader.readLine();
            file2 = reader.readLine();
        } catch (IOException e) {
            try {
                reader.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        BufferedReader fileReader1 = null, fileReader2 = null;

        try
        {
            fileReader1 = new BufferedReader(new FileReader(file1));

            while(true) {
                String s = fileReader1.readLine();
                if (s == null)
                    break;
                allLines.add(s);
            }

            fileReader2 = new BufferedReader(new FileReader(file2));

            while(true) {
                String s = fileReader2.readLine();
                if (s == null)
                    break;
                forRemoveLines.add(s);
            }

            joinData();

        }
        catch (CorruptedDataException e) {}
        catch (FileNotFoundException e) {}
        catch (IOException e) {}
        finally {
            try
            {
                if (fileReader1 != null)
                    fileReader1.close();
                if (fileReader2 != null)
                    fileReader2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void joinData () throws CorruptedDataException {

        if (allLines.containsAll(forRemoveLines))
            allLines.removeAll(forRemoveLines);
        else
        {
            allLines.clear();
            throw new CorruptedDataException();
        }
    }
}

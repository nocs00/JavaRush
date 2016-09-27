package com.javarush.test.level19.lesson08.task05;

/* Дублируем текст
Считайте с консоли имя файла
В методе main подмените объект System.out написанной вами ридер-оберткой по аналогии с лекцией
Ваша ридер-обертка должна дублировать вывод всего текста в файл, имя которого вы считали
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток
Закройте поток файла

Пример вывода на экран:
it's a text for testing

Пример тела файла:
it's a text for testing
*/

import java.io.*;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws Exception {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        final String fname = buffer.readLine();
        final FileWriter fw = new FileWriter(fname);
        buffer.close();

        PrintStream consoleStream = System.out;
        OutputStream newOut = new OutputStream()
        {
            private StringBuilder s = new StringBuilder();
            @Override
            public void write(int i) throws IOException
            {
                s.append((char)i);
            }

            @Override
            public String toString()
            {
                try
                {
                    fw.write(s.toString());
                } catch (Exception e) {

                }
                return s.toString();
            }
        };
        PrintStream pstream = new PrintStream(newOut);
        System.setOut(pstream);
        testString.printSomething();
        System.setOut(consoleStream);
        System.out.println(newOut.toString());
        fw.close();
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}


package com.javarush.test.level19.lesson08.task03;

/* Выводим только цифры
В методе main подмените объект System.out написанной вами ридер-оберткой по аналогии с лекцией
Ваша ридер-обертка должна выводить только цифры
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток
Вывести модифицированную строку в консоль.

Пример вывода:
12345678
*/

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
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
                return s.toString().replaceAll("\\D+", "");
            }
        };
        PrintStream pstream = new PrintStream(newOut);
        System.setOut(pstream);
        testString.printSomething();
        System.setOut(consoleStream);
        System.out.println(newOut.toString());
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's 1 a 23 text 4 f5-6or7 tes8ting");
        }
    }
}

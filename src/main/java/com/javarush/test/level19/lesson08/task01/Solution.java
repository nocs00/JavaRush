package com.javarush.test.level19.lesson08.task01;

/* Ридер обертка
В методе main подмените объект System.out написанной вами ридер-оберткой по аналогии с лекцией
Ваша ридер-обертка должна преобразовывать весь текст в заглавные буквы
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток.
Вывести модифицированную строку в консоль.
*/

import java.io.*;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws IOException {
        PrintStream console = System.out;
        Test stream = new Test();
        PrintStream pstream =  new PrintStream(stream);
        System.setOut(pstream);
        testString.printSomething();
        System.setOut(console);
        System.out.println(stream.toString());
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }

    public static class Test extends OutputStream {
        private StringBuilder s;

        public Test() {
            s = new StringBuilder();
        }
        @Override
        public void write(int i) throws IOException
        {
            s.append(Character.toUpperCase((char)i));
        }

        @Override
        public String toString()
        {
            return s.toString();
        }
    }
}

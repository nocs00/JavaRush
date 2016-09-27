package com.javarush.test.level19.lesson10.home09;

/* Контекстная реклама
В методе main подмените объект System.out написанной вами реадер-оберткой
Ваша реадер-обертка должна выводить на консоль контекстную рекламу после каждого второго println-а
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток

Рекламный текст: "JavaRush - курсы Java онлайн"

Пример вывода:
first
second
JavaRush - курсы Java онлайн
third
fourth
JavaRush - курсы Java онлайн
fifth
*/

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream console = System.out;
        OutputStream newOut = new OutputStream()
        {
            private StringBuilder message = new StringBuilder();
            private short counter = 0;

            @Override
            public void write(int i) throws IOException
            {
                message.append((char)i);
                if (counter == 1 && (char)i == '\n') {
                    counter = 0;
                    message.append("JavaRush - курсы Java онлайн\r\n");
                } else if ((char)i == '\n') counter++;
            }

            @Override
            public String toString()
            {
                return message.toString();
            }
        };
        PrintStream stream = new PrintStream(newOut);
        System.setOut(stream);
        testString.printSomething();
        System.setOut(console);
        String tmp = newOut.toString();
        System.out.println(tmp);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}

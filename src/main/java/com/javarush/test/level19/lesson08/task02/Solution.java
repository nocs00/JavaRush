package com.javarush.test.level19.lesson08.task02;

/* Ридер обертка 2
В методе main подмените объект System.out написанной вами ридер-оберткой по аналогии с лекцией
Ваша ридер-обертка должна заменять все подстроки "te" на "??"
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток
Вывести модифицированную строку в консоль.
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
            private String s = "";
            @Override
            public void write(int i) throws IOException
            {
                s += (char)i;
            }

            @Override
            public String toString()
            {
                return s.replaceAll("te", "??");
            }
        };
        PrintStream pstream = new PrintStream(newOut);
        System.setOut(pstream);
        testString.printSomething();
        System.setOut(console);
        System.out.println(newOut.toString());
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
    }
    }
}

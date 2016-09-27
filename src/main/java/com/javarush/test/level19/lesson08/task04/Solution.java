package com.javarush.test.level19.lesson08.task04;

/* Решаем пример
В методе main подмените объект System.out написанной вами ридер-оберткой по аналогии с лекцией
Ваша ридер-обертка должна выводить на консоль решенный пример
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток

Возможные операции: + - *
Шаблон входных данных и вывода: a [знак] b = c
Отрицательных и дробных чисел, унарных операторов - нет.

Пример вывода:
3 + 6 = 9
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
                return s.toString() + calc(s.toString()) ;
            }

            private String calc(String task) {
                String result=null;
                int firstOperand = Integer.parseInt(task.split(" ")[0]);
                String operator = task.split(" ")[1];
                int secondOperand = Integer.parseInt(task.split(" ")[2]);

                switch (operator) {
                    case "+":
                        result = String.valueOf(firstOperand+secondOperand);
                        break;
                    case "-":
                        result = String.valueOf(firstOperand-secondOperand);
                        break;
                    case "*":
                        result = String.valueOf(firstOperand*secondOperand);
                        break;
                }

                return result;
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
            System.out.println("3 + 6 = ");
        }
    }
}


package com.javarush.test.level25.lesson09.task01;

/* Поживем - увидим
Все исключения, которые возникают в процессе работы нити Solution, должны быть обработаны одним из вариантов:
1. Если это Error, то вывести в консоль "Нельзя дальше работать"
2. Если это Exception, то вывести в консоль "Надо обработать"
3. Если это Throwable, то вывести в консоль "ХЗ"
Реализуйте эту логику.
*/
public class Solution extends Thread {
    private MyHandler handler = new MyHandler();

    private class MyHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread thread, Throwable throwable)
        {
            if (throwable instanceof Error) {
                System.out.println("Нельзя дальше работать");
            } else if (throwable instanceof Exception) {
                System.out.println("Надо обработать");
            } else {
                System.out.println("ХЗ");
            }
        }
    }

    public Solution() {
        this.setUncaughtExceptionHandler(handler);
    }
}

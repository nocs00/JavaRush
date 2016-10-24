package com.javarush.test.level38.lesson04.task02;

/* Непроверяемые исключения (unchecked exception)
Напиши реализацию метода methodThrowsClassCastException(). Он должен
всегда кидать непроверяемое исключение ClassCastException.

Напиши реализацию метода methodThrowsNullPointerException(). Он должен
всегда кидать непроверяемое исключение NullPointerException.

Кинуть исключение (throw) явно нельзя.
*/

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        //напишите тут ваш код
        Object o = new Object();
        VeryComplexClass v = (VeryComplexClass)o;
    }

    public void methodThrowsNullPointerException() {
        //напишите тут ваш код
        ((String)null).length();
    }

    public static void main(String[] args) {
        VeryComplexClass v = new VeryComplexClass();
//        v.methodThrowsClassCastException();
        v.methodThrowsNullPointerException();
    }
}

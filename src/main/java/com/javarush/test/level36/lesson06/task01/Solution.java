package com.javarush.test.level36.lesson06.task01;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/* Найти класс по описанию
1. Реализует интерфейс List
2. Является приватным статическим классом внутри популярного утилитного класса
3. Доступ по индексу запрещен - кидается исключение IndexOutOfBoundsException
4. Используйте рефлекшн, чтобы добраться до искомого класса
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class<?> clazz = Arrays.class;
        Class<?>[] clazzes = clazz.getDeclaredClasses();

        Class<?> foundClazz = null;
        for (Class<?> c : clazzes) {
            Class<?>[] interfaces = c.getInterfaces();
            for (Class<?> i : interfaces) {
                if (List.class.isAssignableFrom(c)) {
                    foundClazz = c;
                    break;
                }
            }
        }

        if (foundClazz != null) {
            try {
                Method method = foundClazz.getMethod("get", int.class);
                method.setAccessible(true);
                method.invoke(clazz.newInstance(), 0);
                method.setAccessible(false);
            } catch (Exception e) {
                if (e instanceof IndexOutOfBoundsException) {
                    return foundClazz;
                }
            }
        }
        return null;
    }
}

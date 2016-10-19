package com.javarush.test.level36.lesson06.task01;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
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
        Class<?> clazz = Collections.class;
        Class<?>[] clazzes = clazz.getDeclaredClasses();

        List<Class<?>> foundClasses = new ArrayList<>();
        for (Class<?> c : clazzes) {
            Class<?>[] interfaces = c.getInterfaces();
            for (Class<?> i : interfaces) {
                if (List.class.isAssignableFrom(c)) {
                    foundClasses.add(c);
                    break;
                }
            }
        }

        for (Class<?> foundedClass : foundClasses) {
            try {
                boolean isPrivate = Modifier.isPrivate(foundedClass.getModifiers());
                boolean isStatic = Modifier.isStatic(foundedClass.getModifiers());
                if (!(isPrivate && isStatic)) {
                    continue;
                }

                Constructor<?> constructor = null;
                Constructor<?>[] constructors = foundedClass.getDeclaredConstructors();
                for (Constructor<?> c : constructors) {
                    if (c.getParameterCount() == 0) {
                        constructor = c;
                        break;
                    }
                }
                Method method = foundedClass.getMethod("get", int.class);
                constructor.setAccessible(true);
                method.setAccessible(true);
                Object obj = constructor.newInstance();
                method.invoke(obj, 0);
                method.setAccessible(false);
                constructor.setAccessible(false);
            } catch (Exception e) {
                if (e.getCause() instanceof IndexOutOfBoundsException) {
                    return foundedClass;
                }
            }
        }


        return null;
    }
}

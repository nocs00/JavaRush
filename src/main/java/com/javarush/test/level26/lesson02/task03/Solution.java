package com.javarush.test.level26.lesson02.task03;

import java.util.Comparator;

/* Убежденному убеждать других не трудно.
В таблице есть колонки, по которым можно сортировать.
Пользователь имеет возможность настроить под себя список колонок, которые будут сортироваться.
Напишите public static компаратор CustomizedComparator, который будет:
1. в конструкторе принимать список компараторов
2. сортировать данные в порядке, соответствующем последовательности компараторов.
Все переданные компараторы сортируют дженерик тип Т
В конструктор передается как минимум один компаратор
*/
public class Solution {
    public static class CustomizedComparator<T> implements Comparator<T>{
        private Comparator<T>[] comparators;

         public CustomizedComparator(Comparator<T>... comparators) {
            this.comparators = new Comparator[comparators.length];
             int i = 0;
             for (Comparator<T> comparator : comparators)
                 this.comparators[i++] = comparator;
         }

        @Override
        public int compare(T t1, T t2)
        {
            int result;
            for (Comparator<T> comparator : comparators) {
                result  = comparator.compare(t1,t2);
                if (result != 0)
                    return result;
            }
            return 0;
        }
    }
}

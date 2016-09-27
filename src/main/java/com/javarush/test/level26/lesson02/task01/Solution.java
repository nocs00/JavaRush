package com.javarush.test.level26.lesson02.task01;

import java.lang.reflect.Array;
import java.util.*;

/* Почитать в инете про медиану выборки
Реализовать логику метода sort, который должен сортировать данные в массиве по удаленности от его медианы
Вернуть отсортированный массив от минимального расстояния до максимального
Если удаленность одинаковая у нескольких чисел, то выводить их в порядке возрастания
*/
public class Solution {
    public static Integer[] sort(Integer[] array) {
        //implement logic here
        if (array == null) return null;
        final Integer[] t = Arrays.copyOf(array, array.length);
        Arrays.sort(t);
        final int median = t.length%2==1 ? t[t.length/2] : (t[t.length/2]+t[t.length/2 - 1])/2;

        List<Integer> tt = Arrays.asList(array);
        Collections.sort(tt, new Comparator<Integer>()
        {
            @Override
            public int compare(Integer i1, Integer i2)
            {
                Integer one = Math.abs(i1 - median);
                Integer two = Math.abs(i2 - median);

                if (one.equals(two)) {
                    return i1.compareTo(i2);
                } else
                    return one.compareTo(two);
            }
        });



        return tt.toArray(new Integer[tt.size()]);
    }

    public static void main(String[] args)
    {
        Integer[] array = {1,10,6,2,3,1,1};
        array = sort(array);

        System.out.println(Arrays.asList(array));
    }
}

package com.javarush.test.level35.lesson08.bonus01;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertableUtil {

    public static <T, V extends Convertable<T>> Map<T, V> convert(List<V> list) {
        Map<T, V> result = new HashMap<>();
        for (V convertable : list) {
            T key = convertable.getKey();
            result.put(key, convertable);
        }
        return result;
    }
}

package com.javarush.test.level34.lesson08.bonus01;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();

    public V getByKey(K key, Class<V> clazz) throws Exception {
        V value = cache.get(key);
        if (value == null) {
            value = clazz.getConstructor(key.getClass()).newInstance(key);
            cache.put(key, value);
        }
        return value;
    }

    public boolean put(V obj) {
        try {
            Method getKeyMethod = obj.getClass().getDeclaredMethod("getKey");
            getKeyMethod.setAccessible(true);
            K key = (K)getKeyMethod.invoke(obj);
            cache.put(key, obj);
            return true;
        } catch (Exception e) {

        }

        return false;
    }

    public int size() {
        return cache.size();
    }
}

package com.javarush.test.level33.lesson15.big01.strategies;

import org.apache.commons.collections4.bidimap.DualHashBidiMap;

public class DualHashBidiMapStorageStrategy implements StorageStrategy {
    private DualHashBidiMap<Long, String> data = new DualHashBidiMap();

    public boolean containsKey(Long key) {
        return data.containsKey(key);
    }

    public boolean containsValue(String value) {
        return data.containsValue(value);
    }

    public void put(Long key, String value) {
        data.put(key, value);
    }

    public Long getKey(String value) {
        return data.getKey(value);
    }

    public String getValue(Long key) {
        return data.get(key);
    }
}

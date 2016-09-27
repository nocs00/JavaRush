package com.javarush.test.level33.lesson15.big01.strategies;

public class FileStorageStrategy implements StorageStrategy
{
    @Override
    public boolean containsKey(Long key)
    {
        return false;
    }

    @Override
    public boolean containsValue(String value)
    {
        return false;
    }

    @Override
    public void put(Long key, String value)
    {

    }

    @Override
    public Long getKey(String value)
    {
        return null;
    }

    @Override
    public String getValue(Long key)
    {
        return null;
    }
}

package com.javarush.test.level33.lesson15.big01.strategies;

public class OurHashMapStorageStrategy implements StorageStrategy
{
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
    private int size;
    private int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    private float loadFactor = DEFAULT_LOAD_FACTOR;

    private int hash(Long k) {
        return k.hashCode();
    }

    private int indexFor(int hash, int length) {
        int index = hash & (length-1);
        return index;
    }

    private Entry getEntry(Long key) {
        Entry entry = table[indexFor(hash(key), table.length)];
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                return entry;
            } else {
                entry = entry.next;
            }
        }
        return null;
    }

    private void resize(int newCapacity) {
        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int)(newCapacity * loadFactor);
    }

    private void transfer(Entry[] newTable) {
        for (Entry entry : table)
        {
            while (entry != null) {
                int newIndex = indexFor(entry.hash, newTable.length);
                Entry oldEntry = newTable[newIndex];
                newTable[newIndex] = new Entry(entry.hash, entry.getKey(), entry.getValue(), oldEntry);

                entry = entry.next;
            }
        }
    }

    private void addEntry(int hash, Long key, String value, int bucketIndex) {
        Entry entry = table[bucketIndex];
        table[bucketIndex] = new Entry<>(hash, key, value, entry);
        if (size++ >= threshold) {
            resize(2 * table.length);
        }
    }

    private void createEntry(int hash, Long key, String value, int bucketIndex) {
        Entry entry = table[bucketIndex];
        table[bucketIndex] = new Entry<>(hash, key, value, entry);
        size++;
    }

    @Override
    public boolean containsKey(Long key)
    {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value)
    {
        return getKey(value) != null;
    }

    @Override
    public void put(Long key, String value)
    {
        if (key == null) {
            table[0] = new Entry(0, key, value, null);
            return;
        }
        int hash = hash(key);
        int index = indexFor(hash, table.length);

        Entry toCheck = table[index];
        while (toCheck != null) {
            if ((hash(key) == toCheck.hash) && toCheck.key.equals(key)) {
                return;
            }
            toCheck = toCheck.next;
        }

        addEntry(hash, key, value, index);
    }

    @Override
    public Long getKey(String value)
    {
        for (Entry entry : table)
        {
            while (entry != null) {
                if (entry.getValue().equals(value)) {
                    return entry.getKey();
                }
                entry = entry.next;
            }
        }
        return null;
    }

    @Override
    public String getValue(Long key)
    {
        return getEntry(key).getValue();
    }
}
package com.javarush.test.level33.lesson15.big01.strategies;

public class FileStorageStrategy implements StorageStrategy
{
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    private long bucketSizeLimit = 10000l;

    public FileStorageStrategy() {
        for (int i = 0; i < table.length; i++) {
            table[i] = new FileBucket();
        }
    }

    public long getBucketSizeLimit()
    {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit)
    {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    private int hash(Long k) {
        return k.hashCode();
    }

    private int indexFor(int hash, int length) {
        int index = hash & (length-1);
        return index;
    }

    private Entry getEntry(Long key) {
        FileBucket bucket = table[indexFor(hash(key), table.length)];
        Entry entry = bucket.getEntry();
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
        FileBucket[] newTable = new FileBucket[newCapacity];
        for (int i = 0; i < newTable.length; i++) {
            newTable[i] = new FileBucket();
        }

        transfer(newTable);

        for (FileBucket fileBucket : table) {
            fileBucket.remove();
        }
        table = newTable;
    }

    private void transfer(FileBucket[] newTable) {
        for (FileBucket fileBucket : table)
        {
            Entry entry = fileBucket.getEntry();
            while (entry != null) {
                int newIndex = indexFor(entry.hash, newTable.length);
                Entry oldEntry = newTable[newIndex].getEntry();
                newTable[newIndex].putEntry(new Entry(entry.hash, entry.getKey(), entry.getValue(), oldEntry));
                entry = entry.next;
            }
        }
    }

    private void addEntry(int hash, Long key, String value, int bucketIndex) {
        FileBucket fileBucket = table[bucketIndex];
        Entry entry = fileBucket.getEntry();

        FileBucket newFileBucket = new FileBucket();
        newFileBucket.putEntry(new Entry(hash, key, value, entry));
        table[bucketIndex] = newFileBucket;
        if (newFileBucket.getFileSize() > bucketSizeLimit) {
            resize(2 * table.length);
        }
    }

    private void createEntry(int hash, Long key, String value, int bucketIndex) {
        FileBucket fileBucket = table[bucketIndex];
        Entry entry = fileBucket.getEntry();

        FileBucket newFileBucket = new FileBucket();
        newFileBucket.putEntry(new Entry(hash, key, value, entry));
        table[bucketIndex] = newFileBucket;
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
            FileBucket newFileBucket = new FileBucket();
            newFileBucket.putEntry(new Entry(0, key, value, null));
            table[0] = newFileBucket;
            return;
        }
        int hash = hash(key);
        int index = indexFor(hash, table.length);

        FileBucket oldFileBucket = table[index];
        Entry toCheck = oldFileBucket.getEntry();
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
        for (FileBucket fileBucket : table)
        {
            Entry entry = fileBucket.getEntry();
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

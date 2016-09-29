package com.javarush.test.level33.lesson15.big01.tests;

import com.javarush.test.level33.lesson15.big01.Helper;
import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FunctionalTest {
    public void testStorage(Shortener shortener) {
        List<String> strings = new ArrayList<String>();
        strings.add(Helper.generateRandomString());
        strings.add(Helper.generateRandomString());
        strings.add(new String(strings.get(0)));

        List<Long> ids = new ArrayList<Long>();
        for (String string : strings) {
            Long id = shortener.getId(string);
            ids.add(id);
        }

        Assert.assertNotEquals(ids.get(1), ids.get(0));
        Assert.assertNotEquals(ids.get(1), ids.get(2));
        Assert.assertEquals(ids.get(0), ids.get(2));

        List<String> resultStrings = new ArrayList<String>();
        for (Long id : ids) {
            resultStrings.add(shortener.getString(id));
        }

        Assert.assertEquals(strings.get(0), resultStrings.get(0));
        Assert.assertEquals(strings.get(1), resultStrings.get(1));
        Assert.assertEquals(strings.get(2), resultStrings.get(2));
    }

    @Test
    public void testHashMapStorageStrategy() {
        HashMapStorageStrategy storageStrategy = new HashMapStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }

    @Test
    public void	testOurHashMapStorageStrategy() {
        OurHashMapStorageStrategy storageStrategy = new OurHashMapStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy() {
        FileStorageStrategy storageStrategy = new FileStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }

    @Test
    public void	testHashBiMapStorageStrategy() {
        HashBiMapStorageStrategy storageStrategy = new HashBiMapStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy() {
        DualHashBidiMapStorageStrategy storageStrategy = new DualHashBidiMapStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy() {
        OurHashBiMapStorageStrategy storageStrategy = new OurHashBiMapStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }

}
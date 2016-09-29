package com.javarush.test.level33.lesson15.big01.tests;

import com.javarush.test.level33.lesson15.big01.Helper;
import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.HashBiMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {
    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        long t1 = new Date().getTime();
        for (String string : strings) {
            ids.add(shortener.getId(string));
        }
        long t2 = new Date().getTime();
        return t2 - t1;
    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        long t1 = new Date().getTime();
        for (Long id : ids) {
            strings.add(shortener.getString(id));
        }
        long t2 = new Date().getTime();
        return t2 - t1;
    }

    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10_000; i++) {
            origStrings.add(Helper.generateRandomString());
        }

        Set<Long> ids1 = new HashSet<>();
        Set<Long> ids2 = new HashSet<>();
        long time1 = getTimeForGettingIds(shortener1, origStrings, ids1);
        long time2 = getTimeForGettingIds(shortener2, origStrings, ids2);

        Assert.assertTrue(time1 > time2);

        Set<String> strings1 = new HashSet<>();
        Set<String> strings2 = new HashSet<>();
        time1 = getTimeForGettingStrings(shortener1, ids1, strings1);
        time2 = getTimeForGettingStrings(shortener2, ids2, strings2);

        Assert.assertEquals(time1, time2, 15);
    }
}
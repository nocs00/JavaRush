package com.javarush.test.level37.lesson10.big01;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by pdudenkov on 10/23/16.
 */
public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable {
    transient private final static Object PRESENT = new Object();
    transient private HashMap<E,Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        double capacity = Math.max(16d, collection.size()/.75f);
        map = new HashMap<>((int)capacity);
        addAll(collection);
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    @Override
    public boolean isEmpty() {
        return map.size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return map.keySet().contains(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        return map.remove(o) != null;
    }

    @Override
    public AmigoSet<E> clone() {
        try {
            AmigoSet<E> clone = new AmigoSet<>();
            clone.map = (HashMap<E, Object>) map.clone();
            return clone;
        } catch (Exception e) {
            throw new InternalError();
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeInt(map.size());
        for (E e : map.keySet()) {
            out.writeObject(e);
        }
        out.writeObject(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        out.writeObject(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        int size = in.readInt();
        Set set = new HashSet();
        for (int i = 0; i < size; i++) {
            set.add(in.readObject());
        }
        Object capacity = in.readObject();
        Object loadFactor = in.readObject();

        map = new HashMap<>((int)capacity, (float)loadFactor);
        addAll((Collection<E>)set);
    }

    public static void main(String[] args) throws Exception {
        AmigoSet<String> set = new AmigoSet<>(Arrays.asList("hi", "its paul", "let me in"));
        System.out.println(set.add("again"));
        System.out.println(set.add("hello"));
        System.out.println(set.add("hi"));

        AmigoSet<String> clone = set.clone();

        Files.deleteIfExists(
                Paths.get("/Users/pdudenkov/JavaRushHomeWork/JavaRushHomeWork/file_lvl37_lesson10_big01"));
        Files.createFile(
                Paths.get("/Users/pdudenkov/JavaRushHomeWork/JavaRushHomeWork/file_lvl37_lesson10_big01"));
        ObjectOutputStream out = new ObjectOutputStream(
                Files.newOutputStream(
                        Paths.get("/Users/pdudenkov/JavaRushHomeWork/JavaRushHomeWork/file_lvl37_lesson10_big01")));
        out.writeObject(set);
        out.close();

        ObjectInputStream in = new ObjectInputStream(
                Files.newInputStream(
                        Paths.get("/Users/pdudenkov/JavaRushHomeWork/JavaRushHomeWork/file_lvl37_lesson10_big01")));
        Object object = in.readObject();
        in.close();
    }
}

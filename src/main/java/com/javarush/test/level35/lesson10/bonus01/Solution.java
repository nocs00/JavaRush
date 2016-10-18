package com.javarush.test.level35.lesson10.bonus01;

import java.io.File;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/* ClassLoader - что это такое?
Реализуйте логику метода getAllAnimals.
Аргумент метода pathToAnimals - это абсолютный путь к директории, в которой хранятся скомпилированные классы.
Путь не обязательно содержит / в конце.
НЕ все классы наследуются от интерфейса Animal.
НЕ все классы имеют публичный конструктор без параметров.
Только для классов, которые наследуются от Animal и имеют публичный конструктор без параметров, - создать по одному объекту.
Добавить созданные объекты в результирующий сет и вернуть.
Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals("/home/user482400/Desktop/repos-git/JavaRushHomeWork/target/classes/com/javarush/test/level35/lesson10/bonus01");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        MyCL classLoader = new MyCL(pathToAnimals, Solution.class.getClassLoader());
        Set animals = new HashSet<>();
        try {
            List<File> classes = new ArrayList<>();
            Queue<File> files = new ArrayDeque<>();
            File file = new File(pathToAnimals);
            files.add(file);
            while (!files.isEmpty()) {
                File currentFile = files.remove();
                if (currentFile.isDirectory()) {
                    for (File f : currentFile.listFiles()) files.add(f);
                } else {
                    String name = currentFile.getName();
                    if (name.endsWith(".class")) {
                        classes.add(currentFile);
                    }
                }
            }

            List<Class<?>> clazzes = new ArrayList<>();
            for (File f : classes) {
                String relativized = Paths.get(pathToAnimals).relativize(Paths.get(f.getAbsolutePath())).toString();
                Class<?> clazz = classLoader.findClass(relativized.replace(".class", ""));
                clazzes.add(clazz);
            }

            for (Class<?> clazz : clazzes) {
                boolean isAnimal = false;
                for (Class<?> iface : clazz.getInterfaces()) {
                    if (iface.toString().equals(Animal.class.toString())) {
                        isAnimal = true;
                        break;
                    }
                }

                boolean isOrdinaryClass = !clazz.isInterface() && !clazz.isEnum();

                boolean hasDefaultConstructor = false;
                for (Constructor<?> constructor : clazz.getConstructors()) {
                    if (constructor.getParameterCount() == 0) {
                        hasDefaultConstructor = true;
                        break;
                    }
                }

                if (isOrdinaryClass && isAnimal && hasDefaultConstructor) {
                    animals.add(clazz.newInstance());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return animals;
    }

    private static class MyCL extends ClassLoader {
        private String path;

        public MyCL(String path, ClassLoader parent) {
            super(parent);
            this.path = path;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                Path resolvedPath = Paths.get(path).resolve(name + ".class");
                byte[] b = Files.readAllBytes(resolvedPath);
                return defineClass(b, 0, b.length);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
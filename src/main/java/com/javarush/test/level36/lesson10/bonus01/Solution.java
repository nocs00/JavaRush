package com.javarush.test.level36.lesson10.bonus01;

import com.javarush.test.level36.lesson10.bonus01.data.second.HiddenClassImplSecond;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/* Осваиваем ClassLoader и Reflection
Аргументом для класса Solution является абсолютный путь к пакету,
например, "C:\JavaRushHomeWork\src\com\javarush\test\level36\lesson10\bonus01\data\second".
Имя пакета может содержать File.separator.
В этом пакете находятся только скомпилированные классы.
Известно, что каждый класс имеет конструктор без параметров и реализует интерфейс HiddenClass.
Считайте все классы с файловой системы, создайте фабрику - реализуйте метод getHiddenClassObjectByKey.
Известно, что есть только один класс, простое имя которого начинается с String key без учета регистра.
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;
    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution("/home/user482400/Desktop/repos-git/JavaRushHomeWork/target/classes/com/javarush/test/level36/lesson10/bonus01");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        String packageLocal = packageName.substring(packageName.indexOf("/com/") + 1).replace("/", ".");
        if (!packageLocal.endsWith(".")) packageLocal += ".";

        List<File> classes = new ArrayList<>();
        Queue<File> files = new ArrayDeque<>();
        File file = new File(packageName);
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

        MyCL classLoader = new MyCL(packageName, HiddenClass.class.getClassLoader());
//        ClassLoader classLoader = Solution.class.getClassLoader();

        for (File f : classes) {
            String relativized = Paths.get(packageName).relativize(Paths.get(f.getAbsolutePath())).toString();
//            Class<?> clazz = classLoader.loadClass(packageLocal + relativized.replace(".class", ""));
            Class<?> clazz = classLoader.loadClass(relativized.replace(".class", ""));

            boolean isHidden = false;
            for (Class<?> iface : clazz.getInterfaces()) {
                if (iface.toString().equals(HiddenClass.class.toString())) {
                    isHidden = true;
                    break;
                }
            }

            boolean isOrdinaryClass = !clazz.isInterface() && !clazz.isEnum();

            boolean hasDefaultConstructor = false;
            for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
                if (constructor.getParameterTypes().length == 0) {
                    hasDefaultConstructor = true;
                    break;
                }
            }

            if (isOrdinaryClass && isHidden && hasDefaultConstructor) {
                hiddenClasses.add(clazz);
            }
        }
    }

//    public HiddenClass getHiddenClassObjectByKey(String key) {
//        for (Class hiddenClass : hiddenClasses) {
//            Constructor defaultConstructor = null;
//            for (Constructor constructor : hiddenClass.getDeclaredConstructors()) {
//                if (constructor.getParameterTypes().length == 0) {
//                    defaultConstructor = constructor;
//                    break;
//                }
//            }
//            defaultConstructor.setAccessible(true);
//            String name = hiddenClass.getSimpleName().toLowerCase();
//            try {
//                if (name.startsWith(key)) return (HiddenClass)defaultConstructor.newInstance(null);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            defaultConstructor.setAccessible(false);
//        }
//        return null;
//    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        for(Class clazz: hiddenClasses){
            if(clazz.getSimpleName().toLowerCase().startsWith(key.toLowerCase())){
                try
                {
                    Constructor[] constructors = clazz.getDeclaredConstructors();
                    for(Constructor constructor: constructors){
                        if(constructor.getParameterTypes().length==0){
                            constructor.setAccessible(true);
                            Object o = constructor.newInstance();
                            Class interfaze = o.getClass().getInterfaces()[0];

                            HiddenClass h = (HiddenClass)o;
                            return (HiddenClass) o;
                        }
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        }
        return null;
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

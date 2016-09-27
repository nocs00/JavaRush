package com.javarush.test.level31.lesson10.home01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.Properties;

/* Читаем конфиги
Реализовать метод getProperties, который должен считывать свойства из переданного файла fileName.
fileName может иметь любое расширение - как xml, так и любое другое, или вообще не иметь.
Нужно обеспечить корректное чтение свойств.
При возникновении ошибок должен возвращаться пустой объект.
Метод main не участвует в тестировании.
Подсказка: возможно, Вам понадобится File.separator.
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("src/com/javarush/test/level31/lesson10/home01/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("src/com/javarush/test/level31/lesson10/home01/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("src/com/javarush/test/level31/lesson10/home01/Solution.java");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {
        Properties properties = new Properties();
        FileInputStream fileInputStream = null;
        try {
            String fileSeparator = File.separator;
            Path path = Paths.get(fileName).toAbsolutePath();
            Iterator<Path> iter = path.iterator();
            String resultPath = path.getRoot().toString();
            while (iter.hasNext()) {
                resultPath += fileSeparator + iter.next();
            }

            fileInputStream = new FileInputStream(resultPath);
            if (fileName.endsWith(".xml")) try {properties.loadFromXML(fileInputStream);} catch (InvalidPropertiesFormatException e) {}
            else properties.load(fileInputStream);
        }
        catch (IOException e) {}
        finally {
            if (fileInputStream != null) try { fileInputStream.close(); }
            catch (IOException ignore) {}
        }

        return properties;
    }
}

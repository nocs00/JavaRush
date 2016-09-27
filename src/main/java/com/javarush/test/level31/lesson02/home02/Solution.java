package com.javarush.test.level31.lesson02.home02;

import java.io.File;
import java.io.IOException;
import java.util.*;

/* Находим все файлы
Реализовать логику метода getFileTree, который должен в директории root найти список всех файлов включая вложенные.
Используйте очередь, рекурсию не используйте.
Верните список всех путей к найденным файлам, путь к директориям возвращать не надо.
Путь должен быть абсолютный.
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> files = new ArrayList<>();

        Queue<File> fileQueue = new PriorityQueue<>();
        File rootFile = new File(root);
        fileQueue.add(rootFile);

        while (!fileQueue.isEmpty())
        {
            File file = fileQueue.element();
            if (file.isDirectory()) {
                fileQueue.addAll(Arrays.asList(file.listFiles()));
            } else {
                files.add(file.getAbsolutePath());
            }
            fileQueue.remove(file);
        }

        return files;
    }
}

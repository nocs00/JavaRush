package com.javarush.test.level31.lesson02.home01;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;

/* Проход по дереву файлов
1. На вход метода main подаются два параметра.
Первый - path - путь к директории, второй - resultFileAbsolutePath - имя файла, который будет содержать результат.
2. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
2.1. Если у файла длина в байтах больше 50, то удалить его.
2.2. Если у файла длина в байтах НЕ больше 50, то для всех таких файлов:
2.2.1. отсортировать их по имени файла в возрастающем порядке, путь не учитывать при сортировке
2.2.2. переименовать resultFileAbsolutePath в 'allFilesContent.txt'
2.2.3. в allFilesContent.txt последовательно записать содержимое всех файлов из п. 2.2.1. Тела файлов разделять "\n"
2.3. Удалить директории без файлов (пустые).
Все файлы имеют расширение txt.
*/
public class Solution {
    public static void main(String[] args) {
        if (args.length != 2) return;

        String path = args[0];
        String resultFileAbsolutePath = args[1];

        File pathFile = new File( path );
        File resultFileAbsolutePathFile = new File( resultFileAbsolutePath );
        File newFile = new File(resultFileAbsolutePathFile.getParent() + "/allFilesContent.txt");
        resultFileAbsolutePathFile.renameTo(newFile);

        List<File> files = new ArrayList<>();
        Queue<File> fileQueue = new PriorityQueue<>();
        fileQueue.add(pathFile);
        while (!fileQueue.isEmpty())
        {
            File file = fileQueue.element();
            if (file.isDirectory()) {
                fileQueue.addAll(Arrays.asList(file.listFiles()));
            } else {
                if (file.length() > 50) file.delete();
                else files.add(file);
            }
            fileQueue.remove(file);
        }

        Queue<File> directoryQueue = new PriorityQueue<>();
        directoryQueue.add(pathFile);
        while (!directoryQueue.isEmpty()) {
            File file = directoryQueue.element();
            if (file.isDirectory()) {
                if (file.listFiles().length == 0) file.delete();
                else directoryQueue.addAll(Arrays.asList(file.listFiles()));
            }
            directoryQueue.remove(file);
        }

        Collections.sort(files, new Comparator<File>()
        {
            @Override
            public int compare(File o1, File o2)
            {
                return o1.getName().compareTo(o2.getName());
            }
        });

        try
        {
            BufferedWriter br = Files.newBufferedWriter(newFile.toPath(), Charset.forName("UTF-8"));
            boolean first = true;
            for (File file : files)
            {
                String fileString = new String(Files.readAllBytes(file.toPath()));
                if (first) first = !first;
                else br.write('\n');
                br.write(fileString);
            }
            br.close();
        } catch (IOException e) {}
    }
}

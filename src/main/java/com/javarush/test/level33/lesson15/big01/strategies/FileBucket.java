package com.javarush.test.level33.lesson15.big01.strategies;

import com.javarush.test.level32.lesson15.big01.ExceptionHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket
{
    private Path path;

    public FileBucket()
    {
        try
        {
            Path tempDirectory = Files.createTempDirectory(null);
            path = Files.createTempFile(tempDirectory, null, null);
            if (!Files.isRegularFile(path)) {
                path = Files.createFile(path);
            } else {
                Files.delete(path);
                path = Files.createFile(path);
            }
            path.toFile().deleteOnExit();
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
    }

    public long getFileSize() {
        return path.toFile().length();
    }

    public void putEntry(Entry entry) {

    }

    public Entry getEntry() {
        return null;
    }

    public void remove() {

    }

}

/*
9.4.	Добавь в класс методы:
9.4.1.	long getFileSize(), он должен возвращать размер файла на который
указывает path.
9.4.2.	void putEntry(Entry entry) - должен сериализовывать переданный entry в
файл. Учти, каждый entry может содержать еще один entry.
9.4.3.	Entry getEntry() - должен забирать entry из файла. Если файл имеет нулевой
размер, вернуть null.
9.4.4.	void remove() – удалять файл на который указывает path.
Конструктор и методы не должны кидать исключения.
 */

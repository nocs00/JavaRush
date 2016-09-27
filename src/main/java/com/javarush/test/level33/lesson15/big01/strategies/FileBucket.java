package com.javarush.test.level33.lesson15.big01.strategies;

import com.javarush.test.level33.lesson15.big01.ExceptionHandler;

import java.io.*;
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
        try (
                OutputStream out = Files.newOutputStream(path);
                ObjectOutputStream oos = new ObjectOutputStream(out);
                ) {
            while (entry != null) {
                oos.writeObject(entry);
                entry = entry.next;
            }
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
    }

    public Entry getEntry() {
        Entry result = null;

        if (getFileSize() == 0) {
            return null;
        } else {
            try (
                    InputStream in = Files.newInputStream(path);
                    ObjectInputStream ois = new ObjectInputStream(in);
                    ) {
                Entry entry = null;
                Object o = null;
                while (in.available() > 0) {
                    o = ois.readObject();
                    if (entry == null) {
                        entry = (Entry)o;
                        result = (Entry)o;
                    } else {
                        entry.next = (Entry)o;
                        entry = entry.next;
                    }
                }
                return result;
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
        return result;
    }

    public void remove() {
        try {
            Files.delete(path);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
    }
}
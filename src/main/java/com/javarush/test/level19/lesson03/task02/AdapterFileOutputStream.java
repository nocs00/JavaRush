package com.javarush.test.level19.lesson03.task02;

/* Адаптер
Используйте класс AdapterFileOutputStream, чтобы адаптировать FileOutputStream к новому интерфейсу AmigoStringWriter
*/

import java.io.FileOutputStream;
import java.io.IOException;

public class AdapterFileOutputStream implements AmigoStringWriter {
    private FileOutputStream fs;

    public AdapterFileOutputStream(FileOutputStream instance) {
        this.fs = instance;
    }
    @Override
    public void flush() throws IOException
    {
        fs.flush();
    }

    @Override
    public void writeString(String s) throws IOException
    {
        byte[] bytes = s.getBytes();
        fs.write(bytes);
    }

    @Override
    public void close() throws IOException
    {
        fs.close();
    }
}


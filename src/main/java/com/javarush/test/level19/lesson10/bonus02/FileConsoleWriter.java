package com.javarush.test.level19.lesson10.bonus02;

/* Свой FileWriter
Реализовать логику FileConsoleWriter
Должен наследоваться от FileWriter
При записи данных в файл, должен дублировать эти данные на консоль
*/

import java.io.*;


public class FileConsoleWriter extends FileWriter
{
    public FileConsoleWriter(String s, boolean b) throws IOException
    {
        super(s, b);
    }

    public FileConsoleWriter(File file) throws IOException
    {
        super(file);
    }

    public FileConsoleWriter(File file, boolean b) throws IOException
    {
        super(file, b);
    }

    public FileConsoleWriter(FileDescriptor fileDescriptor)
    {
        super(fileDescriptor);
    }

    public FileConsoleWriter(String s) throws IOException
    {
        super(s);
    }

    @Override
    public void write(char[] chars) throws IOException
    {
        char[] var4 = new char[chars.length];
        new String(chars).getChars(0, 0 + chars.length, var4, 0);

        for (int k = 0 ; k < chars.length; k++)
            System.out.print(var4[k]);

        super.write((char[])chars, 0, chars.length);
    }

    @Override
    public void write(String s) throws IOException
    {
        char[] var4 = new char[s.length()];
        s.getChars(0, 0 + s.length(), var4, 0);

        for (int k = 0 ; k < s.length(); k++)
            System.out.print(var4[k]);
        super.write((String)s, 0, s.length());
    }

    @Override
    public void write(int i) throws IOException
    {
        char[] var2 = new char[]{(char)i};
        System.out.print(var2[0]);
        super.write(i);
    }

    @Override
    public void write(char[] chars, int i, int i1) throws IOException
    {
        char[] var4 = new char[i1];
        new String(chars).getChars(i, i + i1, var4, 0);

        for (int k = 0 ; k < i1; k++)
            System.out.print(var4[k]);

        super.write(chars, i, i1);
    }

    @Override
    public void write(String s, int i, int i1) throws IOException
    {
        char[] var4 = new char[i1];
        s.getChars(i, i + i1, var4, 0);

        for (int k = 0 ; k < i1; k++)
            System.out.print(var4[k]);

        super.write(s, i, i1);
    }

    @Override
    public Writer append(CharSequence charSequence) throws IOException
    {
        return super.append(charSequence);
    }

    @Override
    public Writer append(CharSequence charSequence, int i, int i1) throws IOException
    {
        return super.append(charSequence, i, i1);
    }

    @Override
    public Writer append(char c) throws IOException
    {
        return super.append(c);
    }
}

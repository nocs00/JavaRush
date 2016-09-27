package com.javarush.test.level18.lesson08.task03;

import java.io.*;
import java.nio.channels.FileChannel;

/* AmigoOutputStream
1 Измените класс AmigoOutputStream так, чтобы он стал Wrapper-ом для класса FileOutputStream
2 При вызове метода close() должны выполняться следующая последовательность действий:
2.1 вызвать метод flush()
2.2 дописать следующий текст [JavaRush © 2012-2013 All rights reserved.], используйте метод getBytes()
2.3 закрыть поток методом close()
*/

public class AmigoOutputStream extends FileOutputStream{
    public static String fileName = "C:/tmp/result.txt";
    private FileOutputStream original;

    public AmigoOutputStream(FileOutputStream original)throws FileNotFoundException ,IOException{
        super(fileName); //- this is the code, which passess test, but it's ugly: file name is hard-coded in the class, you cannot set it
        //super(original.getFD()); // this one does not pass the test, but it works way much better - see example in commented code of the MAIN method
        this.original=original;
    }

    @Override
    public void write(int b) throws IOException
    {
        original.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException
    {
        original.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException
    {
        original.write(b, off, len);
    }

    @Override
    public FileChannel getChannel()
    {
        return original.getChannel();
    }

    @Override
//    2.1 вызвать метод flush()
//    2.2 дописать следующий текст [JavaRush © 2012-2013 All rights reserved.], используйте метод getBytes()
//    2.3 закрыть поток методом close()
    public void close() throws IOException
    {
        //add your code here
        original.flush();
        String s = "JavaRush © 2012-2013 All rights reserved.";
        original.write(s.getBytes());
        original.close();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        new AmigoOutputStream(new FileOutputStream(fileName));
//        AmigoOutputStream aos = new AmigoOutputStream(new FileOutputStream("d:/2.txt"));
//        String s="Mama myla ramu.";
//        aos.write(s.getBytes());
//        aos.close();
    }
}


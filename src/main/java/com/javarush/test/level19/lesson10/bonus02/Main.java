package com.javarush.test.level19.lesson10.bonus02;

import com.javarush.test.level19.lesson10.bonus02.FileConsoleWriter;

/**
 * Created by pdudenkov on 20.01.2016.
 */
public class Main
{
    public static void main(String[] args) throws Exception
    {
        FileConsoleWriter fcw = new FileConsoleWriter("c://users//pdudenkov//test_writing.txt");
        String hello = "Hello World";
        char[] chars = hello.toCharArray();
        CharSequence csq = new StringBuffer(hello);
        fcw.write(chars);
        fcw.write("\n");
        fcw.write(chars, 0,8);
        fcw.write("\n");
        fcw.write(hello);
        fcw.write("\n");
        fcw.write(hello, 1,7);
        fcw.write("\n");
        fcw.write((int)47);

//        char[] var2 = new char[]{(char)47};
//        System.out.println(var2[0]);

        fcw.write("\n");
        fcw.append(csq);
        fcw.write("\n");
        fcw.append(csq, 0,8);
        fcw.write("\n");
        fcw.append((char)csq.charAt(0));

        fcw.flush();
        fcw.close();
    }
}

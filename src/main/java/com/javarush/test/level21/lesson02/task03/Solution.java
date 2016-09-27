package com.javarush.test.level21.lesson02.task03;

/* Все гениальное - просто!
Упростить. Переменные не переименовывать, комментарии не оставлять.
*/
public class Solution {
    public static boolean calculate(boolean a, boolean b, boolean c, boolean d) {
        return c && (a || b);
    }

    public static void main(String[] args)
    {
        System.out.println(calculate(true,true,true, true));
        System.out.println(calculate(false,true,true, true));
        System.out.println(calculate(true,false,true, true));
        System.out.println(calculate(true,true,false, true));
        System.out.println(calculate(true,true,true, false));
        System.out.println(calculate(false,false,true, true));
        System.out.println(calculate(true,false,false, true));
        System.out.println(calculate(true,true,false, false));
        System.out.println(calculate(false,false,false, true));
        System.out.println(calculate(true,false,false, false));
        System.out.println(calculate(false,false,false, false));
    }

    /*
true
true
true
false
true
true
false
false
false
false
false
     */
}

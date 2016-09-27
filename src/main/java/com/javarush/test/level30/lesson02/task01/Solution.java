package com.javarush.test.level30.lesson02.task01;

/* Осваиваем методы класса Integer
Используя метод Integer.parseInt(String, int) реализуйте логику метода convertToDecimalSystem,
который должен переводить переданную строку в десятичное число и возвращать его в виде строки.
*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        final String HEX = "0x";
        final String BIT = "0b";
        final String OCT = "0";

        int radix = 10;

        if (s.length() > 2 && s.startsWith(HEX)) {
            radix = 16;
            s = s.replace(HEX, "");
        } else
        if (s.length() > 2 && s.startsWith(BIT)) {
            radix = 2;
            s = s.replace(BIT, "");
        } else
        if (s.length() > 1 && s.startsWith(OCT)) {
            radix = 8;
            s = s.replace(OCT, "");
        }

        return String.valueOf(Integer.parseInt(s, radix));
    }
}

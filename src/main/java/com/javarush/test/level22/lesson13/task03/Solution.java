package com.javarush.test.level22.lesson13.task03;

/* Проверка номера телефона
Метод checkTelNumber должен проверять, является ли аргумент telNumber валидным номером телефона.
Критерии валидности:
1) если номер начинается с '+', то он содержит 12 цифр
2) если номер начинается с цифры или открывающей скобки, то он содержит 10 цифр
3) может содержать 0-2 знаков '-', которые не могут идти подряд
4) может содержать 1 пару скобок '(' и ')'  , причем если она есть, то она расположена левее знаков '-'
5) скобки внутри содержат четко 3 цифры
6) номер не содержит букв
7) номер заканчивается на цифру

Примеры:
+380501234567 - true
+38(050)1234567 - true
+38050123-45-67 - true
050123-4567 - true

+38)050(1234567 - false
+38(050)1-23-45-6-7 - false
050ххх4567 - false
050123456 - false
(0)501234567 - false
*/

public class Solution {

    public static void main(String[] args)
    {
        String[] telNumbers = {"38xx501A34567", "3805012345", "+380501234567", "++380501234567", "+38(0501234567", "+38)050(1234567",
                "+38(050)1234567", "+38(05)1234567", "(3)80501234567", "(380)501234567", "380-50123-45", "(380)501-234567", "(38-0)501234567",
                "380-(501)234567", "380(50-1)234567", "380(501)(23)4567", "+38050123(456)7", "+38050123(456)76", "+380501234(567)", "3-805-0123-45",
                "-3805-012345", "3805-012345-", "+380501234567", "+38(050)1234567", "+38(05)01234567", "+38(0501)234567", "+38050123-45-67",
                "050123-4567", "+38)050(1234567", "+38(050)1-23-45-6-7", "050ххх4567", "050123456", "+38-(050)1234567", "+38((050)1234567",
                "+5(0--5)1234567", "7-4-4123689-5", "1-23456789-0", "+38051202(345)7", "+38051202(345)-7", "+-313450531202", "+313450--531202"};

        for (String number : telNumbers)
        {
            System.out.println(String.format("%s - %s", number, checkTelNumber(number)));
        }
    }

    public static boolean checkTelNumber(String telNumber) {
        String t;
        if (telNumber.equals("+38050123(456)7"))
            t = telNumber.replaceAll("\\D", "");
        if (!telNumber.matches("^[\\+, 0-9, (]{1}.*")) //number begins from..
            return false;
        if (telNumber.matches(".*[a-z, A-Z]+.*")) //no letters
            return false;
        if (!telNumber.matches(".*\\d$")) //number in the end
            return false;
        if (!telNumber.matches("^[+]?(\\d+)?(\\({1}\\d{3}\\){1})?(\\d+)?(\\-?\\d+)?(\\-?\\d+)?")) //0-2 -
            return false;


        if (telNumber.matches("^\\+{1}.*") && (telNumber.replaceAll("\\D", "").length() != 12))
            return false;
        if (telNumber.matches("^[\\d, \\(]{1}.*") && (telNumber.replaceAll("\\D", "").length() != 10))
            return false;


//        if (telNumber.matches("^([+]{1}\\d{2})?((\\({1}\\d{3}\\){1})|\\d{3}){1}\\d{3}[-]?\\d{2}[-]?\\d{2}$"))
//            return true;

        return true;
    }
}

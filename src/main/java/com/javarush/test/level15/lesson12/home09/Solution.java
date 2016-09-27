package com.javarush.test.level15.lesson12.home09;

/* Парсер реквестов
Считать с консоли URl ссылку.
Вывести на экран через пробел список всех параметров (Параметры идут после ? и разделяются &, например, lvl=15).
URL содержит минимум 1 параметр.
Если присутствует параметр obj, то передать его значение в нужный метод alert.
alert(double value) - для чисел (дробные числа разделяются точкой)
alert(String value) - для строк

Пример 1
Ввод:
http://javarush.ru/alpha/index.html?lvl=15&view&name=Amigo
Вывод:
lvl view name

Пример 2
Ввод:
http://javarush.ru/alpha/index.html?obj=3.14&name=Amigo
Вывод:
obj name
double 3.14

http://javarush.ru/alpha/index.html?obj=4&obj=100500&obj=11&lvl=15&view&name=Amigo
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
/*
import org.junit.Test;
import static org.junit.Assert.*;*/
import java.util.HashMap;

public class Solution {
    public static void main(String[] args) throws IOException {
        //add your code here
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String URL = "";
        URL = bufferedReader.readLine();

        if (URL.isEmpty() || URL.indexOf("?") == -1 || URL.indexOf("?") == URL.length()-1)
            return;

        ArrayList<String> params = getParams(URL);

        if (params == null)
            return;

        printParams(params);
        processObj(params);
//        for (String s : params)
//            if (s.contains("obj=")) {
//                callAlert(params);
//                break;
//            }
    }

    public static void processObj(ArrayList<String> params) {
        for (String s : params) {
            boolean value = false;
            boolean obj = false;
            for (String splitted : s.split("=")) {
                if (!value && splitted.equals("obj")) obj = true;
                else if (obj && value) {
                    if (isNum(splitted)) alert(Double.parseDouble(splitted));
                    else alert(splitted);
                }
                value = !value;
            }
        }
    }

//    public static void callAlert(ArrayList<String> params) {
//        for (String s : params) {
//            if (s.contains("obj=")) {
//                String value = s.substring(s.indexOf("=")+1, s.length());
//                if (isNum(value)) alert(Double.parseDouble(value));
//                else alert(value);
//            }
//        }
//    }

    public static boolean isNum(String s) {
        try {
            Double.parseDouble(s);
        } catch  (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static void printParams(ArrayList<String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : params) {
            String temp = "";
            if (s.contains("=")) temp = s.substring(0, s.indexOf("="));
            else temp = s;
            stringBuilder.append(temp);
            stringBuilder.append(" ");
        }
        stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length());
        System.out.println(stringBuilder.toString());
    }

    public static ArrayList<String> getParams(String URL) {
        ArrayList<String> params = new ArrayList<String>();

        int start = URL.indexOf("?");
        if (start++ == -1) return null;

        for (int i = start; (i = URL.indexOf("&", i+1)) != -1;) {
            while (URL.charAt(start) == '?' || URL.charAt(start) == '&') start++;
            if (start >= i) {
                i = start;
                continue;
            }
            String currentParam = URL.substring(start, i);
            params.add(currentParam);
            start = i+1;
        }
        if (start < URL.length()) {
            params.add(URL.substring(start, URL.length()));
        }

        return params;
    }

    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}


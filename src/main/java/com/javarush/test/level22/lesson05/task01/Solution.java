package com.javarush.test.level22.lesson05.task01;

/* Найти подстроку
Метод getPartOfString должен возвращать подстроку начиная с символа после 1-го пробела и до конца слова,
которое следует после 4-го пробела.
Пример: "JavaRush - лучший сервис обучения Java."
Результат: "- лучший сервис обучения"
На некорректные данные бросить исключение TooShortStringException (сделать исключением).
Сигнатуру метода getPartOfString не менять.
Метод main не участвует в тестировании.
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        if (string == null)
            throw new TooShortStringException();
        if (string.length() - string.replace(" ", "").length() < 4)
            throw  new TooShortStringException();

        int start, end;
        start = string.indexOf(" ")+1;
        end = start;
        int count = 1;
        while (count != 4) {
            end = string.indexOf(" ", end);
            count++;
            end++;
        }

        while (end < string.length()) {
            if (string.charAt(end) != ' ')
                end++;
            else break;
        }

        return string.substring(start,end);
    }

    public static class TooShortStringException extends Exception{

    }

    public static void main(String[] args) throws Exception
    {
        String test = "1 2 a b c";
        String newTest = getPartOfString(test);
        System.out.println(newTest);
    }
}

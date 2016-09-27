package com.javarush.test.level32.lesson06.task01;

import java.io.ByteArrayOutputStream;
import java.util.Random;

/* Генератор паролей
Реализуйте логику метода getPassword, который должен возвращать ByteArrayOutputStream, в котором будут байты пароля.
Требования к паролю:
1) 8 символов
2) только цифры и латинские буквы разного регистра
3) обязательно должны присутствовать цифры, и буквы разного регистра
Все сгенерированные пароли должны быть уникальные.
Пример правильного пароля:
wMh7SmNu
*/
public class Solution {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++)
        {
            ByteArrayOutputStream password = getPassword();
            System.out.println(password.toString());
        }
    }

    public static ByteArrayOutputStream getPassword() {
        int SIZE = 8;
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        Random random = new Random();
        int lowCaseFrom = 97, upCaseFrom = 65, digitsFrom = 48;
        int lowCaseTo = 122, upCaseTo = 90, digitsTo = 57;

        while (os.size() < SIZE) {
            int mode = random.nextInt(3);
            char nextChar = (char)48;
            switch (mode) {
                case 0:
                    nextChar = (char) (lowCaseFrom + random.nextInt(lowCaseTo-lowCaseFrom + 1));
                    break;
                case 1:
                    nextChar = (char) (upCaseFrom + random.nextInt(upCaseTo-upCaseFrom + 1));
                    break;
                case 2:
                    nextChar = (char) (digitsFrom + random.nextInt(digitsTo-digitsFrom + 1));
                    break;
            }

            os.write(nextChar);
        }

        String password = os.toString();
        boolean correctLength = password.matches(".{8}");
        boolean hasDigit = password.matches(".*[0-9]+.*");
        boolean hasLowCase = password.matches(".*[a-z]+.*");
        boolean hasUpCase = password.matches(".*[A-Z]+.*");

        if (!(correctLength && hasDigit && hasLowCase && hasUpCase)) {
            os = getPassword();
        }

        return os;
    }
}

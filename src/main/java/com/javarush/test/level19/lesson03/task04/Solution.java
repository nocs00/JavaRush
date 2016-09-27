package com.javarush.test.level19.lesson03.task04;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/* И еще один адаптер
Адаптировать Scanner к PersonScanner.
Классом-адаптером является PersonScannerAdapter.
Данные в файле хранятся в следующем виде:
Иванов Иван Иванович 31 12 1950

В файле хранится большое количество людей, данные одного человека находятся в одной строке. Метод read() должен читать данные одного человека.
*/

public class Solution {
    public static class PersonScannerAdapter implements PersonScanner {
        private Scanner scanner;
        public PersonScannerAdapter(Scanner scanner) {
            this.scanner = scanner;
        }

        @Override
        public Person read() throws IOException
        {
            if (this.scanner.hasNext()) {
                String personInfo = this.scanner.nextLine();
                String firstName = personInfo.split(" ")[1];
                String middleName = personInfo.split(" ")[2];
                String lastName = personInfo.split(" ")[0];
                DateFormat format = new SimpleDateFormat("dd MM yyyy", Locale.ENGLISH);
                Date birthDate = null;
                try {
                    birthDate = format.parse(personInfo.split(" ")[3] + " " + personInfo.split(" ")[4] + " " + personInfo.split(" ")[5]);
                } catch (ParseException e) {
                    birthDate = new Date();
                }
                Person person = new Person(firstName, middleName, lastName, birthDate);
                return person;
            }
            return null;
        }

        @Override
        public void close() throws IOException
        {
            this.scanner.close();
        }
    }

//    public static void main(String[] args) {
//        String filePath = "c:/users/pdudenkov/test.txt";
//        try {
//            Scanner scanner = new Scanner(new File(filePath), "UTF-8");
//            PersonScannerAdapter personScannerAdapter = new PersonScannerAdapter(scanner);
//            ArrayList<Person> persons = new ArrayList<Person>();
//            while (scanner.hasNext()) {
//                Person t = personScannerAdapter.read();
//                persons.add(t);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}

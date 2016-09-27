package com.javarush.test.level20.lesson02.task02;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* Читаем и пишем в файл: JavaRush
Реализуйте логику записи в файл и чтения из файла для класса JavaRush
В файле your_file_name.tmp может быть несколько объектов JavaRush
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("your_file_name", ".txt", new File("c://users//pdudenkov//"));
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            User u = new User();
            u.setFirstName("Ivan"); u.setLastName("Baranov"); u.setCountry(User.Country.UKRAINE); u.setBirthDate(new Date()); u.setMale(true);
            User u2 = new User();
            u2.setFirstName("Ivana"); u2.setLastName("Baranova"); u2.setCountry(User.Country.RUSSIA); u2.setBirthDate(new Date(new Date().getTime()-200010l)); u2.setMale(false);
            javaRush.users.add(u);
            javaRush.users.add(u2);
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны

            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            PrintWriter pw = new PrintWriter(outputStream);
            pw.println(users.size());
            for (User u : users) {
                String tmpUser = u.getFirstName()+","+u.getLastName()+","+u.getCountry().getDisplayedName()+","+u.getBirthDate().getTime()+","+(u.isMale()?"m":"f");
                pw.println(tmpUser);
            }
            pw.flush();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            int size = Integer.parseInt(reader.readLine());
            for (int i = 0; i < size; i++) {
                String user = reader.readLine();
                User u = new User();
                u.setFirstName(user.split(",")[0]);
                u.setLastName(user.split(",")[1]);
                switch (user.split(",")[2]) {
                    case "Ukraine":
                        u.setCountry(User.Country.UKRAINE);
                        break;
                    case "Russia":
                        u.setCountry(User.Country.RUSSIA);
                        break;
                    case "Other":
                        u.setCountry(User.Country.OTHER);
                        break;
                }
                Date date = new Date(Long.parseLong(user.split(",")[3]));
                u.setBirthDate(date);
                u.setMale(user.split(",")[4].equals("m")?true:false);
                users.add(u);
            }
        }
    }
}

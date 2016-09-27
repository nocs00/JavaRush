package com.javarush.test.level20.lesson02.task05;

import java.io.*;

/* И еще раз о синхронизации
Разберитесь почему не работает метод main()
Реализуйте логику записи в файл и чтения из файла для класса Object
Метод load должен инициализировать объект данными из файла
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(java.lang.String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("your_file_name", ".txt", new File("c://users//pdudenkov"));
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            Object object = new Object();
            object.string1 = new Solution.String();   //string #1
            object.string2 = new Solution.String();   //string #2
            System.out.println("Before save: "+object.string1.number + " , " + object.string2.number);
            object.save(outputStream);
            outputStream.flush();

            Object loadedObject = new Object();
            loadedObject.string1 = new Solution.String(); //string #3
            loadedObject.string2 = new Solution.String(); //string #4


            System.out.println("countStrings before load: "+countStrings);
            loadedObject.load(inputStream);
            //check here that the object variable equals to loadedObject - проверьте тут, что object и loadedObject равны
            System.out.println("After load: "+loadedObject.string1.number + " , " + loadedObject.string2.number);
            System.out.println("countStrings after load: "+countStrings);

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }


    public static class Object {
        public Solution.String string1;
        public Solution.String string2;

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            PrintStream consoleStream = System.out;
            PrintStream currentStream = new PrintStream(outputStream);
            System.setOut(currentStream);
            string1.print();
            string2.print();
            System.setOut(consoleStream);
            currentStream.flush();
            currentStream.close();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader loader = new BufferedReader(new InputStreamReader(inputStream));

            int tmp = countStrings;
            countStrings = Integer.parseInt(loader.readLine().split("#")[1]);
            countStrings--;
            string1 = new Solution.String();

            countStrings = Integer.parseInt(loader.readLine().split("#")[1]);
            countStrings--;
            string2 = new Solution.String();

            countStrings = tmp;

            loader.close();
        }
    }

    public static int countStrings;

    public static class String {
        private final int number;

        public String() {
            number = ++countStrings;
        }

        public void print() {

            System.out.println("string #"+number);
        }
    }
}

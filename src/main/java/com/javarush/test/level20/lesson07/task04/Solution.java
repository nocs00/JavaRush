package com.javarush.test.level20.lesson07.task04;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* Serializable Solution
Сериализуйте класс Solution.
Подумайте, какие поля не нужно сериализовать, пометить ненужные поля — transient.
Объект всегда должен содержать актуальные итоговые данные.
Метод main не участвует в тестировании.
Написать код проверки самостоятельно в методе main:
1) создать файл, открыть поток на чтение (input stream) и на запись(output stream)
2) создать экземпляр класса Solution - savedObject
3) записать в поток на запись savedObject (убедитесь, что они там действительно есть)
4) создать другой экземпляр класса Solution с другим параметром
5) загрузить из потока на чтение объект - loadedObject
6) проверить, что savedObject.string равна loadedObject.string
7) обработать исключения
*/
public class Solution implements Serializable {
    public static void main(String[] args) {
        File file = new File("c://users//pdudenkov//test_level20_lesson07_task04");

        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        Solution savedObject= new Solution(4);

        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(savedObject);
        } catch (FileNotFoundException e) {
            return;
        } catch (IOException e)  {
            return;
        } finally
        {
            try
            {
                oos.flush();
                oos.close();
            } catch (IOException e) {

            }
        }

        Solution loadedObject;

        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            loadedObject = (Solution)ois.readObject();
        } catch (FileNotFoundException e) {
            return;
        } catch (IOException e)  {
            return;
        } catch (ClassNotFoundException e) {
            return;
        }
        finally
        {
            try
            {
                ois.close();
            } catch (IOException e) {

            }
        }

        System.out.println(savedObject.string.equals(loadedObject.string));
    }

    transient private final String pattern = "dd MMMM yyyy, EEEE";
    transient private Date currentDate;
    String string;

    transient private int temperature;
    private static final long serialVersionUID = 42L;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}

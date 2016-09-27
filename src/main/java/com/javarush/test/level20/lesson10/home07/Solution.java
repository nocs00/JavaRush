package com.javarush.test.level20.lesson10.home07;

import java.io.*;

/* Переопределение сериализации в потоке
Сериализация/десериализация Solution не работает.
Исправьте ошибки не меняя сигнатуры методов и класса.
Метод main не участвует в тестировании.
Написать код проверки самостоятельно в методе main:
1) создать экземпляр класса Solution
2) записать в него данные  - writeObject
3) сериализовать класс Solution  - writeObject(ObjectOutputStream out)
4) десериализовать, получаем новый объект
5) записать в новый объект данные - writeObject
6) проверить, что в файле есть данные из п.2 и п.5
*/
public class Solution implements Serializable, AutoCloseable {
    private FileOutputStream stream;
    private String path;

    public Solution() {

    }

    public Solution(String fileName) throws FileNotFoundException {
        this.stream = new FileOutputStream(fileName, true);
        this.path = fileName;
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(path);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        this.path = (String)in.readObject();
        this.stream = new FileOutputStream(this.path, true);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) throws Exception
    {
        Solution s = new Solution("c://users//pdudenkov//test.level20.lesson10.home07_stream");
        s.writeObject("some string");
        s.close();

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("c://users//pdudenkov//test.level20.lesson10.home07"));
        oos.writeObject(s);
        oos.flush();
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("c://users//pdudenkov//test.level20.lesson10.home07"));
        Solution s2 = (Solution)ois.readObject();
        s2.writeObject("deserialized");
        ois.close();
        s2.close();
    }
}

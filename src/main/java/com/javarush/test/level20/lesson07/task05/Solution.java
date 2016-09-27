package com.javarush.test.level20.lesson07.task05;

import java.io.*;

/* Переопределение сериализации
Сделайте так, чтобы после десериализации нить runner продолжила работать.
Ключевые слова объекта runner менять нельзя.
Hint/Подсказка:
Конструктор не вызывается при сериализации, только инициализируются все поля.
*/
public class Solution implements Serializable, Runnable {
    transient private Thread runner;
    private int speed;

    public Solution(int speed) {
        this.speed = speed;
        runner = new Thread(this);
        runner.start();
    }

    public void run() {
        // do something here, does not matter
        while(true)
        try
        {
            Thread.sleep(1000l);
            System.out.println("hello "+ speed++);
        } catch (Exception e) {

        }
    }

    /**
     Переопределяем сериализацию.
     Для этого необходимо объявить методы:
     private void writeObject(ObjectOutputStream out) throws IOException
     private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
     Теперь сериализация/десериализация пойдет по нашему сценарию :)
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        //out.defaultWriteObject();
        out.writeInt(speed);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        //in.defaultReadObject();
        this.speed = in.readInt();
        runner = new Thread(this);
        runner.start();
    }

    public static void main(String[] args) throws Exception
    {
        File file = new File("c://users//pdudenkov//test_level20_lesson07_task05.txt");
        Solution s1 = new Solution(5);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(s1);
        oos.flush();
        oos.close();


        try
        {
            Thread.sleep(10000l);
            System.out.println("Deseriaze.");
        } catch (Exception e) {

        }

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        s1 = (Solution)ois.readObject();
    }
}

package com.javarush.test.level20.lesson07.task03;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* Externalizable Person
Класс Person должен сериализоваться с помощью интерфейса Externalizable.
Подумайте, какие поля не нужно сериализовать.
Исправьте ошибку сериализации.
Сигнатуры методов менять нельзя.
*/
public class Solution {

    public static void main(String[] args) throws Exception
    {
        Person p1 = new Person("kuku", "dudenkov", 25);
        p1.setFather(new Person());
        p1.setMother(new Person());
        List<Person> childs = new ArrayList<>();
        childs.add( new Person("child1", "dudenkov", 3));
        childs.add( new Person("child2", "dudenkov", 2));
        childs.add( new Person("child3", "dudenkov", 1));
        p1.setChildren(childs);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("c://users//pdudenkov//test_level20_lesson07_task03.txt"));
        oos.writeObject(p1);
        oos.flush();
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("c://users//pdudenkov//test_level20_lesson07_task03.txt"));
        Person p2 = (Person)ois.readObject();
    }

    public static class Person implements Externalizable {
        private String firstName;
        private String lastName;
        private int age;
        private Person mother;
        private Person father;
        private List<Person> children;

        public Person() {

        }

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public void setMother(Person mother) {
            this.mother = mother;
        }

        public void setFather(Person father) {
            this.father = father;
        }

        public void setChildren(List<Person> children) {
            this.children = children;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeInt(firstName != null ? 1:0);
            if (firstName != null)
                out.writeObject(firstName);

            out.writeInt(lastName != null ? 1:0);
            if (lastName != null)
                out.writeObject(lastName);

            out.writeInt(age);

            out.writeInt(mother != null ? 1:0);
            if (mother != null)
                out.writeObject(mother);

            out.writeInt(father != null ? 1:0);
            if (father != null)
                out.writeObject(father);

            out.writeInt(children != null ? 1:0);
            if (children != null)
                out.writeObject(children);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            int status;

            status = in.readInt();
            if (status == 1)
                this.firstName = (String)in.readObject();

            status = in.readInt();
            if (status == 1)
                this.lastName = (String)in.readObject();

            this.age = in.readInt();

            status = in.readInt();
            if (status == 1)
                this.mother = (Person)in.readObject();

            status = in.readInt();
            if (status == 1)
                this.father = (Person)in.readObject();

            status = in.readInt();
            if (status == 1)
                this.children = (List<Person>)in.readObject();
        }
    }
}

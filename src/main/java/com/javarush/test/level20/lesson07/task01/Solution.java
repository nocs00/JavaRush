//package com.javarush.test.level20.lesson07.task01;
//
//import java.io.*;
//
///* Externalizable для апартаментов
//Реализуйте интерфейс Externalizable для класса Apartment
//Подумайте, какие поля не нужно сериализовать.
//*/
//public class Solution {
//
//    public static void main(String[] args) throws Exception
//    {
//        Apartment a = new Apartment("class", 2);
//        a.writeExternal(new ObjectOutputStream(new FileOutputStream("c://users//pdudenkov//test_level20_lesson07_task01.txt")));
//
//        Apartment b = new Apartment();
//        b.readExternal(new ObjectInputStream(new FileInputStream("c://users//pdudenkov//test_level20_lesson07_task01.txt")));
//    }
//
//    public static class Apartment implements Externalizable {
//
//        private static final long serialVersionUID = 1L;
//        private String address;
//        private int year;
//
//        /**
//         * Mandatory public no-arg constructor.
//         */
//        public Apartment() { super(); }
//
//        public Apartment(String adr, int y) {
//            address = adr;
//            year = y;
//        }
//
//        /**
//         * Prints out the fields. used for testing!
//         */
//        public String toString() {
//            return("Address: " + address + "\n" + "Year: " + year);
//        }
//
//        @Override
//        public void writeExternal(ObjectOutput objectOutput) throws IOException
//        {
//            objectOutput.writeObject(this.toString());
//        }
//
//        @Override
//        public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException
//        {
//            String read = ((String)objectInput.readObject());
//            address = read.split("Address: ")[1].split("Year: ")[0];
//            address = address.substring(0, address.length()-1);
//            year = Integer.parseInt(read.split("Year: ")[1]);
//        }
//    }
//}

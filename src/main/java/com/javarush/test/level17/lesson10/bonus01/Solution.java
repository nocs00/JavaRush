//package com.javarush.test.level17.lesson10.bonus01;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//
///* CRUD
//CrUD - Create, Update, Delete
//Программа запускается с одним из следующих наборов параметров:
//-c name sex bd
//-u id name sex bd
//-d id
//-i id
//Значения параметров:
//name - имя, String
//sex - пол, "м" или "ж", одна буква
//bd - дата рождения в следующем формате 15/04/1990
//-c  - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
//-u  - обновляет данные человека с данным id
//-d  - производит логическое удаление человека с id
//-i  - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)
//
//id соответствует индексу в списке
//Все люди должны храниться в allPeople
//Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat
//
//Пример параметров: -c Миронов м 15/04/1990
//*/
//
//public class Solution {
//    public static List<Person> allPeople = new ArrayList<Person>();
//    static {
//        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
//        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
//    }
//
//    public static void main(String[] args) throws ParseException
//    {
//        //start here - начни тут
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
//        if (args.length <= 1) {
//            return;
//        }
//        else if (args[0].equals("-c")) {
//            if (args.length != 4) return;
//            if (args[2].equals("м")) {
//                create(Person.createMale(args[1], dateFormat.parse(args[3])));
//            }
//            else if (args[2].equals("ж")) {
//                create(Person.createFemale(args[1], dateFormat.parse(args[3])));
//            }
//
//        }
//        else if (args[0].equals("-u")) {
//            if (args.length != 5) return;
//            if (args[3].equals("м")) {
//                update(Integer.parseInt(args[1]), Person.createMale(args[2], dateFormat.parse(args[4])));
//            }
//            else if (args[3].equals("ж")) {
//                update(Integer.parseInt(args[1]), Person.createFemale(args[2], dateFormat.parse(args[4])));
//            }
//        }
//        else if (args[0].equals("-d")) {
//            if (args.length != 2) return;
//            delete(Integer.parseInt(args[1]));
//        }
//        else if (args[0].equals("-i")) {
//            if (args.length != 2) return;
//            info(Integer.parseInt(args[1]));
//        }
//    }
//
//    public static void create(Person createPerson) {
//        allPeople.add(createPerson);
//        System.out.println(allPeople.indexOf(createPerson));
//    }
//
//    public static void update(int id, Person updatePerson) {
//        allPeople.set(id, updatePerson);
//    }
//
//    public static void delete(int id) {
//        Person person = allPeople.get(id);
//        person.setName(null);
//        person.setSex(null);
//        person.setBirthDay(null);
//    }
//
//    public static void info(int id) {//-i  - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
//        Person person = allPeople.get(id);
//        System.out.println(person.getName() + " " + (person.getSex().equals(Sex.MALE) ? "м" : "ж") + " " + dateFormat.format(person.getBirthDay()));
//    }
//}

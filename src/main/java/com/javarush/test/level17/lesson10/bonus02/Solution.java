package com.javarush.test.level17.lesson10.bonus02;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD 2
CrUD Batch - multiple Creation, Updates, Deletion
!!!РЕКОМЕНДУЕТСЯ выполнить level17.lesson10.bonus01 перед этой задачей!!!

Программа запускается с одним из следующих наборов параметров:
-c name1 sex1 bd1 name2 sex2 bd2 ...
-u id1 name1 sex1 bd1 id2 name2 sex2 bd2 ...
-d id1 id2 id3 id4 ...
-i id1 id2 id3 id4 ...
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-с  - добавляет всех людей с заданными параметрами в конец allPeople, выводит id (index) на экран в соответствующем порядке
-u  - обновляет соответствующие данные людей с заданными id
-d  - производит логическое удаление всех людей с заданными id
-i  - выводит на экран информацию о всех людях с заданными id: name sex bd

id соответствует индексу в списке
Формат вывода даты рождения 15-Apr-1990
Все люди должны храниться в allPeople
Порядок вывода данных соответствует вводу данных
Обеспечить корректную работу с данными для множества нитей (чтоб не было затирания данных)
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException
    {
        //start here - начни тут
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        if (args.length <= 1) {
            return;
        }
        else if (args[0].equals("-c")) {
            if ((args.length-1)%3 != 0) return;
            int count = (args.length-1)/3;
            for (int i = 0; i < count; i++) {
                if (args[(i*3)+2].equals("м")) {
                    create(Person.createMale(args[(i*3)+1], dateFormat.parse(args[(i*3)+3])));
                }
                else if (args[(i*3)+2].equals("ж")) {
                    create(Person.createFemale(args[(i * 3) + 1], dateFormat.parse(args[(i * 3) + 3])));
                }
            }
        }
        else if (args[0].equals("-u")) { //-u id1 name1 sex1 bd1 id2 name2 sex2 bd2 ...
            if ((args.length-1)%4 != 0) return;
            int count = (args.length-1)/4;
            for (int i = 0; i < count; i++) {
                if (args[(i*4)+3].equals("м")) {
                    update(Integer.parseInt(args[(i*4)+1]), Person.createMale(args[(i*4)+2], dateFormat.parse(args[(i*4)+4])));
                }
                else if (args[(i*4)+3].equals("ж")) {
                    update(Integer.parseInt(args[(i*4)+1]), Person.createFemale(args[(i * 4) + 2], dateFormat.parse(args[(i * 4) + 4])));
                }
            }
        }
        else if (args[0].equals("-d")) {
            for (int i = 0; i < args.length-1; i++) {
                delete(Integer.parseInt(args[i+1]));
            }
        }
        else if (args[0].equals("-i")) {
            for (int i = 0; i < args.length-1; i++) {
                info(Integer.parseInt(args[i + 1]));
            }
        }
    }

    public static void create(Person createPerson) {
        allPeople.add(createPerson);
        System.out.println(allPeople.indexOf(createPerson));
    }

    public static void update(int id, Person updatePerson) {
        allPeople.set(id, updatePerson);
    }

    public static void delete(int id) {
        Person person = allPeople.get(id);
        person.setName(null);
        person.setSex(null);
        person.setBirthDay(null);
    }

    public static void info(int id) {//-i  - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        Person person = allPeople.get(id);
        System.out.println(person.getName() + " " + (person.getSex().equals(Sex.MALE) ? "м" : "ж") + " " + dateFormat.format(person.getBirthDay()));
    }
}
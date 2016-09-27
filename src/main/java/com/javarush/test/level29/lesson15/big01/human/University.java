package com.javarush.test.level29.lesson15.big01.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private List<Student> students = new ArrayList<>();
    private int age;
    private String name;

    public List<Student> getStudents()
    {
        return this.students;
    }

    public void setStudents(List<Student> students)
    {
        this.students = students;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public University(String name, int age) {
        this.name = name;
        this.age = age;

    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        for (Student student : students)
        {
            if (student.getAverageGrade() == averageGrade) {
                return student;
            }
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        double maxAverageGrade = -1f;
        int maxIndex = -1;
        for (int i = 0; i < students.size(); i++)
        {
            Student student = students.get(i);
            if (student.getAverageGrade() > maxAverageGrade) {
                maxAverageGrade = student.getAverageGrade();
                maxIndex = i;
            }
        }

        if (maxIndex >= 0) return students.get(maxIndex);
        else return null;
    }

    public Student getStudentWithMinAverageGrade() {
        double minAverageGrade = Double.MAX_VALUE;
        int minIndex = students.size()+1;
        for (int i = 0; i < students.size(); i++)
        {
            Student student = students.get(i);
            if (student.getAverageGrade() < minAverageGrade) {
                minAverageGrade = student.getAverageGrade();
                minIndex = i;
            }
        }

        if (minIndex >= 0 && minIndex < students.size()) return students.get(minIndex);
        else return null;
    }

    public void expel(Student student) {
        students.remove(student);
    }
}

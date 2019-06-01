package ru.raiffeisen.reflection.getclass;

import ru.raiffeisen.Utils;
import ru.raiffeisen.reflection.Person;
import ru.raiffeisen.reflection.Student;

public class Main {
    public static void main(String[] args) {
        Person student = new Student("John", 25);
        System.out.println(student.getClass());
        System.out.println(Person.class);
        System.out.println(Student.class);
        Utils.delimeter();
        System.out.println(Person.class.equals(student.getClass()));
        Utils.delimeter();
        System.out.println(Student.class.equals(student.getClass()));
    }
}

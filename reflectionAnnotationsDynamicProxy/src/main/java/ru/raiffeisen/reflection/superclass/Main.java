package ru.raiffeisen.reflection.superclass;

import ru.raiffeisen.reflection.Person;
import ru.raiffeisen.reflection.Student;

import java.util.Arrays;

import static ru.raiffeisen.Utils.delimeter;

public class Main {
    public static void main(String[] args) {
        Person student = new Student("John", 25);
        delimeter("Возвращает класс родителя");
        System.out.println(student.getClass().getSuperclass());
        delimeter("Возвращает интерфейсы");
        Arrays.asList(student.getClass().getInterfaces()).forEach(System.out::println);
        delimeter("Возвращает класс родителя - String.class");
        System.out.println(String.class.getSuperclass());
        delimeter("Возвращает класс родителя - Object.class");
        System.out.println(Object.class.getSuperclass());
    }
}

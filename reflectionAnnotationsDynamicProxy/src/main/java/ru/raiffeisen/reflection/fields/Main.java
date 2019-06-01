package ru.raiffeisen.reflection.fields;

import ru.raiffeisen.Utils;
import ru.raiffeisen.reflection.Person;
import ru.raiffeisen.reflection.Student;

import java.util.Arrays;

import static ru.raiffeisen.Utils.delimeter;

public class Main {
    public static void main(String[] args) {
        Person student = new Student("John", 25);
        //Список всех public полей, объявленных в классе или унаследованных
        delimeter("Список всех public полей, объявленных в классе или унаследованных");
        Arrays.asList(student.getClass().getFields()).forEach(System.out::println);
        //Список всех полей, объявленных в классе
        delimeter("Список всех полей, объявленных в классе");
        Arrays.asList(student.getClass().getDeclaredFields()).forEach(System.out::println);
        //Поле по имени
        delimeter("Поле по имени");
        try {
            System.out.println(student.getClass().getField("id"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        //Поле, объявленное в классе
        delimeter("Поле, объявленное в классе");
        try {
            System.out.println(student.getClass().getDeclaredField("id"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}

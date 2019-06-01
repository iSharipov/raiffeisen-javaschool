package ru.raiffeisen.reflection.methods;

import ru.raiffeisen.reflection.Person;
import ru.raiffeisen.reflection.Student;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import static ru.raiffeisen.Utils.delimeter;

/**
 * Methods
 */
public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Person student = new Student("John", 25);
        delimeter("Список всех public методов, объявленных в классе или унаследованных");
        //Список всех public методов, объявленных в классе или унаследованных
        Arrays.asList(student.getClass().getMethods()).forEach(System.out::println);
        delimeter("Список всех методов, объявленных в классе");
        //Список всех методов, объявленных в классе
        Arrays.asList(student.getClass().getDeclaredMethods()).forEach(System.out::println);
        delimeter("Метод с заданным именем и аргументами");
        //Метод с заданным именем и аргументами
        //NoSuchMethodException
        Method getName = Student.class.getMethod("getName", String.class);
        System.out.println(getName);
        delimeter("Вызов метода с аргументами");
        //InvocationTargetException, IllegalAccessException
        System.out.println(getName.invoke(student, " Doe"));
        delimeter("Вызов приватного метода");
        Method getStartDate = student.getClass().getDeclaredMethod("dateFormatter", Date.class);
        getStartDate.setAccessible(true);
        System.out.println(getStartDate.invoke(student, new Date()));
    }
}

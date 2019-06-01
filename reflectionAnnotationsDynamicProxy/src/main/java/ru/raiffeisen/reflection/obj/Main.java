package ru.raiffeisen.reflection.obj;

import ru.raiffeisen.reflection.Student;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        try {
            Student studentEmptyArgConstructorFromReflection = Student.class.newInstance();
            System.out.println(studentEmptyArgConstructorFromReflection.getName());
            System.out.println(studentEmptyArgConstructorFromReflection.getAge());
            Student studentArgConstructorFromReflection = Student.class.getDeclaredConstructor(String.class, Integer.class).newInstance("Doe", 15);
            System.out.println(studentArgConstructorFromReflection.getName());
            System.out.println(studentArgConstructorFromReflection.getAge());
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

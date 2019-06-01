package ru.raiffeisen.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainEntryClass {

    public static boolean isDefault(Method method) {
        final int SYNTHETIC = 0x00001000;
        return ((method.getModifiers()
                & (Modifier.ABSTRACT | Modifier.PUBLIC | Modifier.STATIC | SYNTHETIC)) == Modifier.PUBLIC)
                && method.getDeclaringClass().isInterface();
    }

    public static void main(String[] args) {
        Person student = new Student("Петр", 25);
        Class<Person> personClass = Person.class;
        System.out.println(Person.class.equals(student.getClass()));

        //Reflection
        Class<? extends Person> studentClass = student.getClass();


        System.out.println("+++++++++Class name+++++++++");
        System.out.println(studentClass.getName());

        System.out.println("+++++++++Class modifiers+++++++++");
        System.out.println(studentClass.getModifiers());

        //Only public + of all its superclasses
        System.out.println("+++++++++Fields+++++++++");
        Arrays.stream(studentClass.getFields()).forEach(field -> System.out.println(field.getName()));

        //All declared fields, excludes inherited fields
        System.out.println("+++++++++Declared Fields+++++++++");
        Arrays.stream(studentClass.getDeclaredFields()).forEach(field -> System.out.println(field.getName()));

        //All declared fields + of all its superclasses
        System.out.println("+++++++++All Declared Fields+++++++++");
        getAllFields(student).forEach(System.out::println);

        //Only public
        System.out.println("+++++++++Methods+++++++++");
        Arrays.stream(studentClass.getMethods()).forEach(method -> System.out.println(method.getName()));

        //All declared excluding inherited method
        System.out.println("+++++++++All Declared Methods+++++++++");
        Arrays.stream(studentClass.getDeclaredMethods()).forEach(method -> System.out.println(method.getName()));

        //NPE with non enum
        System.out.println("+++++++++Constants+++++++++");
//        Arrays.stream(employeeClass.getEnumConstants()).forEach(constant -> System.out.println(constant.getName()));

        System.out.println("+++++++++Constructors+++++++++");
        Arrays.stream(studentClass.getDeclaredConstructors()).forEach(constructor -> System.out.println(constructor.getName() + " " + Arrays.toString(constructor.getParameters())));

        System.out.println("+++++++++Superclasses+++++++++");
        System.out.println(studentClass.getSuperclass().getName());

        System.out.println("+++++++++Interfaces+++++++++");
        Arrays.stream(studentClass.getInterfaces()).forEach(_interface -> System.out.println(_interface.getName()));

        System.out.println("+++++++++Object creation+++++++++");
        //Create object
        try {
            Student studentEmptyArgConstructorFromReflection = Student.class.newInstance();
            System.out.println(studentEmptyArgConstructorFromReflection.getName());
            System.out.println(studentEmptyArgConstructorFromReflection.getAge());
            Student studentArgConstructorFromReflection = Student.class.getDeclaredConstructor(String.class, Integer.class).newInstance("Иван", 15);
            System.out.println(studentArgConstructorFromReflection.getName());
            System.out.println(studentArgConstructorFromReflection.getAge());
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println("+++++++++Set object field+++++++++");
        Field firstField = studentClass.getDeclaredFields()[0];
        System.out.println(firstField.getName());
        try {
            System.out.println(studentClass.getMethod("getGrade").invoke(student));
            firstField.setAccessible(true);
            firstField.set(student, "5");
            // Get field state through get method
            System.out.println(firstField.get(student));
            // Invoke getter if present - NoSuchMethodException
            System.out.println(studentClass.getMethod("getGrade").invoke(student));
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println("+++++++++Superclass+++++++++");
        System.out.println(String.class.getSuperclass());
        System.out.println(Object.class.getSuperclass());

        System.out.println("+++++++++Hierarchy+++++++++");
        printHierarchy(studentClass);
    }

    public static <T> List<Field> getAllFields(T t) {
        List<Field> fields = new ArrayList<>();
        Class clazz = t.getClass();
        while (clazz != Object.class) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fields;
    }

    public static <T> List<Method> getAllMethods(T t) {
        List<Method> methods = new ArrayList<>();
        Class clazz = t.getClass();
        while (clazz != Object.class) {
            methods.addAll(Arrays.asList(clazz.getDeclaredMethods()));
            clazz = clazz.getSuperclass();
        }
        return methods;
    }

    private static void printHierarchy(Class<?> clazz) {
        while (clazz != null) {
            System.out.println(clazz);
            clazz = clazz.getSuperclass();
        }
    }

}
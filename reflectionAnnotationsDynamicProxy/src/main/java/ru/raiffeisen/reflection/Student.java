package ru.raiffeisen.reflection;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Student extends PersonBase implements Studying {

    private String grade;
    private String id;
    private Date startDate;

    public Student() {
        super("John", 12);
    }

    public Student(String name, Integer age) {
        this(name, age, new Date());
    }

    public Student(String name, Integer age, Date startDate) {
        super(name, age);
        this.startDate = startDate;
    }

    public Student(String name, Integer age, String grade, Date startDate) {
        this(name, age, startDate);
        this.grade = grade;
    }


    public Student(String name, Integer age, String grade, String id) {
        this(name, age, grade, new Date());
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public String getId() {
        return id;
    }

    public String getStartDate() {
        return dateFormatter(this.startDate);
    }

    public String getName(String name, Integer age) {
        return name;
    }

    public String getName(String lastName) {
        return name + lastName;
    }

    @Override
    public Integer getAge() {
        return age - 10;
    }

    @Override
    public String studyFor() {
        return "JAVA";
    }

    private void smth() {
        //
    }

    private String dateFormatter(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}
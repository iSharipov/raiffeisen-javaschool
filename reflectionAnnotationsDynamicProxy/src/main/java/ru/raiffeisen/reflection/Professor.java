package ru.raiffeisen.reflection;

public class Professor extends PersonBase implements Teaching {
    //public class Professor extends PersonBase implements Person {
    private String courseTitle;
    private String title;

    public Professor(String name, Integer age, String courseTitle, String title) {
        super(name, age);
        this.courseTitle = courseTitle;
        this.title = title;
    }

    @Override
    public String getName() {
        return "Prof: " + super.getName();
    }

    @Override
    public String getCourseTitle() {
        return courseTitle;
    }
}
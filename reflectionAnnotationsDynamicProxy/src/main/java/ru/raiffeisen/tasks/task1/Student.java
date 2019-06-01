package ru.raiffeisen.tasks.task1;

import java.util.Date;

public class Student extends Person {

    private static final String MONDAY = "MONDAY";

    private final String fieldOfStudy;

    public Student(String name, String surname, Date dateOfBirth, String fieldOfStudy) {
        super(name, surname, dateOfBirth);
        this.fieldOfStudy = fieldOfStudy;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }
}

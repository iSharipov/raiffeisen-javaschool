package ru.raiffeisen.tasks.task1;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {

    private final String name;
    private final String surname;
    private final Date dateOfBirth;

    public Person(String name, String surname, Date dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDateOfBirth() {
        return format(dateOfBirth);
    }

    public String getFullName() {
        return this.name + ' ' + this.surname;
    }

    private String format(Date date) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }
}

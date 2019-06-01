package ru.raiffeisen.reflection;

public abstract class PersonBase implements Person {
    protected String name;
    protected Integer age;

    public PersonBase(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getAge() {
        return age;
    }

}
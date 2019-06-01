package ru.raiffeisen.proxy;

import java.util.concurrent.atomic.AtomicLong;

public class Person {

    private static AtomicLong idGenerator = new AtomicLong(0);

    private final long id;
    private final String name;
    private final String sirname;

    public Person(String name, String sirname) {
        id = idGenerator.getAndAdd(1);
        this.name = name;
        this.sirname = sirname;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSirname() {
        return sirname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sirname='" + sirname + '\'' +
                '}';
    }
}
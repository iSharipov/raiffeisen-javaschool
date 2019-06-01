package ru.raiffeisen.proxy;

import java.util.ArrayList;
import java.util.List;

public class PersonRepository implements Repository<Person> {

    @Cache
    @Override
    public Person byId(long id) {
        return new Person("John", "Doe");
    }

    @Cache
    @Override
    public List<Person> findAll() {
        return new ArrayList<Person>(){{
           add(new Person("1", "2"));
           add(new Person("3", "4"));
        }};
    }
}

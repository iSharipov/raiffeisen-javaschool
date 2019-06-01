package ru.raiffeisen.proxy;

import java.lang.reflect.Proxy;
import java.net.MalformedURLException;

public class MainEntryClass {
    public static void main(String[] args) throws MalformedURLException {
        Repository<Person> personRepository = (Repository<Person>) Proxy.newProxyInstance(
                PersonRepository.class.getClassLoader(),
                PersonRepository.class.getInterfaces(),
                new CachedRepository(new PersonRepository()));

        System.out.println(personRepository.byId(1));
        System.out.println(personRepository.byId(1));

        System.out.println(personRepository.findAll());
        System.out.println(personRepository.findAll());
    }
}

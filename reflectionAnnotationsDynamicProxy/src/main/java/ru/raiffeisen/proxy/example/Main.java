package ru.raiffeisen.proxy.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        Person o =  (Person) Proxy.newProxyInstance(PersonImpl.class.getClassLoader(), PersonImpl.class.getInterfaces(), new PersonInvocationHandler(new PersonImpl()));
        o.getName();
    }
}

interface Person{
    String getName();
}

class PersonImpl implements Person{

    @Override
    public String getName() {
        return "John Doe";
    }
}

class PersonInvocationHandler implements InvocationHandler{

    private final Object person;

    public PersonInvocationHandler(Object target) {
        this.person = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before");
        Object result = method.invoke(person, args);
        System.out.println(result);
        System.out.println("After");
        return result;
    }
}
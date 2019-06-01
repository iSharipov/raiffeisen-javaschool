package ru.raiffeisen.reflection.generics;

import ru.raiffeisen.Utils;
import ru.raiffeisen.annotations.User;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException {
        User john = new User();
        john.setName("John");
        User doe = new User();
        doe.setName("Doe");

        UserEntityRepository userEntityRepository = new UserEntityRepository(Arrays.asList(john, doe));
        ParameterizedType genericSuperclass = (ParameterizedType) userEntityRepository.getClass().getGenericSuperclass();
        System.out.println(genericSuperclass.getActualTypeArguments()[0]);
        Utils.delimeter();
        ParameterizedType genericInterface = (ParameterizedType) userEntityRepository.getClass().getGenericInterfaces()[0];
        System.out.println(genericInterface.getActualTypeArguments()[0]);
        Utils.delimeter();
        System.out.println(userEntityRepository.getClass().getDeclaredField("users").getGenericType().getTypeName());
    }
}

abstract class UserDao<T> {

}

interface EntityRepository<T> {
    List<T> getUsers();
}

class UserEntityRepository extends UserDao<User> implements EntityRepository<User> {
    private List<User> users;

    public UserEntityRepository(List<User> users) {
        this.users = users;
    }

    @Override
    public List<User> getUsers() {
        return users;
    }
}
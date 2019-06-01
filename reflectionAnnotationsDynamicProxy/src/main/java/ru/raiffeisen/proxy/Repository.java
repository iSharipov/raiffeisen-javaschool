package ru.raiffeisen.proxy;

import java.util.List;

public interface Repository<T> {

    T byId(long id);

    List<T> findAll();
}

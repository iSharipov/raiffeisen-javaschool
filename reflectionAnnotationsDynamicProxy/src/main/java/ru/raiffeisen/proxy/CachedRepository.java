package ru.raiffeisen.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CachedRepository implements InvocationHandler {

    private final Repository repository;

    public CachedRepository(Repository repository) {
        this.repository = repository;
    }

    private final Map<Long, Person> byId = new ConcurrentHashMap<>();
    private final Map<Long, Person> all = new ConcurrentHashMap<>();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Cache cache = repository.getClass()
                .getMethod(method.getName(), method.getParameterTypes())
                .getAnnotation(Cache.class);

        if (cache != null) {
            if (args == null){
                if (!all.isEmpty()){
                    System.out.println("From cache");
                    return new ArrayList<>(all.values());
                }
                List<Person> persons = (List<Person>) method.invoke(repository, args);
                persons.forEach(person -> all.put(person.getId(), person));
                return persons;
            }
            if (args.length > 0 && args[0] instanceof Long) {
                Long id = (Long) args[0];
                if (byId.containsKey(id)) {
                    System.out.println("From cache");
                    return byId.get(id);
                } else {
                    Person person = (Person) method.invoke(repository, args);
                    byId.put(id, person);
                    return person;
                }
            }
        }
        return method.invoke(repository, args);
    }
}

package ru.raiffeisen.aspectj;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException {
        Map<Long, User> users = new HashMap<Long, User>() {{
            put(1L, new User("mkyong", "mkyong@gmail.com"));
            put(2L, new User("alex", "alex@yahoo.com"));
            put(3L, new User("joel", "joel@gmail.com"));
        }};

//        UserService userService = (UserService) Proxy.newProxyInstance(
//                UserServiceImpl.class.getClassLoader(),
//                UserServiceImpl.class.getInterfaces(),
//                new UserServiceInvocationHandler(new UserServiceImpl(users))
//        );

//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(UserService.class);
//        enhancer.setSuperclass(UserServiceImpl.class);
//        enhancer.setCallback(new UserServiceMethodInterceptor());
//        UserService userService = (UserService)enhancer.create();
//        UserService userService = (UserService) enhancer.create(UserServiceImpl.class.getConstructor(Map.class).getParameterTypes(), new Object[]{users});

        UserService userService = new UserServiceImpl(users);
        UserDTO userDTO = userService.byId(1);
        System.out.println("From main byId: " + userDTO);
        List<UserDTO> all = userService.findAll();
        System.out.println("From main findAll: " + all);
        userService.findAll();
        userService.findAll();
    }
}

class UserServiceInvocationHandler implements InvocationHandler {

    private static final ConcurrentHashMap<String, AtomicLong> counter = new ConcurrentHashMap<>(100);

    private final UserServiceImpl userService;

    UserServiceInvocationHandler(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return new CounterSupport(userService).count(method, args);
    }
}

class UserServiceMethodInterceptor implements MethodInterceptor {

    private final ConcurrentHashMap<String, AtomicLong> counter = new ConcurrentHashMap<>(100);

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if (method.isAnnotationPresent(Counter.class)) {
            if (counter.containsKey(method.getName())) {
                counter.get(method.getName()).incrementAndGet();
            } else {
                counter.put(method.getName(), new AtomicLong(1));
            }
            System.out.println(String.format("Method %s is called: %s times", method.getName(), counter.get(method.getName())));
        }
        return methodProxy.invokeSuper(o, objects);
    }
}

class CounterSupport {

    private final UserService userService;

    private static final ConcurrentHashMap<String, AtomicLong> counter = new ConcurrentHashMap<>(100);

    public CounterSupport(UserService userService) {
        this.userService = userService;
    }

    public Object count(Method method, Object[] objects) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (userService.getClass().getMethod(method.getName(), method.getParameterTypes()).isAnnotationPresent(Counter.class)) {
            if (counter.containsKey(method.getName())) {
                counter.get(method.getName()).incrementAndGet();
            } else {
                counter.put(method.getName(), new AtomicLong(1));
            }
            System.out.println(String.format("Method %s is called: %s times", method.getName(), counter.get(method.getName())));
        }
        return method.invoke(userService, objects);
    }
}
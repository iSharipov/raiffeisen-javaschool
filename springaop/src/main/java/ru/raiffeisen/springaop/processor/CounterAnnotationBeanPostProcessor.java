package ru.raiffeisen.springaop.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.raiffeisen.springaop.service.Counter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.aop.support.AopUtils.isCglibProxy;

@Order(1000)
@Component
public class CounterAnnotationBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private BeanFactory beanFactory;

    private Logger logger = LoggerFactory.getLogger(CounterAnnotationBeanPostProcessor.class.getName());

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Object proxiedBean;
        boolean isProxy = false;
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(bean);
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Counter.class)) {
                isProxy = true;
                break;
            }
        }
        if (!isProxy) {
            return bean;
        }

        Object proxy = new ProxyFactory(bean).getProxy();
        System.out.println(proxy);
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            if (targetClass.getMethod(method.getName(), method.getParameterTypes()).isAnnotationPresent(Counter.class)) {
                logger.info("From CounterAnnotationBeanPostProcessor before method call");
                Object result = method.invoke(bean, objects);
                logger.info("From CounterAnnotationBeanPostProcessor after method call");
                return result;
            }
            return method.invoke(bean, objects);
        });
        if (targetClass.isInterface() || (!isCglibProxy(bean) && bean.getClass().getInterfaces().length > 0)) {
            enhancer.setInterfaces(targetClass.getInterfaces());
            proxiedBean = enhancer.create();
        } else {
            enhancer.setSuperclass(targetClass);
            enhancer.setInterfaces(targetClass.getInterfaces());
            Constructor targetConstructor;
            try {
                targetConstructor = targetClass.getConstructor();
            } catch (NoSuchMethodException e) {
                targetConstructor = targetClass.getConstructors()[0];
            }
            List<Object> arguments = new ArrayList<>(targetConstructor.getParameterTypes().length);
            for (Class<?> parameterType : targetClass.getConstructors()[0].getParameterTypes()) {
                arguments.add(beanFactory.getBean(parameterType));
            }
            proxiedBean = enhancer.create(targetConstructor.getParameterTypes(), arguments.toArray());
        }
        return proxiedBean;
    }
}

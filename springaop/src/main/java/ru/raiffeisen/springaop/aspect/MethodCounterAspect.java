package ru.raiffeisen.springaop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
@Aspect
public class MethodCounterAspect {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(MethodCounterAspect.class.getName());

    private static final ConcurrentHashMap<String, AtomicLong> counter = new ConcurrentHashMap<>(100);

    @Around("execution(* ru.raiffeisen.springaop.service.UserService.byId(..)) || execution(* ru.raiffeisen.springaop.service.UserService.findAll(..))")
    public Object counterMethod(ProceedingJoinPoint pjp) throws Throwable {
        String name = pjp.getSignature().getName();
        if (counter.containsKey(name)) {
            counter.get(name).incrementAndGet();
        } else {
            counter.put(name, new AtomicLong(1));
        }
        logger.info("From MethodCounterAspect method call: " + counter.get(name));
        Object result = pjp.proceed();
        logger.info("From MethodCounterAspect after method call");
        return result;
    }

//    @Before("execution(* ru.raiffeisen.springaop.service.UserService.byId(..))")
//    public void counterMethod() {
//        logger.info("From MethodCounterAspect before method call");
//    }
}
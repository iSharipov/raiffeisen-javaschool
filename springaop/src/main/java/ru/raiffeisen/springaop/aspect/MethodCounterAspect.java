package ru.raiffeisen.springaop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MethodCounterAspect {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(MethodCounterAspect.class.getName());

    @Around("execution(* ru.raiffeisen.springaop.service.UserService.byId(..))")
    public Object counterMethod(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("From MethodCounterAspect before method call");
        Object result = pjp.proceed();
        logger.info("From MethodCounterAspect after method call");
        return result;
    }
}
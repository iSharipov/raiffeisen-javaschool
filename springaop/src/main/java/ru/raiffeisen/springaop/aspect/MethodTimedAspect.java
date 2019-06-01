package ru.raiffeisen.springaop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MethodTimedAspect {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(MethodTimedAspect.class.getName());

    @Around("execution(* ru.raiffeisen.springaop.service.UserService.*(..))")
    public Object timedMethod(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("From MethodTimedAspect before method call");
        Object result = pjp.proceed();
        logger.info("From MethodTimedAspect after method call");
        return result;
    }
}

package ru.raiffeisen.aspectj;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class UserServiceAspect {

//    @Before("execution(* ru.raiffeisen.aspectj.UserService.byId(..))")
//    public void doSomethingBefore() {
//        System.out.println("Before");
//    }
//
//    @AfterReturning("execution(* ru.raiffeisen.aspectj.UserService.byId(..))")
//    public void doSomethingAfterReturning() {
//        System.out.println("After returning");
//    }
//
//    @AfterReturning(
//            pointcut = "execution(* ru.raiffeisen.aspectj.UserService.byId(..))",
//            returning = "retVal")
//    public void doSomethingAfterReturning(Object retVal) {
//        System.out.println("After returning with returning");
//        System.out.println("From After Returning Aspect: " + retVal);
//    }
//
//    @AfterThrowing("execution(* ru.raiffeisen.aspectj.UserService.byId(..))")
//    public void doSomethingAfterThrowing() {
//        System.out.println("After throwing");
//    }
//
//    @AfterThrowing(
//            pointcut = "execution(* ru.raiffeisen.aspectj.UserService.byId(..))",
//            throwing = "ex")
//    public void doSomethingAfterThrowing(Throwable ex) {
//        System.out.println("After throwing with Exception");
//        System.out.println(ex.getMessage());
//    }
//
//    @After("execution(* ru.raiffeisen.aspectj.UserService.byId(..))")
//    public void doSomethingAfter() {
//        System.out.println("After");
//    }

//    @Around("execution(* ru.raiffeisen.aspectj.UserService.byId(..))")
//    public Object doSomethingAroundByMethodName(ProceedingJoinPoint pjp) throws Throwable {
//        System.out.println("Around start");
//        Object retVal = pjp.proceed();
//        System.out.println("From Around Aspect: " + retVal);
//        System.out.println("Around stop");
//        return retVal;
//    }

//    @Around("@annotation(ru.raiffeisen.aspectj.Counter)")
//    public Object doSomethingAroundByAnnotation(ProceedingJoinPoint pjp) throws Throwable {
//        System.out.println("Around start");
//        Object retVal = pjp.proceed();
//        System.out.println("From Around Aspect: " + retVal);
//        System.out.println("Around stop");
//        return retVal;
//    }

//    @Before("initialization(UserService.new(..)) && !within(UserServiceAspect)")
//    public void doSomethingInitialization() {
//        System.out.println("Before initialization");
//    }
//
//    @Before("staticinitialization(UserService+) && !within(UserServiceAspect)")
//    public void doSomethingStaticInitialization() {
//        System.out.println("Before static initialization");
//    }

//    @Before("call(* ru.raiffeisen.aspectj.UserService.byId(..))")
//    public void doCallBefore() {
//        System.out.println("Before");
//    }

}
package ru.raiffeisen.annotations;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.AggregateResourceBundleLocator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static java.util.Collections.singletonList;

public class MainEntryClass {

    public static void main(String[] args) {

        Validator validator = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(
                        new ResourceBundleMessageInterpolator(
                                new AggregateResourceBundleLocator(
                                        singletonList(
                                                "messages"
                                        )
                                )
                        )
                )
                .buildValidatorFactory()
                .getValidator();

        User user = new User();
        user.setName("Иван");
        user.setSirname("Иванов");
        user.setAge(25);
        user.setEmail("123@mail.ru");
        user.setInn("123");

        //Validate bean
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        //Show errors
        if (constraintViolations.size() > 0) {
            for (ConstraintViolation<User> violation : constraintViolations) {
                System.out.println(violation.getMessage());
            }
        } else {
            System.out.println("Valid Object");
        }
    }

}
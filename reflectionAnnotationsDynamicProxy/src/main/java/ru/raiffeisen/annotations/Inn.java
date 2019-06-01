package ru.raiffeisen.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = InnConstraintValidator.class)
public @interface Inn {
    String message() default "{javax.validation.constraints.Inn.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

package ru.raiffeisen.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class InnConstraintValidator implements ConstraintValidator<Inn, String> {
   public void initialize(Inn constraint) {
   }

   public boolean isValid(String obj, ConstraintValidatorContext context) {
      return obj != null && !obj.isEmpty();
   }
}

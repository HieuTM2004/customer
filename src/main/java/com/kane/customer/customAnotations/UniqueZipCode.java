package com.kane.customer.customAnotations;

import com.kane.customer.validator.UniqueZipCodeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueZipCodeValidator.class)
public @interface UniqueZipCode {
  String message() default "Zip code already exists";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}

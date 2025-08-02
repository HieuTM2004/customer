package com.kane.customer.customAnotations;

import com.kane.customer.validator.UniqueSSNValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueSSNValidator.class)
public @interface UniqueSSN {
  String message() default "Mobile phone number already exists";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}

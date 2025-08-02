package com.kane.customer.customAnotations;

import com.kane.customer.validator.EmailsEqualValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailsEqualValidator.class)
public @interface EmailsMustBeEqual {
  String message() default "Email must be the same as backup email";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}

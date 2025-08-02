package com.kane.customer.customAnotations;

import com.kane.customer.validator.UniqueMobilePhoneNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueMobilePhoneNumberValidator.class)
public @interface UniqueMobilePhoneNumber {
  String message() default "Mobile phone number already exists";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}

package com.kane.customer.validator;

import com.kane.customer.customAnotations.UniqueEmail;
import com.kane.customer.repository.CustomerRepo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

  private final CustomerRepo customerRepo;

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) return true;
    return !customerRepo.existsByEmail(value);
  }
}

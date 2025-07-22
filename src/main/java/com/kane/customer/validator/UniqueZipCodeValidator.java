package com.kane.customer.validator;

import com.kane.customer.customAnotations.UniqueZipCode;
import com.kane.customer.repository.AddressRepo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueZipCodeValidator implements ConstraintValidator<UniqueZipCode, String> {

  private final AddressRepo addressRepo;

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) return true;
    return !addressRepo.existsByZipCode(value);
  }
}

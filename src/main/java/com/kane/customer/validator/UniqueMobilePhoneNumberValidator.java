package com.kane.customer.validator;

import com.kane.customer.customAnotations.UniqueMobilePhoneNumber;
import com.kane.customer.repository.CustomerRepo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueMobilePhoneNumberValidator
    implements ConstraintValidator<UniqueMobilePhoneNumber, String> {

  private final CustomerRepo customerRepo;

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) return true; // Để @NotBlank xử lý null
    return !customerRepo.existsByMobilePhoneNumber(value);
  }
}

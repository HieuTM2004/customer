package com.kane.customer.validator;

import com.kane.customer.customAnotations.EmailsMustBeEqual;
import com.kane.customer.dto.request.CreateCustomerRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailsEqualValidator
    implements ConstraintValidator<EmailsMustBeEqual, CreateCustomerRequest> {

  @Override
  public boolean isValid(CreateCustomerRequest request, ConstraintValidatorContext context) {
    if (request == null || request.getEmail() == null || request.getBackupEmail() == null) {
      return true;
    }

    return request.getEmail().equalsIgnoreCase(request.getBackupEmail());
  }
}

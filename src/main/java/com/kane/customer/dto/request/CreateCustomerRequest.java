package com.kane.customer.dto.request;

import com.kane.customer.customAnotations.EmailsMustBeEqual;
import com.kane.customer.customAnotations.UniqueEmail;
import com.kane.customer.customAnotations.UniqueMobilePhoneNumber;
import com.kane.customer.customAnotations.UniqueSSN;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EmailsMustBeEqual
public class CreateCustomerRequest {
  @NotBlank(message = "First name is required")
  private String firstName;

  @NotBlank(message = "Last name is required")
  private String lastName;

  @Email(message = "Invalid email format")
  @NotBlank(message = "Email is required")
  @UniqueEmail
  private String email;

  @Email(message = "Invalid backup email format")
  private String backupEmail;

  @Pattern(regexp = "^\\+?[0-9]{9,15}$", message = "Invalid mobile number")
  @UniqueMobilePhoneNumber
  private String mobilePhoneNumber;

  private boolean isActive;

  @NotBlank(message = "SSN is required")
  @UniqueSSN
  private String ssn;

  private OffsetDateTime createdTime = OffsetDateTime.now();

  @Valid private AddressRequest address;
}

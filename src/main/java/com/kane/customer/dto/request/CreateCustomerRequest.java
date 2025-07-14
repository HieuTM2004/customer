package com.kane.customer.dto.request;

import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCustomerRequest {
  private String firstName;

  private String lastName;

  private String email;

  private String backupEmail;

  private String mobilePhoneNumber;

  private boolean isActive;

  private String ssn;

  private OffsetDateTime createdTime = OffsetDateTime.now();

  private AddressRequest address;
}

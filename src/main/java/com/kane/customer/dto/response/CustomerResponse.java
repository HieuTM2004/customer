package com.kane.customer.dto.response;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse implements Serializable {
  private static final long serialVersionUID = 1L;

  private UUID uuid;

  private String firstName;

  private String lastName;

  private String email;

  private String backupEmail;

  private String mobilePhoneNumber;

  private boolean isActive;

  private String ssn;

  private OffsetDateTime createdTime;

  private OffsetDateTime updatedTime;

  private AddressResponse address;
}

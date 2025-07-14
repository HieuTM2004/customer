package com.kane.customer.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest {
  private String addressLine1;

  private String addressLine2;

  private String zipCode;

  private String city;
}

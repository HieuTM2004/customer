package com.kane.customer.dto.response;

import java.io.Serializable;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponse implements Serializable {
  private static final long serialVersionUID = 1L;

  private UUID uuid;

  private String addressLine1;

  private String addressLine2;

  private String zipCode;

  private String city;
}

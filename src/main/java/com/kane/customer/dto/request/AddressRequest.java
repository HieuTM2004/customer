package com.kane.customer.dto.request;

import com.kane.customer.customAnotations.UniqueZipCode;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest {
  @NotBlank(message = "Address line 1 is required")
  private String addressLine1;

  private String addressLine2;

  @NotBlank(message = "Zip code is required")
  @UniqueZipCode
  private String zipCode;

  @NotBlank(message = "City is required")
  private String city;
}

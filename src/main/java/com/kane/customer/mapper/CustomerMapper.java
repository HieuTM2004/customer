package com.kane.customer.mapper;

import com.kane.customer.dto.request.AddressRequest;
import com.kane.customer.dto.request.CreateCustomerRequest;
import com.kane.customer.dto.response.CustomerResponse;
import com.kane.customer.model.Address;
import com.kane.customer.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
  @Mapping(source = "address", target = "address")
  Customer toCustomer(final CreateCustomerRequest createCustomerRequest);

  Address toAddress(final AddressRequest addressRequest);

  @Mapping(source = "address", target = "address")
  CustomerResponse toCustomerDTO(final Customer customer);
}

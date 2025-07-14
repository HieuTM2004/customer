package com.kane.customer.service;

import com.kane.customer.dto.request.CreateCustomerRequest;
import com.kane.customer.model.Customer;
import java.util.Optional;

public interface CustomerService {
  Customer createCustomer(final CreateCustomerRequest createCustomerRequest);

  Optional<Customer> findByEmail(final String username);
}

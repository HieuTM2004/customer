package com.kane.customer.controller;

import com.kane.common.exception.NoFoundException;
import com.kane.customer.dto.request.CreateCustomerRequest;
import com.kane.customer.dto.response.CustomerResponse;
import com.kane.customer.mapper.CustomerMapper;
import com.kane.customer.model.Customer;
import com.kane.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/customer")
public class CustomerController {
  private final CustomerService customerService;
  private final CustomerMapper customerMapper;

  @PostMapping("/create-customer")
  public ResponseEntity<CustomerResponse> createCustomer(
      @Valid @RequestBody CreateCustomerRequest createCustomerRequest) {
    log.info("Create Customer Request: {}", createCustomerRequest);

    Customer customer = customerService.createCustomer(createCustomerRequest);
    CustomerResponse customerResponse = customerMapper.toCustomerDTO(customer);
    return ResponseEntity.ok(customerResponse);
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<CustomerResponse> getCustomerByEmail(@PathVariable String email) {
    Customer customer =
        customerService
            .findByEmail(email)
            .orElseThrow(() -> new NoFoundException("Customer not found with email: " + email));
    CustomerResponse customerResponse = customerMapper.toCustomerDTO(customer);

    log.info("Get Customer By Email: {}", customerResponse.getAddress());
    return ResponseEntity.ok(customerMapper.toCustomerDTO(customer));
  }
}

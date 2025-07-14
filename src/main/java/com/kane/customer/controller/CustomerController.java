package com.kane.customer.controller;

import com.kane.common.exception.NoFoundException;
import com.kane.common.response.SuccessResponse;
import com.kane.customer.dto.request.CreateCustomerRequest;
import com.kane.customer.dto.response.CustomerResponse;
import com.kane.customer.mapper.CustomerMapper;
import com.kane.customer.model.Customer;
import com.kane.customer.service.CustomerService;
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

  @PostMapping("/create-customer")
  public ResponseEntity<SuccessResponse<CustomerResponse>> createCustomer(
      @RequestBody CreateCustomerRequest createCustomerRequest) {
    log.info("Create Customer Request: {}", createCustomerRequest);

    Customer customer = customerService.createCustomer(createCustomerRequest);
    CustomerResponse customerResponse = CustomerMapper.INSTANCE.toCustomerDTO(customer);
    return ResponseEntity.ok(SuccessResponse.of(customerResponse));
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<SuccessResponse<CustomerResponse>> getCustomerByEmail(
      @PathVariable String email) {
    Customer customer =
        customerService
            .findByEmail(email)
            .orElseThrow(() -> new NoFoundException("Customer not found with email: " + email));
    CustomerResponse customerResponse = CustomerMapper.INSTANCE.toCustomerDTO(customer);

    log.info("Get Customer By Email: {}", customerResponse.getAddress());
    return ResponseEntity.ok(SuccessResponse.of(CustomerMapper.INSTANCE.toCustomerDTO(customer)));
  }
}

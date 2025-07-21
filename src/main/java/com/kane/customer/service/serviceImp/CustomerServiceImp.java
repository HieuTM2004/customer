package com.kane.customer.service.serviceImp;

import com.kane.common.exception.DatabaseConstraintViolationException;
import com.kane.customer.dto.request.CreateCustomerRequest;
import com.kane.customer.mapper.CustomerMapper;
import com.kane.customer.model.Address;
import com.kane.customer.model.Customer;
import com.kane.customer.repository.AddressRepo;
import com.kane.customer.repository.CustomerRepo;
import com.kane.customer.service.CustomerService;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImp implements CustomerService {
  private final CustomerRepo customerRepo;
  private final AddressRepo addressRepo;
  private final CustomerMapper customerMapper;

  private static final Map<String, String> CONSTRAINT_MESSAGES =
      Map.of(
          "address_zip_code_key", "Zip code already exists.",
          "customer_email_key", "Email already exists.",
          "email_equals_backup_email", "Email must not be the same as backup email.");

  @Transactional
  @Override
  public Customer createCustomer(final CreateCustomerRequest createCustomerRequest) {
    try {
      log.info("createCustomerRequest: " + createCustomerRequest);
      Address address = customerMapper.toAddress(createCustomerRequest.getAddress());
      addressRepo.save(address);

      Customer customer = customerMapper.toCustomer(createCustomerRequest);
      customer.setAddress(address);
      customer.setActive(false);
      customerRepo.save(customer);

      return customer;

    } catch (DataIntegrityViolationException e) {
      String rootMsg = e.getRootCause() != null ? e.getRootCause().getMessage() : e.getMessage();

      log.error("PostgreSQL Error: " + rootMsg);
      System.out.println(">>> PostgreSQL Error: " + rootMsg);

      String message =
          CONSTRAINT_MESSAGES.entrySet().stream()
              .filter(entry -> rootMsg != null && rootMsg.contains(entry.getKey()))
              .map(Map.Entry::getValue)
              .findFirst()
              .orElse("Data constraint violation: " + rootMsg);

      throw new DatabaseConstraintViolationException(message);
    }
  }

  @Override
  public Optional<Customer> findByEmail(String username) {
    return customerRepo.findByEmail(username);
  }
}

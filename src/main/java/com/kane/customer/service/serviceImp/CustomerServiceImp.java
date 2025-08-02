package com.kane.customer.service.serviceImp;

import com.kane.customer.dto.request.CreateCustomerRequest;
import com.kane.customer.mapper.CustomerMapper;
import com.kane.customer.model.Address;
import com.kane.customer.model.Customer;
import com.kane.customer.repository.AddressRepo;
import com.kane.customer.repository.CustomerRepo;
import com.kane.customer.service.CustomerService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImp implements CustomerService {
  private final CustomerRepo customerRepo;
  private final AddressRepo addressRepo;
  private final CustomerMapper customerMapper;

  @Transactional
  @Override
  public Customer createCustomer(final CreateCustomerRequest createCustomerRequest) {
    log.info("createCustomerRequest: {}", createCustomerRequest);
    Address address = customerMapper.toAddress(createCustomerRequest.getAddress());
    addressRepo.save(address);

    Customer customer = customerMapper.toCustomer(createCustomerRequest);
    customer.setAddress(address);
    customer.setActive(false);
    customerRepo.save(customer);

    return customer;
  }

  @Override
  public Optional<Customer> findByEmail(String username) {
    return customerRepo.findByEmail(username);
  }
}

package com.kane.customer.service.serviceImp;

import com.kane.customer.dto.request.CreateCustomerRequest;
import com.kane.customer.dto.response.CustomerResponse;
import com.kane.customer.mapper.CustomerMapper;
import com.kane.customer.model.Address;
import com.kane.customer.model.Customer;
import com.kane.customer.repository.AddressRepo;
import com.kane.customer.repository.CustomerRepo;
import com.kane.customer.service.CustomerService;
import java.util.List;
import java.util.Optional;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImp implements CustomerService {
  private final CustomerRepo customerRepo;
  private final AddressRepo addressRepo;
//  @Autowired
//  @Resource
  private final CustomerMapper customerMapper;

  @Transactional
  @Override
  @CacheEvict(value = "allCustomers", allEntries = true)
  // value is the name of the cache to evict
  // allEntries = true to evict all entries in the cache
  // if don't use allEntries = true, it will evict only the entry with the key
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

  @Override
  @Cacheable(value = "allCustomers")
  public List<CustomerResponse> getAllCustomers() {
    log.info("Fetching all customers");
    List<Customer> customers = customerRepo.findAll();
    return customers.stream().map(customerMapper::toCustomerDTO).toList();
  }
}
